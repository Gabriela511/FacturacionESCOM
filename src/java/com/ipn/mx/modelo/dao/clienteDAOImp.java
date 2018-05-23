/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Cliente;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**

 * @author Gabriela A. Perez 
 * @author Manuel Sandoval Arreola 
 */
public class clienteDAOImp implements clienteDAO{

    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> lista=null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t =  session.beginTransaction();
        String hql = "FROM Cliente";
        
        try{
            lista = session.createQuery(hql).list();
            t.commit();
            session.close();
        }catch(Exception e){
            t.rollback();
        }
        return lista;
    }

    @Override
    public void newCliente(Cliente cliente) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(cliente);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void updateCliente(Cliente cliente) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(cliente);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void deleteCliente(Cliente cliente) {
        Session session =null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(cliente);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        
    }
    
    @Override
    public Cliente obtenerClientePorID(Session session, Integer codCliente){
        String hql = "FROM Cliente WHERE codCliente = :codCliente";
        Query q = session.createQuery(hql);
        q.setParameter("codCliente", codCliente);
        return (Cliente) q.uniqueResult();
    }
    
}
