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

import io.shm.tsubasa.domain.MGuerillaQuestStage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuerillaQuestStageRepository;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageCriteria;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestStageMapper;

/**
 * Service for executing complex queries for {@link MGuerillaQuestStage} entities in the database.
 * The main input is a {@link MGuerillaQuestStageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuerillaQuestStageDTO} or a {@link Page} of {@link MGuerillaQuestStageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuerillaQuestStageQueryService extends QueryService<MGuerillaQuestStage> {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestStageQueryService.class);

    private final MGuerillaQuestStageRepository mGuerillaQuestStageRepository;

    private final MGuerillaQuestStageMapper mGuerillaQuestStageMapper;

    public MGuerillaQuestStageQueryService(MGuerillaQuestStageRepository mGuerillaQuestStageRepository, MGuerillaQuestStageMapper mGuerillaQuestStageMapper) {
        this.mGuerillaQuestStageRepository = mGuerillaQuestStageRepository;
        this.mGuerillaQuestStageMapper = mGuerillaQuestStageMapper;
    }

    /**
     * Return a {@link List} of {@link MGuerillaQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuerillaQuestStageDTO> findByCriteria(MGuerillaQuestStageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuerillaQuestStage> specification = createSpecification(criteria);
        return mGuerillaQuestStageMapper.toDto(mGuerillaQuestStageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuerillaQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuerillaQuestStageDTO> findByCriteria(MGuerillaQuestStageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuerillaQuestStage> specification = createSpecification(criteria);
        return mGuerillaQuestStageRepository.findAll(specification, page)
            .map(mGuerillaQuestStageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuerillaQuestStageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuerillaQuestStage> specification = createSpecification(criteria);
        return mGuerillaQuestStageRepository.count(specification);
    }

    /**
     * Function to convert MGuerillaQuestStageCriteria to a {@link Specification}.
     */
    private Specification<MGuerillaQuestStage> createSpecification(MGuerillaQuestStageCriteria criteria) {
        Specification<MGuerillaQuestStage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuerillaQuestStage_.id));
            }
            if (criteria.getWorldId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWorldId(), MGuerillaQuestStage_.worldId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MGuerillaQuestStage_.number));
            }
            if (criteria.getDifficulty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDifficulty(), MGuerillaQuestStage_.difficulty));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MGuerillaQuestStage_.stageUnlockPattern));
            }
            if (criteria.getStoryOnly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStoryOnly(), MGuerillaQuestStage_.storyOnly));
            }
            if (criteria.getFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfNpcDeckId(), MGuerillaQuestStage_.firstHalfNpcDeckId));
            }
            if (criteria.getFirstHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfEnvironmentId(), MGuerillaQuestStage_.firstHalfEnvironmentId));
            }
            if (criteria.getSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfNpcDeckId(), MGuerillaQuestStage_.secondHalfNpcDeckId));
            }
            if (criteria.getSecondHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfEnvironmentId(), MGuerillaQuestStage_.secondHalfEnvironmentId));
            }
            if (criteria.getExtraFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraFirstHalfNpcDeckId(), MGuerillaQuestStage_.extraFirstHalfNpcDeckId));
            }
            if (criteria.getExtraSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraSecondHalfNpcDeckId(), MGuerillaQuestStage_.extraSecondHalfNpcDeckId));
            }
            if (criteria.getConsumeAp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumeAp(), MGuerillaQuestStage_.consumeAp));
            }
            if (criteria.getKickOffType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickOffType(), MGuerillaQuestStage_.kickOffType));
            }
            if (criteria.getMatchMinute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchMinute(), MGuerillaQuestStage_.matchMinute));
            }
            if (criteria.getEnableExtra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnableExtra(), MGuerillaQuestStage_.enableExtra));
            }
            if (criteria.getEnablePk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnablePk(), MGuerillaQuestStage_.enablePk));
            }
            if (criteria.getAiLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAiLevel(), MGuerillaQuestStage_.aiLevel));
            }
            if (criteria.getStartAtSecondHalf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAtSecondHalf(), MGuerillaQuestStage_.startAtSecondHalf));
            }
            if (criteria.getStartScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScore(), MGuerillaQuestStage_.startScore));
            }
            if (criteria.getStartScoreOpponent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScoreOpponent(), MGuerillaQuestStage_.startScoreOpponent));
            }
            if (criteria.getConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConditionId(), MGuerillaQuestStage_.conditionId));
            }
            if (criteria.getOptionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOptionId(), MGuerillaQuestStage_.optionId));
            }
            if (criteria.getDeckConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckConditionId(), MGuerillaQuestStage_.deckConditionId));
            }
            if (criteria.getMguerillaquestworldId() != null) {
                specification = specification.and(buildSpecification(criteria.getMguerillaquestworldId(),
                    root -> root.join(MGuerillaQuestStage_.mguerillaquestworld, JoinType.LEFT).get(MGuerillaQuestWorld_.id)));
            }
        }
        return specification;
    }
}
