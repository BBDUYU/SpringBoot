package org.doit.ik.emp.repository;

import org.doit.ik.dept.entity.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

    @Query("SELECT e, d " +
           " FROM Emp e LEFT JOIN e.dept d " +
           " WHERE e.empno = :empno")
    Object getEmpWithDept(@Param("empno") Integer empno);

    @Query(value = "SELECT e, d " +
                   " FROM Emp e LEFT JOIN e.dept d ",
           countQuery = "SELECT COUNT(e) FROM Emp e")
    Page<Object[]> getEmpListWithDept(Pageable pageable);

}