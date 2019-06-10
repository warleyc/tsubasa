package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCardIllustAssets} entity.
 */
public class MCardIllustAssetsDTO implements Serializable {

    private Long id;

    
    @Lob
    private String assetName;

    @Lob
    private String subAssetName;

    @Lob
    private String offset;

    
    @Lob
    private String backgroundAssetName;

    @Lob
    private String decorationAssetName;

    @Lob
    private String effectAssetName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getSubAssetName() {
        return subAssetName;
    }

    public void setSubAssetName(String subAssetName) {
        this.subAssetName = subAssetName;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getBackgroundAssetName() {
        return backgroundAssetName;
    }

    public void setBackgroundAssetName(String backgroundAssetName) {
        this.backgroundAssetName = backgroundAssetName;
    }

    public String getDecorationAssetName() {
        return decorationAssetName;
    }

    public void setDecorationAssetName(String decorationAssetName) {
        this.decorationAssetName = decorationAssetName;
    }

    public String getEffectAssetName() {
        return effectAssetName;
    }

    public void setEffectAssetName(String effectAssetName) {
        this.effectAssetName = effectAssetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCardIllustAssetsDTO mCardIllustAssetsDTO = (MCardIllustAssetsDTO) o;
        if (mCardIllustAssetsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCardIllustAssetsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCardIllustAssetsDTO{" +
            "id=" + getId() +
            ", assetName='" + getAssetName() + "'" +
            ", subAssetName='" + getSubAssetName() + "'" +
            ", offset='" + getOffset() + "'" +
            ", backgroundAssetName='" + getBackgroundAssetName() + "'" +
            ", decorationAssetName='" + getDecorationAssetName() + "'" +
            ", effectAssetName='" + getEffectAssetName() + "'" +
            "}";
    }
}
