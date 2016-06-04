package com.algaworks.socialbooks.services.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {

  private static final long serialVersionUID = -8982650449879918484L;

  public RecursoNaoEncontradoException(String message) {
    super(message);
  }

  public RecursoNaoEncontradoException(String message, Throwable cause) {
    super(message, cause);
  }

}
