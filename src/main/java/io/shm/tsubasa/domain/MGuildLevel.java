package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGuildLevel.
 */
@Entity
@Table(name = "m_guild_level")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGuildLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_level", nullable = false)
    private Integer level;

    @NotNull
    @Column(name = "exp", nullable = false)
    private Integer exp;

    @NotNull
    @Column(name = "member_count", nullable = false)
    private Integer memberCount;

    @NotNull
    @Column(name = "recommend_member_count", nullable = false)
    private Integer recommendMemberCount;

    @NotNull
    @Column(name = "guild_medal", nullable = false)
    private Integer guildMedal;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public MGuildLevel level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExp() {
        return exp;
    }

    public MGuildLevel exp(Integer exp) {
        this.exp = exp;
        return this;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public MGuildLevel memberCount(Integer memberCount) {
        this.memberCount = memberCount;
        return this;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getRecommendMemberCount() {
        return recommendMemberCount;
    }

    public MGuildLevel recommendMemberCount(Integer recommendMemberCount) {
        this.recommendMemberCount = recommendMemberCount;
        return this;
    }

    public void setRecommendMemberCount(Integer recommendMemberCount) {
        this.recommendMemberCount = recommendMemberCount;
    }

    public Integer getGuildMedal() {
        return guildMedal;
    }

    public MGuildLevel guildMedal(Integer guildMedal) {
        this.guildMedal = guildMedal;
        return this;
    }

    public void setGuildMedal(Integer guildMedal) {
        this.guildMedal = guildMedal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGuildLevel)) {
            return false;
        }
        return id != null && id.equals(((MGuildLevel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGuildLevel{" +
            "id=" + getId() +
            ", level=" + getLevel() +
            ", exp=" + getExp() +
            ", memberCount=" + getMemberCount() +
            ", recommendMemberCount=" + getRecommendMemberCount() +
            ", guildMedal=" + getGuildMedal() +
            "}";
    }
}
