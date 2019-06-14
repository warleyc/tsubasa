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

import io.shm.tsubasa.domain.MWeeklyQuestStageReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MWeeklyQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageRewardCriteria;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestStageRewardMapper;

/**
 * Service for executing complex queries for {@link MWeeklyQuestStageReward} entities in the database.
 * The main input is a {@link MWeeklyQuestStageRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MWeeklyQuestStageRewardDTO} or a {@link Page} of {@link MWeeklyQuestStageRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MWeeklyQuestStageRewardQueryService extends QueryService<MWeeklyQuestStageReward> {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestStageRewardQueryService.class);

    private final MWeeklyQuestStageRewardRepository mWeeklyQuestStageRewardRepository;

    private final MWeeklyQuestStageRewardMapper mWeeklyQuestStageRewardMapper;

    public MWeeklyQuestStageRewardQueryService(MWeeklyQuestStageRewardRepository mWeeklyQuestStageRewardRepository, MWeeklyQuestStageRewardMapper mWeeklyQuestStageRewardMapper) {
        this.mWeeklyQuestStageRewardRepository = mWeeklyQuestStageRewardRepository;
        this.mWeeklyQuestStageRewardMapper = mWeeklyQuestStageRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MWeeklyQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MWeeklyQuestStageRewardDTO> findByCriteria(MWeeklyQuestStageRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MWeeklyQuestStageReward> specification = createSpecification(criteria);
        return mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MWeeklyQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MWeeklyQuestStageRewardDTO> findByCriteria(MWeeklyQuestStageRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MWeeklyQuestStageReward> specification = createSpecification(criteria);
        return mWeeklyQuestStageRewardRepository.findAll(specification, page)
            .map(mWeeklyQuestStageRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MWeeklyQuestStageRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MWeeklyQuestStageReward> specification = createSpecification(criteria);
        return mWeeklyQuestStageRewardRepository.count(specification);
    }

    /**
     * Function to convert MWeeklyQuestStageRewardCriteria to a {@link Specification}.
     */
    private Specification<MWeeklyQuestStageReward> createSpecification(MWeeklyQuestStageRewardCriteria criteria) {
        Specification<MWeeklyQuestStageReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MWeeklyQuestStageReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MWeeklyQuestStageReward_.stageId));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MWeeklyQuestStageReward_.exp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MWeeklyQuestStageReward_.coin));
            }
            if (criteria.getGuildPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildPoint(), MWeeklyQuestStageReward_.guildPoint));
            }
            if (criteria.getClearRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardGroupId(), MWeeklyQuestStageReward_.clearRewardGroupId));
            }
            if (criteria.getClearRewardWeightId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardWeightId(), MWeeklyQuestStageReward_.clearRewardWeightId));
            }
            if (criteria.getAchievementRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAchievementRewardGroupId(), MWeeklyQuestStageReward_.achievementRewardGroupId));
            }
            if (criteria.getCoopGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoopGroupId(), MWeeklyQuestStageReward_.coopGroupId));
            }
            if (criteria.getSpecialRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardGroupId(), MWeeklyQuestStageReward_.specialRewardGroupId));
            }
            if (criteria.getSpecialRewardAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardAmount(), MWeeklyQuestStageReward_.specialRewardAmount));
            }
            if (criteria.getGoalRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoalRewardGroupId(), MWeeklyQuestStageReward_.goalRewardGroupId));
            }
        }
        return specification;
    }
}
