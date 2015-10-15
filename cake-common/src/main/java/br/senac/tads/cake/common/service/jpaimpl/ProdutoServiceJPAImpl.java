/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.cake.common.service.jpaimpl;

import br.senac.tads.cake.common.entity.Categoria;
import br.senac.tads.cake.common.entity.Produto;
import br.senac.tads.cake.common.service.ProdutoService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Produto p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(Long idProduto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
