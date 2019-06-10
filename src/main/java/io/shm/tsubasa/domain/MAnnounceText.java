package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MAnnounceText.
 */
@Entity
@Table(name = "m_announce_text")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MAnnounceText implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "seq_no", nullable = false)
    private Integer seqNo;

    
    @Lob
    @Column(name = "normal_announce", nullable = false)
    private String normalAnnounce;

    
    @Lob
    @Column(name = "critical_s_announce", nullable = false)
    private String criticalSAnnounce;

    
    @Lob
    @Column(name = "critical_m_announce", nullable = false)
    private String criticalMAnnounce;

    
    @Lob
    @Column(name = "critical_l_announce", nullable = false)
    private String criticalLAnnounce;

    @Column(name = "delay_time")
    private Integer delayTime;

    @Column(name = "continue_time")
    private Integer continueTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public MAnnounceText groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public MAnnounceText seqNo(Integer seqNo) {
        this.seqNo = seqNo;
        return this;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getNormalAnnounce() {
        return normalAnnounce;
    }

    public MAnnounceText normalAnnounce(String normalAnnounce) {
        this.normalAnnounce = normalAnnounce;
        return this;
    }

    public void setNormalAnnounce(String normalAnnounce) {
        this.normalAnnounce = normalAnnounce;
    }

    public String getCriticalSAnnounce() {
        return criticalSAnnounce;
    }

    public MAnnounceText criticalSAnnounce(String criticalSAnnounce) {
        this.criticalSAnnounce = criticalSAnnounce;
        return this;
    }

    public void setCriticalSAnnounce(String criticalSAnnounce) {
        this.criticalSAnnounce = criticalSAnnounce;
    }

    public String getCriticalMAnnounce() {
        return criticalMAnnounce;
    }

    public MAnnounceText criticalMAnnounce(String criticalMAnnounce) {
        this.criticalMAnnounce = criticalMAnnounce;
        return this;
    }

    public void setCriticalMAnnounce(String criticalMAnnounce) {
        this.criticalMAnnounce = criticalMAnnounce;
    }

    public String getCriticalLAnnounce() {
        return criticalLAnnounce;
    }

    public MAnnounceText criticalLAnnounce(String criticalLAnnounce) {
        this.criticalLAnnounce = criticalLAnnounce;
        return this;
    }

    public void setCriticalLAnnounce(String criticalLAnnounce) {
        this.criticalLAnnounce = criticalLAnnounce;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public MAnnounceText delayTime(Integer delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    public Integer getContinueTime() {
        return continueTime;
    }

    public MAnnounceText continueTime(Integer continueTime) {
        this.continueTime = continueTime;
        return this;
    }

    public void setContinueTime(Integer continueTime) {
        this.continueTime = continueTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MAnnounceText)) {
            return false;
        }
        return id != null && id.equals(((MAnnounceText) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MAnnounceText{" +
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
