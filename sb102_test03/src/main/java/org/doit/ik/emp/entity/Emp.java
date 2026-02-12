package org.doit.ik.emp.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "emp")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "dept") 
public class Emp {

    @Id
    private Integer empno;

    private String ename;
    private String job;
    private Integer mgr;
    private LocalDate hiredate;

    @Column(precision = 7, scale = 2)
    private BigDecimal sal;

    @Column(precision = 7, scale = 2)
    private BigDecimal comm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptno")
    private Dept dept;
}