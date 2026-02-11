package org.doit.ik.emp.service;

import org.doit.ik.dept.dto.EmpDTO;
import org.doit.ik.dept.dto.PageRequestDTO;
import org.doit.ik.dept.dto.PageResultDTO;
import org.doit.ik.dept.entity.Dept;
import org.doit.ik.dept.entity.Emp;


public interface EmpService {

    // [1] DTO -> Entity 변환
    default Emp dtoToEntity(EmpDTO dto) {
        Dept dept = Dept.builder().deptno(dto.getDeptno()).build();

        return Emp.builder()
                .empno(dto.getEmpno())
                .ename(dto.getEname())
                .job(dto.getJob())
                .mgr(dto.getMgr())
                .hiredate(dto.getHiredate())
                .sal(dto.getSal())
                .comm(dto.getComm())
                .dept(dept) // 연관관계 설정
                .build();
    }

    // [2] Entity -> DTO 변환 (BoardService의 entityToDto와 동일한 로직)
    default EmpDTO entityToDto(Emp emp, Dept dept) {
        return EmpDTO.builder()
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

    // 서비스 메서드 정의
    PageResultDTO<EmpDTO, Object[]> getList(PageRequestDTO requestDTO);
    EmpDTO get(Integer empno);
}