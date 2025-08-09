package xyz.eo.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @ExceptionHandler(ErrorMessageException.class)
    public ResponseEntity<BadApiResponse> handleErrorMessage(ErrorMessageException errorMessage){
        BadApiResponse badApiResponse = new BadApiResponse();
        badApiResponse.setMessage(errorMessage.getMessage());
        badApiResponse.setStatus(errorMessage.getStatus());
        return new ResponseEntity<>(badApiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<BadApiResponse> notAuthorizedException(NotAuthorizedException errorMessage){
        BadApiResponse badApiResponse = new BadApiResponse();
        badApiResponse.setMessage(errorMessage.getMessage());
        badApiResponse.setStatus(0);
        return new ResponseEntity<>(badApiResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BadApiResponse> handleUserNotFoundException(UserNotFoundException errorMessage){
        BadApiResponse badApiResponse = new BadApiResponse();
        badApiResponse.setMessage(errorMessage.getMessage());
        badApiResponse.setStatus(0);
        return new ResponseEntity<>(badApiResponse, HttpStatus.NOT_FOUND);
    }

}
