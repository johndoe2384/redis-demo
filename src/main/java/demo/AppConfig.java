package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {			
	@Bean
	JedisConnectionFactory jedisConnectionFactory(
			@Value("${redis.host}") String redisHost,  
			@Value("${redis.port}") int redisPort,
			@Value("${redis.password}") String redisPassword) {
	    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
	    
	    if (redisPassword != null && !redisPassword.isEmpty()) {
	    	redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
	    }
	    
	    return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory);
	    return template;
	}		
}
