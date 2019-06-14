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

import io.shm.tsubasa.domain.MTrainingCost;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTrainingCostRepository;
import io.shm.tsubasa.service.dto.MTrainingCostCriteria;
import io.shm.tsubasa.service.dto.MTrainingCostDTO;
import io.shm.tsubasa.service.mapper.MTrainingCostMapper;

/**
 * Service for executing complex queries for {@link MTrainingCost} entities in the database.
 * The main input is a {@link MTrainingCostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTrainingCostDTO} or a {@link Page} of {@link MTrainingCostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTrainingCostQueryService extends QueryService<MTrainingCost> {

    private final Logger log = LoggerFactory.getLogger(MTrainingCostQueryService.class);

    private final MTrainingCostRepository mTrainingCostRepository;

    private final MTrainingCostMapper mTrainingCostMapper;

    public MTrainingCostQueryService(MTrainingCostRepository mTrainingCostRepository, MTrainingCostMapper mTrainingCostMapper) {
        this.mTrainingCostRepository = mTrainingCostRepository;
        this.mTrainingCostMapper = mTrainingCostMapper;
    }

    /**
     * Return a {@link List} of {@link MTrainingCostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTrainingCostDTO> findByCriteria(MTrainingCostCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTrainingCost> specification = createSpecification(criteria);
        return mTrainingCostMapper.toDto(mTrainingCostRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTrainingCostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTrainingCostDTO> findByCriteria(MTrainingCostCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTrainingCost> specification = createSpecification(criteria);
        return mTrainingCostRepository.findAll(specification, page)
            .map(mTrainingCostMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTrainingCostCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTrainingCost> specification = createSpecification(criteria);
        return mTrainingCostRepository.count(specification);
    }

    /**
     * Function to convert MTrainingCostCriteria to a {@link Specification}.
     */
    private Specification<MTrainingCost> createSpecification(MTrainingCostCriteria criteria) {
        Specification<MTrainingCost> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTrainingCost_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MTrainingCost_.rarity));
            }
            if (criteria.getLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevel(), MTrainingCost_.level));
            }
            if (criteria.getCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCost(), MTrainingCost_.cost));
            }
        }
        return specification;
    }
}
