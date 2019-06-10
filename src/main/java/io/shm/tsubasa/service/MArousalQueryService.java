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

import io.shm.tsubasa.domain.MArousal;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MArousalRepository;
import io.shm.tsubasa.service.dto.MArousalCriteria;
import io.shm.tsubasa.service.dto.MArousalDTO;
import io.shm.tsubasa.service.mapper.MArousalMapper;

/**
 * Service for executing complex queries for {@link MArousal} entities in the database.
 * The main input is a {@link MArousalCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MArousalDTO} or a {@link Page} of {@link MArousalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MArousalQueryService extends QueryService<MArousal> {

    private final Logger log = LoggerFactory.getLogger(MArousalQueryService.class);

    private final MArousalRepository mArousalRepository;

    private final MArousalMapper mArousalMapper;

    public MArousalQueryService(MArousalRepository mArousalRepository, MArousalMapper mArousalMapper) {
        this.mArousalRepository = mArousalRepository;
        this.mArousalMapper = mArousalMapper;
    }

    /**
     * Return a {@link List} of {@link MArousalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MArousalDTO> findByCriteria(MArousalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MArousal> specification = createSpecification(criteria);
        return mArousalMapper.toDto(mArousalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MArousalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MArousalDTO> findByCriteria(MArousalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MArousal> specification = createSpecification(criteria);
        return mArousalRepository.findAll(specification, page)
            .map(mArousalMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MArousalCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MArousal> specification = createSpecification(criteria);
        return mArousalRepository.count(specification);
    }

    /**
     * Function to convert MArousalCriteria to a {@link Specification}.
     */
    private Specification<MArousal> createSpecification(MArousalCriteria criteria) {
        Specification<MArousal> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MArousal_.id));
            }
            if (criteria.getBeforeId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBeforeId(), MArousal_.beforeId));
            }
            if (criteria.getAfterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAfterId(), MArousal_.afterId));
            }
            if (criteria.getCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCost(), MArousal_.cost));
            }
            if (criteria.getMaterialGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaterialGroupId(), MArousal_.materialGroupId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MArousal_.id, JoinType.LEFT).get(MPlayableCard_.id)));
            }
        }
        return specification;
    }
}
