package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MRecommendShopMessage} entity.
 */
public class MRecommendShopMessageDTO implements Serializable {

    private Long id;

    
    @Lob
    private String message;

    @NotNull
    private Integer hasSalesPeriod;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHasSalesPeriod() {
        return hasSalesPeriod;
    }

    public void setHasSalesPeriod(Integer hasSalesPeriod) {
        this.hasSalesPeriod = hasSalesPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MRecommendShopMessageDTO mRecommendShopMessageDTO = (MRecommendShopMessageDTO) o;
        if (mRecommendShopMessageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mRecommendShopMessageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MRecommendShopMessageDTO{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", hasSalesPeriod=" + getHasSalesPeriod() +
            "}";
    }
}
