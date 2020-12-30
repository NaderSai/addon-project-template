package org.exoplatform.addons.DAO;

import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class FavoriteActivityDAO extends GenericDAOJPAImpl<FavoriteActivityEntity, Long> {

    public FavoriteActivityDAO() {
    }

    public FavoriteActivityEntity findActivity(Long id)
    {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        Query q = em.createQuery("select f from FavoriteActivityEntity f where f.ID= :idd");
        q.setParameter("idd", id);
        FavoriteActivityEntity res = (FavoriteActivityEntity) q.getSingleResult();
        em.getTransaction().commit();
        return res;
    }

    public List<FavoriteActivityEntity> findAllActivity()
    {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        Query q = em.createQuery("select f from FavoriteActivityEntity f  ");
        List<FavoriteActivityEntity> list = q.getResultList();
        em.getTransaction().commit();
        return list;
    }

    public void addActivity(FavoriteActivityEntity entity) {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void addAllActivity(List<FavoriteActivityEntity> entities)
    {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        em.persist(entities);
        em.getTransaction().commit();
    }

    public void updateActivity(FavoriteActivityEntity entity)
    {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        getEntityManager().merge(entity);
        em.getTransaction().commit();
    }

    public void updateAllActivity(List<FavoriteActivityEntity> entities)
    {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        for (FavoriteActivityEntity entity : entities) {
            getEntityManager().merge(entity);
        }
        em.getTransaction().commit();

    }

    public void deleteActivity(Long id)
    {
        FavoriteActivityEntity entity = findActivity(id);
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    public void deleteAllActivity()
    {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        em.createQuery("delete from FavoriteActivityEntity ").executeUpdate();
        em.getTransaction().commit();
    }
}
