package com.algaworks.socialbooks.services.exceptions;

public class LivroNaoEncontradoException extends RecursoNaoEncontradoException {

  private static final long serialVersionUID = 1869300553614629710L;

  public LivroNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public LivroNaoEncontradoException(String message, Throwable cause) {
    super(message, cause);
  }

}
