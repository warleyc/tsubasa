package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionTradeSign.
 */
@Entity
@Table(name = "m_gacha_rendition_trade_sign")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionTradeSign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rendition_id", nullable = false)
    private Integer renditionId;

    @NotNull
    @Column(name = "is_ssr", nullable = false)
    private Integer isSsr;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    
    @Lob
    @Column(name = "sign_texture_name", nullable = false)
    private String signTextureName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRenditionId() {
        return renditionId;
    }

    public MGachaRenditionTradeSign renditionId(Integer renditionId) {
        this.renditionId = renditionId;
        return this;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public MGachaRenditionTradeSign isSsr(Integer isSsr) {
        this.isSsr = isSsr;
        return this;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionTradeSign weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSignTextureName() {
        return signTextureName;
    }

    public MGachaRenditionTradeSign signTextureName(String signTextureName) {
        this.signTextureName = signTextureName;
        return this;
    }

    public void setSignTextureName(String signTextureName) {
        this.signTextureName = signTextureName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionTradeSign)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionTradeSign) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionTradeSign{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", isSsr=" + getIsSsr() +
            ", weight=" + getWeight() +
            ", signTextureName='" + getSignTextureName() + "'" +
            "}";
    }
}
