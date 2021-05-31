package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.ChildCategory;
import com.c1120g1.adweb.service.ChildCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ChildCategoryController {

    @Autowired
    private ChildCategoryService service;

    @GetMapping("api/child-category/{id}")
    public ResponseEntity<List<ChildCategory>> getChildCategories(@PathVariable(name = "id") Integer id){
        List<ChildCategory> categoryList = service.getAllChildCategoryByCategoryId(id);
        if (categoryList.isEmpty()){
            return new ResponseEntity<>(categoryList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
