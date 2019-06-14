package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMission} entity.
 */
public class MMissionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer term;

    private Integer roundNum;

    @Lob
    private String title;

    @Lob
    private String description;

    @NotNull
    private Integer missionType;

    @NotNull
    private Integer absolute;

    private Integer param1;

    private Integer param2;

    private Integer param3;

    private Integer param4;

    private Integer param5;

    @NotNull
    private Integer trigger;

    @NotNull
    private Integer triggerCondition;

    @NotNull
    private Integer rewardId;

    @NotNull
    private Integer startAt;

    private Integer endAt;

    private Integer link;

    @Lob
    private String sceneTransitionParam;

    @NotNull
    private Integer pickup;

    @NotNull
    private Integer orderNum;


    private Long idId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(Integer roundNum) {
        this.roundNum = roundNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMissionType() {
        return missionType;
    }

    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    public Integer getAbsolute() {
        return absolute;
    }

    public void setAbsolute(Integer absolute) {
        this.absolute = absolute;
    }

    public Integer getParam1() {
        return param1;
    }

    public void setParam1(Integer param1) {
        this.param1 = param1;
    }

    public Integer getParam2() {
        return param2;
    }

    public void setParam2(Integer param2) {
        this.param2 = param2;
    }

    public Integer getParam3() {
        return param3;
    }

    public void setParam3(Integer param3) {
        this.param3 = param3;
    }

    public Integer getParam4() {
        return param4;
    }

    public void setParam4(Integer param4) {
        this.param4 = param4;
    }

    public Integer getParam5() {
        return param5;
    }

    public void setParam5(Integer param5) {
        this.param5 = param5;
    }

    public Integer getTrigger() {
        return trigger;
    }

    public void setTrigger(Integer trigger) {
        this.trigger = trigger;
    }

    public Integer getTriggerCondition() {
        return triggerCondition;
    }

    public void setTriggerCondition(Integer triggerCondition) {
        this.triggerCondition = triggerCondition;
    }

    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public String getSceneTransitionParam() {
        return sceneTransitionParam;
    }

    public void setSceneTransitionParam(String sceneTransitionParam) {
        this.sceneTransitionParam = sceneTransitionParam;
    }

    public Integer getPickup() {
        return pickup;
    }

    public void setPickup(Integer pickup) {
        this.pickup = pickup;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long mMissionRewardId) {
        this.idId = mMissionRewardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MMissionDTO mMissionDTO = (MMissionDTO) o;
        if (mMissionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMissionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMissionDTO{" +
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
            ", id=" + getIdId() +
            "}";
    }
}
