package com.demo.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.entity.Dept;
import com.demo.entity.Emp;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class HrRestController {

	@Autowired
	private RestTemplate rt;
	@Autowired
	DiscoveryClient dc;
	
	@Autowired
	DemoService service;

	@GetMapping(path = "/hr/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getEmpDetails(@PathVariable int id) {
		ResponseEntity<String> resp = rt.getForEntity("http://emp-service/emp/" + id, String.class);
		// System.out.println(resp.getStatusCodeValue()+" "+resp.getBody());
		return resp;
	}

	@GetMapping(path = "/hr/dept/{dno}/emplist")
	@CircuitBreaker(name = "demo", fallbackMethod = "fallbackListEmpInDept")
	public ResponseEntity listEmpInDept(@PathVariable("dno") int dno) {
		System.out.println("Calls for failure");
		dc.getInstances("emp-service").stream().forEach(s -> System.out.println(s.getHost() + ":" + s.getPort()));
		ResponseEntity<Dept> deptResp = rt.getForEntity("http://dept-service/dept/" + dno, Dept.class);
		System.out.println(deptResp.getStatusCodeValue());
		ResponseEntity<List<Emp>> empListResp = rt.exchange("http://emp-service/emp/list/" + dno, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Emp>>() {
				});
		Dept dept = deptResp.getBody();

		dept.setEmps(empListResp.getBody());
		return ResponseEntity.ok(dept);

	}

	public ResponseEntity fallbackListEmpInDept(int id, Exception ex) {
		return ResponseEntity.ok("Service is Down, try after some time");
	}

	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<String>
	 * handleError(Exception ex) { String status=ex.getMessage().substring(0, 3);
	 * String message=ex.getMessage().substring(5);
	 * System.out.println(status+" "+message); return
	 * ResponseEntity.ok(ex.getMessage()); }
	 */

	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<String>
	 * handleError(Exception ex) { return ResponseEntity.ok(ex.getMessage()); }
	 */

}
