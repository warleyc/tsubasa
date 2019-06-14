package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MInitUserDeck} entity.
 */
public class MInitUserDeckDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer deckId;

    
    @Lob
    private String name;

    @NotNull
    private Integer formationId;

    private Integer captainCardId;

    private Integer gkCardId;

    private Integer fp1CardId;

    private Integer fp2CardId;

    private Integer fp3CardId;

    private Integer fp4CardId;

    private Integer fp5CardId;

    private Integer fp6CardId;

    private Integer fp7CardId;

    private Integer fp8CardId;

    private Integer fp9CardId;

    private Integer fp10CardId;

    private Integer sub1CardId;

    private Integer sub2CardId;

    private Integer sub3CardId;

    private Integer sub4CardId;

    private Integer sub5CardId;

    private Integer teamEffect1CardId;

    private Integer teamEffect2CardId;

    private Integer teamEffect3CardId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeckId() {
        return deckId;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFormationId() {
        return formationId;
    }

    public void setFormationId(Integer formationId) {
        this.formationId = formationId;
    }

    public Integer getCaptainCardId() {
        return captainCardId;
    }

    public void setCaptainCardId(Integer captainCardId) {
        this.captainCardId = captainCardId;
    }

    public Integer getGkCardId() {
        return gkCardId;
    }

    public void setGkCardId(Integer gkCardId) {
        this.gkCardId = gkCardId;
    }

    public Integer getFp1CardId() {
        return fp1CardId;
    }

    public void setFp1CardId(Integer fp1CardId) {
        this.fp1CardId = fp1CardId;
    }

    public Integer getFp2CardId() {
        return fp2CardId;
    }

    public void setFp2CardId(Integer fp2CardId) {
        this.fp2CardId = fp2CardId;
    }

    public Integer getFp3CardId() {
        return fp3CardId;
    }

    public void setFp3CardId(Integer fp3CardId) {
        this.fp3CardId = fp3CardId;
    }

    public Integer getFp4CardId() {
        return fp4CardId;
    }

    public void setFp4CardId(Integer fp4CardId) {
        this.fp4CardId = fp4CardId;
    }

    public Integer getFp5CardId() {
        return fp5CardId;
    }

    public void setFp5CardId(Integer fp5CardId) {
        this.fp5CardId = fp5CardId;
    }

    public Integer getFp6CardId() {
        return fp6CardId;
    }

    public void setFp6CardId(Integer fp6CardId) {
        this.fp6CardId = fp6CardId;
    }

    public Integer getFp7CardId() {
        return fp7CardId;
    }

    public void setFp7CardId(Integer fp7CardId) {
        this.fp7CardId = fp7CardId;
    }

    public Integer getFp8CardId() {
        return fp8CardId;
    }

    public void setFp8CardId(Integer fp8CardId) {
        this.fp8CardId = fp8CardId;
    }

    public Integer getFp9CardId() {
        return fp9CardId;
    }

    public void setFp9CardId(Integer fp9CardId) {
        this.fp9CardId = fp9CardId;
    }

    public Integer getFp10CardId() {
        return fp10CardId;
    }

    public void setFp10CardId(Integer fp10CardId) {
        this.fp10CardId = fp10CardId;
    }

    public Integer getSub1CardId() {
        return sub1CardId;
    }

    public void setSub1CardId(Integer sub1CardId) {
        this.sub1CardId = sub1CardId;
    }

    public Integer getSub2CardId() {
        return sub2CardId;
    }

    public void setSub2CardId(Integer sub2CardId) {
        this.sub2CardId = sub2CardId;
    }

    public Integer getSub3CardId() {
        return sub3CardId;
    }

    public void setSub3CardId(Integer sub3CardId) {
        this.sub3CardId = sub3CardId;
    }

    public Integer getSub4CardId() {
        return sub4CardId;
    }

    public void setSub4CardId(Integer sub4CardId) {
        this.sub4CardId = sub4CardId;
    }

    public Integer getSub5CardId() {
        return sub5CardId;
    }

    public void setSub5CardId(Integer sub5CardId) {
        this.sub5CardId = sub5CardId;
    }

    public Integer getTeamEffect1CardId() {
        return teamEffect1CardId;
    }

    public void setTeamEffect1CardId(Integer teamEffect1CardId) {
        this.teamEffect1CardId = teamEffect1CardId;
    }

    public Integer getTeamEffect2CardId() {
        return teamEffect2CardId;
    }

    public void setTeamEffect2CardId(Integer teamEffect2CardId) {
        this.teamEffect2CardId = teamEffect2CardId;
    }

    public Integer getTeamEffect3CardId() {
        return teamEffect3CardId;
    }

    public void setTeamEffect3CardId(Integer teamEffect3CardId) {
        this.teamEffect3CardId = teamEffect3CardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MInitUserDeckDTO mInitUserDeckDTO = (MInitUserDeckDTO) o;
        if (mInitUserDeckDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mInitUserDeckDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MInitUserDeckDTO{" +
            "id=" + getId() +
            ", deckId=" + getDeckId() +
            ", name='" + getName() + "'" +
            ", formationId=" + getFormationId() +
            ", captainCardId=" + getCaptainCardId() +
            ", gkCardId=" + getGkCardId() +
            ", fp1CardId=" + getFp1CardId() +
            ", fp2CardId=" + getFp2CardId() +
            ", fp3CardId=" + getFp3CardId() +
            ", fp4CardId=" + getFp4CardId() +
            ", fp5CardId=" + getFp5CardId() +
            ", fp6CardId=" + getFp6CardId() +
            ", fp7CardId=" + getFp7CardId() +
            ", fp8CardId=" + getFp8CardId() +
            ", fp9CardId=" + getFp9CardId() +
            ", fp10CardId=" + getFp10CardId() +
            ", sub1CardId=" + getSub1CardId() +
            ", sub2CardId=" + getSub2CardId() +
            ", sub3CardId=" + getSub3CardId() +
            ", sub4CardId=" + getSub4CardId() +
            ", sub5CardId=" + getSub5CardId() +
            ", teamEffect1CardId=" + getTeamEffect1CardId() +
            ", teamEffect2CardId=" + getTeamEffect2CardId() +
            ", teamEffect3CardId=" + getTeamEffect3CardId() +
            "}";
    }
}
