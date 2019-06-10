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

import io.shm.tsubasa.domain.MChallengeQuestAchievementReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MChallengeQuestAchievementRewardRepository;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardCriteria;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestAchievementRewardMapper;

/**
 * Service for executing complex queries for {@link MChallengeQuestAchievementReward} entities in the database.
 * The main input is a {@link MChallengeQuestAchievementRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MChallengeQuestAchievementRewardDTO} or a {@link Page} of {@link MChallengeQuestAchievementRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MChallengeQuestAchievementRewardQueryService extends QueryService<MChallengeQuestAchievementReward> {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestAchievementRewardQueryService.class);

    private final MChallengeQuestAchievementRewardRepository mChallengeQuestAchievementRewardRepository;

    private final MChallengeQuestAchievementRewardMapper mChallengeQuestAchievementRewardMapper;

    public MChallengeQuestAchievementRewardQueryService(MChallengeQuestAchievementRewardRepository mChallengeQuestAchievementRewardRepository, MChallengeQuestAchievementRewardMapper mChallengeQuestAchievementRewardMapper) {
        this.mChallengeQuestAchievementRewardRepository = mChallengeQuestAchievementRewardRepository;
        this.mChallengeQuestAchievementRewardMapper = mChallengeQuestAchievementRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MChallengeQuestAchievementRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MChallengeQuestAchievementRewardDTO> findByCriteria(MChallengeQuestAchievementRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MChallengeQuestAchievementReward> specification = createSpecification(criteria);
        return mChallengeQuestAchievementRewardMapper.toDto(mChallengeQuestAchievementRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MChallengeQuestAchievementRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MChallengeQuestAchievementRewardDTO> findByCriteria(MChallengeQuestAchievementRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MChallengeQuestAchievementReward> specification = createSpecification(criteria);
        return mChallengeQuestAchievementRewardRepository.findAll(specification, page)
            .map(mChallengeQuestAchievementRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MChallengeQuestAchievementRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MChallengeQuestAchievementReward> specification = createSpecification(criteria);
        return mChallengeQuestAchievementRewardRepository.count(specification);
    }

    /**
     * Function to convert MChallengeQuestAchievementRewardCriteria to a {@link Specification}.
     */
    private Specification<MChallengeQuestAchievementReward> createSpecification(MChallengeQuestAchievementRewardCriteria criteria) {
        Specification<MChallengeQuestAchievementReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MChallengeQuestAchievementReward_.id));
            }
            if (criteria.getWorldId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWorldId(), MChallengeQuestAchievementReward_.worldId));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MChallengeQuestAchievementReward_.stageId));
            }
            if (criteria.getRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRewardGroupId(), MChallengeQuestAchievementReward_.rewardGroupId));
            }
        }
        return specification;
    }
}
