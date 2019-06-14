package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MNgWord.
 */
@Entity
@Table(name = "m_ng_word")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MNgWord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "word", nullable = false)
    private String word;

    @NotNull
    @Column(name = "judge_type", nullable = false)
    private Integer judgeType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public MNgWord word(String word) {
        this.word = word;
        return this;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getJudgeType() {
        return judgeType;
    }

    public MNgWord judgeType(Integer judgeType) {
        this.judgeType = judgeType;
        return this;
    }

    public void setJudgeType(Integer judgeType) {
        this.judgeType = judgeType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MNgWord)) {
            return false;
        }
        return id != null && id.equals(((MNgWord) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MNgWord{" +
            "id=" + getId() +
            ", word='" + getWord() + "'" +
            ", judgeType=" + getJudgeType() +
            "}";
    }
}
