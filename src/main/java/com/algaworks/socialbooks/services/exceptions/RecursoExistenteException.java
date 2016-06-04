package com.algaworks.socialbooks.services.exceptions;

public class RecursoExistenteException extends RuntimeException {

  private static final long serialVersionUID = 3102285043620313882L;

  public RecursoExistenteException(String message) {
    super(message);
  }

  public RecursoExistenteException(String message, Throwable cause) {
    super(message, cause);
  }

}
