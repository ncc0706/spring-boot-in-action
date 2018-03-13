package io.arukas.ex.handler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import io.arukas.tools.Dump;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Controller 入参验证错误处理。<br>
 * 
 * @since 0.0.1
 * 
 */
@RestControllerAdvice
@Order(Integer.MIN_VALUE)
public class ValidationExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 捕获Controller DTO参数验证结果抛出的异常
	 * 
	 * @Validated Object obj
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Dump bindException(BindException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		Map<String, String> errors = new HashMap<String, String>();
		fieldErrors.forEach(fieldError -> {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
		if (logger.isErrorEnabled()) {
			logger.error("argument has error: {}", errors);
		}
		return Dump.fail(HttpStatus.BAD_REQUEST.value(), "validate failed", errors);
	}

	/**
	 * 验证post dto参数
	 * 
	 * @param exception
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Dump processValidationError(MethodArgumentNotValidException exception) {
		BindingResult result = exception.getBindingResult();

		List<FieldError> fieldErrors = result.getFieldErrors();
		Map<String, String> errors = new HashMap<String, String>();
		for (FieldError error : fieldErrors) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
		if (logger.isErrorEnabled()) {
			logger.error("argument has error: {}", errors);
		}
		return Dump.fail(HttpStatus.BAD_REQUEST.value(), "invalid arguments", errors);
	}
	
	/**
	 * 单个参数校验统一异常处理
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Dump handleValidationException(ConstraintViolationException exception) {
        return Dump.fail(HttpStatus.BAD_REQUEST.value(), "invalid argument" , error(exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList())));
    }

    private Map<String, Object> error(Object message) {
        return Collections.singletonMap("error", message);
    }
}
