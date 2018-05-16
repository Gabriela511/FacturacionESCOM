/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Vendedor;
import java.util.List;

/**
 *
 * @author darka
 */
public interface vendedorDAO {
    public List<Vendedor> listarVendedor();
    public void newVendedor(Vendedor vendedor);
    public void updateVendedor(Vendedor vendedor);
    public void deleteVendedor(Vendedor vendedor);
}
