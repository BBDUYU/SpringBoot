package org.doit.ik.dept.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
	private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private LocalDate hiredate;
    private BigDecimal sal;
    private BigDecimal comm;

    private Integer deptno;
    private String dname;
    private String loc;
}