package com.dark.spring.spms.config;

import com.dark.spring.spms.entity.User;
import com.dark.spring.spms.service.JwtService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Collections;
import java.util.jar.JarException;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	JwtService jwtService;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/websocket");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/spms-websocket");
	}

	// TODO: websocket Authentication pending: Tried this but not working
//	@Override
//	public void configureClientInboundChannel(ChannelRegistration registration) {
//		System.out.println("Rana calling configureClientInboundChannel");
//		registration.interceptors(new ChannelInterceptor() {
//			@Override
//			public Message<?> preSend(Message<?> message, MessageChannel channel) {
//
//				final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//				System.out.println("Rana accessor" + accessor);
//				if (accessor != null && (StompCommand.CONNECT == accessor.getCommand() || StompCommand.SEND == accessor.getCommand())) {
//					final String url = accessor.getFirstNativeHeader("destination");
//					if (url != null && url.contains("users")) {
//						final String token = accessor.getFirstNativeHeader("token");
//						String userEmail = jwtService.extractUsername(token);
//						Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//						User userDetails = (User) userDetailsService.loadUserByUsername(userEmail);
//						System.out.println("Rana authentication "+ authentication + " userEmail is " + userEmail + " And Token: " + token + " And userDetails: " + userDetails);
//						try {
//							if (jwtService.isTokenValid(token, userDetails)) {
//								System.out.println("Rana TokenValid");
//								UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//										userDetails,
//										null,
//										Collections.singleton((GrantedAuthority) () -> "USER")
//								);
//
//								SecurityContextHolder.getContext().setAuthentication(authToken);
//								authentication = SecurityContextHolder.getContext().getAuthentication();
//								accessor.setUser(authToken);
//								System.out.println("Rana authToken " + authToken + " userDetails is " + userDetails + " authentication.getPrincipal()" + authentication.getPrincipal() + " userDetails.getAuthorities()" + userDetails.getAuthorities());
//							}
//						} catch (Exception jwtException) {
//							System.out.println("EXCEPTION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//							jwtException.printStackTrace();
//						}
//					}
//				}
//				// Proceed with the message processing if the token is valid
//				System.out.println("Rana return message"+ message);
//				return message;
//			}
//		});
//	}
}
