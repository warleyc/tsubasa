package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MAsset.
 */
@Entity
@Table(name = "m_asset")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MAsset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "asset_bundle_name", nullable = false)
    private String assetBundleName;

    
    @Lob
    @Column(name = "tag", nullable = false)
    private String tag;

    
    @Lob
    @Column(name = "dependencies", nullable = false)
    private String dependencies;

    @NotNull
    @Column(name = "i_18_n", nullable = false)
    private Integer i18n;

    
    @Lob
    @Column(name = "platform", nullable = false)
    private String platform;

    
    @Lob
    @Column(name = "pack_name", nullable = false)
    private String packName;

    @NotNull
    @Column(name = "head", nullable = false)
    private Integer head;

    @NotNull
    @Column(name = "size", nullable = false)
    private Integer size;

    @NotNull
    @Column(name = "key_1", nullable = false)
    private Integer key1;

    @NotNull
    @Column(name = "key_2", nullable = false)
    private Integer key2;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetBundleName() {
        return assetBundleName;
    }

    public MAsset assetBundleName(String assetBundleName) {
        this.assetBundleName = assetBundleName;
        return this;
    }

    public void setAssetBundleName(String assetBundleName) {
        this.assetBundleName = assetBundleName;
    }

    public String getTag() {
        return tag;
    }

    public MAsset tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDependencies() {
        return dependencies;
    }

    public MAsset dependencies(String dependencies) {
        this.dependencies = dependencies;
        return this;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }

    public Integer geti18n() {
        return i18n;
    }

    public MAsset i18n(Integer i18n) {
        this.i18n = i18n;
        return this;
    }

    public void seti18n(Integer i18n) {
        this.i18n = i18n;
    }

    public String getPlatform() {
        return platform;
    }

    public MAsset platform(String platform) {
        this.platform = platform;
        return this;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPackName() {
        return packName;
    }

    public MAsset packName(String packName) {
        this.packName = packName;
        return this;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public Integer getHead() {
        return head;
    }

    public MAsset head(Integer head) {
        this.head = head;
        return this;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getSize() {
        return size;
    }

    public MAsset size(Integer size) {
        this.size = size;
        return this;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getKey1() {
        return key1;
    }

    public MAsset key1(Integer key1) {
        this.key1 = key1;
        return this;
    }

    public void setKey1(Integer key1) {
        this.key1 = key1;
    }

    public Integer getKey2() {
        return key2;
    }

    public MAsset key2(Integer key2) {
        this.key2 = key2;
        return this;
    }

    public void setKey2(Integer key2) {
        this.key2 = key2;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MAsset)) {
            return false;
        }
        return id != null && id.equals(((MAsset) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MAsset{" +
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
