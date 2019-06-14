package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MModelCard} entity.
 */
public class MModelCardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer hairModel;

    @NotNull
    private Integer hairTexture;

    @NotNull
    private Integer headModel;

    @NotNull
    private Integer headTexture;

    @NotNull
    private Integer skinTexture;

    @NotNull
    private Integer shoesModel;

    @NotNull
    private Integer shoesTexture;

    private Integer globeTexture;

    private Integer uniqueModel;

    private Integer uniqueTexture;

    @NotNull
    private Integer dressingTypeUp;

    @NotNull
    private Integer dressingTypeBottom;

    @NotNull
    private Integer height;

    @NotNull
    private Integer width;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHairModel() {
        return hairModel;
    }

    public void setHairModel(Integer hairModel) {
        this.hairModel = hairModel;
    }

    public Integer getHairTexture() {
        return hairTexture;
    }

    public void setHairTexture(Integer hairTexture) {
        this.hairTexture = hairTexture;
    }

    public Integer getHeadModel() {
        return headModel;
    }

    public void setHeadModel(Integer headModel) {
        this.headModel = headModel;
    }

    public Integer getHeadTexture() {
        return headTexture;
    }

    public void setHeadTexture(Integer headTexture) {
        this.headTexture = headTexture;
    }

    public Integer getSkinTexture() {
        return skinTexture;
    }

    public void setSkinTexture(Integer skinTexture) {
        this.skinTexture = skinTexture;
    }

    public Integer getShoesModel() {
        return shoesModel;
    }

    public void setShoesModel(Integer shoesModel) {
        this.shoesModel = shoesModel;
    }

    public Integer getShoesTexture() {
        return shoesTexture;
    }

    public void setShoesTexture(Integer shoesTexture) {
        this.shoesTexture = shoesTexture;
    }

    public Integer getGlobeTexture() {
        return globeTexture;
    }

    public void setGlobeTexture(Integer globeTexture) {
        this.globeTexture = globeTexture;
    }

    public Integer getUniqueModel() {
        return uniqueModel;
    }

    public void setUniqueModel(Integer uniqueModel) {
        this.uniqueModel = uniqueModel;
    }

    public Integer getUniqueTexture() {
        return uniqueTexture;
    }

    public void setUniqueTexture(Integer uniqueTexture) {
        this.uniqueTexture = uniqueTexture;
    }

    public Integer getDressingTypeUp() {
        return dressingTypeUp;
    }

    public void setDressingTypeUp(Integer dressingTypeUp) {
        this.dressingTypeUp = dressingTypeUp;
    }

    public Integer getDressingTypeBottom() {
        return dressingTypeBottom;
    }

    public void setDressingTypeBottom(Integer dressingTypeBottom) {
        this.dressingTypeBottom = dressingTypeBottom;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MModelCardDTO mModelCardDTO = (MModelCardDTO) o;
        if (mModelCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mModelCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MModelCardDTO{" +
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
