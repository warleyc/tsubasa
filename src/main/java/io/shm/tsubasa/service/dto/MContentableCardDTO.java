package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MContentableCard} entity.
 */
public class MContentableCardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer playableCardId;

    @NotNull
    private Integer level;

    @NotNull
    private Integer actionMainLevel;

    @NotNull
    private Integer actionSub1Level;

    @NotNull
    private Integer actionSub2Level;

    @NotNull
    private Integer actionSub3Level;

    @NotNull
    private Integer actionSub4Level;

    @NotNull
    private Integer plusRate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlayableCardId() {
        return playableCardId;
    }

    public void setPlayableCardId(Integer playableCardId) {
        this.playableCardId = playableCardId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getActionMainLevel() {
        return actionMainLevel;
    }

    public void setActionMainLevel(Integer actionMainLevel) {
        this.actionMainLevel = actionMainLevel;
    }

    public Integer getActionSub1Level() {
        return actionSub1Level;
    }

    public void setActionSub1Level(Integer actionSub1Level) {
        this.actionSub1Level = actionSub1Level;
    }

    public Integer getActionSub2Level() {
        return actionSub2Level;
    }

    public void setActionSub2Level(Integer actionSub2Level) {
        this.actionSub2Level = actionSub2Level;
    }

    public Integer getActionSub3Level() {
        return actionSub3Level;
    }

    public void setActionSub3Level(Integer actionSub3Level) {
        this.actionSub3Level = actionSub3Level;
    }

    public Integer getActionSub4Level() {
        return actionSub4Level;
    }

    public void setActionSub4Level(Integer actionSub4Level) {
        this.actionSub4Level = actionSub4Level;
    }

    public Integer getPlusRate() {
        return plusRate;
    }

    public void setPlusRate(Integer plusRate) {
        this.plusRate = plusRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MContentableCardDTO mContentableCardDTO = (MContentableCardDTO) o;
        if (mContentableCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mContentableCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MContentableCardDTO{" +
            "id=" + getId() +
            ", playableCardId=" + getPlayableCardId() +
            ", level=" + getLevel() +
            ", actionMainLevel=" + getActionMainLevel() +
            ", actionSub1Level=" + getActionSub1Level() +
            ", actionSub2Level=" + getActionSub2Level() +
            ", actionSub3Level=" + getActionSub3Level() +
            ", actionSub4Level=" + getActionSub4Level() +
            ", plusRate=" + getPlusRate() +
            "}";
    }
}
