package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MNgWord} entity.
 */
public class MNgWordDTO implements Serializable {

    private Long id;

    
    @Lob
    private String word;

    @NotNull
    private Integer judgeType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(Integer judgeType) {
        this.judgeType = judgeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MNgWordDTO mNgWordDTO = (MNgWordDTO) o;
        if (mNgWordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mNgWordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MNgWordDTO{" +
            "id=" + getId() +
            ", word='" + getWord() + "'" +
            ", judgeType=" + getJudgeType() +
            "}";
    }
}
