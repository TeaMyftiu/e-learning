package com.elearning.elearning.controller;

import com.elearning.elearning.model.Blog;
import com.elearning.elearning.service.BlogService;
import com.elearning.elearning.service.UserService;
import com.elearning.elearning.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/e-learning")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private Validation validation;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/show_blogs", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity showBlogs() {
        try {
            if (blogService.retrieveBlogs().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are any blogs available");
            }
            return new ResponseEntity(blogService.retrieveBlogs(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/add_blog", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity addBlog(@RequestBody Blog blog) {
        try {
            if (!validation.isPresent(userService.retrieveUserById(blog.getUser().getId()), userService.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user ID you trying to sign in does not exist");
            }
            return new ResponseEntity(blogService.addBlog(blog), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/getBlog/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBlogById(@Valid @PathVariable("id") Long id) {
        try {
            if (!validation.isPresent(blogService.retrieveBlog(id), blogService.retrieveBlogs())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This blog does not exist");
            }
            return new ResponseEntity(blogService.retrieveBlog(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/getBlogByUserId/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBlogByUserId(@Valid @PathVariable("id") Long id) {
        try {
            if (!validation.isPresent(userService.retrieveUserById(id), userService.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user with ID: " + id + " does not exist");
            }
            return new ResponseEntity(blogService.getBlogsOfOneUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/deleteBlog/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBlog(@Valid @PathVariable("id") Long id) {
        try {
            if (!validation.isPresent(blogService.retrieveBlog(id), blogService.retrieveBlogs())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The blog with ID:" + id + " does not exist");
            }
            return new ResponseEntity(blogService.deleteBlog(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/updateBlog/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity getUpdatedBlog(@RequestBody Blog updatedBlog, @Valid @PathVariable("id") Long id) {
        try {
            if (!validation.isPresent(blogService.retrieveBlog(id), blogService.retrieveBlogs())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The blog with ID:" + id + " does not exist, so it can not be updated");
            }
            if (!validation.isPresent(userService.retrieveUserById(updatedBlog.getUser().getId()), userService.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user IID you trying to sign in does not exist");
            }
            blogService.updateBlog(id, updatedBlog.getTitle(), updatedBlog.getDescription(), updatedBlog.getUser().getId());
            return ResponseEntity.status(HttpStatus.OK).body("The user with ID:" + id + " is updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

    }
}
