package io.shm.tsubasa.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.shm.tsubasa.domain.MGuildLevel} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGuildLevelResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-guild-levels?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGuildLevelCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter level;

    private IntegerFilter exp;

    private IntegerFilter memberCount;

    private IntegerFilter recommendMemberCount;

    private IntegerFilter guildMedal;

    public MGuildLevelCriteria(){
    }

    public MGuildLevelCriteria(MGuildLevelCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.level = other.level == null ? null : other.level.copy();
        this.exp = other.exp == null ? null : other.exp.copy();
        this.memberCount = other.memberCount == null ? null : other.memberCount.copy();
        this.recommendMemberCount = other.recommendMemberCount == null ? null : other.recommendMemberCount.copy();
        this.guildMedal = other.guildMedal == null ? null : other.guildMedal.copy();
    }

    @Override
    public MGuildLevelCriteria copy() {
        return new MGuildLevelCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getLevel() {
        return level;
    }

    public void setLevel(IntegerFilter level) {
        this.level = level;
    }

    public IntegerFilter getExp() {
        return exp;
    }

    public void setExp(IntegerFilter exp) {
        this.exp = exp;
    }

    public IntegerFilter getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(IntegerFilter memberCount) {
        this.memberCount = memberCount;
    }

    public IntegerFilter getRecommendMemberCount() {
        return recommendMemberCount;
    }

    public void setRecommendMemberCount(IntegerFilter recommendMemberCount) {
        this.recommendMemberCount = recommendMemberCount;
    }

    public IntegerFilter getGuildMedal() {
        return guildMedal;
    }

    public void setGuildMedal(IntegerFilter guildMedal) {
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
        final MGuildLevelCriteria that = (MGuildLevelCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(level, that.level) &&
            Objects.equals(exp, that.exp) &&
            Objects.equals(memberCount, that.memberCount) &&
            Objects.equals(recommendMemberCount, that.recommendMemberCount) &&
            Objects.equals(guildMedal, that.guildMedal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        level,
        exp,
        memberCount,
        recommendMemberCount,
        guildMedal
        );
    }

    @Override
    public String toString() {
        return "MGuildLevelCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (level != null ? "level=" + level + ", " : "") +
                (exp != null ? "exp=" + exp + ", " : "") +
                (memberCount != null ? "memberCount=" + memberCount + ", " : "") +
                (recommendMemberCount != null ? "recommendMemberCount=" + recommendMemberCount + ", " : "") +
                (guildMedal != null ? "guildMedal=" + guildMedal + ", " : "") +
            "}";
    }

}
