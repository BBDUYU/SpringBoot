package org.doit.ik.emp.repository;


import org.doit.ik.emp.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp, Integer>, SearchEmpRepository {
}