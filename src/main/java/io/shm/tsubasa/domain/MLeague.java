package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MLeague.
 */
@Entity
@Table(name = "m_league")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MLeague implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "hierarchy", nullable = false)
    private Integer hierarchy;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "rooms", nullable = false)
    private Integer rooms;

    @NotNull
    @Column(name = "promotion_rate", nullable = false)
    private Integer promotionRate;

    @NotNull
    @Column(name = "demotion_rate", nullable = false)
    private Integer demotionRate;

    @NotNull
    @Column(name = "total_parameter_range_upper", nullable = false)
    private Integer totalParameterRangeUpper;

    @NotNull
    @Column(name = "total_parameter_range_lower", nullable = false)
    private Integer totalParameterRangeLower;

    @Column(name = "required_matches")
    private Integer requiredMatches;

    @NotNull
    @Column(name = "start_week_id", nullable = false)
    private Integer startWeekId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }

    public MLeague hierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
        return this;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Integer getWeight() {
        return weight;
    }

    public MLeague weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public MLeague name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRooms() {
        return rooms;
    }

    public MLeague rooms(Integer rooms) {
        this.rooms = rooms;
        return this;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getPromotionRate() {
        return promotionRate;
    }

    public MLeague promotionRate(Integer promotionRate) {
        this.promotionRate = promotionRate;
        return this;
    }

    public void setPromotionRate(Integer promotionRate) {
        this.promotionRate = promotionRate;
    }

    public Integer getDemotionRate() {
        return demotionRate;
    }

    public MLeague demotionRate(Integer demotionRate) {
        this.demotionRate = demotionRate;
        return this;
    }

    public void setDemotionRate(Integer demotionRate) {
        this.demotionRate = demotionRate;
    }

    public Integer getTotalParameterRangeUpper() {
        return totalParameterRangeUpper;
    }

    public MLeague totalParameterRangeUpper(Integer totalParameterRangeUpper) {
        this.totalParameterRangeUpper = totalParameterRangeUpper;
        return this;
    }

    public void setTotalParameterRangeUpper(Integer totalParameterRangeUpper) {
        this.totalParameterRangeUpper = totalParameterRangeUpper;
    }

    public Integer getTotalParameterRangeLower() {
        return totalParameterRangeLower;
    }

    public MLeague totalParameterRangeLower(Integer totalParameterRangeLower) {
        this.totalParameterRangeLower = totalParameterRangeLower;
        return this;
    }

    public void setTotalParameterRangeLower(Integer totalParameterRangeLower) {
        this.totalParameterRangeLower = totalParameterRangeLower;
    }

    public Integer getRequiredMatches() {
        return requiredMatches;
    }

    public MLeague requiredMatches(Integer requiredMatches) {
        this.requiredMatches = requiredMatches;
        return this;
    }

    public void setRequiredMatches(Integer requiredMatches) {
        this.requiredMatches = requiredMatches;
    }

    public Integer getStartWeekId() {
        return startWeekId;
    }

    public MLeague startWeekId(Integer startWeekId) {
        this.startWeekId = startWeekId;
        return this;
    }

    public void setStartWeekId(Integer startWeekId) {
        this.startWeekId = startWeekId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MLeague)) {
            return false;
        }
        return id != null && id.equals(((MLeague) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MLeague{" +
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
