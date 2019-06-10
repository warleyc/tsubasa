package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCoopRoomStamp} entity.
 */
public class MCoopRoomStampDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer role;

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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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

        MCoopRoomStampDTO mCoopRoomStampDTO = (MCoopRoomStampDTO) o;
        if (mCoopRoomStampDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCoopRoomStampDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCoopRoomStampDTO{" +
            "id=" + getId() +
            ", role=" + getRole() +
            ", orderNum=" + getOrderNum() +
            ", masterId=" + getMasterId() +
            "}";
    }
}
