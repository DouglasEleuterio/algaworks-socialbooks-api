package com.algaworks.socialbooks.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

  @Autowired
  private LivrosRepository livrosRepository;

  @Autowired
  private AutoresService autoresService;

  @Autowired
  private ComentariosRepository comentarioRepository;

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

    autoresService.verificarExistencia(livro.getAutor());

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
    if (livro != null && livro.getId() != null) {
      buscar(livro.getId());
    }
  }

  public Comentario salvarComentario(final Long livroId, Comentario comentario) {
    Livro livro = buscar(livroId);
    comentario.setLivro(livro);
    comentario.setData(new Date());
    return comentarioRepository.save(comentario);
  }

  public List<Comentario> listarComentarios(Long livroId) {
    Livro livro = buscar(livroId);
    return livro.getComentarios();
  }

}
