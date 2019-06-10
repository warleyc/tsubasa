package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionKicker} entity.
 */
public class MGachaRenditionKickerDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer renditionId;

    @NotNull
    private Integer isManySsr;

    @NotNull
    private Integer isSsr;

    @NotNull
    private Integer weight;

    @NotNull
    private Integer leftModelId;

    @NotNull
    private Integer leftUniformUpId;

    @NotNull
    private Integer leftUniformBottomId;

    @NotNull
    private Integer leftUniformNumber;

    @NotNull
    private Integer rightModelId;

    @NotNull
    private Integer rightUniformUpId;

    @NotNull
    private Integer rightUniformBottomId;

    @NotNull
    private Integer rightUniformNumber;

    
    @Lob
    private String cutInSpriteName;

    
    @Lob
    private String leftMessage;

    
    @Lob
    private String rightMessage;

    
    @Lob
    private String voiceTrigger;

    
    @Lob
    private String voiceStartCutIn;

    @NotNull
    private Integer kickerId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRenditionId() {
        return renditionId;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public Integer getIsManySsr() {
        return isManySsr;
    }

    public void setIsManySsr(Integer isManySsr) {
        this.isManySsr = isManySsr;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getLeftModelId() {
        return leftModelId;
    }

    public void setLeftModelId(Integer leftModelId) {
        this.leftModelId = leftModelId;
    }

    public Integer getLeftUniformUpId() {
        return leftUniformUpId;
    }

    public void setLeftUniformUpId(Integer leftUniformUpId) {
        this.leftUniformUpId = leftUniformUpId;
    }

    public Integer getLeftUniformBottomId() {
        return leftUniformBottomId;
    }

    public void setLeftUniformBottomId(Integer leftUniformBottomId) {
        this.leftUniformBottomId = leftUniformBottomId;
    }

    public Integer getLeftUniformNumber() {
        return leftUniformNumber;
    }

    public void setLeftUniformNumber(Integer leftUniformNumber) {
        this.leftUniformNumber = leftUniformNumber;
    }

    public Integer getRightModelId() {
        return rightModelId;
    }

    public void setRightModelId(Integer rightModelId) {
        this.rightModelId = rightModelId;
    }

    public Integer getRightUniformUpId() {
        return rightUniformUpId;
    }

    public void setRightUniformUpId(Integer rightUniformUpId) {
        this.rightUniformUpId = rightUniformUpId;
    }

    public Integer getRightUniformBottomId() {
        return rightUniformBottomId;
    }

    public void setRightUniformBottomId(Integer rightUniformBottomId) {
        this.rightUniformBottomId = rightUniformBottomId;
    }

    public Integer getRightUniformNumber() {
        return rightUniformNumber;
    }

    public void setRightUniformNumber(Integer rightUniformNumber) {
        this.rightUniformNumber = rightUniformNumber;
    }

    public String getCutInSpriteName() {
        return cutInSpriteName;
    }

    public void setCutInSpriteName(String cutInSpriteName) {
        this.cutInSpriteName = cutInSpriteName;
    }

    public String getLeftMessage() {
        return leftMessage;
    }

    public void setLeftMessage(String leftMessage) {
        this.leftMessage = leftMessage;
    }

    public String getRightMessage() {
        return rightMessage;
    }

    public void setRightMessage(String rightMessage) {
        this.rightMessage = rightMessage;
    }

    public String getVoiceTrigger() {
        return voiceTrigger;
    }

    public void setVoiceTrigger(String voiceTrigger) {
        this.voiceTrigger = voiceTrigger;
    }

    public String getVoiceStartCutIn() {
        return voiceStartCutIn;
    }

    public void setVoiceStartCutIn(String voiceStartCutIn) {
        this.voiceStartCutIn = voiceStartCutIn;
    }

    public Integer getKickerId() {
        return kickerId;
    }

    public void setKickerId(Integer kickerId) {
        this.kickerId = kickerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = (MGachaRenditionKickerDTO) o;
        if (mGachaRenditionKickerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionKickerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionKickerDTO{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", isManySsr=" + getIsManySsr() +
            ", isSsr=" + getIsSsr() +
            ", weight=" + getWeight() +
            ", leftModelId=" + getLeftModelId() +
            ", leftUniformUpId=" + getLeftUniformUpId() +
            ", leftUniformBottomId=" + getLeftUniformBottomId() +
            ", leftUniformNumber=" + getLeftUniformNumber() +
            ", rightModelId=" + getRightModelId() +
            ", rightUniformUpId=" + getRightUniformUpId() +
            ", rightUniformBottomId=" + getRightUniformBottomId() +
            ", rightUniformNumber=" + getRightUniformNumber() +
            ", cutInSpriteName='" + getCutInSpriteName() + "'" +
            ", leftMessage='" + getLeftMessage() + "'" +
            ", rightMessage='" + getRightMessage() + "'" +
            ", voiceTrigger='" + getVoiceTrigger() + "'" +
            ", voiceStartCutIn='" + getVoiceStartCutIn() + "'" +
            ", kickerId=" + getKickerId() +
            "}";
    }
}
