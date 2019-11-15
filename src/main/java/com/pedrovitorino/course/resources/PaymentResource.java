package com.pedrovitorino.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedrovitorino.course.dto.PaymentDTO;
import com.pedrovitorino.course.entities.Payment;
import com.pedrovitorino.course.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<PaymentDTO>> findAll() {
		List<PaymentDTO> paymentList = service.findAll();
		return ResponseEntity.ok().body(paymentList);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<PaymentDTO> findById(@PathVariable Long id) {
		PaymentDTO payment = service.findById(id);
		return ResponseEntity.ok().body(payment);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PaymentDTO> insert(@RequestBody PaymentDTO obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<PaymentDTO> update(@PathVariable Long id, @RequestBody PaymentDTO obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
