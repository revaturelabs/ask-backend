package com.revaturelabs.ask.tag;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

/**
 * The TagController is responsible for handling requests involving tags. TagController can return a
 * list of tags, a tag by id and can add, update or delete tags from a database.
 * 
 */
@RestController
@RequestMapping(path = "/tags")
public class TagController {

  @Autowired
  TagService tagService;

  /**
   * A mapping used to request a list of tags. Accepts HTTP GET request.
   * 
   * @return a list of the tags in a database.
   */
  @GetMapping
  public ResponseEntity<List<Tag>> getAllTags() {
    return ResponseEntity.ok(tagService.getAll());
  }

  /**
   * A mapping used to return an individual tag. Accepts HTTP GET request.
   * 
   * @param id -path id for an individual tag
   * @return a tag for a specific id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Tag> getTagById(@PathVariable int id) {
    try {
      return ResponseEntity.ok(tagService.getById(id));
    } catch (TagNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found", e);
    }
  }

  /**
   * A mapping used to create a new tags. Accepts HTTP POST requests.
   * 
   * @param tag - Tag to be created.
   * @return tag - Created tag.
   */
  @PostMapping
  public Tag createTag(@RequestBody Tag tag) {
    return tagService.create(tag);
  }

  /**
   * A mapping used to create or update a tag. Accepts HTTP put requests. If no tag in the database
   * has a matching id. The given tag is added to the database.
   * 
   * @param tag
   * @param id
   * @return tag created or throws exception.
   */
  @PutMapping("/{id}")
  public Tag createOrUpdate(@RequestBody Tag tag, @PathVariable int id) {
    tag.setId(id);
    try {
      return tagService.createOrUpdate(tag);
    } catch (TagConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Tag name already exists", e);
    }
  }

  /**
   * A mapping used for updating a tag. Accepts HTTP PATCH requests and updates the specified tag.
   * 
   * @param tag
   * @param id
   * @return Tag that was updated or throws exception
   */
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

  /**
   * A mapping used for deleting tags. Deletes the tag at a particular path. Accepts HTTP DELETE
   * requests.
   * 
   * @param id
   */
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
