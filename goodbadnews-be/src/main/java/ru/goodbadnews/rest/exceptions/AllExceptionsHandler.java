package ru.goodbadnews.rest.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class AllExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotNewsFoundRepoException.class)
    protected ResponseEntity<String> handleNotNewsFoundException() {
        return new ResponseEntity<>("News not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({org.springframework.dao.DuplicateKeyException.class, com.mongodb.MongoWriteException.class})
    protected ResponseEntity<String> handleMongoDBDuplicateObjectException() {
        return new ResponseEntity<>("You are inserting duplicate object.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameFoundException.class)
    protected ResponseEntity<String> handleUsernameFoundException() {
        return new ResponseEntity<>("User exists, please try with another credentials", HttpStatus.IM_USED);
    }

    @ExceptionHandler(UsersNotFoundInRepositoryException.class)
    protected ResponseEntity<String> handleUsersnotFoundInRepositoryException() {
        return new ResponseEntity<>("Users not found in database", HttpStatus.NOT_FOUND);
    }
}
