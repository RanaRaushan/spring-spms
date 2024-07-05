package com.dark.spring.spms.config;

import com.dark.spring.spms.entity.User;
import com.dark.spring.spms.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	private static final Logger LOG = LoggerFactory.getLogger(WebSocketConfig.class);

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
		registry.addEndpoint("/spms-websocket")
				.setHandshakeHandler(new UserHandshakeHandler(jwtService, userDetailsService))
				;
	}


	// TODO: websocket Authentication pending: Tried this but still not able authenticate websocket
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		LOG.info("Rana calling configureClientInboundChannel");		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {

				final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
				if (accessor != null && (StompCommand.CONNECT == accessor.getCommand())) {
					String jwtToken = "";
					final String url = accessor.getFirstNativeHeader("destination");
					final String authHeader = accessor.getFirstNativeHeader("Authorization");
					if (authHeader != null && authHeader.startsWith("Bearer ")) {
						jwtToken = authHeader.substring(7);
					}

                    final String token = jwtToken;
					LOG.info("accessor is {}, and token {}", accessor.getCommand(), token);
                    try {
						String userEmail = jwtService.extractUsername(token);
						Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
						User userDetails = (User) userDetailsService.loadUserByUsername(userEmail);
						LOG.info("Rana authentication "+ authentication + " userEmail is " + userEmail + " And Token: " + token + " And userDetails: " + userDetails);
                        if (jwtService.isTokenValid(token, userDetails)) {
                            LOG.info("Rana TokenValid");
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    Collections.singleton((GrantedAuthority) () -> "USER")
                            );

                            SecurityContextHolder.getContext().setAuthentication(authToken);
                            authentication = SecurityContextHolder.getContext().getAuthentication();
                            accessor.setUser(authToken);
                            LOG.info("Rana authToken " + authToken + " userDetails is " + userDetails + " authentication.getPrincipal()" + authentication.getPrincipal() + " userDetails.getAuthorities()" + userDetails.getAuthorities());
                        }
                    } catch (Exception jwtException) {
                        LOG.error("EXCEPTION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", jwtException);
                    }
                }
				// Proceed with the message processing if the token is valid
				LOG.info("Rana return message"+ message);
				return message;
			}
		});
	}
}
