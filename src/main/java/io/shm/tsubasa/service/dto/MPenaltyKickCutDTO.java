package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MPenaltyKickCut} entity.
 */
public class MPenaltyKickCutDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer kickerCourse;

    @NotNull
    private Integer keeperCourse;

    @NotNull
    private Integer keeperCommand;

    @NotNull
    private Integer resultType;

    
    @Lob
    private String offenseSequenceText;

    
    @Lob
    private String defenseSequenceText;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKickerCourse() {
        return kickerCourse;
    }

    public void setKickerCourse(Integer kickerCourse) {
        this.kickerCourse = kickerCourse;
    }

    public Integer getKeeperCourse() {
        return keeperCourse;
    }

    public void setKeeperCourse(Integer keeperCourse) {
        this.keeperCourse = keeperCourse;
    }

    public Integer getKeeperCommand() {
        return keeperCommand;
    }

    public void setKeeperCommand(Integer keeperCommand) {
        this.keeperCommand = keeperCommand;
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getOffenseSequenceText() {
        return offenseSequenceText;
    }

    public void setOffenseSequenceText(String offenseSequenceText) {
        this.offenseSequenceText = offenseSequenceText;
    }

    public String getDefenseSequenceText() {
        return defenseSequenceText;
    }

    public void setDefenseSequenceText(String defenseSequenceText) {
        this.defenseSequenceText = defenseSequenceText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MPenaltyKickCutDTO mPenaltyKickCutDTO = (MPenaltyKickCutDTO) o;
        if (mPenaltyKickCutDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mPenaltyKickCutDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MPenaltyKickCutDTO{" +
            "id=" + getId() +
            ", kickerCourse=" + getKickerCourse() +
            ", keeperCourse=" + getKeeperCourse() +
            ", keeperCommand=" + getKeeperCommand() +
            ", resultType=" + getResultType() +
            ", offenseSequenceText='" + getOffenseSequenceText() + "'" +
            ", defenseSequenceText='" + getDefenseSequenceText() + "'" +
            "}";
    }
}
