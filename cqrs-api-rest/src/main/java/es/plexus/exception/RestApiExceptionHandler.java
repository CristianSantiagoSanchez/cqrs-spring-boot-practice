package es.plexus.exception;

import es.plexus.exceptions.book.BookNotFoundException;
import es.plexus.exceptions.book.IsbnUsedException;
import es.plexus.exceptions.swap.SwapNotFoundException;
import es.plexus.exceptions.swap.SwapStateIllegalException;
import es.plexus.exceptions.user.EmailUsedException;
import es.plexus.exceptions.user.TokenException;
import es.plexus.exceptions.user.UserNotFoundException;
import es.plexus.exceptions.user.UsernameUsedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        System.out.println("entra aqui");
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({UserNotFoundException.class, BookNotFoundException.class, SwapNotFoundException.class})
    public final ResponseEntity handleNotFoundException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmailUsedException.class, UsernameUsedException.class, IsbnUsedException.class, SwapStateIllegalException.class})
    public final ResponseEntity handleRepeatedException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler({TokenException.class})
    public final ResponseEntity handleForbidenException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}
