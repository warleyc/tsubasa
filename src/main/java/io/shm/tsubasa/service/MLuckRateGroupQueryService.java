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

import io.shm.tsubasa.domain.MLuckRateGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLuckRateGroupRepository;
import io.shm.tsubasa.service.dto.MLuckRateGroupCriteria;
import io.shm.tsubasa.service.dto.MLuckRateGroupDTO;
import io.shm.tsubasa.service.mapper.MLuckRateGroupMapper;

/**
 * Service for executing complex queries for {@link MLuckRateGroup} entities in the database.
 * The main input is a {@link MLuckRateGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLuckRateGroupDTO} or a {@link Page} of {@link MLuckRateGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLuckRateGroupQueryService extends QueryService<MLuckRateGroup> {

    private final Logger log = LoggerFactory.getLogger(MLuckRateGroupQueryService.class);

    private final MLuckRateGroupRepository mLuckRateGroupRepository;

    private final MLuckRateGroupMapper mLuckRateGroupMapper;

    public MLuckRateGroupQueryService(MLuckRateGroupRepository mLuckRateGroupRepository, MLuckRateGroupMapper mLuckRateGroupMapper) {
        this.mLuckRateGroupRepository = mLuckRateGroupRepository;
        this.mLuckRateGroupMapper = mLuckRateGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MLuckRateGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLuckRateGroupDTO> findByCriteria(MLuckRateGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLuckRateGroup> specification = createSpecification(criteria);
        return mLuckRateGroupMapper.toDto(mLuckRateGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLuckRateGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLuckRateGroupDTO> findByCriteria(MLuckRateGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLuckRateGroup> specification = createSpecification(criteria);
        return mLuckRateGroupRepository.findAll(specification, page)
            .map(mLuckRateGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLuckRateGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLuckRateGroup> specification = createSpecification(criteria);
        return mLuckRateGroupRepository.count(specification);
    }

    /**
     * Function to convert MLuckRateGroupCriteria to a {@link Specification}.
     */
    private Specification<MLuckRateGroup> createSpecification(MLuckRateGroupCriteria criteria) {
        Specification<MLuckRateGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLuckRateGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MLuckRateGroup_.groupId));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MLuckRateGroup_.rarity));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), MLuckRateGroup_.rate));
            }
        }
        return specification;
    }
}
