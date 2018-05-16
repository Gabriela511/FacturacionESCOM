package com.ipn.mx.beans;

import com.ipn.mx.modelo.dao.clienteDAO;
import com.ipn.mx.modelo.dao.clienteDAOImp;
import com.ipn.mx.modelo.entidades.Cliente;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class clienteBean {

    private List<Cliente> listaClientes;
    private Cliente cliente;
    
    public clienteBean() {
    }

    public List<Cliente> getListaClientes(){
        clienteDAO cdao = new clienteDAOImp();
        listaClientes = cdao.listarCliente();
        return listaClientes;
    }
    
    public void prepararNuevoCliente(){
        cliente = new Cliente();
    }
    
    public void nuevoCliente(){
        clienteDAO cdao = new clienteDAOImp();
        cdao.newCliente(cliente);
    }
    
    public void modificarCliente(){
        clienteDAO cdao =  new clienteDAOImp();
        cdao.updateCliente(cliente);
        cliente = new Cliente();
    }
    
    public void eliminarCliente(){
        clienteDAO cdao = new clienteDAOImp();
        cdao.deleteCliente(cliente);
        cliente = new Cliente();
    }
    
    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
