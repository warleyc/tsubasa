package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MModelUniformUp.
 */
@Entity
@Table(name = "m_model_uniform_up")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MModelUniformUp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "uniform_up_id", nullable = false)
    private Integer uniformUpId;

    @NotNull
    @Column(name = "default_dressing_type", nullable = false)
    private Integer defaultDressingType;

    @NotNull
    @Column(name = "uniform_model", nullable = false)
    private Integer uniformModel;

    @NotNull
    @Column(name = "uniform_texture", nullable = false)
    private Integer uniformTexture;

    @NotNull
    @Column(name = "uniform_no_texture", nullable = false)
    private Integer uniformNoTexture;

    @Lob
    @Column(name = "default_color")
    private String defaultColor;

    
    @Lob
    @Column(name = "uniform_no_color", nullable = false)
    private String uniformNoColor;

    @NotNull
    @Column(name = "number_chest", nullable = false)
    private Integer numberChest;

    @NotNull
    @Column(name = "number_belly", nullable = false)
    private Integer numberBelly;

    @NotNull
    @Column(name = "number_back", nullable = false)
    private Integer numberBack;

    @Column(name = "uniform_no_position_x")
    private Integer uniformNoPositionX;

    @Column(name = "uniform_no_position_y")
    private Integer uniformNoPositionY;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUniformUpId() {
        return uniformUpId;
    }

    public MModelUniformUp uniformUpId(Integer uniformUpId) {
        this.uniformUpId = uniformUpId;
        return this;
    }

    public void setUniformUpId(Integer uniformUpId) {
        this.uniformUpId = uniformUpId;
    }

    public Integer getDefaultDressingType() {
        return defaultDressingType;
    }

    public MModelUniformUp defaultDressingType(Integer defaultDressingType) {
        this.defaultDressingType = defaultDressingType;
        return this;
    }

    public void setDefaultDressingType(Integer defaultDressingType) {
        this.defaultDressingType = defaultDressingType;
    }

    public Integer getUniformModel() {
        return uniformModel;
    }

    public MModelUniformUp uniformModel(Integer uniformModel) {
        this.uniformModel = uniformModel;
        return this;
    }

    public void setUniformModel(Integer uniformModel) {
        this.uniformModel = uniformModel;
    }

    public Integer getUniformTexture() {
        return uniformTexture;
    }

    public MModelUniformUp uniformTexture(Integer uniformTexture) {
        this.uniformTexture = uniformTexture;
        return this;
    }

    public void setUniformTexture(Integer uniformTexture) {
        this.uniformTexture = uniformTexture;
    }

    public Integer getUniformNoTexture() {
        return uniformNoTexture;
    }

    public MModelUniformUp uniformNoTexture(Integer uniformNoTexture) {
        this.uniformNoTexture = uniformNoTexture;
        return this;
    }

    public void setUniformNoTexture(Integer uniformNoTexture) {
        this.uniformNoTexture = uniformNoTexture;
    }

    public String getDefaultColor() {
        return defaultColor;
    }

    public MModelUniformUp defaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
        return this;
    }

    public void setDefaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
    }

    public String getUniformNoColor() {
        return uniformNoColor;
    }

    public MModelUniformUp uniformNoColor(String uniformNoColor) {
        this.uniformNoColor = uniformNoColor;
        return this;
    }

    public void setUniformNoColor(String uniformNoColor) {
        this.uniformNoColor = uniformNoColor;
    }

    public Integer getNumberChest() {
        return numberChest;
    }

    public MModelUniformUp numberChest(Integer numberChest) {
        this.numberChest = numberChest;
        return this;
    }

    public void setNumberChest(Integer numberChest) {
        this.numberChest = numberChest;
    }

    public Integer getNumberBelly() {
        return numberBelly;
    }

    public MModelUniformUp numberBelly(Integer numberBelly) {
        this.numberBelly = numberBelly;
        return this;
    }

    public void setNumberBelly(Integer numberBelly) {
        this.numberBelly = numberBelly;
    }

    public Integer getNumberBack() {
        return numberBack;
    }

    public MModelUniformUp numberBack(Integer numberBack) {
        this.numberBack = numberBack;
        return this;
    }

    public void setNumberBack(Integer numberBack) {
        this.numberBack = numberBack;
    }

    public Integer getUniformNoPositionX() {
        return uniformNoPositionX;
    }

    public MModelUniformUp uniformNoPositionX(Integer uniformNoPositionX) {
        this.uniformNoPositionX = uniformNoPositionX;
        return this;
    }

    public void setUniformNoPositionX(Integer uniformNoPositionX) {
        this.uniformNoPositionX = uniformNoPositionX;
    }

    public Integer getUniformNoPositionY() {
        return uniformNoPositionY;
    }

    public MModelUniformUp uniformNoPositionY(Integer uniformNoPositionY) {
        this.uniformNoPositionY = uniformNoPositionY;
        return this;
    }

    public void setUniformNoPositionY(Integer uniformNoPositionY) {
        this.uniformNoPositionY = uniformNoPositionY;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MModelUniformUp)) {
            return false;
        }
        return id != null && id.equals(((MModelUniformUp) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MModelUniformUp{" +
            "id=" + getId() +
            ", uniformUpId=" + getUniformUpId() +
            ", defaultDressingType=" + getDefaultDressingType() +
            ", uniformModel=" + getUniformModel() +
            ", uniformTexture=" + getUniformTexture() +
            ", uniformNoTexture=" + getUniformNoTexture() +
            ", defaultColor='" + getDefaultColor() + "'" +
            ", uniformNoColor='" + getUniformNoColor() + "'" +
            ", numberChest=" + getNumberChest() +
            ", numberBelly=" + getNumberBelly() +
            ", numberBack=" + getNumberBack() +
            ", uniformNoPositionX=" + getUniformNoPositionX() +
            ", uniformNoPositionY=" + getUniformNoPositionY() +
            "}";
    }
}
