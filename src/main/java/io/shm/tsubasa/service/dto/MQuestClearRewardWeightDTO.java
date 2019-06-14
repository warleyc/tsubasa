package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MQuestClearRewardWeight} entity.
 */
public class MQuestClearRewardWeightDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer reward1;

    @NotNull
    private Integer reward2;

    @NotNull
    private Integer reward3;

    @NotNull
    private Integer reward4;

    @NotNull
    private Integer reward5;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReward1() {
        return reward1;
    }

    public void setReward1(Integer reward1) {
        this.reward1 = reward1;
    }

    public Integer getReward2() {
        return reward2;
    }

    public void setReward2(Integer reward2) {
        this.reward2 = reward2;
    }

    public Integer getReward3() {
        return reward3;
    }

    public void setReward3(Integer reward3) {
        this.reward3 = reward3;
    }

    public Integer getReward4() {
        return reward4;
    }

    public void setReward4(Integer reward4) {
        this.reward4 = reward4;
    }

    public Integer getReward5() {
        return reward5;
    }

    public void setReward5(Integer reward5) {
        this.reward5 = reward5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO = (MQuestClearRewardWeightDTO) o;
        if (mQuestClearRewardWeightDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mQuestClearRewardWeightDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MQuestClearRewardWeightDTO{" +
            "id=" + getId() +
            ", reward1=" + getReward1() +
            ", reward2=" + getReward2() +
            ", reward3=" + getReward3() +
            ", reward4=" + getReward4() +
            ", reward5=" + getReward5() +
            "}";
    }
}
