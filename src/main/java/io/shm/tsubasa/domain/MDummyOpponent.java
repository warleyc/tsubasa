package io.shm.tsubasa.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MDummyOpponent.
 */
@Entity
@Table(name = "m_dummy_opponent")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MDummyOpponent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "npc_deck_id", nullable = false)
    private Integer npcDeckId;

    @NotNull
    @Column(name = "total_parameter", nullable = false)
    private Integer totalParameter;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "jhi_rank", nullable = false)
    private Integer rank;

    @NotNull
    @Column(name = "emblem_id", nullable = false)
    private Integer emblemId;

    @NotNull
    @Column(name = "fp_uniform_up_id", nullable = false)
    private Integer fpUniformUpId;

    @Lob
    @Column(name = "fp_uniform_up_color")
    private String fpUniformUpColor;

    @NotNull
    @Column(name = "fp_uniform_bottom_id", nullable = false)
    private Integer fpUniformBottomId;

    @Lob
    @Column(name = "fp_uniform_bottom_color")
    private String fpUniformBottomColor;

    @NotNull
    @Column(name = "gk_uniform_up_id", nullable = false)
    private Integer gkUniformUpId;

    @Lob
    @Column(name = "gk_uniform_up_color")
    private String gkUniformUpColor;

    @NotNull
    @Column(name = "gk_uniform_bottom_id", nullable = false)
    private Integer gkUniformBottomId;

    @Lob
    @Column(name = "gk_uniform_bottom_color")
    private String gkUniformBottomColor;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mDummyOpponents")
    private MNpcDeck id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNpcDeckId() {
        return npcDeckId;
    }

    public MDummyOpponent npcDeckId(Integer npcDeckId) {
        this.npcDeckId = npcDeckId;
        return this;
    }

    public void setNpcDeckId(Integer npcDeckId) {
        this.npcDeckId = npcDeckId;
    }

    public Integer getTotalParameter() {
        return totalParameter;
    }

    public MDummyOpponent totalParameter(Integer totalParameter) {
        this.totalParameter = totalParameter;
        return this;
    }

    public void setTotalParameter(Integer totalParameter) {
        this.totalParameter = totalParameter;
    }

    public String getName() {
        return name;
    }

    public MDummyOpponent name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public MDummyOpponent rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getEmblemId() {
        return emblemId;
    }

    public MDummyOpponent emblemId(Integer emblemId) {
        this.emblemId = emblemId;
        return this;
    }

    public void setEmblemId(Integer emblemId) {
        this.emblemId = emblemId;
    }

    public Integer getFpUniformUpId() {
        return fpUniformUpId;
    }

    public MDummyOpponent fpUniformUpId(Integer fpUniformUpId) {
        this.fpUniformUpId = fpUniformUpId;
        return this;
    }

    public void setFpUniformUpId(Integer fpUniformUpId) {
        this.fpUniformUpId = fpUniformUpId;
    }

    public String getFpUniformUpColor() {
        return fpUniformUpColor;
    }

    public MDummyOpponent fpUniformUpColor(String fpUniformUpColor) {
        this.fpUniformUpColor = fpUniformUpColor;
        return this;
    }

    public void setFpUniformUpColor(String fpUniformUpColor) {
        this.fpUniformUpColor = fpUniformUpColor;
    }

    public Integer getFpUniformBottomId() {
        return fpUniformBottomId;
    }

    public MDummyOpponent fpUniformBottomId(Integer fpUniformBottomId) {
        this.fpUniformBottomId = fpUniformBottomId;
        return this;
    }

    public void setFpUniformBottomId(Integer fpUniformBottomId) {
        this.fpUniformBottomId = fpUniformBottomId;
    }

    public String getFpUniformBottomColor() {
        return fpUniformBottomColor;
    }

    public MDummyOpponent fpUniformBottomColor(String fpUniformBottomColor) {
        this.fpUniformBottomColor = fpUniformBottomColor;
        return this;
    }

    public void setFpUniformBottomColor(String fpUniformBottomColor) {
        this.fpUniformBottomColor = fpUniformBottomColor;
    }

    public Integer getGkUniformUpId() {
        return gkUniformUpId;
    }

    public MDummyOpponent gkUniformUpId(Integer gkUniformUpId) {
        this.gkUniformUpId = gkUniformUpId;
        return this;
    }

    public void setGkUniformUpId(Integer gkUniformUpId) {
        this.gkUniformUpId = gkUniformUpId;
    }

    public String getGkUniformUpColor() {
        return gkUniformUpColor;
    }

    public MDummyOpponent gkUniformUpColor(String gkUniformUpColor) {
        this.gkUniformUpColor = gkUniformUpColor;
        return this;
    }

    public void setGkUniformUpColor(String gkUniformUpColor) {
        this.gkUniformUpColor = gkUniformUpColor;
    }

    public Integer getGkUniformBottomId() {
        return gkUniformBottomId;
    }

    public MDummyOpponent gkUniformBottomId(Integer gkUniformBottomId) {
        this.gkUniformBottomId = gkUniformBottomId;
        return this;
    }

    public void setGkUniformBottomId(Integer gkUniformBottomId) {
        this.gkUniformBottomId = gkUniformBottomId;
    }

    public String getGkUniformBottomColor() {
        return gkUniformBottomColor;
    }

    public MDummyOpponent gkUniformBottomColor(String gkUniformBottomColor) {
        this.gkUniformBottomColor = gkUniformBottomColor;
        return this;
    }

    public void setGkUniformBottomColor(String gkUniformBottomColor) {
        this.gkUniformBottomColor = gkUniformBottomColor;
    }

    public MNpcDeck getId() {
        return id;
    }

    public MDummyOpponent id(MNpcDeck mNpcDeck) {
        this.id = mNpcDeck;
        return this;
    }

    public void setId(MNpcDeck mNpcDeck) {
        this.id = mNpcDeck;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MDummyOpponent)) {
            return false;
        }
        return id != null && id.equals(((MDummyOpponent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MDummyOpponent{" +
            "id=" + getId() +
            ", npcDeckId=" + getNpcDeckId() +
            ", totalParameter=" + getTotalParameter() +
            ", name='" + getName() + "'" +
            ", rank=" + getRank() +
            ", emblemId=" + getEmblemId() +
            ", fpUniformUpId=" + getFpUniformUpId() +
            ", fpUniformUpColor='" + getFpUniformUpColor() + "'" +
            ", fpUniformBottomId=" + getFpUniformBottomId() +
            ", fpUniformBottomColor='" + getFpUniformBottomColor() + "'" +
            ", gkUniformUpId=" + getGkUniformUpId() +
            ", gkUniformUpColor='" + getGkUniformUpColor() + "'" +
            ", gkUniformBottomId=" + getGkUniformBottomId() +
            ", gkUniformBottomColor='" + getGkUniformBottomColor() + "'" +
            "}";
    }
}
