package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTargetPlayableCardGroup.
 */
@Entity
@Table(name = "m_target_playable_card_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTargetPlayableCardGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "card_id", nullable = false)
    private Integer cardId;

    @NotNull
    @Column(name = "is_show_thumbnail", nullable = false)
    private Integer isShowThumbnail;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mTargetPlayableCardGroups")
    private MPlayableCard id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public MTargetPlayableCardGroup groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public MTargetPlayableCardGroup cardId(Integer cardId) {
        this.cardId = cardId;
        return this;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getIsShowThumbnail() {
        return isShowThumbnail;
    }

    public MTargetPlayableCardGroup isShowThumbnail(Integer isShowThumbnail) {
        this.isShowThumbnail = isShowThumbnail;
        return this;
    }

    public void setIsShowThumbnail(Integer isShowThumbnail) {
        this.isShowThumbnail = isShowThumbnail;
    }

    public MPlayableCard getId() {
        return id;
    }

    public MTargetPlayableCardGroup id(MPlayableCard mPlayableCard) {
        this.id = mPlayableCard;
        return this;
    }

    public void setId(MPlayableCard mPlayableCard) {
        this.id = mPlayableCard;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTargetPlayableCardGroup)) {
            return false;
        }
        return id != null && id.equals(((MTargetPlayableCardGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTargetPlayableCardGroup{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", cardId=" + getCardId() +
            ", isShowThumbnail=" + getIsShowThumbnail() +
            "}";
    }
}
