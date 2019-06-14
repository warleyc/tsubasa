package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MMedal.
 */
@Entity
@Table(name = "m_medal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMedal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "medal_type", nullable = false)
    private Integer medalType;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "max_amount", nullable = false)
    private Integer maxAmount;

    
    @Lob
    @Column(name = "icon_asset_name", nullable = false)
    private String iconAssetName;

    
    @Lob
    @Column(name = "thumbnail_asset_name", nullable = false)
    private String thumbnailAssetName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMedalType() {
        return medalType;
    }

    public MMedal medalType(Integer medalType) {
        this.medalType = medalType;
        return this;
    }

    public void setMedalType(Integer medalType) {
        this.medalType = medalType;
    }

    public String getName() {
        return name;
    }

    public MMedal name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public MMedal description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public MMedal maxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
        return this;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getIconAssetName() {
        return iconAssetName;
    }

    public MMedal iconAssetName(String iconAssetName) {
        this.iconAssetName = iconAssetName;
        return this;
    }

    public void setIconAssetName(String iconAssetName) {
        this.iconAssetName = iconAssetName;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public MMedal thumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
        return this;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MMedal)) {
            return false;
        }
        return id != null && id.equals(((MMedal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMedal{" +
            "id=" + getId() +
            ", medalType=" + getMedalType() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", maxAmount=" + getMaxAmount() +
            ", iconAssetName='" + getIconAssetName() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            "}";
    }
}
