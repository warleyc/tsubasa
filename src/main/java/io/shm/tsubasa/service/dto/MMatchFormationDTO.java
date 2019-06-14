package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMatchFormation} entity.
 */
public class MMatchFormationDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer position1;

    @NotNull
    private Integer position2;

    @NotNull
    private Integer position3;

    @NotNull
    private Integer position4;

    @NotNull
    private Integer position5;

    @NotNull
    private Integer position6;

    @NotNull
    private Integer position7;

    @NotNull
    private Integer position8;

    @NotNull
    private Integer position9;

    @NotNull
    private Integer position10;

    @NotNull
    private Integer position11;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition1() {
        return position1;
    }

    public void setPosition1(Integer position1) {
        this.position1 = position1;
    }

    public Integer getPosition2() {
        return position2;
    }

    public void setPosition2(Integer position2) {
        this.position2 = position2;
    }

    public Integer getPosition3() {
        return position3;
    }

    public void setPosition3(Integer position3) {
        this.position3 = position3;
    }

    public Integer getPosition4() {
        return position4;
    }

    public void setPosition4(Integer position4) {
        this.position4 = position4;
    }

    public Integer getPosition5() {
        return position5;
    }

    public void setPosition5(Integer position5) {
        this.position5 = position5;
    }

    public Integer getPosition6() {
        return position6;
    }

    public void setPosition6(Integer position6) {
        this.position6 = position6;
    }

    public Integer getPosition7() {
        return position7;
    }

    public void setPosition7(Integer position7) {
        this.position7 = position7;
    }

    public Integer getPosition8() {
        return position8;
    }

    public void setPosition8(Integer position8) {
        this.position8 = position8;
    }

    public Integer getPosition9() {
        return position9;
    }

    public void setPosition9(Integer position9) {
        this.position9 = position9;
    }

    public Integer getPosition10() {
        return position10;
    }

    public void setPosition10(Integer position10) {
        this.position10 = position10;
    }

    public Integer getPosition11() {
        return position11;
    }

    public void setPosition11(Integer position11) {
        this.position11 = position11;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MMatchFormationDTO mMatchFormationDTO = (MMatchFormationDTO) o;
        if (mMatchFormationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMatchFormationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMatchFormationDTO{" +
            "id=" + getId() +
            ", position1=" + getPosition1() +
            ", position2=" + getPosition2() +
            ", position3=" + getPosition3() +
            ", position4=" + getPosition4() +
            ", position5=" + getPosition5() +
            ", position6=" + getPosition6() +
            ", position7=" + getPosition7() +
            ", position8=" + getPosition8() +
            ", position9=" + getPosition9() +
            ", position10=" + getPosition10() +
            ", position11=" + getPosition11() +
            "}";
    }
}
