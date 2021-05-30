package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.ChildCategory;
import com.c1120g1.adweb.service.ChildCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/")
    public ResponseEntity<List<ChildCategory>> getList() {
        List<ChildCategory> childCategoryList = childCategoryService.findAllChildCategory();
        if (childCategoryList.isEmpty()) {
            return new ResponseEntity<List<ChildCategory>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ChildCategory>>(childCategoryList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChildCategory> getChildCategoryById(@PathVariable int id) {
        return new ResponseEntity<>(this.childCategoryService.findChildCategoryById(id), HttpStatus.OK);
    }

    //----------------------Tạo mới child_category----------------------
    // Ở đây, khi đối tượng có khóa phụ, mà đối tượng bị null(category == rỗng) thì nó sẽ bị lỗi và không lưu được
    // nên khi nãy a không truyền được thằng category xuống nên nó báo lỗi. hết
    @PostMapping("/create-child-category")
    public ResponseEntity<Void> create(@RequestBody ChildCategory childCategory, UriComponentsBuilder ucBuilder) {
        childCategoryService.save(childCategory);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/child_category/{id}").buildAndExpand(childCategory.getChildCategoryId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-----------------------update child_category-------------------------------
    @PutMapping(value = "/edit-child-category")
    public ResponseEntity<ChildCategory> updateCustomer(@RequestBody ChildCategory childCategory) {
        childCategoryService.save(childCategory);
        return new ResponseEntity<ChildCategory>(childCategory, HttpStatus.OK);
    }

    //--------------------------delete child_category---------------------------
    @DeleteMapping(value = "/delete-child-category/{id}")
    public ResponseEntity<ChildCategory> deleteCustomer(@PathVariable("id") Integer id) {

        ChildCategory childCategory = childCategoryService.findChildCategoryById(id);
        if (childCategory == null) {
            return new ResponseEntity<ChildCategory>(HttpStatus.NOT_FOUND);
        }

        childCategoryService.delete(id);
        return new ResponseEntity<ChildCategory>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ChildCategory>> searchName(@RequestParam Optional<String> childCategoryName,
                                                          @RequestParam Optional<String> categoryName) {
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