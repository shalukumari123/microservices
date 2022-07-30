package com.demo.spring.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Dept;
import com.demo.spring.repository.DeptRepository;
import com.demo.spring.util.Message;

@RestController
public class DeptRestController {

	@Autowired
	private DeptRepository repo;

	@GetMapping(path = "/dept/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findOneDept(@PathVariable("id") int id) {
		Optional<Dept> deptData = repo.findById(id);
		if (deptData.isPresent()) {
			return ResponseEntity.ok(deptData.get());
		} else {
			
			return ResponseEntity.status(404).build();
		}
	}

	@GetMapping(path = "/dept", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Dept>> getAllDeps() {
		return ResponseEntity.ok(repo.findAll());
	}
}
