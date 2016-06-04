package com.algaworks.socialbooks.resources.exceptions;

public class SocialBooksBadRequestException extends RuntimeException {

  private static final long serialVersionUID = 3589291432869835766L;

  public SocialBooksBadRequestException(String message) {
    super(message);
  }

  public SocialBooksBadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

}
