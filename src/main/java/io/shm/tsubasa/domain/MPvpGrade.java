package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MPvpGrade.
 */
@Entity
@Table(name = "m_pvp_grade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPvpGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "wave_id", nullable = false)
    private Integer waveId;

    @NotNull
    @Column(name = "grade", nullable = false)
    private Integer grade;

    @NotNull
    @Column(name = "is_higher", nullable = false)
    private Integer isHigher;

    @NotNull
    @Column(name = "is_lower", nullable = false)
    private Integer isLower;

    
    @Lob
    @Column(name = "grade_name", nullable = false)
    private String gradeName;

    @NotNull
    @Column(name = "look", nullable = false)
    private Integer look;

    @Column(name = "up_requirement_id")
    private Integer upRequirementId;

    @Column(name = "down_requirement_id")
    private Integer downRequirementId;

    @NotNull
    @Column(name = "win_point", nullable = false)
    private Integer winPoint;

    @NotNull
    @Column(name = "lose_point", nullable = false)
    private Integer losePoint;

    
    @Lob
    @Column(name = "grade_up_serif", nullable = false)
    private String gradeUpSerif;

    
    @Lob
    @Column(name = "grade_down_serif", nullable = false)
    private String gradeDownSerif;

    
    @Lob
    @Column(name = "grade_up_chara_asset_name", nullable = false)
    private String gradeUpCharaAssetName;

    
    @Lob
    @Column(name = "grade_down_chara_asset_name", nullable = false)
    private String gradeDownCharaAssetName;

    
    @Lob
    @Column(name = "grade_up_voice_chara_id", nullable = false)
    private String gradeUpVoiceCharaId;

    
    @Lob
    @Column(name = "grade_down_voice_chara_id", nullable = false)
    private String gradeDownVoiceCharaId;

    @NotNull
    @Column(name = "total_parameter_range_upper", nullable = false)
    private Integer totalParameterRangeUpper;

    @NotNull
    @Column(name = "total_parameter_range_lower", nullable = false)
    private Integer totalParameterRangeLower;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWaveId() {
        return waveId;
    }

    public MPvpGrade waveId(Integer waveId) {
        this.waveId = waveId;
        return this;
    }

    public void setWaveId(Integer waveId) {
        this.waveId = waveId;
    }

    public Integer getGrade() {
        return grade;
    }

    public MPvpGrade grade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getIsHigher() {
        return isHigher;
    }

    public MPvpGrade isHigher(Integer isHigher) {
        this.isHigher = isHigher;
        return this;
    }

    public void setIsHigher(Integer isHigher) {
        this.isHigher = isHigher;
    }

    public Integer getIsLower() {
        return isLower;
    }

    public MPvpGrade isLower(Integer isLower) {
        this.isLower = isLower;
        return this;
    }

    public void setIsLower(Integer isLower) {
        this.isLower = isLower;
    }

    public String getGradeName() {
        return gradeName;
    }

    public MPvpGrade gradeName(String gradeName) {
        this.gradeName = gradeName;
        return this;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getLook() {
        return look;
    }

    public MPvpGrade look(Integer look) {
        this.look = look;
        return this;
    }

    public void setLook(Integer look) {
        this.look = look;
    }

    public Integer getUpRequirementId() {
        return upRequirementId;
    }

    public MPvpGrade upRequirementId(Integer upRequirementId) {
        this.upRequirementId = upRequirementId;
        return this;
    }

    public void setUpRequirementId(Integer upRequirementId) {
        this.upRequirementId = upRequirementId;
    }

    public Integer getDownRequirementId() {
        return downRequirementId;
    }

    public MPvpGrade downRequirementId(Integer downRequirementId) {
        this.downRequirementId = downRequirementId;
        return this;
    }

    public void setDownRequirementId(Integer downRequirementId) {
        this.downRequirementId = downRequirementId;
    }

    public Integer getWinPoint() {
        return winPoint;
    }

    public MPvpGrade winPoint(Integer winPoint) {
        this.winPoint = winPoint;
        return this;
    }

    public void setWinPoint(Integer winPoint) {
        this.winPoint = winPoint;
    }

    public Integer getLosePoint() {
        return losePoint;
    }

    public MPvpGrade losePoint(Integer losePoint) {
        this.losePoint = losePoint;
        return this;
    }

    public void setLosePoint(Integer losePoint) {
        this.losePoint = losePoint;
    }

    public String getGradeUpSerif() {
        return gradeUpSerif;
    }

    public MPvpGrade gradeUpSerif(String gradeUpSerif) {
        this.gradeUpSerif = gradeUpSerif;
        return this;
    }

    public void setGradeUpSerif(String gradeUpSerif) {
        this.gradeUpSerif = gradeUpSerif;
    }

    public String getGradeDownSerif() {
        return gradeDownSerif;
    }

    public MPvpGrade gradeDownSerif(String gradeDownSerif) {
        this.gradeDownSerif = gradeDownSerif;
        return this;
    }

    public void setGradeDownSerif(String gradeDownSerif) {
        this.gradeDownSerif = gradeDownSerif;
    }

    public String getGradeUpCharaAssetName() {
        return gradeUpCharaAssetName;
    }

    public MPvpGrade gradeUpCharaAssetName(String gradeUpCharaAssetName) {
        this.gradeUpCharaAssetName = gradeUpCharaAssetName;
        return this;
    }

    public void setGradeUpCharaAssetName(String gradeUpCharaAssetName) {
        this.gradeUpCharaAssetName = gradeUpCharaAssetName;
    }

    public String getGradeDownCharaAssetName() {
        return gradeDownCharaAssetName;
    }

    public MPvpGrade gradeDownCharaAssetName(String gradeDownCharaAssetName) {
        this.gradeDownCharaAssetName = gradeDownCharaAssetName;
        return this;
    }

    public void setGradeDownCharaAssetName(String gradeDownCharaAssetName) {
        this.gradeDownCharaAssetName = gradeDownCharaAssetName;
    }

    public String getGradeUpVoiceCharaId() {
        return gradeUpVoiceCharaId;
    }

    public MPvpGrade gradeUpVoiceCharaId(String gradeUpVoiceCharaId) {
        this.gradeUpVoiceCharaId = gradeUpVoiceCharaId;
        return this;
    }

    public void setGradeUpVoiceCharaId(String gradeUpVoiceCharaId) {
        this.gradeUpVoiceCharaId = gradeUpVoiceCharaId;
    }

    public String getGradeDownVoiceCharaId() {
        return gradeDownVoiceCharaId;
    }

    public MPvpGrade gradeDownVoiceCharaId(String gradeDownVoiceCharaId) {
        this.gradeDownVoiceCharaId = gradeDownVoiceCharaId;
        return this;
    }

    public void setGradeDownVoiceCharaId(String gradeDownVoiceCharaId) {
        this.gradeDownVoiceCharaId = gradeDownVoiceCharaId;
    }

    public Integer getTotalParameterRangeUpper() {
        return totalParameterRangeUpper;
    }

    public MPvpGrade totalParameterRangeUpper(Integer totalParameterRangeUpper) {
        this.totalParameterRangeUpper = totalParameterRangeUpper;
        return this;
    }

    public void setTotalParameterRangeUpper(Integer totalParameterRangeUpper) {
        this.totalParameterRangeUpper = totalParameterRangeUpper;
    }

    public Integer getTotalParameterRangeLower() {
        return totalParameterRangeLower;
    }

    public MPvpGrade totalParameterRangeLower(Integer totalParameterRangeLower) {
        this.totalParameterRangeLower = totalParameterRangeLower;
        return this;
    }

    public void setTotalParameterRangeLower(Integer totalParameterRangeLower) {
        this.totalParameterRangeLower = totalParameterRangeLower;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPvpGrade)) {
            return false;
        }
        return id != null && id.equals(((MPvpGrade) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPvpGrade{" +
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
