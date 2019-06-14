package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MUniformBottom.
 */
@Entity
@Table(name = "m_uniform_bottom")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MUniformBottom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "short_name", nullable = false)
    private String shortName;

    
    @Lob
    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @NotNull
    @Column(name = "model_id", nullable = false)
    private Integer modelId;

    
    @Lob
    @Column(name = "thumbnail_asset_name", nullable = false)
    private String thumbnailAssetName;

    @NotNull
    @Column(name = "uniform_type", nullable = false)
    private Integer uniformType;

    @NotNull
    @Column(name = "is_default", nullable = false)
    private Integer isDefault;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    
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

    public MUniformBottom name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public MUniformBottom shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getMenuName() {
        return menuName;
    }

    public MUniformBottom menuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public MUniformBottom modelId(Integer modelId) {
        this.modelId = modelId;
        return this;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public MUniformBottom thumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
        return this;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }

    public Integer getUniformType() {
        return uniformType;
    }

    public MUniformBottom uniformType(Integer uniformType) {
        this.uniformType = uniformType;
        return this;
    }

    public void setUniformType(Integer uniformType) {
        this.uniformType = uniformType;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public MUniformBottom isDefault(Integer isDefault) {
        this.isDefault = isDefault;
        return this;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public MUniformBottom orderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getDescription() {
        return description;
    }

    public MUniformBottom description(String description) {
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
        if (!(o instanceof MUniformBottom)) {
            return false;
        }
        return id != null && id.equals(((MUniformBottom) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MUniformBottom{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", menuName='" + getMenuName() + "'" +
            ", modelId=" + getModelId() +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", uniformType=" + getUniformType() +
            ", isDefault=" + getIsDefault() +
            ", orderNum=" + getOrderNum() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
