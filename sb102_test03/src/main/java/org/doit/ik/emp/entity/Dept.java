package org.doit.ik.emp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "dept")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Dept {

    @Id
    private Integer deptno;

    @Column(length = 14)
    private String dname;

    @Column(length = 13)
    private String loc;
}