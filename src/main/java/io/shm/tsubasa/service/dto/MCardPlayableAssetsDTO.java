package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCardPlayableAssets} entity.
 */
public class MCardPlayableAssetsDTO implements Serializable {

    private Long id;

    
    @Lob
    private String cutinImageAssetName;

    @Lob
    private String soundEventSuffix;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCutinImageAssetName() {
        return cutinImageAssetName;
    }

    public void setCutinImageAssetName(String cutinImageAssetName) {
        this.cutinImageAssetName = cutinImageAssetName;
    }

    public String getSoundEventSuffix() {
        return soundEventSuffix;
    }

    public void setSoundEventSuffix(String soundEventSuffix) {
        this.soundEventSuffix = soundEventSuffix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCardPlayableAssetsDTO mCardPlayableAssetsDTO = (MCardPlayableAssetsDTO) o;
        if (mCardPlayableAssetsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCardPlayableAssetsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCardPlayableAssetsDTO{" +
            "id=" + getId() +
            ", cutinImageAssetName='" + getCutinImageAssetName() + "'" +
            ", soundEventSuffix='" + getSoundEventSuffix() + "'" +
            "}";
    }
}
