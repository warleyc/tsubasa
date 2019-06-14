package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MPowerupActionSkillCost} entity.
 */
public class MPowerupActionSkillCostDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer actionRarity;

    @NotNull
    private Integer actionLevel;

    @NotNull
    private Integer cost;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActionRarity() {
        return actionRarity;
    }

    public void setActionRarity(Integer actionRarity) {
        this.actionRarity = actionRarity;
    }

    public Integer getActionLevel() {
        return actionLevel;
    }

    public void setActionLevel(Integer actionLevel) {
        this.actionLevel = actionLevel;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO = (MPowerupActionSkillCostDTO) o;
        if (mPowerupActionSkillCostDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mPowerupActionSkillCostDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MPowerupActionSkillCostDTO{" +
            "id=" + getId() +
            ", actionRarity=" + getActionRarity() +
            ", actionLevel=" + getActionLevel() +
            ", cost=" + getCost() +
            "}";
    }
}
