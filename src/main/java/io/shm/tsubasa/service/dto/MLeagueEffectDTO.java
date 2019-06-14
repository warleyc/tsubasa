package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MLeagueEffect} entity.
 */
public class MLeagueEffectDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer effectType;

    @NotNull
    private Integer leagueHierarchy;

    @NotNull
    private Integer rate;

    @NotNull
    private Integer price;


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

    public Integer getLeagueHierarchy() {
        return leagueHierarchy;
    }

    public void setLeagueHierarchy(Integer leagueHierarchy) {
        this.leagueHierarchy = leagueHierarchy;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MLeagueEffectDTO mLeagueEffectDTO = (MLeagueEffectDTO) o;
        if (mLeagueEffectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mLeagueEffectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MLeagueEffectDTO{" +
            "id=" + getId() +
            ", effectType=" + getEffectType() +
            ", leagueHierarchy=" + getLeagueHierarchy() +
            ", rate=" + getRate() +
            ", price=" + getPrice() +
            "}";
    }
}
