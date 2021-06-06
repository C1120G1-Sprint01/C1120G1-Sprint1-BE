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

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ChildCategoryController {

    @Autowired
    private ChildCategoryService childCategoryService;

    @GetMapping("child-category/{categoryId}")
    public ResponseEntity<List<ChildCategory>> findAllByCategoryId(@PathVariable Integer categoryId) {
        List<ChildCategory> childCategoryList = childCategoryService.findAllByCategoryId(categoryId);

        if (childCategoryList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(childCategoryList, HttpStatus.OK);
    }

    /**
     * Method: get all child_category
     * Author: TuanLHM
     *
     * @return
     */

    @GetMapping("/main-category/child-category")
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
     *
     * @return
     */

    @GetMapping("/main-category/child-category/{id}")
    public ResponseEntity<ChildCategory> getChildCategoryById(@PathVariable int id) {
        return new ResponseEntity<>(this.childCategoryService.findChildCategoryById(id), HttpStatus.OK);
    }

    /**
     * Method: create child_category
     * Author: TuanLHM
     *
     * @return
     */

    @PostMapping("/main-category/child-category/create-child-category")
    public ResponseEntity<Void> createChildCategory(@RequestBody ChildCategory childCategory, UriComponentsBuilder ucBuilder) {
        childCategoryService.addChildCategory(childCategory);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/child_category/{id}").buildAndExpand(childCategory.getChildCategoryId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Method: edit child_category
     * Author: TuanLHM
     *
     * @return
     */

    @PutMapping(value = "/main-category/child-category/edit-child-category/{id}")
    public ResponseEntity<ChildCategory> updateChildCategory(@PathVariable Integer id, @RequestBody ChildCategory childCategory) {

        ChildCategory childCategory1 = childCategoryService.findChildCategoryById(id);

        if (childCategory1 == null) {
            return new ResponseEntity<ChildCategory>(HttpStatus.NOT_FOUND);
        } else {
            childCategory1.setChildCategoryName(childCategory.getChildCategoryName());
            childCategory1.setCategory(childCategory.getCategory());
            childCategory1.setPostSet(childCategory.getPostSet());
            childCategory1.setDeleteFlag(false);
            childCategory1.setChildCategoryId(id);

            childCategoryService.saveChildCategory(childCategory1);
            return new ResponseEntity<ChildCategory>(childCategory1, HttpStatus.OK);
        }
    }

    /**
     * Method: delete child_category
     * Author: TuanLHM
     *
     * @return
     */

    @GetMapping(value = "/main-category/child-category/delete-child-category/{id}")
    public ResponseEntity<?> deleteChildCategory(@PathVariable("id") Integer id) {

        ChildCategory childCategory = childCategoryService.findChildCategoryById(id);
        if (childCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            childCategoryService.deleteChildCategory(childCategory);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Method: search child_category
     * Author: TuanLHM
     *
     * @return
     */
    @GetMapping("/main-category/child-category/search")
    public ResponseEntity<List<ChildCategory>> searchName(@RequestParam(name = "childCategoryName") String childCategoryName,
                                                          @RequestParam(name = "categoryName") String categoryName) {
        List<ChildCategory> childCategoryList;

        childCategoryList = childCategoryService.findAllByChildCategoryNameAndCategoryName(childCategoryName, categoryName);

        if (childCategoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(childCategoryList, HttpStatus.OK);
    }

    @GetMapping("api/child-category/{id}")
    public ResponseEntity<List<ChildCategory>> getChildCategories(@PathVariable(name = "id") Integer id) {
        List<ChildCategory> categoryList = childCategoryService.getAllChildCategoryByCategoryId(id);
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(categoryList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
