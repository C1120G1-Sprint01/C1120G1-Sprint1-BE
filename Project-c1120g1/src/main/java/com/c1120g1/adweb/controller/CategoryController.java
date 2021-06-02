package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Category;
import com.c1120g1.adweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    @GetMapping("/main-category/category")
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
        System.out.println("Creating Category " + category.getCategoryName());
        categoryService.save(category);
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

    @PutMapping("/main-category/category/edit-category")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {

        categoryService.save(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @GetMapping("api/category")
    public ResponseEntity<List<Category>> getCategory() {
        List<Category> categoryList = categoryService.findAllCategory();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    /**
     * Method: delete category by id
     * Author: TuanLHM
     *
     * @return
     */

    @DeleteMapping(value = "/delete-category/{id}")
    public ResponseEntity<Category> deleteCustomer(@PathVariable("id") Integer id) {

        Category category = categoryService.findCategoryById(id);
        if (category == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }

        categoryService.delete(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);

    }

}
