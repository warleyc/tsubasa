package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MNpcPersonality} entity.
 */
public class MNpcPersonalityDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer passingTargetRate;

    @NotNull
    private Integer actionSkillRate;

    @NotNull
    private Integer dribbleMagnification;

    @NotNull
    private Integer passingMagnification;

    @NotNull
    private Integer onetwoMagnification;

    @NotNull
    private Integer shootMagnification;

    @NotNull
    private Integer volleyShootMagnification;

    @NotNull
    private Integer headingShootMagnification;

    @NotNull
    private Integer tackleMagnification;

    @NotNull
    private Integer blockMagnification;

    @NotNull
    private Integer passCutMagnification;

    @NotNull
    private Integer clearMagnification;

    @NotNull
    private Integer competeMagnification;

    @NotNull
    private Integer trapMagnification;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPassingTargetRate() {
        return passingTargetRate;
    }

    public void setPassingTargetRate(Integer passingTargetRate) {
        this.passingTargetRate = passingTargetRate;
    }

    public Integer getActionSkillRate() {
        return actionSkillRate;
    }

    public void setActionSkillRate(Integer actionSkillRate) {
        this.actionSkillRate = actionSkillRate;
    }

    public Integer getDribbleMagnification() {
        return dribbleMagnification;
    }

    public void setDribbleMagnification(Integer dribbleMagnification) {
        this.dribbleMagnification = dribbleMagnification;
    }

    public Integer getPassingMagnification() {
        return passingMagnification;
    }

    public void setPassingMagnification(Integer passingMagnification) {
        this.passingMagnification = passingMagnification;
    }

    public Integer getOnetwoMagnification() {
        return onetwoMagnification;
    }

    public void setOnetwoMagnification(Integer onetwoMagnification) {
        this.onetwoMagnification = onetwoMagnification;
    }

    public Integer getShootMagnification() {
        return shootMagnification;
    }

    public void setShootMagnification(Integer shootMagnification) {
        this.shootMagnification = shootMagnification;
    }

    public Integer getVolleyShootMagnification() {
        return volleyShootMagnification;
    }

    public void setVolleyShootMagnification(Integer volleyShootMagnification) {
        this.volleyShootMagnification = volleyShootMagnification;
    }

    public Integer getHeadingShootMagnification() {
        return headingShootMagnification;
    }

    public void setHeadingShootMagnification(Integer headingShootMagnification) {
        this.headingShootMagnification = headingShootMagnification;
    }

    public Integer getTackleMagnification() {
        return tackleMagnification;
    }

    public void setTackleMagnification(Integer tackleMagnification) {
        this.tackleMagnification = tackleMagnification;
    }

    public Integer getBlockMagnification() {
        return blockMagnification;
    }

    public void setBlockMagnification(Integer blockMagnification) {
        this.blockMagnification = blockMagnification;
    }

    public Integer getPassCutMagnification() {
        return passCutMagnification;
    }

    public void setPassCutMagnification(Integer passCutMagnification) {
        this.passCutMagnification = passCutMagnification;
    }

    public Integer getClearMagnification() {
        return clearMagnification;
    }

    public void setClearMagnification(Integer clearMagnification) {
        this.clearMagnification = clearMagnification;
    }

    public Integer getCompeteMagnification() {
        return competeMagnification;
    }

    public void setCompeteMagnification(Integer competeMagnification) {
        this.competeMagnification = competeMagnification;
    }

    public Integer getTrapMagnification() {
        return trapMagnification;
    }

    public void setTrapMagnification(Integer trapMagnification) {
        this.trapMagnification = trapMagnification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MNpcPersonalityDTO mNpcPersonalityDTO = (MNpcPersonalityDTO) o;
        if (mNpcPersonalityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mNpcPersonalityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MNpcPersonalityDTO{" +
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
