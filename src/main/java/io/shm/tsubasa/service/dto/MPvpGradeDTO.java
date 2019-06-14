package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MPvpGrade} entity.
 */
public class MPvpGradeDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer waveId;

    @NotNull
    private Integer grade;

    @NotNull
    private Integer isHigher;

    @NotNull
    private Integer isLower;

    
    @Lob
    private String gradeName;

    @NotNull
    private Integer look;

    private Integer upRequirementId;

    private Integer downRequirementId;

    @NotNull
    private Integer winPoint;

    @NotNull
    private Integer losePoint;

    
    @Lob
    private String gradeUpSerif;

    
    @Lob
    private String gradeDownSerif;

    
    @Lob
    private String gradeUpCharaAssetName;

    
    @Lob
    private String gradeDownCharaAssetName;

    
    @Lob
    private String gradeUpVoiceCharaId;

    
    @Lob
    private String gradeDownVoiceCharaId;

    @NotNull
    private Integer totalParameterRangeUpper;

    @NotNull
    private Integer totalParameterRangeLower;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWaveId() {
        return waveId;
    }

    public void setWaveId(Integer waveId) {
        this.waveId = waveId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getIsHigher() {
        return isHigher;
    }

    public void setIsHigher(Integer isHigher) {
        this.isHigher = isHigher;
    }

    public Integer getIsLower() {
        return isLower;
    }

    public void setIsLower(Integer isLower) {
        this.isLower = isLower;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getLook() {
        return look;
    }

    public void setLook(Integer look) {
        this.look = look;
    }

    public Integer getUpRequirementId() {
        return upRequirementId;
    }

    public void setUpRequirementId(Integer upRequirementId) {
        this.upRequirementId = upRequirementId;
    }

    public Integer getDownRequirementId() {
        return downRequirementId;
    }

    public void setDownRequirementId(Integer downRequirementId) {
        this.downRequirementId = downRequirementId;
    }

    public Integer getWinPoint() {
        return winPoint;
    }

    public void setWinPoint(Integer winPoint) {
        this.winPoint = winPoint;
    }

    public Integer getLosePoint() {
        return losePoint;
    }

    public void setLosePoint(Integer losePoint) {
        this.losePoint = losePoint;
    }

    public String getGradeUpSerif() {
        return gradeUpSerif;
    }

    public void setGradeUpSerif(String gradeUpSerif) {
        this.gradeUpSerif = gradeUpSerif;
    }

    public String getGradeDownSerif() {
        return gradeDownSerif;
    }

    public void setGradeDownSerif(String gradeDownSerif) {
        this.gradeDownSerif = gradeDownSerif;
    }

    public String getGradeUpCharaAssetName() {
        return gradeUpCharaAssetName;
    }

    public void setGradeUpCharaAssetName(String gradeUpCharaAssetName) {
        this.gradeUpCharaAssetName = gradeUpCharaAssetName;
    }

    public String getGradeDownCharaAssetName() {
        return gradeDownCharaAssetName;
    }

    public void setGradeDownCharaAssetName(String gradeDownCharaAssetName) {
        this.gradeDownCharaAssetName = gradeDownCharaAssetName;
    }

    public String getGradeUpVoiceCharaId() {
        return gradeUpVoiceCharaId;
    }

    public void setGradeUpVoiceCharaId(String gradeUpVoiceCharaId) {
        this.gradeUpVoiceCharaId = gradeUpVoiceCharaId;
    }

    public String getGradeDownVoiceCharaId() {
        return gradeDownVoiceCharaId;
    }

    public void setGradeDownVoiceCharaId(String gradeDownVoiceCharaId) {
        this.gradeDownVoiceCharaId = gradeDownVoiceCharaId;
    }

    public Integer getTotalParameterRangeUpper() {
        return totalParameterRangeUpper;
    }

    public void setTotalParameterRangeUpper(Integer totalParameterRangeUpper) {
        this.totalParameterRangeUpper = totalParameterRangeUpper;
    }

    public Integer getTotalParameterRangeLower() {
        return totalParameterRangeLower;
    }

    public void setTotalParameterRangeLower(Integer totalParameterRangeLower) {
        this.totalParameterRangeLower = totalParameterRangeLower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MPvpGradeDTO mPvpGradeDTO = (MPvpGradeDTO) o;
        if (mPvpGradeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mPvpGradeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MPvpGradeDTO{" +
            "id=" + getId() +
            ", waveId=" + getWaveId() +
            ", grade=" + getGrade() +
            ", isHigher=" + getIsHigher() +
            ", isLower=" + getIsLower() +
            ", gradeName='" + getGradeName() + "'" +
            ", look=" + getLook() +
            ", upRequirementId=" + getUpRequirementId() +
            ", downRequirementId=" + getDownRequirementId() +
            ", winPoint=" + getWinPoint() +
            ", losePoint=" + getLosePoint() +
            ", gradeUpSerif='" + getGradeUpSerif() + "'" +
            ", gradeDownSerif='" + getGradeDownSerif() + "'" +
            ", gradeUpCharaAssetName='" + getGradeUpCharaAssetName() + "'" +
            ", gradeDownCharaAssetName='" + getGradeDownCharaAssetName() + "'" +
            ", gradeUpVoiceCharaId='" + getGradeUpVoiceCharaId() + "'" +
            ", gradeDownVoiceCharaId='" + getGradeDownVoiceCharaId() + "'" +
            ", totalParameterRangeUpper=" + getTotalParameterRangeUpper() +
            ", totalParameterRangeLower=" + getTotalParameterRangeLower() +
            "}";
    }
}
