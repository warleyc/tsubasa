package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTeam} entity.
 */
public class MTeamDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTeamDTO mTeamDTO = (MTeamDTO) o;
        if (mTeamDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTeamDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTeamDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
