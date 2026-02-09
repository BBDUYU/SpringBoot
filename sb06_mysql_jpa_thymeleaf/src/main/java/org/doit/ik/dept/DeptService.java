package org.doit.ik.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
	@Autowired
	private DeptRepository deptRepository;
	
	public List<Dept> getDepts(){
		return this.deptRepository.findAll();
	}
	
	// 페이징처리
	public Page<Dept> getDeptsByPage(int pageNum, int pageSize){
		Pageable pageable = PageRequest.of(pageNum-1, pageSize);
		return this.deptRepository.findAll(pageable);
	}
	
	public Dept saveDept(Dept dept) {
		return this.deptRepository.save(dept);
	}
	
	public void updateDept(Dept dept) {
		Dept d = this.deptRepository
			.findById(dept.getDeptno())
			.orElseThrow(()->new RuntimeException("부서없음"));
		this.deptRepository.save(dept);	
	}
	
	public void deleteDept(Integer deptno) {
		this.deptRepository.deleteById(deptno);	
	}
}
