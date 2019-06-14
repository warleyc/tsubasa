package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGuildEffectLevel} entity.
 */
public class MGuildEffectLevelDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer effectType;

    @NotNull
    private Integer level;

    @NotNull
    private Integer rate;

    
    @Lob
    private String rateStr;

    @NotNull
    private Integer coin;

    @NotNull
    private Integer guildLevel;

    @NotNull
    private Integer guildMedal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEffectType() {
        return effectType;
    }

    public void setEffectType(Integer effectType) {
        this.effectType = effectType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getRateStr() {
        return rateStr;
    }

    public void setRateStr(String rateStr) {
        this.rateStr = rateStr;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getGuildLevel() {
        return guildLevel;
    }

    public void setGuildLevel(Integer guildLevel) {
        this.guildLevel = guildLevel;
    }

    public Integer getGuildMedal() {
        return guildMedal;
    }

    public void setGuildMedal(Integer guildMedal) {
        this.guildMedal = guildMedal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGuildEffectLevelDTO mGuildEffectLevelDTO = (MGuildEffectLevelDTO) o;
        if (mGuildEffectLevelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGuildEffectLevelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGuildEffectLevelDTO{" +
            "id=" + getId() +
            ", effectType=" + getEffectType() +
            ", level=" + getLevel() +
            ", rate=" + getRate() +
            ", rateStr='" + getRateStr() + "'" +
            ", coin=" + getCoin() +
            ", guildLevel=" + getGuildLevel() +
            ", guildMedal=" + getGuildMedal() +
            "}";
    }
}
