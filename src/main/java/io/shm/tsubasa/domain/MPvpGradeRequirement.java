package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MPvpGradeRequirement.
 */
@Entity
@Table(name = "m_pvp_grade_requirement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPvpGradeRequirement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "up_description", nullable = false)
    private String upDescription;

    
    @Lob
    @Column(name = "down_description", nullable = false)
    private String downDescription;

    @NotNull
    @Column(name = "target_type", nullable = false)
    private Integer targetType;

    @NotNull
    @Column(name = "target_wave", nullable = false)
    private Integer targetWave;

    @NotNull
    @Column(name = "target_lower_grade", nullable = false)
    private Integer targetLowerGrade;

    @NotNull
    @Column(name = "target_point", nullable = false)
    private Integer targetPoint;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpDescription() {
        return upDescription;
    }

    public MPvpGradeRequirement upDescription(String upDescription) {
        this.upDescription = upDescription;
        return this;
    }

    public void setUpDescription(String upDescription) {
        this.upDescription = upDescription;
    }

    public String getDownDescription() {
        return downDescription;
    }

    public MPvpGradeRequirement downDescription(String downDescription) {
        this.downDescription = downDescription;
        return this;
    }

    public void setDownDescription(String downDescription) {
        this.downDescription = downDescription;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public MPvpGradeRequirement targetType(Integer targetType) {
        this.targetType = targetType;
        return this;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getTargetWave() {
        return targetWave;
    }

    public MPvpGradeRequirement targetWave(Integer targetWave) {
        this.targetWave = targetWave;
        return this;
    }

    public void setTargetWave(Integer targetWave) {
        this.targetWave = targetWave;
    }

    public Integer getTargetLowerGrade() {
        return targetLowerGrade;
    }

    public MPvpGradeRequirement targetLowerGrade(Integer targetLowerGrade) {
        this.targetLowerGrade = targetLowerGrade;
        return this;
    }

    public void setTargetLowerGrade(Integer targetLowerGrade) {
        this.targetLowerGrade = targetLowerGrade;
    }

    public Integer getTargetPoint() {
        return targetPoint;
    }

    public MPvpGradeRequirement targetPoint(Integer targetPoint) {
        this.targetPoint = targetPoint;
        return this;
    }

    public void setTargetPoint(Integer targetPoint) {
        this.targetPoint = targetPoint;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPvpGradeRequirement)) {
            return false;
        }
        return id != null && id.equals(((MPvpGradeRequirement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPvpGradeRequirement{" +
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
