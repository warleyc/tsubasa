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
 * Criteria class for the {@link io.shm.tsubasa.domain.MActionSkillCutin} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MActionSkillCutinResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-action-skill-cutins?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MActionSkillCutinCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter actionCutId;

    private IntegerFilter characterId;

    private IntegerFilter type;

    private IntegerFilter startSynchronize;

    private IntegerFilter finishSynchronize;

    private IntegerFilter startDelay;

    private IntegerFilter chapter1Character;

    private IntegerFilter chapter2Character;

    private IntegerFilter chapter3Character;

    private IntegerFilter chapter4Character;

    private IntegerFilter chapter5Character;

    public MActionSkillCutinCriteria(){
    }

    public MActionSkillCutinCriteria(MActionSkillCutinCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.actionCutId = other.actionCutId == null ? null : other.actionCutId.copy();
        this.characterId = other.characterId == null ? null : other.characterId.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.startSynchronize = other.startSynchronize == null ? null : other.startSynchronize.copy();
        this.finishSynchronize = other.finishSynchronize == null ? null : other.finishSynchronize.copy();
        this.startDelay = other.startDelay == null ? null : other.startDelay.copy();
        this.chapter1Character = other.chapter1Character == null ? null : other.chapter1Character.copy();
        this.chapter2Character = other.chapter2Character == null ? null : other.chapter2Character.copy();
        this.chapter3Character = other.chapter3Character == null ? null : other.chapter3Character.copy();
        this.chapter4Character = other.chapter4Character == null ? null : other.chapter4Character.copy();
        this.chapter5Character = other.chapter5Character == null ? null : other.chapter5Character.copy();
    }

    @Override
    public MActionSkillCutinCriteria copy() {
        return new MActionSkillCutinCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getActionCutId() {
        return actionCutId;
    }

    public void setActionCutId(IntegerFilter actionCutId) {
        this.actionCutId = actionCutId;
    }

    public IntegerFilter getCharacterId() {
        return characterId;
    }

    public void setCharacterId(IntegerFilter characterId) {
        this.characterId = characterId;
    }

    public IntegerFilter getType() {
        return type;
    }

    public void setType(IntegerFilter type) {
        this.type = type;
    }

    public IntegerFilter getStartSynchronize() {
        return startSynchronize;
    }

    public void setStartSynchronize(IntegerFilter startSynchronize) {
        this.startSynchronize = startSynchronize;
    }

    public IntegerFilter getFinishSynchronize() {
        return finishSynchronize;
    }

    public void setFinishSynchronize(IntegerFilter finishSynchronize) {
        this.finishSynchronize = finishSynchronize;
    }

    public IntegerFilter getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(IntegerFilter startDelay) {
        this.startDelay = startDelay;
    }

    public IntegerFilter getChapter1Character() {
        return chapter1Character;
    }

    public void setChapter1Character(IntegerFilter chapter1Character) {
        this.chapter1Character = chapter1Character;
    }

    public IntegerFilter getChapter2Character() {
        return chapter2Character;
    }

    public void setChapter2Character(IntegerFilter chapter2Character) {
        this.chapter2Character = chapter2Character;
    }

    public IntegerFilter getChapter3Character() {
        return chapter3Character;
    }

    public void setChapter3Character(IntegerFilter chapter3Character) {
        this.chapter3Character = chapter3Character;
    }

    public IntegerFilter getChapter4Character() {
        return chapter4Character;
    }

    public void setChapter4Character(IntegerFilter chapter4Character) {
        this.chapter4Character = chapter4Character;
    }

    public IntegerFilter getChapter5Character() {
        return chapter5Character;
    }

    public void setChapter5Character(IntegerFilter chapter5Character) {
        this.chapter5Character = chapter5Character;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MActionSkillCutinCriteria that = (MActionSkillCutinCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(actionCutId, that.actionCutId) &&
            Objects.equals(characterId, that.characterId) &&
            Objects.equals(type, that.type) &&
            Objects.equals(startSynchronize, that.startSynchronize) &&
            Objects.equals(finishSynchronize, that.finishSynchronize) &&
            Objects.equals(startDelay, that.startDelay) &&
            Objects.equals(chapter1Character, that.chapter1Character) &&
            Objects.equals(chapter2Character, that.chapter2Character) &&
            Objects.equals(chapter3Character, that.chapter3Character) &&
            Objects.equals(chapter4Character, that.chapter4Character) &&
            Objects.equals(chapter5Character, that.chapter5Character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        actionCutId,
        characterId,
        type,
        startSynchronize,
        finishSynchronize,
        startDelay,
        chapter1Character,
        chapter2Character,
        chapter3Character,
        chapter4Character,
        chapter5Character
        );
    }

    @Override
    public String toString() {
        return "MActionSkillCutinCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (actionCutId != null ? "actionCutId=" + actionCutId + ", " : "") +
                (characterId != null ? "characterId=" + characterId + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (startSynchronize != null ? "startSynchronize=" + startSynchronize + ", " : "") +
                (finishSynchronize != null ? "finishSynchronize=" + finishSynchronize + ", " : "") +
                (startDelay != null ? "startDelay=" + startDelay + ", " : "") +
                (chapter1Character != null ? "chapter1Character=" + chapter1Character + ", " : "") +
                (chapter2Character != null ? "chapter2Character=" + chapter2Character + ", " : "") +
                (chapter3Character != null ? "chapter3Character=" + chapter3Character + ", " : "") +
                (chapter4Character != null ? "chapter4Character=" + chapter4Character + ", " : "") +
                (chapter5Character != null ? "chapter5Character=" + chapter5Character + ", " : "") +
            "}";
    }

}
