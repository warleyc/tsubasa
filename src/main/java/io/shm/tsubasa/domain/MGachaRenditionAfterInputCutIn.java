package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionAfterInputCutIn.
 */
@Entity
@Table(name = "m_gacha_rendition_after_input_cut_in")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionAfterInputCutIn implements Serializable {

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
    @Column(name = "cut_in_bg_prefab_name", nullable = false)
    private String cutInBgPrefabName;

    
    @Lob
    @Column(name = "se_start_cut_in", nullable = false)
    private String seStartCutIn;

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

    public MGachaRenditionAfterInputCutIn renditionId(Integer renditionId) {
        this.renditionId = renditionId;
        return this;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public MGachaRenditionAfterInputCutIn isSsr(Integer isSsr) {
        this.isSsr = isSsr;
        return this;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionAfterInputCutIn weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCutInBgPrefabName() {
        return cutInBgPrefabName;
    }

    public MGachaRenditionAfterInputCutIn cutInBgPrefabName(String cutInBgPrefabName) {
        this.cutInBgPrefabName = cutInBgPrefabName;
        return this;
    }

    public void setCutInBgPrefabName(String cutInBgPrefabName) {
        this.cutInBgPrefabName = cutInBgPrefabName;
    }

    public String getSeStartCutIn() {
        return seStartCutIn;
    }

    public MGachaRenditionAfterInputCutIn seStartCutIn(String seStartCutIn) {
        this.seStartCutIn = seStartCutIn;
        return this;
    }

    public void setSeStartCutIn(String seStartCutIn) {
        this.seStartCutIn = seStartCutIn;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionAfterInputCutIn)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionAfterInputCutIn) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionAfterInputCutIn{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", isSsr=" + getIsSsr() +
            ", weight=" + getWeight() +
            ", cutInBgPrefabName='" + getCutInBgPrefabName() + "'" +
            ", seStartCutIn='" + getSeStartCutIn() + "'" +
            "}";
    }
}
