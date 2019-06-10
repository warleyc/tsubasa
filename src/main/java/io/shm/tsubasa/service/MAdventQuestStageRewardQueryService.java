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

import io.shm.tsubasa.domain.MAdventQuestStageReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MAdventQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MAdventQuestStageRewardCriteria;
import io.shm.tsubasa.service.dto.MAdventQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestStageRewardMapper;

/**
 * Service for executing complex queries for {@link MAdventQuestStageReward} entities in the database.
 * The main input is a {@link MAdventQuestStageRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MAdventQuestStageRewardDTO} or a {@link Page} of {@link MAdventQuestStageRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MAdventQuestStageRewardQueryService extends QueryService<MAdventQuestStageReward> {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestStageRewardQueryService.class);

    private final MAdventQuestStageRewardRepository mAdventQuestStageRewardRepository;

    private final MAdventQuestStageRewardMapper mAdventQuestStageRewardMapper;

    public MAdventQuestStageRewardQueryService(MAdventQuestStageRewardRepository mAdventQuestStageRewardRepository, MAdventQuestStageRewardMapper mAdventQuestStageRewardMapper) {
        this.mAdventQuestStageRewardRepository = mAdventQuestStageRewardRepository;
        this.mAdventQuestStageRewardMapper = mAdventQuestStageRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MAdventQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MAdventQuestStageRewardDTO> findByCriteria(MAdventQuestStageRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MAdventQuestStageReward> specification = createSpecification(criteria);
        return mAdventQuestStageRewardMapper.toDto(mAdventQuestStageRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MAdventQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MAdventQuestStageRewardDTO> findByCriteria(MAdventQuestStageRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MAdventQuestStageReward> specification = createSpecification(criteria);
        return mAdventQuestStageRewardRepository.findAll(specification, page)
            .map(mAdventQuestStageRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MAdventQuestStageRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MAdventQuestStageReward> specification = createSpecification(criteria);
        return mAdventQuestStageRewardRepository.count(specification);
    }

    /**
     * Function to convert MAdventQuestStageRewardCriteria to a {@link Specification}.
     */
    private Specification<MAdventQuestStageReward> createSpecification(MAdventQuestStageRewardCriteria criteria) {
        Specification<MAdventQuestStageReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MAdventQuestStageReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MAdventQuestStageReward_.stageId));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MAdventQuestStageReward_.exp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MAdventQuestStageReward_.coin));
            }
            if (criteria.getGuildPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildPoint(), MAdventQuestStageReward_.guildPoint));
            }
            if (criteria.getClearRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardGroupId(), MAdventQuestStageReward_.clearRewardGroupId));
            }
            if (criteria.getClearRewardWeightId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardWeightId(), MAdventQuestStageReward_.clearRewardWeightId));
            }
            if (criteria.getAchievementRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAchievementRewardGroupId(), MAdventQuestStageReward_.achievementRewardGroupId));
            }
            if (criteria.getCoopGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoopGroupId(), MAdventQuestStageReward_.coopGroupId));
            }
            if (criteria.getSpecialRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardGroupId(), MAdventQuestStageReward_.specialRewardGroupId));
            }
            if (criteria.getSpecialRewardAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardAmount(), MAdventQuestStageReward_.specialRewardAmount));
            }
            if (criteria.getGoalRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoalRewardGroupId(), MAdventQuestStageReward_.goalRewardGroupId));
            }
        }
        return specification;
    }
}
