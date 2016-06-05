package com.algaworks.socialbooks.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Campo obrigatório.")
  private String nome;

  @JsonFormat(pattern = "dd/MM/yyyy")
  @NotNull(message = "Campo obrigatório.")
  private Date publicacao;

  @NotNull(message = "Campo obrigatório.")
  private String editora;

  @NotNull(message = "Campo obrigatório.")
  @Size(max = 1500, message = "O campo resumo excedeu o tamanho permitido. Permitido 1500 caracteres.")
  private String resumo;

  @JsonInclude(Include.NON_EMPTY)
  @OneToMany(mappedBy = "livro")
  private List<Comentario> comentarios;

  @ManyToOne
  @JoinColumn(name = "AUTOR_ID")
  private Autor autor;

  public Livro() {

  }

  public Livro(String nome) {
    super();
    this.nome = nome;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Date getPublicacao() {
    return publicacao;
  }

  public void setPublicacao(Date publicacao) {
    this.publicacao = publicacao;
  }

  public String getEditora() {
    return editora;
  }

  public void setEditora(String editora) {
    this.editora = editora;
  }

  public List<Comentario> getComentarios() {
    return comentarios;
  }

  public void setComentarios(List<Comentario> comentarios) {
    this.comentarios = comentarios;
  }

  public Autor getAutor() {
    return autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public String getResumo() {
    return resumo;
  }

  public void setResumo(String resumo) {
    this.resumo = resumo;
  }

}
