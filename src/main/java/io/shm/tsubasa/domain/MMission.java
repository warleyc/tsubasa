package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MMission.
 */
@Entity
@Table(name = "m_mission")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "term", nullable = false)
    private Integer term;

    @Column(name = "round_num")
    private Integer roundNum;

    @Lob
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "mission_type", nullable = false)
    private Integer missionType;

    @NotNull
    @Column(name = "absolute", nullable = false)
    private Integer absolute;

    @Column(name = "param_1")
    private Integer param1;

    @Column(name = "param_2")
    private Integer param2;

    @Column(name = "param_3")
    private Integer param3;

    @Column(name = "param_4")
    private Integer param4;

    @Column(name = "param_5")
    private Integer param5;

    @NotNull
    @Column(name = "jhi_trigger", nullable = false)
    private Integer trigger;

    @NotNull
    @Column(name = "trigger_condition", nullable = false)
    private Integer triggerCondition;

    @NotNull
    @Column(name = "reward_id", nullable = false)
    private Integer rewardId;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Integer startAt;

    @Column(name = "end_at")
    private Integer endAt;

    @Column(name = "jhi_link")
    private Integer link;

    @Lob
    @Column(name = "scene_transition_param")
    private String sceneTransitionParam;

    @NotNull
    @Column(name = "pickup", nullable = false)
    private Integer pickup;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mMissions")
    private MMissionReward mmissionreward;

    @OneToMany(mappedBy = "mmission")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MAchievement> mAchievements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTerm() {
        return term;
    }

    public MMission term(Integer term) {
        this.term = term;
        return this;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getRoundNum() {
        return roundNum;
    }

    public MMission roundNum(Integer roundNum) {
        this.roundNum = roundNum;
        return this;
    }

    public void setRoundNum(Integer roundNum) {
        this.roundNum = roundNum;
    }

    public String getTitle() {
        return title;
    }

    public MMission title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public MMission description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMissionType() {
        return missionType;
    }

    public MMission missionType(Integer missionType) {
        this.missionType = missionType;
        return this;
    }

    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    public Integer getAbsolute() {
        return absolute;
    }

    public MMission absolute(Integer absolute) {
        this.absolute = absolute;
        return this;
    }

    public void setAbsolute(Integer absolute) {
        this.absolute = absolute;
    }

    public Integer getParam1() {
        return param1;
    }

    public MMission param1(Integer param1) {
        this.param1 = param1;
        return this;
    }

    public void setParam1(Integer param1) {
        this.param1 = param1;
    }

    public Integer getParam2() {
        return param2;
    }

    public MMission param2(Integer param2) {
        this.param2 = param2;
        return this;
    }

    public void setParam2(Integer param2) {
        this.param2 = param2;
    }

    public Integer getParam3() {
        return param3;
    }

    public MMission param3(Integer param3) {
        this.param3 = param3;
        return this;
    }

    public void setParam3(Integer param3) {
        this.param3 = param3;
    }

    public Integer getParam4() {
        return param4;
    }

    public MMission param4(Integer param4) {
        this.param4 = param4;
        return this;
    }

    public void setParam4(Integer param4) {
        this.param4 = param4;
    }

    public Integer getParam5() {
        return param5;
    }

    public MMission param5(Integer param5) {
        this.param5 = param5;
        return this;
    }

    public void setParam5(Integer param5) {
        this.param5 = param5;
    }

    public Integer getTrigger() {
        return trigger;
    }

    public MMission trigger(Integer trigger) {
        this.trigger = trigger;
        return this;
    }

    public void setTrigger(Integer trigger) {
        this.trigger = trigger;
    }

    public Integer getTriggerCondition() {
        return triggerCondition;
    }

    public MMission triggerCondition(Integer triggerCondition) {
        this.triggerCondition = triggerCondition;
        return this;
    }

    public void setTriggerCondition(Integer triggerCondition) {
        this.triggerCondition = triggerCondition;
    }

    public Integer getRewardId() {
        return rewardId;
    }

    public MMission rewardId(Integer rewardId) {
        this.rewardId = rewardId;
        return this;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MMission startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MMission endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public Integer getLink() {
        return link;
    }

    public MMission link(Integer link) {
        this.link = link;
        return this;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public String getSceneTransitionParam() {
        return sceneTransitionParam;
    }

    public MMission sceneTransitionParam(String sceneTransitionParam) {
        this.sceneTransitionParam = sceneTransitionParam;
        return this;
    }

    public void setSceneTransitionParam(String sceneTransitionParam) {
        this.sceneTransitionParam = sceneTransitionParam;
    }

    public Integer getPickup() {
        return pickup;
    }

    public MMission pickup(Integer pickup) {
        this.pickup = pickup;
        return this;
    }

    public void setPickup(Integer pickup) {
        this.pickup = pickup;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public MMission orderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public MMissionReward getMmissionreward() {
        return mmissionreward;
    }

    public MMission mmissionreward(MMissionReward mMissionReward) {
        this.mmissionreward = mMissionReward;
        return this;
    }

    public void setMmissionreward(MMissionReward mMissionReward) {
        this.mmissionreward = mMissionReward;
    }

    public Set<MAchievement> getMAchievements() {
        return mAchievements;
    }

    public MMission mAchievements(Set<MAchievement> mAchievements) {
        this.mAchievements = mAchievements;
        return this;
    }

    public MMission addMAchievement(MAchievement mAchievement) {
        this.mAchievements.add(mAchievement);
        mAchievement.setMmission(this);
        return this;
    }

    public MMission removeMAchievement(MAchievement mAchievement) {
        this.mAchievements.remove(mAchievement);
        mAchievement.setMmission(null);
        return this;
    }

    public void setMAchievements(Set<MAchievement> mAchievements) {
        this.mAchievements = mAchievements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MMission)) {
            return false;
        }
        return id != null && id.equals(((MMission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMission{" +
            "id=" + getId() +
            ", term=" + getTerm() +
            ", roundNum=" + getRoundNum() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", missionType=" + getMissionType() +
            ", absolute=" + getAbsolute() +
            ", param1=" + getParam1() +
            ", param2=" + getParam2() +
            ", param3=" + getParam3() +
            ", param4=" + getParam4() +
            ", param5=" + getParam5() +
            ", trigger=" + getTrigger() +
            ", triggerCondition=" + getTriggerCondition() +
            ", rewardId=" + getRewardId() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            ", link=" + getLink() +
            ", sceneTransitionParam='" + getSceneTransitionParam() + "'" +
            ", pickup=" + getPickup() +
            ", orderNum=" + getOrderNum() +
            "}";
    }
}
