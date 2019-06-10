package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCharacterBook} entity.
 */
public class MCharacterBookDTO implements Serializable {

    private Long id;

    @Lob
    private String cvName;

    @NotNull
    private Integer series;

    private Integer height;

    private Integer weight;

    
    @Lob
    private String introduction;

    
    @Lob
    private String firstAppearedComic;

    
    @Lob
    private String firstAppearedComicLink;

    @Lob
    private String skill1;

    @Lob
    private String skill1Comic;

    @Lob
    private String skill1ComicLink;

    @Lob
    private String skill2;

    @Lob
    private String skill2Comic;

    @Lob
    private String skill2ComicLink;

    @Lob
    private String skill3;

    @Lob
    private String skill3Comic;

    @Lob
    private String skill3ComicLink;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getFirstAppearedComic() {
        return firstAppearedComic;
    }

    public void setFirstAppearedComic(String firstAppearedComic) {
        this.firstAppearedComic = firstAppearedComic;
    }

    public String getFirstAppearedComicLink() {
        return firstAppearedComicLink;
    }

    public void setFirstAppearedComicLink(String firstAppearedComicLink) {
        this.firstAppearedComicLink = firstAppearedComicLink;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill1Comic() {
        return skill1Comic;
    }

    public void setSkill1Comic(String skill1Comic) {
        this.skill1Comic = skill1Comic;
    }

    public String getSkill1ComicLink() {
        return skill1ComicLink;
    }

    public void setSkill1ComicLink(String skill1ComicLink) {
        this.skill1ComicLink = skill1ComicLink;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill2Comic() {
        return skill2Comic;
    }

    public void setSkill2Comic(String skill2Comic) {
        this.skill2Comic = skill2Comic;
    }

    public String getSkill2ComicLink() {
        return skill2ComicLink;
    }

    public void setSkill2ComicLink(String skill2ComicLink) {
        this.skill2ComicLink = skill2ComicLink;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill3Comic() {
        return skill3Comic;
    }

    public void setSkill3Comic(String skill3Comic) {
        this.skill3Comic = skill3Comic;
    }

    public String getSkill3ComicLink() {
        return skill3ComicLink;
    }

    public void setSkill3ComicLink(String skill3ComicLink) {
        this.skill3ComicLink = skill3ComicLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCharacterBookDTO mCharacterBookDTO = (MCharacterBookDTO) o;
        if (mCharacterBookDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCharacterBookDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCharacterBookDTO{" +
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
