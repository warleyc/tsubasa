package io.shm.tsubasa.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.shm.tsubasa.domain.MMatchEnvironment} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMatchEnvironmentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-match-environments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMatchEnvironmentCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter category;

    private IntegerFilter isPlayRainySound;

    public MMatchEnvironmentCriteria(){
    }

    public MMatchEnvironmentCriteria(MMatchEnvironmentCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.category = other.category == null ? null : other.category.copy();
        this.isPlayRainySound = other.isPlayRainySound == null ? null : other.isPlayRainySound.copy();
    }

    @Override
    public MMatchEnvironmentCriteria copy() {
        return new MMatchEnvironmentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getCategory() {
        return category;
    }

    public void setCategory(IntegerFilter category) {
        this.category = category;
    }

    public IntegerFilter getIsPlayRainySound() {
        return isPlayRainySound;
    }

    public void setIsPlayRainySound(IntegerFilter isPlayRainySound) {
        this.isPlayRainySound = isPlayRainySound;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMatchEnvironmentCriteria that = (MMatchEnvironmentCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(category, that.category) &&
            Objects.equals(isPlayRainySound, that.isPlayRainySound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        category,
        isPlayRainySound
        );
    }

    @Override
    public String toString() {
        return "MMatchEnvironmentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (category != null ? "category=" + category + ", " : "") +
                (isPlayRainySound != null ? "isPlayRainySound=" + isPlayRainySound + ", " : "") +
            "}";
    }

}
