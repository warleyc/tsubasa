package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInPattern} entity.
 */
public class MGachaRenditionBeforeShootCutInPatternDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer renditionId;

    @NotNull
    private Integer isManySsr;

    @NotNull
    private Integer isSsr;

    @NotNull
    private Integer weight;

    @NotNull
    private Integer pattern;

    
    @Lob
    private String normalPrefabName;

    
    @Lob
    private String flashBackPrefabName1;

    
    @Lob
    private String flashBackPrefabName2;

    
    @Lob
    private String flashBackPrefabName3;

    
    @Lob
    private String flashBackPrefabName4;

    
    @Lob
    private String voicePrefix;

    
    @Lob
    private String seNormal;

    
    @Lob
    private String seFlashBack;

    @NotNull
    private Integer exceptKickerId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRenditionId() {
        return renditionId;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public Integer getIsManySsr() {
        return isManySsr;
    }

    public void setIsManySsr(Integer isManySsr) {
        this.isManySsr = isManySsr;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPattern() {
        return pattern;
    }

    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }

    public String getNormalPrefabName() {
        return normalPrefabName;
    }

    public void setNormalPrefabName(String normalPrefabName) {
        this.normalPrefabName = normalPrefabName;
    }

    public String getFlashBackPrefabName1() {
        return flashBackPrefabName1;
    }

    public void setFlashBackPrefabName1(String flashBackPrefabName1) {
        this.flashBackPrefabName1 = flashBackPrefabName1;
    }

    public String getFlashBackPrefabName2() {
        return flashBackPrefabName2;
    }

    public void setFlashBackPrefabName2(String flashBackPrefabName2) {
        this.flashBackPrefabName2 = flashBackPrefabName2;
    }

    public String getFlashBackPrefabName3() {
        return flashBackPrefabName3;
    }

    public void setFlashBackPrefabName3(String flashBackPrefabName3) {
        this.flashBackPrefabName3 = flashBackPrefabName3;
    }

    public String getFlashBackPrefabName4() {
        return flashBackPrefabName4;
    }

    public void setFlashBackPrefabName4(String flashBackPrefabName4) {
        this.flashBackPrefabName4 = flashBackPrefabName4;
    }

    public String getVoicePrefix() {
        return voicePrefix;
    }

    public void setVoicePrefix(String voicePrefix) {
        this.voicePrefix = voicePrefix;
    }

    public String getSeNormal() {
        return seNormal;
    }

    public void setSeNormal(String seNormal) {
        this.seNormal = seNormal;
    }

    public String getSeFlashBack() {
        return seFlashBack;
    }

    public void setSeFlashBack(String seFlashBack) {
        this.seFlashBack = seFlashBack;
    }

    public Integer getExceptKickerId() {
        return exceptKickerId;
    }

    public void setExceptKickerId(Integer exceptKickerId) {
        this.exceptKickerId = exceptKickerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = (MGachaRenditionBeforeShootCutInPatternDTO) o;
        if (mGachaRenditionBeforeShootCutInPatternDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionBeforeShootCutInPatternDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionBeforeShootCutInPatternDTO{" +
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
