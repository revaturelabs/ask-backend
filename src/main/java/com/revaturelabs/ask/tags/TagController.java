package com.revaturelabs.ask.tags;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
