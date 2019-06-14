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

import io.shm.tsubasa.domain.MTeamEffectBase;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTeamEffectBaseRepository;
import io.shm.tsubasa.service.dto.MTeamEffectBaseCriteria;
import io.shm.tsubasa.service.dto.MTeamEffectBaseDTO;
import io.shm.tsubasa.service.mapper.MTeamEffectBaseMapper;

/**
 * Service for executing complex queries for {@link MTeamEffectBase} entities in the database.
 * The main input is a {@link MTeamEffectBaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTeamEffectBaseDTO} or a {@link Page} of {@link MTeamEffectBaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTeamEffectBaseQueryService extends QueryService<MTeamEffectBase> {

    private final Logger log = LoggerFactory.getLogger(MTeamEffectBaseQueryService.class);

    private final MTeamEffectBaseRepository mTeamEffectBaseRepository;

    private final MTeamEffectBaseMapper mTeamEffectBaseMapper;

    public MTeamEffectBaseQueryService(MTeamEffectBaseRepository mTeamEffectBaseRepository, MTeamEffectBaseMapper mTeamEffectBaseMapper) {
        this.mTeamEffectBaseRepository = mTeamEffectBaseRepository;
        this.mTeamEffectBaseMapper = mTeamEffectBaseMapper;
    }

    /**
     * Return a {@link List} of {@link MTeamEffectBaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTeamEffectBaseDTO> findByCriteria(MTeamEffectBaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTeamEffectBase> specification = createSpecification(criteria);
        return mTeamEffectBaseMapper.toDto(mTeamEffectBaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTeamEffectBaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTeamEffectBaseDTO> findByCriteria(MTeamEffectBaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTeamEffectBase> specification = createSpecification(criteria);
        return mTeamEffectBaseRepository.findAll(specification, page)
            .map(mTeamEffectBaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTeamEffectBaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTeamEffectBase> specification = createSpecification(criteria);
        return mTeamEffectBaseRepository.count(specification);
    }

    /**
     * Function to convert MTeamEffectBaseCriteria to a {@link Specification}.
     */
    private Specification<MTeamEffectBase> createSpecification(MTeamEffectBaseCriteria criteria) {
        Specification<MTeamEffectBase> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTeamEffectBase_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MTeamEffectBase_.rarity));
            }
            if (criteria.getEffectValueMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectValueMin(), MTeamEffectBase_.effectValueMin));
            }
            if (criteria.getEffectValueMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectValueMax(), MTeamEffectBase_.effectValueMax));
            }
            if (criteria.getTriggerProbabilityMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerProbabilityMin(), MTeamEffectBase_.triggerProbabilityMin));
            }
            if (criteria.getTriggerProbabilityMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerProbabilityMax(), MTeamEffectBase_.triggerProbabilityMax));
            }
            if (criteria.getEffectId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectId(), MTeamEffectBase_.effectId));
            }
            if (criteria.getEffectValueMin2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectValueMin2(), MTeamEffectBase_.effectValueMin2));
            }
            if (criteria.getEffectValueMax2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectValueMax2(), MTeamEffectBase_.effectValueMax2));
            }
            if (criteria.getTriggerProbabilityMin2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerProbabilityMin2(), MTeamEffectBase_.triggerProbabilityMin2));
            }
            if (criteria.getTriggerProbabilityMax2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerProbabilityMax2(), MTeamEffectBase_.triggerProbabilityMax2));
            }
            if (criteria.getEffectId2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectId2(), MTeamEffectBase_.effectId2));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MTeamEffectBase_.id, JoinType.LEFT).get(MPassiveEffectRange_.id)));
            }
        }
        return specification;
    }
}
