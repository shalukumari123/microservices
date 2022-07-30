package com.demo.spring;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.spring.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer>{

	public List<Emp> findAllByDeptNo(int deptNo);
	
	
	@Query(value = "select e from Emp e where e.salary between ?1 and ?2")
	public List<Emp> findAllBySalaryRange(double r1, double r2);
	
}
