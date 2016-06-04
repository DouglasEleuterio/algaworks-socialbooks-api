package com.algaworks.socialbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutoresRepository;

@Service
public class AutoresService {

  @Autowired
  private AutoresRepository autoresRepository;

  public List<Autor> listar() {
    return autoresRepository.findAll();
  }

}
