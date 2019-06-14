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

import io.shm.tsubasa.domain.MMarathonRankingRewardGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonRankingRewardGroupRepository;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardGroupCriteria;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MMarathonRankingRewardGroupMapper;

/**
 * Service for executing complex queries for {@link MMarathonRankingRewardGroup} entities in the database.
 * The main input is a {@link MMarathonRankingRewardGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonRankingRewardGroupDTO} or a {@link Page} of {@link MMarathonRankingRewardGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonRankingRewardGroupQueryService extends QueryService<MMarathonRankingRewardGroup> {

    private final Logger log = LoggerFactory.getLogger(MMarathonRankingRewardGroupQueryService.class);

    private final MMarathonRankingRewardGroupRepository mMarathonRankingRewardGroupRepository;

    private final MMarathonRankingRewardGroupMapper mMarathonRankingRewardGroupMapper;

    public MMarathonRankingRewardGroupQueryService(MMarathonRankingRewardGroupRepository mMarathonRankingRewardGroupRepository, MMarathonRankingRewardGroupMapper mMarathonRankingRewardGroupMapper) {
        this.mMarathonRankingRewardGroupRepository = mMarathonRankingRewardGroupRepository;
        this.mMarathonRankingRewardGroupMapper = mMarathonRankingRewardGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonRankingRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonRankingRewardGroupDTO> findByCriteria(MMarathonRankingRewardGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonRankingRewardGroup> specification = createSpecification(criteria);
        return mMarathonRankingRewardGroupMapper.toDto(mMarathonRankingRewardGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonRankingRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonRankingRewardGroupDTO> findByCriteria(MMarathonRankingRewardGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonRankingRewardGroup> specification = createSpecification(criteria);
        return mMarathonRankingRewardGroupRepository.findAll(specification, page)
            .map(mMarathonRankingRewardGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonRankingRewardGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonRankingRewardGroup> specification = createSpecification(criteria);
        return mMarathonRankingRewardGroupRepository.count(specification);
    }

    /**
     * Function to convert MMarathonRankingRewardGroupCriteria to a {@link Specification}.
     */
    private Specification<MMarathonRankingRewardGroup> createSpecification(MMarathonRankingRewardGroupCriteria criteria) {
        Specification<MMarathonRankingRewardGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonRankingRewardGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MMarathonRankingRewardGroup_.groupId));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MMarathonRankingRewardGroup_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MMarathonRankingRewardGroup_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MMarathonRankingRewardGroup_.contentAmount));
            }
        }
        return specification;
    }
}
