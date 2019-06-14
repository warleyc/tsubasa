package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MLoginBonusRound} entity.
 */
public class MLoginBonusRoundDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer roundId;

    @NotNull
    private Integer bonusType;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;

    
    @Lob
    private String serifSanae;

    
    @Lob
    private String serifYayoi;

    
    @Lob
    private String serifYoshiko;

    
    @Lob
    private String serifMaki;

    @Lob
    private String sanaeImage;

    @Lob
    private String yayoiImage;

    @Lob
    private String yoshikoImage;

    @Lob
    private String makiImage;

    
    @Lob
    private String soundSwitchEventName;

    private Integer nextId;

    @Lob
    private String lastDayAppealComment;

    @Lob
    private String backgroundImage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getBonusType() {
        return bonusType;
    }

    public void setBonusType(Integer bonusType) {
        this.bonusType = bonusType;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public String getSerifSanae() {
        return serifSanae;
    }

    public void setSerifSanae(String serifSanae) {
        this.serifSanae = serifSanae;
    }

    public String getSerifYayoi() {
        return serifYayoi;
    }

    public void setSerifYayoi(String serifYayoi) {
        this.serifYayoi = serifYayoi;
    }

    public String getSerifYoshiko() {
        return serifYoshiko;
    }

    public void setSerifYoshiko(String serifYoshiko) {
        this.serifYoshiko = serifYoshiko;
    }

    public String getSerifMaki() {
        return serifMaki;
    }

    public void setSerifMaki(String serifMaki) {
        this.serifMaki = serifMaki;
    }

    public String getSanaeImage() {
        return sanaeImage;
    }

    public void setSanaeImage(String sanaeImage) {
        this.sanaeImage = sanaeImage;
    }

    public String getYayoiImage() {
        return yayoiImage;
    }

    public void setYayoiImage(String yayoiImage) {
        this.yayoiImage = yayoiImage;
    }

    public String getYoshikoImage() {
        return yoshikoImage;
    }

    public void setYoshikoImage(String yoshikoImage) {
        this.yoshikoImage = yoshikoImage;
    }

    public String getMakiImage() {
        return makiImage;
    }

    public void setMakiImage(String makiImage) {
        this.makiImage = makiImage;
    }

    public String getSoundSwitchEventName() {
        return soundSwitchEventName;
    }

    public void setSoundSwitchEventName(String soundSwitchEventName) {
        this.soundSwitchEventName = soundSwitchEventName;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }

    public String getLastDayAppealComment() {
        return lastDayAppealComment;
    }

    public void setLastDayAppealComment(String lastDayAppealComment) {
        this.lastDayAppealComment = lastDayAppealComment;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MLoginBonusRoundDTO mLoginBonusRoundDTO = (MLoginBonusRoundDTO) o;
        if (mLoginBonusRoundDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mLoginBonusRoundDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MLoginBonusRoundDTO{" +
            "id=" + getId() +
            ", roundId=" + getRoundId() +
            ", bonusType=" + getBonusType() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            ", serifSanae='" + getSerifSanae() + "'" +
            ", serifYayoi='" + getSerifYayoi() + "'" +
            ", serifYoshiko='" + getSerifYoshiko() + "'" +
            ", serifMaki='" + getSerifMaki() + "'" +
            ", sanaeImage='" + getSanaeImage() + "'" +
            ", yayoiImage='" + getYayoiImage() + "'" +
            ", yoshikoImage='" + getYoshikoImage() + "'" +
            ", makiImage='" + getMakiImage() + "'" +
            ", soundSwitchEventName='" + getSoundSwitchEventName() + "'" +
            ", nextId=" + getNextId() +
            ", lastDayAppealComment='" + getLastDayAppealComment() + "'" +
            ", backgroundImage='" + getBackgroundImage() + "'" +
            "}";
    }
}
