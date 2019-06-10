package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCutShootOrbit} entity.
 */
public class MCutShootOrbitDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer shootOrbit;

    @NotNull
    private Integer shootResult;

    
    @Lob
    private String offenseSequenceText;

    
    @Lob
    private String defenseSequenceText;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getShootOrbit() {
        return shootOrbit;
    }

    public void setShootOrbit(Integer shootOrbit) {
        this.shootOrbit = shootOrbit;
    }

    public Integer getShootResult() {
        return shootResult;
    }

    public void setShootResult(Integer shootResult) {
        this.shootResult = shootResult;
    }

    public String getOffenseSequenceText() {
        return offenseSequenceText;
    }

    public void setOffenseSequenceText(String offenseSequenceText) {
        this.offenseSequenceText = offenseSequenceText;
    }

    public String getDefenseSequenceText() {
        return defenseSequenceText;
    }

    public void setDefenseSequenceText(String defenseSequenceText) {
        this.defenseSequenceText = defenseSequenceText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCutShootOrbitDTO mCutShootOrbitDTO = (MCutShootOrbitDTO) o;
        if (mCutShootOrbitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCutShootOrbitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCutShootOrbitDTO{" +
            "id=" + getId() +
            ", shootOrbit=" + getShootOrbit() +
            ", shootResult=" + getShootResult() +
            ", offenseSequenceText='" + getOffenseSequenceText() + "'" +
            ", defenseSequenceText='" + getDefenseSequenceText() + "'" +
            "}";
    }
}
