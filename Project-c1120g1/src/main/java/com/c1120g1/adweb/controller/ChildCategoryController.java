package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.ChildCategory;
import com.c1120g1.adweb.service.ChildCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChildCategoryController {
    @Autowired
    private ChildCategoryService service;

    @GetMapping("listChildCategory")
    public ResponseEntity<List<ChildCategory>> getChildCategories(){
        if (service.findAll().isEmpty()){
            return new ResponseEntity<>(service.findAll(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }


}
