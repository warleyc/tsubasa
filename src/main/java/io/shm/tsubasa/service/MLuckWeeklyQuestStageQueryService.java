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

import io.shm.tsubasa.domain.MLuckWeeklyQuestStage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLuckWeeklyQuestStageRepository;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageCriteria;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageDTO;
import io.shm.tsubasa.service.mapper.MLuckWeeklyQuestStageMapper;

/**
 * Service for executing complex queries for {@link MLuckWeeklyQuestStage} entities in the database.
 * The main input is a {@link MLuckWeeklyQuestStageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLuckWeeklyQuestStageDTO} or a {@link Page} of {@link MLuckWeeklyQuestStageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLuckWeeklyQuestStageQueryService extends QueryService<MLuckWeeklyQuestStage> {

    private final Logger log = LoggerFactory.getLogger(MLuckWeeklyQuestStageQueryService.class);

    private final MLuckWeeklyQuestStageRepository mLuckWeeklyQuestStageRepository;

    private final MLuckWeeklyQuestStageMapper mLuckWeeklyQuestStageMapper;

    public MLuckWeeklyQuestStageQueryService(MLuckWeeklyQuestStageRepository mLuckWeeklyQuestStageRepository, MLuckWeeklyQuestStageMapper mLuckWeeklyQuestStageMapper) {
        this.mLuckWeeklyQuestStageRepository = mLuckWeeklyQuestStageRepository;
        this.mLuckWeeklyQuestStageMapper = mLuckWeeklyQuestStageMapper;
    }

    /**
     * Return a {@link List} of {@link MLuckWeeklyQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLuckWeeklyQuestStageDTO> findByCriteria(MLuckWeeklyQuestStageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLuckWeeklyQuestStage> specification = createSpecification(criteria);
        return mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLuckWeeklyQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLuckWeeklyQuestStageDTO> findByCriteria(MLuckWeeklyQuestStageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLuckWeeklyQuestStage> specification = createSpecification(criteria);
        return mLuckWeeklyQuestStageRepository.findAll(specification, page)
            .map(mLuckWeeklyQuestStageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLuckWeeklyQuestStageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLuckWeeklyQuestStage> specification = createSpecification(criteria);
        return mLuckWeeklyQuestStageRepository.count(specification);
    }

    /**
     * Function to convert MLuckWeeklyQuestStageCriteria to a {@link Specification}.
     */
    private Specification<MLuckWeeklyQuestStage> createSpecification(MLuckWeeklyQuestStageCriteria criteria) {
        Specification<MLuckWeeklyQuestStage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLuckWeeklyQuestStage_.id));
            }
            if (criteria.getWorldId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWorldId(), MLuckWeeklyQuestStage_.worldId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MLuckWeeklyQuestStage_.number));
            }
            if (criteria.getDifficulty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDifficulty(), MLuckWeeklyQuestStage_.difficulty));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MLuckWeeklyQuestStage_.stageUnlockPattern));
            }
            if (criteria.getStoryOnly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStoryOnly(), MLuckWeeklyQuestStage_.storyOnly));
            }
            if (criteria.getFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfNpcDeckId(), MLuckWeeklyQuestStage_.firstHalfNpcDeckId));
            }
            if (criteria.getFirstHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfEnvironmentId(), MLuckWeeklyQuestStage_.firstHalfEnvironmentId));
            }
            if (criteria.getSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfNpcDeckId(), MLuckWeeklyQuestStage_.secondHalfNpcDeckId));
            }
            if (criteria.getSecondHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfEnvironmentId(), MLuckWeeklyQuestStage_.secondHalfEnvironmentId));
            }
            if (criteria.getExtraFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraFirstHalfNpcDeckId(), MLuckWeeklyQuestStage_.extraFirstHalfNpcDeckId));
            }
            if (criteria.getExtraSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraSecondHalfNpcDeckId(), MLuckWeeklyQuestStage_.extraSecondHalfNpcDeckId));
            }
            if (criteria.getConsumeAp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumeAp(), MLuckWeeklyQuestStage_.consumeAp));
            }
            if (criteria.getKickOffType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickOffType(), MLuckWeeklyQuestStage_.kickOffType));
            }
            if (criteria.getMatchMinute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchMinute(), MLuckWeeklyQuestStage_.matchMinute));
            }
            if (criteria.getEnableExtra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnableExtra(), MLuckWeeklyQuestStage_.enableExtra));
            }
            if (criteria.getEnablePk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnablePk(), MLuckWeeklyQuestStage_.enablePk));
            }
            if (criteria.getAiLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAiLevel(), MLuckWeeklyQuestStage_.aiLevel));
            }
            if (criteria.getStartAtSecondHalf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAtSecondHalf(), MLuckWeeklyQuestStage_.startAtSecondHalf));
            }
            if (criteria.getStartScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScore(), MLuckWeeklyQuestStage_.startScore));
            }
            if (criteria.getStartScoreOpponent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScoreOpponent(), MLuckWeeklyQuestStage_.startScoreOpponent));
            }
            if (criteria.getConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConditionId(), MLuckWeeklyQuestStage_.conditionId));
            }
            if (criteria.getOptionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOptionId(), MLuckWeeklyQuestStage_.optionId));
            }
            if (criteria.getDeckConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckConditionId(), MLuckWeeklyQuestStage_.deckConditionId));
            }
            if (criteria.getLuckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLuckId(), MLuckWeeklyQuestStage_.luckId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MLuckWeeklyQuestStage_.id, JoinType.LEFT).get(MLuckWeeklyQuestWorld_.id)));
            }
        }
        return specification;
    }
}
