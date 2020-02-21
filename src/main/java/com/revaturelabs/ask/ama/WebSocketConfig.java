package com.revaturelabs.ask.ama;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // TODO Auto-generated method stub
    config.enableSimpleBroker("/messages");
    config.setApplicationDestinationPrefixes("/ama");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // TODO Auto-generated method stub

    registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:4200").withSockJS();
  }


}
