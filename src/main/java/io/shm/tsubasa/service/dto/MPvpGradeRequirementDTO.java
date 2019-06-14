package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MPvpGradeRequirement} entity.
 */
public class MPvpGradeRequirementDTO implements Serializable {

    private Long id;

    
    @Lob
    private String upDescription;

    
    @Lob
    private String downDescription;

    @NotNull
    private Integer targetType;

    @NotNull
    private Integer targetWave;

    @NotNull
    private Integer targetLowerGrade;

    @NotNull
    private Integer targetPoint;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpDescription() {
        return upDescription;
    }

    public void setUpDescription(String upDescription) {
        this.upDescription = upDescription;
    }

    public String getDownDescription() {
        return downDescription;
    }

    public void setDownDescription(String downDescription) {
        this.downDescription = downDescription;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getTargetWave() {
        return targetWave;
    }

    public void setTargetWave(Integer targetWave) {
        this.targetWave = targetWave;
    }

    public Integer getTargetLowerGrade() {
        return targetLowerGrade;
    }

    public void setTargetLowerGrade(Integer targetLowerGrade) {
        this.targetLowerGrade = targetLowerGrade;
    }

    public Integer getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(Integer targetPoint) {
        this.targetPoint = targetPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MPvpGradeRequirementDTO mPvpGradeRequirementDTO = (MPvpGradeRequirementDTO) o;
        if (mPvpGradeRequirementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mPvpGradeRequirementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MPvpGradeRequirementDTO{" +
            "id=" + getId() +
            ", upDescription='" + getUpDescription() + "'" +
            ", downDescription='" + getDownDescription() + "'" +
            ", targetType=" + getTargetType() +
            ", targetWave=" + getTargetWave() +
            ", targetLowerGrade=" + getTargetLowerGrade() +
            ", targetPoint=" + getTargetPoint() +
            "}";
    }
}
