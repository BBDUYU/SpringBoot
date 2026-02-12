package org.doit.ik.emp.service;


import org.doit.ik.emp.dto.EmpDeptDTO;
import org.doit.ik.emp.dto.PageRequestDTO;
import org.doit.ik.emp.dto.PageResultDTO;
import org.doit.ik.emp.entity.Dept;
import org.doit.ik.emp.entity.Emp;

public interface EmpService {

    PageResultDTO<EmpDeptDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    default Emp dtoToEntity(EmpDeptDTO dto) {
        Dept dept = Dept.builder().deptno(dto.getDeptno()).build();

        return Emp.builder()
                .empno(dto.getEmpno())
                .ename(dto.getEname())
                .job(dto.getJob())
                .mgr(dto.getMgr())
                .hiredate(dto.getHiredate())
                .sal(dto.getSal())
                .comm(dto.getComm())
                .dept(dept)
                .build();
    }

    default EmpDeptDTO entityToDto(Emp emp, Dept dept) {
        return EmpDeptDTO.builder()
                .empno(emp.getEmpno())
                .ename(emp.getEname())
                .job(emp.getJob())
                .mgr(emp.getMgr())
                .hiredate(emp.getHiredate())
                .sal(emp.getSal())
                .comm(emp.getComm())
                .deptno(dept.getDeptno())
                .dname(dept.getDname())
                .loc(dept.getLoc())

                .build();
    }
}