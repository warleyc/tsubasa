package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionBall.
 */
@Entity
@Table(name = "m_gacha_rendition_ball")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionBall implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rendition_id", nullable = false)
    private Integer renditionId;

    @NotNull
    @Column(name = "is_ssr", nullable = false)
    private Integer isSsr;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    
    @Lob
    @Column(name = "ball_texture_name", nullable = false)
    private String ballTextureName;

    
    @Lob
    @Column(name = "trajectory_normal_texture_name", nullable = false)
    private String trajectoryNormalTextureName;

    
    @Lob
    @Column(name = "trajectory_gold_texture_name", nullable = false)
    private String trajectoryGoldTextureName;

    
    @Lob
    @Column(name = "trajectory_rainbow_texture_name", nullable = false)
    private String trajectoryRainbowTextureName;

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

    public MGachaRenditionBall renditionId(Integer renditionId) {
        this.renditionId = renditionId;
        return this;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public MGachaRenditionBall isSsr(Integer isSsr) {
        this.isSsr = isSsr;
        return this;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionBall weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBallTextureName() {
        return ballTextureName;
    }

    public MGachaRenditionBall ballTextureName(String ballTextureName) {
        this.ballTextureName = ballTextureName;
        return this;
    }

    public void setBallTextureName(String ballTextureName) {
        this.ballTextureName = ballTextureName;
    }

    public String getTrajectoryNormalTextureName() {
        return trajectoryNormalTextureName;
    }

    public MGachaRenditionBall trajectoryNormalTextureName(String trajectoryNormalTextureName) {
        this.trajectoryNormalTextureName = trajectoryNormalTextureName;
        return this;
    }

    public void setTrajectoryNormalTextureName(String trajectoryNormalTextureName) {
        this.trajectoryNormalTextureName = trajectoryNormalTextureName;
    }

    public String getTrajectoryGoldTextureName() {
        return trajectoryGoldTextureName;
    }

    public MGachaRenditionBall trajectoryGoldTextureName(String trajectoryGoldTextureName) {
        this.trajectoryGoldTextureName = trajectoryGoldTextureName;
        return this;
    }

    public void setTrajectoryGoldTextureName(String trajectoryGoldTextureName) {
        this.trajectoryGoldTextureName = trajectoryGoldTextureName;
    }

    public String getTrajectoryRainbowTextureName() {
        return trajectoryRainbowTextureName;
    }

    public MGachaRenditionBall trajectoryRainbowTextureName(String trajectoryRainbowTextureName) {
        this.trajectoryRainbowTextureName = trajectoryRainbowTextureName;
        return this;
    }

    public void setTrajectoryRainbowTextureName(String trajectoryRainbowTextureName) {
        this.trajectoryRainbowTextureName = trajectoryRainbowTextureName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionBall)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionBall) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionBall{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", isSsr=" + getIsSsr() +
            ", weight=" + getWeight() +
            ", ballTextureName='" + getBallTextureName() + "'" +
            ", trajectoryNormalTextureName='" + getTrajectoryNormalTextureName() + "'" +
            ", trajectoryGoldTextureName='" + getTrajectoryGoldTextureName() + "'" +
            ", trajectoryRainbowTextureName='" + getTrajectoryRainbowTextureName() + "'" +
            "}";
    }
}
