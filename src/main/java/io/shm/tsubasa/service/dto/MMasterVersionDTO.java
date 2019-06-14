package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMasterVersion} entity.
 */
public class MMasterVersionDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    @NotNull
    private Integer version;

    @Lob
    private String path;

    private Integer size;


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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MMasterVersionDTO mMasterVersionDTO = (MMasterVersionDTO) o;
        if (mMasterVersionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMasterVersionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMasterVersionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", version=" + getVersion() +
            ", path='" + getPath() + "'" +
            ", size=" + getSize() +
            "}";
    }
}
