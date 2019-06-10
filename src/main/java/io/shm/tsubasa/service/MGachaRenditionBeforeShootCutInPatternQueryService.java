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

import io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInPattern;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionBeforeShootCutInPatternRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInPatternCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInPatternDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionBeforeShootCutInPatternMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionBeforeShootCutInPattern} entities in the database.
 * The main input is a {@link MGachaRenditionBeforeShootCutInPatternCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionBeforeShootCutInPatternDTO} or a {@link Page} of {@link MGachaRenditionBeforeShootCutInPatternDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionBeforeShootCutInPatternQueryService extends QueryService<MGachaRenditionBeforeShootCutInPattern> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionBeforeShootCutInPatternQueryService.class);

    private final MGachaRenditionBeforeShootCutInPatternRepository mGachaRenditionBeforeShootCutInPatternRepository;

    private final MGachaRenditionBeforeShootCutInPatternMapper mGachaRenditionBeforeShootCutInPatternMapper;

    public MGachaRenditionBeforeShootCutInPatternQueryService(MGachaRenditionBeforeShootCutInPatternRepository mGachaRenditionBeforeShootCutInPatternRepository, MGachaRenditionBeforeShootCutInPatternMapper mGachaRenditionBeforeShootCutInPatternMapper) {
        this.mGachaRenditionBeforeShootCutInPatternRepository = mGachaRenditionBeforeShootCutInPatternRepository;
        this.mGachaRenditionBeforeShootCutInPatternMapper = mGachaRenditionBeforeShootCutInPatternMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionBeforeShootCutInPatternDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionBeforeShootCutInPatternDTO> findByCriteria(MGachaRenditionBeforeShootCutInPatternCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionBeforeShootCutInPattern> specification = createSpecification(criteria);
        return mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPatternRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionBeforeShootCutInPatternDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionBeforeShootCutInPatternDTO> findByCriteria(MGachaRenditionBeforeShootCutInPatternCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionBeforeShootCutInPattern> specification = createSpecification(criteria);
        return mGachaRenditionBeforeShootCutInPatternRepository.findAll(specification, page)
            .map(mGachaRenditionBeforeShootCutInPatternMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionBeforeShootCutInPatternCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionBeforeShootCutInPattern> specification = createSpecification(criteria);
        return mGachaRenditionBeforeShootCutInPatternRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionBeforeShootCutInPatternCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionBeforeShootCutInPattern> createSpecification(MGachaRenditionBeforeShootCutInPatternCriteria criteria) {
        Specification<MGachaRenditionBeforeShootCutInPattern> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionBeforeShootCutInPattern_.id));
            }
            if (criteria.getRenditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRenditionId(), MGachaRenditionBeforeShootCutInPattern_.renditionId));
            }
            if (criteria.getIsManySsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsManySsr(), MGachaRenditionBeforeShootCutInPattern_.isManySsr));
            }
            if (criteria.getIsSsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSsr(), MGachaRenditionBeforeShootCutInPattern_.isSsr));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionBeforeShootCutInPattern_.weight));
            }
            if (criteria.getPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPattern(), MGachaRenditionBeforeShootCutInPattern_.pattern));
            }
            if (criteria.getExceptKickerId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExceptKickerId(), MGachaRenditionBeforeShootCutInPattern_.exceptKickerId));
            }
        }
        return specification;
    }
}
