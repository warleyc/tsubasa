package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MActionSkillHolderCardContent} entity.
 */
public class MActionSkillHolderCardContentDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer targetCharaId;

    @NotNull
    private Integer actionMasterId;

    @NotNull
    private Integer actionSkillExp;

    
    @Lob
    private String name;

    
    @Lob
    private String description;


    private Long mcharacterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetCharaId() {
        return targetCharaId;
    }

    public void setTargetCharaId(Integer targetCharaId) {
        this.targetCharaId = targetCharaId;
    }

    public Integer getActionMasterId() {
        return actionMasterId;
    }

    public void setActionMasterId(Integer actionMasterId) {
        this.actionMasterId = actionMasterId;
    }

    public Integer getActionSkillExp() {
        return actionSkillExp;
    }

    public void setActionSkillExp(Integer actionSkillExp) {
        this.actionSkillExp = actionSkillExp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMcharacterId() {
        return mcharacterId;
    }

    public void setMcharacterId(Long mCharacterId) {
        this.mcharacterId = mCharacterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO = (MActionSkillHolderCardContentDTO) o;
        if (mActionSkillHolderCardContentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mActionSkillHolderCardContentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MActionSkillHolderCardContentDTO{" +
            "id=" + getId() +
            ", targetCharaId=" + getTargetCharaId() +
            ", actionMasterId=" + getActionMasterId() +
            ", actionSkillExp=" + getActionSkillExp() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", mcharacter=" + getMcharacterId() +
            "}";
    }
}
