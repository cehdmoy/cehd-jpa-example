package cehd.globallogic.contest.controller;

import cehd.globallogic.contest.dto.response.ErrorMensage;
import cehd.globallogic.contest.save.exception.UserEmailTwiceException;
import cehd.globallogic.contest.service.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppControllerAdvice {
    @ResponseBody
    @ExceptionHandler(UserEmailTwiceException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ErrorMensage employeeNotFoundHandler(UserEmailTwiceException ex) {
        return ErrorMensage.builder().mensaje("El correo ya registrado").build();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMensage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorMensage.builder().mensaje("Error en argumento no valido: " + e.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMensage UserNotFoundException(UserNotFoundException e) {
        return ErrorMensage.builder().mensaje("Usuario no encontrado").build();
    }

    @ResponseBody
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ErrorMensage emptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return ErrorMensage.builder().mensaje("No se puede eliminar un usuario ya eliminado o inexistente").build();
    }
}
