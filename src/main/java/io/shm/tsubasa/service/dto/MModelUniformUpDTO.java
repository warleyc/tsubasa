package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MModelUniformUp} entity.
 */
public class MModelUniformUpDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer uniformUpId;

    @NotNull
    private Integer defaultDressingType;

    @NotNull
    private Integer uniformModel;

    @NotNull
    private Integer uniformTexture;

    @NotNull
    private Integer uniformNoTexture;

    @Lob
    private String defaultColor;

    
    @Lob
    private String uniformNoColor;

    @NotNull
    private Integer numberChest;

    @NotNull
    private Integer numberBelly;

    @NotNull
    private Integer numberBack;

    private Integer uniformNoPositionX;

    private Integer uniformNoPositionY;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUniformUpId() {
        return uniformUpId;
    }

    public void setUniformUpId(Integer uniformUpId) {
        this.uniformUpId = uniformUpId;
    }

    public Integer getDefaultDressingType() {
        return defaultDressingType;
    }

    public void setDefaultDressingType(Integer defaultDressingType) {
        this.defaultDressingType = defaultDressingType;
    }

    public Integer getUniformModel() {
        return uniformModel;
    }

    public void setUniformModel(Integer uniformModel) {
        this.uniformModel = uniformModel;
    }

    public Integer getUniformTexture() {
        return uniformTexture;
    }

    public void setUniformTexture(Integer uniformTexture) {
        this.uniformTexture = uniformTexture;
    }

    public Integer getUniformNoTexture() {
        return uniformNoTexture;
    }

    public void setUniformNoTexture(Integer uniformNoTexture) {
        this.uniformNoTexture = uniformNoTexture;
    }

    public String getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
    }

    public String getUniformNoColor() {
        return uniformNoColor;
    }

    public void setUniformNoColor(String uniformNoColor) {
        this.uniformNoColor = uniformNoColor;
    }

    public Integer getNumberChest() {
        return numberChest;
    }

    public void setNumberChest(Integer numberChest) {
        this.numberChest = numberChest;
    }

    public Integer getNumberBelly() {
        return numberBelly;
    }

    public void setNumberBelly(Integer numberBelly) {
        this.numberBelly = numberBelly;
    }

    public Integer getNumberBack() {
        return numberBack;
    }

    public void setNumberBack(Integer numberBack) {
        this.numberBack = numberBack;
    }

    public Integer getUniformNoPositionX() {
        return uniformNoPositionX;
    }

    public void setUniformNoPositionX(Integer uniformNoPositionX) {
        this.uniformNoPositionX = uniformNoPositionX;
    }

    public Integer getUniformNoPositionY() {
        return uniformNoPositionY;
    }

    public void setUniformNoPositionY(Integer uniformNoPositionY) {
        this.uniformNoPositionY = uniformNoPositionY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MModelUniformUpDTO mModelUniformUpDTO = (MModelUniformUpDTO) o;
        if (mModelUniformUpDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mModelUniformUpDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MModelUniformUpDTO{" +
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
