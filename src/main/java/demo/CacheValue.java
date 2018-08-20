package demo;

import java.io.Serializable;

public class CacheValue implements Serializable {	
	final String message;
	final Integer length;	
	
	public CacheValue(String message) {
		super();
		this.message = message;
		this.length = message.length();
	}


	public String getMessage() {
		return message;
	}


	public Integer getLength() {
		return length;
	}			
}
