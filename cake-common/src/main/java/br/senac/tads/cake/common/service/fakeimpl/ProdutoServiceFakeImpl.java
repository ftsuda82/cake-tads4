/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.cake.common.service.fakeimpl;

import br.senac.tads.cake.common.entity.Categoria;
import br.senac.tads.cake.common.entity.ImagemProduto;
import br.senac.tads.cake.common.entity.Produto;
import br.senac.tads.cake.common.service.CategoriaService;
import br.senac.tads.cake.common.service.ProdutoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fernando
 */
public class ProdutoServiceFakeImpl implements ProdutoService {
  
  private static final Map<Long, Produto> MAPA_PRODUTOS = new LinkedHashMap<Long, Produto>();
  
  private static final String DESCRICAO_PADRAO = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
          + "Aenean vel ipsum vehicula, venenatis leo nec, ornare felis. Ut consectetur est vel pulvinar tempus. "
          + "Suspendisse commodo cursus turpis. Etiam ac enim egestas, sollicitudin libero ac, eleifend risus. "
          + "Phasellus nec posuere magna, in vehicula elit. "
          + "Etiam rhoncus, ipsum eget dapibus vulputate, massa nisi feugiat odio, a consectetur urna diam id risus. "
          + "Morbi sed pharetra nisl, nec aliquam ex. Morbi congue urna ut semper aliquam. "
          + "Sed aliquet turpis ac sem egestas dignissim. Praesent interdum dapibus cursus. "
          + "Cras posuere tempor lectus, ac porttitor tellus maximus vel.";
  
  static {
    CategoriaService categorias = new CategoriaServiceFakeImpl();
    Produto produto = new Produto(1L, "Floresta negra",
            DESCRICAO_PADRAO,
            Arrays.asList(new ImagemProduto(1L, "Bla bla bla", "imagem01a.jpg"), new ImagemProduto(2L, "Xpto Xpto", "imagem01b.jpg"), new ImagemProduto(3L, "Chola mais", "imagem01c.jpg")),
            Arrays.asList(categorias.obter(1L), categorias.obter(3L)),
            new BigDecimal(100));
    MAPA_PRODUTOS.put(produto.getId(), produto);
    produto = new Produto(2L, "Torta de morango",
            DESCRICAO_PADRAO,
            Arrays.asList(new ImagemProduto(4L, "Bla bla bla", "imagem02a.jpg"), new ImagemProduto(5L, "Xpto Xpto", "imagem02b.jpg")),
            Arrays.asList(categorias.obter(1L), categorias.obter(3L)),
            new BigDecimal(90));
    MAPA_PRODUTOS.put(produto.getId(), produto);
    produto = new Produto(3L, "Sonho de valsa",
            DESCRICAO_PADRAO,
            Arrays.asList(new ImagemProduto(6L, "Bla bla bla", "imagem03a.jpg")),
            Arrays.asList(categorias.obter(1L), categorias.obter(3L), categorias.obter(6L)),
            new BigDecimal(110));
    MAPA_PRODUTOS.put(produto.getId(), produto);
    produto = new Produto(4L, "Morango com leite condensado",
            DESCRICAO_PADRAO,
            Arrays.asList(new ImagemProduto(7L, "Bla bla bla", "imagem04a.jpg"), new ImagemProduto(8L, "Xpto Xpto", "imagem04b.jpg")),
            Arrays.asList(categorias.obter(1L), categorias.obter(4L)),
            new BigDecimal(105));
    MAPA_PRODUTOS.put(produto.getId(), produto);
    produto = new Produto(5L, "Abacaxi com coco",
            DESCRICAO_PADRAO,
            Arrays.asList(new ImagemProduto(9L, "Bla bla bla", "imagem04a.jpg"), new ImagemProduto(10L, "Xpto Xpto", "imagem04b.jpg")),
            Arrays.asList(categorias.obter(1L), categorias.obter(5L), categorias.obter(7L), categorias.obter(8L)),
            new BigDecimal(85));
    MAPA_PRODUTOS.put(produto.getId(), produto);
  }

  @Override
  public List<Produto> listar(int offset, int quantidade) {
    return new ArrayList<Produto>(MAPA_PRODUTOS.values());
  }

  @Override
  public List<Produto> listarPorCategoria(Categoria categoria, int offset, int quantidade) {
    List<Produto> lista = new ArrayList<Produto>();
    for (Map.Entry<Long, Produto> entry : MAPA_PRODUTOS.entrySet()) {
      Produto p = entry.getValue();
      if (p.getCategorias().contains(categoria)) {
        lista.add(p);
      }
    }
    return lista;
  }

  @Override
  public Produto obter(Long idProduto) {
    return MAPA_PRODUTOS.get(idProduto);
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
