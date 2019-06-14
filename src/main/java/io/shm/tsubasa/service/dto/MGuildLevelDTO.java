package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGuildLevel} entity.
 */
public class MGuildLevelDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer level;

    @NotNull
    private Integer exp;

    @NotNull
    private Integer memberCount;

    @NotNull
    private Integer recommendMemberCount;

    @NotNull
    private Integer guildMedal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getRecommendMemberCount() {
        return recommendMemberCount;
    }

    public void setRecommendMemberCount(Integer recommendMemberCount) {
        this.recommendMemberCount = recommendMemberCount;
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

        MGuildLevelDTO mGuildLevelDTO = (MGuildLevelDTO) o;
        if (mGuildLevelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGuildLevelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGuildLevelDTO{" +
            "id=" + getId() +
            ", level=" + getLevel() +
            ", exp=" + getExp() +
            ", memberCount=" + getMemberCount() +
            ", recommendMemberCount=" + getRecommendMemberCount() +
            ", guildMedal=" + getGuildMedal() +
            "}";
    }
}
