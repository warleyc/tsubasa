package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MStoreReviewUrl.
 */
@Entity
@Table(name = "m_store_review_url")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MStoreReviewUrl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "platform", nullable = false)
    private Integer platform;

    
    @Lob
    @Column(name = "url", nullable = false)
    private String url;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlatform() {
        return platform;
    }

    public MStoreReviewUrl platform(Integer platform) {
        this.platform = platform;
        return this;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public MStoreReviewUrl url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MStoreReviewUrl)) {
            return false;
        }
        return id != null && id.equals(((MStoreReviewUrl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MStoreReviewUrl{" +
            "id=" + getId() +
            ", platform=" + getPlatform() +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
