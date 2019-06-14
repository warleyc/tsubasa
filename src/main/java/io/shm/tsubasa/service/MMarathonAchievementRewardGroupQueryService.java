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

import io.shm.tsubasa.domain.MMarathonAchievementRewardGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonAchievementRewardGroupRepository;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardGroupCriteria;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MMarathonAchievementRewardGroupMapper;

/**
 * Service for executing complex queries for {@link MMarathonAchievementRewardGroup} entities in the database.
 * The main input is a {@link MMarathonAchievementRewardGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonAchievementRewardGroupDTO} or a {@link Page} of {@link MMarathonAchievementRewardGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonAchievementRewardGroupQueryService extends QueryService<MMarathonAchievementRewardGroup> {

    private final Logger log = LoggerFactory.getLogger(MMarathonAchievementRewardGroupQueryService.class);

    private final MMarathonAchievementRewardGroupRepository mMarathonAchievementRewardGroupRepository;

    private final MMarathonAchievementRewardGroupMapper mMarathonAchievementRewardGroupMapper;

    public MMarathonAchievementRewardGroupQueryService(MMarathonAchievementRewardGroupRepository mMarathonAchievementRewardGroupRepository, MMarathonAchievementRewardGroupMapper mMarathonAchievementRewardGroupMapper) {
        this.mMarathonAchievementRewardGroupRepository = mMarathonAchievementRewardGroupRepository;
        this.mMarathonAchievementRewardGroupMapper = mMarathonAchievementRewardGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonAchievementRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonAchievementRewardGroupDTO> findByCriteria(MMarathonAchievementRewardGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonAchievementRewardGroup> specification = createSpecification(criteria);
        return mMarathonAchievementRewardGroupMapper.toDto(mMarathonAchievementRewardGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonAchievementRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonAchievementRewardGroupDTO> findByCriteria(MMarathonAchievementRewardGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonAchievementRewardGroup> specification = createSpecification(criteria);
        return mMarathonAchievementRewardGroupRepository.findAll(specification, page)
            .map(mMarathonAchievementRewardGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonAchievementRewardGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonAchievementRewardGroup> specification = createSpecification(criteria);
        return mMarathonAchievementRewardGroupRepository.count(specification);
    }

    /**
     * Function to convert MMarathonAchievementRewardGroupCriteria to a {@link Specification}.
     */
    private Specification<MMarathonAchievementRewardGroup> createSpecification(MMarathonAchievementRewardGroupCriteria criteria) {
        Specification<MMarathonAchievementRewardGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonAchievementRewardGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MMarathonAchievementRewardGroup_.groupId));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MMarathonAchievementRewardGroup_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MMarathonAchievementRewardGroup_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MMarathonAchievementRewardGroup_.contentAmount));
            }
        }
        return specification;
    }
}
