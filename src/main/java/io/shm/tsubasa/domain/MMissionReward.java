package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MMissionReward.
 */
@Entity
@Table(name = "m_mission_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMissionReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "content_type", nullable = false)
    private Integer contentType;

    @Column(name = "content_id")
    private Integer contentId;

    @NotNull
    @Column(name = "content_amount", nullable = false)
    private Integer contentAmount;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MGuildMission> mGuildMissions = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MMission> mMissions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getContentType() {
        return contentType;
    }

    public MMissionReward contentType(Integer contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public MMissionReward contentId(Integer contentId) {
        this.contentId = contentId;
        return this;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentAmount() {
        return contentAmount;
    }

    public MMissionReward contentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
        return this;
    }

    public void setContentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
    }

    public Set<MGuildMission> getMGuildMissions() {
        return mGuildMissions;
    }

    public MMissionReward mGuildMissions(Set<MGuildMission> mGuildMissions) {
        this.mGuildMissions = mGuildMissions;
        return this;
    }

    public MMissionReward addMGuildMission(MGuildMission mGuildMission) {
        this.mGuildMissions.add(mGuildMission);
        mGuildMission.setId(this);
        return this;
    }

    public MMissionReward removeMGuildMission(MGuildMission mGuildMission) {
        this.mGuildMissions.remove(mGuildMission);
        mGuildMission.setId(null);
        return this;
    }

    public void setMGuildMissions(Set<MGuildMission> mGuildMissions) {
        this.mGuildMissions = mGuildMissions;
    }

    public Set<MMission> getMMissions() {
        return mMissions;
    }

    public MMissionReward mMissions(Set<MMission> mMissions) {
        this.mMissions = mMissions;
        return this;
    }

    public MMissionReward addMMission(MMission mMission) {
        this.mMissions.add(mMission);
        mMission.setId(this);
        return this;
    }

    public MMissionReward removeMMission(MMission mMission) {
        this.mMissions.remove(mMission);
        mMission.setId(null);
        return this;
    }

    public void setMMissions(Set<MMission> mMissions) {
        this.mMissions = mMissions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MMissionReward)) {
            return false;
        }
        return id != null && id.equals(((MMissionReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMissionReward{" +
            "id=" + getId() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            "}";
    }
}
