package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MLoginBonusIncentive} entity.
 */
public class MLoginBonusIncentiveDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer roundId;

    @NotNull
    private Integer day;

    @NotNull
    private Integer contentType;

    private Integer contentId;

    @NotNull
    private Integer contentAmount;

    
    @Lob
    private String presentDetail;

    @NotNull
    private Integer isDecorated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentAmount() {
        return contentAmount;
    }

    public void setContentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
    }

    public String getPresentDetail() {
        return presentDetail;
    }

    public void setPresentDetail(String presentDetail) {
        this.presentDetail = presentDetail;
    }

    public Integer getIsDecorated() {
        return isDecorated;
    }

    public void setIsDecorated(Integer isDecorated) {
        this.isDecorated = isDecorated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO = (MLoginBonusIncentiveDTO) o;
        if (mLoginBonusIncentiveDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mLoginBonusIncentiveDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MLoginBonusIncentiveDTO{" +
            "id=" + getId() +
            ", roundId=" + getRoundId() +
            ", day=" + getDay() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            ", presentDetail='" + getPresentDetail() + "'" +
            ", isDecorated=" + getIsDecorated() +
            "}";
    }
}
