package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionBeforeShootCutInCharacterNum.
 */
@Entity
@Table(name = "m_gacha_rendition_before_shoot_cut_in_character_num")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionBeforeShootCutInCharacterNum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "is_many_ssr", nullable = false)
    private Integer isManySsr;

    @NotNull
    @Column(name = "is_ssr", nullable = false)
    private Integer isSsr;

    @NotNull
    @Column(name = "pattern", nullable = false)
    private Integer pattern;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @NotNull
    @Column(name = "num", nullable = false)
    private Integer num;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsManySsr() {
        return isManySsr;
    }

    public MGachaRenditionBeforeShootCutInCharacterNum isManySsr(Integer isManySsr) {
        this.isManySsr = isManySsr;
        return this;
    }

    public void setIsManySsr(Integer isManySsr) {
        this.isManySsr = isManySsr;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public MGachaRenditionBeforeShootCutInCharacterNum isSsr(Integer isSsr) {
        this.isSsr = isSsr;
        return this;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getPattern() {
        return pattern;
    }

    public MGachaRenditionBeforeShootCutInCharacterNum pattern(Integer pattern) {
        this.pattern = pattern;
        return this;
    }

    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionBeforeShootCutInCharacterNum weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getNum() {
        return num;
    }

    public MGachaRenditionBeforeShootCutInCharacterNum num(Integer num) {
        this.num = num;
        return this;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionBeforeShootCutInCharacterNum)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionBeforeShootCutInCharacterNum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionBeforeShootCutInCharacterNum{" +
            "id=" + getId() +
            ", isManySsr=" + getIsManySsr() +
            ", isSsr=" + getIsSsr() +
            ", pattern=" + getPattern() +
            ", weight=" + getWeight() +
            ", num=" + getNum() +
            "}";
    }
}
