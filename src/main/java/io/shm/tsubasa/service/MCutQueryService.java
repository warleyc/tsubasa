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

import io.shm.tsubasa.domain.MCut;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCutRepository;
import io.shm.tsubasa.service.dto.MCutCriteria;
import io.shm.tsubasa.service.dto.MCutDTO;
import io.shm.tsubasa.service.mapper.MCutMapper;

/**
 * Service for executing complex queries for {@link MCut} entities in the database.
 * The main input is a {@link MCutCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCutDTO} or a {@link Page} of {@link MCutDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCutQueryService extends QueryService<MCut> {

    private final Logger log = LoggerFactory.getLogger(MCutQueryService.class);

    private final MCutRepository mCutRepository;

    private final MCutMapper mCutMapper;

    public MCutQueryService(MCutRepository mCutRepository, MCutMapper mCutMapper) {
        this.mCutRepository = mCutRepository;
        this.mCutMapper = mCutMapper;
    }

    /**
     * Return a {@link List} of {@link MCutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCutDTO> findByCriteria(MCutCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCut> specification = createSpecification(criteria);
        return mCutMapper.toDto(mCutRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCutDTO> findByCriteria(MCutCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCut> specification = createSpecification(criteria);
        return mCutRepository.findAll(specification, page)
            .map(mCutMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCutCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCut> specification = createSpecification(criteria);
        return mCutRepository.count(specification);
    }

    /**
     * Function to convert MCutCriteria to a {@link Specification}.
     */
    private Specification<MCut> createSpecification(MCutCriteria criteria) {
        Specification<MCut> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCut_.id));
            }
            if (criteria.getFpA() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFpA(), MCut_.fpA));
            }
            if (criteria.getFpB() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFpB(), MCut_.fpB));
            }
            if (criteria.getFpC() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFpC(), MCut_.fpC));
            }
            if (criteria.getFpD() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFpD(), MCut_.fpD));
            }
            if (criteria.getFpE() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFpE(), MCut_.fpE));
            }
            if (criteria.getFpF() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFpF(), MCut_.fpF));
            }
            if (criteria.getGkA() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGkA(), MCut_.gkA));
            }
            if (criteria.getGkB() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGkB(), MCut_.gkB));
            }
            if (criteria.getShowEnvironmentalEffect() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShowEnvironmentalEffect(), MCut_.showEnvironmentalEffect));
            }
            if (criteria.getCutType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCutType(), MCut_.cutType));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MCut_.groupId));
            }
            if (criteria.getIsCombiCut() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCombiCut(), MCut_.isCombiCut));
            }
        }
        return specification;
    }
}
