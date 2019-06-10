package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDbMapping} entity.
 */
public class MDbMappingDTO implements Serializable {

    private Long id;

    
    @Lob
    private String fileName;

    
    @Lob
    private String dbName;

    
    @Lob
    private String path;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MDbMappingDTO mDbMappingDTO = (MDbMappingDTO) o;
        if (mDbMappingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDbMappingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDbMappingDTO{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", dbName='" + getDbName() + "'" +
            ", path='" + getPath() + "'" +
            "}";
    }
}
