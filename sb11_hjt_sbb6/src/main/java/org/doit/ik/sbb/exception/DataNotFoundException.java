package org.doit.ik.sbb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Spring MVC에서 HTTP응답할 때의 상태 코드값을 직접 지정
@ResponseStatus(code = HttpStatus.NOT_FOUND,reason="Entity Not Found")
public class DataNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
	
}
