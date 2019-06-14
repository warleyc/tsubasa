package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MQuestClearRewardWeight.
 */
@Entity
@Table(name = "m_quest_clear_reward_weight")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MQuestClearRewardWeight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reward_1", nullable = false)
    private Integer reward1;

    @NotNull
    @Column(name = "reward_2", nullable = false)
    private Integer reward2;

    @NotNull
    @Column(name = "reward_3", nullable = false)
    private Integer reward3;

    @NotNull
    @Column(name = "reward_4", nullable = false)
    private Integer reward4;

    @NotNull
    @Column(name = "reward_5", nullable = false)
    private Integer reward5;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReward1() {
        return reward1;
    }

    public MQuestClearRewardWeight reward1(Integer reward1) {
        this.reward1 = reward1;
        return this;
    }

    public void setReward1(Integer reward1) {
        this.reward1 = reward1;
    }

    public Integer getReward2() {
        return reward2;
    }

    public MQuestClearRewardWeight reward2(Integer reward2) {
        this.reward2 = reward2;
        return this;
    }

    public void setReward2(Integer reward2) {
        this.reward2 = reward2;
    }

    public Integer getReward3() {
        return reward3;
    }

    public MQuestClearRewardWeight reward3(Integer reward3) {
        this.reward3 = reward3;
        return this;
    }

    public void setReward3(Integer reward3) {
        this.reward3 = reward3;
    }

    public Integer getReward4() {
        return reward4;
    }

    public MQuestClearRewardWeight reward4(Integer reward4) {
        this.reward4 = reward4;
        return this;
    }

    public void setReward4(Integer reward4) {
        this.reward4 = reward4;
    }

    public Integer getReward5() {
        return reward5;
    }

    public MQuestClearRewardWeight reward5(Integer reward5) {
        this.reward5 = reward5;
        return this;
    }

    public void setReward5(Integer reward5) {
        this.reward5 = reward5;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MQuestClearRewardWeight)) {
            return false;
        }
        return id != null && id.equals(((MQuestClearRewardWeight) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MQuestClearRewardWeight{" +
            "id=" + getId() +
            ", reward1=" + getReward1() +
            ", reward2=" + getReward2() +
            ", reward3=" + getReward3() +
            ", reward4=" + getReward4() +
            ", reward5=" + getReward5() +
            "}";
    }
}
