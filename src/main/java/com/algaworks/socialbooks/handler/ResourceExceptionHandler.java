package com.algaworks.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.socialbooks.domain.DetalhesErro;
import com.algaworks.socialbooks.services.exceptions.RecursoExistenteException;
import com.algaworks.socialbooks.services.exceptions.RecursoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

  private static final String CONFLICT_URL_ERROR = "http://erros.socialbooks.com.br/409";
  private static final String NOT_FOUND_URL_ERROR = "http://erros.socialbooks.com.br/404";

  @ExceptionHandler(RecursoNaoEncontradoException.class)
  public ResponseEntity<DetalhesErro> handleRecursoNaoEncontradoException(
      RecursoNaoEncontradoException e, HttpServletRequest request) {

    return getResponseEntity(HttpStatus.NOT_FOUND, e, NOT_FOUND_URL_ERROR);
  }

  @ExceptionHandler(RecursoExistenteException.class)
  public ResponseEntity<DetalhesErro> handleRecursoExistenteException(RecursoExistenteException e,
      HttpServletRequest request) {

    return getResponseEntity(HttpStatus.CONFLICT, e, CONFLICT_URL_ERROR);
  }

  private ResponseEntity<DetalhesErro> getResponseEntity(HttpStatus status, RuntimeException e,
      String mensagemDesenvolvedor) {

    return ResponseEntity.status(status)
        .body(getDetalhesErro(status, e.getMessage(), mensagemDesenvolvedor));
  }

  private DetalhesErro getDetalhesErro(HttpStatus status, String titulo,
      String mensagemDesenvolvedor) {

    DetalhesErro erro = new DetalhesErro();
    erro.setStatus(Long.valueOf(status.value()));
    erro.setTitulo(titulo);
    erro.setMensagemDesenvolvedor(mensagemDesenvolvedor);
    erro.setTimestamp(System.currentTimeMillis());
    return erro;
  }

}
