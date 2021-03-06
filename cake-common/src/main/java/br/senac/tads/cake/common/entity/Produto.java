/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.cake.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernando.tsuda
 */
@Entity
@Table(name = "TB_PRODUTO")
@NamedQueries({
  @NamedQuery(name = "Produto.listar",
          query = "SELECT DISTINCT p FROM Produto p "
          + "LEFT JOIN FETCH p.categorias "
          + "LEFT JOIN FETCH p.imagens"),
  @NamedQuery(name = "Produto.listarPorCategoria",
          query = "SELECT DISTINCT p FROM Produto p "
          + "LEFT JOIN FETCH p.categorias "
          + "LEFT JOIN FETCH p.imagens "
          + "INNER JOIN p.categorias c "
          + "WHERE c.id = :iCategoria"),
  @NamedQuery(name = "Produto.obter",
          query = "SELECT DISTINCT p FROM Produto p "
          + "LEFT JOIN FETCH p.categorias "
          + "LEFT JOIN FETCH p.imagens "
          + "WHERE p.id = :idProduto")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Produto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_PRODUTO")
  private Long id;

  @Column(name = "NM_PRODUTO", nullable = false)
  private String nome;

  @Column(name = "DS_PRODUTO")
  private String descricao;

  @Column(name = "VL_PRODUTO", precision = 12,
          scale = 2, nullable = false)
  private BigDecimal preco;

  @Column(name = "DT_CADASTRO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dtCadastro;

  @OneToMany(mappedBy = "produto")
  private List<ImagemProduto> imagens;

  @ManyToMany
  @JoinTable(name = "TB_PRODUTO_CATEGORIA",
          joinColumns = {
            @JoinColumn(name = "ID_PRODUTO")
          },
          inverseJoinColumns = {
            @JoinColumn(name = "ID_CATEGORIA")
          })
  private List<Categoria> categorias;

  @Transient
  private List<ItemCompra> itensCompra;

  public Produto() {
    this.dtCadastro = new Date();
  }

  public Produto(Long id, String nome, String descricao,
          List<ImagemProduto> imagens, List<Categoria> categorias, BigDecimal preco, Date dtCadastro) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.imagens = imagens;
    this.categorias = categorias;
    this.preco = preco;
    this.dtCadastro = dtCadastro;
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

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public List<ImagemProduto> getImagens() {
    return imagens;
  }

  public void setImagens(List<ImagemProduto> imagens) {
    this.imagens = imagens;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public Date getDtCadastro() {
    return dtCadastro;
  }

  public void setDtCadastro(Date dtCadastro) {
    this.dtCadastro = dtCadastro;
  }

  public List<Categoria> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<Categoria> categorias) {
    this.categorias = categorias;
  }

  public List<ItemCompra> getItensCompra() {
    return itensCompra;
  }

  public void setItensCompra(List<ItemCompra> itensCompra) {
    this.itensCompra = itensCompra;
  }

  @Override
  public String toString() {
    return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + ", dtCadastro=" + dtCadastro + ", imagens=" + imagens + ", categorias=" + categorias + ", itensCompra=" + itensCompra + '}';
  }

}
