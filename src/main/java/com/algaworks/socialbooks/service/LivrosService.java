package com.algaworks.socialbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

  @Autowired
  private LivrosRepository livrosRepository;

  public List<Livro> listar() {
    return livrosRepository.findAll();
  }

  public Livro buscar(final Long id) {
    Livro livro = livrosRepository.findOne(id);

    if (livro == null) {
      throw new LivroNaoEncontradoException("O livro não pode ser encontrado");
    }

    return livro;

  }

  public Livro salvar(Livro livro) {
    livro.setId(null);
    return livrosRepository.save(livro);
  }

  public void remover(final Long id) {

    try {
      livrosRepository.delete(id);
    } catch (EmptyResultDataAccessException e) {
      throw new LivroNaoEncontradoException("O livro não pode ser encontrado");
    }

  }

  public void atualizar(Livro livro) {
    verificarExistencia(livro);
    livrosRepository.save(livro);
  }

  private void verificarExistencia(final Livro livro) {
    buscar(livro.getId());
  }

}
