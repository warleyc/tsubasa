package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MDeckRarityConditionDescription.
 */
@Entity
@Table(name = "m_deck_rarity_condition_description")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MDeckRarityConditionDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rarity_group_id", nullable = false)
    private Integer rarityGroupId;

    @NotNull
    @Column(name = "count_type", nullable = false)
    private Integer countType;

    @NotNull
    @Column(name = "is_starting", nullable = false)
    private Integer isStarting;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    
    @Lob
    @Column(name = "icon_name", nullable = false)
    private String iconName;

    
    @Lob
    @Column(name = "small_icon_name", nullable = false)
    private String smallIconName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRarityGroupId() {
        return rarityGroupId;
    }

    public MDeckRarityConditionDescription rarityGroupId(Integer rarityGroupId) {
        this.rarityGroupId = rarityGroupId;
        return this;
    }

    public void setRarityGroupId(Integer rarityGroupId) {
        this.rarityGroupId = rarityGroupId;
    }

    public Integer getCountType() {
        return countType;
    }

    public MDeckRarityConditionDescription countType(Integer countType) {
        this.countType = countType;
        return this;
    }

    public void setCountType(Integer countType) {
        this.countType = countType;
    }

    public Integer getIsStarting() {
        return isStarting;
    }

    public MDeckRarityConditionDescription isStarting(Integer isStarting) {
        this.isStarting = isStarting;
        return this;
    }

    public void setIsStarting(Integer isStarting) {
        this.isStarting = isStarting;
    }

    public String getDescription() {
        return description;
    }

    public MDeckRarityConditionDescription description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconName() {
        return iconName;
    }

    public MDeckRarityConditionDescription iconName(String iconName) {
        this.iconName = iconName;
        return this;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getSmallIconName() {
        return smallIconName;
    }

    public MDeckRarityConditionDescription smallIconName(String smallIconName) {
        this.smallIconName = smallIconName;
        return this;
    }

    public void setSmallIconName(String smallIconName) {
        this.smallIconName = smallIconName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MDeckRarityConditionDescription)) {
            return false;
        }
        return id != null && id.equals(((MDeckRarityConditionDescription) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MDeckRarityConditionDescription{" +
            "id=" + getId() +
            ", rarityGroupId=" + getRarityGroupId() +
            ", countType=" + getCountType() +
            ", isStarting=" + getIsStarting() +
            ", description='" + getDescription() + "'" +
            ", iconName='" + getIconName() + "'" +
            ", smallIconName='" + getSmallIconName() + "'" +
            "}";
    }
}
