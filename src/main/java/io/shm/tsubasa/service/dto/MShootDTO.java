package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MShoot} entity.
 */
public class MShootDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer angleDecayType;

    @NotNull
    private Integer shootOrbit;

    @NotNull
    private Integer judgementId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAngleDecayType() {
        return angleDecayType;
    }

    public void setAngleDecayType(Integer angleDecayType) {
        this.angleDecayType = angleDecayType;
    }

    public Integer getShootOrbit() {
        return shootOrbit;
    }

    public void setShootOrbit(Integer shootOrbit) {
        this.shootOrbit = shootOrbit;
    }

    public Integer getJudgementId() {
        return judgementId;
    }

    public void setJudgementId(Integer judgementId) {
        this.judgementId = judgementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MShootDTO mShootDTO = (MShootDTO) o;
        if (mShootDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mShootDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MShootDTO{" +
            "id=" + getId() +
            ", angleDecayType=" + getAngleDecayType() +
            ", shootOrbit=" + getShootOrbit() +
            ", judgementId=" + getJudgementId() +
            "}";
    }
}
