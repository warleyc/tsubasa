package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMarathonEffectiveCard} entity.
 */
public class MMarathonEffectiveCardDTO implements Serializable {

    private Long id;

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

        MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO = (MMarathonEffectiveCardDTO) o;
        if (mMarathonEffectiveCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMarathonEffectiveCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMarathonEffectiveCardDTO{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", playableCardId=" + getPlayableCardId() +
            ", rate=" + getRate() +
            "}";
    }
}
