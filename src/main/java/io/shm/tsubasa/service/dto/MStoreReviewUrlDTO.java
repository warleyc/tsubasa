package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MStoreReviewUrl} entity.
 */
public class MStoreReviewUrlDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer platform;

    
    @Lob
    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MStoreReviewUrlDTO mStoreReviewUrlDTO = (MStoreReviewUrlDTO) o;
        if (mStoreReviewUrlDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mStoreReviewUrlDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MStoreReviewUrlDTO{" +
            "id=" + getId() +
            ", platform=" + getPlatform() +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
