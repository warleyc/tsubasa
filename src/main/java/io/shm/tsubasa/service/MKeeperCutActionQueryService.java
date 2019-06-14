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

import io.shm.tsubasa.domain.MKeeperCutAction;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MKeeperCutActionRepository;
import io.shm.tsubasa.service.dto.MKeeperCutActionCriteria;
import io.shm.tsubasa.service.dto.MKeeperCutActionDTO;
import io.shm.tsubasa.service.mapper.MKeeperCutActionMapper;

/**
 * Service for executing complex queries for {@link MKeeperCutAction} entities in the database.
 * The main input is a {@link MKeeperCutActionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MKeeperCutActionDTO} or a {@link Page} of {@link MKeeperCutActionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MKeeperCutActionQueryService extends QueryService<MKeeperCutAction> {

    private final Logger log = LoggerFactory.getLogger(MKeeperCutActionQueryService.class);

    private final MKeeperCutActionRepository mKeeperCutActionRepository;

    private final MKeeperCutActionMapper mKeeperCutActionMapper;

    public MKeeperCutActionQueryService(MKeeperCutActionRepository mKeeperCutActionRepository, MKeeperCutActionMapper mKeeperCutActionMapper) {
        this.mKeeperCutActionRepository = mKeeperCutActionRepository;
        this.mKeeperCutActionMapper = mKeeperCutActionMapper;
    }

    /**
     * Return a {@link List} of {@link MKeeperCutActionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MKeeperCutActionDTO> findByCriteria(MKeeperCutActionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MKeeperCutAction> specification = createSpecification(criteria);
        return mKeeperCutActionMapper.toDto(mKeeperCutActionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MKeeperCutActionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MKeeperCutActionDTO> findByCriteria(MKeeperCutActionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MKeeperCutAction> specification = createSpecification(criteria);
        return mKeeperCutActionRepository.findAll(specification, page)
            .map(mKeeperCutActionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MKeeperCutActionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MKeeperCutAction> specification = createSpecification(criteria);
        return mKeeperCutActionRepository.count(specification);
    }

    /**
     * Function to convert MKeeperCutActionCriteria to a {@link Specification}.
     */
    private Specification<MKeeperCutAction> createSpecification(MKeeperCutActionCriteria criteria) {
        Specification<MKeeperCutAction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MKeeperCutAction_.id));
            }
            if (criteria.getActionCutId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionCutId(), MKeeperCutAction_.actionCutId));
            }
            if (criteria.getKeeperCutActionType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKeeperCutActionType(), MKeeperCutAction_.keeperCutActionType));
            }
        }
        return specification;
    }
}
