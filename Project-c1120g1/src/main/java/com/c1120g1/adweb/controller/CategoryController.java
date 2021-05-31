package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Category;
import com.c1120g1.adweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("api/category")
    public ResponseEntity<List<Category>> getCategory(){
        if (service.findAll().isEmpty()){
            return new ResponseEntity<>(service.findAll(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
