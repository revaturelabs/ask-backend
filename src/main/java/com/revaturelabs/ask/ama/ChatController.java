package com.revaturelabs.ask.ama;



import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/socket")
@CrossOrigin
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    private MessageRepository messageRepository ;

	
    @MessageMapping("/send/message")
    public Map<String, String> broadcastNotification(String message){
    	
    	MessageTable messageObj = null;
    	
    	System.out.println(message);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        try {
			messageObj = mapper.readValue (message, MessageTable.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
       
        messageRepository.save(messageObj);
        
        
        Map<String, String> messageConverted = null;
        try {
            messageConverted = mapper.readValue(message, Map.class);
        } catch (IOException e) {
            messageConverted = null;
        }
        if(messageConverted!=null){
            if(messageConverted.containsKey("toId") && messageConverted.get("toId")!=null && !messageConverted.get("toId").equals("")){
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("toId"),messageConverted);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("fromId"),message);
            }else{
                this.simpMessagingTemplate.convertAndSend("/socket-publisher",messageConverted);
            }
        }
        return messageConverted;
    }

}
