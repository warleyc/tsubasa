package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MModelUniformBottom.
 */
@Entity
@Table(name = "m_model_uniform_bottom")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MModelUniformBottom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "uniform_bottom_id", nullable = false)
    private Integer uniformBottomId;

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
    @Column(name = "number_right_leg", nullable = false)
    private Integer numberRightLeg;

    @NotNull
    @Column(name = "number_left_leg", nullable = false)
    private Integer numberLeftLeg;

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

    public Integer getUniformBottomId() {
        return uniformBottomId;
    }

    public MModelUniformBottom uniformBottomId(Integer uniformBottomId) {
        this.uniformBottomId = uniformBottomId;
        return this;
    }

    public void setUniformBottomId(Integer uniformBottomId) {
        this.uniformBottomId = uniformBottomId;
    }

    public Integer getDefaultDressingType() {
        return defaultDressingType;
    }

    public MModelUniformBottom defaultDressingType(Integer defaultDressingType) {
        this.defaultDressingType = defaultDressingType;
        return this;
    }

    public void setDefaultDressingType(Integer defaultDressingType) {
        this.defaultDressingType = defaultDressingType;
    }

    public Integer getUniformModel() {
        return uniformModel;
    }

    public MModelUniformBottom uniformModel(Integer uniformModel) {
        this.uniformModel = uniformModel;
        return this;
    }

    public void setUniformModel(Integer uniformModel) {
        this.uniformModel = uniformModel;
    }

    public Integer getUniformTexture() {
        return uniformTexture;
    }

    public MModelUniformBottom uniformTexture(Integer uniformTexture) {
        this.uniformTexture = uniformTexture;
        return this;
    }

    public void setUniformTexture(Integer uniformTexture) {
        this.uniformTexture = uniformTexture;
    }

    public Integer getUniformNoTexture() {
        return uniformNoTexture;
    }

    public MModelUniformBottom uniformNoTexture(Integer uniformNoTexture) {
        this.uniformNoTexture = uniformNoTexture;
        return this;
    }

    public void setUniformNoTexture(Integer uniformNoTexture) {
        this.uniformNoTexture = uniformNoTexture;
    }

    public String getDefaultColor() {
        return defaultColor;
    }

    public MModelUniformBottom defaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
        return this;
    }

    public void setDefaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
    }

    public String getUniformNoColor() {
        return uniformNoColor;
    }

    public MModelUniformBottom uniformNoColor(String uniformNoColor) {
        this.uniformNoColor = uniformNoColor;
        return this;
    }

    public void setUniformNoColor(String uniformNoColor) {
        this.uniformNoColor = uniformNoColor;
    }

    public Integer getNumberRightLeg() {
        return numberRightLeg;
    }

    public MModelUniformBottom numberRightLeg(Integer numberRightLeg) {
        this.numberRightLeg = numberRightLeg;
        return this;
    }

    public void setNumberRightLeg(Integer numberRightLeg) {
        this.numberRightLeg = numberRightLeg;
    }

    public Integer getNumberLeftLeg() {
        return numberLeftLeg;
    }

    public MModelUniformBottom numberLeftLeg(Integer numberLeftLeg) {
        this.numberLeftLeg = numberLeftLeg;
        return this;
    }

    public void setNumberLeftLeg(Integer numberLeftLeg) {
        this.numberLeftLeg = numberLeftLeg;
    }

    public Integer getUniformNoPositionX() {
        return uniformNoPositionX;
    }

    public MModelUniformBottom uniformNoPositionX(Integer uniformNoPositionX) {
        this.uniformNoPositionX = uniformNoPositionX;
        return this;
    }

    public void setUniformNoPositionX(Integer uniformNoPositionX) {
        this.uniformNoPositionX = uniformNoPositionX;
    }

    public Integer getUniformNoPositionY() {
        return uniformNoPositionY;
    }

    public MModelUniformBottom uniformNoPositionY(Integer uniformNoPositionY) {
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
        if (!(o instanceof MModelUniformBottom)) {
            return false;
        }
        return id != null && id.equals(((MModelUniformBottom) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MModelUniformBottom{" +
            "id=" + getId() +
            ", uniformBottomId=" + getUniformBottomId() +
            ", defaultDressingType=" + getDefaultDressingType() +
            ", uniformModel=" + getUniformModel() +
            ", uniformTexture=" + getUniformTexture() +
            ", uniformNoTexture=" + getUniformNoTexture() +
            ", defaultColor='" + getDefaultColor() + "'" +
            ", uniformNoColor='" + getUniformNoColor() + "'" +
            ", numberRightLeg=" + getNumberRightLeg() +
            ", numberLeftLeg=" + getNumberLeftLeg() +
            ", uniformNoPositionX=" + getUniformNoPositionX() +
            ", uniformNoPositionY=" + getUniformNoPositionY() +
            "}";
    }
}
