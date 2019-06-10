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

import io.shm.tsubasa.domain.MGachaRenditionExtraCutin;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionExtraCutinRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionExtraCutinCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionExtraCutinDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionExtraCutinMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionExtraCutin} entities in the database.
 * The main input is a {@link MGachaRenditionExtraCutinCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionExtraCutinDTO} or a {@link Page} of {@link MGachaRenditionExtraCutinDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionExtraCutinQueryService extends QueryService<MGachaRenditionExtraCutin> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionExtraCutinQueryService.class);

    private final MGachaRenditionExtraCutinRepository mGachaRenditionExtraCutinRepository;

    private final MGachaRenditionExtraCutinMapper mGachaRenditionExtraCutinMapper;

    public MGachaRenditionExtraCutinQueryService(MGachaRenditionExtraCutinRepository mGachaRenditionExtraCutinRepository, MGachaRenditionExtraCutinMapper mGachaRenditionExtraCutinMapper) {
        this.mGachaRenditionExtraCutinRepository = mGachaRenditionExtraCutinRepository;
        this.mGachaRenditionExtraCutinMapper = mGachaRenditionExtraCutinMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionExtraCutinDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionExtraCutinDTO> findByCriteria(MGachaRenditionExtraCutinCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionExtraCutin> specification = createSpecification(criteria);
        return mGachaRenditionExtraCutinMapper.toDto(mGachaRenditionExtraCutinRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionExtraCutinDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionExtraCutinDTO> findByCriteria(MGachaRenditionExtraCutinCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionExtraCutin> specification = createSpecification(criteria);
        return mGachaRenditionExtraCutinRepository.findAll(specification, page)
            .map(mGachaRenditionExtraCutinMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionExtraCutinCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionExtraCutin> specification = createSpecification(criteria);
        return mGachaRenditionExtraCutinRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionExtraCutinCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionExtraCutin> createSpecification(MGachaRenditionExtraCutinCriteria criteria) {
        Specification<MGachaRenditionExtraCutin> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionExtraCutin_.id));
            }
            if (criteria.getRenditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRenditionId(), MGachaRenditionExtraCutin_.renditionId));
            }
        }
        return specification;
    }
}
