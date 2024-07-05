package com.dark.spring.spms.config;

import com.dark.spring.spms.entity.User;
import com.dark.spring.spms.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;


public class UserHandshakeHandler extends DefaultHandshakeHandler {

    private static final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);

    JwtService jwtService;

    UserDetailsService userDetailsService;

    public UserHandshakeHandler(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    // TODO: websocket Authentication pending: Tried using determineUser but still not able authenticate websocket
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        LOG.info("calling determineUser START {} and uri: {} and query {}", request.getPrincipal(), request.getURI(), request.getURI().getQuery());
        LOG.info(" {} --------- {} ----- {}", wsHandler, attributes, request.getHeaders());

        final String token = Objects.nonNull(request.getHeaders().get("token")) ? Objects.requireNonNull(request.getHeaders().get("token")).getFirst() : request.getURI().getQuery();
        if (!token.isEmpty()) {
            try {
                String userEmail = jwtService.extractUsername(token);
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User userDetails = (User) userDetailsService.loadUserByUsername(userEmail);
                LOG.info("Rana authentication " + authentication + " userEmail is " + userEmail + " And Token: " + token + " And userDetails: " + userDetails);
                if (jwtService.isTokenValid(token, userDetails)) {
                    LOG.info("Rana TokenValid");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            Collections.singleton((GrantedAuthority) () -> "USER")
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    authentication = SecurityContextHolder.getContext().getAuthentication();
                    LOG.info("authentication {} and {}", authentication, request.getPrincipal());
                    LOG.info("Rana authToken " + authToken + " userDetails is " + userDetails + " authentication.getPrincipal()" + authentication.getPrincipal() + " userDetails.getAuthorities()" + userDetails.getAuthorities());
                    return authToken;
                }
            } catch (Exception jwtException) {
                LOG.error("EXCEPTION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", jwtException);
            }
        } else {
            LOG.info("Token is empty {}", token);
        }
        LOG.info("calling determineUser END {}", request.getPrincipal());
        return super.determineUser(request, wsHandler, attributes);
    }
}
