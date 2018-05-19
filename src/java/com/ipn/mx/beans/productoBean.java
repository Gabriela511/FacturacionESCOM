/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.beans;

import com.ipn.mx.modelo.dao.productoDAO;
import com.ipn.mx.modelo.dao.productoDAOImp;
import com.ipn.mx.modelo.entidades.Producto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author darka
 */
@ManagedBean
@ViewScoped
public class productoBean {

    private List<Producto> listaProducto;
    private Producto producto;
    /**
     * Creates a new instance of productoBean
     */
    public productoBean() {
    }
    
    public List<Producto> getListaProducto() {
        productoDAO pdao = new productoDAOImp();
        listaProducto = pdao.listarProducto();
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public void prepararNuevoProducto(){
        producto = new Producto();
    }
    
    public void nuevoProducto(){
        productoDAO pdao = new productoDAOImp();
        pdao.newProducto(producto);
    }
    
    public void modificarProducto(){
        productoDAO pdao = new productoDAOImp();
        pdao.updateProducto(producto);
        producto = new Producto();
    }
    
    public void eliminarProducto(){
        productoDAO pdao = new productoDAOImp();
        pdao.deleteProducto(producto);
        producto = new Producto();
    }
    
}
