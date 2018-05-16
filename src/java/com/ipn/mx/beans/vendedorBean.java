/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.beans;

import com.ipn.mx.modelo.dao.vendedorDAO;
import com.ipn.mx.modelo.dao.vendedorDAOImp;
import com.ipn.mx.modelo.entidades.Vendedor;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author darka
 */
@ManagedBean
@ViewScoped
public class vendedorBean {
    private List<Vendedor> listaVendedor;
    private Vendedor vendedor;
    /**
     * Creates a new instance of vendedorBean
     */
    public vendedorBean() {
    }

    
    public List<Vendedor> getListaVendedor() {
        vendedorDAO vdao = new vendedorDAOImp();
        listaVendedor = vdao.listarVendedor();
        return listaVendedor;
    }

    public void setListaVendedor(List<Vendedor> listaVendedor) {
        this.listaVendedor = listaVendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public void prepararNuevoVendedor(){
        vendedor = new Vendedor();
    }
    
    public void nuevoVendedor(){
        vendedorDAO vdao = new vendedorDAOImp();
        vdao.newVendedor(vendedor);
    }
    
    public void modificarVendedor(){
        vendedorDAO vdao = new vendedorDAOImp();
        vdao.updateVendedor(vendedor);
        vendedor = new Vendedor();
    }
    
    public void eliminarVendedor(){
        vendedorDAO vdao = new vendedorDAOImp();
        vdao.deleteVendedor(vendedor);
        vendedor = new Vendedor();
    }
    
}
