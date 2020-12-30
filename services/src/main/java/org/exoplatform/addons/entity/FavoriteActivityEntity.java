package org.exoplatform.addons.entity;


import javax.persistence.*;

import org.exoplatform.commons.api.persistence.ExoEntity;
import org.exoplatform.social.core.jpa.storage.entity.ActivityEntity;

import java.util.Calendar;

@Entity
@ExoEntity
@Table(name = "ADDON_FAVOURITE_ACTIVITY")
public class FavoriteActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name="ACTIVITY_ID")
    private String activityTitle;



    @ManyToOne
    @JoinColumn(name="FAVOURITE_BY_ID")
    private ActivityEntity targetActivity;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FAVOURITE_DATE")
    private Calendar favouriteDate;


    public FavoriteActivityEntity() {
    }

    public FavoriteActivityEntity(Long id, String activityTitle, ActivityEntity targetActivity, Calendar favouriteDate) {
        this.id = id;
        this.activityTitle = activityTitle;
        this.targetActivity = targetActivity;
        this.favouriteDate = favouriteDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public ActivityEntity getTargetActivity() {
        return targetActivity;
    }

    public void setTargetActivity(ActivityEntity targetActivity) {
        this.targetActivity = targetActivity;
    }

    public Calendar getFavouriteDate() {
        return favouriteDate;
    }

    public void setFavouriteDate(Calendar favouriteDate) {
        this.favouriteDate = favouriteDate;
    }
}
