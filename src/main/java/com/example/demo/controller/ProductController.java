package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;


@RestController
public class ProductController 
{
	@Autowired
	private ProductService service;

	//http://localhost:9090/products
	@GetMapping("/products")
	public List<Product> list() {
		return service.listAll();
	}
	//[{"id":1,"name":"iPhone X","price":999.98}]


	//http://localhost:9090/products/1
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> get(@PathVariable Integer id) {
		try {
			Product product = service.get(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}      
	}

	/*
		 {
		"id":3,
		"name" : "Jasvin",
		"price" : "405"
		}
	*/
	// http://localhost:9090/products
	@PostMapping("/products")
	public void add(@RequestBody Product product) 
	{
		service.save(product);
	}

	/*    {	"id": 2,	 "name": "Dhatri2",	   "price": 305	   }	 */
	//http://localhost:9090/products/2
	@PutMapping("/products/{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id) {
		try {
			Product existProduct = service.get(id);
			service.save(product);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}      
	}

	//http://localhost:9090/products/2
	@DeleteMapping("/products/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}
