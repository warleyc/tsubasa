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

import io.shm.tsubasa.domain.MAdventQuestStage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MAdventQuestStageRepository;
import io.shm.tsubasa.service.dto.MAdventQuestStageCriteria;
import io.shm.tsubasa.service.dto.MAdventQuestStageDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestStageMapper;

/**
 * Service for executing complex queries for {@link MAdventQuestStage} entities in the database.
 * The main input is a {@link MAdventQuestStageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MAdventQuestStageDTO} or a {@link Page} of {@link MAdventQuestStageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MAdventQuestStageQueryService extends QueryService<MAdventQuestStage> {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestStageQueryService.class);

    private final MAdventQuestStageRepository mAdventQuestStageRepository;

    private final MAdventQuestStageMapper mAdventQuestStageMapper;

    public MAdventQuestStageQueryService(MAdventQuestStageRepository mAdventQuestStageRepository, MAdventQuestStageMapper mAdventQuestStageMapper) {
        this.mAdventQuestStageRepository = mAdventQuestStageRepository;
        this.mAdventQuestStageMapper = mAdventQuestStageMapper;
    }

    /**
     * Return a {@link List} of {@link MAdventQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MAdventQuestStageDTO> findByCriteria(MAdventQuestStageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MAdventQuestStage> specification = createSpecification(criteria);
        return mAdventQuestStageMapper.toDto(mAdventQuestStageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MAdventQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MAdventQuestStageDTO> findByCriteria(MAdventQuestStageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MAdventQuestStage> specification = createSpecification(criteria);
        return mAdventQuestStageRepository.findAll(specification, page)
            .map(mAdventQuestStageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MAdventQuestStageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MAdventQuestStage> specification = createSpecification(criteria);
        return mAdventQuestStageRepository.count(specification);
    }

    /**
     * Function to convert MAdventQuestStageCriteria to a {@link Specification}.
     */
    private Specification<MAdventQuestStage> createSpecification(MAdventQuestStageCriteria criteria) {
        Specification<MAdventQuestStage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MAdventQuestStage_.id));
            }
            if (criteria.getWorldId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWorldId(), MAdventQuestStage_.worldId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MAdventQuestStage_.number));
            }
            if (criteria.getDifficulty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDifficulty(), MAdventQuestStage_.difficulty));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MAdventQuestStage_.stageUnlockPattern));
            }
            if (criteria.getStoryOnly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStoryOnly(), MAdventQuestStage_.storyOnly));
            }
            if (criteria.getFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfNpcDeckId(), MAdventQuestStage_.firstHalfNpcDeckId));
            }
            if (criteria.getFirstHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfEnvironmentId(), MAdventQuestStage_.firstHalfEnvironmentId));
            }
            if (criteria.getSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfNpcDeckId(), MAdventQuestStage_.secondHalfNpcDeckId));
            }
            if (criteria.getSecondHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfEnvironmentId(), MAdventQuestStage_.secondHalfEnvironmentId));
            }
            if (criteria.getExtraFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraFirstHalfNpcDeckId(), MAdventQuestStage_.extraFirstHalfNpcDeckId));
            }
            if (criteria.getExtraSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraSecondHalfNpcDeckId(), MAdventQuestStage_.extraSecondHalfNpcDeckId));
            }
            if (criteria.getConsumeAp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumeAp(), MAdventQuestStage_.consumeAp));
            }
            if (criteria.getKickOffType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickOffType(), MAdventQuestStage_.kickOffType));
            }
            if (criteria.getMatchMinute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchMinute(), MAdventQuestStage_.matchMinute));
            }
            if (criteria.getEnableExtra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnableExtra(), MAdventQuestStage_.enableExtra));
            }
            if (criteria.getEnablePk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnablePk(), MAdventQuestStage_.enablePk));
            }
            if (criteria.getAiLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAiLevel(), MAdventQuestStage_.aiLevel));
            }
            if (criteria.getStartAtSecondHalf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAtSecondHalf(), MAdventQuestStage_.startAtSecondHalf));
            }
            if (criteria.getStartScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScore(), MAdventQuestStage_.startScore));
            }
            if (criteria.getStartScoreOpponent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScoreOpponent(), MAdventQuestStage_.startScoreOpponent));
            }
            if (criteria.getConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConditionId(), MAdventQuestStage_.conditionId));
            }
            if (criteria.getOptionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOptionId(), MAdventQuestStage_.optionId));
            }
            if (criteria.getDeckConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckConditionId(), MAdventQuestStage_.deckConditionId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MAdventQuestStage_.id, JoinType.LEFT).get(MAdventQuestWorld_.id)));
            }
        }
        return specification;
    }
}
