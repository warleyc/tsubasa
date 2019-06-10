package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionFirstGimmick.
 */
@Entity
@Table(name = "m_gacha_rendition_first_gimmick")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionFirstGimmick implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "is_ssr", nullable = false)
    private Integer isSsr;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @NotNull
    @Column(name = "bird_num", nullable = false)
    private Integer birdNum;

    @NotNull
    @Column(name = "is_ticker_tape", nullable = false)
    private Integer isTickerTape;

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

    public MGachaRenditionFirstGimmick isSsr(Integer isSsr) {
        this.isSsr = isSsr;
        return this;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionFirstGimmick weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBirdNum() {
        return birdNum;
    }

    public MGachaRenditionFirstGimmick birdNum(Integer birdNum) {
        this.birdNum = birdNum;
        return this;
    }

    public void setBirdNum(Integer birdNum) {
        this.birdNum = birdNum;
    }

    public Integer getIsTickerTape() {
        return isTickerTape;
    }

    public MGachaRenditionFirstGimmick isTickerTape(Integer isTickerTape) {
        this.isTickerTape = isTickerTape;
        return this;
    }

    public void setIsTickerTape(Integer isTickerTape) {
        this.isTickerTape = isTickerTape;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionFirstGimmick)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionFirstGimmick) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionFirstGimmick{" +
            "id=" + getId() +
            ", isSsr=" + getIsSsr() +
            ", weight=" + getWeight() +
            ", birdNum=" + getBirdNum() +
            ", isTickerTape=" + getIsTickerTape() +
            "}";
    }
}
