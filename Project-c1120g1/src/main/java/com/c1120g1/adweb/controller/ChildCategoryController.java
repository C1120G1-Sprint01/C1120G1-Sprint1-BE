package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.ChildCategory;
import com.c1120g1.adweb.service.ChildCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/main-category/child-category")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ChildCategoryController {

    @Autowired
    private ChildCategoryService childCategoryService;

    /**
     * Method: get all child_category
     * Author: TuanLHM
     * @return
     */

    @GetMapping("/")
    public ResponseEntity<List<ChildCategory>> getList() {
        List<ChildCategory> childCategoryList = childCategoryService.findAllChildCategory();
        if (childCategoryList.isEmpty()) {
            return new ResponseEntity<List<ChildCategory>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ChildCategory>>(childCategoryList, HttpStatus.OK);
    }

    /**
     * Method: get child_category by id
     * Author: TuanLHM
     * @return
     */

    @GetMapping("/{id}")
    public ResponseEntity<ChildCategory> getChildCategoryById(@PathVariable int id) {
        return new ResponseEntity<>(this.childCategoryService.findChildCategoryById(id), HttpStatus.OK);
    }

    /**
     * Method: create child_category
     * Author: TuanLHM
     * @return
     */

    @PostMapping("/create-child-category")
    public ResponseEntity<Void> createChildCategory(@RequestBody ChildCategory childCategory, UriComponentsBuilder ucBuilder) {
        childCategoryService.save(childCategory);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/child_category/{id}").buildAndExpand(childCategory.getChildCategoryId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Method: edit child_category
     * Author: TuanLHM
     * @return
     */

    @PutMapping(value = "/edit-child-category")
    public ResponseEntity<ChildCategory> updateChildCategory(@RequestBody ChildCategory childCategory) {
        childCategoryService.save(childCategory);
        return new ResponseEntity<ChildCategory>(childCategory, HttpStatus.OK);
    }

    /**
     * Method: delete child_category
     * Author: TuanLHM
     * @return
     */

    @DeleteMapping(value = "/delete-child-category/{id}")
    public ResponseEntity deleteChildCategory(@PathVariable("id") Integer id) {

        ChildCategory childCategory = childCategoryService.findChildCategoryById(id);
        if (childCategory.getChildCategoryId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            childCategory.setDeleteFlag(true);
            childCategoryService.save(childCategory);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Method: search child_category
     * Author: TuanLHM
     * @return
     */

    @GetMapping("/search")
    public ResponseEntity<List<ChildCategory>> searchName(@RequestParam(name = "childCategoryName") Optional<String> childCategoryName,
                                                          @RequestParam(name = "categoryName") Optional<String> categoryName) {
        List<ChildCategory> childCategoryList;
        if (categoryName.isPresent()){
            if (childCategoryName.isPresent()){
                childCategoryList = childCategoryService.findAllByChildCategoryNameAndCategoryName(childCategoryName.get(),categoryName.get());
            } else {
                childCategoryList = childCategoryService.findAllByCategoryName(categoryName.get());
            }
        } else {
            if (childCategoryName.isPresent()) {
                childCategoryList = childCategoryService.findAllByChildCategoryName(childCategoryName.get());
            } else {
                childCategoryList = childCategoryService.findAllChildCategory();
            }
        }

        if (childCategoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(childCategoryList, HttpStatus.OK);
    }
}



