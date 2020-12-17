package org.exoplatform.addons.entity;

import javax.persistence.*;

import org.exoplatform.commons.api.persistence.ExoEntity;
import org.exoplatform.social.core.jpa.storage.entity.ActivityEntity;
import org.exoplatform.social.core.jpa.storage.entity.IdentityEntity;

import java.util.Calendar;

@Entity
@ExoEntity
@Table(name = "ADDON_FAVOURITE_ACTIVITY")
public class FavouriteActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    private IdentityEntity favouriteBy;
    @ManyToOne
    private ActivityEntity activity;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FAVOURITE_DATE")
    private Calendar favouriteDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IdentityEntity getFavouriteBy() {
        return favouriteBy;
    }

    public void setFavouriteBy(IdentityEntity favouriteBy) {
        this.favouriteBy = favouriteBy;
    }

    public ActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(ActivityEntity activity) {
        this.activity = activity;
    }

    public Calendar getFavouriteDate() {
        return favouriteDate;
    }

    public void setFavouriteDate(Calendar favouriteDate) {
        this.favouriteDate = favouriteDate;
    }
}
