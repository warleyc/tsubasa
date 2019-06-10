package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MCharacterBook.
 */
@Entity
@Table(name = "m_character_book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCharacterBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "cv_name")
    private String cvName;

    @NotNull
    @Column(name = "series", nullable = false)
    private Integer series;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    
    @Lob
    @Column(name = "introduction", nullable = false)
    private String introduction;

    
    @Lob
    @Column(name = "first_appeared_comic", nullable = false)
    private String firstAppearedComic;

    
    @Lob
    @Column(name = "first_appeared_comic_link", nullable = false)
    private String firstAppearedComicLink;

    @Lob
    @Column(name = "skill_1")
    private String skill1;

    @Lob
    @Column(name = "skill_1_comic")
    private String skill1Comic;

    @Lob
    @Column(name = "skill_1_comic_link")
    private String skill1ComicLink;

    @Lob
    @Column(name = "skill_2")
    private String skill2;

    @Lob
    @Column(name = "skill_2_comic")
    private String skill2Comic;

    @Lob
    @Column(name = "skill_2_comic_link")
    private String skill2ComicLink;

    @Lob
    @Column(name = "skill_3")
    private String skill3;

    @Lob
    @Column(name = "skill_3_comic")
    private String skill3Comic;

    @Lob
    @Column(name = "skill_3_comic_link")
    private String skill3ComicLink;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCvName() {
        return cvName;
    }

    public MCharacterBook cvName(String cvName) {
        this.cvName = cvName;
        return this;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    public Integer getSeries() {
        return series;
    }

    public MCharacterBook series(Integer series) {
        this.series = series;
        return this;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getHeight() {
        return height;
    }

    public MCharacterBook height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public MCharacterBook weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getIntroduction() {
        return introduction;
    }

    public MCharacterBook introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getFirstAppearedComic() {
        return firstAppearedComic;
    }

    public MCharacterBook firstAppearedComic(String firstAppearedComic) {
        this.firstAppearedComic = firstAppearedComic;
        return this;
    }

    public void setFirstAppearedComic(String firstAppearedComic) {
        this.firstAppearedComic = firstAppearedComic;
    }

    public String getFirstAppearedComicLink() {
        return firstAppearedComicLink;
    }

    public MCharacterBook firstAppearedComicLink(String firstAppearedComicLink) {
        this.firstAppearedComicLink = firstAppearedComicLink;
        return this;
    }

    public void setFirstAppearedComicLink(String firstAppearedComicLink) {
        this.firstAppearedComicLink = firstAppearedComicLink;
    }

    public String getSkill1() {
        return skill1;
    }

    public MCharacterBook skill1(String skill1) {
        this.skill1 = skill1;
        return this;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill1Comic() {
        return skill1Comic;
    }

    public MCharacterBook skill1Comic(String skill1Comic) {
        this.skill1Comic = skill1Comic;
        return this;
    }

    public void setSkill1Comic(String skill1Comic) {
        this.skill1Comic = skill1Comic;
    }

    public String getSkill1ComicLink() {
        return skill1ComicLink;
    }

    public MCharacterBook skill1ComicLink(String skill1ComicLink) {
        this.skill1ComicLink = skill1ComicLink;
        return this;
    }

    public void setSkill1ComicLink(String skill1ComicLink) {
        this.skill1ComicLink = skill1ComicLink;
    }

    public String getSkill2() {
        return skill2;
    }

    public MCharacterBook skill2(String skill2) {
        this.skill2 = skill2;
        return this;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill2Comic() {
        return skill2Comic;
    }

    public MCharacterBook skill2Comic(String skill2Comic) {
        this.skill2Comic = skill2Comic;
        return this;
    }

    public void setSkill2Comic(String skill2Comic) {
        this.skill2Comic = skill2Comic;
    }

    public String getSkill2ComicLink() {
        return skill2ComicLink;
    }

    public MCharacterBook skill2ComicLink(String skill2ComicLink) {
        this.skill2ComicLink = skill2ComicLink;
        return this;
    }

    public void setSkill2ComicLink(String skill2ComicLink) {
        this.skill2ComicLink = skill2ComicLink;
    }

    public String getSkill3() {
        return skill3;
    }

    public MCharacterBook skill3(String skill3) {
        this.skill3 = skill3;
        return this;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill3Comic() {
        return skill3Comic;
    }

    public MCharacterBook skill3Comic(String skill3Comic) {
        this.skill3Comic = skill3Comic;
        return this;
    }

    public void setSkill3Comic(String skill3Comic) {
        this.skill3Comic = skill3Comic;
    }

    public String getSkill3ComicLink() {
        return skill3ComicLink;
    }

    public MCharacterBook skill3ComicLink(String skill3ComicLink) {
        this.skill3ComicLink = skill3ComicLink;
        return this;
    }

    public void setSkill3ComicLink(String skill3ComicLink) {
        this.skill3ComicLink = skill3ComicLink;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCharacterBook)) {
            return false;
        }
        return id != null && id.equals(((MCharacterBook) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCharacterBook{" +
            "id=" + getId() +
            ", cvName='" + getCvName() + "'" +
            ", series=" + getSeries() +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", introduction='" + getIntroduction() + "'" +
            ", firstAppearedComic='" + getFirstAppearedComic() + "'" +
            ", firstAppearedComicLink='" + getFirstAppearedComicLink() + "'" +
            ", skill1='" + getSkill1() + "'" +
            ", skill1Comic='" + getSkill1Comic() + "'" +
            ", skill1ComicLink='" + getSkill1ComicLink() + "'" +
            ", skill2='" + getSkill2() + "'" +
            ", skill2Comic='" + getSkill2Comic() + "'" +
            ", skill2ComicLink='" + getSkill2ComicLink() + "'" +
            ", skill3='" + getSkill3() + "'" +
            ", skill3Comic='" + getSkill3Comic() + "'" +
            ", skill3ComicLink='" + getSkill3ComicLink() + "'" +
            "}";
    }
}
