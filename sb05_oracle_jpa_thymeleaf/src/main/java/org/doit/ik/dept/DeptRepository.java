package org.doit.ik.dept;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface DeptRepository extends JpaRepository<Dept,Integer>{
	List<Dept> findAll();
	
	Dept findByDname(String dname);
	List<Dept> findByLoc(String loc);
	
	@Query("SELECT d FROM Dept d")
	List<Dept> gettAllDepts();
	
	Page<Dept> findAll(Pageable pageable);
	
	// [1] 부서 수정: @Modifying + @Query 사용 (직접 SQL 작성)
	   @Modifying
	   @Transactional
	   @Query("UPDATE Dept d SET d.dname = :dname, d.loc = :loc WHERE d.deptno = :deptno")
	   int updateDept(
	         @Param("deptno") Integer deptno,
	            @Param("dname") String dname,
	            @Param("loc") String loc);
}
