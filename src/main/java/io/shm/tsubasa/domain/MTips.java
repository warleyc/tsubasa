package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTips.
 */
@Entity
@Table(name = "m_tips")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTips implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "priority", nullable = false)
    private Integer priority;

    
    @Lob
    @Column(name = "title", nullable = false)
    private String title;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    
    @Lob
    @Column(name = "image_asset_name", nullable = false)
    private String imageAssetName;

    @NotNull
    @Column(name = "color_type", nullable = false)
    private Integer colorType;

    @NotNull
    @Column(name = "is_tips", nullable = false)
    private Integer isTips;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Integer startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private Integer endAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public MTips priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public MTips title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public MTips description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageAssetName() {
        return imageAssetName;
    }

    public MTips imageAssetName(String imageAssetName) {
        this.imageAssetName = imageAssetName;
        return this;
    }

    public void setImageAssetName(String imageAssetName) {
        this.imageAssetName = imageAssetName;
    }

    public Integer getColorType() {
        return colorType;
    }

    public MTips colorType(Integer colorType) {
        this.colorType = colorType;
        return this;
    }

    public void setColorType(Integer colorType) {
        this.colorType = colorType;
    }

    public Integer getIsTips() {
        return isTips;
    }

    public MTips isTips(Integer isTips) {
        this.isTips = isTips;
        return this;
    }

    public void setIsTips(Integer isTips) {
        this.isTips = isTips;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MTips startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MTips endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTips)) {
            return false;
        }
        return id != null && id.equals(((MTips) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTips{" +
            "id=" + getId() +
            ", priority=" + getPriority() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageAssetName='" + getImageAssetName() + "'" +
            ", colorType=" + getColorType() +
            ", isTips=" + getIsTips() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
