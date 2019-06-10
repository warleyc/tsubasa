package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionKicker.
 */
@Entity
@Table(name = "m_gacha_rendition_kicker")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionKicker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rendition_id", nullable = false)
    private Integer renditionId;

    @NotNull
    @Column(name = "is_many_ssr", nullable = false)
    private Integer isManySsr;

    @NotNull
    @Column(name = "is_ssr", nullable = false)
    private Integer isSsr;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @NotNull
    @Column(name = "left_model_id", nullable = false)
    private Integer leftModelId;

    @NotNull
    @Column(name = "left_uniform_up_id", nullable = false)
    private Integer leftUniformUpId;

    @NotNull
    @Column(name = "left_uniform_bottom_id", nullable = false)
    private Integer leftUniformBottomId;

    @NotNull
    @Column(name = "left_uniform_number", nullable = false)
    private Integer leftUniformNumber;

    @NotNull
    @Column(name = "right_model_id", nullable = false)
    private Integer rightModelId;

    @NotNull
    @Column(name = "right_uniform_up_id", nullable = false)
    private Integer rightUniformUpId;

    @NotNull
    @Column(name = "right_uniform_bottom_id", nullable = false)
    private Integer rightUniformBottomId;

    @NotNull
    @Column(name = "right_uniform_number", nullable = false)
    private Integer rightUniformNumber;

    
    @Lob
    @Column(name = "cut_in_sprite_name", nullable = false)
    private String cutInSpriteName;

    
    @Lob
    @Column(name = "left_message", nullable = false)
    private String leftMessage;

    
    @Lob
    @Column(name = "right_message", nullable = false)
    private String rightMessage;

    
    @Lob
    @Column(name = "voice_trigger", nullable = false)
    private String voiceTrigger;

    
    @Lob
    @Column(name = "voice_start_cut_in", nullable = false)
    private String voiceStartCutIn;

    @NotNull
    @Column(name = "kicker_id", nullable = false)
    private Integer kickerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRenditionId() {
        return renditionId;
    }

    public MGachaRenditionKicker renditionId(Integer renditionId) {
        this.renditionId = renditionId;
        return this;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public Integer getIsManySsr() {
        return isManySsr;
    }

    public MGachaRenditionKicker isManySsr(Integer isManySsr) {
        this.isManySsr = isManySsr;
        return this;
    }

    public void setIsManySsr(Integer isManySsr) {
        this.isManySsr = isManySsr;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public MGachaRenditionKicker isSsr(Integer isSsr) {
        this.isSsr = isSsr;
        return this;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionKicker weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getLeftModelId() {
        return leftModelId;
    }

    public MGachaRenditionKicker leftModelId(Integer leftModelId) {
        this.leftModelId = leftModelId;
        return this;
    }

    public void setLeftModelId(Integer leftModelId) {
        this.leftModelId = leftModelId;
    }

    public Integer getLeftUniformUpId() {
        return leftUniformUpId;
    }

    public MGachaRenditionKicker leftUniformUpId(Integer leftUniformUpId) {
        this.leftUniformUpId = leftUniformUpId;
        return this;
    }

    public void setLeftUniformUpId(Integer leftUniformUpId) {
        this.leftUniformUpId = leftUniformUpId;
    }

    public Integer getLeftUniformBottomId() {
        return leftUniformBottomId;
    }

    public MGachaRenditionKicker leftUniformBottomId(Integer leftUniformBottomId) {
        this.leftUniformBottomId = leftUniformBottomId;
        return this;
    }

    public void setLeftUniformBottomId(Integer leftUniformBottomId) {
        this.leftUniformBottomId = leftUniformBottomId;
    }

    public Integer getLeftUniformNumber() {
        return leftUniformNumber;
    }

    public MGachaRenditionKicker leftUniformNumber(Integer leftUniformNumber) {
        this.leftUniformNumber = leftUniformNumber;
        return this;
    }

    public void setLeftUniformNumber(Integer leftUniformNumber) {
        this.leftUniformNumber = leftUniformNumber;
    }

    public Integer getRightModelId() {
        return rightModelId;
    }

    public MGachaRenditionKicker rightModelId(Integer rightModelId) {
        this.rightModelId = rightModelId;
        return this;
    }

    public void setRightModelId(Integer rightModelId) {
        this.rightModelId = rightModelId;
    }

    public Integer getRightUniformUpId() {
        return rightUniformUpId;
    }

    public MGachaRenditionKicker rightUniformUpId(Integer rightUniformUpId) {
        this.rightUniformUpId = rightUniformUpId;
        return this;
    }

    public void setRightUniformUpId(Integer rightUniformUpId) {
        this.rightUniformUpId = rightUniformUpId;
    }

    public Integer getRightUniformBottomId() {
        return rightUniformBottomId;
    }

    public MGachaRenditionKicker rightUniformBottomId(Integer rightUniformBottomId) {
        this.rightUniformBottomId = rightUniformBottomId;
        return this;
    }

    public void setRightUniformBottomId(Integer rightUniformBottomId) {
        this.rightUniformBottomId = rightUniformBottomId;
    }

    public Integer getRightUniformNumber() {
        return rightUniformNumber;
    }

    public MGachaRenditionKicker rightUniformNumber(Integer rightUniformNumber) {
        this.rightUniformNumber = rightUniformNumber;
        return this;
    }

    public void setRightUniformNumber(Integer rightUniformNumber) {
        this.rightUniformNumber = rightUniformNumber;
    }

    public String getCutInSpriteName() {
        return cutInSpriteName;
    }

    public MGachaRenditionKicker cutInSpriteName(String cutInSpriteName) {
        this.cutInSpriteName = cutInSpriteName;
        return this;
    }

    public void setCutInSpriteName(String cutInSpriteName) {
        this.cutInSpriteName = cutInSpriteName;
    }

    public String getLeftMessage() {
        return leftMessage;
    }

    public MGachaRenditionKicker leftMessage(String leftMessage) {
        this.leftMessage = leftMessage;
        return this;
    }

    public void setLeftMessage(String leftMessage) {
        this.leftMessage = leftMessage;
    }

    public String getRightMessage() {
        return rightMessage;
    }

    public MGachaRenditionKicker rightMessage(String rightMessage) {
        this.rightMessage = rightMessage;
        return this;
    }

    public void setRightMessage(String rightMessage) {
        this.rightMessage = rightMessage;
    }

    public String getVoiceTrigger() {
        return voiceTrigger;
    }

    public MGachaRenditionKicker voiceTrigger(String voiceTrigger) {
        this.voiceTrigger = voiceTrigger;
        return this;
    }

    public void setVoiceTrigger(String voiceTrigger) {
        this.voiceTrigger = voiceTrigger;
    }

    public String getVoiceStartCutIn() {
        return voiceStartCutIn;
    }

    public MGachaRenditionKicker voiceStartCutIn(String voiceStartCutIn) {
        this.voiceStartCutIn = voiceStartCutIn;
        return this;
    }

    public void setVoiceStartCutIn(String voiceStartCutIn) {
        this.voiceStartCutIn = voiceStartCutIn;
    }

    public Integer getKickerId() {
        return kickerId;
    }

    public MGachaRenditionKicker kickerId(Integer kickerId) {
        this.kickerId = kickerId;
        return this;
    }

    public void setKickerId(Integer kickerId) {
        this.kickerId = kickerId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionKicker)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionKicker) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionKicker{" +
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
