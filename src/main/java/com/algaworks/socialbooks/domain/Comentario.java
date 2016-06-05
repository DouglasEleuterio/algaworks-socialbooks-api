package com.algaworks.socialbooks.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonInclude(Include.NON_NULL)
public class Comentario {

  @JsonInclude(Include.NON_NULL)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Campo obrigatório.")
  @Size(max = 1500, message = "O campo texto excedeu o tamanho permitido. Permitido 1500 caracteres.")
  @JsonProperty(value = "comentario")
  private String texto;

  private String usuario;

  @JsonInclude(Include.NON_NULL)
  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date data;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LIVRO_ID")
  @JsonIgnore
  private Livro livro;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

}
