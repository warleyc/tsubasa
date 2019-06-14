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

import io.shm.tsubasa.domain.MTimeattackQuestStage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTimeattackQuestStageRepository;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageCriteria;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageDTO;
import io.shm.tsubasa.service.mapper.MTimeattackQuestStageMapper;

/**
 * Service for executing complex queries for {@link MTimeattackQuestStage} entities in the database.
 * The main input is a {@link MTimeattackQuestStageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTimeattackQuestStageDTO} or a {@link Page} of {@link MTimeattackQuestStageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTimeattackQuestStageQueryService extends QueryService<MTimeattackQuestStage> {

    private final Logger log = LoggerFactory.getLogger(MTimeattackQuestStageQueryService.class);

    private final MTimeattackQuestStageRepository mTimeattackQuestStageRepository;

    private final MTimeattackQuestStageMapper mTimeattackQuestStageMapper;

    public MTimeattackQuestStageQueryService(MTimeattackQuestStageRepository mTimeattackQuestStageRepository, MTimeattackQuestStageMapper mTimeattackQuestStageMapper) {
        this.mTimeattackQuestStageRepository = mTimeattackQuestStageRepository;
        this.mTimeattackQuestStageMapper = mTimeattackQuestStageMapper;
    }

    /**
     * Return a {@link List} of {@link MTimeattackQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTimeattackQuestStageDTO> findByCriteria(MTimeattackQuestStageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTimeattackQuestStage> specification = createSpecification(criteria);
        return mTimeattackQuestStageMapper.toDto(mTimeattackQuestStageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTimeattackQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTimeattackQuestStageDTO> findByCriteria(MTimeattackQuestStageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTimeattackQuestStage> specification = createSpecification(criteria);
        return mTimeattackQuestStageRepository.findAll(specification, page)
            .map(mTimeattackQuestStageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTimeattackQuestStageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTimeattackQuestStage> specification = createSpecification(criteria);
        return mTimeattackQuestStageRepository.count(specification);
    }

    /**
     * Function to convert MTimeattackQuestStageCriteria to a {@link Specification}.
     */
    private Specification<MTimeattackQuestStage> createSpecification(MTimeattackQuestStageCriteria criteria) {
        Specification<MTimeattackQuestStage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTimeattackQuestStage_.id));
            }
            if (criteria.getWorldId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWorldId(), MTimeattackQuestStage_.worldId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MTimeattackQuestStage_.number));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MTimeattackQuestStage_.startAt));
            }
            if (criteria.getDifficulty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDifficulty(), MTimeattackQuestStage_.difficulty));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MTimeattackQuestStage_.stageUnlockPattern));
            }
            if (criteria.getStoryOnly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStoryOnly(), MTimeattackQuestStage_.storyOnly));
            }
            if (criteria.getFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfNpcDeckId(), MTimeattackQuestStage_.firstHalfNpcDeckId));
            }
            if (criteria.getFirstHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfEnvironmentId(), MTimeattackQuestStage_.firstHalfEnvironmentId));
            }
            if (criteria.getSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfNpcDeckId(), MTimeattackQuestStage_.secondHalfNpcDeckId));
            }
            if (criteria.getSecondHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfEnvironmentId(), MTimeattackQuestStage_.secondHalfEnvironmentId));
            }
            if (criteria.getExtraFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraFirstHalfNpcDeckId(), MTimeattackQuestStage_.extraFirstHalfNpcDeckId));
            }
            if (criteria.getConsumeAp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumeAp(), MTimeattackQuestStage_.consumeAp));
            }
            if (criteria.getTicketId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTicketId(), MTimeattackQuestStage_.ticketId));
            }
            if (criteria.getTicketAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTicketAmount(), MTimeattackQuestStage_.ticketAmount));
            }
            if (criteria.getKickOffType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickOffType(), MTimeattackQuestStage_.kickOffType));
            }
            if (criteria.getMatchMinute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchMinute(), MTimeattackQuestStage_.matchMinute));
            }
            if (criteria.getEnableExtra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnableExtra(), MTimeattackQuestStage_.enableExtra));
            }
            if (criteria.getEnablePk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnablePk(), MTimeattackQuestStage_.enablePk));
            }
            if (criteria.getAiLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAiLevel(), MTimeattackQuestStage_.aiLevel));
            }
            if (criteria.getStartAtSecondHalf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAtSecondHalf(), MTimeattackQuestStage_.startAtSecondHalf));
            }
            if (criteria.getStartScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScore(), MTimeattackQuestStage_.startScore));
            }
            if (criteria.getStartScoreOpponent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScoreOpponent(), MTimeattackQuestStage_.startScoreOpponent));
            }
            if (criteria.getConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConditionId(), MTimeattackQuestStage_.conditionId));
            }
            if (criteria.getOptionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOptionId(), MTimeattackQuestStage_.optionId));
            }
            if (criteria.getDeckConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckConditionId(), MTimeattackQuestStage_.deckConditionId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MTimeattackQuestStage_.id, JoinType.LEFT).get(MTimeattackQuestWorld_.id)));
            }
        }
        return specification;
    }
}
