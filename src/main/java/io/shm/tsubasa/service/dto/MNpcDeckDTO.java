package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MNpcDeck} entity.
 */
public class MNpcDeckDTO implements Serializable {

    private Long id;

    
    @Lob
    private String teamName;

    @NotNull
    private Integer uniformBottomFp;

    @NotNull
    private Integer uniformUpFp;

    @NotNull
    private Integer uniformBottomGk;

    @NotNull
    private Integer uniformUpGk;

    @NotNull
    private Integer formationId;

    @NotNull
    private Integer captainCardId;

    @NotNull
    private Integer teamEffect1CardId;

    @NotNull
    private Integer teamEffect2CardId;

    @NotNull
    private Integer teamEffect3CardId;

    @NotNull
    private Integer npcCardId1;

    @NotNull
    private Integer npcCardId2;

    @NotNull
    private Integer npcCardId3;

    @NotNull
    private Integer npcCardId4;

    @NotNull
    private Integer npcCardId5;

    @NotNull
    private Integer npcCardId6;

    @NotNull
    private Integer npcCardId7;

    @NotNull
    private Integer npcCardId8;

    @NotNull
    private Integer npcCardId9;

    @NotNull
    private Integer npcCardId10;

    @NotNull
    private Integer npcCardId11;

    @NotNull
    private Integer tick;


    private Long idId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getUniformBottomFp() {
        return uniformBottomFp;
    }

    public void setUniformBottomFp(Integer uniformBottomFp) {
        this.uniformBottomFp = uniformBottomFp;
    }

    public Integer getUniformUpFp() {
        return uniformUpFp;
    }

    public void setUniformUpFp(Integer uniformUpFp) {
        this.uniformUpFp = uniformUpFp;
    }

    public Integer getUniformBottomGk() {
        return uniformBottomGk;
    }

    public void setUniformBottomGk(Integer uniformBottomGk) {
        this.uniformBottomGk = uniformBottomGk;
    }

    public Integer getUniformUpGk() {
        return uniformUpGk;
    }

    public void setUniformUpGk(Integer uniformUpGk) {
        this.uniformUpGk = uniformUpGk;
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

    public Integer getNpcCardId1() {
        return npcCardId1;
    }

    public void setNpcCardId1(Integer npcCardId1) {
        this.npcCardId1 = npcCardId1;
    }

    public Integer getNpcCardId2() {
        return npcCardId2;
    }

    public void setNpcCardId2(Integer npcCardId2) {
        this.npcCardId2 = npcCardId2;
    }

    public Integer getNpcCardId3() {
        return npcCardId3;
    }

    public void setNpcCardId3(Integer npcCardId3) {
        this.npcCardId3 = npcCardId3;
    }

    public Integer getNpcCardId4() {
        return npcCardId4;
    }

    public void setNpcCardId4(Integer npcCardId4) {
        this.npcCardId4 = npcCardId4;
    }

    public Integer getNpcCardId5() {
        return npcCardId5;
    }

    public void setNpcCardId5(Integer npcCardId5) {
        this.npcCardId5 = npcCardId5;
    }

    public Integer getNpcCardId6() {
        return npcCardId6;
    }

    public void setNpcCardId6(Integer npcCardId6) {
        this.npcCardId6 = npcCardId6;
    }

    public Integer getNpcCardId7() {
        return npcCardId7;
    }

    public void setNpcCardId7(Integer npcCardId7) {
        this.npcCardId7 = npcCardId7;
    }

    public Integer getNpcCardId8() {
        return npcCardId8;
    }

    public void setNpcCardId8(Integer npcCardId8) {
        this.npcCardId8 = npcCardId8;
    }

    public Integer getNpcCardId9() {
        return npcCardId9;
    }

    public void setNpcCardId9(Integer npcCardId9) {
        this.npcCardId9 = npcCardId9;
    }

    public Integer getNpcCardId10() {
        return npcCardId10;
    }

    public void setNpcCardId10(Integer npcCardId10) {
        this.npcCardId10 = npcCardId10;
    }

    public Integer getNpcCardId11() {
        return npcCardId11;
    }

    public void setNpcCardId11(Integer npcCardId11) {
        this.npcCardId11 = npcCardId11;
    }

    public Integer getTick() {
        return tick;
    }

    public void setTick(Integer tick) {
        this.tick = tick;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long mFormationId) {
        this.idId = mFormationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MNpcDeckDTO mNpcDeckDTO = (MNpcDeckDTO) o;
        if (mNpcDeckDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mNpcDeckDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MNpcDeckDTO{" +
            "id=" + getId() +
            ", teamName='" + getTeamName() + "'" +
            ", uniformBottomFp=" + getUniformBottomFp() +
            ", uniformUpFp=" + getUniformUpFp() +
            ", uniformBottomGk=" + getUniformBottomGk() +
            ", uniformUpGk=" + getUniformUpGk() +
            ", formationId=" + getFormationId() +
            ", captainCardId=" + getCaptainCardId() +
            ", teamEffect1CardId=" + getTeamEffect1CardId() +
            ", teamEffect2CardId=" + getTeamEffect2CardId() +
            ", teamEffect3CardId=" + getTeamEffect3CardId() +
            ", npcCardId1=" + getNpcCardId1() +
            ", npcCardId2=" + getNpcCardId2() +
            ", npcCardId3=" + getNpcCardId3() +
            ", npcCardId4=" + getNpcCardId4() +
            ", npcCardId5=" + getNpcCardId5() +
            ", npcCardId6=" + getNpcCardId6() +
            ", npcCardId7=" + getNpcCardId7() +
            ", npcCardId8=" + getNpcCardId8() +
            ", npcCardId9=" + getNpcCardId9() +
            ", npcCardId10=" + getNpcCardId10() +
            ", npcCardId11=" + getNpcCardId11() +
            ", tick=" + getTick() +
            ", id=" + getIdId() +
            "}";
    }
}
