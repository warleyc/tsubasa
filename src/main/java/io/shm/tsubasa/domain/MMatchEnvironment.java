package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MMatchEnvironment.
 */
@Entity
@Table(name = "m_match_environment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMatchEnvironment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "category", nullable = false)
    private Integer category;

    
    @Lob
    @Column(name = "sky_tex", nullable = false)
    private String skyTex;

    
    @Lob
    @Column(name = "ball", nullable = false)
    private String ball;

    
    @Lob
    @Column(name = "stadium", nullable = false)
    private String stadium;

    @Lob
    @Column(name = "rainy_asset_name")
    private String rainyAssetName;

    @NotNull
    @Column(name = "is_play_rainy_sound", nullable = false)
    private Integer isPlayRainySound;

    
    @Lob
    @Column(name = "offense_start_bgm", nullable = false)
    private String offenseStartBgm;

    
    @Lob
    @Column(name = "offense_stop_bgm", nullable = false)
    private String offenseStopBgm;

    
    @Lob
    @Column(name = "defense_start_bgm", nullable = false)
    private String defenseStartBgm;

    
    @Lob
    @Column(name = "defense_stop_bgm", nullable = false)
    private String defenseStopBgm;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public MMatchEnvironment category(Integer category) {
        this.category = category;
        return this;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getSkyTex() {
        return skyTex;
    }

    public MMatchEnvironment skyTex(String skyTex) {
        this.skyTex = skyTex;
        return this;
    }

    public void setSkyTex(String skyTex) {
        this.skyTex = skyTex;
    }

    public String getBall() {
        return ball;
    }

    public MMatchEnvironment ball(String ball) {
        this.ball = ball;
        return this;
    }

    public void setBall(String ball) {
        this.ball = ball;
    }

    public String getStadium() {
        return stadium;
    }

    public MMatchEnvironment stadium(String stadium) {
        this.stadium = stadium;
        return this;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getRainyAssetName() {
        return rainyAssetName;
    }

    public MMatchEnvironment rainyAssetName(String rainyAssetName) {
        this.rainyAssetName = rainyAssetName;
        return this;
    }

    public void setRainyAssetName(String rainyAssetName) {
        this.rainyAssetName = rainyAssetName;
    }

    public Integer getIsPlayRainySound() {
        return isPlayRainySound;
    }

    public MMatchEnvironment isPlayRainySound(Integer isPlayRainySound) {
        this.isPlayRainySound = isPlayRainySound;
        return this;
    }

    public void setIsPlayRainySound(Integer isPlayRainySound) {
        this.isPlayRainySound = isPlayRainySound;
    }

    public String getOffenseStartBgm() {
        return offenseStartBgm;
    }

    public MMatchEnvironment offenseStartBgm(String offenseStartBgm) {
        this.offenseStartBgm = offenseStartBgm;
        return this;
    }

    public void setOffenseStartBgm(String offenseStartBgm) {
        this.offenseStartBgm = offenseStartBgm;
    }

    public String getOffenseStopBgm() {
        return offenseStopBgm;
    }

    public MMatchEnvironment offenseStopBgm(String offenseStopBgm) {
        this.offenseStopBgm = offenseStopBgm;
        return this;
    }

    public void setOffenseStopBgm(String offenseStopBgm) {
        this.offenseStopBgm = offenseStopBgm;
    }

    public String getDefenseStartBgm() {
        return defenseStartBgm;
    }

    public MMatchEnvironment defenseStartBgm(String defenseStartBgm) {
        this.defenseStartBgm = defenseStartBgm;
        return this;
    }

    public void setDefenseStartBgm(String defenseStartBgm) {
        this.defenseStartBgm = defenseStartBgm;
    }

    public String getDefenseStopBgm() {
        return defenseStopBgm;
    }

    public MMatchEnvironment defenseStopBgm(String defenseStopBgm) {
        this.defenseStopBgm = defenseStopBgm;
        return this;
    }

    public void setDefenseStopBgm(String defenseStopBgm) {
        this.defenseStopBgm = defenseStopBgm;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MMatchEnvironment)) {
            return false;
        }
        return id != null && id.equals(((MMatchEnvironment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMatchEnvironment{" +
            "id=" + getId() +
            ", category=" + getCategory() +
            ", skyTex='" + getSkyTex() + "'" +
            ", ball='" + getBall() + "'" +
            ", stadium='" + getStadium() + "'" +
            ", rainyAssetName='" + getRainyAssetName() + "'" +
            ", isPlayRainySound=" + getIsPlayRainySound() +
            ", offenseStartBgm='" + getOffenseStartBgm() + "'" +
            ", offenseStopBgm='" + getOffenseStopBgm() + "'" +
            ", defenseStartBgm='" + getDefenseStartBgm() + "'" +
            ", defenseStopBgm='" + getDefenseStopBgm() + "'" +
            "}";
    }
}
