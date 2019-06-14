package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGoalEffectiveCard} entity.
 */
public class MGoalEffectiveCardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer eventType;

    @NotNull
    private Integer eventId;

    @NotNull
    private Integer playableCardId;

    @NotNull
    private Integer rate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getPlayableCardId() {
        return playableCardId;
    }

    public void setPlayableCardId(Integer playableCardId) {
        this.playableCardId = playableCardId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGoalEffectiveCardDTO mGoalEffectiveCardDTO = (MGoalEffectiveCardDTO) o;
        if (mGoalEffectiveCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGoalEffectiveCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGoalEffectiveCardDTO{" +
            "id=" + getId() +
            ", eventType=" + getEventType() +
            ", eventId=" + getEventId() +
            ", playableCardId=" + getPlayableCardId() +
            ", rate=" + getRate() +
            "}";
    }
}
