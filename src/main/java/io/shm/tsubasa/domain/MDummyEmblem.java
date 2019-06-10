package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MDummyEmblem.
 */
@Entity
@Table(name = "m_dummy_emblem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MDummyEmblem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "background_id", nullable = false)
    private Integer backgroundId;

    
    @Lob
    @Column(name = "background_color", nullable = false)
    private String backgroundColor;

    @Column(name = "upper_id")
    private Integer upperId;

    @Lob
    @Column(name = "upper_color")
    private String upperColor;

    @NotNull
    @Column(name = "middle_id", nullable = false)
    private Integer middleId;

    
    @Lob
    @Column(name = "middle_color", nullable = false)
    private String middleColor;

    @Column(name = "lower_id")
    private Integer lowerId;

    @Lob
    @Column(name = "lower_color")
    private String lowerColor;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mDummyEmblems")
    private MEmblemParts id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBackgroundId() {
        return backgroundId;
    }

    public MDummyEmblem backgroundId(Integer backgroundId) {
        this.backgroundId = backgroundId;
        return this;
    }

    public void setBackgroundId(Integer backgroundId) {
        this.backgroundId = backgroundId;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public MDummyEmblem backgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getUpperId() {
        return upperId;
    }

    public MDummyEmblem upperId(Integer upperId) {
        this.upperId = upperId;
        return this;
    }

    public void setUpperId(Integer upperId) {
        this.upperId = upperId;
    }

    public String getUpperColor() {
        return upperColor;
    }

    public MDummyEmblem upperColor(String upperColor) {
        this.upperColor = upperColor;
        return this;
    }

    public void setUpperColor(String upperColor) {
        this.upperColor = upperColor;
    }

    public Integer getMiddleId() {
        return middleId;
    }

    public MDummyEmblem middleId(Integer middleId) {
        this.middleId = middleId;
        return this;
    }

    public void setMiddleId(Integer middleId) {
        this.middleId = middleId;
    }

    public String getMiddleColor() {
        return middleColor;
    }

    public MDummyEmblem middleColor(String middleColor) {
        this.middleColor = middleColor;
        return this;
    }

    public void setMiddleColor(String middleColor) {
        this.middleColor = middleColor;
    }

    public Integer getLowerId() {
        return lowerId;
    }

    public MDummyEmblem lowerId(Integer lowerId) {
        this.lowerId = lowerId;
        return this;
    }

    public void setLowerId(Integer lowerId) {
        this.lowerId = lowerId;
    }

    public String getLowerColor() {
        return lowerColor;
    }

    public MDummyEmblem lowerColor(String lowerColor) {
        this.lowerColor = lowerColor;
        return this;
    }

    public void setLowerColor(String lowerColor) {
        this.lowerColor = lowerColor;
    }

    public MEmblemParts getId() {
        return id;
    }

    public MDummyEmblem id(MEmblemParts mEmblemParts) {
        this.id = mEmblemParts;
        return this;
    }

    public void setId(MEmblemParts mEmblemParts) {
        this.id = mEmblemParts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MDummyEmblem)) {
            return false;
        }
        return id != null && id.equals(((MDummyEmblem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MDummyEmblem{" +
            "id=" + getId() +
            ", backgroundId=" + getBackgroundId() +
            ", backgroundColor='" + getBackgroundColor() + "'" +
            ", upperId=" + getUpperId() +
            ", upperColor='" + getUpperColor() + "'" +
            ", middleId=" + getMiddleId() +
            ", middleColor='" + getMiddleColor() + "'" +
            ", lowerId=" + getLowerId() +
            ", lowerColor='" + getLowerColor() + "'" +
            "}";
    }
}
