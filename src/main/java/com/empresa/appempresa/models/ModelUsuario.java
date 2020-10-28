/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.appempresa.models;

import com.empresa.appempresa.entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DELL
 */
public class ModelUsuario {

    public static EntityManager openDB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Empresa_PU");
        return emf.createEntityManager();
    }

    public boolean salvar(Usuario u) {
        EntityManager em = ModelCliente.openDB();//Abre a conexão
        try {
            em.getTransaction().begin();//Início da transação
            if (u.getId() == null) {
                em.persist(u);//Monta um INSERT INTO..
            } else {
                em.merge(u);//Monat um UPDATE
            }
            em.getTransaction().commit();//Executa o INSERT ou o UPDATE com o objeto c
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();//Defaz a transação e volta ao estado incial.
            return false;
        } finally {
            em.close();//Fecha a conexão
        }
    }
    
    public Usuario buscaUser(String user){
        EntityManager em = ModelCliente.openDB();//Abre a conexão
        try {
            return (Usuario) em.createQuery("select u from Usuario u where u.user = '"+user+"'").getSingleResult();
        } catch (Exception e) {
            return null;
        }finally{
            em.close();
        }
    }
}
