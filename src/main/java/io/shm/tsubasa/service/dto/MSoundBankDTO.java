package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MSoundBank} entity.
 */
public class MSoundBankDTO implements Serializable {

    private Long id;

    
    @Lob
    private String path;

    
    @Lob
    private String pf;

    @NotNull
    private Integer version;

    @NotNull
    private Integer fileSize;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MSoundBankDTO mSoundBankDTO = (MSoundBankDTO) o;
        if (mSoundBankDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mSoundBankDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MSoundBankDTO{" +
            "id=" + getId() +
            ", path='" + getPath() + "'" +
            ", pf='" + getPf() + "'" +
            ", version=" + getVersion() +
            ", fileSize=" + getFileSize() +
            "}";
    }
}
