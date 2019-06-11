package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MApRecoveryItem.
 */
@Entity
@Table(name = "m_ap_recovery_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MApRecoveryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ap_recovery_item_type", nullable = false)
    private Integer apRecoveryItemType;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    
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

    public Integer getApRecoveryItemType() {
        return apRecoveryItemType;
    }

    public MApRecoveryItem apRecoveryItemType(Integer apRecoveryItemType) {
        this.apRecoveryItemType = apRecoveryItemType;
        return this;
    }

    public void setApRecoveryItemType(Integer apRecoveryItemType) {
        this.apRecoveryItemType = apRecoveryItemType;
    }

    public String getName() {
        return name;
    }

    public MApRecoveryItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public MApRecoveryItem description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public MApRecoveryItem thumbnailAssetName(String thumbnailAssetName) {
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
        if (!(o instanceof MApRecoveryItem)) {
            return false;
        }
        return id != null && id.equals(((MApRecoveryItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MApRecoveryItem{" +
            "id=" + getId() +
            ", apRecoveryItemType=" + getApRecoveryItemType() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            "}";
    }
}
