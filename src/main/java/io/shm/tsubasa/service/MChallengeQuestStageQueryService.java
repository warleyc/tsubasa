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

import io.shm.tsubasa.domain.MChallengeQuestStage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MChallengeQuestStageRepository;
import io.shm.tsubasa.service.dto.MChallengeQuestStageCriteria;
import io.shm.tsubasa.service.dto.MChallengeQuestStageDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestStageMapper;

/**
 * Service for executing complex queries for {@link MChallengeQuestStage} entities in the database.
 * The main input is a {@link MChallengeQuestStageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MChallengeQuestStageDTO} or a {@link Page} of {@link MChallengeQuestStageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MChallengeQuestStageQueryService extends QueryService<MChallengeQuestStage> {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestStageQueryService.class);

    private final MChallengeQuestStageRepository mChallengeQuestStageRepository;

    private final MChallengeQuestStageMapper mChallengeQuestStageMapper;

    public MChallengeQuestStageQueryService(MChallengeQuestStageRepository mChallengeQuestStageRepository, MChallengeQuestStageMapper mChallengeQuestStageMapper) {
        this.mChallengeQuestStageRepository = mChallengeQuestStageRepository;
        this.mChallengeQuestStageMapper = mChallengeQuestStageMapper;
    }

    /**
     * Return a {@link List} of {@link MChallengeQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MChallengeQuestStageDTO> findByCriteria(MChallengeQuestStageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MChallengeQuestStage> specification = createSpecification(criteria);
        return mChallengeQuestStageMapper.toDto(mChallengeQuestStageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MChallengeQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MChallengeQuestStageDTO> findByCriteria(MChallengeQuestStageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MChallengeQuestStage> specification = createSpecification(criteria);
        return mChallengeQuestStageRepository.findAll(specification, page)
            .map(mChallengeQuestStageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MChallengeQuestStageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MChallengeQuestStage> specification = createSpecification(criteria);
        return mChallengeQuestStageRepository.count(specification);
    }

    /**
     * Function to convert MChallengeQuestStageCriteria to a {@link Specification}.
     */
    private Specification<MChallengeQuestStage> createSpecification(MChallengeQuestStageCriteria criteria) {
        Specification<MChallengeQuestStage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MChallengeQuestStage_.id));
            }
            if (criteria.getWorldId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWorldId(), MChallengeQuestStage_.worldId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MChallengeQuestStage_.number));
            }
            if (criteria.getDifficulty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDifficulty(), MChallengeQuestStage_.difficulty));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MChallengeQuestStage_.stageUnlockPattern));
            }
            if (criteria.getStoryOnly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStoryOnly(), MChallengeQuestStage_.storyOnly));
            }
            if (criteria.getFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfNpcDeckId(), MChallengeQuestStage_.firstHalfNpcDeckId));
            }
            if (criteria.getFirstHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfEnvironmentId(), MChallengeQuestStage_.firstHalfEnvironmentId));
            }
            if (criteria.getSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfNpcDeckId(), MChallengeQuestStage_.secondHalfNpcDeckId));
            }
            if (criteria.getSecondHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfEnvironmentId(), MChallengeQuestStage_.secondHalfEnvironmentId));
            }
            if (criteria.getExtraFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraFirstHalfNpcDeckId(), MChallengeQuestStage_.extraFirstHalfNpcDeckId));
            }
            if (criteria.getConsumeAp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumeAp(), MChallengeQuestStage_.consumeAp));
            }
            if (criteria.getKickOffType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickOffType(), MChallengeQuestStage_.kickOffType));
            }
            if (criteria.getMatchMinute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchMinute(), MChallengeQuestStage_.matchMinute));
            }
            if (criteria.getEnableExtra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnableExtra(), MChallengeQuestStage_.enableExtra));
            }
            if (criteria.getEnablePk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnablePk(), MChallengeQuestStage_.enablePk));
            }
            if (criteria.getAiLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAiLevel(), MChallengeQuestStage_.aiLevel));
            }
            if (criteria.getStartAtSecondHalf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAtSecondHalf(), MChallengeQuestStage_.startAtSecondHalf));
            }
            if (criteria.getStartScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScore(), MChallengeQuestStage_.startScore));
            }
            if (criteria.getStartScoreOpponent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScoreOpponent(), MChallengeQuestStage_.startScoreOpponent));
            }
            if (criteria.getConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConditionId(), MChallengeQuestStage_.conditionId));
            }
            if (criteria.getOptionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOptionId(), MChallengeQuestStage_.optionId));
            }
            if (criteria.getDeckConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckConditionId(), MChallengeQuestStage_.deckConditionId));
            }
            if (criteria.getSkipCheckPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSkipCheckPoint(), MChallengeQuestStage_.skipCheckPoint));
            }
            if (criteria.getRoadNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRoadNumber(), MChallengeQuestStage_.roadNumber));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MChallengeQuestStage_.id, JoinType.LEFT).get(MChallengeQuestWorld_.id)));
            }
        }
        return specification;
    }
}
