package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MUserRank} entity.
 */
public class MUserRankDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer rank;

    @NotNull
    private Integer exp;

    @NotNull
    private Integer questAp;

    @NotNull
    private Integer maxFriendCount;

    
    @Lob
    private String rankupSerif;

    
    @Lob
    private String charaAssetName;

    
    @Lob
    private String voiceCharaId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getQuestAp() {
        return questAp;
    }

    public void setQuestAp(Integer questAp) {
        this.questAp = questAp;
    }

    public Integer getMaxFriendCount() {
        return maxFriendCount;
    }

    public void setMaxFriendCount(Integer maxFriendCount) {
        this.maxFriendCount = maxFriendCount;
    }

    public String getRankupSerif() {
        return rankupSerif;
    }

    public void setRankupSerif(String rankupSerif) {
        this.rankupSerif = rankupSerif;
    }

    public String getCharaAssetName() {
        return charaAssetName;
    }

    public void setCharaAssetName(String charaAssetName) {
        this.charaAssetName = charaAssetName;
    }

    public String getVoiceCharaId() {
        return voiceCharaId;
    }

    public void setVoiceCharaId(String voiceCharaId) {
        this.voiceCharaId = voiceCharaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MUserRankDTO mUserRankDTO = (MUserRankDTO) o;
        if (mUserRankDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mUserRankDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MUserRankDTO{" +
            "id=" + getId() +
            ", rank=" + getRank() +
            ", exp=" + getExp() +
            ", questAp=" + getQuestAp() +
            ", maxFriendCount=" + getMaxFriendCount() +
            ", rankupSerif='" + getRankupSerif() + "'" +
            ", charaAssetName='" + getCharaAssetName() + "'" +
            ", voiceCharaId='" + getVoiceCharaId() + "'" +
            "}";
    }
}
