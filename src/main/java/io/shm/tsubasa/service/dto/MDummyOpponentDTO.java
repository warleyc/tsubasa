package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDummyOpponent} entity.
 */
public class MDummyOpponentDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer npcDeckId;

    @NotNull
    private Integer totalParameter;

    
    @Lob
    private String name;

    @NotNull
    private Integer rank;

    @NotNull
    private Integer emblemId;

    @NotNull
    private Integer fpUniformUpId;

    @Lob
    private String fpUniformUpColor;

    @NotNull
    private Integer fpUniformBottomId;

    @Lob
    private String fpUniformBottomColor;

    @NotNull
    private Integer gkUniformUpId;

    @Lob
    private String gkUniformUpColor;

    @NotNull
    private Integer gkUniformBottomId;

    @Lob
    private String gkUniformBottomColor;


    private Long mnpcdeckId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNpcDeckId() {
        return npcDeckId;
    }

    public void setNpcDeckId(Integer npcDeckId) {
        this.npcDeckId = npcDeckId;
    }

    public Integer getTotalParameter() {
        return totalParameter;
    }

    public void setTotalParameter(Integer totalParameter) {
        this.totalParameter = totalParameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getEmblemId() {
        return emblemId;
    }

    public void setEmblemId(Integer emblemId) {
        this.emblemId = emblemId;
    }

    public Integer getFpUniformUpId() {
        return fpUniformUpId;
    }

    public void setFpUniformUpId(Integer fpUniformUpId) {
        this.fpUniformUpId = fpUniformUpId;
    }

    public String getFpUniformUpColor() {
        return fpUniformUpColor;
    }

    public void setFpUniformUpColor(String fpUniformUpColor) {
        this.fpUniformUpColor = fpUniformUpColor;
    }

    public Integer getFpUniformBottomId() {
        return fpUniformBottomId;
    }

    public void setFpUniformBottomId(Integer fpUniformBottomId) {
        this.fpUniformBottomId = fpUniformBottomId;
    }

    public String getFpUniformBottomColor() {
        return fpUniformBottomColor;
    }

    public void setFpUniformBottomColor(String fpUniformBottomColor) {
        this.fpUniformBottomColor = fpUniformBottomColor;
    }

    public Integer getGkUniformUpId() {
        return gkUniformUpId;
    }

    public void setGkUniformUpId(Integer gkUniformUpId) {
        this.gkUniformUpId = gkUniformUpId;
    }

    public String getGkUniformUpColor() {
        return gkUniformUpColor;
    }

    public void setGkUniformUpColor(String gkUniformUpColor) {
        this.gkUniformUpColor = gkUniformUpColor;
    }

    public Integer getGkUniformBottomId() {
        return gkUniformBottomId;
    }

    public void setGkUniformBottomId(Integer gkUniformBottomId) {
        this.gkUniformBottomId = gkUniformBottomId;
    }

    public String getGkUniformBottomColor() {
        return gkUniformBottomColor;
    }

    public void setGkUniformBottomColor(String gkUniformBottomColor) {
        this.gkUniformBottomColor = gkUniformBottomColor;
    }

    public Long getMnpcdeckId() {
        return mnpcdeckId;
    }

    public void setMnpcdeckId(Long mNpcDeckId) {
        this.mnpcdeckId = mNpcDeckId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MDummyOpponentDTO mDummyOpponentDTO = (MDummyOpponentDTO) o;
        if (mDummyOpponentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDummyOpponentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDummyOpponentDTO{" +
            "id=" + getId() +
            ", npcDeckId=" + getNpcDeckId() +
            ", totalParameter=" + getTotalParameter() +
            ", name='" + getName() + "'" +
            ", rank=" + getRank() +
            ", emblemId=" + getEmblemId() +
            ", fpUniformUpId=" + getFpUniformUpId() +
            ", fpUniformUpColor='" + getFpUniformUpColor() + "'" +
            ", fpUniformBottomId=" + getFpUniformBottomId() +
            ", fpUniformBottomColor='" + getFpUniformBottomColor() + "'" +
            ", gkUniformUpId=" + getGkUniformUpId() +
            ", gkUniformUpColor='" + getGkUniformUpColor() + "'" +
            ", gkUniformBottomId=" + getGkUniformBottomId() +
            ", gkUniformBottomColor='" + getGkUniformBottomColor() + "'" +
            ", mnpcdeck=" + getMnpcdeckId() +
            "}";
    }
}
