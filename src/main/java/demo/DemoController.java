package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DemoController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;    
    
    @RequestMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String msg) {
        redisTemplate.opsForValue().set(key, new CacheValue(msg));
        return "Cache set using key: " + key;
    }
    
    @RequestMapping("/get")
    public CacheValue getValue(@RequestParam String key) {
    	CacheValue cacheValue = (CacheValue)redisTemplate.opsForValue().get(key);      
    	if (cacheValue == null) throw new RuntimeException("Failed to find cache value using key: " + key);
    	return cacheValue;
    }
    

    @ExceptionHandler(value = Exception.class)  
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
