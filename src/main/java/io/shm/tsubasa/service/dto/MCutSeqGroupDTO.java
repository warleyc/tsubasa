package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCutSeqGroup} entity.
 */
public class MCutSeqGroupDTO implements Serializable {

    private Long id;

    
    @Lob
    private String key;

    
    @Lob
    private String param;

    
    @Lob
    private String cutSequenceText;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCutSequenceText() {
        return cutSequenceText;
    }

    public void setCutSequenceText(String cutSequenceText) {
        this.cutSequenceText = cutSequenceText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCutSeqGroupDTO mCutSeqGroupDTO = (MCutSeqGroupDTO) o;
        if (mCutSeqGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCutSeqGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCutSeqGroupDTO{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", param='" + getParam() + "'" +
            ", cutSequenceText='" + getCutSequenceText() + "'" +
            "}";
    }
}
