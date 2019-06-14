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

import io.shm.tsubasa.domain.MMarathonQuestStage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonQuestStageRepository;
import io.shm.tsubasa.service.dto.MMarathonQuestStageCriteria;
import io.shm.tsubasa.service.dto.MMarathonQuestStageDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestStageMapper;

/**
 * Service for executing complex queries for {@link MMarathonQuestStage} entities in the database.
 * The main input is a {@link MMarathonQuestStageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonQuestStageDTO} or a {@link Page} of {@link MMarathonQuestStageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonQuestStageQueryService extends QueryService<MMarathonQuestStage> {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestStageQueryService.class);

    private final MMarathonQuestStageRepository mMarathonQuestStageRepository;

    private final MMarathonQuestStageMapper mMarathonQuestStageMapper;

    public MMarathonQuestStageQueryService(MMarathonQuestStageRepository mMarathonQuestStageRepository, MMarathonQuestStageMapper mMarathonQuestStageMapper) {
        this.mMarathonQuestStageRepository = mMarathonQuestStageRepository;
        this.mMarathonQuestStageMapper = mMarathonQuestStageMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonQuestStageDTO> findByCriteria(MMarathonQuestStageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonQuestStage> specification = createSpecification(criteria);
        return mMarathonQuestStageMapper.toDto(mMarathonQuestStageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonQuestStageDTO> findByCriteria(MMarathonQuestStageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonQuestStage> specification = createSpecification(criteria);
        return mMarathonQuestStageRepository.findAll(specification, page)
            .map(mMarathonQuestStageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonQuestStageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonQuestStage> specification = createSpecification(criteria);
        return mMarathonQuestStageRepository.count(specification);
    }

    /**
     * Function to convert MMarathonQuestStageCriteria to a {@link Specification}.
     */
    private Specification<MMarathonQuestStage> createSpecification(MMarathonQuestStageCriteria criteria) {
        Specification<MMarathonQuestStage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonQuestStage_.id));
            }
            if (criteria.getWorldId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWorldId(), MMarathonQuestStage_.worldId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MMarathonQuestStage_.number));
            }
            if (criteria.getDifficulty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDifficulty(), MMarathonQuestStage_.difficulty));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MMarathonQuestStage_.stageUnlockPattern));
            }
            if (criteria.getStoryOnly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStoryOnly(), MMarathonQuestStage_.storyOnly));
            }
            if (criteria.getFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfNpcDeckId(), MMarathonQuestStage_.firstHalfNpcDeckId));
            }
            if (criteria.getFirstHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfEnvironmentId(), MMarathonQuestStage_.firstHalfEnvironmentId));
            }
            if (criteria.getSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfNpcDeckId(), MMarathonQuestStage_.secondHalfNpcDeckId));
            }
            if (criteria.getSecondHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfEnvironmentId(), MMarathonQuestStage_.secondHalfEnvironmentId));
            }
            if (criteria.getExtraFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraFirstHalfNpcDeckId(), MMarathonQuestStage_.extraFirstHalfNpcDeckId));
            }
            if (criteria.getExtraSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraSecondHalfNpcDeckId(), MMarathonQuestStage_.extraSecondHalfNpcDeckId));
            }
            if (criteria.getConsumeAp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumeAp(), MMarathonQuestStage_.consumeAp));
            }
            if (criteria.getKickOffType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickOffType(), MMarathonQuestStage_.kickOffType));
            }
            if (criteria.getMatchMinute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchMinute(), MMarathonQuestStage_.matchMinute));
            }
            if (criteria.getEnableExtra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnableExtra(), MMarathonQuestStage_.enableExtra));
            }
            if (criteria.getEnablePk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnablePk(), MMarathonQuestStage_.enablePk));
            }
            if (criteria.getAiLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAiLevel(), MMarathonQuestStage_.aiLevel));
            }
            if (criteria.getStartAtSecondHalf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAtSecondHalf(), MMarathonQuestStage_.startAtSecondHalf));
            }
            if (criteria.getStartScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScore(), MMarathonQuestStage_.startScore));
            }
            if (criteria.getStartScoreOpponent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScoreOpponent(), MMarathonQuestStage_.startScoreOpponent));
            }
            if (criteria.getConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConditionId(), MMarathonQuestStage_.conditionId));
            }
            if (criteria.getOptionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOptionId(), MMarathonQuestStage_.optionId));
            }
            if (criteria.getDeckConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckConditionId(), MMarathonQuestStage_.deckConditionId));
            }
            if (criteria.getMmarathonquestworldId() != null) {
                specification = specification.and(buildSpecification(criteria.getMmarathonquestworldId(),
                    root -> root.join(MMarathonQuestStage_.mmarathonquestworld, JoinType.LEFT).get(MMarathonQuestWorld_.id)));
            }
        }
        return specification;
    }
}
