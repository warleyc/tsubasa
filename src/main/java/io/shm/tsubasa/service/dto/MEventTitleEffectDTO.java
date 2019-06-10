package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MEventTitleEffect} entity.
 */
public class MEventTitleEffectDTO implements Serializable {

    private Long id;

    
    @Lob
    private String effectPath;

    
    @Lob
    private String bgmSoundEvent;

    
    @Lob
    private String seSoundEvent;

    
    @Lob
    private String voiceSoundEventSuffixes;

    
    @Lob
    private String copyrightText;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEffectPath() {
        return effectPath;
    }

    public void setEffectPath(String effectPath) {
        this.effectPath = effectPath;
    }

    public String getBgmSoundEvent() {
        return bgmSoundEvent;
    }

    public void setBgmSoundEvent(String bgmSoundEvent) {
        this.bgmSoundEvent = bgmSoundEvent;
    }

    public String getSeSoundEvent() {
        return seSoundEvent;
    }

    public void setSeSoundEvent(String seSoundEvent) {
        this.seSoundEvent = seSoundEvent;
    }

    public String getVoiceSoundEventSuffixes() {
        return voiceSoundEventSuffixes;
    }

    public void setVoiceSoundEventSuffixes(String voiceSoundEventSuffixes) {
        this.voiceSoundEventSuffixes = voiceSoundEventSuffixes;
    }

    public String getCopyrightText() {
        return copyrightText;
    }

    public void setCopyrightText(String copyrightText) {
        this.copyrightText = copyrightText;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MEventTitleEffectDTO mEventTitleEffectDTO = (MEventTitleEffectDTO) o;
        if (mEventTitleEffectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mEventTitleEffectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MEventTitleEffectDTO{" +
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
