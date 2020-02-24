package com.revaturelabs.ask.ama;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revaturelabs.ask.user.User;
import com.revaturelabs.ask.user.UserService;

@RestController
@RequestMapping(path = "/amaSession")
@CrossOrigin
public class AMASessionController {
  
  @Autowired
  AMASessionService amaService;
  
  @Autowired
  UserService userService;
  
  @GetMapping("/{expertId}")
  public ResponseEntity<List<AMASession>> getSessionsByExpert(@PathVariable int expertId){
    User expert = userService.findById(expertId);
    return ResponseEntity.ok(amaService.getAllSessionsByExpert(expert));
  }
  
  @GetMapping
  public ResponseEntity<List<AMASession>> getSessions(){
    return ResponseEntity.ok(amaService.getAllSessions());
  }
  
  @PostMapping //Dillon please fill this out
  public ResponseEntity<AMASession> postNewSession(@RequestBody AMASession newSession){
    return ResponseEntity.ok(amaService.postNewSession(newSession));
  }
  
}
