package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInCharacterNum} entity.
 */
public class MGachaRenditionBeforeShootCutInCharacterNumDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer isManySsr;

    @NotNull
    private Integer isSsr;

    @NotNull
    private Integer pattern;

    @NotNull
    private Integer weight;

    @NotNull
    private Integer num;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsManySsr() {
        return isManySsr;
    }

    public void setIsManySsr(Integer isManySsr) {
        this.isManySsr = isManySsr;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getPattern() {
        return pattern;
    }

    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO = (MGachaRenditionBeforeShootCutInCharacterNumDTO) o;
        if (mGachaRenditionBeforeShootCutInCharacterNumDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionBeforeShootCutInCharacterNumDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionBeforeShootCutInCharacterNumDTO{" +
            "id=" + getId() +
            ", isManySsr=" + getIsManySsr() +
            ", isSsr=" + getIsSsr() +
            ", pattern=" + getPattern() +
            ", weight=" + getWeight() +
            ", num=" + getNum() +
            "}";
    }
}
