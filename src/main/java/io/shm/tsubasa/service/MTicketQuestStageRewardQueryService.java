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

import io.shm.tsubasa.domain.MTicketQuestStageReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTicketQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MTicketQuestStageRewardCriteria;
import io.shm.tsubasa.service.dto.MTicketQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestStageRewardMapper;

/**
 * Service for executing complex queries for {@link MTicketQuestStageReward} entities in the database.
 * The main input is a {@link MTicketQuestStageRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTicketQuestStageRewardDTO} or a {@link Page} of {@link MTicketQuestStageRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTicketQuestStageRewardQueryService extends QueryService<MTicketQuestStageReward> {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestStageRewardQueryService.class);

    private final MTicketQuestStageRewardRepository mTicketQuestStageRewardRepository;

    private final MTicketQuestStageRewardMapper mTicketQuestStageRewardMapper;

    public MTicketQuestStageRewardQueryService(MTicketQuestStageRewardRepository mTicketQuestStageRewardRepository, MTicketQuestStageRewardMapper mTicketQuestStageRewardMapper) {
        this.mTicketQuestStageRewardRepository = mTicketQuestStageRewardRepository;
        this.mTicketQuestStageRewardMapper = mTicketQuestStageRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MTicketQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTicketQuestStageRewardDTO> findByCriteria(MTicketQuestStageRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTicketQuestStageReward> specification = createSpecification(criteria);
        return mTicketQuestStageRewardMapper.toDto(mTicketQuestStageRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTicketQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTicketQuestStageRewardDTO> findByCriteria(MTicketQuestStageRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTicketQuestStageReward> specification = createSpecification(criteria);
        return mTicketQuestStageRewardRepository.findAll(specification, page)
            .map(mTicketQuestStageRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTicketQuestStageRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTicketQuestStageReward> specification = createSpecification(criteria);
        return mTicketQuestStageRewardRepository.count(specification);
    }

    /**
     * Function to convert MTicketQuestStageRewardCriteria to a {@link Specification}.
     */
    private Specification<MTicketQuestStageReward> createSpecification(MTicketQuestStageRewardCriteria criteria) {
        Specification<MTicketQuestStageReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTicketQuestStageReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MTicketQuestStageReward_.stageId));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MTicketQuestStageReward_.exp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MTicketQuestStageReward_.coin));
            }
            if (criteria.getGuildPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildPoint(), MTicketQuestStageReward_.guildPoint));
            }
            if (criteria.getClearRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardGroupId(), MTicketQuestStageReward_.clearRewardGroupId));
            }
            if (criteria.getClearRewardWeightId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardWeightId(), MTicketQuestStageReward_.clearRewardWeightId));
            }
            if (criteria.getAchievementRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAchievementRewardGroupId(), MTicketQuestStageReward_.achievementRewardGroupId));
            }
            if (criteria.getCoopGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoopGroupId(), MTicketQuestStageReward_.coopGroupId));
            }
            if (criteria.getSpecialRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardGroupId(), MTicketQuestStageReward_.specialRewardGroupId));
            }
            if (criteria.getSpecialRewardAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardAmount(), MTicketQuestStageReward_.specialRewardAmount));
            }
            if (criteria.getGoalRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoalRewardGroupId(), MTicketQuestStageReward_.goalRewardGroupId));
            }
        }
        return specification;
    }
}
