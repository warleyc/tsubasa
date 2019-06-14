package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MUserRank.
 */
@Entity
@Table(name = "m_user_rank")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MUserRank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_rank", nullable = false)
    private Integer rank;

    @NotNull
    @Column(name = "exp", nullable = false)
    private Integer exp;

    @NotNull
    @Column(name = "quest_ap", nullable = false)
    private Integer questAp;

    @NotNull
    @Column(name = "max_friend_count", nullable = false)
    private Integer maxFriendCount;

    
    @Lob
    @Column(name = "rankup_serif", nullable = false)
    private String rankupSerif;

    
    @Lob
    @Column(name = "chara_asset_name", nullable = false)
    private String charaAssetName;

    
    @Lob
    @Column(name = "voice_chara_id", nullable = false)
    private String voiceCharaId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public MUserRank rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getExp() {
        return exp;
    }

    public MUserRank exp(Integer exp) {
        this.exp = exp;
        return this;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getQuestAp() {
        return questAp;
    }

    public MUserRank questAp(Integer questAp) {
        this.questAp = questAp;
        return this;
    }

    public void setQuestAp(Integer questAp) {
        this.questAp = questAp;
    }

    public Integer getMaxFriendCount() {
        return maxFriendCount;
    }

    public MUserRank maxFriendCount(Integer maxFriendCount) {
        this.maxFriendCount = maxFriendCount;
        return this;
    }

    public void setMaxFriendCount(Integer maxFriendCount) {
        this.maxFriendCount = maxFriendCount;
    }

    public String getRankupSerif() {
        return rankupSerif;
    }

    public MUserRank rankupSerif(String rankupSerif) {
        this.rankupSerif = rankupSerif;
        return this;
    }

    public void setRankupSerif(String rankupSerif) {
        this.rankupSerif = rankupSerif;
    }

    public String getCharaAssetName() {
        return charaAssetName;
    }

    public MUserRank charaAssetName(String charaAssetName) {
        this.charaAssetName = charaAssetName;
        return this;
    }

    public void setCharaAssetName(String charaAssetName) {
        this.charaAssetName = charaAssetName;
    }

    public String getVoiceCharaId() {
        return voiceCharaId;
    }

    public MUserRank voiceCharaId(String voiceCharaId) {
        this.voiceCharaId = voiceCharaId;
        return this;
    }

    public void setVoiceCharaId(String voiceCharaId) {
        this.voiceCharaId = voiceCharaId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MUserRank)) {
            return false;
        }
        return id != null && id.equals(((MUserRank) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MUserRank{" +
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
