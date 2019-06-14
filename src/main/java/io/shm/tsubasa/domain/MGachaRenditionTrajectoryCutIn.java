package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionTrajectoryCutIn.
 */
@Entity
@Table(name = "m_gacha_rendition_trajectory_cut_in")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionTrajectoryCutIn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rendition_id", nullable = false)
    private Integer renditionId;

    @NotNull
    @Column(name = "trajectory_type", nullable = false)
    private Integer trajectoryType;

    
    @Lob
    @Column(name = "sprite_name", nullable = false)
    private String spriteName;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    
    @Lob
    @Column(name = "voice", nullable = false)
    private String voice;

    @NotNull
    @Column(name = "except_kicker_id", nullable = false)
    private Integer exceptKickerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRenditionId() {
        return renditionId;
    }

    public MGachaRenditionTrajectoryCutIn renditionId(Integer renditionId) {
        this.renditionId = renditionId;
        return this;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public Integer getTrajectoryType() {
        return trajectoryType;
    }

    public MGachaRenditionTrajectoryCutIn trajectoryType(Integer trajectoryType) {
        this.trajectoryType = trajectoryType;
        return this;
    }

    public void setTrajectoryType(Integer trajectoryType) {
        this.trajectoryType = trajectoryType;
    }

    public String getSpriteName() {
        return spriteName;
    }

    public MGachaRenditionTrajectoryCutIn spriteName(String spriteName) {
        this.spriteName = spriteName;
        return this;
    }

    public void setSpriteName(String spriteName) {
        this.spriteName = spriteName;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionTrajectoryCutIn weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getVoice() {
        return voice;
    }

    public MGachaRenditionTrajectoryCutIn voice(String voice) {
        this.voice = voice;
        return this;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public Integer getExceptKickerId() {
        return exceptKickerId;
    }

    public MGachaRenditionTrajectoryCutIn exceptKickerId(Integer exceptKickerId) {
        this.exceptKickerId = exceptKickerId;
        return this;
    }

    public void setExceptKickerId(Integer exceptKickerId) {
        this.exceptKickerId = exceptKickerId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionTrajectoryCutIn)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionTrajectoryCutIn) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionTrajectoryCutIn{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", trajectoryType=" + getTrajectoryType() +
            ", spriteName='" + getSpriteName() + "'" +
            ", weight=" + getWeight() +
            ", voice='" + getVoice() + "'" +
            ", exceptKickerId=" + getExceptKickerId() +
            "}";
    }
}
