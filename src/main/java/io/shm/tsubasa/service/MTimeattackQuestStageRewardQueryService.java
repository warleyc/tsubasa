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

import io.shm.tsubasa.domain.MTimeattackQuestStageReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTimeattackQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageRewardCriteria;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MTimeattackQuestStageRewardMapper;

/**
 * Service for executing complex queries for {@link MTimeattackQuestStageReward} entities in the database.
 * The main input is a {@link MTimeattackQuestStageRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTimeattackQuestStageRewardDTO} or a {@link Page} of {@link MTimeattackQuestStageRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTimeattackQuestStageRewardQueryService extends QueryService<MTimeattackQuestStageReward> {

    private final Logger log = LoggerFactory.getLogger(MTimeattackQuestStageRewardQueryService.class);

    private final MTimeattackQuestStageRewardRepository mTimeattackQuestStageRewardRepository;

    private final MTimeattackQuestStageRewardMapper mTimeattackQuestStageRewardMapper;

    public MTimeattackQuestStageRewardQueryService(MTimeattackQuestStageRewardRepository mTimeattackQuestStageRewardRepository, MTimeattackQuestStageRewardMapper mTimeattackQuestStageRewardMapper) {
        this.mTimeattackQuestStageRewardRepository = mTimeattackQuestStageRewardRepository;
        this.mTimeattackQuestStageRewardMapper = mTimeattackQuestStageRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MTimeattackQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTimeattackQuestStageRewardDTO> findByCriteria(MTimeattackQuestStageRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTimeattackQuestStageReward> specification = createSpecification(criteria);
        return mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTimeattackQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTimeattackQuestStageRewardDTO> findByCriteria(MTimeattackQuestStageRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTimeattackQuestStageReward> specification = createSpecification(criteria);
        return mTimeattackQuestStageRewardRepository.findAll(specification, page)
            .map(mTimeattackQuestStageRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTimeattackQuestStageRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTimeattackQuestStageReward> specification = createSpecification(criteria);
        return mTimeattackQuestStageRewardRepository.count(specification);
    }

    /**
     * Function to convert MTimeattackQuestStageRewardCriteria to a {@link Specification}.
     */
    private Specification<MTimeattackQuestStageReward> createSpecification(MTimeattackQuestStageRewardCriteria criteria) {
        Specification<MTimeattackQuestStageReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTimeattackQuestStageReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MTimeattackQuestStageReward_.stageId));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MTimeattackQuestStageReward_.exp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MTimeattackQuestStageReward_.coin));
            }
            if (criteria.getGuildPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildPoint(), MTimeattackQuestStageReward_.guildPoint));
            }
            if (criteria.getClearRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardGroupId(), MTimeattackQuestStageReward_.clearRewardGroupId));
            }
            if (criteria.getClearRewardWeightId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardWeightId(), MTimeattackQuestStageReward_.clearRewardWeightId));
            }
            if (criteria.getAchievementRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAchievementRewardGroupId(), MTimeattackQuestStageReward_.achievementRewardGroupId));
            }
            if (criteria.getCoopGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoopGroupId(), MTimeattackQuestStageReward_.coopGroupId));
            }
            if (criteria.getSpecialRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardGroupId(), MTimeattackQuestStageReward_.specialRewardGroupId));
            }
            if (criteria.getSpecialRewardAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardAmount(), MTimeattackQuestStageReward_.specialRewardAmount));
            }
            if (criteria.getGoalRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoalRewardGroupId(), MTimeattackQuestStageReward_.goalRewardGroupId));
            }
        }
        return specification;
    }
}
