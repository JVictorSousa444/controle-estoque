package gmail.davidsousalves.controller.handlers;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import gmail.davidsousalves.dto.response.exception.CustomErrorDTO;
import gmail.davidsousalves.exceptions.DatabaseException;
import gmail.davidsousalves.exceptions.GenerateTokenException;
import gmail.davidsousalves.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler{

	 @ExceptionHandler(InvalidFormatException.class)
	    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
	        String fieldName = ex.getPath().get(0).getFieldName();
	        Object value = ex.getValue();
	        String message = String.format("Valor inválido para o campo '%s': '%s'", fieldName, value);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	    }
	
	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException ex, WebRequest request) {
	    String errorMessage = "Invalid date format: " + ex.getMessage();
	    CustomErrorDTO error = new CustomErrorDTO(
	        Instant.now(),
	        HttpStatus.BAD_REQUEST.value(),
	        errorMessage,
	        request.getDescription(false)
	    );
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}


	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorDTO> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(GenerateTokenException.class)
    public ResponseEntity<CustomErrorDTO> generateToken(GenerateTokenException e, HttpServletRequest request) {
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
    	return ResponseEntity.status(status).body(err);
    
    }	
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorDTO> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());

        String message = String.join(", ", errors);

        CustomErrorDTO err = new CustomErrorDTO(
                Instant.now(), // timestamp
                HttpStatus.BAD_REQUEST.value(), // status
                message, // error
                request.getRequestURI() // path
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CustomErrorDTO> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        String path = request.getDescription(false);
        if (path != null && path.contains("uri=")) {
            path = path.substring(path.indexOf("uri=") + 4); // Remove "uri=" prefix
        }

        String message = "Método HTTP não permitido para esta URI";

        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), HttpStatus.METHOD_NOT_ALLOWED.value(), message, path);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(err);
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorDTO> handleException(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
}
