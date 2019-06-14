package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MStoryResourceMapping} entity.
 */
public class MStoryResourceMappingDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer lang;

    
    @Lob
    private String scriptName;

    
    @Lob
    private String ids;


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

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MStoryResourceMappingDTO mStoryResourceMappingDTO = (MStoryResourceMappingDTO) o;
        if (mStoryResourceMappingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mStoryResourceMappingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MStoryResourceMappingDTO{" +
            "id=" + getId() +
            ", lang=" + getLang() +
            ", scriptName='" + getScriptName() + "'" +
            ", ids='" + getIds() + "'" +
            "}";
    }
}
