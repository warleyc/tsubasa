package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MNpcPersonality.
 */
@Entity
@Table(name = "m_npc_personality")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MNpcPersonality implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "passing_target_rate", nullable = false)
    private Integer passingTargetRate;

    @NotNull
    @Column(name = "action_skill_rate", nullable = false)
    private Integer actionSkillRate;

    @NotNull
    @Column(name = "dribble_magnification", nullable = false)
    private Integer dribbleMagnification;

    @NotNull
    @Column(name = "passing_magnification", nullable = false)
    private Integer passingMagnification;

    @NotNull
    @Column(name = "onetwo_magnification", nullable = false)
    private Integer onetwoMagnification;

    @NotNull
    @Column(name = "shoot_magnification", nullable = false)
    private Integer shootMagnification;

    @NotNull
    @Column(name = "volley_shoot_magnification", nullable = false)
    private Integer volleyShootMagnification;

    @NotNull
    @Column(name = "heading_shoot_magnification", nullable = false)
    private Integer headingShootMagnification;

    @NotNull
    @Column(name = "tackle_magnification", nullable = false)
    private Integer tackleMagnification;

    @NotNull
    @Column(name = "block_magnification", nullable = false)
    private Integer blockMagnification;

    @NotNull
    @Column(name = "pass_cut_magnification", nullable = false)
    private Integer passCutMagnification;

    @NotNull
    @Column(name = "clear_magnification", nullable = false)
    private Integer clearMagnification;

    @NotNull
    @Column(name = "compete_magnification", nullable = false)
    private Integer competeMagnification;

    @NotNull
    @Column(name = "trap_magnification", nullable = false)
    private Integer trapMagnification;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPassingTargetRate() {
        return passingTargetRate;
    }

    public MNpcPersonality passingTargetRate(Integer passingTargetRate) {
        this.passingTargetRate = passingTargetRate;
        return this;
    }

    public void setPassingTargetRate(Integer passingTargetRate) {
        this.passingTargetRate = passingTargetRate;
    }

    public Integer getActionSkillRate() {
        return actionSkillRate;
    }

    public MNpcPersonality actionSkillRate(Integer actionSkillRate) {
        this.actionSkillRate = actionSkillRate;
        return this;
    }

    public void setActionSkillRate(Integer actionSkillRate) {
        this.actionSkillRate = actionSkillRate;
    }

    public Integer getDribbleMagnification() {
        return dribbleMagnification;
    }

    public MNpcPersonality dribbleMagnification(Integer dribbleMagnification) {
        this.dribbleMagnification = dribbleMagnification;
        return this;
    }

    public void setDribbleMagnification(Integer dribbleMagnification) {
        this.dribbleMagnification = dribbleMagnification;
    }

    public Integer getPassingMagnification() {
        return passingMagnification;
    }

    public MNpcPersonality passingMagnification(Integer passingMagnification) {
        this.passingMagnification = passingMagnification;
        return this;
    }

    public void setPassingMagnification(Integer passingMagnification) {
        this.passingMagnification = passingMagnification;
    }

    public Integer getOnetwoMagnification() {
        return onetwoMagnification;
    }

    public MNpcPersonality onetwoMagnification(Integer onetwoMagnification) {
        this.onetwoMagnification = onetwoMagnification;
        return this;
    }

    public void setOnetwoMagnification(Integer onetwoMagnification) {
        this.onetwoMagnification = onetwoMagnification;
    }

    public Integer getShootMagnification() {
        return shootMagnification;
    }

    public MNpcPersonality shootMagnification(Integer shootMagnification) {
        this.shootMagnification = shootMagnification;
        return this;
    }

    public void setShootMagnification(Integer shootMagnification) {
        this.shootMagnification = shootMagnification;
    }

    public Integer getVolleyShootMagnification() {
        return volleyShootMagnification;
    }

    public MNpcPersonality volleyShootMagnification(Integer volleyShootMagnification) {
        this.volleyShootMagnification = volleyShootMagnification;
        return this;
    }

    public void setVolleyShootMagnification(Integer volleyShootMagnification) {
        this.volleyShootMagnification = volleyShootMagnification;
    }

    public Integer getHeadingShootMagnification() {
        return headingShootMagnification;
    }

    public MNpcPersonality headingShootMagnification(Integer headingShootMagnification) {
        this.headingShootMagnification = headingShootMagnification;
        return this;
    }

    public void setHeadingShootMagnification(Integer headingShootMagnification) {
        this.headingShootMagnification = headingShootMagnification;
    }

    public Integer getTackleMagnification() {
        return tackleMagnification;
    }

    public MNpcPersonality tackleMagnification(Integer tackleMagnification) {
        this.tackleMagnification = tackleMagnification;
        return this;
    }

    public void setTackleMagnification(Integer tackleMagnification) {
        this.tackleMagnification = tackleMagnification;
    }

    public Integer getBlockMagnification() {
        return blockMagnification;
    }

    public MNpcPersonality blockMagnification(Integer blockMagnification) {
        this.blockMagnification = blockMagnification;
        return this;
    }

    public void setBlockMagnification(Integer blockMagnification) {
        this.blockMagnification = blockMagnification;
    }

    public Integer getPassCutMagnification() {
        return passCutMagnification;
    }

    public MNpcPersonality passCutMagnification(Integer passCutMagnification) {
        this.passCutMagnification = passCutMagnification;
        return this;
    }

    public void setPassCutMagnification(Integer passCutMagnification) {
        this.passCutMagnification = passCutMagnification;
    }

    public Integer getClearMagnification() {
        return clearMagnification;
    }

    public MNpcPersonality clearMagnification(Integer clearMagnification) {
        this.clearMagnification = clearMagnification;
        return this;
    }

    public void setClearMagnification(Integer clearMagnification) {
        this.clearMagnification = clearMagnification;
    }

    public Integer getCompeteMagnification() {
        return competeMagnification;
    }

    public MNpcPersonality competeMagnification(Integer competeMagnification) {
        this.competeMagnification = competeMagnification;
        return this;
    }

    public void setCompeteMagnification(Integer competeMagnification) {
        this.competeMagnification = competeMagnification;
    }

    public Integer getTrapMagnification() {
        return trapMagnification;
    }

    public MNpcPersonality trapMagnification(Integer trapMagnification) {
        this.trapMagnification = trapMagnification;
        return this;
    }

    public void setTrapMagnification(Integer trapMagnification) {
        this.trapMagnification = trapMagnification;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MNpcPersonality)) {
            return false;
        }
        return id != null && id.equals(((MNpcPersonality) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MNpcPersonality{" +
            "id=" + getId() +
            ", passingTargetRate=" + getPassingTargetRate() +
            ", actionSkillRate=" + getActionSkillRate() +
            ", dribbleMagnification=" + getDribbleMagnification() +
            ", passingMagnification=" + getPassingMagnification() +
            ", onetwoMagnification=" + getOnetwoMagnification() +
            ", shootMagnification=" + getShootMagnification() +
            ", volleyShootMagnification=" + getVolleyShootMagnification() +
            ", headingShootMagnification=" + getHeadingShootMagnification() +
            ", tackleMagnification=" + getTackleMagnification() +
            ", blockMagnification=" + getBlockMagnification() +
            ", passCutMagnification=" + getPassCutMagnification() +
            ", clearMagnification=" + getClearMagnification() +
            ", competeMagnification=" + getCompeteMagnification() +
            ", trapMagnification=" + getTrapMagnification() +
            "}";
    }
}
