package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDummyEmblem} entity.
 */
public class MDummyEmblemDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer backgroundId;

    
    @Lob
    private String backgroundColor;

    private Integer upperId;

    @Lob
    private String upperColor;

    @NotNull
    private Integer middleId;

    
    @Lob
    private String middleColor;

    private Integer lowerId;

    @Lob
    private String lowerColor;


    private Long memblempartsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(Integer backgroundId) {
        this.backgroundId = backgroundId;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getUpperId() {
        return upperId;
    }

    public void setUpperId(Integer upperId) {
        this.upperId = upperId;
    }

    public String getUpperColor() {
        return upperColor;
    }

    public void setUpperColor(String upperColor) {
        this.upperColor = upperColor;
    }

    public Integer getMiddleId() {
        return middleId;
    }

    public void setMiddleId(Integer middleId) {
        this.middleId = middleId;
    }

    public String getMiddleColor() {
        return middleColor;
    }

    public void setMiddleColor(String middleColor) {
        this.middleColor = middleColor;
    }

    public Integer getLowerId() {
        return lowerId;
    }

    public void setLowerId(Integer lowerId) {
        this.lowerId = lowerId;
    }

    public String getLowerColor() {
        return lowerColor;
    }

    public void setLowerColor(String lowerColor) {
        this.lowerColor = lowerColor;
    }

    public Long getMemblempartsId() {
        return memblempartsId;
    }

    public void setMemblempartsId(Long mEmblemPartsId) {
        this.memblempartsId = mEmblemPartsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MDummyEmblemDTO mDummyEmblemDTO = (MDummyEmblemDTO) o;
        if (mDummyEmblemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDummyEmblemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDummyEmblemDTO{" +
            "id=" + getId() +
            ", backgroundId=" + getBackgroundId() +
            ", backgroundColor='" + getBackgroundColor() + "'" +
            ", upperId=" + getUpperId() +
            ", upperColor='" + getUpperColor() + "'" +
            ", middleId=" + getMiddleId() +
            ", middleColor='" + getMiddleColor() + "'" +
            ", lowerId=" + getLowerId() +
            ", lowerColor='" + getLowerColor() + "'" +
            ", memblemparts=" + getMemblempartsId() +
            "}";
    }
}
