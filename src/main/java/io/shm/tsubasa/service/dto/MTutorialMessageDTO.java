package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTutorialMessage} entity.
 */
public class MTutorialMessageDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer step;

    @NotNull
    private Integer orderNum;

    @NotNull
    private Integer position;

    
    @Lob
    private String message;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTutorialMessageDTO mTutorialMessageDTO = (MTutorialMessageDTO) o;
        if (mTutorialMessageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTutorialMessageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTutorialMessageDTO{" +
            "id=" + getId() +
            ", step=" + getStep() +
            ", orderNum=" + getOrderNum() +
            ", position=" + getPosition() +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
