package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGuildTopBanner.
 */
@Entity
@Table(name = "m_guild_top_banner")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGuildTopBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "asset_path", nullable = false)
    private String assetPath;

    @NotNull
    @Column(name = "guild_banner_type", nullable = false)
    private Integer guildBannerType;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Integer startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private Integer endAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetPath() {
        return assetPath;
    }

    public MGuildTopBanner assetPath(String assetPath) {
        this.assetPath = assetPath;
        return this;
    }

    public void setAssetPath(String assetPath) {
        this.assetPath = assetPath;
    }

    public Integer getGuildBannerType() {
        return guildBannerType;
    }

    public MGuildTopBanner guildBannerType(Integer guildBannerType) {
        this.guildBannerType = guildBannerType;
        return this;
    }

    public void setGuildBannerType(Integer guildBannerType) {
        this.guildBannerType = guildBannerType;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MGuildTopBanner startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MGuildTopBanner endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGuildTopBanner)) {
            return false;
        }
        return id != null && id.equals(((MGuildTopBanner) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGuildTopBanner{" +
            "id=" + getId() +
            ", assetPath='" + getAssetPath() + "'" +
            ", guildBannerType=" + getGuildBannerType() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
