package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MSellCardCoin} entity.
 */
public class MSellCardCoinDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupNum;

    @NotNull
    private Integer level;

    @NotNull
    private Integer coin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MSellCardCoinDTO mSellCardCoinDTO = (MSellCardCoinDTO) o;
        if (mSellCardCoinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mSellCardCoinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MSellCardCoinDTO{" +
            "id=" + getId() +
            ", groupNum=" + getGroupNum() +
            ", level=" + getLevel() +
            ", coin=" + getCoin() +
            "}";
    }
}
