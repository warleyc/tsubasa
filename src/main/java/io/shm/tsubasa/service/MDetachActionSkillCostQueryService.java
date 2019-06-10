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

import io.shm.tsubasa.domain.MDetachActionSkillCost;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDetachActionSkillCostRepository;
import io.shm.tsubasa.service.dto.MDetachActionSkillCostCriteria;
import io.shm.tsubasa.service.dto.MDetachActionSkillCostDTO;
import io.shm.tsubasa.service.mapper.MDetachActionSkillCostMapper;

/**
 * Service for executing complex queries for {@link MDetachActionSkillCost} entities in the database.
 * The main input is a {@link MDetachActionSkillCostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDetachActionSkillCostDTO} or a {@link Page} of {@link MDetachActionSkillCostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDetachActionSkillCostQueryService extends QueryService<MDetachActionSkillCost> {

    private final Logger log = LoggerFactory.getLogger(MDetachActionSkillCostQueryService.class);

    private final MDetachActionSkillCostRepository mDetachActionSkillCostRepository;

    private final MDetachActionSkillCostMapper mDetachActionSkillCostMapper;

    public MDetachActionSkillCostQueryService(MDetachActionSkillCostRepository mDetachActionSkillCostRepository, MDetachActionSkillCostMapper mDetachActionSkillCostMapper) {
        this.mDetachActionSkillCostRepository = mDetachActionSkillCostRepository;
        this.mDetachActionSkillCostMapper = mDetachActionSkillCostMapper;
    }

    /**
     * Return a {@link List} of {@link MDetachActionSkillCostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDetachActionSkillCostDTO> findByCriteria(MDetachActionSkillCostCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDetachActionSkillCost> specification = createSpecification(criteria);
        return mDetachActionSkillCostMapper.toDto(mDetachActionSkillCostRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDetachActionSkillCostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDetachActionSkillCostDTO> findByCriteria(MDetachActionSkillCostCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDetachActionSkillCost> specification = createSpecification(criteria);
        return mDetachActionSkillCostRepository.findAll(specification, page)
            .map(mDetachActionSkillCostMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDetachActionSkillCostCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDetachActionSkillCost> specification = createSpecification(criteria);
        return mDetachActionSkillCostRepository.count(specification);
    }

    /**
     * Function to convert MDetachActionSkillCostCriteria to a {@link Specification}.
     */
    private Specification<MDetachActionSkillCost> createSpecification(MDetachActionSkillCostCriteria criteria) {
        Specification<MDetachActionSkillCost> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDetachActionSkillCost_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MDetachActionSkillCost_.rarity));
            }
            if (criteria.getCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCost(), MDetachActionSkillCost_.cost));
            }
        }
        return specification;
    }
}
