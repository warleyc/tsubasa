package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionSwipeIcon.
 */
@Entity
@Table(name = "m_gacha_rendition_swipe_icon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionSwipeIcon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "is_ssr", nullable = false)
    private Integer isSsr;

    @NotNull
    @Column(name = "is_r_or_less", nullable = false)
    private Integer isROrLess;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    
    @Lob
    @Column(name = "swipe_icon_prefab_name", nullable = false)
    private String swipeIconPrefabName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public MGachaRenditionSwipeIcon isSsr(Integer isSsr) {
        this.isSsr = isSsr;
        return this;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getIsROrLess() {
        return isROrLess;
    }

    public MGachaRenditionSwipeIcon isROrLess(Integer isROrLess) {
        this.isROrLess = isROrLess;
        return this;
    }

    public void setIsROrLess(Integer isROrLess) {
        this.isROrLess = isROrLess;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionSwipeIcon weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSwipeIconPrefabName() {
        return swipeIconPrefabName;
    }

    public MGachaRenditionSwipeIcon swipeIconPrefabName(String swipeIconPrefabName) {
        this.swipeIconPrefabName = swipeIconPrefabName;
        return this;
    }

    public void setSwipeIconPrefabName(String swipeIconPrefabName) {
        this.swipeIconPrefabName = swipeIconPrefabName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionSwipeIcon)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionSwipeIcon) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionSwipeIcon{" +
            "id=" + getId() +
            ", isSsr=" + getIsSsr() +
            ", isROrLess=" + getIsROrLess() +
            ", weight=" + getWeight() +
            ", swipeIconPrefabName='" + getSwipeIconPrefabName() + "'" +
            "}";
    }
}
