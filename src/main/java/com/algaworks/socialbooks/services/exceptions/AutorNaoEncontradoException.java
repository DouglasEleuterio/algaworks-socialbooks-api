package com.algaworks.socialbooks.services.exceptions;

public class AutorNaoEncontradoException extends RecursoNaoEncontradoException {

  private static final long serialVersionUID = -7503198838912178474L;

  public AutorNaoEncontradoException(String message) {
    super(message);
  }

  public AutorNaoEncontradoException(String message, Throwable cause) {
    super(message, cause);
  }

}
