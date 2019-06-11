package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MArousalItem.
 */
@Entity
@Table(name = "m_arousal_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MArousalItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
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
    @Column(name = "thumbnail_bg_asset_name", nullable = false)
    private String thumbnailBgAssetName;

    
    @Lob
    @Column(name = "thumbnail_frame_asset_name", nullable = false)
    private String thumbnailFrameAssetName;

    @NotNull
    @Column(name = "arousal_item_type", nullable = false)
    private Integer arousalItemType;

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

    public MArousalItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public MArousalItem description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public MArousalItem thumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
        return this;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }

    public String getThumbnailBgAssetName() {
        return thumbnailBgAssetName;
    }

    public MArousalItem thumbnailBgAssetName(String thumbnailBgAssetName) {
        this.thumbnailBgAssetName = thumbnailBgAssetName;
        return this;
    }

    public void setThumbnailBgAssetName(String thumbnailBgAssetName) {
        this.thumbnailBgAssetName = thumbnailBgAssetName;
    }

    public String getThumbnailFrameAssetName() {
        return thumbnailFrameAssetName;
    }

    public MArousalItem thumbnailFrameAssetName(String thumbnailFrameAssetName) {
        this.thumbnailFrameAssetName = thumbnailFrameAssetName;
        return this;
    }

    public void setThumbnailFrameAssetName(String thumbnailFrameAssetName) {
        this.thumbnailFrameAssetName = thumbnailFrameAssetName;
    }

    public Integer getArousalItemType() {
        return arousalItemType;
    }

    public MArousalItem arousalItemType(Integer arousalItemType) {
        this.arousalItemType = arousalItemType;
        return this;
    }

    public void setArousalItemType(Integer arousalItemType) {
        this.arousalItemType = arousalItemType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MArousalItem)) {
            return false;
        }
        return id != null && id.equals(((MArousalItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MArousalItem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", thumbnailBgAssetName='" + getThumbnailBgAssetName() + "'" +
            ", thumbnailFrameAssetName='" + getThumbnailFrameAssetName() + "'" +
            ", arousalItemType=" + getArousalItemType() +
            "}";
    }
}
