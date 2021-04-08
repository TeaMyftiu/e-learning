package com.elearning.elearning.controller;

import com.elearning.elearning.common.BaseAction;
import com.elearning.elearning.common.Validation;
import com.elearning.elearning.model.Blog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/e-learning")
public class BlogController extends BaseAction{


    @RequestMapping(value = "/show_blogs", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity showBlogs() {
        try {
            if (facade.retrieveBlogs().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are any blogs available");
            }
            return new ResponseEntity(facade.retrieveBlogs(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/add_blog", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity addBlog(@RequestBody Blog blog) {
        try {
            if (!Validation.isPresent(facade.retrieveUserById(blog.getUser().getId()), facade.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user ID you trying to sign in does not exist");
            }
            return new ResponseEntity(facade.addBlog(blog), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/getBlog/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBlogById(@Valid @PathVariable("id") Long id) {
        try {
            if (!Validation.isPresent(facade.retrieveBlogById(id), facade.retrieveBlogs())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This blog does not exist");
            }
            return new ResponseEntity(facade.retrieveBlogById(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/getBlogByUserId/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBlogByUserId(@Valid @PathVariable("id") Long id) {
        try {
            if (!Validation.isPresent(facade.retrieveUserById(id), facade.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user with ID: " + id + " does not exist");
            }
            return new ResponseEntity(facade.getBlogsOfOneUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/deleteBlog/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBlog(@Valid @PathVariable("id") Long id) {
        try {
            if (!Validation.isPresent(facade.retrieveBlogById(id), facade.retrieveBlogs())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The blog with ID:" + id + " does not exist");
            }
            facade.deleteBlog(id);
            return  ResponseEntity.status( HttpStatus.OK).body("The blog with ID:"+id+" is deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/updateBlog/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity getUpdatedBlog(@RequestBody Blog updatedBlog, @Valid @PathVariable("id") Long id) {
        try {
            if (!Validation.isPresent(facade.retrieveBlogById(id), facade.retrieveBlogs())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The blog with ID:" + id + " does not exist, so it can not be updated");
            }
            if (!Validation.isPresent(facade.retrieveUserById(updatedBlog.getUser().getId()), facade.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user IID you trying to sign in does not exist");
            }
            facade.updateBlog(id, updatedBlog.getTitle(), updatedBlog.getDescription(), updatedBlog.getUser().getId());
            return ResponseEntity.status(HttpStatus.OK).body("The user with ID:" + id + " is updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

    }
}
