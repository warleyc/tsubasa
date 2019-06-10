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

import io.shm.tsubasa.domain.MCutAction;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCutActionRepository;
import io.shm.tsubasa.service.dto.MCutActionCriteria;
import io.shm.tsubasa.service.dto.MCutActionDTO;
import io.shm.tsubasa.service.mapper.MCutActionMapper;

/**
 * Service for executing complex queries for {@link MCutAction} entities in the database.
 * The main input is a {@link MCutActionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCutActionDTO} or a {@link Page} of {@link MCutActionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCutActionQueryService extends QueryService<MCutAction> {

    private final Logger log = LoggerFactory.getLogger(MCutActionQueryService.class);

    private final MCutActionRepository mCutActionRepository;

    private final MCutActionMapper mCutActionMapper;

    public MCutActionQueryService(MCutActionRepository mCutActionRepository, MCutActionMapper mCutActionMapper) {
        this.mCutActionRepository = mCutActionRepository;
        this.mCutActionMapper = mCutActionMapper;
    }

    /**
     * Return a {@link List} of {@link MCutActionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCutActionDTO> findByCriteria(MCutActionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCutAction> specification = createSpecification(criteria);
        return mCutActionMapper.toDto(mCutActionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCutActionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCutActionDTO> findByCriteria(MCutActionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCutAction> specification = createSpecification(criteria);
        return mCutActionRepository.findAll(specification, page)
            .map(mCutActionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCutActionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCutAction> specification = createSpecification(criteria);
        return mCutActionRepository.count(specification);
    }

    /**
     * Function to convert MCutActionCriteria to a {@link Specification}.
     */
    private Specification<MCutAction> createSpecification(MCutActionCriteria criteria) {
        Specification<MCutAction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCutAction_.id));
            }
            if (criteria.getActionCutId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionCutId(), MCutAction_.actionCutId));
            }
            if (criteria.getCutActionType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCutActionType(), MCutAction_.cutActionType));
            }
        }
        return specification;
    }
}
