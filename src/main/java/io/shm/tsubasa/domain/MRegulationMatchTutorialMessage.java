package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MRegulationMatchTutorialMessage.
 */
@Entity
@Table(name = "m_regulation_match_tutorial_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MRegulationMatchTutorialMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rule_id", nullable = false)
    private Integer ruleId;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    @NotNull
    @Column(name = "position", nullable = false)
    private Integer position;

    
    @Lob
    @Column(name = "message", nullable = false)
    private String message;

    
    @Lob
    @Column(name = "asset_name", nullable = false)
    private String assetName;

    
    @Lob
    @Column(name = "title", nullable = false)
    private String title;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public MRegulationMatchTutorialMessage ruleId(Integer ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public MRegulationMatchTutorialMessage orderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getPosition() {
        return position;
    }

    public MRegulationMatchTutorialMessage position(Integer position) {
        this.position = position;
        return this;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public MRegulationMatchTutorialMessage message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAssetName() {
        return assetName;
    }

    public MRegulationMatchTutorialMessage assetName(String assetName) {
        this.assetName = assetName;
        return this;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getTitle() {
        return title;
    }

    public MRegulationMatchTutorialMessage title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MRegulationMatchTutorialMessage)) {
            return false;
        }
        return id != null && id.equals(((MRegulationMatchTutorialMessage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MRegulationMatchTutorialMessage{" +
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
