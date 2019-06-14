package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MPvpWatcherStamp} entity.
 */
public class MPvpWatcherStampDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer orderNum;

    @NotNull
    private Integer masterId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MPvpWatcherStampDTO mPvpWatcherStampDTO = (MPvpWatcherStampDTO) o;
        if (mPvpWatcherStampDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mPvpWatcherStampDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MPvpWatcherStampDTO{" +
            "id=" + getId() +
            ", orderNum=" + getOrderNum() +
            ", masterId=" + getMasterId() +
            "}";
    }
}
