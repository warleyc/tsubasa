package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MMatchFormation.
 */
@Entity
@Table(name = "m_match_formation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMatchFormation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "position_1", nullable = false)
    private Integer position1;

    @NotNull
    @Column(name = "position_2", nullable = false)
    private Integer position2;

    @NotNull
    @Column(name = "position_3", nullable = false)
    private Integer position3;

    @NotNull
    @Column(name = "position_4", nullable = false)
    private Integer position4;

    @NotNull
    @Column(name = "position_5", nullable = false)
    private Integer position5;

    @NotNull
    @Column(name = "position_6", nullable = false)
    private Integer position6;

    @NotNull
    @Column(name = "position_7", nullable = false)
    private Integer position7;

    @NotNull
    @Column(name = "position_8", nullable = false)
    private Integer position8;

    @NotNull
    @Column(name = "position_9", nullable = false)
    private Integer position9;

    @NotNull
    @Column(name = "position_10", nullable = false)
    private Integer position10;

    @NotNull
    @Column(name = "position_11", nullable = false)
    private Integer position11;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition1() {
        return position1;
    }

    public MMatchFormation position1(Integer position1) {
        this.position1 = position1;
        return this;
    }

    public void setPosition1(Integer position1) {
        this.position1 = position1;
    }

    public Integer getPosition2() {
        return position2;
    }

    public MMatchFormation position2(Integer position2) {
        this.position2 = position2;
        return this;
    }

    public void setPosition2(Integer position2) {
        this.position2 = position2;
    }

    public Integer getPosition3() {
        return position3;
    }

    public MMatchFormation position3(Integer position3) {
        this.position3 = position3;
        return this;
    }

    public void setPosition3(Integer position3) {
        this.position3 = position3;
    }

    public Integer getPosition4() {
        return position4;
    }

    public MMatchFormation position4(Integer position4) {
        this.position4 = position4;
        return this;
    }

    public void setPosition4(Integer position4) {
        this.position4 = position4;
    }

    public Integer getPosition5() {
        return position5;
    }

    public MMatchFormation position5(Integer position5) {
        this.position5 = position5;
        return this;
    }

    public void setPosition5(Integer position5) {
        this.position5 = position5;
    }

    public Integer getPosition6() {
        return position6;
    }

    public MMatchFormation position6(Integer position6) {
        this.position6 = position6;
        return this;
    }

    public void setPosition6(Integer position6) {
        this.position6 = position6;
    }

    public Integer getPosition7() {
        return position7;
    }

    public MMatchFormation position7(Integer position7) {
        this.position7 = position7;
        return this;
    }

    public void setPosition7(Integer position7) {
        this.position7 = position7;
    }

    public Integer getPosition8() {
        return position8;
    }

    public MMatchFormation position8(Integer position8) {
        this.position8 = position8;
        return this;
    }

    public void setPosition8(Integer position8) {
        this.position8 = position8;
    }

    public Integer getPosition9() {
        return position9;
    }

    public MMatchFormation position9(Integer position9) {
        this.position9 = position9;
        return this;
    }

    public void setPosition9(Integer position9) {
        this.position9 = position9;
    }

    public Integer getPosition10() {
        return position10;
    }

    public MMatchFormation position10(Integer position10) {
        this.position10 = position10;
        return this;
    }

    public void setPosition10(Integer position10) {
        this.position10 = position10;
    }

    public Integer getPosition11() {
        return position11;
    }

    public MMatchFormation position11(Integer position11) {
        this.position11 = position11;
        return this;
    }

    public void setPosition11(Integer position11) {
        this.position11 = position11;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MMatchFormation)) {
            return false;
        }
        return id != null && id.equals(((MMatchFormation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMatchFormation{" +
            "id=" + getId() +
            ", position1=" + getPosition1() +
            ", position2=" + getPosition2() +
            ", position3=" + getPosition3() +
            ", position4=" + getPosition4() +
            ", position5=" + getPosition5() +
            ", position6=" + getPosition6() +
            ", position7=" + getPosition7() +
            ", position8=" + getPosition8() +
            ", position9=" + getPosition9() +
            ", position10=" + getPosition10() +
            ", position11=" + getPosition11() +
            "}";
    }
}
