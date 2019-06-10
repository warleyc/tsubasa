package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDeckCondition} entity.
 */
public class MDeckConditionDTO implements Serializable {

    private Long id;

    private Integer targetFormationGroupId;

    private Integer targetCharacterGroupMinId;

    private Integer targetCharacterGroupMaxId;

    private Integer targetPlayableCardGroupMinId;

    private Integer targetPlayableCardGroupMaxId;

    private Integer targetRarityGroupId;

    private Integer targetAttribute;

    private Integer targetNationalityGroupMinId;

    private Integer targetNationalityGroupMaxId;

    private Integer targetTeamGroupMinId;

    private Integer targetTeamGroupMaxId;

    private Integer targetActionGroupMinId;

    private Integer targetActionGroupMaxId;

    private Integer targetTriggerEffectGroupMinId;

    private Integer targetTriggerEffectGroupMaxId;

    @NotNull
    private Integer targetCharacterMinCount;

    @NotNull
    private Integer targetCharacterMaxCount;

    @NotNull
    private Integer targetPlayableCardMinCount;

    @NotNull
    private Integer targetPlayableCardMaxCount;

    @NotNull
    private Integer targetRarityMaxCount;

    @NotNull
    private Integer targetAttributeMinCount;

    @NotNull
    private Integer targetNationalityMinCount;

    @NotNull
    private Integer targetNationalityMaxCount;

    @NotNull
    private Integer targetTeamMinCount;

    @NotNull
    private Integer targetTeamMaxCount;

    @NotNull
    private Integer targetActionMinCount;

    @NotNull
    private Integer targetActionMaxCount;

    @NotNull
    private Integer targetTriggerEffectMinCount;

    @NotNull
    private Integer targetTriggerEffectMaxCount;

    @NotNull
    private Integer targetCharacterIsStartingMin;

    @NotNull
    private Integer targetCharacterIsStartingMax;

    @NotNull
    private Integer targetPlayableCardIsStartingMin;

    @NotNull
    private Integer targetPlayableCardIsStartingMax;

    @NotNull
    private Integer targetRarityIsStarting;

    @NotNull
    private Integer targetAttributeIsStarting;

    @NotNull
    private Integer targetNationalityIsStartingMin;

    @NotNull
    private Integer targetNationalityIsStartingMax;

    @NotNull
    private Integer targetTeamIsStartingMin;

    @NotNull
    private Integer targetTeamIsStartingMax;

    @NotNull
    private Integer targetActionIsStartingMin;

    @NotNull
    private Integer targetActionIsStartingMax;

    @NotNull
    private Integer targetTriggerEffectIsStartingMin;

    @NotNull
    private Integer targetTriggerEffectIsStartingMax;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetFormationGroupId() {
        return targetFormationGroupId;
    }

    public void setTargetFormationGroupId(Integer targetFormationGroupId) {
        this.targetFormationGroupId = targetFormationGroupId;
    }

    public Integer getTargetCharacterGroupMinId() {
        return targetCharacterGroupMinId;
    }

    public void setTargetCharacterGroupMinId(Integer targetCharacterGroupMinId) {
        this.targetCharacterGroupMinId = targetCharacterGroupMinId;
    }

    public Integer getTargetCharacterGroupMaxId() {
        return targetCharacterGroupMaxId;
    }

    public void setTargetCharacterGroupMaxId(Integer targetCharacterGroupMaxId) {
        this.targetCharacterGroupMaxId = targetCharacterGroupMaxId;
    }

    public Integer getTargetPlayableCardGroupMinId() {
        return targetPlayableCardGroupMinId;
    }

    public void setTargetPlayableCardGroupMinId(Integer targetPlayableCardGroupMinId) {
        this.targetPlayableCardGroupMinId = targetPlayableCardGroupMinId;
    }

    public Integer getTargetPlayableCardGroupMaxId() {
        return targetPlayableCardGroupMaxId;
    }

    public void setTargetPlayableCardGroupMaxId(Integer targetPlayableCardGroupMaxId) {
        this.targetPlayableCardGroupMaxId = targetPlayableCardGroupMaxId;
    }

    public Integer getTargetRarityGroupId() {
        return targetRarityGroupId;
    }

    public void setTargetRarityGroupId(Integer targetRarityGroupId) {
        this.targetRarityGroupId = targetRarityGroupId;
    }

    public Integer getTargetAttribute() {
        return targetAttribute;
    }

    public void setTargetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public Integer getTargetNationalityGroupMinId() {
        return targetNationalityGroupMinId;
    }

    public void setTargetNationalityGroupMinId(Integer targetNationalityGroupMinId) {
        this.targetNationalityGroupMinId = targetNationalityGroupMinId;
    }

    public Integer getTargetNationalityGroupMaxId() {
        return targetNationalityGroupMaxId;
    }

    public void setTargetNationalityGroupMaxId(Integer targetNationalityGroupMaxId) {
        this.targetNationalityGroupMaxId = targetNationalityGroupMaxId;
    }

    public Integer getTargetTeamGroupMinId() {
        return targetTeamGroupMinId;
    }

    public void setTargetTeamGroupMinId(Integer targetTeamGroupMinId) {
        this.targetTeamGroupMinId = targetTeamGroupMinId;
    }

    public Integer getTargetTeamGroupMaxId() {
        return targetTeamGroupMaxId;
    }

    public void setTargetTeamGroupMaxId(Integer targetTeamGroupMaxId) {
        this.targetTeamGroupMaxId = targetTeamGroupMaxId;
    }

    public Integer getTargetActionGroupMinId() {
        return targetActionGroupMinId;
    }

    public void setTargetActionGroupMinId(Integer targetActionGroupMinId) {
        this.targetActionGroupMinId = targetActionGroupMinId;
    }

    public Integer getTargetActionGroupMaxId() {
        return targetActionGroupMaxId;
    }

    public void setTargetActionGroupMaxId(Integer targetActionGroupMaxId) {
        this.targetActionGroupMaxId = targetActionGroupMaxId;
    }

    public Integer getTargetTriggerEffectGroupMinId() {
        return targetTriggerEffectGroupMinId;
    }

    public void setTargetTriggerEffectGroupMinId(Integer targetTriggerEffectGroupMinId) {
        this.targetTriggerEffectGroupMinId = targetTriggerEffectGroupMinId;
    }

    public Integer getTargetTriggerEffectGroupMaxId() {
        return targetTriggerEffectGroupMaxId;
    }

    public void setTargetTriggerEffectGroupMaxId(Integer targetTriggerEffectGroupMaxId) {
        this.targetTriggerEffectGroupMaxId = targetTriggerEffectGroupMaxId;
    }

    public Integer getTargetCharacterMinCount() {
        return targetCharacterMinCount;
    }

    public void setTargetCharacterMinCount(Integer targetCharacterMinCount) {
        this.targetCharacterMinCount = targetCharacterMinCount;
    }

    public Integer getTargetCharacterMaxCount() {
        return targetCharacterMaxCount;
    }

    public void setTargetCharacterMaxCount(Integer targetCharacterMaxCount) {
        this.targetCharacterMaxCount = targetCharacterMaxCount;
    }

    public Integer getTargetPlayableCardMinCount() {
        return targetPlayableCardMinCount;
    }

    public void setTargetPlayableCardMinCount(Integer targetPlayableCardMinCount) {
        this.targetPlayableCardMinCount = targetPlayableCardMinCount;
    }

    public Integer getTargetPlayableCardMaxCount() {
        return targetPlayableCardMaxCount;
    }

    public void setTargetPlayableCardMaxCount(Integer targetPlayableCardMaxCount) {
        this.targetPlayableCardMaxCount = targetPlayableCardMaxCount;
    }

    public Integer getTargetRarityMaxCount() {
        return targetRarityMaxCount;
    }

    public void setTargetRarityMaxCount(Integer targetRarityMaxCount) {
        this.targetRarityMaxCount = targetRarityMaxCount;
    }

    public Integer getTargetAttributeMinCount() {
        return targetAttributeMinCount;
    }

    public void setTargetAttributeMinCount(Integer targetAttributeMinCount) {
        this.targetAttributeMinCount = targetAttributeMinCount;
    }

    public Integer getTargetNationalityMinCount() {
        return targetNationalityMinCount;
    }

    public void setTargetNationalityMinCount(Integer targetNationalityMinCount) {
        this.targetNationalityMinCount = targetNationalityMinCount;
    }

    public Integer getTargetNationalityMaxCount() {
        return targetNationalityMaxCount;
    }

    public void setTargetNationalityMaxCount(Integer targetNationalityMaxCount) {
        this.targetNationalityMaxCount = targetNationalityMaxCount;
    }

    public Integer getTargetTeamMinCount() {
        return targetTeamMinCount;
    }

    public void setTargetTeamMinCount(Integer targetTeamMinCount) {
        this.targetTeamMinCount = targetTeamMinCount;
    }

    public Integer getTargetTeamMaxCount() {
        return targetTeamMaxCount;
    }

    public void setTargetTeamMaxCount(Integer targetTeamMaxCount) {
        this.targetTeamMaxCount = targetTeamMaxCount;
    }

    public Integer getTargetActionMinCount() {
        return targetActionMinCount;
    }

    public void setTargetActionMinCount(Integer targetActionMinCount) {
        this.targetActionMinCount = targetActionMinCount;
    }

    public Integer getTargetActionMaxCount() {
        return targetActionMaxCount;
    }

    public void setTargetActionMaxCount(Integer targetActionMaxCount) {
        this.targetActionMaxCount = targetActionMaxCount;
    }

    public Integer getTargetTriggerEffectMinCount() {
        return targetTriggerEffectMinCount;
    }

    public void setTargetTriggerEffectMinCount(Integer targetTriggerEffectMinCount) {
        this.targetTriggerEffectMinCount = targetTriggerEffectMinCount;
    }

    public Integer getTargetTriggerEffectMaxCount() {
        return targetTriggerEffectMaxCount;
    }

    public void setTargetTriggerEffectMaxCount(Integer targetTriggerEffectMaxCount) {
        this.targetTriggerEffectMaxCount = targetTriggerEffectMaxCount;
    }

    public Integer getTargetCharacterIsStartingMin() {
        return targetCharacterIsStartingMin;
    }

    public void setTargetCharacterIsStartingMin(Integer targetCharacterIsStartingMin) {
        this.targetCharacterIsStartingMin = targetCharacterIsStartingMin;
    }

    public Integer getTargetCharacterIsStartingMax() {
        return targetCharacterIsStartingMax;
    }

    public void setTargetCharacterIsStartingMax(Integer targetCharacterIsStartingMax) {
        this.targetCharacterIsStartingMax = targetCharacterIsStartingMax;
    }

    public Integer getTargetPlayableCardIsStartingMin() {
        return targetPlayableCardIsStartingMin;
    }

    public void setTargetPlayableCardIsStartingMin(Integer targetPlayableCardIsStartingMin) {
        this.targetPlayableCardIsStartingMin = targetPlayableCardIsStartingMin;
    }

    public Integer getTargetPlayableCardIsStartingMax() {
        return targetPlayableCardIsStartingMax;
    }

    public void setTargetPlayableCardIsStartingMax(Integer targetPlayableCardIsStartingMax) {
        this.targetPlayableCardIsStartingMax = targetPlayableCardIsStartingMax;
    }

    public Integer getTargetRarityIsStarting() {
        return targetRarityIsStarting;
    }

    public void setTargetRarityIsStarting(Integer targetRarityIsStarting) {
        this.targetRarityIsStarting = targetRarityIsStarting;
    }

    public Integer getTargetAttributeIsStarting() {
        return targetAttributeIsStarting;
    }

    public void setTargetAttributeIsStarting(Integer targetAttributeIsStarting) {
        this.targetAttributeIsStarting = targetAttributeIsStarting;
    }

    public Integer getTargetNationalityIsStartingMin() {
        return targetNationalityIsStartingMin;
    }

    public void setTargetNationalityIsStartingMin(Integer targetNationalityIsStartingMin) {
        this.targetNationalityIsStartingMin = targetNationalityIsStartingMin;
    }

    public Integer getTargetNationalityIsStartingMax() {
        return targetNationalityIsStartingMax;
    }

    public void setTargetNationalityIsStartingMax(Integer targetNationalityIsStartingMax) {
        this.targetNationalityIsStartingMax = targetNationalityIsStartingMax;
    }

    public Integer getTargetTeamIsStartingMin() {
        return targetTeamIsStartingMin;
    }

    public void setTargetTeamIsStartingMin(Integer targetTeamIsStartingMin) {
        this.targetTeamIsStartingMin = targetTeamIsStartingMin;
    }

    public Integer getTargetTeamIsStartingMax() {
        return targetTeamIsStartingMax;
    }

    public void setTargetTeamIsStartingMax(Integer targetTeamIsStartingMax) {
        this.targetTeamIsStartingMax = targetTeamIsStartingMax;
    }

    public Integer getTargetActionIsStartingMin() {
        return targetActionIsStartingMin;
    }

    public void setTargetActionIsStartingMin(Integer targetActionIsStartingMin) {
        this.targetActionIsStartingMin = targetActionIsStartingMin;
    }

    public Integer getTargetActionIsStartingMax() {
        return targetActionIsStartingMax;
    }

    public void setTargetActionIsStartingMax(Integer targetActionIsStartingMax) {
        this.targetActionIsStartingMax = targetActionIsStartingMax;
    }

    public Integer getTargetTriggerEffectIsStartingMin() {
        return targetTriggerEffectIsStartingMin;
    }

    public void setTargetTriggerEffectIsStartingMin(Integer targetTriggerEffectIsStartingMin) {
        this.targetTriggerEffectIsStartingMin = targetTriggerEffectIsStartingMin;
    }

    public Integer getTargetTriggerEffectIsStartingMax() {
        return targetTriggerEffectIsStartingMax;
    }

    public void setTargetTriggerEffectIsStartingMax(Integer targetTriggerEffectIsStartingMax) {
        this.targetTriggerEffectIsStartingMax = targetTriggerEffectIsStartingMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MDeckConditionDTO mDeckConditionDTO = (MDeckConditionDTO) o;
        if (mDeckConditionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDeckConditionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDeckConditionDTO{" +
            "id=" + getId() +
            ", targetFormationGroupId=" + getTargetFormationGroupId() +
            ", targetCharacterGroupMinId=" + getTargetCharacterGroupMinId() +
            ", targetCharacterGroupMaxId=" + getTargetCharacterGroupMaxId() +
            ", targetPlayableCardGroupMinId=" + getTargetPlayableCardGroupMinId() +
            ", targetPlayableCardGroupMaxId=" + getTargetPlayableCardGroupMaxId() +
            ", targetRarityGroupId=" + getTargetRarityGroupId() +
            ", targetAttribute=" + getTargetAttribute() +
            ", targetNationalityGroupMinId=" + getTargetNationalityGroupMinId() +
            ", targetNationalityGroupMaxId=" + getTargetNationalityGroupMaxId() +
            ", targetTeamGroupMinId=" + getTargetTeamGroupMinId() +
            ", targetTeamGroupMaxId=" + getTargetTeamGroupMaxId() +
            ", targetActionGroupMinId=" + getTargetActionGroupMinId() +
            ", targetActionGroupMaxId=" + getTargetActionGroupMaxId() +
            ", targetTriggerEffectGroupMinId=" + getTargetTriggerEffectGroupMinId() +
            ", targetTriggerEffectGroupMaxId=" + getTargetTriggerEffectGroupMaxId() +
            ", targetCharacterMinCount=" + getTargetCharacterMinCount() +
            ", targetCharacterMaxCount=" + getTargetCharacterMaxCount() +
            ", targetPlayableCardMinCount=" + getTargetPlayableCardMinCount() +
            ", targetPlayableCardMaxCount=" + getTargetPlayableCardMaxCount() +
            ", targetRarityMaxCount=" + getTargetRarityMaxCount() +
            ", targetAttributeMinCount=" + getTargetAttributeMinCount() +
            ", targetNationalityMinCount=" + getTargetNationalityMinCount() +
            ", targetNationalityMaxCount=" + getTargetNationalityMaxCount() +
            ", targetTeamMinCount=" + getTargetTeamMinCount() +
            ", targetTeamMaxCount=" + getTargetTeamMaxCount() +
            ", targetActionMinCount=" + getTargetActionMinCount() +
            ", targetActionMaxCount=" + getTargetActionMaxCount() +
            ", targetTriggerEffectMinCount=" + getTargetTriggerEffectMinCount() +
            ", targetTriggerEffectMaxCount=" + getTargetTriggerEffectMaxCount() +
            ", targetCharacterIsStartingMin=" + getTargetCharacterIsStartingMin() +
            ", targetCharacterIsStartingMax=" + getTargetCharacterIsStartingMax() +
            ", targetPlayableCardIsStartingMin=" + getTargetPlayableCardIsStartingMin() +
            ", targetPlayableCardIsStartingMax=" + getTargetPlayableCardIsStartingMax() +
            ", targetRarityIsStarting=" + getTargetRarityIsStarting() +
            ", targetAttributeIsStarting=" + getTargetAttributeIsStarting() +
            ", targetNationalityIsStartingMin=" + getTargetNationalityIsStartingMin() +
            ", targetNationalityIsStartingMax=" + getTargetNationalityIsStartingMax() +
            ", targetTeamIsStartingMin=" + getTargetTeamIsStartingMin() +
            ", targetTeamIsStartingMax=" + getTargetTeamIsStartingMax() +
            ", targetActionIsStartingMin=" + getTargetActionIsStartingMin() +
            ", targetActionIsStartingMax=" + getTargetActionIsStartingMax() +
            ", targetTriggerEffectIsStartingMin=" + getTargetTriggerEffectIsStartingMin() +
            ", targetTriggerEffectIsStartingMax=" + getTargetTriggerEffectIsStartingMax() +
            "}";
    }
}
