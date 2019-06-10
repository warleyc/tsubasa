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

import io.shm.tsubasa.domain.MCutShootOrbit;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCutShootOrbitRepository;
import io.shm.tsubasa.service.dto.MCutShootOrbitCriteria;
import io.shm.tsubasa.service.dto.MCutShootOrbitDTO;
import io.shm.tsubasa.service.mapper.MCutShootOrbitMapper;

/**
 * Service for executing complex queries for {@link MCutShootOrbit} entities in the database.
 * The main input is a {@link MCutShootOrbitCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCutShootOrbitDTO} or a {@link Page} of {@link MCutShootOrbitDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCutShootOrbitQueryService extends QueryService<MCutShootOrbit> {

    private final Logger log = LoggerFactory.getLogger(MCutShootOrbitQueryService.class);

    private final MCutShootOrbitRepository mCutShootOrbitRepository;

    private final MCutShootOrbitMapper mCutShootOrbitMapper;

    public MCutShootOrbitQueryService(MCutShootOrbitRepository mCutShootOrbitRepository, MCutShootOrbitMapper mCutShootOrbitMapper) {
        this.mCutShootOrbitRepository = mCutShootOrbitRepository;
        this.mCutShootOrbitMapper = mCutShootOrbitMapper;
    }

    /**
     * Return a {@link List} of {@link MCutShootOrbitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCutShootOrbitDTO> findByCriteria(MCutShootOrbitCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCutShootOrbit> specification = createSpecification(criteria);
        return mCutShootOrbitMapper.toDto(mCutShootOrbitRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCutShootOrbitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCutShootOrbitDTO> findByCriteria(MCutShootOrbitCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCutShootOrbit> specification = createSpecification(criteria);
        return mCutShootOrbitRepository.findAll(specification, page)
            .map(mCutShootOrbitMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCutShootOrbitCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCutShootOrbit> specification = createSpecification(criteria);
        return mCutShootOrbitRepository.count(specification);
    }

    /**
     * Function to convert MCutShootOrbitCriteria to a {@link Specification}.
     */
    private Specification<MCutShootOrbit> createSpecification(MCutShootOrbitCriteria criteria) {
        Specification<MCutShootOrbit> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCutShootOrbit_.id));
            }
            if (criteria.getShootOrbit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShootOrbit(), MCutShootOrbit_.shootOrbit));
            }
            if (criteria.getShootResult() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShootResult(), MCutShootOrbit_.shootResult));
            }
        }
        return specification;
    }
}
