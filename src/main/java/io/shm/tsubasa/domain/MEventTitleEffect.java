package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MEventTitleEffect.
 */
@Entity
@Table(name = "m_event_title_effect")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MEventTitleEffect implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "effect_path", nullable = false)
    private String effectPath;

    
    @Lob
    @Column(name = "bgm_sound_event", nullable = false)
    private String bgmSoundEvent;

    
    @Lob
    @Column(name = "se_sound_event", nullable = false)
    private String seSoundEvent;

    
    @Lob
    @Column(name = "voice_sound_event_suffixes", nullable = false)
    private String voiceSoundEventSuffixes;

    
    @Lob
    @Column(name = "copyright_text", nullable = false)
    private String copyrightText;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Integer startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private Integer endAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEffectPath() {
        return effectPath;
    }

    public MEventTitleEffect effectPath(String effectPath) {
        this.effectPath = effectPath;
        return this;
    }

    public void setEffectPath(String effectPath) {
        this.effectPath = effectPath;
    }

    public String getBgmSoundEvent() {
        return bgmSoundEvent;
    }

    public MEventTitleEffect bgmSoundEvent(String bgmSoundEvent) {
        this.bgmSoundEvent = bgmSoundEvent;
        return this;
    }

    public void setBgmSoundEvent(String bgmSoundEvent) {
        this.bgmSoundEvent = bgmSoundEvent;
    }

    public String getSeSoundEvent() {
        return seSoundEvent;
    }

    public MEventTitleEffect seSoundEvent(String seSoundEvent) {
        this.seSoundEvent = seSoundEvent;
        return this;
    }

    public void setSeSoundEvent(String seSoundEvent) {
        this.seSoundEvent = seSoundEvent;
    }

    public String getVoiceSoundEventSuffixes() {
        return voiceSoundEventSuffixes;
    }

    public MEventTitleEffect voiceSoundEventSuffixes(String voiceSoundEventSuffixes) {
        this.voiceSoundEventSuffixes = voiceSoundEventSuffixes;
        return this;
    }

    public void setVoiceSoundEventSuffixes(String voiceSoundEventSuffixes) {
        this.voiceSoundEventSuffixes = voiceSoundEventSuffixes;
    }

    public String getCopyrightText() {
        return copyrightText;
    }

    public MEventTitleEffect copyrightText(String copyrightText) {
        this.copyrightText = copyrightText;
        return this;
    }

    public void setCopyrightText(String copyrightText) {
        this.copyrightText = copyrightText;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MEventTitleEffect startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MEventTitleEffect endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MEventTitleEffect)) {
            return false;
        }
        return id != null && id.equals(((MEventTitleEffect) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MEventTitleEffect{" +
            "id=" + getId() +
            ", effectPath='" + getEffectPath() + "'" +
            ", bgmSoundEvent='" + getBgmSoundEvent() + "'" +
            ", seSoundEvent='" + getSeSoundEvent() + "'" +
            ", voiceSoundEventSuffixes='" + getVoiceSoundEventSuffixes() + "'" +
            ", copyrightText='" + getCopyrightText() + "'" +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
