/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.cake.common.service.jpaimpl;

import br.senac.tads.cake.common.entity.Categoria;
import br.senac.tads.cake.common.entity.Produto;
import br.senac.tads.cake.common.service.ProdutoService;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author fernando.tsuda
 */
public class ProdutoServiceJPAImpl implements ProdutoService {

  private EntityManagerFactory emFactory
          = Persistence.createEntityManagerFactory("CakePU");

  @Override
  public List<Produto> listar(int offset, int quantidade) {
    EntityManager em = emFactory.createEntityManager();
    Query query = em.createNamedQuery("Produto.listar")
            .setFirstResult(offset)
            .setMaxResults(quantidade);
    List<Produto> resultados = query.getResultList();
    em.close();
    return resultados;
  }

  @Override
  public List<Produto> listarPorCategoria(Categoria categoria, int offset, int quantidade) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Produto obter(Long idProduto) {
    EntityManager em = emFactory.createEntityManager();
    Query query = em.createNamedQuery("Produto.obter")
            .setParameter("idProduto", idProduto);
    Produto p = (Produto) query.getSingleResult();
    em.close();
    return p;
  }

  @Override
  public void incluir(Produto p) {
    EntityManager em = emFactory.createEntityManager();
    EntityTransaction transacao = em.getTransaction();
    try {
      // Loop para fazer attach do objeto Categoria no EntityManager, para 
      // evitar a criação de categorias com nomes duplicados.
      transacao.begin();
      for (Categoria c : p.getCategorias()) {
        if (c.getId() != null) {
          em.merge(c);
        } else {
          em.persist(c);
        }
      }
      em.persist(p);
      transacao.commit();
    } catch (Exception e) {
      transacao.rollback();
    } finally {
      em.close();
    }
  }

  @Override
  public void alterar(Produto p) {
    EntityManager em = emFactory.createEntityManager();
    EntityTransaction transacao = em.getTransaction();
    try {
      // Loop para fazer attach do objeto Categoria no EntityManager, para 
      // evitar a criação de categorias com nomes duplicados.
      transacao.begin();
      for (Categoria c : p.getCategorias()) {
        if (c.getId() != null) {
          em.merge(c);
        } else {
          em.persist(c);
        }
      }
      em.merge(p);
      transacao.commit();
    } catch (Exception e) {
      transacao.rollback();
    } finally {
      em.close();
    }
  }

  @Override
  public void remover(Long idProduto) {
    EntityManager em = emFactory.createEntityManager();
    EntityTransaction transacao = em.getTransaction();
    try {
      transacao.begin();
      Produto p = em.find(Produto.class, idProduto);
      em.remove(p);
      transacao.commit();
    } catch (Exception e) {
      transacao.rollback();
    } finally {
      em.close();
    }
  }

}
