package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionAfterInputCutIn} entity.
 */
public class MGachaRenditionAfterInputCutInDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer renditionId;

    @NotNull
    private Integer isSsr;

    @NotNull
    private Integer weight;

    
    @Lob
    private String cutInBgPrefabName;

    
    @Lob
    private String seStartCutIn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRenditionId() {
        return renditionId;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCutInBgPrefabName() {
        return cutInBgPrefabName;
    }

    public void setCutInBgPrefabName(String cutInBgPrefabName) {
        this.cutInBgPrefabName = cutInBgPrefabName;
    }

    public String getSeStartCutIn() {
        return seStartCutIn;
    }

    public void setSeStartCutIn(String seStartCutIn) {
        this.seStartCutIn = seStartCutIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO = (MGachaRenditionAfterInputCutInDTO) o;
        if (mGachaRenditionAfterInputCutInDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionAfterInputCutInDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionAfterInputCutInDTO{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", isSsr=" + getIsSsr() +
            ", weight=" + getWeight() +
            ", cutInBgPrefabName='" + getCutInBgPrefabName() + "'" +
            ", seStartCutIn='" + getSeStartCutIn() + "'" +
            "}";
    }
}
