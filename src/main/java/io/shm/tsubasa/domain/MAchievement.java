package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MAchievement.
 */
@Entity
@Table(name = "m_achievement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MAchievement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "achievement_id", nullable = false)
    private String achievementId;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "jhi_type", nullable = false)
    private Integer type;

    @NotNull
    @Column(name = "mission_id", nullable = false)
    private Integer missionId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mAchievements")
    private MMission mmission;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAchievementId() {
        return achievementId;
    }

    public MAchievement achievementId(String achievementId) {
        this.achievementId = achievementId;
        return this;
    }

    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    public String getName() {
        return name;
    }

    public MAchievement name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public MAchievement type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public MAchievement missionId(Integer missionId) {
        this.missionId = missionId;
        return this;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public MMission getMmission() {
        return mmission;
    }

    public MAchievement mmission(MMission mMission) {
        this.mmission = mMission;
        return this;
    }

    public void setMmission(MMission mMission) {
        this.mmission = mMission;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MAchievement)) {
            return false;
        }
        return id != null && id.equals(((MAchievement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MAchievement{" +
            "id=" + getId() +
            ", achievementId='" + getAchievementId() + "'" +
            ", name='" + getName() + "'" +
            ", type=" + getType() +
            ", missionId=" + getMissionId() +
            "}";
    }
}
