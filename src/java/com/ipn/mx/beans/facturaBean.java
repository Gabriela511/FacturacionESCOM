/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.beans;

import com.ipn.mx.modelo.dao.clienteDAO;
import com.ipn.mx.modelo.dao.clienteDAOImp;
import com.ipn.mx.modelo.dao.productoDAO;
import com.ipn.mx.modelo.dao.productoDAOImp;
import com.ipn.mx.modelo.entidades.Cliente;
import com.ipn.mx.modelo.entidades.Detallefactura;
import com.ipn.mx.modelo.entidades.Producto;
import com.ipn.mx.utilerias.HibernateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class facturaBean{

    Session session = null;
    Transaction transaction = null;

    private Cliente cliente;
    private Integer codigoCliente;

    private Producto producto;
    private String codigoBarra;

    private List<Detallefactura> listaDetalleFactura;

    public facturaBean() {
        this.listaDetalleFactura = new ArrayList<>();
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public List<Detallefactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public void setListaDetalleFactura(List<Detallefactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
    }

    //Agrega datos del cliente por dialogCliente
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
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //agregar datos del cliente buscado por codCliente
    public void agregarDatosCliente2() {
        this.session = null;
        this.transaction = null;
        try {
            if (this.codigoCliente == null) {
                return;           //si esta vacio, vuelve a ejecutar 
            }
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDAO dao = new clienteDAOImp();
            this.transaction = this.session.beginTransaction();

            this.cliente = dao.obtenerClientePorID(this.session, this.codigoCliente);
            if (this.cliente != null) {           //si tiene cliente datos, es correcto 
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del cliente agregado"));
            } else {              //en caso que no, mandar error
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
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //Agrega datos de producto al dialogProductos
    public void agregarDatosProducto(String codBarra) {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDAO pdao = new productoDAOImp();
            this.transaction = this.session.beginTransaction();
            //Obtiene datos y posteriormente los agrega a la lista
            this.producto = pdao.obtenerProductoPorCodBarra(this.session, codBarra);

            this.listaDetalleFactura.add(new Detallefactura(null, null, this.producto.getCodBarra(), this.producto.getNombreProducto(), 0, this.producto.getPrecioVenta(), new BigDecimal(0)));
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado."));
        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
//
    //agregar datos del cliente buscado por codCliente
    public void agregarDatosProducto2() {
        this.session = null;
        this.transaction = null;
        try {
            if (this.codigoBarra.equals("")) {
                return;           //si esta vacio, vuelve a ejecutar 
            }
            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDAO pdao = new productoDAOImp();
            
            this.transaction = this.session.beginTransaction();

            this.producto = pdao.obtenerProductoPorCodBarra(this.session, this.codigoBarra);
            
            if (this.producto != null) {           //si tiene cliente datos, es correcto 
                this.listaDetalleFactura.add(new Detallefactura(null, null, this.producto.getCodBarra(), this.producto.getNombreProducto(), 0, this.producto.getPrecioVenta(), new BigDecimal(0)));
                this.codigoBarra = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado"));
                
            } else {              //en caso que no, mandar error
                this.codigoBarra = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Producto NO encontrado"));
            }
            this.transaction.commit();
        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
}
