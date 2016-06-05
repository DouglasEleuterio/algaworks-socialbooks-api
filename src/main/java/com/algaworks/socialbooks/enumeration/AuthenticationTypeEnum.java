package com.algaworks.socialbooks.enumeration;

public enum AuthenticationTypeEnum {

  MEMORY, DATABASE;


  public static AuthenticationTypeEnum fromName(final String value) {
    for (AuthenticationTypeEnum item : AuthenticationTypeEnum.values()) {
      if (item.name().equals(value)) {
        return item;
      }
    }

    return MEMORY;
  }

}
