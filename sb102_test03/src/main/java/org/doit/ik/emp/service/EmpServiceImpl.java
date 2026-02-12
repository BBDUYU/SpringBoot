package org.doit.ik.emp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.doit.ik.emp.entity.Dept; 
import org.doit.ik.emp.entity.Emp; 
import org.doit.ik.emp.dto.EmpDeptDTO;
import org.doit.ik.emp.dto.PageRequestDTO;
import org.doit.ik.emp.dto.PageResultDTO;
import org.doit.ik.emp.repository.EmpRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

    private final EmpRepository empRepository;

    @Override
    public PageResultDTO<EmpDeptDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        
        log.info("@ EmpServiceImpl.getList()..." + pageRequestDTO);

        Page<Object[]> result = empRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("empno").descending())
        );

        Function<Object[], EmpDeptDTO> fn = (en -> {
            Emp emp = (Emp) en[0];
            Dept dept = (Dept) en[1];
            return entityToDto(emp, dept);
        });

        return new PageResultDTO<>(result, fn);
    }
}