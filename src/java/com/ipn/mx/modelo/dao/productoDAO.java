/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Producto;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author darka
 */
public interface productoDAO {
    public List<Producto> listarProducto();
    public void newProducto(Producto producto);
    public void updateProducto(Producto producto);
    public void deleteProducto(Producto producto);
    
    /* Metodos para FACTURA */
    public Producto obtenerProductoPorCodBarra(Session session, String codBarra) throws Exception;
}
