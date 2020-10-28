package com.empresa.appempresa.models;

import com.empresa.appempresa.entidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DELL
 */
public class ModelCliente {

    public static EntityManager openDB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Empresa_PU");
        return emf.createEntityManager();
    }

    public boolean salvar(Cliente c) {
        EntityManager em = ModelCliente.openDB();//Abre a conexão
        try {
            em.getTransaction().begin();//Início da transação
            if (c.getId() == null) {
                em.persist(c);//Monta um INSERT INTO..
            } else {
                em.merge(c);//Monat um UPDATE
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
    
    /**
     *
     * @return
     */
    public List<Cliente> listaClientes(){
        EntityManager em = ModelCliente.openDB();
        try{
            return em.createQuery("Select c from Cliente c order by c.nome").getResultList();
        } finally{
            em.close();
        }
    }

}
