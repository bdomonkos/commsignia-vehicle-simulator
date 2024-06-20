package com.vechile.simulator;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket configuration class that enables WebSocket message handling, backed by a message broker.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * Configures the message broker.
   *
   * @param config the message broker registry to configure
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // Enable a simple memory-based message broker to carry the messages back to the client on destinations prefixed with "/topic".
    config.enableSimpleBroker("/topic");
    // Designate "/app" as the prefix for messages that are bound for methods annotated with @MessageMapping.
    config.setApplicationDestinationPrefixes("/app");
  }

  /**
   * Registers STOMP endpoints.
   *
   * @param registry the registry for STOMP endpoints
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // Register an endpoint at "/ws" and allow cross-origin requests.
    // Also, enable SockJS fallback options in case WebSocket is not available.
    registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:3000").withSockJS();
  }
}
