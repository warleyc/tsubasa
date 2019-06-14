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

import io.shm.tsubasa.domain.MPowerupActionSkillCost;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPowerupActionSkillCostRepository;
import io.shm.tsubasa.service.dto.MPowerupActionSkillCostCriteria;
import io.shm.tsubasa.service.dto.MPowerupActionSkillCostDTO;
import io.shm.tsubasa.service.mapper.MPowerupActionSkillCostMapper;

/**
 * Service for executing complex queries for {@link MPowerupActionSkillCost} entities in the database.
 * The main input is a {@link MPowerupActionSkillCostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPowerupActionSkillCostDTO} or a {@link Page} of {@link MPowerupActionSkillCostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPowerupActionSkillCostQueryService extends QueryService<MPowerupActionSkillCost> {

    private final Logger log = LoggerFactory.getLogger(MPowerupActionSkillCostQueryService.class);

    private final MPowerupActionSkillCostRepository mPowerupActionSkillCostRepository;

    private final MPowerupActionSkillCostMapper mPowerupActionSkillCostMapper;

    public MPowerupActionSkillCostQueryService(MPowerupActionSkillCostRepository mPowerupActionSkillCostRepository, MPowerupActionSkillCostMapper mPowerupActionSkillCostMapper) {
        this.mPowerupActionSkillCostRepository = mPowerupActionSkillCostRepository;
        this.mPowerupActionSkillCostMapper = mPowerupActionSkillCostMapper;
    }

    /**
     * Return a {@link List} of {@link MPowerupActionSkillCostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPowerupActionSkillCostDTO> findByCriteria(MPowerupActionSkillCostCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPowerupActionSkillCost> specification = createSpecification(criteria);
        return mPowerupActionSkillCostMapper.toDto(mPowerupActionSkillCostRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPowerupActionSkillCostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPowerupActionSkillCostDTO> findByCriteria(MPowerupActionSkillCostCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPowerupActionSkillCost> specification = createSpecification(criteria);
        return mPowerupActionSkillCostRepository.findAll(specification, page)
            .map(mPowerupActionSkillCostMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPowerupActionSkillCostCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPowerupActionSkillCost> specification = createSpecification(criteria);
        return mPowerupActionSkillCostRepository.count(specification);
    }

    /**
     * Function to convert MPowerupActionSkillCostCriteria to a {@link Specification}.
     */
    private Specification<MPowerupActionSkillCost> createSpecification(MPowerupActionSkillCostCriteria criteria) {
        Specification<MPowerupActionSkillCost> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPowerupActionSkillCost_.id));
            }
            if (criteria.getActionRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionRarity(), MPowerupActionSkillCost_.actionRarity));
            }
            if (criteria.getActionLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionLevel(), MPowerupActionSkillCost_.actionLevel));
            }
            if (criteria.getCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCost(), MPowerupActionSkillCost_.cost));
            }
        }
        return specification;
    }
}
