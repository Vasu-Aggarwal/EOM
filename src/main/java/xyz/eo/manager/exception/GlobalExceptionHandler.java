package xyz.eo.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.eo.manager.dto.response.BadApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        BadApiResponse badApiResponse = new BadApiResponse();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            assert message != null;
            badApiResponse.setMessage(String.format(message));
            badApiResponse.setStatus(0);
        });
        return new ResponseEntity<>(badApiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorMessage.class)
    public ResponseEntity<BadApiResponse> handleErrorMessage(ErrorMessage errorMessage){
        BadApiResponse badApiResponse = new BadApiResponse();
        badApiResponse.setMessage(errorMessage.getMessage());
        badApiResponse.setStatus(errorMessage.getStatus());
        return new ResponseEntity<>(badApiResponse, HttpStatus.BAD_REQUEST);
    }

}
