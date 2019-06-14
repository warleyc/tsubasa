package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MRegulationMatchTutorialMessage} entity.
 */
public class MRegulationMatchTutorialMessageDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer ruleId;

    @NotNull
    private Integer orderNum;

    @NotNull
    private Integer position;

    
    @Lob
    private String message;

    
    @Lob
    private String assetName;

    
    @Lob
    private String title;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO = (MRegulationMatchTutorialMessageDTO) o;
        if (mRegulationMatchTutorialMessageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mRegulationMatchTutorialMessageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MRegulationMatchTutorialMessageDTO{" +
            "id=" + getId() +
            ", ruleId=" + getRuleId() +
            ", orderNum=" + getOrderNum() +
            ", position=" + getPosition() +
            ", message='" + getMessage() + "'" +
            ", assetName='" + getAssetName() + "'" +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
