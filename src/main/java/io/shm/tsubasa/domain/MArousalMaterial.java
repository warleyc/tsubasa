package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MArousalMaterial.
 */
@Entity
@Table(name = "m_arousal_material")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MArousalMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "content_type", nullable = false)
    private Integer contentType;

    @NotNull
    @Column(name = "content_id", nullable = false)
    private Integer contentId;

    @NotNull
    @Column(name = "content_amount", nullable = false)
    private Integer contentAmount;

    @Column(name = "main_action_level")
    private Integer mainActionLevel;

    @Lob
    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public MArousalMaterial groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public MArousalMaterial contentType(Integer contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public MArousalMaterial contentId(Integer contentId) {
        this.contentId = contentId;
        return this;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentAmount() {
        return contentAmount;
    }

    public MArousalMaterial contentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
        return this;
    }

    public void setContentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
    }

    public Integer getMainActionLevel() {
        return mainActionLevel;
    }

    public MArousalMaterial mainActionLevel(Integer mainActionLevel) {
        this.mainActionLevel = mainActionLevel;
        return this;
    }

    public void setMainActionLevel(Integer mainActionLevel) {
        this.mainActionLevel = mainActionLevel;
    }

    public String getDescription() {
        return description;
    }

    public MArousalMaterial description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MArousalMaterial)) {
            return false;
        }
        return id != null && id.equals(((MArousalMaterial) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MArousalMaterial{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            ", mainActionLevel=" + getMainActionLevel() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
