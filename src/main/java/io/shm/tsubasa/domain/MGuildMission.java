package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGuildMission.
 */
@Entity
@Table(name = "m_guild_mission")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGuildMission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "term", nullable = false)
    private Integer term;

    
    @Lob
    @Column(name = "title", nullable = false)
    private String title;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "mission_type", nullable = false)
    private Integer missionType;

    @Column(name = "param_1")
    private Integer param1;

    @NotNull
    @Column(name = "reward_id", nullable = false)
    private Integer rewardId;

    @Column(name = "jhi_link")
    private Integer link;

    @Lob
    @Column(name = "scene_transition_param")
    private String sceneTransitionParam;

    @NotNull
    @Column(name = "pickup", nullable = false)
    private Integer pickup;

    @Column(name = "trigger_mission_id")
    private Integer triggerMissionId;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mGuildMissions")
    private MMissionReward mmissionreward;

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

    public MGuildMission term(Integer term) {
        this.term = term;
        return this;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getTitle() {
        return title;
    }

    public MGuildMission title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public MGuildMission description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMissionType() {
        return missionType;
    }

    public MGuildMission missionType(Integer missionType) {
        this.missionType = missionType;
        return this;
    }

    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    public Integer getParam1() {
        return param1;
    }

    public MGuildMission param1(Integer param1) {
        this.param1 = param1;
        return this;
    }

    public void setParam1(Integer param1) {
        this.param1 = param1;
    }

    public Integer getRewardId() {
        return rewardId;
    }

    public MGuildMission rewardId(Integer rewardId) {
        this.rewardId = rewardId;
        return this;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public Integer getLink() {
        return link;
    }

    public MGuildMission link(Integer link) {
        this.link = link;
        return this;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public String getSceneTransitionParam() {
        return sceneTransitionParam;
    }

    public MGuildMission sceneTransitionParam(String sceneTransitionParam) {
        this.sceneTransitionParam = sceneTransitionParam;
        return this;
    }

    public void setSceneTransitionParam(String sceneTransitionParam) {
        this.sceneTransitionParam = sceneTransitionParam;
    }

    public Integer getPickup() {
        return pickup;
    }

    public MGuildMission pickup(Integer pickup) {
        this.pickup = pickup;
        return this;
    }

    public void setPickup(Integer pickup) {
        this.pickup = pickup;
    }

    public Integer getTriggerMissionId() {
        return triggerMissionId;
    }

    public MGuildMission triggerMissionId(Integer triggerMissionId) {
        this.triggerMissionId = triggerMissionId;
        return this;
    }

    public void setTriggerMissionId(Integer triggerMissionId) {
        this.triggerMissionId = triggerMissionId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public MGuildMission orderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public MMissionReward getMmissionreward() {
        return mmissionreward;
    }

    public MGuildMission mmissionreward(MMissionReward mMissionReward) {
        this.mmissionreward = mMissionReward;
        return this;
    }

    public void setMmissionreward(MMissionReward mMissionReward) {
        this.mmissionreward = mMissionReward;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGuildMission)) {
            return false;
        }
        return id != null && id.equals(((MGuildMission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGuildMission{" +
            "id=" + getId() +
            ", term=" + getTerm() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", missionType=" + getMissionType() +
            ", param1=" + getParam1() +
            ", rewardId=" + getRewardId() +
            ", link=" + getLink() +
            ", sceneTransitionParam='" + getSceneTransitionParam() + "'" +
            ", pickup=" + getPickup() +
            ", triggerMissionId=" + getTriggerMissionId() +
            ", orderNum=" + getOrderNum() +
            "}";
    }
}
