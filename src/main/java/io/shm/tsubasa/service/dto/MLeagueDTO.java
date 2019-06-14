package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MLeague} entity.
 */
public class MLeagueDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer hierarchy;

    @NotNull
    private Integer weight;

    
    @Lob
    private String name;

    @NotNull
    private Integer rooms;

    @NotNull
    private Integer promotionRate;

    @NotNull
    private Integer demotionRate;

    @NotNull
    private Integer totalParameterRangeUpper;

    @NotNull
    private Integer totalParameterRangeLower;

    private Integer requiredMatches;

    @NotNull
    private Integer startWeekId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(Integer promotionRate) {
        this.promotionRate = promotionRate;
    }

    public Integer getDemotionRate() {
        return demotionRate;
    }

    public void setDemotionRate(Integer demotionRate) {
        this.demotionRate = demotionRate;
    }

    public Integer getTotalParameterRangeUpper() {
        return totalParameterRangeUpper;
    }

    public void setTotalParameterRangeUpper(Integer totalParameterRangeUpper) {
        this.totalParameterRangeUpper = totalParameterRangeUpper;
    }

    public Integer getTotalParameterRangeLower() {
        return totalParameterRangeLower;
    }

    public void setTotalParameterRangeLower(Integer totalParameterRangeLower) {
        this.totalParameterRangeLower = totalParameterRangeLower;
    }

    public Integer getRequiredMatches() {
        return requiredMatches;
    }

    public void setRequiredMatches(Integer requiredMatches) {
        this.requiredMatches = requiredMatches;
    }

    public Integer getStartWeekId() {
        return startWeekId;
    }

    public void setStartWeekId(Integer startWeekId) {
        this.startWeekId = startWeekId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MLeagueDTO mLeagueDTO = (MLeagueDTO) o;
        if (mLeagueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mLeagueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MLeagueDTO{" +
            "id=" + getId() +
            ", hierarchy=" + getHierarchy() +
            ", weight=" + getWeight() +
            ", name='" + getName() + "'" +
            ", rooms=" + getRooms() +
            ", promotionRate=" + getPromotionRate() +
            ", demotionRate=" + getDemotionRate() +
            ", totalParameterRangeUpper=" + getTotalParameterRangeUpper() +
            ", totalParameterRangeLower=" + getTotalParameterRangeLower() +
            ", requiredMatches=" + getRequiredMatches() +
            ", startWeekId=" + getStartWeekId() +
            "}";
    }
}
