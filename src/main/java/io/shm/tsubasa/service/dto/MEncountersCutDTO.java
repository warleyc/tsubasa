package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MEncountersCut} entity.
 */
public class MEncountersCutDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer condition;

    @NotNull
    private Integer ballFloatCondition;

    @NotNull
    private Integer command1Type;

    @NotNull
    private Integer command1IsSkill;

    @NotNull
    private Integer command2Type;

    @NotNull
    private Integer command2IsSkill;

    @NotNull
    private Integer result;

    @NotNull
    private Integer shootResult;

    @NotNull
    private Integer isThrown;

    
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

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Integer getBallFloatCondition() {
        return ballFloatCondition;
    }

    public void setBallFloatCondition(Integer ballFloatCondition) {
        this.ballFloatCondition = ballFloatCondition;
    }

    public Integer getCommand1Type() {
        return command1Type;
    }

    public void setCommand1Type(Integer command1Type) {
        this.command1Type = command1Type;
    }

    public Integer getCommand1IsSkill() {
        return command1IsSkill;
    }

    public void setCommand1IsSkill(Integer command1IsSkill) {
        this.command1IsSkill = command1IsSkill;
    }

    public Integer getCommand2Type() {
        return command2Type;
    }

    public void setCommand2Type(Integer command2Type) {
        this.command2Type = command2Type;
    }

    public Integer getCommand2IsSkill() {
        return command2IsSkill;
    }

    public void setCommand2IsSkill(Integer command2IsSkill) {
        this.command2IsSkill = command2IsSkill;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getShootResult() {
        return shootResult;
    }

    public void setShootResult(Integer shootResult) {
        this.shootResult = shootResult;
    }

    public Integer getIsThrown() {
        return isThrown;
    }

    public void setIsThrown(Integer isThrown) {
        this.isThrown = isThrown;
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

        MEncountersCutDTO mEncountersCutDTO = (MEncountersCutDTO) o;
        if (mEncountersCutDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mEncountersCutDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MEncountersCutDTO{" +
            "id=" + getId() +
            ", condition=" + getCondition() +
            ", ballFloatCondition=" + getBallFloatCondition() +
            ", command1Type=" + getCommand1Type() +
            ", command1IsSkill=" + getCommand1IsSkill() +
            ", command2Type=" + getCommand2Type() +
            ", command2IsSkill=" + getCommand2IsSkill() +
            ", result=" + getResult() +
            ", shootResult=" + getShootResult() +
            ", isThrown=" + getIsThrown() +
            ", offenseSequenceText='" + getOffenseSequenceText() + "'" +
            ", defenseSequenceText='" + getDefenseSequenceText() + "'" +
            "}";
    }
}
