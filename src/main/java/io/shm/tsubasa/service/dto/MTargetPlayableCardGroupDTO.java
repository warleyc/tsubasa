package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTargetPlayableCardGroup} entity.
 */
public class MTargetPlayableCardGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer cardId;

    @NotNull
    private Integer isShowThumbnail;


    private Long idId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getIsShowThumbnail() {
        return isShowThumbnail;
    }

    public void setIsShowThumbnail(Integer isShowThumbnail) {
        this.isShowThumbnail = isShowThumbnail;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long mPlayableCardId) {
        this.idId = mPlayableCardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO = (MTargetPlayableCardGroupDTO) o;
        if (mTargetPlayableCardGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTargetPlayableCardGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTargetPlayableCardGroupDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", cardId=" + getCardId() +
            ", isShowThumbnail=" + getIsShowThumbnail() +
            ", id=" + getIdId() +
            "}";
    }
}
