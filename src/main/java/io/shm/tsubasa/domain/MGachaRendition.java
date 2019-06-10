package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRendition.
 */
@Entity
@Table(name = "m_gacha_rendition")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRendition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "main_prefab_name", nullable = false)
    private String mainPrefabName;

    
    @Lob
    @Column(name = "result_expected_up_prefab_name", nullable = false)
    private String resultExpectedUpPrefabName;

    @Lob
    @Column(name = "result_question_prefab_name")
    private String resultQuestionPrefabName;

    
    @Lob
    @Column(name = "sound_switch_event_name", nullable = false)
    private String soundSwitchEventName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainPrefabName() {
        return mainPrefabName;
    }

    public MGachaRendition mainPrefabName(String mainPrefabName) {
        this.mainPrefabName = mainPrefabName;
        return this;
    }

    public void setMainPrefabName(String mainPrefabName) {
        this.mainPrefabName = mainPrefabName;
    }

    public String getResultExpectedUpPrefabName() {
        return resultExpectedUpPrefabName;
    }

    public MGachaRendition resultExpectedUpPrefabName(String resultExpectedUpPrefabName) {
        this.resultExpectedUpPrefabName = resultExpectedUpPrefabName;
        return this;
    }

    public void setResultExpectedUpPrefabName(String resultExpectedUpPrefabName) {
        this.resultExpectedUpPrefabName = resultExpectedUpPrefabName;
    }

    public String getResultQuestionPrefabName() {
        return resultQuestionPrefabName;
    }

    public MGachaRendition resultQuestionPrefabName(String resultQuestionPrefabName) {
        this.resultQuestionPrefabName = resultQuestionPrefabName;
        return this;
    }

    public void setResultQuestionPrefabName(String resultQuestionPrefabName) {
        this.resultQuestionPrefabName = resultQuestionPrefabName;
    }

    public String getSoundSwitchEventName() {
        return soundSwitchEventName;
    }

    public MGachaRendition soundSwitchEventName(String soundSwitchEventName) {
        this.soundSwitchEventName = soundSwitchEventName;
        return this;
    }

    public void setSoundSwitchEventName(String soundSwitchEventName) {
        this.soundSwitchEventName = soundSwitchEventName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRendition)) {
            return false;
        }
        return id != null && id.equals(((MGachaRendition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRendition{" +
            "id=" + getId() +
            ", mainPrefabName='" + getMainPrefabName() + "'" +
            ", resultExpectedUpPrefabName='" + getResultExpectedUpPrefabName() + "'" +
            ", resultQuestionPrefabName='" + getResultQuestionPrefabName() + "'" +
            ", soundSwitchEventName='" + getSoundSwitchEventName() + "'" +
            "}";
    }
}
