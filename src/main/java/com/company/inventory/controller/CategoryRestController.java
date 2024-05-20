package com.company.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.model.category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;

@RestController
@RequestMapping("/api/v1")

public class CategoryRestController {
	@Autowired
	private ICategoryService service;
	/**
	 * get all categories
	 * @return
	 */
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest>searchCategories(){
		ResponseEntity<CategoryResponseRest> response = service.search();
		return response;
	}
	
	/**
	 * get categories by id
	 * @param id
	 * @return
	 */
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest>searchCategoriesById(@PathVariable Long id){
		ResponseEntity <CategoryResponseRest>response = service.searchById(id);
		return response;
	}
	/**
	 * save categories
	 * @param category
	 * @return
	 */
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest>save (@RequestBody category Category){
		ResponseEntity <CategoryResponseRest>response = service.save(Category);
		return response;
	}
	
	/**
	 * update categories
	 * @param Category
	 * @param id
	 * @return
	 */
	@PutMapping ("/categories/{id}")
	public ResponseEntity<CategoryResponseRest>update(@RequestBody category Category, @PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = service.update(Category, id);
		return response;
	}
	
	
}
