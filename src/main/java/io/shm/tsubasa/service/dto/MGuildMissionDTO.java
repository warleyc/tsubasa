package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGuildMission} entity.
 */
public class MGuildMissionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer term;

    
    @Lob
    private String title;

    
    @Lob
    private String description;

    @NotNull
    private Integer missionType;

    private Integer param1;

    @NotNull
    private Integer rewardId;

    private Integer link;

    @Lob
    private String sceneTransitionParam;

    @NotNull
    private Integer pickup;

    private Integer triggerMissionId;

    @NotNull
    private Integer orderNum;


    private Long mmissionrewardId;

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

    public Integer getParam1() {
        return param1;
    }

    public void setParam1(Integer param1) {
        this.param1 = param1;
    }

    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
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

    public Integer getTriggerMissionId() {
        return triggerMissionId;
    }

    public void setTriggerMissionId(Integer triggerMissionId) {
        this.triggerMissionId = triggerMissionId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getMmissionrewardId() {
        return mmissionrewardId;
    }

    public void setMmissionrewardId(Long mMissionRewardId) {
        this.mmissionrewardId = mMissionRewardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGuildMissionDTO mGuildMissionDTO = (MGuildMissionDTO) o;
        if (mGuildMissionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGuildMissionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGuildMissionDTO{" +
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
            ", mmissionreward=" + getMmissionrewardId() +
            "}";
    }
}
