package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MRecommendShopMessage.
 */
@Entity
@Table(name = "m_recommend_shop_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MRecommendShopMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "message", nullable = false)
    private String message;

    @NotNull
    @Column(name = "has_sales_period", nullable = false)
    private Integer hasSalesPeriod;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public MRecommendShopMessage message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHasSalesPeriod() {
        return hasSalesPeriod;
    }

    public MRecommendShopMessage hasSalesPeriod(Integer hasSalesPeriod) {
        this.hasSalesPeriod = hasSalesPeriod;
        return this;
    }

    public void setHasSalesPeriod(Integer hasSalesPeriod) {
        this.hasSalesPeriod = hasSalesPeriod;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MRecommendShopMessage)) {
            return false;
        }
        return id != null && id.equals(((MRecommendShopMessage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MRecommendShopMessage{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", hasSalesPeriod=" + getHasSalesPeriod() +
            "}";
    }
}
