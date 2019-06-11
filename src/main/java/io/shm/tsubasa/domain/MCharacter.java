package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A MCharacter.
 */
@Entity
@Table(name = "m_character")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCharacter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "announce_name", nullable = false)
    private String announceName;

    
    @Lob
    @Column(name = "short_name", nullable = false)
    private String shortName;

    @NotNull
    @Column(name = "character_book_priority", nullable = false)
    private Integer characterBookPriority;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MActionSkillHolderCardCt> mActionSkillHolderCardCts = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MCombinationCutPosition> mCombinationCutPositions = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MMatchResultCutin> mMatchResultCutins = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MNpcCard> mNpcCards = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MTargetCharacterGroup> mTargetCharacterGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MCharacter name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnounceName() {
        return announceName;
    }

    public MCharacter announceName(String announceName) {
        this.announceName = announceName;
        return this;
    }

    public void setAnnounceName(String announceName) {
        this.announceName = announceName;
    }

    public String getShortName() {
        return shortName;
    }

    public MCharacter shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getCharacterBookPriority() {
        return characterBookPriority;
    }

    public MCharacter characterBookPriority(Integer characterBookPriority) {
        this.characterBookPriority = characterBookPriority;
        return this;
    }

    public void setCharacterBookPriority(Integer characterBookPriority) {
        this.characterBookPriority = characterBookPriority;
    }

    public Set<MActionSkillHolderCardCt> getMActionSkillHolderCardCts() {
        return mActionSkillHolderCardCts;
    }

    public MCharacter mActionSkillHolderCardCts(Set<MActionSkillHolderCardCt> mActionSkillHolderCardCts) {
        this.mActionSkillHolderCardCts = mActionSkillHolderCardCts;
        return this;
    }

    public MCharacter addMActionSkillHolderCardCt(MActionSkillHolderCardCt mActionSkillHolderCardCt) {
        this.mActionSkillHolderCardCts.add(mActionSkillHolderCardCt);
        mActionSkillHolderCardCt.setId(this);
        return this;
    }

    public MCharacter removeMActionSkillHolderCardCt(MActionSkillHolderCardCt mActionSkillHolderCardCt) {
        this.mActionSkillHolderCardCts.remove(mActionSkillHolderCardCt);
        mActionSkillHolderCardCt.setId(null);
        return this;
    }

    public void setMActionSkillHolderCardCts(Set<MActionSkillHolderCardCt> mActionSkillHolderCardCts) {
        this.mActionSkillHolderCardCts = mActionSkillHolderCardCts;
    }

    public Set<MCombinationCutPosition> getMCombinationCutPositions() {
        return mCombinationCutPositions;
    }

    public MCharacter mCombinationCutPositions(Set<MCombinationCutPosition> mCombinationCutPositions) {
        this.mCombinationCutPositions = mCombinationCutPositions;
        return this;
    }

    public MCharacter addMCombinationCutPosition(MCombinationCutPosition mCombinationCutPosition) {
        this.mCombinationCutPositions.add(mCombinationCutPosition);
        mCombinationCutPosition.setId(this);
        return this;
    }

    public MCharacter removeMCombinationCutPosition(MCombinationCutPosition mCombinationCutPosition) {
        this.mCombinationCutPositions.remove(mCombinationCutPosition);
        mCombinationCutPosition.setId(null);
        return this;
    }

    public void setMCombinationCutPositions(Set<MCombinationCutPosition> mCombinationCutPositions) {
        this.mCombinationCutPositions = mCombinationCutPositions;
    }

    public Set<MMatchResultCutin> getMMatchResultCutins() {
        return mMatchResultCutins;
    }

    public MCharacter mMatchResultCutins(Set<MMatchResultCutin> mMatchResultCutins) {
        this.mMatchResultCutins = mMatchResultCutins;
        return this;
    }

    public MCharacter addMMatchResultCutin(MMatchResultCutin mMatchResultCutin) {
        this.mMatchResultCutins.add(mMatchResultCutin);
        mMatchResultCutin.setId(this);
        return this;
    }

    public MCharacter removeMMatchResultCutin(MMatchResultCutin mMatchResultCutin) {
        this.mMatchResultCutins.remove(mMatchResultCutin);
        mMatchResultCutin.setId(null);
        return this;
    }

    public void setMMatchResultCutins(Set<MMatchResultCutin> mMatchResultCutins) {
        this.mMatchResultCutins = mMatchResultCutins;
    }

    public Set<MNpcCard> getMNpcCards() {
        return mNpcCards;
    }

    public MCharacter mNpcCards(Set<MNpcCard> mNpcCards) {
        this.mNpcCards = mNpcCards;
        return this;
    }

    public MCharacter addMNpcCard(MNpcCard mNpcCard) {
        this.mNpcCards.add(mNpcCard);
        mNpcCard.setId(this);
        return this;
    }

    public MCharacter removeMNpcCard(MNpcCard mNpcCard) {
        this.mNpcCards.remove(mNpcCard);
        mNpcCard.setId(null);
        return this;
    }

    public void setMNpcCards(Set<MNpcCard> mNpcCards) {
        this.mNpcCards = mNpcCards;
    }

    public Set<MTargetCharacterGroup> getMTargetCharacterGroups() {
        return mTargetCharacterGroups;
    }

    public MCharacter mTargetCharacterGroups(Set<MTargetCharacterGroup> mTargetCharacterGroups) {
        this.mTargetCharacterGroups = mTargetCharacterGroups;
        return this;
    }

    public MCharacter addMTargetCharacterGroup(MTargetCharacterGroup mTargetCharacterGroup) {
        this.mTargetCharacterGroups.add(mTargetCharacterGroup);
        mTargetCharacterGroup.setId(this);
        return this;
    }

    public MCharacter removeMTargetCharacterGroup(MTargetCharacterGroup mTargetCharacterGroup) {
        this.mTargetCharacterGroups.remove(mTargetCharacterGroup);
        mTargetCharacterGroup.setId(null);
        return this;
    }

    public void setMTargetCharacterGroups(Set<MTargetCharacterGroup> mTargetCharacterGroups) {
        this.mTargetCharacterGroups = mTargetCharacterGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCharacter)) {
            return false;
        }
        return id != null && id.equals(((MCharacter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCharacter{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", announceName='" + getAnnounceName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", characterBookPriority=" + getCharacterBookPriority() +
            "}";
    }
}
