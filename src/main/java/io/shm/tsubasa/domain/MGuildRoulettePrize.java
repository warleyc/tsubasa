package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGuildRoulettePrize.
 */
@Entity
@Table(name = "m_guild_roulette_prize")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGuildRoulettePrize implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_rank", nullable = false)
    private Integer rank;

    @NotNull
    @Column(name = "content_type", nullable = false)
    private Integer contentType;

    @Column(name = "content_id")
    private Integer contentId;

    @NotNull
    @Column(name = "content_amount", nullable = false)
    private Integer contentAmount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public MGuildRoulettePrize rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getContentType() {
        return contentType;
    }

    public MGuildRoulettePrize contentType(Integer contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public MGuildRoulettePrize contentId(Integer contentId) {
        this.contentId = contentId;
        return this;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentAmount() {
        return contentAmount;
    }

    public MGuildRoulettePrize contentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
        return this;
    }

    public void setContentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGuildRoulettePrize)) {
            return false;
        }
        return id != null && id.equals(((MGuildRoulettePrize) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGuildRoulettePrize{" +
            "id=" + getId() +
            ", rank=" + getRank() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            "}";
    }
}
