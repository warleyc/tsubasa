package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MStoryResourceMapping.
 */
@Entity
@Table(name = "m_story_resource_mapping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MStoryResourceMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "lang", nullable = false)
    private Integer lang;

    
    @Lob
    @Column(name = "script_name", nullable = false)
    private String scriptName;

    
    @Lob
    @Column(name = "ids", nullable = false)
    private String ids;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLang() {
        return lang;
    }

    public MStoryResourceMapping lang(Integer lang) {
        this.lang = lang;
        return this;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public String getScriptName() {
        return scriptName;
    }

    public MStoryResourceMapping scriptName(String scriptName) {
        this.scriptName = scriptName;
        return this;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getIds() {
        return ids;
    }

    public MStoryResourceMapping ids(String ids) {
        this.ids = ids;
        return this;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MStoryResourceMapping)) {
            return false;
        }
        return id != null && id.equals(((MStoryResourceMapping) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MStoryResourceMapping{" +
            "id=" + getId() +
            ", lang=" + getLang() +
            ", scriptName='" + getScriptName() + "'" +
            ", ids='" + getIds() + "'" +
            "}";
    }
}
