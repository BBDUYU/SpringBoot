package org.doit.ik.emp.service;

import java.util.Optional;
import java.util.function.Function;

import org.doit.ik.dept.dto.EmpDTO;
import org.doit.ik.dept.dto.PageRequestDTO;
import org.doit.ik.dept.dto.PageResultDTO;
import org.doit.ik.dept.entity.Dept;
import org.doit.ik.dept.entity.Emp;
import org.doit.ik.emp.repository.EmpRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

    private final EmpRepository empRepository; // Repository가 있다고 가정

    @Override
    public PageResultDTO<EmpDTO, Object[]> getList(PageRequestDTO requestDTO) {
        log.info("🍀 Emp 목록 조회: " + requestDTO);

        Pageable pageable = requestDTO.getPageable(Sort.by("empno").descending());

        // 수정: getEmpWithDept -> getEmpListWithDept (Repository에 정의된 이름)
        Page<Object[]> result = empRepository.getEmpListWithDept(pageable);

        Function<Object[], EmpDTO> fn = (en -> entityToDto(
                (Emp)en[0], 
                (Dept)en[1]
        ));

        return new PageResultDTO<>(result, fn);
    }

	
	@Override
	public EmpDTO get(Integer empno) {
		// TODO Auto-generated method stub
		return null;
	}
}