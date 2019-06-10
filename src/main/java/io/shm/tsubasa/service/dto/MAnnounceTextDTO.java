package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MAnnounceText} entity.
 */
public class MAnnounceTextDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer seqNo;

    
    @Lob
    private String normalAnnounce;

    
    @Lob
    private String criticalSAnnounce;

    
    @Lob
    private String criticalMAnnounce;

    
    @Lob
    private String criticalLAnnounce;

    private Integer delayTime;

    private Integer continueTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getNormalAnnounce() {
        return normalAnnounce;
    }

    public void setNormalAnnounce(String normalAnnounce) {
        this.normalAnnounce = normalAnnounce;
    }

    public String getCriticalSAnnounce() {
        return criticalSAnnounce;
    }

    public void setCriticalSAnnounce(String criticalSAnnounce) {
        this.criticalSAnnounce = criticalSAnnounce;
    }

    public String getCriticalMAnnounce() {
        return criticalMAnnounce;
    }

    public void setCriticalMAnnounce(String criticalMAnnounce) {
        this.criticalMAnnounce = criticalMAnnounce;
    }

    public String getCriticalLAnnounce() {
        return criticalLAnnounce;
    }

    public void setCriticalLAnnounce(String criticalLAnnounce) {
        this.criticalLAnnounce = criticalLAnnounce;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    public Integer getContinueTime() {
        return continueTime;
    }

    public void setContinueTime(Integer continueTime) {
        this.continueTime = continueTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MAnnounceTextDTO mAnnounceTextDTO = (MAnnounceTextDTO) o;
        if (mAnnounceTextDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mAnnounceTextDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MAnnounceTextDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", seqNo=" + getSeqNo() +
            ", normalAnnounce='" + getNormalAnnounce() + "'" +
            ", criticalSAnnounce='" + getCriticalSAnnounce() + "'" +
            ", criticalMAnnounce='" + getCriticalMAnnounce() + "'" +
            ", criticalLAnnounce='" + getCriticalLAnnounce() + "'" +
            ", delayTime=" + getDelayTime() +
            ", continueTime=" + getContinueTime() +
            "}";
    }
}
