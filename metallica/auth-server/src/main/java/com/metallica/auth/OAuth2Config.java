package com.metallica.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 
 * @author Filip Lindby
 *
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.authenticationManager(this.authenticationManager)
		.tokenStore(tokenStore(jedisConnectionFactory()));	
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer)
			throws Exception {
		oauthServer
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("ui1")
			.secret("ui1-secret")
			.authorities("ROLE_TRUSTED_CLIENT")
			.authorizedGrantTypes("authorization_code", "refresh_token")
			.scopes("ui1.read")
			.autoApprove(true)		
		.and()
			.withClient("ui2")
			.secret("ui2-secret")
			.authorities("ROLE_TRUSTED_CLIENT")
			.authorizedGrantTypes("authorization_code", "refresh_token")
			.scopes("ui2.read", "ui2.write")
			.autoApprove(true)
		.and()
			.withClient("mobile-app")			
			.authorities("ROLE_CLIENT")
			.authorizedGrantTypes("implicit", "refresh_token")
			.scopes("read")
			.autoApprove(true)
		.and()
			.withClient("customer-integration-system")	
			.secret("1234567890")
			.authorities("ROLE_CLIENT")
			.authorizedGrantTypes("client_credentials")
			.scopes("read")
			.autoApprove(true);
	}
	
	@Bean
	public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
	    return new RedisTokenStore(redisConnectionFactory);
	}
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
	    return new JedisConnectionFactory();
	}
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
	    return template;
	}	
}
