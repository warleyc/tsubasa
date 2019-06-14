package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MMarathonBoostItem.
 */
@Entity
@Table(name = "m_marathon_boost_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMarathonBoostItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id")
    private Integer eventId;

    @NotNull
    @Column(name = "boost_ratio", nullable = false)
    private Integer boostRatio;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "short_name", nullable = false)
    private String shortName;

    
    @Lob
    @Column(name = "thumbnail_asset_name", nullable = false)
    private String thumbnailAssetName;

    
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

    public Integer getEventId() {
        return eventId;
    }

    public MMarathonBoostItem eventId(Integer eventId) {
        this.eventId = eventId;
        return this;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getBoostRatio() {
        return boostRatio;
    }

    public MMarathonBoostItem boostRatio(Integer boostRatio) {
        this.boostRatio = boostRatio;
        return this;
    }

    public void setBoostRatio(Integer boostRatio) {
        this.boostRatio = boostRatio;
    }

    public String getName() {
        return name;
    }

    public MMarathonBoostItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public MMarathonBoostItem shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public MMarathonBoostItem thumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
        return this;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }

    public String getDescription() {
        return description;
    }

    public MMarathonBoostItem description(String description) {
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
        if (!(o instanceof MMarathonBoostItem)) {
            return false;
        }
        return id != null && id.equals(((MMarathonBoostItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMarathonBoostItem{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", boostRatio=" + getBoostRatio() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
