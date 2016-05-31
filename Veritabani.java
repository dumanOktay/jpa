/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author oktay
 */
public class Veritabani {
    public static EntityManagerFactory emf;
    public static EntityManager em;
    public static void baglantiAc(){
        emf=javax.persistence.Persistence.createEntityManagerFactory("danPU");
        em=emf.createEntityManager();
        System.out.println("girildi");
    }
    
    public static void persist(Object object){
       if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
       }
        
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
        }
       
    }
    
    public static void merge(Object object){
        em.getTransaction().begin();
        
        try {
          
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
        }
        //em.flush();
    }
    
     public static  List readObject(Object object){
       
       // onCreate();
        Query query=em.createQuery("select h from "+object.getClass().getSimpleName()+" h");
        
        return  query.getResultList();
    }
     
     public static Object findObject(Object object,String kosul){
         Query querySql=em.createQuery("select o from "+object.getClass().getSimpleName()
                +" o where o."+kosul);
         
         if(querySql.getResultList().isEmpty())
         {
             return null;
         }else{
              return  querySql.getResultList().get(0);
         }
       
     }
     
}
