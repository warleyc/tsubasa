package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionExtraCutin.
 */
@Entity
@Table(name = "m_gacha_rendition_extra_cutin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionExtraCutin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rendition_id", nullable = false)
    private Integer renditionId;

    
    @Lob
    @Column(name = "main_prefab_name", nullable = false)
    private String mainPrefabName;

    
    @Lob
    @Column(name = "voice_start_cut_in", nullable = false)
    private String voiceStartCutIn;

    
    @Lob
    @Column(name = "serif", nullable = false)
    private String serif;

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

    public MGachaRenditionExtraCutin renditionId(Integer renditionId) {
        this.renditionId = renditionId;
        return this;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public String getMainPrefabName() {
        return mainPrefabName;
    }

    public MGachaRenditionExtraCutin mainPrefabName(String mainPrefabName) {
        this.mainPrefabName = mainPrefabName;
        return this;
    }

    public void setMainPrefabName(String mainPrefabName) {
        this.mainPrefabName = mainPrefabName;
    }

    public String getVoiceStartCutIn() {
        return voiceStartCutIn;
    }

    public MGachaRenditionExtraCutin voiceStartCutIn(String voiceStartCutIn) {
        this.voiceStartCutIn = voiceStartCutIn;
        return this;
    }

    public void setVoiceStartCutIn(String voiceStartCutIn) {
        this.voiceStartCutIn = voiceStartCutIn;
    }

    public String getSerif() {
        return serif;
    }

    public MGachaRenditionExtraCutin serif(String serif) {
        this.serif = serif;
        return this;
    }

    public void setSerif(String serif) {
        this.serif = serif;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionExtraCutin)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionExtraCutin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionExtraCutin{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", mainPrefabName='" + getMainPrefabName() + "'" +
            ", voiceStartCutIn='" + getVoiceStartCutIn() + "'" +
            ", serif='" + getSerif() + "'" +
            "}";
    }
}
