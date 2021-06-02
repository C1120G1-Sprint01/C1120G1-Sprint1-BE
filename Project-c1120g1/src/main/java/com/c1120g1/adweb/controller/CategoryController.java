package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Category;
import com.c1120g1.adweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Method: get all category
     * Author: TuanLHM
     *
     * @return
     */
    @GetMapping("/main-category/category/")
    public ResponseEntity<List<Category>> getList() {
        List<Category> categoryList = categoryService.findAllCategory();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
    }

    /**
     * Method: get category by id
     * Author: TuanLHM
     *
     * @return
     */

    @GetMapping("/main-category/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        return new ResponseEntity<>(this.categoryService.findCategoryById(id), HttpStatus.OK);
    }

    /**
     * Method: create category
     * Author: TuanLHM
     *
     * @return
     */

    @PostMapping("/main-category/category/create-category")
    public ResponseEntity<Void> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        categoryService.addCategory(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/category/{id}").buildAndExpand(category.getCategoryId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-----------------------update category-------------------------------

    /**
     * Method: edit category
     * Author: TuanLHM
     *
     * @return
     */

    @PutMapping("/main-category/category/edit-category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category category) {

        Category category1 = categoryService.findCategoryById(id);

        if (category1 == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        } else {

            category1.setCategoryName(category.getCategoryName());
            category1.setChildCategorySet(category.getChildCategorySet());
            category1.setDeleteFlag(false);
            category1.setCategoryId(id);

            categoryService.saveCategory(category1);
            return new ResponseEntity<Category>(category1, HttpStatus.OK);
        }
    }

    /**
     * Method: delete category by id
     * Author: TuanLHM
     *
     * @return
     */

    @GetMapping(value = "/main-category/category/delete-category/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Integer id) {

        Category category = categoryService.findCategoryById(id);
        if (category.getCategoryId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            categoryService.deleteCategory(category);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
