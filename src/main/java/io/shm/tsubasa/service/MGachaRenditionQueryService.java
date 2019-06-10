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

import io.shm.tsubasa.domain.MGachaRendition;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionMapper;

/**
 * Service for executing complex queries for {@link MGachaRendition} entities in the database.
 * The main input is a {@link MGachaRenditionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionDTO} or a {@link Page} of {@link MGachaRenditionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionQueryService extends QueryService<MGachaRendition> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionQueryService.class);

    private final MGachaRenditionRepository mGachaRenditionRepository;

    private final MGachaRenditionMapper mGachaRenditionMapper;

    public MGachaRenditionQueryService(MGachaRenditionRepository mGachaRenditionRepository, MGachaRenditionMapper mGachaRenditionMapper) {
        this.mGachaRenditionRepository = mGachaRenditionRepository;
        this.mGachaRenditionMapper = mGachaRenditionMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionDTO> findByCriteria(MGachaRenditionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRendition> specification = createSpecification(criteria);
        return mGachaRenditionMapper.toDto(mGachaRenditionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionDTO> findByCriteria(MGachaRenditionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRendition> specification = createSpecification(criteria);
        return mGachaRenditionRepository.findAll(specification, page)
            .map(mGachaRenditionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRendition> specification = createSpecification(criteria);
        return mGachaRenditionRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionCriteria to a {@link Specification}.
     */
    private Specification<MGachaRendition> createSpecification(MGachaRenditionCriteria criteria) {
        Specification<MGachaRendition> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRendition_.id));
            }
        }
        return specification;
    }
}
