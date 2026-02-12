package org.doit.ik.emp.repository;


import org.doit.ik.emp.entity.Emp;
import org.doit.ik.emp.entity.QDept;
import org.doit.ik.emp.entity.QEmp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;

import java.util.List;
import java.util.stream.Collectors;

public class SearchEmpRepositoryImpl extends QuerydslRepositorySupport implements SearchEmpRepository {

    public SearchEmpRepositoryImpl() {
        super(Emp.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        QEmp emp = QEmp.emp;
        QDept dept = QDept.dept;

        JPQLQuery<Emp> query = from(emp);
        query.leftJoin(dept).on(emp.dept.eq(dept));

        JPQLQuery<Tuple> tuple = query.select(emp, dept);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = emp.empno.gt(0);
        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();
            for (String t : typeArr) {
                switch (t) {
                    case "n":
                        conditionBuilder.or(emp.ename.contains(keyword));
                        break;
                    case "d":
                        conditionBuilder.or(dept.dname.contains(keyword));
                        break;
                    case "j": 
                        conditionBuilder.or(emp.job.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }

        tuple.where(booleanBuilder);

        this.getQuerydsl().applyPagination(pageable, tuple);

        List<Tuple> result = tuple.fetch();
        long count = tuple.fetchCount();

        return new PageImpl<>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count
        );
    }
}