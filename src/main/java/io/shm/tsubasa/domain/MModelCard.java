package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MModelCard.
 */
@Entity
@Table(name = "m_model_card")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MModelCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "hair_model", nullable = false)
    private Integer hairModel;

    @NotNull
    @Column(name = "hair_texture", nullable = false)
    private Integer hairTexture;

    @NotNull
    @Column(name = "head_model", nullable = false)
    private Integer headModel;

    @NotNull
    @Column(name = "head_texture", nullable = false)
    private Integer headTexture;

    @NotNull
    @Column(name = "skin_texture", nullable = false)
    private Integer skinTexture;

    @NotNull
    @Column(name = "shoes_model", nullable = false)
    private Integer shoesModel;

    @NotNull
    @Column(name = "shoes_texture", nullable = false)
    private Integer shoesTexture;

    @Column(name = "globe_texture")
    private Integer globeTexture;

    @Column(name = "unique_model")
    private Integer uniqueModel;

    @Column(name = "unique_texture")
    private Integer uniqueTexture;

    @NotNull
    @Column(name = "dressing_type_up", nullable = false)
    private Integer dressingTypeUp;

    @NotNull
    @Column(name = "dressing_type_bottom", nullable = false)
    private Integer dressingTypeBottom;

    @NotNull
    @Column(name = "height", nullable = false)
    private Integer height;

    @NotNull
    @Column(name = "width", nullable = false)
    private Integer width;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MPlayableCard> mPlayableCards = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHairModel() {
        return hairModel;
    }

    public MModelCard hairModel(Integer hairModel) {
        this.hairModel = hairModel;
        return this;
    }

    public void setHairModel(Integer hairModel) {
        this.hairModel = hairModel;
    }

    public Integer getHairTexture() {
        return hairTexture;
    }

    public MModelCard hairTexture(Integer hairTexture) {
        this.hairTexture = hairTexture;
        return this;
    }

    public void setHairTexture(Integer hairTexture) {
        this.hairTexture = hairTexture;
    }

    public Integer getHeadModel() {
        return headModel;
    }

    public MModelCard headModel(Integer headModel) {
        this.headModel = headModel;
        return this;
    }

    public void setHeadModel(Integer headModel) {
        this.headModel = headModel;
    }

    public Integer getHeadTexture() {
        return headTexture;
    }

    public MModelCard headTexture(Integer headTexture) {
        this.headTexture = headTexture;
        return this;
    }

    public void setHeadTexture(Integer headTexture) {
        this.headTexture = headTexture;
    }

    public Integer getSkinTexture() {
        return skinTexture;
    }

    public MModelCard skinTexture(Integer skinTexture) {
        this.skinTexture = skinTexture;
        return this;
    }

    public void setSkinTexture(Integer skinTexture) {
        this.skinTexture = skinTexture;
    }

    public Integer getShoesModel() {
        return shoesModel;
    }

    public MModelCard shoesModel(Integer shoesModel) {
        this.shoesModel = shoesModel;
        return this;
    }

    public void setShoesModel(Integer shoesModel) {
        this.shoesModel = shoesModel;
    }

    public Integer getShoesTexture() {
        return shoesTexture;
    }

    public MModelCard shoesTexture(Integer shoesTexture) {
        this.shoesTexture = shoesTexture;
        return this;
    }

    public void setShoesTexture(Integer shoesTexture) {
        this.shoesTexture = shoesTexture;
    }

    public Integer getGlobeTexture() {
        return globeTexture;
    }

    public MModelCard globeTexture(Integer globeTexture) {
        this.globeTexture = globeTexture;
        return this;
    }

    public void setGlobeTexture(Integer globeTexture) {
        this.globeTexture = globeTexture;
    }

    public Integer getUniqueModel() {
        return uniqueModel;
    }

    public MModelCard uniqueModel(Integer uniqueModel) {
        this.uniqueModel = uniqueModel;
        return this;
    }

    public void setUniqueModel(Integer uniqueModel) {
        this.uniqueModel = uniqueModel;
    }

    public Integer getUniqueTexture() {
        return uniqueTexture;
    }

    public MModelCard uniqueTexture(Integer uniqueTexture) {
        this.uniqueTexture = uniqueTexture;
        return this;
    }

    public void setUniqueTexture(Integer uniqueTexture) {
        this.uniqueTexture = uniqueTexture;
    }

    public Integer getDressingTypeUp() {
        return dressingTypeUp;
    }

    public MModelCard dressingTypeUp(Integer dressingTypeUp) {
        this.dressingTypeUp = dressingTypeUp;
        return this;
    }

    public void setDressingTypeUp(Integer dressingTypeUp) {
        this.dressingTypeUp = dressingTypeUp;
    }

    public Integer getDressingTypeBottom() {
        return dressingTypeBottom;
    }

    public MModelCard dressingTypeBottom(Integer dressingTypeBottom) {
        this.dressingTypeBottom = dressingTypeBottom;
        return this;
    }

    public void setDressingTypeBottom(Integer dressingTypeBottom) {
        this.dressingTypeBottom = dressingTypeBottom;
    }

    public Integer getHeight() {
        return height;
    }

    public MModelCard height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public MModelCard width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Set<MPlayableCard> getMPlayableCards() {
        return mPlayableCards;
    }

    public MModelCard mPlayableCards(Set<MPlayableCard> mPlayableCards) {
        this.mPlayableCards = mPlayableCards;
        return this;
    }

    public MModelCard addMPlayableCard(MPlayableCard mPlayableCard) {
        this.mPlayableCards.add(mPlayableCard);
        mPlayableCard.setId(this);
        return this;
    }

    public MModelCard removeMPlayableCard(MPlayableCard mPlayableCard) {
        this.mPlayableCards.remove(mPlayableCard);
        mPlayableCard.setId(null);
        return this;
    }

    public void setMPlayableCards(Set<MPlayableCard> mPlayableCards) {
        this.mPlayableCards = mPlayableCards;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MModelCard)) {
            return false;
        }
        return id != null && id.equals(((MModelCard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MModelCard{" +
            "id=" + getId() +
            ", hairModel=" + getHairModel() +
            ", hairTexture=" + getHairTexture() +
            ", headModel=" + getHeadModel() +
            ", headTexture=" + getHeadTexture() +
            ", skinTexture=" + getSkinTexture() +
            ", shoesModel=" + getShoesModel() +
            ", shoesTexture=" + getShoesTexture() +
            ", globeTexture=" + getGlobeTexture() +
            ", uniqueModel=" + getUniqueModel() +
            ", uniqueTexture=" + getUniqueTexture() +
            ", dressingTypeUp=" + getDressingTypeUp() +
            ", dressingTypeBottom=" + getDressingTypeBottom() +
            ", height=" + getHeight() +
            ", width=" + getWidth() +
            "}";
    }
}
