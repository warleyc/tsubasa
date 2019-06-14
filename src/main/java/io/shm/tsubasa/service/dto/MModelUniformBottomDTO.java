package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MModelUniformBottom} entity.
 */
public class MModelUniformBottomDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer uniformBottomId;

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
    private Integer numberRightLeg;

    @NotNull
    private Integer numberLeftLeg;

    private Integer uniformNoPositionX;

    private Integer uniformNoPositionY;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUniformBottomId() {
        return uniformBottomId;
    }

    public void setUniformBottomId(Integer uniformBottomId) {
        this.uniformBottomId = uniformBottomId;
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

    public Integer getNumberRightLeg() {
        return numberRightLeg;
    }

    public void setNumberRightLeg(Integer numberRightLeg) {
        this.numberRightLeg = numberRightLeg;
    }

    public Integer getNumberLeftLeg() {
        return numberLeftLeg;
    }

    public void setNumberLeftLeg(Integer numberLeftLeg) {
        this.numberLeftLeg = numberLeftLeg;
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

        MModelUniformBottomDTO mModelUniformBottomDTO = (MModelUniformBottomDTO) o;
        if (mModelUniformBottomDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mModelUniformBottomDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MModelUniformBottomDTO{" +
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
