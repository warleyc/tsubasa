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

import io.shm.tsubasa.domain.MWeeklyQuestStage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MWeeklyQuestStageRepository;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageCriteria;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestStageMapper;

/**
 * Service for executing complex queries for {@link MWeeklyQuestStage} entities in the database.
 * The main input is a {@link MWeeklyQuestStageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MWeeklyQuestStageDTO} or a {@link Page} of {@link MWeeklyQuestStageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MWeeklyQuestStageQueryService extends QueryService<MWeeklyQuestStage> {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestStageQueryService.class);

    private final MWeeklyQuestStageRepository mWeeklyQuestStageRepository;

    private final MWeeklyQuestStageMapper mWeeklyQuestStageMapper;

    public MWeeklyQuestStageQueryService(MWeeklyQuestStageRepository mWeeklyQuestStageRepository, MWeeklyQuestStageMapper mWeeklyQuestStageMapper) {
        this.mWeeklyQuestStageRepository = mWeeklyQuestStageRepository;
        this.mWeeklyQuestStageMapper = mWeeklyQuestStageMapper;
    }

    /**
     * Return a {@link List} of {@link MWeeklyQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MWeeklyQuestStageDTO> findByCriteria(MWeeklyQuestStageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MWeeklyQuestStage> specification = createSpecification(criteria);
        return mWeeklyQuestStageMapper.toDto(mWeeklyQuestStageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MWeeklyQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MWeeklyQuestStageDTO> findByCriteria(MWeeklyQuestStageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MWeeklyQuestStage> specification = createSpecification(criteria);
        return mWeeklyQuestStageRepository.findAll(specification, page)
            .map(mWeeklyQuestStageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MWeeklyQuestStageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MWeeklyQuestStage> specification = createSpecification(criteria);
        return mWeeklyQuestStageRepository.count(specification);
    }

    /**
     * Function to convert MWeeklyQuestStageCriteria to a {@link Specification}.
     */
    private Specification<MWeeklyQuestStage> createSpecification(MWeeklyQuestStageCriteria criteria) {
        Specification<MWeeklyQuestStage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MWeeklyQuestStage_.id));
            }
            if (criteria.getWorldId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWorldId(), MWeeklyQuestStage_.worldId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MWeeklyQuestStage_.number));
            }
            if (criteria.getDifficulty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDifficulty(), MWeeklyQuestStage_.difficulty));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MWeeklyQuestStage_.stageUnlockPattern));
            }
            if (criteria.getStoryOnly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStoryOnly(), MWeeklyQuestStage_.storyOnly));
            }
            if (criteria.getFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfNpcDeckId(), MWeeklyQuestStage_.firstHalfNpcDeckId));
            }
            if (criteria.getFirstHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfEnvironmentId(), MWeeklyQuestStage_.firstHalfEnvironmentId));
            }
            if (criteria.getSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfNpcDeckId(), MWeeklyQuestStage_.secondHalfNpcDeckId));
            }
            if (criteria.getSecondHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfEnvironmentId(), MWeeklyQuestStage_.secondHalfEnvironmentId));
            }
            if (criteria.getExtraFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraFirstHalfNpcDeckId(), MWeeklyQuestStage_.extraFirstHalfNpcDeckId));
            }
            if (criteria.getExtraSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraSecondHalfNpcDeckId(), MWeeklyQuestStage_.extraSecondHalfNpcDeckId));
            }
            if (criteria.getConsumeAp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumeAp(), MWeeklyQuestStage_.consumeAp));
            }
            if (criteria.getKickOffType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickOffType(), MWeeklyQuestStage_.kickOffType));
            }
            if (criteria.getMatchMinute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchMinute(), MWeeklyQuestStage_.matchMinute));
            }
            if (criteria.getEnableExtra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnableExtra(), MWeeklyQuestStage_.enableExtra));
            }
            if (criteria.getEnablePk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnablePk(), MWeeklyQuestStage_.enablePk));
            }
            if (criteria.getAiLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAiLevel(), MWeeklyQuestStage_.aiLevel));
            }
            if (criteria.getStartAtSecondHalf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAtSecondHalf(), MWeeklyQuestStage_.startAtSecondHalf));
            }
            if (criteria.getStartScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScore(), MWeeklyQuestStage_.startScore));
            }
            if (criteria.getStartScoreOpponent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScoreOpponent(), MWeeklyQuestStage_.startScoreOpponent));
            }
            if (criteria.getConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConditionId(), MWeeklyQuestStage_.conditionId));
            }
            if (criteria.getOptionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOptionId(), MWeeklyQuestStage_.optionId));
            }
            if (criteria.getDeckConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckConditionId(), MWeeklyQuestStage_.deckConditionId));
            }
            if (criteria.getMweeklyquestworldId() != null) {
                specification = specification.and(buildSpecification(criteria.getMweeklyquestworldId(),
                    root -> root.join(MWeeklyQuestStage_.mweeklyquestworld, JoinType.LEFT).get(MWeeklyQuestWorld_.id)));
            }
        }
        return specification;
    }
}
