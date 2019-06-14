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

import io.shm.tsubasa.domain.MTimeattackRankingRewardGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTimeattackRankingRewardGroupRepository;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardGroupCriteria;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MTimeattackRankingRewardGroupMapper;

/**
 * Service for executing complex queries for {@link MTimeattackRankingRewardGroup} entities in the database.
 * The main input is a {@link MTimeattackRankingRewardGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTimeattackRankingRewardGroupDTO} or a {@link Page} of {@link MTimeattackRankingRewardGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTimeattackRankingRewardGroupQueryService extends QueryService<MTimeattackRankingRewardGroup> {

    private final Logger log = LoggerFactory.getLogger(MTimeattackRankingRewardGroupQueryService.class);

    private final MTimeattackRankingRewardGroupRepository mTimeattackRankingRewardGroupRepository;

    private final MTimeattackRankingRewardGroupMapper mTimeattackRankingRewardGroupMapper;

    public MTimeattackRankingRewardGroupQueryService(MTimeattackRankingRewardGroupRepository mTimeattackRankingRewardGroupRepository, MTimeattackRankingRewardGroupMapper mTimeattackRankingRewardGroupMapper) {
        this.mTimeattackRankingRewardGroupRepository = mTimeattackRankingRewardGroupRepository;
        this.mTimeattackRankingRewardGroupMapper = mTimeattackRankingRewardGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MTimeattackRankingRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTimeattackRankingRewardGroupDTO> findByCriteria(MTimeattackRankingRewardGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTimeattackRankingRewardGroup> specification = createSpecification(criteria);
        return mTimeattackRankingRewardGroupMapper.toDto(mTimeattackRankingRewardGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTimeattackRankingRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTimeattackRankingRewardGroupDTO> findByCriteria(MTimeattackRankingRewardGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTimeattackRankingRewardGroup> specification = createSpecification(criteria);
        return mTimeattackRankingRewardGroupRepository.findAll(specification, page)
            .map(mTimeattackRankingRewardGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTimeattackRankingRewardGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTimeattackRankingRewardGroup> specification = createSpecification(criteria);
        return mTimeattackRankingRewardGroupRepository.count(specification);
    }

    /**
     * Function to convert MTimeattackRankingRewardGroupCriteria to a {@link Specification}.
     */
    private Specification<MTimeattackRankingRewardGroup> createSpecification(MTimeattackRankingRewardGroupCriteria criteria) {
        Specification<MTimeattackRankingRewardGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTimeattackRankingRewardGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MTimeattackRankingRewardGroup_.groupId));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MTimeattackRankingRewardGroup_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MTimeattackRankingRewardGroup_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MTimeattackRankingRewardGroup_.contentAmount));
            }
        }
        return specification;
    }
}
