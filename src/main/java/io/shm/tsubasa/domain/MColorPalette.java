package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MColorPalette.
 */
@Entity
@Table(name = "m_color_palette")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MColorPalette implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "color", nullable = false)
    private String color;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public MColorPalette color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public MColorPalette orderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MColorPalette)) {
            return false;
        }
        return id != null && id.equals(((MColorPalette) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MColorPalette{" +
            "id=" + getId() +
            ", color='" + getColor() + "'" +
            ", orderNum=" + getOrderNum() +
            "}";
    }
}
