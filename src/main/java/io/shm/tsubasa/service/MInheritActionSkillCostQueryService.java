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

import io.shm.tsubasa.domain.MInheritActionSkillCost;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MInheritActionSkillCostRepository;
import io.shm.tsubasa.service.dto.MInheritActionSkillCostCriteria;
import io.shm.tsubasa.service.dto.MInheritActionSkillCostDTO;
import io.shm.tsubasa.service.mapper.MInheritActionSkillCostMapper;

/**
 * Service for executing complex queries for {@link MInheritActionSkillCost} entities in the database.
 * The main input is a {@link MInheritActionSkillCostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MInheritActionSkillCostDTO} or a {@link Page} of {@link MInheritActionSkillCostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MInheritActionSkillCostQueryService extends QueryService<MInheritActionSkillCost> {

    private final Logger log = LoggerFactory.getLogger(MInheritActionSkillCostQueryService.class);

    private final MInheritActionSkillCostRepository mInheritActionSkillCostRepository;

    private final MInheritActionSkillCostMapper mInheritActionSkillCostMapper;

    public MInheritActionSkillCostQueryService(MInheritActionSkillCostRepository mInheritActionSkillCostRepository, MInheritActionSkillCostMapper mInheritActionSkillCostMapper) {
        this.mInheritActionSkillCostRepository = mInheritActionSkillCostRepository;
        this.mInheritActionSkillCostMapper = mInheritActionSkillCostMapper;
    }

    /**
     * Return a {@link List} of {@link MInheritActionSkillCostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MInheritActionSkillCostDTO> findByCriteria(MInheritActionSkillCostCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MInheritActionSkillCost> specification = createSpecification(criteria);
        return mInheritActionSkillCostMapper.toDto(mInheritActionSkillCostRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MInheritActionSkillCostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MInheritActionSkillCostDTO> findByCriteria(MInheritActionSkillCostCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MInheritActionSkillCost> specification = createSpecification(criteria);
        return mInheritActionSkillCostRepository.findAll(specification, page)
            .map(mInheritActionSkillCostMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MInheritActionSkillCostCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MInheritActionSkillCost> specification = createSpecification(criteria);
        return mInheritActionSkillCostRepository.count(specification);
    }

    /**
     * Function to convert MInheritActionSkillCostCriteria to a {@link Specification}.
     */
    private Specification<MInheritActionSkillCost> createSpecification(MInheritActionSkillCostCriteria criteria) {
        Specification<MInheritActionSkillCost> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MInheritActionSkillCost_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MInheritActionSkillCost_.rarity));
            }
            if (criteria.getLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevel(), MInheritActionSkillCost_.level));
            }
            if (criteria.getCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCost(), MInheritActionSkillCost_.cost));
            }
        }
        return specification;
    }
}
