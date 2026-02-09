package org.doit.ik.dept;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer>{

   List<Dept> findAll();
   
   Page<Dept> findAll(Pageable pageable);
   
}
