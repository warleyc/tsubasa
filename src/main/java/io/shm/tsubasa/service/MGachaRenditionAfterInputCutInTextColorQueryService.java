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

import io.shm.tsubasa.domain.MGachaRenditionAfterInputCutInTextColor;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionAfterInputCutInTextColorRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInTextColorCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInTextColorDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionAfterInputCutInTextColorMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionAfterInputCutInTextColor} entities in the database.
 * The main input is a {@link MGachaRenditionAfterInputCutInTextColorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionAfterInputCutInTextColorDTO} or a {@link Page} of {@link MGachaRenditionAfterInputCutInTextColorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionAfterInputCutInTextColorQueryService extends QueryService<MGachaRenditionAfterInputCutInTextColor> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionAfterInputCutInTextColorQueryService.class);

    private final MGachaRenditionAfterInputCutInTextColorRepository mGachaRenditionAfterInputCutInTextColorRepository;

    private final MGachaRenditionAfterInputCutInTextColorMapper mGachaRenditionAfterInputCutInTextColorMapper;

    public MGachaRenditionAfterInputCutInTextColorQueryService(MGachaRenditionAfterInputCutInTextColorRepository mGachaRenditionAfterInputCutInTextColorRepository, MGachaRenditionAfterInputCutInTextColorMapper mGachaRenditionAfterInputCutInTextColorMapper) {
        this.mGachaRenditionAfterInputCutInTextColorRepository = mGachaRenditionAfterInputCutInTextColorRepository;
        this.mGachaRenditionAfterInputCutInTextColorMapper = mGachaRenditionAfterInputCutInTextColorMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionAfterInputCutInTextColorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionAfterInputCutInTextColorDTO> findByCriteria(MGachaRenditionAfterInputCutInTextColorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionAfterInputCutInTextColor> specification = createSpecification(criteria);
        return mGachaRenditionAfterInputCutInTextColorMapper.toDto(mGachaRenditionAfterInputCutInTextColorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionAfterInputCutInTextColorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionAfterInputCutInTextColorDTO> findByCriteria(MGachaRenditionAfterInputCutInTextColorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionAfterInputCutInTextColor> specification = createSpecification(criteria);
        return mGachaRenditionAfterInputCutInTextColorRepository.findAll(specification, page)
            .map(mGachaRenditionAfterInputCutInTextColorMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionAfterInputCutInTextColorCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionAfterInputCutInTextColor> specification = createSpecification(criteria);
        return mGachaRenditionAfterInputCutInTextColorRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionAfterInputCutInTextColorCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionAfterInputCutInTextColor> createSpecification(MGachaRenditionAfterInputCutInTextColorCriteria criteria) {
        Specification<MGachaRenditionAfterInputCutInTextColor> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionAfterInputCutInTextColor_.id));
            }
            if (criteria.getIsSsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSsr(), MGachaRenditionAfterInputCutInTextColor_.isSsr));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionAfterInputCutInTextColor_.weight));
            }
        }
        return specification;
    }
}
