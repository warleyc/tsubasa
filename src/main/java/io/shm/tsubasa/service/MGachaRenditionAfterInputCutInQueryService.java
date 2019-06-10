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

import io.shm.tsubasa.domain.MGachaRenditionAfterInputCutIn;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionAfterInputCutInRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionAfterInputCutInMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionAfterInputCutIn} entities in the database.
 * The main input is a {@link MGachaRenditionAfterInputCutInCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionAfterInputCutInDTO} or a {@link Page} of {@link MGachaRenditionAfterInputCutInDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionAfterInputCutInQueryService extends QueryService<MGachaRenditionAfterInputCutIn> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionAfterInputCutInQueryService.class);

    private final MGachaRenditionAfterInputCutInRepository mGachaRenditionAfterInputCutInRepository;

    private final MGachaRenditionAfterInputCutInMapper mGachaRenditionAfterInputCutInMapper;

    public MGachaRenditionAfterInputCutInQueryService(MGachaRenditionAfterInputCutInRepository mGachaRenditionAfterInputCutInRepository, MGachaRenditionAfterInputCutInMapper mGachaRenditionAfterInputCutInMapper) {
        this.mGachaRenditionAfterInputCutInRepository = mGachaRenditionAfterInputCutInRepository;
        this.mGachaRenditionAfterInputCutInMapper = mGachaRenditionAfterInputCutInMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionAfterInputCutInDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionAfterInputCutInDTO> findByCriteria(MGachaRenditionAfterInputCutInCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionAfterInputCutIn> specification = createSpecification(criteria);
        return mGachaRenditionAfterInputCutInMapper.toDto(mGachaRenditionAfterInputCutInRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionAfterInputCutInDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionAfterInputCutInDTO> findByCriteria(MGachaRenditionAfterInputCutInCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionAfterInputCutIn> specification = createSpecification(criteria);
        return mGachaRenditionAfterInputCutInRepository.findAll(specification, page)
            .map(mGachaRenditionAfterInputCutInMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionAfterInputCutInCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionAfterInputCutIn> specification = createSpecification(criteria);
        return mGachaRenditionAfterInputCutInRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionAfterInputCutInCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionAfterInputCutIn> createSpecification(MGachaRenditionAfterInputCutInCriteria criteria) {
        Specification<MGachaRenditionAfterInputCutIn> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionAfterInputCutIn_.id));
            }
            if (criteria.getRenditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRenditionId(), MGachaRenditionAfterInputCutIn_.renditionId));
            }
            if (criteria.getIsSsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSsr(), MGachaRenditionAfterInputCutIn_.isSsr));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionAfterInputCutIn_.weight));
            }
        }
        return specification;
    }
}
