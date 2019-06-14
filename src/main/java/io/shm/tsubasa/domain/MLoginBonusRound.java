package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MLoginBonusRound.
 */
@Entity
@Table(name = "m_login_bonus_round")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MLoginBonusRound implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "round_id", nullable = false)
    private Integer roundId;

    @NotNull
    @Column(name = "bonus_type", nullable = false)
    private Integer bonusType;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Integer startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private Integer endAt;

    
    @Lob
    @Column(name = "serif_sanae", nullable = false)
    private String serifSanae;

    
    @Lob
    @Column(name = "serif_yayoi", nullable = false)
    private String serifYayoi;

    
    @Lob
    @Column(name = "serif_yoshiko", nullable = false)
    private String serifYoshiko;

    
    @Lob
    @Column(name = "serif_maki", nullable = false)
    private String serifMaki;

    @Lob
    @Column(name = "sanae_image")
    private String sanaeImage;

    @Lob
    @Column(name = "yayoi_image")
    private String yayoiImage;

    @Lob
    @Column(name = "yoshiko_image")
    private String yoshikoImage;

    @Lob
    @Column(name = "maki_image")
    private String makiImage;

    
    @Lob
    @Column(name = "sound_switch_event_name", nullable = false)
    private String soundSwitchEventName;

    @Column(name = "next_id")
    private Integer nextId;

    @Lob
    @Column(name = "last_day_appeal_comment")
    private String lastDayAppealComment;

    @Lob
    @Column(name = "background_image")
    private String backgroundImage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public MLoginBonusRound roundId(Integer roundId) {
        this.roundId = roundId;
        return this;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getBonusType() {
        return bonusType;
    }

    public MLoginBonusRound bonusType(Integer bonusType) {
        this.bonusType = bonusType;
        return this;
    }

    public void setBonusType(Integer bonusType) {
        this.bonusType = bonusType;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MLoginBonusRound startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MLoginBonusRound endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public String getSerifSanae() {
        return serifSanae;
    }

    public MLoginBonusRound serifSanae(String serifSanae) {
        this.serifSanae = serifSanae;
        return this;
    }

    public void setSerifSanae(String serifSanae) {
        this.serifSanae = serifSanae;
    }

    public String getSerifYayoi() {
        return serifYayoi;
    }

    public MLoginBonusRound serifYayoi(String serifYayoi) {
        this.serifYayoi = serifYayoi;
        return this;
    }

    public void setSerifYayoi(String serifYayoi) {
        this.serifYayoi = serifYayoi;
    }

    public String getSerifYoshiko() {
        return serifYoshiko;
    }

    public MLoginBonusRound serifYoshiko(String serifYoshiko) {
        this.serifYoshiko = serifYoshiko;
        return this;
    }

    public void setSerifYoshiko(String serifYoshiko) {
        this.serifYoshiko = serifYoshiko;
    }

    public String getSerifMaki() {
        return serifMaki;
    }

    public MLoginBonusRound serifMaki(String serifMaki) {
        this.serifMaki = serifMaki;
        return this;
    }

    public void setSerifMaki(String serifMaki) {
        this.serifMaki = serifMaki;
    }

    public String getSanaeImage() {
        return sanaeImage;
    }

    public MLoginBonusRound sanaeImage(String sanaeImage) {
        this.sanaeImage = sanaeImage;
        return this;
    }

    public void setSanaeImage(String sanaeImage) {
        this.sanaeImage = sanaeImage;
    }

    public String getYayoiImage() {
        return yayoiImage;
    }

    public MLoginBonusRound yayoiImage(String yayoiImage) {
        this.yayoiImage = yayoiImage;
        return this;
    }

    public void setYayoiImage(String yayoiImage) {
        this.yayoiImage = yayoiImage;
    }

    public String getYoshikoImage() {
        return yoshikoImage;
    }

    public MLoginBonusRound yoshikoImage(String yoshikoImage) {
        this.yoshikoImage = yoshikoImage;
        return this;
    }

    public void setYoshikoImage(String yoshikoImage) {
        this.yoshikoImage = yoshikoImage;
    }

    public String getMakiImage() {
        return makiImage;
    }

    public MLoginBonusRound makiImage(String makiImage) {
        this.makiImage = makiImage;
        return this;
    }

    public void setMakiImage(String makiImage) {
        this.makiImage = makiImage;
    }

    public String getSoundSwitchEventName() {
        return soundSwitchEventName;
    }

    public MLoginBonusRound soundSwitchEventName(String soundSwitchEventName) {
        this.soundSwitchEventName = soundSwitchEventName;
        return this;
    }

    public void setSoundSwitchEventName(String soundSwitchEventName) {
        this.soundSwitchEventName = soundSwitchEventName;
    }

    public Integer getNextId() {
        return nextId;
    }

    public MLoginBonusRound nextId(Integer nextId) {
        this.nextId = nextId;
        return this;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }

    public String getLastDayAppealComment() {
        return lastDayAppealComment;
    }

    public MLoginBonusRound lastDayAppealComment(String lastDayAppealComment) {
        this.lastDayAppealComment = lastDayAppealComment;
        return this;
    }

    public void setLastDayAppealComment(String lastDayAppealComment) {
        this.lastDayAppealComment = lastDayAppealComment;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public MLoginBonusRound backgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
        return this;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MLoginBonusRound)) {
            return false;
        }
        return id != null && id.equals(((MLoginBonusRound) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MLoginBonusRound{" +
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
