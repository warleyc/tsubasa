package io.shm.tsubasa.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.shm.tsubasa.domain.MPlayableCard;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPlayableCardRepository;
import io.shm.tsubasa.service.dto.MPlayableCardCriteria;
import io.shm.tsubasa.service.dto.MPlayableCardDTO;
import io.shm.tsubasa.service.mapper.MPlayableCardMapper;

/**
 * Service for executing complex queries for {@link MPlayableCard} entities in the database.
 * The main input is a {@link MPlayableCardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPlayableCardDTO} or a {@link Page} of {@link MPlayableCardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPlayableCardQueryService extends QueryService<MPlayableCard> {

    private final Logger log = LoggerFactory.getLogger(MPlayableCardQueryService.class);

    private final MPlayableCardRepository mPlayableCardRepository;

    private final MPlayableCardMapper mPlayableCardMapper;

    public MPlayableCardQueryService(MPlayableCardRepository mPlayableCardRepository, MPlayableCardMapper mPlayableCardMapper) {
        this.mPlayableCardRepository = mPlayableCardRepository;
        this.mPlayableCardMapper = mPlayableCardMapper;
    }

    /**
     * Return a {@link List} of {@link MPlayableCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPlayableCardDTO> findByCriteria(MPlayableCardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPlayableCard> specification = createSpecification(criteria);
        return mPlayableCardMapper.toDto(mPlayableCardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPlayableCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPlayableCardDTO> findByCriteria(MPlayableCardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPlayableCard> specification = createSpecification(criteria);
        return mPlayableCardRepository.findAll(specification, page)
            .map(mPlayableCardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPlayableCardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPlayableCard> specification = createSpecification(criteria);
        return mPlayableCardRepository.count(specification);
    }

    /**
     * Function to convert MPlayableCardCriteria to a {@link Specification}.
     */
    private Specification<MPlayableCard> createSpecification(MPlayableCardCriteria criteria) {
        Specification<MPlayableCard> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPlayableCard_.id));
            }
            if (criteria.getModelId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModelId(), MPlayableCard_.modelId));
            }
            if (criteria.getProperPositionGk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProperPositionGk(), MPlayableCard_.properPositionGk));
            }
            if (criteria.getProperPositionFw() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProperPositionFw(), MPlayableCard_.properPositionFw));
            }
            if (criteria.getProperPositionOmf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProperPositionOmf(), MPlayableCard_.properPositionOmf));
            }
            if (criteria.getProperPositionDmf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProperPositionDmf(), MPlayableCard_.properPositionDmf));
            }
            if (criteria.getProperPositionDf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProperPositionDf(), MPlayableCard_.properPositionDf));
            }
            if (criteria.getCharacterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCharacterId(), MPlayableCard_.characterId));
            }
            if (criteria.getTeamId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamId(), MPlayableCard_.teamId));
            }
            if (criteria.getNationalityId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNationalityId(), MPlayableCard_.nationalityId));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MPlayableCard_.rarity));
            }
            if (criteria.getAttribute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAttribute(), MPlayableCard_.attribute));
            }
            if (criteria.getThumbnailAssetsId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThumbnailAssetsId(), MPlayableCard_.thumbnailAssetsId));
            }
            if (criteria.getCardIllustAssetsId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCardIllustAssetsId(), MPlayableCard_.cardIllustAssetsId));
            }
            if (criteria.getPlayableAssetsId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlayableAssetsId(), MPlayableCard_.playableAssetsId));
            }
            if (criteria.getTeamEffectId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamEffectId(), MPlayableCard_.teamEffectId));
            }
            if (criteria.getTriggerEffectId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerEffectId(), MPlayableCard_.triggerEffectId));
            }
            if (criteria.getMaxActionSlot() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxActionSlot(), MPlayableCard_.maxActionSlot));
            }
            if (criteria.getInitialActionId1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialActionId1(), MPlayableCard_.initialActionId1));
            }
            if (criteria.getInitialActionId2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialActionId2(), MPlayableCard_.initialActionId2));
            }
            if (criteria.getInitialActionId3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialActionId3(), MPlayableCard_.initialActionId3));
            }
            if (criteria.getInitialActionId4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialActionId4(), MPlayableCard_.initialActionId4));
            }
            if (criteria.getInitialActionId5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialActionId5(), MPlayableCard_.initialActionId5));
            }
            if (criteria.getInitialStamina() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialStamina(), MPlayableCard_.initialStamina));
            }
            if (criteria.getInitialDribble() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialDribble(), MPlayableCard_.initialDribble));
            }
            if (criteria.getInitialShoot() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialShoot(), MPlayableCard_.initialShoot));
            }
            if (criteria.getInitialPass() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialPass(), MPlayableCard_.initialPass));
            }
            if (criteria.getInitialTackle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialTackle(), MPlayableCard_.initialTackle));
            }
            if (criteria.getInitialBlock() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialBlock(), MPlayableCard_.initialBlock));
            }
            if (criteria.getInitialIntercept() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialIntercept(), MPlayableCard_.initialIntercept));
            }
            if (criteria.getInitialSpeed() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialSpeed(), MPlayableCard_.initialSpeed));
            }
            if (criteria.getInitialPower() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialPower(), MPlayableCard_.initialPower));
            }
            if (criteria.getInitialTechnique() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialTechnique(), MPlayableCard_.initialTechnique));
            }
            if (criteria.getInitialPunching() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialPunching(), MPlayableCard_.initialPunching));
            }
            if (criteria.getInitialCatching() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialCatching(), MPlayableCard_.initialCatching));
            }
            if (criteria.getMaxStamina() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxStamina(), MPlayableCard_.maxStamina));
            }
            if (criteria.getMaxDribble() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxDribble(), MPlayableCard_.maxDribble));
            }
            if (criteria.getMaxShoot() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxShoot(), MPlayableCard_.maxShoot));
            }
            if (criteria.getMaxPass() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPass(), MPlayableCard_.maxPass));
            }
            if (criteria.getMaxTackle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxTackle(), MPlayableCard_.maxTackle));
            }
            if (criteria.getMaxBlock() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxBlock(), MPlayableCard_.maxBlock));
            }
            if (criteria.getMaxIntercept() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxIntercept(), MPlayableCard_.maxIntercept));
            }
            if (criteria.getMaxSpeed() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxSpeed(), MPlayableCard_.maxSpeed));
            }
            if (criteria.getMaxPower() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPower(), MPlayableCard_.maxPower));
            }
            if (criteria.getMaxTechnique() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxTechnique(), MPlayableCard_.maxTechnique));
            }
            if (criteria.getMaxPunching() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPunching(), MPlayableCard_.maxPunching));
            }
            if (criteria.getMaxCatching() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxCatching(), MPlayableCard_.maxCatching));
            }
            if (criteria.getMaxPlusDribble() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusDribble(), MPlayableCard_.maxPlusDribble));
            }
            if (criteria.getMaxPlusShoot() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusShoot(), MPlayableCard_.maxPlusShoot));
            }
            if (criteria.getMaxPlusPass() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusPass(), MPlayableCard_.maxPlusPass));
            }
            if (criteria.getMaxPlusTackle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusTackle(), MPlayableCard_.maxPlusTackle));
            }
            if (criteria.getMaxPlusBlock() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusBlock(), MPlayableCard_.maxPlusBlock));
            }
            if (criteria.getMaxPlusIntercept() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusIntercept(), MPlayableCard_.maxPlusIntercept));
            }
            if (criteria.getMaxPlusSpeed() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusSpeed(), MPlayableCard_.maxPlusSpeed));
            }
            if (criteria.getMaxPlusPower() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusPower(), MPlayableCard_.maxPlusPower));
            }
            if (criteria.getMaxPlusTechnique() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusTechnique(), MPlayableCard_.maxPlusTechnique));
            }
            if (criteria.getMaxPlusPunching() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusPunching(), MPlayableCard_.maxPlusPunching));
            }
            if (criteria.getMaxPlusCatching() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxPlusCatching(), MPlayableCard_.maxPlusCatching));
            }
            if (criteria.getHighBallBonus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHighBallBonus(), MPlayableCard_.highBallBonus));
            }
            if (criteria.getLowBallBonus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLowBallBonus(), MPlayableCard_.lowBallBonus));
            }
            if (criteria.getIsDropOnly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsDropOnly(), MPlayableCard_.isDropOnly));
            }
            if (criteria.getSellCoinGroupNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSellCoinGroupNum(), MPlayableCard_.sellCoinGroupNum));
            }
            if (criteria.getSellMedalId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSellMedalId(), MPlayableCard_.sellMedalId));
            }
            if (criteria.getCharacterBookId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCharacterBookId(), MPlayableCard_.characterBookId));
            }
            if (criteria.getBookNo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBookNo(), MPlayableCard_.bookNo));
            }
            if (criteria.getIsShowBook() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsShowBook(), MPlayableCard_.isShowBook));
            }
            if (criteria.getLevelGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevelGroupId(), MPlayableCard_.levelGroupId));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MPlayableCard_.startAt));
            }
            if (criteria.getMmodelcardId() != null) {
                specification = specification.and(buildSpecification(criteria.getMmodelcardId(),
                    root -> root.join(MPlayableCard_.mmodelcard, JoinType.LEFT).get(MModelCard_.id)));
            }
            if (criteria.getMArousalId() != null) {
                specification = specification.and(buildSpecification(criteria.getMArousalId(),
                    root -> root.join(MPlayableCard_.mArousals, JoinType.LEFT).get(MArousal_.id)));
            }
            if (criteria.getMTargetPlayableCardGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTargetPlayableCardGroupId(),
                    root -> root.join(MPlayableCard_.mTargetPlayableCardGroups, JoinType.LEFT).get(MTargetPlayableCardGroup_.id)));
            }
        }
        return specification;
    }
}
