package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MCutShootOrbit.
 */
@Entity
@Table(name = "m_cut_shoot_orbit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCutShootOrbit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "shoot_orbit", nullable = false)
    private Integer shootOrbit;

    @NotNull
    @Column(name = "shoot_result", nullable = false)
    private Integer shootResult;

    
    @Lob
    @Column(name = "offense_sequence_text", nullable = false)
    private String offenseSequenceText;

    
    @Lob
    @Column(name = "defense_sequence_text", nullable = false)
    private String defenseSequenceText;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getShootOrbit() {
        return shootOrbit;
    }

    public MCutShootOrbit shootOrbit(Integer shootOrbit) {
        this.shootOrbit = shootOrbit;
        return this;
    }

    public void setShootOrbit(Integer shootOrbit) {
        this.shootOrbit = shootOrbit;
    }

    public Integer getShootResult() {
        return shootResult;
    }

    public MCutShootOrbit shootResult(Integer shootResult) {
        this.shootResult = shootResult;
        return this;
    }

    public void setShootResult(Integer shootResult) {
        this.shootResult = shootResult;
    }

    public String getOffenseSequenceText() {
        return offenseSequenceText;
    }

    public MCutShootOrbit offenseSequenceText(String offenseSequenceText) {
        this.offenseSequenceText = offenseSequenceText;
        return this;
    }

    public void setOffenseSequenceText(String offenseSequenceText) {
        this.offenseSequenceText = offenseSequenceText;
    }

    public String getDefenseSequenceText() {
        return defenseSequenceText;
    }

    public MCutShootOrbit defenseSequenceText(String defenseSequenceText) {
        this.defenseSequenceText = defenseSequenceText;
        return this;
    }

    public void setDefenseSequenceText(String defenseSequenceText) {
        this.defenseSequenceText = defenseSequenceText;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCutShootOrbit)) {
            return false;
        }
        return id != null && id.equals(((MCutShootOrbit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCutShootOrbit{" +
            "id=" + getId() +
            ", shootOrbit=" + getShootOrbit() +
            ", shootResult=" + getShootResult() +
            ", offenseSequenceText='" + getOffenseSequenceText() + "'" +
            ", defenseSequenceText='" + getDefenseSequenceText() + "'" +
            "}";
    }
}
