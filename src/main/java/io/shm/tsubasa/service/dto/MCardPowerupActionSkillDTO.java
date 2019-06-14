package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCardPowerupActionSkill} entity.
 */
public class MCardPowerupActionSkillDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    
    @Lob
    private String shortName;

    
    @Lob
    private String description;

    @NotNull
    private Integer rarity;

    @NotNull
    private Integer attribute;

    @NotNull
    private Integer actionRarity;

    @NotNull
    private Integer gainActionExp;

    @NotNull
    private Integer coin;

    private Integer sellMedalId;

    @NotNull
    private Integer thumbnailAssetsId;

    @NotNull
    private Integer cardIllustAssetsId;

    private Integer targetActionCommandType;

    private Integer targetCharacterId;


    private Long mcardthumbnailassetsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public Integer getActionRarity() {
        return actionRarity;
    }

    public void setActionRarity(Integer actionRarity) {
        this.actionRarity = actionRarity;
    }

    public Integer getGainActionExp() {
        return gainActionExp;
    }

    public void setGainActionExp(Integer gainActionExp) {
        this.gainActionExp = gainActionExp;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getSellMedalId() {
        return sellMedalId;
    }

    public void setSellMedalId(Integer sellMedalId) {
        this.sellMedalId = sellMedalId;
    }

    public Integer getThumbnailAssetsId() {
        return thumbnailAssetsId;
    }

    public void setThumbnailAssetsId(Integer thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
    }

    public Integer getCardIllustAssetsId() {
        return cardIllustAssetsId;
    }

    public void setCardIllustAssetsId(Integer cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
    }

    public Integer getTargetActionCommandType() {
        return targetActionCommandType;
    }

    public void setTargetActionCommandType(Integer targetActionCommandType) {
        this.targetActionCommandType = targetActionCommandType;
    }

    public Integer getTargetCharacterId() {
        return targetCharacterId;
    }

    public void setTargetCharacterId(Integer targetCharacterId) {
        this.targetCharacterId = targetCharacterId;
    }

    public Long getMcardthumbnailassetsId() {
        return mcardthumbnailassetsId;
    }

    public void setMcardthumbnailassetsId(Long mCardThumbnailAssetsId) {
        this.mcardthumbnailassetsId = mCardThumbnailAssetsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = (MCardPowerupActionSkillDTO) o;
        if (mCardPowerupActionSkillDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCardPowerupActionSkillDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCardPowerupActionSkillDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", description='" + getDescription() + "'" +
            ", rarity=" + getRarity() +
            ", attribute=" + getAttribute() +
            ", actionRarity=" + getActionRarity() +
            ", gainActionExp=" + getGainActionExp() +
            ", coin=" + getCoin() +
            ", sellMedalId=" + getSellMedalId() +
            ", thumbnailAssetsId=" + getThumbnailAssetsId() +
            ", cardIllustAssetsId=" + getCardIllustAssetsId() +
            ", targetActionCommandType=" + getTargetActionCommandType() +
            ", targetCharacterId=" + getTargetCharacterId() +
            ", mcardthumbnailassets=" + getMcardthumbnailassetsId() +
            "}";
    }
}
