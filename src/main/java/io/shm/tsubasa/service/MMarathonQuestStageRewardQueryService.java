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

import io.shm.tsubasa.domain.MMarathonQuestStageReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MMarathonQuestStageRewardCriteria;
import io.shm.tsubasa.service.dto.MMarathonQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestStageRewardMapper;

/**
 * Service for executing complex queries for {@link MMarathonQuestStageReward} entities in the database.
 * The main input is a {@link MMarathonQuestStageRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonQuestStageRewardDTO} or a {@link Page} of {@link MMarathonQuestStageRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonQuestStageRewardQueryService extends QueryService<MMarathonQuestStageReward> {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestStageRewardQueryService.class);

    private final MMarathonQuestStageRewardRepository mMarathonQuestStageRewardRepository;

    private final MMarathonQuestStageRewardMapper mMarathonQuestStageRewardMapper;

    public MMarathonQuestStageRewardQueryService(MMarathonQuestStageRewardRepository mMarathonQuestStageRewardRepository, MMarathonQuestStageRewardMapper mMarathonQuestStageRewardMapper) {
        this.mMarathonQuestStageRewardRepository = mMarathonQuestStageRewardRepository;
        this.mMarathonQuestStageRewardMapper = mMarathonQuestStageRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonQuestStageRewardDTO> findByCriteria(MMarathonQuestStageRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonQuestStageReward> specification = createSpecification(criteria);
        return mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonQuestStageRewardDTO> findByCriteria(MMarathonQuestStageRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonQuestStageReward> specification = createSpecification(criteria);
        return mMarathonQuestStageRewardRepository.findAll(specification, page)
            .map(mMarathonQuestStageRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonQuestStageRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonQuestStageReward> specification = createSpecification(criteria);
        return mMarathonQuestStageRewardRepository.count(specification);
    }

    /**
     * Function to convert MMarathonQuestStageRewardCriteria to a {@link Specification}.
     */
    private Specification<MMarathonQuestStageReward> createSpecification(MMarathonQuestStageRewardCriteria criteria) {
        Specification<MMarathonQuestStageReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonQuestStageReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MMarathonQuestStageReward_.stageId));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MMarathonQuestStageReward_.exp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MMarathonQuestStageReward_.coin));
            }
            if (criteria.getGuildPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildPoint(), MMarathonQuestStageReward_.guildPoint));
            }
            if (criteria.getClearRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardGroupId(), MMarathonQuestStageReward_.clearRewardGroupId));
            }
            if (criteria.getClearRewardWeightId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardWeightId(), MMarathonQuestStageReward_.clearRewardWeightId));
            }
            if (criteria.getAchievementRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAchievementRewardGroupId(), MMarathonQuestStageReward_.achievementRewardGroupId));
            }
            if (criteria.getCoopGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoopGroupId(), MMarathonQuestStageReward_.coopGroupId));
            }
            if (criteria.getSpecialRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardGroupId(), MMarathonQuestStageReward_.specialRewardGroupId));
            }
            if (criteria.getSpecialRewardAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardAmount(), MMarathonQuestStageReward_.specialRewardAmount));
            }
            if (criteria.getGoalRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoalRewardGroupId(), MMarathonQuestStageReward_.goalRewardGroupId));
            }
        }
        return specification;
    }
}
