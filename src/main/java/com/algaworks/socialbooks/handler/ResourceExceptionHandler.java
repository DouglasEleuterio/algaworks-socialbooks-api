package com.algaworks.socialbooks.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.socialbooks.domain.ResourceDetailError;
import com.algaworks.socialbooks.domain.ResourceError;
import com.algaworks.socialbooks.resources.exceptions.SocialBooksBadRequestException;
import com.algaworks.socialbooks.services.exceptions.RecursoExistenteException;
import com.algaworks.socialbooks.services.exceptions.RecursoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

  private static final String ON_FIELD = "on field '";
  private static final String BAD_REQUEST_URL_ERROR = "http://erros.socialbooks.com.br/400";
  private static final String NOT_FOUND_URL_ERROR = "http://erros.socialbooks.com.br/404";
  private static final String CONFLICT_URL_ERROR = "http://erros.socialbooks.com.br/409";

  @ExceptionHandler(RecursoNaoEncontradoException.class)
  public ResponseEntity<ResourceError> handleRecursoNaoEncontradoException(
      RecursoNaoEncontradoException e) {

    return getResponseEntity(HttpStatus.NOT_FOUND, e, NOT_FOUND_URL_ERROR);
  }

  @ExceptionHandler(RecursoExistenteException.class)
  public ResponseEntity<ResourceError> handleRecursoExistenteException(
      RecursoExistenteException e) {

    return getResponseEntity(HttpStatus.CONFLICT, e, CONFLICT_URL_ERROR);
  }

  @ExceptionHandler(SocialBooksBadRequestException.class)
  public ResponseEntity<ResourceError> handleSocialBooksBadRequestException(
      SocialBooksBadRequestException e) {

    return getResponseEntity(HttpStatus.BAD_REQUEST, e, BAD_REQUEST_URL_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResourceError> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {

    List<ResourceDetailError> detalhesErro = new ArrayList<ResourceDetailError>();
    for (ObjectError error : e.getBindingResult().getAllErrors()) {
      detalhesErro.add(new ResourceDetailError(getField(error), error.getDefaultMessage()));
    }

    return getResponseEntity(HttpStatus.BAD_REQUEST, detalhesErro, BAD_REQUEST_URL_ERROR);
  }

  private String getField(ObjectError error) {
    Integer from = error.toString().indexOf(ON_FIELD) + ON_FIELD.length();
    int to = error.toString().indexOf("'", from);
    return error.toString().substring(from, to);
  }

  private ResponseEntity<ResourceError> getResponseEntity(HttpStatus status, RuntimeException e,
      String mensagemDesenvolvedor) {

    return ResponseEntity.status(status)
        .body(getResourceError(status, e.getMessage(), mensagemDesenvolvedor));
  }

  private ResponseEntity<ResourceError> getResponseEntity(HttpStatus status,
      List<ResourceDetailError> detalhesErro, String mensagemDesenvolvedor) {

    return ResponseEntity.status(status)
        .body(getResourceError(status, detalhesErro, mensagemDesenvolvedor));
  }

  private ResourceError getResourceError(HttpStatus status, List<ResourceDetailError> detalhesErro,
      String mensagemDesenvolvedor) {
    ResourceError resourceError = getResourceError(status, "", mensagemDesenvolvedor);
    resourceError.setDetalhes(detalhesErro);
    return resourceError;
  }

  private ResourceError getResourceError(HttpStatus status, String titulo,
      String mensagemDesenvolvedor) {

    ResourceError erro = new ResourceError();
    erro.setStatus(Long.valueOf(status.value()));
    erro.setTitulo(titulo);
    erro.setAjuda(mensagemDesenvolvedor);
    erro.setData(new Date());
    return erro;
  }

}
