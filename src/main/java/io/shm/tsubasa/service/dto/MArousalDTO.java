package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MArousal} entity.
 */
public class MArousalDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer beforeId;

    @NotNull
    private Integer afterId;

    @NotNull
    private Integer cost;

    @NotNull
    private Integer materialGroupId;


    private Long mplayablecardId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBeforeId() {
        return beforeId;
    }

    public void setBeforeId(Integer beforeId) {
        this.beforeId = beforeId;
    }

    public Integer getAfterId() {
        return afterId;
    }

    public void setAfterId(Integer afterId) {
        this.afterId = afterId;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getMaterialGroupId() {
        return materialGroupId;
    }

    public void setMaterialGroupId(Integer materialGroupId) {
        this.materialGroupId = materialGroupId;
    }

    public Long getMplayablecardId() {
        return mplayablecardId;
    }

    public void setMplayablecardId(Long mPlayableCardId) {
        this.mplayablecardId = mPlayableCardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MArousalDTO mArousalDTO = (MArousalDTO) o;
        if (mArousalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mArousalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MArousalDTO{" +
            "id=" + getId() +
            ", beforeId=" + getBeforeId() +
            ", afterId=" + getAfterId() +
            ", cost=" + getCost() +
            ", materialGroupId=" + getMaterialGroupId() +
            ", mplayablecard=" + getMplayablecardId() +
            "}";
    }
}
