package org.doit.ik.emp;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.doit.ik.dept.Dept;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 엔티티 클래스   1:1 매핑 DB 테이블
@Table(name = "tbl_emp")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Emp {
   
   @Id
   @Column(name = "empno", nullable = false)
   private Integer empno;
   
   @Column(name = "ename",length = 10)
   private String ename;
   
   @Column(name = "job", length = 9)
   private String job;
   
   @Column(name = "mgr")
   private Integer mgr;
   
   @Column(name = "hiredate")
   private LocalDate hiredate;
   
   @Column(name = "sal", precision = 7, scale = 2)
   private BigDecimal sal;
   
   @Column(name = "comm", precision = 7, scale = 2)
   private BigDecimal comm;
   
   // Emp 엔티티를 기준으로 Dept 엔티티와 [연관관계]
   // deptno         deptno 컬럼
   // 외래키(FK) 설정
   @ManyToOne
   @JoinColumn(name = "deptno") // Emp 테이블의 deptno 컬럼( FK )
   private Dept dept;
}
