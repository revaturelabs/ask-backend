package com.revaturelabs.ask.tags;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/tags")
public class TagController {
	
	@Autowired
	TagService tagService;
	
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAll();
    }
    
//    @PostMapping
//    public Tag createTag(@RequestBody Tag tag) {
//        //return TagService.create(tag);
//        return null;
//    }
    
    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable int id) {
        try {
            return tagService.getById(id);
        } catch (TagNotFoundException e) {
            
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found",e);
        }
    }
	
}
