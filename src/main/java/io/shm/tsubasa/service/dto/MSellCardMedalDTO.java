package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MSellCardMedal} entity.
 */
public class MSellCardMedalDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer medalId;

    @NotNull
    private Integer amount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMedalId() {
        return medalId;
    }

    public void setMedalId(Integer medalId) {
        this.medalId = medalId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MSellCardMedalDTO mSellCardMedalDTO = (MSellCardMedalDTO) o;
        if (mSellCardMedalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mSellCardMedalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MSellCardMedalDTO{" +
            "id=" + getId() +
            ", medalId=" + getMedalId() +
            ", amount=" + getAmount() +
            "}";
    }
}
