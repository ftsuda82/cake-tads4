/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.cake.jsf2.managedbean;

import br.senac.tads.cake.common.entity.Produto;
import br.senac.tads.cake.common.service.ProdutoService;
import br.senac.tads.cake.common.service.fakeimpl.ProdutoServiceFakeImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ProdutoBean implements Serializable {
    
    @ManagedProperty(value="#{param.id}")
    private Long idProd;

    private List<Produto> listaProdutos;

    /**
     * Creates a new instance of ProdutoBean
     */
    public ProdutoBean() {
    }

    /**
     * @return the lista
     */
    public List<Produto> getLista() {
        ProdutoService service = new ProdutoServiceFakeImpl();
        setListaProdutos(service.listar(0, 100));
        return getListaProdutos();
    }

    public Produto getProduto() {
        /*
        String id = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");
                
        Long idProd = Long.parseLong(id);*/
        ProdutoService service = new ProdutoServiceFakeImpl();
        return service.obter(idProd);
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }
}
