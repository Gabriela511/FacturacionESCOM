/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.beans;

import com.ipn.mx.modelo.dao.clienteDAO;
import com.ipn.mx.modelo.dao.clienteDAOImp;
import com.ipn.mx.modelo.entidades.Cliente;
import com.ipn.mx.utilerias.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author darka
 */
@ManagedBean
@ViewScoped
public class facturaBean {

    Session session = null;
    Transaction transaction = null;

    private Cliente cliente;
    private Integer codigoCliente;

    public facturaBean() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    //Agrega datos por dialogCliente
    public void agregarDatosCliente(int codCliente) {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDAO dao = new clienteDAOImp();
            this.transaction = this.session.beginTransaction();

            this.cliente = dao.obtenerClientePorID(this.session, codCliente);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del cliente obtenido"));
        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(this.session!=null){
                this.session.close();
            }
        }
    }
    
    //agregar datos del cliente buscado por codCliente
    public void agregarDatosCliente2() {
        this.session = null;
        this.transaction = null;
        try {
            if(this.codigoCliente==null) return;           //si esta vacio, vuelve a ejecutar 
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDAO dao = new clienteDAOImp();
            this.transaction = this.session.beginTransaction();

            this.cliente = dao.obtenerClientePorID(this.session, this.codigoCliente);
            if(this.cliente != null){           //si tiene cliente datos, es correcto 
                this.codigoCliente =null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del cliente agregado"));    
            }else{              //en caso que no, mandar error
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos del cliente NO encontrado"));
            }
            this.transaction.commit();
        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(this.session!=null){
                this.session.close();
            }
        }
    }
}
