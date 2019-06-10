package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDefine} entity.
 */
public class MDefineDTO implements Serializable {

    private Long id;

    
    @Lob
    private String key;

    @NotNull
    private Integer value;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MDefineDTO mDefineDTO = (MDefineDTO) o;
        if (mDefineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDefineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDefineDTO{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", value=" + getValue() +
            "}";
    }
}
