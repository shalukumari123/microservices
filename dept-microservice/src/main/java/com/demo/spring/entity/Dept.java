package com.demo.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="MYDEPT")
public class Dept {
	@Id
	@Column(name="DNO")
	private int deptNo;
	
	@Column(name="DNAME")
	private String deptName;
	
	private String manager;
	
	public Dept() {

	}

	public Dept(int deptNo, String deptName, String manager) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.manager = manager;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

}
