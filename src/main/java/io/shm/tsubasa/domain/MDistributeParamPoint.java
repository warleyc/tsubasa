package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MDistributeParamPoint.
 */
@Entity
@Table(name = "m_distribute_param_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MDistributeParamPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "target_attribute", nullable = false)
    private Integer targetAttribute;

    @NotNull
    @Column(name = "target_nationality_group_id", nullable = false)
    private Integer targetNationalityGroupId;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    
    @Lob
    @Column(name = "thumbnail_asset_name", nullable = false)
    private String thumbnailAssetName;

    
    @Lob
    @Column(name = "icon_asset_name", nullable = false)
    private String iconAssetName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetAttribute() {
        return targetAttribute;
    }

    public MDistributeParamPoint targetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
        return this;
    }

    public void setTargetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public Integer getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public MDistributeParamPoint targetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
        return this;
    }

    public void setTargetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
    }

    public String getName() {
        return name;
    }

    public MDistributeParamPoint name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public MDistributeParamPoint description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public MDistributeParamPoint thumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
        return this;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }

    public String getIconAssetName() {
        return iconAssetName;
    }

    public MDistributeParamPoint iconAssetName(String iconAssetName) {
        this.iconAssetName = iconAssetName;
        return this;
    }

    public void setIconAssetName(String iconAssetName) {
        this.iconAssetName = iconAssetName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MDistributeParamPoint)) {
            return false;
        }
        return id != null && id.equals(((MDistributeParamPoint) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MDistributeParamPoint{" +
            "id=" + getId() +
            ", targetAttribute=" + getTargetAttribute() +
            ", targetNationalityGroupId=" + getTargetNationalityGroupId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", iconAssetName='" + getIconAssetName() + "'" +
            "}";
    }
}
