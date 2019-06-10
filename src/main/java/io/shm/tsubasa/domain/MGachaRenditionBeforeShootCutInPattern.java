package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionBeforeShootCutInPattern.
 */
@Entity
@Table(name = "m_gacha_rendition_before_shoot_cut_in_pattern")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionBeforeShootCutInPattern implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rendition_id", nullable = false)
    private Integer renditionId;

    @NotNull
    @Column(name = "is_many_ssr", nullable = false)
    private Integer isManySsr;

    @NotNull
    @Column(name = "is_ssr", nullable = false)
    private Integer isSsr;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @NotNull
    @Column(name = "pattern", nullable = false)
    private Integer pattern;

    
    @Lob
    @Column(name = "normal_prefab_name", nullable = false)
    private String normalPrefabName;

    
    @Lob
    @Column(name = "flash_back_prefab_name_1", nullable = false)
    private String flashBackPrefabName1;

    
    @Lob
    @Column(name = "flash_back_prefab_name_2", nullable = false)
    private String flashBackPrefabName2;

    
    @Lob
    @Column(name = "flash_back_prefab_name_3", nullable = false)
    private String flashBackPrefabName3;

    
    @Lob
    @Column(name = "flash_back_prefab_name_4", nullable = false)
    private String flashBackPrefabName4;

    
    @Lob
    @Column(name = "voice_prefix", nullable = false)
    private String voicePrefix;

    
    @Lob
    @Column(name = "se_normal", nullable = false)
    private String seNormal;

    
    @Lob
    @Column(name = "se_flash_back", nullable = false)
    private String seFlashBack;

    @NotNull
    @Column(name = "except_kicker_id", nullable = false)
    private Integer exceptKickerId;

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

    public MGachaRenditionBeforeShootCutInPattern renditionId(Integer renditionId) {
        this.renditionId = renditionId;
        return this;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public Integer getIsManySsr() {
        return isManySsr;
    }

    public MGachaRenditionBeforeShootCutInPattern isManySsr(Integer isManySsr) {
        this.isManySsr = isManySsr;
        return this;
    }

    public void setIsManySsr(Integer isManySsr) {
        this.isManySsr = isManySsr;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public MGachaRenditionBeforeShootCutInPattern isSsr(Integer isSsr) {
        this.isSsr = isSsr;
        return this;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionBeforeShootCutInPattern weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPattern() {
        return pattern;
    }

    public MGachaRenditionBeforeShootCutInPattern pattern(Integer pattern) {
        this.pattern = pattern;
        return this;
    }

    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }

    public String getNormalPrefabName() {
        return normalPrefabName;
    }

    public MGachaRenditionBeforeShootCutInPattern normalPrefabName(String normalPrefabName) {
        this.normalPrefabName = normalPrefabName;
        return this;
    }

    public void setNormalPrefabName(String normalPrefabName) {
        this.normalPrefabName = normalPrefabName;
    }

    public String getFlashBackPrefabName1() {
        return flashBackPrefabName1;
    }

    public MGachaRenditionBeforeShootCutInPattern flashBackPrefabName1(String flashBackPrefabName1) {
        this.flashBackPrefabName1 = flashBackPrefabName1;
        return this;
    }

    public void setFlashBackPrefabName1(String flashBackPrefabName1) {
        this.flashBackPrefabName1 = flashBackPrefabName1;
    }

    public String getFlashBackPrefabName2() {
        return flashBackPrefabName2;
    }

    public MGachaRenditionBeforeShootCutInPattern flashBackPrefabName2(String flashBackPrefabName2) {
        this.flashBackPrefabName2 = flashBackPrefabName2;
        return this;
    }

    public void setFlashBackPrefabName2(String flashBackPrefabName2) {
        this.flashBackPrefabName2 = flashBackPrefabName2;
    }

    public String getFlashBackPrefabName3() {
        return flashBackPrefabName3;
    }

    public MGachaRenditionBeforeShootCutInPattern flashBackPrefabName3(String flashBackPrefabName3) {
        this.flashBackPrefabName3 = flashBackPrefabName3;
        return this;
    }

    public void setFlashBackPrefabName3(String flashBackPrefabName3) {
        this.flashBackPrefabName3 = flashBackPrefabName3;
    }

    public String getFlashBackPrefabName4() {
        return flashBackPrefabName4;
    }

    public MGachaRenditionBeforeShootCutInPattern flashBackPrefabName4(String flashBackPrefabName4) {
        this.flashBackPrefabName4 = flashBackPrefabName4;
        return this;
    }

    public void setFlashBackPrefabName4(String flashBackPrefabName4) {
        this.flashBackPrefabName4 = flashBackPrefabName4;
    }

    public String getVoicePrefix() {
        return voicePrefix;
    }

    public MGachaRenditionBeforeShootCutInPattern voicePrefix(String voicePrefix) {
        this.voicePrefix = voicePrefix;
        return this;
    }

    public void setVoicePrefix(String voicePrefix) {
        this.voicePrefix = voicePrefix;
    }

    public String getSeNormal() {
        return seNormal;
    }

    public MGachaRenditionBeforeShootCutInPattern seNormal(String seNormal) {
        this.seNormal = seNormal;
        return this;
    }

    public void setSeNormal(String seNormal) {
        this.seNormal = seNormal;
    }

    public String getSeFlashBack() {
        return seFlashBack;
    }

    public MGachaRenditionBeforeShootCutInPattern seFlashBack(String seFlashBack) {
        this.seFlashBack = seFlashBack;
        return this;
    }

    public void setSeFlashBack(String seFlashBack) {
        this.seFlashBack = seFlashBack;
    }

    public Integer getExceptKickerId() {
        return exceptKickerId;
    }

    public MGachaRenditionBeforeShootCutInPattern exceptKickerId(Integer exceptKickerId) {
        this.exceptKickerId = exceptKickerId;
        return this;
    }

    public void setExceptKickerId(Integer exceptKickerId) {
        this.exceptKickerId = exceptKickerId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionBeforeShootCutInPattern)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionBeforeShootCutInPattern) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionBeforeShootCutInPattern{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", isManySsr=" + getIsManySsr() +
            ", isSsr=" + getIsSsr() +
            ", weight=" + getWeight() +
            ", pattern=" + getPattern() +
            ", normalPrefabName='" + getNormalPrefabName() + "'" +
            ", flashBackPrefabName1='" + getFlashBackPrefabName1() + "'" +
            ", flashBackPrefabName2='" + getFlashBackPrefabName2() + "'" +
            ", flashBackPrefabName3='" + getFlashBackPrefabName3() + "'" +
            ", flashBackPrefabName4='" + getFlashBackPrefabName4() + "'" +
            ", voicePrefix='" + getVoicePrefix() + "'" +
            ", seNormal='" + getSeNormal() + "'" +
            ", seFlashBack='" + getSeFlashBack() + "'" +
            ", exceptKickerId=" + getExceptKickerId() +
            "}";
    }
}
