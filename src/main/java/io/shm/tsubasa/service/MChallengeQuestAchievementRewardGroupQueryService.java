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

import io.shm.tsubasa.domain.MChallengeQuestAchievementRewardGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MChallengeQuestAchievementRewardGroupRepository;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardGroupCriteria;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestAchievementRewardGroupMapper;

/**
 * Service for executing complex queries for {@link MChallengeQuestAchievementRewardGroup} entities in the database.
 * The main input is a {@link MChallengeQuestAchievementRewardGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MChallengeQuestAchievementRewardGroupDTO} or a {@link Page} of {@link MChallengeQuestAchievementRewardGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MChallengeQuestAchievementRewardGroupQueryService extends QueryService<MChallengeQuestAchievementRewardGroup> {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestAchievementRewardGroupQueryService.class);

    private final MChallengeQuestAchievementRewardGroupRepository mChallengeQuestAchievementRewardGroupRepository;

    private final MChallengeQuestAchievementRewardGroupMapper mChallengeQuestAchievementRewardGroupMapper;

    public MChallengeQuestAchievementRewardGroupQueryService(MChallengeQuestAchievementRewardGroupRepository mChallengeQuestAchievementRewardGroupRepository, MChallengeQuestAchievementRewardGroupMapper mChallengeQuestAchievementRewardGroupMapper) {
        this.mChallengeQuestAchievementRewardGroupRepository = mChallengeQuestAchievementRewardGroupRepository;
        this.mChallengeQuestAchievementRewardGroupMapper = mChallengeQuestAchievementRewardGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MChallengeQuestAchievementRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MChallengeQuestAchievementRewardGroupDTO> findByCriteria(MChallengeQuestAchievementRewardGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MChallengeQuestAchievementRewardGroup> specification = createSpecification(criteria);
        return mChallengeQuestAchievementRewardGroupMapper.toDto(mChallengeQuestAchievementRewardGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MChallengeQuestAchievementRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MChallengeQuestAchievementRewardGroupDTO> findByCriteria(MChallengeQuestAchievementRewardGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MChallengeQuestAchievementRewardGroup> specification = createSpecification(criteria);
        return mChallengeQuestAchievementRewardGroupRepository.findAll(specification, page)
            .map(mChallengeQuestAchievementRewardGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MChallengeQuestAchievementRewardGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MChallengeQuestAchievementRewardGroup> specification = createSpecification(criteria);
        return mChallengeQuestAchievementRewardGroupRepository.count(specification);
    }

    /**
     * Function to convert MChallengeQuestAchievementRewardGroupCriteria to a {@link Specification}.
     */
    private Specification<MChallengeQuestAchievementRewardGroup> createSpecification(MChallengeQuestAchievementRewardGroupCriteria criteria) {
        Specification<MChallengeQuestAchievementRewardGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MChallengeQuestAchievementRewardGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MChallengeQuestAchievementRewardGroup_.groupId));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MChallengeQuestAchievementRewardGroup_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MChallengeQuestAchievementRewardGroup_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MChallengeQuestAchievementRewardGroup_.contentAmount));
            }
        }
        return specification;
    }
}
