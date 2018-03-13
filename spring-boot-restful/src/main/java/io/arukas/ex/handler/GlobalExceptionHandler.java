package io.arukas.ex.handler;

import io.arukas.tools.Dump;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(Integer.MAX_VALUE)
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	public Dump handle500(Exception exception) {
		int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
		String message = exception.getMessage();
		if (logger.isErrorEnabled()) {
			logger.error("error occurred : {}", message, exception);
		}
		return Dump.fail(code, !ObjectUtils.isEmpty(message) ? message : "服务器内部错误");
	}

	/**
	 * 处理404
	 * 
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public Dump handle404(HttpServletRequest request) {
		String path = request.getRequestURI();
		Map<String, String> data = new HashMap<String, String>();
		data.put("path", path);
		return Dump.fail(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), data);
	}

	/**
	 * 请求类型异常处理
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Dump handle405(HttpRequestMethodNotSupportedException exception) {
		String[] supportedMethods = exception.getSupportedMethods();
		String methods = String.join(",", supportedMethods);
		String method = exception.getMethod();
		String message = "%s %s, PLZ Try %s Method Again";
		message = String.format(message, method, HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), methods);
		return Dump.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), message);
	}

	/**
	 * 415
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Dump handle415(HttpMediaTypeNotSupportedException e) {
		String message = e.getMessage();
		if (logger.isErrorEnabled()) {
			logger.error(message);
		}
		return Dump.fail(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), message);
	}

	@ExceptionHandler(ArithmeticException.class)
	public Dump handle(Exception exception) {
		int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
		String message = exception.getMessage();
		if (logger.isErrorEnabled()) {
			logger.error("error occurred : {}", message, exception);
		}
		return Dump.fail(code, message);
	}

}
