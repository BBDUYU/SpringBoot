package org.doit.ik.board.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.QBoard;
import org.doit.ik.board.entity.QMember;
import org.doit.ik.board.entity.QReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{

	public SearchBoardRepositoryImpl() {
		super(Board.class);
	}

	//board + member + reply 엔티티를 조인
	//검색
	//페이징 처리결과 ...
	@Override
	public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
		log.info("@ SearchBoardRepositoryImpl.searchPage()....");
		// 1. Q도메인 클래스의 객체를 얻어왔다
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		QMember member = QMember.member;
		// 2. JPQLQuery 객체 생성
		JPQLQuery<Board> jpqlQuery = from(board); // from Board
		jpqlQuery.leftJoin(member).on(board.writer.eq(member)); // left outer join Member m 조인 조건
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board)); 

		JPQLQuery<Tuple> tuple = jpqlQuery.select(board,member,reply.count());

		// 검색조건
		BooleanBuilder booleanBuilder = new BooleanBuilder();


		BooleanExpression booleanExpression = board.bno.gt(0L);
		booleanBuilder.and(booleanExpression);

		if ( type != null) {
			String [] typeArr = type.split(""); // t c w
			BooleanBuilder conditionBuilder = new BooleanBuilder();
			for (String t : typeArr) {
				switch (t) {
				case "t":  
					conditionBuilder.or(board.title.contains(keyword));
					break; 
				case "c":  
					conditionBuilder.or(board.content.contains(keyword));
					break; 
				case "w":  
					conditionBuilder.or(member.email.contains(keyword));
					break;
				} // switch
			} // for
			booleanBuilder.and(conditionBuilder);
		} // if
		tuple.where(booleanBuilder);
		tuple.groupBy(board);
		
		// 3. 페이징처리
		this.getQuerydsl().applyPagination(pageable, tuple);
		
		// 4. 쿼리 실행
		List<Tuple> result = tuple.fetch();
		// 4-2. 총 레코드 수
		long count = tuple.fetchCount();
		
		// 5. List<Tuple> -> Page<Object[]> 변환시켜서 리턴값
		// result List<Tuple>
		// .stream()  Stream<Tuple> 변환
		// .map() Stream<Object []>
		// .collect() List<Object[]> 변환 + pageable + count
		// Page<Object[]>
		return new PageImpl<Object[]>(
	            result.stream().map(Tuple::toArray).collect(Collectors.toList())  // List<T> content
	            , pageable
	            , count
	            );
	}

}
