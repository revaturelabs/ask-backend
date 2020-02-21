package com.revaturelabs.ask.ama;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/amaSession")
@CrossOrigin
public class AMASessionController {
  
  @Autowired
  AMASessionService amaService;
  
  @GetMapping("/{expertName}")
  public ResponseEntity<List<AMASession>> getSessionsByTrainer(@PathVariable String expertName){
    return ResponseEntity.ok(amaService.getAllSessionsByExpert(expertName));
  }
  
  @GetMapping
  public ResponseEntity<List<AMASession>> getSessions(){
    return ResponseEntity.ok(amaService.getAllSessions());
  }
  
}
