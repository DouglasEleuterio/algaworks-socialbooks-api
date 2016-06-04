package com.algaworks.socialbooks.services.exceptions;

public class AutorExistenteException extends RecursoExistenteException {

  private static final long serialVersionUID = 4069562509345450798L;

  public AutorExistenteException(String message) {
    super(message);
  }

  public AutorExistenteException(String message, Throwable cause) {
    super(message, cause);
  }

}
