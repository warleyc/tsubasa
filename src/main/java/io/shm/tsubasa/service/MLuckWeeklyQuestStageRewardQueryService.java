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

import io.shm.tsubasa.domain.MLuckWeeklyQuestStageReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLuckWeeklyQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageRewardCriteria;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MLuckWeeklyQuestStageRewardMapper;

/**
 * Service for executing complex queries for {@link MLuckWeeklyQuestStageReward} entities in the database.
 * The main input is a {@link MLuckWeeklyQuestStageRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLuckWeeklyQuestStageRewardDTO} or a {@link Page} of {@link MLuckWeeklyQuestStageRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLuckWeeklyQuestStageRewardQueryService extends QueryService<MLuckWeeklyQuestStageReward> {

    private final Logger log = LoggerFactory.getLogger(MLuckWeeklyQuestStageRewardQueryService.class);

    private final MLuckWeeklyQuestStageRewardRepository mLuckWeeklyQuestStageRewardRepository;

    private final MLuckWeeklyQuestStageRewardMapper mLuckWeeklyQuestStageRewardMapper;

    public MLuckWeeklyQuestStageRewardQueryService(MLuckWeeklyQuestStageRewardRepository mLuckWeeklyQuestStageRewardRepository, MLuckWeeklyQuestStageRewardMapper mLuckWeeklyQuestStageRewardMapper) {
        this.mLuckWeeklyQuestStageRewardRepository = mLuckWeeklyQuestStageRewardRepository;
        this.mLuckWeeklyQuestStageRewardMapper = mLuckWeeklyQuestStageRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MLuckWeeklyQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLuckWeeklyQuestStageRewardDTO> findByCriteria(MLuckWeeklyQuestStageRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLuckWeeklyQuestStageReward> specification = createSpecification(criteria);
        return mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLuckWeeklyQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLuckWeeklyQuestStageRewardDTO> findByCriteria(MLuckWeeklyQuestStageRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLuckWeeklyQuestStageReward> specification = createSpecification(criteria);
        return mLuckWeeklyQuestStageRewardRepository.findAll(specification, page)
            .map(mLuckWeeklyQuestStageRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLuckWeeklyQuestStageRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLuckWeeklyQuestStageReward> specification = createSpecification(criteria);
        return mLuckWeeklyQuestStageRewardRepository.count(specification);
    }

    /**
     * Function to convert MLuckWeeklyQuestStageRewardCriteria to a {@link Specification}.
     */
    private Specification<MLuckWeeklyQuestStageReward> createSpecification(MLuckWeeklyQuestStageRewardCriteria criteria) {
        Specification<MLuckWeeklyQuestStageReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLuckWeeklyQuestStageReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MLuckWeeklyQuestStageReward_.stageId));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MLuckWeeklyQuestStageReward_.exp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MLuckWeeklyQuestStageReward_.coin));
            }
            if (criteria.getGuildPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildPoint(), MLuckWeeklyQuestStageReward_.guildPoint));
            }
            if (criteria.getClearRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardGroupId(), MLuckWeeklyQuestStageReward_.clearRewardGroupId));
            }
            if (criteria.getClearRewardWeightId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardWeightId(), MLuckWeeklyQuestStageReward_.clearRewardWeightId));
            }
            if (criteria.getAchievementRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAchievementRewardGroupId(), MLuckWeeklyQuestStageReward_.achievementRewardGroupId));
            }
            if (criteria.getCoopGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoopGroupId(), MLuckWeeklyQuestStageReward_.coopGroupId));
            }
            if (criteria.getSpecialRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardGroupId(), MLuckWeeklyQuestStageReward_.specialRewardGroupId));
            }
            if (criteria.getSpecialRewardAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardAmount(), MLuckWeeklyQuestStageReward_.specialRewardAmount));
            }
            if (criteria.getGoalRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoalRewardGroupId(), MLuckWeeklyQuestStageReward_.goalRewardGroupId));
            }
        }
        return specification;
    }
}
