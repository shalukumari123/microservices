package com.demo.spring.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.EmpRepository;
import com.demo.spring.entity.Emp;

@RestController
public class EmpRestController {

	@Autowired
	EmpRepository repo;

	@GetMapping(path = "/emp/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity findEmpById(@PathVariable("id") int id) {
		Optional<Emp> empOp = repo.findById(id);
		if (empOp.isPresent()) {
			return ResponseEntity.ok(empOp.get());
		} else {
			return ResponseEntity.status(404).body("{\"status\":\"Emp Not Found\"}");
		}
	}

	@PostMapping(value = "/emp", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addEmp(@RequestBody Emp e) {
		
		if(repo.existsById(e.getEmpId())) {
			return ResponseEntity.ok("{\"status\":\"Emp Exists\"}");
		}else {
			repo.save(e);
			return ResponseEntity.ok("{\"status\":\"Emp saved..\"}");
		}
	}
	
	@GetMapping(path = "/emp")
	public ResponseEntity<List<Emp>> listAll(){
		return ResponseEntity.ok(repo.findAll());
		
	}
	
	@GetMapping(path="/emp/list/{dno}")
	public ResponseEntity getAllByDno(@PathVariable("dno")int dno)
	{
		return ResponseEntity.ok(repo.findAllByDeptNo(dno));
	}
}
