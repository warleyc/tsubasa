package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MHomeMarquee} entity.
 */
public class MHomeMarqueeDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer priority;

    
    @Lob
    private String marqueeText;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMarqueeText() {
        return marqueeText;
    }

    public void setMarqueeText(String marqueeText) {
        this.marqueeText = marqueeText;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MHomeMarqueeDTO mHomeMarqueeDTO = (MHomeMarqueeDTO) o;
        if (mHomeMarqueeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mHomeMarqueeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MHomeMarqueeDTO{" +
            "id=" + getId() +
            ", priority=" + getPriority() +
            ", marqueeText='" + getMarqueeText() + "'" +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
