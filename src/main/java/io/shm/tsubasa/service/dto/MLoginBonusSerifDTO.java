package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MLoginBonusSerif} entity.
 */
public class MLoginBonusSerifDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer serifId;

    @Lob
    private String serif1;

    @Lob
    private String serif2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSerifId() {
        return serifId;
    }

    public void setSerifId(Integer serifId) {
        this.serifId = serifId;
    }

    public String getSerif1() {
        return serif1;
    }

    public void setSerif1(String serif1) {
        this.serif1 = serif1;
    }

    public String getSerif2() {
        return serif2;
    }

    public void setSerif2(String serif2) {
        this.serif2 = serif2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MLoginBonusSerifDTO mLoginBonusSerifDTO = (MLoginBonusSerifDTO) o;
        if (mLoginBonusSerifDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mLoginBonusSerifDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MLoginBonusSerifDTO{" +
            "id=" + getId() +
            ", serifId=" + getSerifId() +
            ", serif1='" + getSerif1() + "'" +
            ", serif2='" + getSerif2() + "'" +
            "}";
    }
}
