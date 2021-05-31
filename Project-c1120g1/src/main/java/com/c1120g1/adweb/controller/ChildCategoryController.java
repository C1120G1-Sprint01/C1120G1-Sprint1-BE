package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.ChildCategory;
import com.c1120g1.adweb.service.ChildCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/child-category")
@CrossOrigin(origins = "http://localhost:4200")
public class ChildCategoryController {

    @Autowired
    ChildCategoryService childCategoryService;

    @GetMapping("{categoryId}")
    public ResponseEntity<List<ChildCategory>> findAllByCategoryId(@PathVariable Integer categoryId) {
        List<ChildCategory> childCategoryList = childCategoryService.findAllByCategoryId(categoryId);

        if (childCategoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(childCategoryList, HttpStatus.OK);
    }
}
