package com.algaworks.socialbooks.resources;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.security.core.context.SecurityContextHolder;

public class AbstractResource {

  @Value("${cache.control.age}")
  private Long cacheControlAge;

  @Value("${cache.control.unit}")
  private String cacheControlUnit;

  protected String getAuthenticatedUser() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }

  protected CacheControl getCacheControlMaxAge() {
    return CacheControl.maxAge(cacheControlAge, TimeUnit.valueOf(cacheControlUnit));
  }

}
