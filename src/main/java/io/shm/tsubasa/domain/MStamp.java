package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MStamp.
 */
@Entity
@Table(name = "m_stamp")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MStamp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "thumbnail_asset", nullable = false)
    private String thumbnailAsset;

    @Lob
    @Column(name = "sound_event")
    private String soundEvent;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MStamp name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailAsset() {
        return thumbnailAsset;
    }

    public MStamp thumbnailAsset(String thumbnailAsset) {
        this.thumbnailAsset = thumbnailAsset;
        return this;
    }

    public void setThumbnailAsset(String thumbnailAsset) {
        this.thumbnailAsset = thumbnailAsset;
    }

    public String getSoundEvent() {
        return soundEvent;
    }

    public MStamp soundEvent(String soundEvent) {
        this.soundEvent = soundEvent;
        return this;
    }

    public void setSoundEvent(String soundEvent) {
        this.soundEvent = soundEvent;
    }

    public String getDescription() {
        return description;
    }

    public MStamp description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MStamp)) {
            return false;
        }
        return id != null && id.equals(((MStamp) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MStamp{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", thumbnailAsset='" + getThumbnailAsset() + "'" +
            ", soundEvent='" + getSoundEvent() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
