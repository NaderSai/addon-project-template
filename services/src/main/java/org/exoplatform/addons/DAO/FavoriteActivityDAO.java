package org.exoplatform.addons.DAO;

import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import java.util.List;

public class FavoriteActivityDAO extends GenericDAOJPAImpl {

    public FavoriteActivityDAO() {
    }

    public FavoriteActivityEntity findActivity(Long id)
    {
        return (FavoriteActivityEntity) find(id);
    }

    public List<FavoriteActivityEntity> findAllActivity()
    {
        return findAll();
    }

    public FavoriteActivityEntity addActivity(FavoriteActivityEntity entity) {
        return (FavoriteActivityEntity) create(entity);
    }

    public void addAllActivity(List<FavoriteActivityEntity> entities) {
        createAll(entities);
    }

    public FavoriteActivityEntity updateActivity(FavoriteActivityEntity entity) {
        return (FavoriteActivityEntity) update(entity);
    }

    public void updateAllActivity(List<FavoriteActivityEntity> entities) {
        updateAll(entities);
    }

    public void deleteActivity(FavoriteActivityEntity entity) {
        delete(entity);
    }

    public void deleteAllActivity(List<FavoriteActivityEntity> entities) {
        deleteAll(entities);
    }
}
