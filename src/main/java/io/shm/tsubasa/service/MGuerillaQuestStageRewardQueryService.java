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

import io.shm.tsubasa.domain.MGuerillaQuestStageReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuerillaQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageRewardCriteria;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestStageRewardMapper;

/**
 * Service for executing complex queries for {@link MGuerillaQuestStageReward} entities in the database.
 * The main input is a {@link MGuerillaQuestStageRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuerillaQuestStageRewardDTO} or a {@link Page} of {@link MGuerillaQuestStageRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuerillaQuestStageRewardQueryService extends QueryService<MGuerillaQuestStageReward> {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestStageRewardQueryService.class);

    private final MGuerillaQuestStageRewardRepository mGuerillaQuestStageRewardRepository;

    private final MGuerillaQuestStageRewardMapper mGuerillaQuestStageRewardMapper;

    public MGuerillaQuestStageRewardQueryService(MGuerillaQuestStageRewardRepository mGuerillaQuestStageRewardRepository, MGuerillaQuestStageRewardMapper mGuerillaQuestStageRewardMapper) {
        this.mGuerillaQuestStageRewardRepository = mGuerillaQuestStageRewardRepository;
        this.mGuerillaQuestStageRewardMapper = mGuerillaQuestStageRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MGuerillaQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuerillaQuestStageRewardDTO> findByCriteria(MGuerillaQuestStageRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuerillaQuestStageReward> specification = createSpecification(criteria);
        return mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuerillaQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuerillaQuestStageRewardDTO> findByCriteria(MGuerillaQuestStageRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuerillaQuestStageReward> specification = createSpecification(criteria);
        return mGuerillaQuestStageRewardRepository.findAll(specification, page)
            .map(mGuerillaQuestStageRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuerillaQuestStageRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuerillaQuestStageReward> specification = createSpecification(criteria);
        return mGuerillaQuestStageRewardRepository.count(specification);
    }

    /**
     * Function to convert MGuerillaQuestStageRewardCriteria to a {@link Specification}.
     */
    private Specification<MGuerillaQuestStageReward> createSpecification(MGuerillaQuestStageRewardCriteria criteria) {
        Specification<MGuerillaQuestStageReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuerillaQuestStageReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MGuerillaQuestStageReward_.stageId));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MGuerillaQuestStageReward_.exp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MGuerillaQuestStageReward_.coin));
            }
            if (criteria.getGuildPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildPoint(), MGuerillaQuestStageReward_.guildPoint));
            }
            if (criteria.getClearRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardGroupId(), MGuerillaQuestStageReward_.clearRewardGroupId));
            }
            if (criteria.getClearRewardWeightId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardWeightId(), MGuerillaQuestStageReward_.clearRewardWeightId));
            }
            if (criteria.getAchievementRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAchievementRewardGroupId(), MGuerillaQuestStageReward_.achievementRewardGroupId));
            }
            if (criteria.getCoopGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoopGroupId(), MGuerillaQuestStageReward_.coopGroupId));
            }
            if (criteria.getSpecialRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardGroupId(), MGuerillaQuestStageReward_.specialRewardGroupId));
            }
            if (criteria.getSpecialRewardAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardAmount(), MGuerillaQuestStageReward_.specialRewardAmount));
            }
            if (criteria.getGoalRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoalRewardGroupId(), MGuerillaQuestStageReward_.goalRewardGroupId));
            }
        }
        return specification;
    }
}
