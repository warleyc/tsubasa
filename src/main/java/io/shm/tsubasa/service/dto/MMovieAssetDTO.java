package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMovieAsset} entity.
 */
public class MMovieAssetDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer lang;

    
    @Lob
    private String name;

    @NotNull
    private Integer size;

    @NotNull
    private Integer version;

    @NotNull
    private Integer type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLang() {
        return lang;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MMovieAssetDTO mMovieAssetDTO = (MMovieAssetDTO) o;
        if (mMovieAssetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMovieAssetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMovieAssetDTO{" +
            "id=" + getId() +
            ", lang=" + getLang() +
            ", name='" + getName() + "'" +
            ", size=" + getSize() +
            ", version=" + getVersion() +
            ", type=" + getType() +
            "}";
    }
}
