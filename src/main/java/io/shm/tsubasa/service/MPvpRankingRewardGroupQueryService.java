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

import io.shm.tsubasa.domain.MPvpRankingRewardGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPvpRankingRewardGroupRepository;
import io.shm.tsubasa.service.dto.MPvpRankingRewardGroupCriteria;
import io.shm.tsubasa.service.dto.MPvpRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MPvpRankingRewardGroupMapper;

/**
 * Service for executing complex queries for {@link MPvpRankingRewardGroup} entities in the database.
 * The main input is a {@link MPvpRankingRewardGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPvpRankingRewardGroupDTO} or a {@link Page} of {@link MPvpRankingRewardGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPvpRankingRewardGroupQueryService extends QueryService<MPvpRankingRewardGroup> {

    private final Logger log = LoggerFactory.getLogger(MPvpRankingRewardGroupQueryService.class);

    private final MPvpRankingRewardGroupRepository mPvpRankingRewardGroupRepository;

    private final MPvpRankingRewardGroupMapper mPvpRankingRewardGroupMapper;

    public MPvpRankingRewardGroupQueryService(MPvpRankingRewardGroupRepository mPvpRankingRewardGroupRepository, MPvpRankingRewardGroupMapper mPvpRankingRewardGroupMapper) {
        this.mPvpRankingRewardGroupRepository = mPvpRankingRewardGroupRepository;
        this.mPvpRankingRewardGroupMapper = mPvpRankingRewardGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MPvpRankingRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPvpRankingRewardGroupDTO> findByCriteria(MPvpRankingRewardGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPvpRankingRewardGroup> specification = createSpecification(criteria);
        return mPvpRankingRewardGroupMapper.toDto(mPvpRankingRewardGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPvpRankingRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpRankingRewardGroupDTO> findByCriteria(MPvpRankingRewardGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPvpRankingRewardGroup> specification = createSpecification(criteria);
        return mPvpRankingRewardGroupRepository.findAll(specification, page)
            .map(mPvpRankingRewardGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPvpRankingRewardGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPvpRankingRewardGroup> specification = createSpecification(criteria);
        return mPvpRankingRewardGroupRepository.count(specification);
    }

    /**
     * Function to convert MPvpRankingRewardGroupCriteria to a {@link Specification}.
     */
    private Specification<MPvpRankingRewardGroup> createSpecification(MPvpRankingRewardGroupCriteria criteria) {
        Specification<MPvpRankingRewardGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPvpRankingRewardGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MPvpRankingRewardGroup_.groupId));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MPvpRankingRewardGroup_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MPvpRankingRewardGroup_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MPvpRankingRewardGroup_.contentAmount));
            }
        }
        return specification;
    }
}
