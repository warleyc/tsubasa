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

import io.shm.tsubasa.domain.MMarathonLoopRewardGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonLoopRewardGroupRepository;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardGroupCriteria;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MMarathonLoopRewardGroupMapper;

/**
 * Service for executing complex queries for {@link MMarathonLoopRewardGroup} entities in the database.
 * The main input is a {@link MMarathonLoopRewardGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonLoopRewardGroupDTO} or a {@link Page} of {@link MMarathonLoopRewardGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonLoopRewardGroupQueryService extends QueryService<MMarathonLoopRewardGroup> {

    private final Logger log = LoggerFactory.getLogger(MMarathonLoopRewardGroupQueryService.class);

    private final MMarathonLoopRewardGroupRepository mMarathonLoopRewardGroupRepository;

    private final MMarathonLoopRewardGroupMapper mMarathonLoopRewardGroupMapper;

    public MMarathonLoopRewardGroupQueryService(MMarathonLoopRewardGroupRepository mMarathonLoopRewardGroupRepository, MMarathonLoopRewardGroupMapper mMarathonLoopRewardGroupMapper) {
        this.mMarathonLoopRewardGroupRepository = mMarathonLoopRewardGroupRepository;
        this.mMarathonLoopRewardGroupMapper = mMarathonLoopRewardGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonLoopRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonLoopRewardGroupDTO> findByCriteria(MMarathonLoopRewardGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonLoopRewardGroup> specification = createSpecification(criteria);
        return mMarathonLoopRewardGroupMapper.toDto(mMarathonLoopRewardGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonLoopRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonLoopRewardGroupDTO> findByCriteria(MMarathonLoopRewardGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonLoopRewardGroup> specification = createSpecification(criteria);
        return mMarathonLoopRewardGroupRepository.findAll(specification, page)
            .map(mMarathonLoopRewardGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonLoopRewardGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonLoopRewardGroup> specification = createSpecification(criteria);
        return mMarathonLoopRewardGroupRepository.count(specification);
    }

    /**
     * Function to convert MMarathonLoopRewardGroupCriteria to a {@link Specification}.
     */
    private Specification<MMarathonLoopRewardGroup> createSpecification(MMarathonLoopRewardGroupCriteria criteria) {
        Specification<MMarathonLoopRewardGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonLoopRewardGroup_.id));
            }
            if (criteria.getEventId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventId(), MMarathonLoopRewardGroup_.eventId));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MMarathonLoopRewardGroup_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MMarathonLoopRewardGroup_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MMarathonLoopRewardGroup_.contentAmount));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MMarathonLoopRewardGroup_.weight));
            }
        }
        return specification;
    }
}
