package org.exoplatform.addons.DAO;

import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.social.core.jpa.storage.entity.ActivityEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteActivityDAOTest {


    private FavoriteActivityDAO activityDAO;

    @Test
    void testFindActivity() {
        FavoriteActivityEntity favAct = new FavoriteActivityEntity(9L,"title", new ActivityEntity(), Calendar.getInstance());
        activityDAO.addActivity(favAct);
        List<FavoriteActivityEntity> favActs = activityDAO.findAllActivity();
        Assert.assertEquals(favAct.getId(), activityDAO.findActivity(favActs.get(0).getId()));
    }

    @Test
    void testFindAllActivity() {

    }

    @Test
    void testAddActivity() {

        FavoriteActivityEntity favAct = new FavoriteActivityEntity(9L,"title", new ActivityEntity(), Calendar.getInstance());
        activityDAO.addActivity(favAct);
        List<FavoriteActivityEntity> favActs = activityDAO.findAllActivity();
        Assert.assertEquals(favAct.getId(), favActs.get(0).getId());

    }

    @Test
    void testAddAllActivity() {
    }

    @Test
    void testUpdateActivity() {
        FavoriteActivityEntity favAct = new FavoriteActivityEntity(9L,"title", new ActivityEntity(), Calendar.getInstance());
        FavoriteActivityEntity newFavAct = new FavoriteActivityEntity(5L,"title", new ActivityEntity(), Calendar.getInstance());
        activityDAO.addActivity(favAct);

        Assert.assertEquals(favAct,activityDAO.updateActivity(newFavAct));

    }

    @Test
    void testUpdateAllActivity() {
    }

    @Test
    void testDeleteActivity() {
    }

    @Test
    void testDeleteAllActivity() {
    }
}