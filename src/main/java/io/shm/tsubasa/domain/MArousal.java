package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MArousal.
 */
@Entity
@Table(name = "m_arousal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MArousal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "before_id", nullable = false)
    private Integer beforeId;

    @NotNull
    @Column(name = "after_id", nullable = false)
    private Integer afterId;

    @NotNull
    @Column(name = "jhi_cost", nullable = false)
    private Integer cost;

    @NotNull
    @Column(name = "material_group_id", nullable = false)
    private Integer materialGroupId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mArousals")
    private MPlayableCard mplayablecard;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBeforeId() {
        return beforeId;
    }

    public MArousal beforeId(Integer beforeId) {
        this.beforeId = beforeId;
        return this;
    }

    public void setBeforeId(Integer beforeId) {
        this.beforeId = beforeId;
    }

    public Integer getAfterId() {
        return afterId;
    }

    public MArousal afterId(Integer afterId) {
        this.afterId = afterId;
        return this;
    }

    public void setAfterId(Integer afterId) {
        this.afterId = afterId;
    }

    public Integer getCost() {
        return cost;
    }

    public MArousal cost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getMaterialGroupId() {
        return materialGroupId;
    }

    public MArousal materialGroupId(Integer materialGroupId) {
        this.materialGroupId = materialGroupId;
        return this;
    }

    public void setMaterialGroupId(Integer materialGroupId) {
        this.materialGroupId = materialGroupId;
    }

    public MPlayableCard getMplayablecard() {
        return mplayablecard;
    }

    public MArousal mplayablecard(MPlayableCard mPlayableCard) {
        this.mplayablecard = mPlayableCard;
        return this;
    }

    public void setMplayablecard(MPlayableCard mPlayableCard) {
        this.mplayablecard = mPlayableCard;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MArousal)) {
            return false;
        }
        return id != null && id.equals(((MArousal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MArousal{" +
            "id=" + getId() +
            ", beforeId=" + getBeforeId() +
            ", afterId=" + getAfterId() +
            ", cost=" + getCost() +
            ", materialGroupId=" + getMaterialGroupId() +
            "}";
    }
}
