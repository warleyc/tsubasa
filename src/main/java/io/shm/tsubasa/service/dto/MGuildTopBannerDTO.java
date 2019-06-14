package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGuildTopBanner} entity.
 */
public class MGuildTopBannerDTO implements Serializable {

    private Long id;

    
    @Lob
    private String assetPath;

    @NotNull
    private Integer guildBannerType;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetPath() {
        return assetPath;
    }

    public void setAssetPath(String assetPath) {
        this.assetPath = assetPath;
    }

    public Integer getGuildBannerType() {
        return guildBannerType;
    }

    public void setGuildBannerType(Integer guildBannerType) {
        this.guildBannerType = guildBannerType;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGuildTopBannerDTO mGuildTopBannerDTO = (MGuildTopBannerDTO) o;
        if (mGuildTopBannerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGuildTopBannerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGuildTopBannerDTO{" +
            "id=" + getId() +
            ", assetPath='" + getAssetPath() + "'" +
            ", guildBannerType=" + getGuildBannerType() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
