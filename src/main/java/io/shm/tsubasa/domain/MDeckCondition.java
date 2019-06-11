package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MDeckCondition.
 */
@Entity
@Table(name = "m_deck_condition")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MDeckCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "target_formation_group_id")
    private Integer targetFormationGroupId;

    @Column(name = "target_character_group_min_id")
    private Integer targetCharacterGroupMinId;

    @Column(name = "target_character_group_max_id")
    private Integer targetCharacterGroupMaxId;

    @Column(name = "target_playable_card_group_min_id")
    private Integer targetPlayableCardGroupMinId;

    @Column(name = "target_playable_card_group_max_id")
    private Integer targetPlayableCardGroupMaxId;

    @Column(name = "target_rarity_group_id")
    private Integer targetRarityGroupId;

    @Column(name = "target_attribute")
    private Integer targetAttribute;

    @Column(name = "target_nationality_group_min_id")
    private Integer targetNationalityGroupMinId;

    @Column(name = "target_nationality_group_max_id")
    private Integer targetNationalityGroupMaxId;

    @Column(name = "target_team_group_min_id")
    private Integer targetTeamGroupMinId;

    @Column(name = "target_team_group_max_id")
    private Integer targetTeamGroupMaxId;

    @Column(name = "target_action_group_min_id")
    private Integer targetActionGroupMinId;

    @Column(name = "target_action_group_max_id")
    private Integer targetActionGroupMaxId;

    @Column(name = "target_trigger_effect_group_min_id")
    private Integer targetTriggerEffectGroupMinId;

    @Column(name = "target_trigger_effect_group_max_id")
    private Integer targetTriggerEffectGroupMaxId;

    @NotNull
    @Column(name = "target_character_min_count", nullable = false)
    private Integer targetCharacterMinCount;

    @NotNull
    @Column(name = "target_character_max_count", nullable = false)
    private Integer targetCharacterMaxCount;

    @NotNull
    @Column(name = "target_playable_card_min_count", nullable = false)
    private Integer targetPlayableCardMinCount;

    @NotNull
    @Column(name = "target_playable_card_max_count", nullable = false)
    private Integer targetPlayableCardMaxCount;

    @NotNull
    @Column(name = "target_rarity_max_count", nullable = false)
    private Integer targetRarityMaxCount;

    @NotNull
    @Column(name = "target_attribute_min_count", nullable = false)
    private Integer targetAttributeMinCount;

    @NotNull
    @Column(name = "target_nationality_min_count", nullable = false)
    private Integer targetNationalityMinCount;

    @NotNull
    @Column(name = "target_nationality_max_count", nullable = false)
    private Integer targetNationalityMaxCount;

    @NotNull
    @Column(name = "target_team_min_count", nullable = false)
    private Integer targetTeamMinCount;

    @NotNull
    @Column(name = "target_team_max_count", nullable = false)
    private Integer targetTeamMaxCount;

    @NotNull
    @Column(name = "target_action_min_count", nullable = false)
    private Integer targetActionMinCount;

    @NotNull
    @Column(name = "target_action_max_count", nullable = false)
    private Integer targetActionMaxCount;

    @NotNull
    @Column(name = "target_trigger_effect_min_count", nullable = false)
    private Integer targetTriggerEffectMinCount;

    @NotNull
    @Column(name = "target_trigger_effect_max_count", nullable = false)
    private Integer targetTriggerEffectMaxCount;

    @NotNull
    @Column(name = "target_character_is_starting_min", nullable = false)
    private Integer targetCharacterIsStartingMin;

    @NotNull
    @Column(name = "target_character_is_starting_max", nullable = false)
    private Integer targetCharacterIsStartingMax;

    @NotNull
    @Column(name = "target_playable_card_is_starting_min", nullable = false)
    private Integer targetPlayableCardIsStartingMin;

    @NotNull
    @Column(name = "target_playable_card_is_starting_max", nullable = false)
    private Integer targetPlayableCardIsStartingMax;

    @NotNull
    @Column(name = "target_rarity_is_starting", nullable = false)
    private Integer targetRarityIsStarting;

    @NotNull
    @Column(name = "target_attribute_is_starting", nullable = false)
    private Integer targetAttributeIsStarting;

    @NotNull
    @Column(name = "target_nationality_is_starting_min", nullable = false)
    private Integer targetNationalityIsStartingMin;

    @NotNull
    @Column(name = "target_nationality_is_starting_max", nullable = false)
    private Integer targetNationalityIsStartingMax;

    @NotNull
    @Column(name = "target_team_is_starting_min", nullable = false)
    private Integer targetTeamIsStartingMin;

    @NotNull
    @Column(name = "target_team_is_starting_max", nullable = false)
    private Integer targetTeamIsStartingMax;

    @NotNull
    @Column(name = "target_action_is_starting_min", nullable = false)
    private Integer targetActionIsStartingMin;

    @NotNull
    @Column(name = "target_action_is_starting_max", nullable = false)
    private Integer targetActionIsStartingMax;

    @NotNull
    @Column(name = "target_trigger_effect_is_starting_min", nullable = false)
    private Integer targetTriggerEffectIsStartingMin;

    @NotNull
    @Column(name = "target_trigger_effect_is_starting_max", nullable = false)
    private Integer targetTriggerEffectIsStartingMax;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetFormationGroupId() {
        return targetFormationGroupId;
    }

    public MDeckCondition targetFormationGroupId(Integer targetFormationGroupId) {
        this.targetFormationGroupId = targetFormationGroupId;
        return this;
    }

    public void setTargetFormationGroupId(Integer targetFormationGroupId) {
        this.targetFormationGroupId = targetFormationGroupId;
    }

    public Integer getTargetCharacterGroupMinId() {
        return targetCharacterGroupMinId;
    }

    public MDeckCondition targetCharacterGroupMinId(Integer targetCharacterGroupMinId) {
        this.targetCharacterGroupMinId = targetCharacterGroupMinId;
        return this;
    }

    public void setTargetCharacterGroupMinId(Integer targetCharacterGroupMinId) {
        this.targetCharacterGroupMinId = targetCharacterGroupMinId;
    }

    public Integer getTargetCharacterGroupMaxId() {
        return targetCharacterGroupMaxId;
    }

    public MDeckCondition targetCharacterGroupMaxId(Integer targetCharacterGroupMaxId) {
        this.targetCharacterGroupMaxId = targetCharacterGroupMaxId;
        return this;
    }

    public void setTargetCharacterGroupMaxId(Integer targetCharacterGroupMaxId) {
        this.targetCharacterGroupMaxId = targetCharacterGroupMaxId;
    }

    public Integer getTargetPlayableCardGroupMinId() {
        return targetPlayableCardGroupMinId;
    }

    public MDeckCondition targetPlayableCardGroupMinId(Integer targetPlayableCardGroupMinId) {
        this.targetPlayableCardGroupMinId = targetPlayableCardGroupMinId;
        return this;
    }

    public void setTargetPlayableCardGroupMinId(Integer targetPlayableCardGroupMinId) {
        this.targetPlayableCardGroupMinId = targetPlayableCardGroupMinId;
    }

    public Integer getTargetPlayableCardGroupMaxId() {
        return targetPlayableCardGroupMaxId;
    }

    public MDeckCondition targetPlayableCardGroupMaxId(Integer targetPlayableCardGroupMaxId) {
        this.targetPlayableCardGroupMaxId = targetPlayableCardGroupMaxId;
        return this;
    }

    public void setTargetPlayableCardGroupMaxId(Integer targetPlayableCardGroupMaxId) {
        this.targetPlayableCardGroupMaxId = targetPlayableCardGroupMaxId;
    }

    public Integer getTargetRarityGroupId() {
        return targetRarityGroupId;
    }

    public MDeckCondition targetRarityGroupId(Integer targetRarityGroupId) {
        this.targetRarityGroupId = targetRarityGroupId;
        return this;
    }

    public void setTargetRarityGroupId(Integer targetRarityGroupId) {
        this.targetRarityGroupId = targetRarityGroupId;
    }

    public Integer getTargetAttribute() {
        return targetAttribute;
    }

    public MDeckCondition targetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
        return this;
    }

    public void setTargetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public Integer getTargetNationalityGroupMinId() {
        return targetNationalityGroupMinId;
    }

    public MDeckCondition targetNationalityGroupMinId(Integer targetNationalityGroupMinId) {
        this.targetNationalityGroupMinId = targetNationalityGroupMinId;
        return this;
    }

    public void setTargetNationalityGroupMinId(Integer targetNationalityGroupMinId) {
        this.targetNationalityGroupMinId = targetNationalityGroupMinId;
    }

    public Integer getTargetNationalityGroupMaxId() {
        return targetNationalityGroupMaxId;
    }

    public MDeckCondition targetNationalityGroupMaxId(Integer targetNationalityGroupMaxId) {
        this.targetNationalityGroupMaxId = targetNationalityGroupMaxId;
        return this;
    }

    public void setTargetNationalityGroupMaxId(Integer targetNationalityGroupMaxId) {
        this.targetNationalityGroupMaxId = targetNationalityGroupMaxId;
    }

    public Integer getTargetTeamGroupMinId() {
        return targetTeamGroupMinId;
    }

    public MDeckCondition targetTeamGroupMinId(Integer targetTeamGroupMinId) {
        this.targetTeamGroupMinId = targetTeamGroupMinId;
        return this;
    }

    public void setTargetTeamGroupMinId(Integer targetTeamGroupMinId) {
        this.targetTeamGroupMinId = targetTeamGroupMinId;
    }

    public Integer getTargetTeamGroupMaxId() {
        return targetTeamGroupMaxId;
    }

    public MDeckCondition targetTeamGroupMaxId(Integer targetTeamGroupMaxId) {
        this.targetTeamGroupMaxId = targetTeamGroupMaxId;
        return this;
    }

    public void setTargetTeamGroupMaxId(Integer targetTeamGroupMaxId) {
        this.targetTeamGroupMaxId = targetTeamGroupMaxId;
    }

    public Integer getTargetActionGroupMinId() {
        return targetActionGroupMinId;
    }

    public MDeckCondition targetActionGroupMinId(Integer targetActionGroupMinId) {
        this.targetActionGroupMinId = targetActionGroupMinId;
        return this;
    }

    public void setTargetActionGroupMinId(Integer targetActionGroupMinId) {
        this.targetActionGroupMinId = targetActionGroupMinId;
    }

    public Integer getTargetActionGroupMaxId() {
        return targetActionGroupMaxId;
    }

    public MDeckCondition targetActionGroupMaxId(Integer targetActionGroupMaxId) {
        this.targetActionGroupMaxId = targetActionGroupMaxId;
        return this;
    }

    public void setTargetActionGroupMaxId(Integer targetActionGroupMaxId) {
        this.targetActionGroupMaxId = targetActionGroupMaxId;
    }

    public Integer getTargetTriggerEffectGroupMinId() {
        return targetTriggerEffectGroupMinId;
    }

    public MDeckCondition targetTriggerEffectGroupMinId(Integer targetTriggerEffectGroupMinId) {
        this.targetTriggerEffectGroupMinId = targetTriggerEffectGroupMinId;
        return this;
    }

    public void setTargetTriggerEffectGroupMinId(Integer targetTriggerEffectGroupMinId) {
        this.targetTriggerEffectGroupMinId = targetTriggerEffectGroupMinId;
    }

    public Integer getTargetTriggerEffectGroupMaxId() {
        return targetTriggerEffectGroupMaxId;
    }

    public MDeckCondition targetTriggerEffectGroupMaxId(Integer targetTriggerEffectGroupMaxId) {
        this.targetTriggerEffectGroupMaxId = targetTriggerEffectGroupMaxId;
        return this;
    }

    public void setTargetTriggerEffectGroupMaxId(Integer targetTriggerEffectGroupMaxId) {
        this.targetTriggerEffectGroupMaxId = targetTriggerEffectGroupMaxId;
    }

    public Integer getTargetCharacterMinCount() {
        return targetCharacterMinCount;
    }

    public MDeckCondition targetCharacterMinCount(Integer targetCharacterMinCount) {
        this.targetCharacterMinCount = targetCharacterMinCount;
        return this;
    }

    public void setTargetCharacterMinCount(Integer targetCharacterMinCount) {
        this.targetCharacterMinCount = targetCharacterMinCount;
    }

    public Integer getTargetCharacterMaxCount() {
        return targetCharacterMaxCount;
    }

    public MDeckCondition targetCharacterMaxCount(Integer targetCharacterMaxCount) {
        this.targetCharacterMaxCount = targetCharacterMaxCount;
        return this;
    }

    public void setTargetCharacterMaxCount(Integer targetCharacterMaxCount) {
        this.targetCharacterMaxCount = targetCharacterMaxCount;
    }

    public Integer getTargetPlayableCardMinCount() {
        return targetPlayableCardMinCount;
    }

    public MDeckCondition targetPlayableCardMinCount(Integer targetPlayableCardMinCount) {
        this.targetPlayableCardMinCount = targetPlayableCardMinCount;
        return this;
    }

    public void setTargetPlayableCardMinCount(Integer targetPlayableCardMinCount) {
        this.targetPlayableCardMinCount = targetPlayableCardMinCount;
    }

    public Integer getTargetPlayableCardMaxCount() {
        return targetPlayableCardMaxCount;
    }

    public MDeckCondition targetPlayableCardMaxCount(Integer targetPlayableCardMaxCount) {
        this.targetPlayableCardMaxCount = targetPlayableCardMaxCount;
        return this;
    }

    public void setTargetPlayableCardMaxCount(Integer targetPlayableCardMaxCount) {
        this.targetPlayableCardMaxCount = targetPlayableCardMaxCount;
    }

    public Integer getTargetRarityMaxCount() {
        return targetRarityMaxCount;
    }

    public MDeckCondition targetRarityMaxCount(Integer targetRarityMaxCount) {
        this.targetRarityMaxCount = targetRarityMaxCount;
        return this;
    }

    public void setTargetRarityMaxCount(Integer targetRarityMaxCount) {
        this.targetRarityMaxCount = targetRarityMaxCount;
    }

    public Integer getTargetAttributeMinCount() {
        return targetAttributeMinCount;
    }

    public MDeckCondition targetAttributeMinCount(Integer targetAttributeMinCount) {
        this.targetAttributeMinCount = targetAttributeMinCount;
        return this;
    }

    public void setTargetAttributeMinCount(Integer targetAttributeMinCount) {
        this.targetAttributeMinCount = targetAttributeMinCount;
    }

    public Integer getTargetNationalityMinCount() {
        return targetNationalityMinCount;
    }

    public MDeckCondition targetNationalityMinCount(Integer targetNationalityMinCount) {
        this.targetNationalityMinCount = targetNationalityMinCount;
        return this;
    }

    public void setTargetNationalityMinCount(Integer targetNationalityMinCount) {
        this.targetNationalityMinCount = targetNationalityMinCount;
    }

    public Integer getTargetNationalityMaxCount() {
        return targetNationalityMaxCount;
    }

    public MDeckCondition targetNationalityMaxCount(Integer targetNationalityMaxCount) {
        this.targetNationalityMaxCount = targetNationalityMaxCount;
        return this;
    }

    public void setTargetNationalityMaxCount(Integer targetNationalityMaxCount) {
        this.targetNationalityMaxCount = targetNationalityMaxCount;
    }

    public Integer getTargetTeamMinCount() {
        return targetTeamMinCount;
    }

    public MDeckCondition targetTeamMinCount(Integer targetTeamMinCount) {
        this.targetTeamMinCount = targetTeamMinCount;
        return this;
    }

    public void setTargetTeamMinCount(Integer targetTeamMinCount) {
        this.targetTeamMinCount = targetTeamMinCount;
    }

    public Integer getTargetTeamMaxCount() {
        return targetTeamMaxCount;
    }

    public MDeckCondition targetTeamMaxCount(Integer targetTeamMaxCount) {
        this.targetTeamMaxCount = targetTeamMaxCount;
        return this;
    }

    public void setTargetTeamMaxCount(Integer targetTeamMaxCount) {
        this.targetTeamMaxCount = targetTeamMaxCount;
    }

    public Integer getTargetActionMinCount() {
        return targetActionMinCount;
    }

    public MDeckCondition targetActionMinCount(Integer targetActionMinCount) {
        this.targetActionMinCount = targetActionMinCount;
        return this;
    }

    public void setTargetActionMinCount(Integer targetActionMinCount) {
        this.targetActionMinCount = targetActionMinCount;
    }

    public Integer getTargetActionMaxCount() {
        return targetActionMaxCount;
    }

    public MDeckCondition targetActionMaxCount(Integer targetActionMaxCount) {
        this.targetActionMaxCount = targetActionMaxCount;
        return this;
    }

    public void setTargetActionMaxCount(Integer targetActionMaxCount) {
        this.targetActionMaxCount = targetActionMaxCount;
    }

    public Integer getTargetTriggerEffectMinCount() {
        return targetTriggerEffectMinCount;
    }

    public MDeckCondition targetTriggerEffectMinCount(Integer targetTriggerEffectMinCount) {
        this.targetTriggerEffectMinCount = targetTriggerEffectMinCount;
        return this;
    }

    public void setTargetTriggerEffectMinCount(Integer targetTriggerEffectMinCount) {
        this.targetTriggerEffectMinCount = targetTriggerEffectMinCount;
    }

    public Integer getTargetTriggerEffectMaxCount() {
        return targetTriggerEffectMaxCount;
    }

    public MDeckCondition targetTriggerEffectMaxCount(Integer targetTriggerEffectMaxCount) {
        this.targetTriggerEffectMaxCount = targetTriggerEffectMaxCount;
        return this;
    }

    public void setTargetTriggerEffectMaxCount(Integer targetTriggerEffectMaxCount) {
        this.targetTriggerEffectMaxCount = targetTriggerEffectMaxCount;
    }

    public Integer getTargetCharacterIsStartingMin() {
        return targetCharacterIsStartingMin;
    }

    public MDeckCondition targetCharacterIsStartingMin(Integer targetCharacterIsStartingMin) {
        this.targetCharacterIsStartingMin = targetCharacterIsStartingMin;
        return this;
    }

    public void setTargetCharacterIsStartingMin(Integer targetCharacterIsStartingMin) {
        this.targetCharacterIsStartingMin = targetCharacterIsStartingMin;
    }

    public Integer getTargetCharacterIsStartingMax() {
        return targetCharacterIsStartingMax;
    }

    public MDeckCondition targetCharacterIsStartingMax(Integer targetCharacterIsStartingMax) {
        this.targetCharacterIsStartingMax = targetCharacterIsStartingMax;
        return this;
    }

    public void setTargetCharacterIsStartingMax(Integer targetCharacterIsStartingMax) {
        this.targetCharacterIsStartingMax = targetCharacterIsStartingMax;
    }

    public Integer getTargetPlayableCardIsStartingMin() {
        return targetPlayableCardIsStartingMin;
    }

    public MDeckCondition targetPlayableCardIsStartingMin(Integer targetPlayableCardIsStartingMin) {
        this.targetPlayableCardIsStartingMin = targetPlayableCardIsStartingMin;
        return this;
    }

    public void setTargetPlayableCardIsStartingMin(Integer targetPlayableCardIsStartingMin) {
        this.targetPlayableCardIsStartingMin = targetPlayableCardIsStartingMin;
    }

    public Integer getTargetPlayableCardIsStartingMax() {
        return targetPlayableCardIsStartingMax;
    }

    public MDeckCondition targetPlayableCardIsStartingMax(Integer targetPlayableCardIsStartingMax) {
        this.targetPlayableCardIsStartingMax = targetPlayableCardIsStartingMax;
        return this;
    }

    public void setTargetPlayableCardIsStartingMax(Integer targetPlayableCardIsStartingMax) {
        this.targetPlayableCardIsStartingMax = targetPlayableCardIsStartingMax;
    }

    public Integer getTargetRarityIsStarting() {
        return targetRarityIsStarting;
    }

    public MDeckCondition targetRarityIsStarting(Integer targetRarityIsStarting) {
        this.targetRarityIsStarting = targetRarityIsStarting;
        return this;
    }

    public void setTargetRarityIsStarting(Integer targetRarityIsStarting) {
        this.targetRarityIsStarting = targetRarityIsStarting;
    }

    public Integer getTargetAttributeIsStarting() {
        return targetAttributeIsStarting;
    }

    public MDeckCondition targetAttributeIsStarting(Integer targetAttributeIsStarting) {
        this.targetAttributeIsStarting = targetAttributeIsStarting;
        return this;
    }

    public void setTargetAttributeIsStarting(Integer targetAttributeIsStarting) {
        this.targetAttributeIsStarting = targetAttributeIsStarting;
    }

    public Integer getTargetNationalityIsStartingMin() {
        return targetNationalityIsStartingMin;
    }

    public MDeckCondition targetNationalityIsStartingMin(Integer targetNationalityIsStartingMin) {
        this.targetNationalityIsStartingMin = targetNationalityIsStartingMin;
        return this;
    }

    public void setTargetNationalityIsStartingMin(Integer targetNationalityIsStartingMin) {
        this.targetNationalityIsStartingMin = targetNationalityIsStartingMin;
    }

    public Integer getTargetNationalityIsStartingMax() {
        return targetNationalityIsStartingMax;
    }

    public MDeckCondition targetNationalityIsStartingMax(Integer targetNationalityIsStartingMax) {
        this.targetNationalityIsStartingMax = targetNationalityIsStartingMax;
        return this;
    }

    public void setTargetNationalityIsStartingMax(Integer targetNationalityIsStartingMax) {
        this.targetNationalityIsStartingMax = targetNationalityIsStartingMax;
    }

    public Integer getTargetTeamIsStartingMin() {
        return targetTeamIsStartingMin;
    }

    public MDeckCondition targetTeamIsStartingMin(Integer targetTeamIsStartingMin) {
        this.targetTeamIsStartingMin = targetTeamIsStartingMin;
        return this;
    }

    public void setTargetTeamIsStartingMin(Integer targetTeamIsStartingMin) {
        this.targetTeamIsStartingMin = targetTeamIsStartingMin;
    }

    public Integer getTargetTeamIsStartingMax() {
        return targetTeamIsStartingMax;
    }

    public MDeckCondition targetTeamIsStartingMax(Integer targetTeamIsStartingMax) {
        this.targetTeamIsStartingMax = targetTeamIsStartingMax;
        return this;
    }

    public void setTargetTeamIsStartingMax(Integer targetTeamIsStartingMax) {
        this.targetTeamIsStartingMax = targetTeamIsStartingMax;
    }

    public Integer getTargetActionIsStartingMin() {
        return targetActionIsStartingMin;
    }

    public MDeckCondition targetActionIsStartingMin(Integer targetActionIsStartingMin) {
        this.targetActionIsStartingMin = targetActionIsStartingMin;
        return this;
    }

    public void setTargetActionIsStartingMin(Integer targetActionIsStartingMin) {
        this.targetActionIsStartingMin = targetActionIsStartingMin;
    }

    public Integer getTargetActionIsStartingMax() {
        return targetActionIsStartingMax;
    }

    public MDeckCondition targetActionIsStartingMax(Integer targetActionIsStartingMax) {
        this.targetActionIsStartingMax = targetActionIsStartingMax;
        return this;
    }

    public void setTargetActionIsStartingMax(Integer targetActionIsStartingMax) {
        this.targetActionIsStartingMax = targetActionIsStartingMax;
    }

    public Integer getTargetTriggerEffectIsStartingMin() {
        return targetTriggerEffectIsStartingMin;
    }

    public MDeckCondition targetTriggerEffectIsStartingMin(Integer targetTriggerEffectIsStartingMin) {
        this.targetTriggerEffectIsStartingMin = targetTriggerEffectIsStartingMin;
        return this;
    }

    public void setTargetTriggerEffectIsStartingMin(Integer targetTriggerEffectIsStartingMin) {
        this.targetTriggerEffectIsStartingMin = targetTriggerEffectIsStartingMin;
    }

    public Integer getTargetTriggerEffectIsStartingMax() {
        return targetTriggerEffectIsStartingMax;
    }

    public MDeckCondition targetTriggerEffectIsStartingMax(Integer targetTriggerEffectIsStartingMax) {
        this.targetTriggerEffectIsStartingMax = targetTriggerEffectIsStartingMax;
        return this;
    }

    public void setTargetTriggerEffectIsStartingMax(Integer targetTriggerEffectIsStartingMax) {
        this.targetTriggerEffectIsStartingMax = targetTriggerEffectIsStartingMax;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MDeckCondition)) {
            return false;
        }
        return id != null && id.equals(((MDeckCondition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MDeckCondition{" +
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
