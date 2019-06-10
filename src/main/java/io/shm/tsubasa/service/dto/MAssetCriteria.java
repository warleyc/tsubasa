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
 * Criteria class for the {@link io.shm.tsubasa.domain.MAsset} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MAssetResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-assets?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MAssetCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter i18n;

    private IntegerFilter head;

    private IntegerFilter size;

    private IntegerFilter key1;

    private IntegerFilter key2;

    public MAssetCriteria(){
    }

    public MAssetCriteria(MAssetCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.i18n = other.i18n == null ? null : other.i18n.copy();
        this.head = other.head == null ? null : other.head.copy();
        this.size = other.size == null ? null : other.size.copy();
        this.key1 = other.key1 == null ? null : other.key1.copy();
        this.key2 = other.key2 == null ? null : other.key2.copy();
    }

    @Override
    public MAssetCriteria copy() {
        return new MAssetCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter geti18n() {
        return i18n;
    }

    public void seti18n(IntegerFilter i18n) {
        this.i18n = i18n;
    }

    public IntegerFilter getHead() {
        return head;
    }

    public void setHead(IntegerFilter head) {
        this.head = head;
    }

    public IntegerFilter getSize() {
        return size;
    }

    public void setSize(IntegerFilter size) {
        this.size = size;
    }

    public IntegerFilter getKey1() {
        return key1;
    }

    public void setKey1(IntegerFilter key1) {
        this.key1 = key1;
    }

    public IntegerFilter getKey2() {
        return key2;
    }

    public void setKey2(IntegerFilter key2) {
        this.key2 = key2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MAssetCriteria that = (MAssetCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(i18n, that.i18n) &&
            Objects.equals(head, that.head) &&
            Objects.equals(size, that.size) &&
            Objects.equals(key1, that.key1) &&
            Objects.equals(key2, that.key2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        i18n,
        head,
        size,
        key1,
        key2
        );
    }

    @Override
    public String toString() {
        return "MAssetCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (i18n != null ? "i18n=" + i18n + ", " : "") +
                (head != null ? "head=" + head + ", " : "") +
                (size != null ? "size=" + size + ", " : "") +
                (key1 != null ? "key1=" + key1 + ", " : "") +
                (key2 != null ? "key2=" + key2 + ", " : "") +
            "}";
    }

}
