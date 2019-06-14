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

import io.shm.tsubasa.domain.MTicketQuestStage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTicketQuestStageRepository;
import io.shm.tsubasa.service.dto.MTicketQuestStageCriteria;
import io.shm.tsubasa.service.dto.MTicketQuestStageDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestStageMapper;

/**
 * Service for executing complex queries for {@link MTicketQuestStage} entities in the database.
 * The main input is a {@link MTicketQuestStageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTicketQuestStageDTO} or a {@link Page} of {@link MTicketQuestStageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTicketQuestStageQueryService extends QueryService<MTicketQuestStage> {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestStageQueryService.class);

    private final MTicketQuestStageRepository mTicketQuestStageRepository;

    private final MTicketQuestStageMapper mTicketQuestStageMapper;

    public MTicketQuestStageQueryService(MTicketQuestStageRepository mTicketQuestStageRepository, MTicketQuestStageMapper mTicketQuestStageMapper) {
        this.mTicketQuestStageRepository = mTicketQuestStageRepository;
        this.mTicketQuestStageMapper = mTicketQuestStageMapper;
    }

    /**
     * Return a {@link List} of {@link MTicketQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTicketQuestStageDTO> findByCriteria(MTicketQuestStageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTicketQuestStage> specification = createSpecification(criteria);
        return mTicketQuestStageMapper.toDto(mTicketQuestStageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTicketQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTicketQuestStageDTO> findByCriteria(MTicketQuestStageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTicketQuestStage> specification = createSpecification(criteria);
        return mTicketQuestStageRepository.findAll(specification, page)
            .map(mTicketQuestStageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTicketQuestStageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTicketQuestStage> specification = createSpecification(criteria);
        return mTicketQuestStageRepository.count(specification);
    }

    /**
     * Function to convert MTicketQuestStageCriteria to a {@link Specification}.
     */
    private Specification<MTicketQuestStage> createSpecification(MTicketQuestStageCriteria criteria) {
        Specification<MTicketQuestStage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTicketQuestStage_.id));
            }
            if (criteria.getWorldId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWorldId(), MTicketQuestStage_.worldId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MTicketQuestStage_.number));
            }
            if (criteria.getTicketId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTicketId(), MTicketQuestStage_.ticketId));
            }
            if (criteria.getTicketAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTicketAmount(), MTicketQuestStage_.ticketAmount));
            }
            if (criteria.getDifficulty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDifficulty(), MTicketQuestStage_.difficulty));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MTicketQuestStage_.stageUnlockPattern));
            }
            if (criteria.getStoryOnly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStoryOnly(), MTicketQuestStage_.storyOnly));
            }
            if (criteria.getFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfNpcDeckId(), MTicketQuestStage_.firstHalfNpcDeckId));
            }
            if (criteria.getFirstHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstHalfEnvironmentId(), MTicketQuestStage_.firstHalfEnvironmentId));
            }
            if (criteria.getSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfNpcDeckId(), MTicketQuestStage_.secondHalfNpcDeckId));
            }
            if (criteria.getSecondHalfEnvironmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondHalfEnvironmentId(), MTicketQuestStage_.secondHalfEnvironmentId));
            }
            if (criteria.getExtraFirstHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraFirstHalfNpcDeckId(), MTicketQuestStage_.extraFirstHalfNpcDeckId));
            }
            if (criteria.getExtraSecondHalfNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraSecondHalfNpcDeckId(), MTicketQuestStage_.extraSecondHalfNpcDeckId));
            }
            if (criteria.getConsumeAp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumeAp(), MTicketQuestStage_.consumeAp));
            }
            if (criteria.getKickOffType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickOffType(), MTicketQuestStage_.kickOffType));
            }
            if (criteria.getMatchMinute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchMinute(), MTicketQuestStage_.matchMinute));
            }
            if (criteria.getEnableExtra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnableExtra(), MTicketQuestStage_.enableExtra));
            }
            if (criteria.getEnablePk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnablePk(), MTicketQuestStage_.enablePk));
            }
            if (criteria.getAiLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAiLevel(), MTicketQuestStage_.aiLevel));
            }
            if (criteria.getStartAtSecondHalf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAtSecondHalf(), MTicketQuestStage_.startAtSecondHalf));
            }
            if (criteria.getStartScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScore(), MTicketQuestStage_.startScore));
            }
            if (criteria.getStartScoreOpponent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartScoreOpponent(), MTicketQuestStage_.startScoreOpponent));
            }
            if (criteria.getConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConditionId(), MTicketQuestStage_.conditionId));
            }
            if (criteria.getOptionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOptionId(), MTicketQuestStage_.optionId));
            }
            if (criteria.getDeckConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckConditionId(), MTicketQuestStage_.deckConditionId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MTicketQuestStage_.id, JoinType.LEFT).get(MTicketQuestWorld_.id)));
            }
        }
        return specification;
    }
}
