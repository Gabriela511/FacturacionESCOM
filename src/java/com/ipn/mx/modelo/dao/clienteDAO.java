/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Cliente;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author darka
 */
public interface clienteDAO {
    public List<Cliente> listarCliente();
    public void newCliente(Cliente cliente);
    public void updateCliente(Cliente cliente);
    public void deleteCliente(Cliente cliente);
    
    //facturaBean
    public Cliente obtenerClientePorID(Session session, Integer codCliente) throws Exception;
}
