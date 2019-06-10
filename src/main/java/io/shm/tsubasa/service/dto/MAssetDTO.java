package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MAsset} entity.
 */
public class MAssetDTO implements Serializable {

    private Long id;

    
    @Lob
    private String assetBundleName;

    
    @Lob
    private String tag;

    
    @Lob
    private String dependencies;

    @NotNull
    private Integer i18n;

    
    @Lob
    private String platform;

    
    @Lob
    private String packName;

    @NotNull
    private Integer head;

    @NotNull
    private Integer size;

    @NotNull
    private Integer key1;

    @NotNull
    private Integer key2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetBundleName() {
        return assetBundleName;
    }

    public void setAssetBundleName(String assetBundleName) {
        this.assetBundleName = assetBundleName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDependencies() {
        return dependencies;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }

    public Integer geti18n() {
        return i18n;
    }

    public void seti18n(Integer i18n) {
        this.i18n = i18n;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getKey1() {
        return key1;
    }

    public void setKey1(Integer key1) {
        this.key1 = key1;
    }

    public Integer getKey2() {
        return key2;
    }

    public void setKey2(Integer key2) {
        this.key2 = key2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MAssetDTO mAssetDTO = (MAssetDTO) o;
        if (mAssetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mAssetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MAssetDTO{" +
            "id=" + getId() +
            ", assetBundleName='" + getAssetBundleName() + "'" +
            ", tag='" + getTag() + "'" +
            ", dependencies='" + getDependencies() + "'" +
            ", i18n=" + geti18n() +
            ", platform='" + getPlatform() + "'" +
            ", packName='" + getPackName() + "'" +
            ", head=" + getHead() +
            ", size=" + getSize() +
            ", key1=" + getKey1() +
            ", key2=" + getKey2() +
            "}";
    }
}
