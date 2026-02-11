package org.doit.ik.dept.entity;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Dept{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deptno;

	private String dname;
	private String loc;

	@OneToMany(mappedBy = "dept")
	private List<Emp> empList;


}
