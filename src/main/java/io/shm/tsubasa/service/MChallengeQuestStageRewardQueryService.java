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

import io.shm.tsubasa.domain.MChallengeQuestStageReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MChallengeQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MChallengeQuestStageRewardCriteria;
import io.shm.tsubasa.service.dto.MChallengeQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestStageRewardMapper;

/**
 * Service for executing complex queries for {@link MChallengeQuestStageReward} entities in the database.
 * The main input is a {@link MChallengeQuestStageRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MChallengeQuestStageRewardDTO} or a {@link Page} of {@link MChallengeQuestStageRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MChallengeQuestStageRewardQueryService extends QueryService<MChallengeQuestStageReward> {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestStageRewardQueryService.class);

    private final MChallengeQuestStageRewardRepository mChallengeQuestStageRewardRepository;

    private final MChallengeQuestStageRewardMapper mChallengeQuestStageRewardMapper;

    public MChallengeQuestStageRewardQueryService(MChallengeQuestStageRewardRepository mChallengeQuestStageRewardRepository, MChallengeQuestStageRewardMapper mChallengeQuestStageRewardMapper) {
        this.mChallengeQuestStageRewardRepository = mChallengeQuestStageRewardRepository;
        this.mChallengeQuestStageRewardMapper = mChallengeQuestStageRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MChallengeQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MChallengeQuestStageRewardDTO> findByCriteria(MChallengeQuestStageRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MChallengeQuestStageReward> specification = createSpecification(criteria);
        return mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MChallengeQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MChallengeQuestStageRewardDTO> findByCriteria(MChallengeQuestStageRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MChallengeQuestStageReward> specification = createSpecification(criteria);
        return mChallengeQuestStageRewardRepository.findAll(specification, page)
            .map(mChallengeQuestStageRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MChallengeQuestStageRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MChallengeQuestStageReward> specification = createSpecification(criteria);
        return mChallengeQuestStageRewardRepository.count(specification);
    }

    /**
     * Function to convert MChallengeQuestStageRewardCriteria to a {@link Specification}.
     */
    private Specification<MChallengeQuestStageReward> createSpecification(MChallengeQuestStageRewardCriteria criteria) {
        Specification<MChallengeQuestStageReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MChallengeQuestStageReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MChallengeQuestStageReward_.stageId));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MChallengeQuestStageReward_.exp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MChallengeQuestStageReward_.coin));
            }
            if (criteria.getGuildPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildPoint(), MChallengeQuestStageReward_.guildPoint));
            }
            if (criteria.getClearRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardGroupId(), MChallengeQuestStageReward_.clearRewardGroupId));
            }
            if (criteria.getClearRewardWeightId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardWeightId(), MChallengeQuestStageReward_.clearRewardWeightId));
            }
            if (criteria.getAchievementRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAchievementRewardGroupId(), MChallengeQuestStageReward_.achievementRewardGroupId));
            }
            if (criteria.getCoopGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoopGroupId(), MChallengeQuestStageReward_.coopGroupId));
            }
            if (criteria.getSpecialRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardGroupId(), MChallengeQuestStageReward_.specialRewardGroupId));
            }
            if (criteria.getSpecialRewardAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardAmount(), MChallengeQuestStageReward_.specialRewardAmount));
            }
            if (criteria.getGoalRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoalRewardGroupId(), MChallengeQuestStageReward_.goalRewardGroupId));
            }
        }
        return specification;
    }
}
