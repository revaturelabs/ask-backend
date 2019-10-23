package com.revaturelabs.ask.tags;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/tags")
public class TagController {
	
	@Autowired
	TagService tagService;
	
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAll();
    }
    
    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable int id) {
        try {
            return tagService.getById(id);
        } catch (TagNotFoundException e) {
            
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found",e);
        }
    }
    
    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
      System.out.println(tag.toString());
      return tagService.create(tag);
    }
    

    @PutMapping("/{id}")
    public Tag createOrUpdate(@RequestBody Tag tag, @PathVariable int id) {
        tag.setId(id);
        try {
            return tagService.createOrUpdate(tag);
        } catch (TagConflictException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tag name already exists", e);
        }
    }
    
    @PatchMapping("/{id}")
    public Tag updateTag(@RequestBody Tag tag, @PathVariable int id) {
        tag.setId(id);
        try {
            return tagService.update(tag);
        } catch (TagConflictException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tag name already exists", e);
        } catch (TagNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found", e);
        }
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable int id) {
        try {
            tagService.delete(id);
        } catch (TagNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found", e);
        }
    }
	
}
