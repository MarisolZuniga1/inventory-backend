package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.model.category;
import com.company.inventory.response.CategoryResponseRest;
@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search(){
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			List<category> category = (List<com.company.inventory.model.category>) categoryDao.findAll();
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Respuesta ok","00", "Respuesta exitosa");
		} catch(Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override 
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List <category> list = new ArrayList<>();
		try {
			Optional<category> Category =categoryDao.findById(id);
			if(Category.isPresent()) {
				list.add(Category.get());
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta ok","00", "categoria encontrada");
			}
			else {
				response.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> save(category Category) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<category> list = new ArrayList<>();
		try {
			category categorySaved = categoryDao.save (Category);
			if(categorySaved != null) {
				list.add(categorySaved);
				response.getCategoryResponse().setCategory(list);	
				response.setMetadata("Repuesta ok","00","categoria guardada");
			}else {
				response.setMetadata("Respuesta nok", "-1", "Categoria no guardada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al grabar la categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Transactional 
	public ResponseEntity<CategoryResponseRest> update(category category, Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<category> list = new ArrayList<>();
		try {
			Optional<category> CategorySearch= categoryDao.findById(id);
			if(CategorySearch.isPresent()) {
				CategorySearch.get().setName(category.getName());
				CategorySearch.get().setDescripcion(category.getDescripcion());
				
				category categoryToUpdate = categoryDao.save(CategorySearch.get());
				if(categoryToUpdate !=null) {
					list.add(categoryToUpdate);
					response.getCategoryResponse().setCategory(list);
					response.setMetadata("Respuesta ok", "00", "categoria actualizada");
				}else {
					response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
					}
				}
		}catch (Exception e){
			response.setMetadata("Respuesta nok", "-1", "Error al actulizar la categoria");
			e.getStackTrace();
			return new ResponseEntity <CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			categoryDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Registro eliminado");

		}catch(Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}