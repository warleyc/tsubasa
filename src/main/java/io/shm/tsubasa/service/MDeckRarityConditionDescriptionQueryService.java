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

import io.shm.tsubasa.domain.MDeckRarityConditionDescription;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDeckRarityConditionDescriptionRepository;
import io.shm.tsubasa.service.dto.MDeckRarityConditionDescriptionCriteria;
import io.shm.tsubasa.service.dto.MDeckRarityConditionDescriptionDTO;
import io.shm.tsubasa.service.mapper.MDeckRarityConditionDescriptionMapper;

/**
 * Service for executing complex queries for {@link MDeckRarityConditionDescription} entities in the database.
 * The main input is a {@link MDeckRarityConditionDescriptionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDeckRarityConditionDescriptionDTO} or a {@link Page} of {@link MDeckRarityConditionDescriptionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDeckRarityConditionDescriptionQueryService extends QueryService<MDeckRarityConditionDescription> {

    private final Logger log = LoggerFactory.getLogger(MDeckRarityConditionDescriptionQueryService.class);

    private final MDeckRarityConditionDescriptionRepository mDeckRarityConditionDescriptionRepository;

    private final MDeckRarityConditionDescriptionMapper mDeckRarityConditionDescriptionMapper;

    public MDeckRarityConditionDescriptionQueryService(MDeckRarityConditionDescriptionRepository mDeckRarityConditionDescriptionRepository, MDeckRarityConditionDescriptionMapper mDeckRarityConditionDescriptionMapper) {
        this.mDeckRarityConditionDescriptionRepository = mDeckRarityConditionDescriptionRepository;
        this.mDeckRarityConditionDescriptionMapper = mDeckRarityConditionDescriptionMapper;
    }

    /**
     * Return a {@link List} of {@link MDeckRarityConditionDescriptionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDeckRarityConditionDescriptionDTO> findByCriteria(MDeckRarityConditionDescriptionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDeckRarityConditionDescription> specification = createSpecification(criteria);
        return mDeckRarityConditionDescriptionMapper.toDto(mDeckRarityConditionDescriptionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDeckRarityConditionDescriptionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDeckRarityConditionDescriptionDTO> findByCriteria(MDeckRarityConditionDescriptionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDeckRarityConditionDescription> specification = createSpecification(criteria);
        return mDeckRarityConditionDescriptionRepository.findAll(specification, page)
            .map(mDeckRarityConditionDescriptionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDeckRarityConditionDescriptionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDeckRarityConditionDescription> specification = createSpecification(criteria);
        return mDeckRarityConditionDescriptionRepository.count(specification);
    }

    /**
     * Function to convert MDeckRarityConditionDescriptionCriteria to a {@link Specification}.
     */
    private Specification<MDeckRarityConditionDescription> createSpecification(MDeckRarityConditionDescriptionCriteria criteria) {
        Specification<MDeckRarityConditionDescription> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDeckRarityConditionDescription_.id));
            }
            if (criteria.getRarityGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarityGroupId(), MDeckRarityConditionDescription_.rarityGroupId));
            }
            if (criteria.getCountType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCountType(), MDeckRarityConditionDescription_.countType));
            }
            if (criteria.getIsStarting() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsStarting(), MDeckRarityConditionDescription_.isStarting));
            }
        }
        return specification;
    }
}
