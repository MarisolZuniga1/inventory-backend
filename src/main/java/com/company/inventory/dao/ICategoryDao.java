package com.company.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.inventory.model.category;

public interface ICategoryDao extends CrudRepository<category, Long>{

}
