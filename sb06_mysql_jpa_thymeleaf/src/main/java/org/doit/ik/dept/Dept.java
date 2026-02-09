package org.doit.ik.dept;

import java.util.List;

import org.doit.ik.emp.Emp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // 엔티티 클래스   1:1 매핑 DB 테이블
@Table(name = "dept")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dept {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql에는 시퀀스 개념 X
   private Integer deptno;
   
   @Column(length = 14, nullable = true)
   private String dname;
   @Column(length = 13, nullable = true)
   private String loc;
   
   // Dept 엔티티를 기준으로 Emp 엔티티와 연관관계 
   //  1      :        N
   @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Emp> empList;
   
}
