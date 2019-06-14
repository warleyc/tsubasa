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

import io.shm.tsubasa.domain.MTriggerEffectBase;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTriggerEffectBaseRepository;
import io.shm.tsubasa.service.dto.MTriggerEffectBaseCriteria;
import io.shm.tsubasa.service.dto.MTriggerEffectBaseDTO;
import io.shm.tsubasa.service.mapper.MTriggerEffectBaseMapper;

/**
 * Service for executing complex queries for {@link MTriggerEffectBase} entities in the database.
 * The main input is a {@link MTriggerEffectBaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTriggerEffectBaseDTO} or a {@link Page} of {@link MTriggerEffectBaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTriggerEffectBaseQueryService extends QueryService<MTriggerEffectBase> {

    private final Logger log = LoggerFactory.getLogger(MTriggerEffectBaseQueryService.class);

    private final MTriggerEffectBaseRepository mTriggerEffectBaseRepository;

    private final MTriggerEffectBaseMapper mTriggerEffectBaseMapper;

    public MTriggerEffectBaseQueryService(MTriggerEffectBaseRepository mTriggerEffectBaseRepository, MTriggerEffectBaseMapper mTriggerEffectBaseMapper) {
        this.mTriggerEffectBaseRepository = mTriggerEffectBaseRepository;
        this.mTriggerEffectBaseMapper = mTriggerEffectBaseMapper;
    }

    /**
     * Return a {@link List} of {@link MTriggerEffectBaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTriggerEffectBaseDTO> findByCriteria(MTriggerEffectBaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTriggerEffectBase> specification = createSpecification(criteria);
        return mTriggerEffectBaseMapper.toDto(mTriggerEffectBaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTriggerEffectBaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTriggerEffectBaseDTO> findByCriteria(MTriggerEffectBaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTriggerEffectBase> specification = createSpecification(criteria);
        return mTriggerEffectBaseRepository.findAll(specification, page)
            .map(mTriggerEffectBaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTriggerEffectBaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTriggerEffectBase> specification = createSpecification(criteria);
        return mTriggerEffectBaseRepository.count(specification);
    }

    /**
     * Function to convert MTriggerEffectBaseCriteria to a {@link Specification}.
     */
    private Specification<MTriggerEffectBase> createSpecification(MTriggerEffectBaseCriteria criteria) {
        Specification<MTriggerEffectBase> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTriggerEffectBase_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MTriggerEffectBase_.rarity));
            }
            if (criteria.getEffectValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectValue(), MTriggerEffectBase_.effectValue));
            }
            if (criteria.getTriggerProbability() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerProbability(), MTriggerEffectBase_.triggerProbability));
            }
            if (criteria.getTargetCardParameter() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCardParameter(), MTriggerEffectBase_.targetCardParameter));
            }
            if (criteria.getEffectId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectId(), MTriggerEffectBase_.effectId));
            }
            if (criteria.getMtriggereffectrangeId() != null) {
                specification = specification.and(buildSpecification(criteria.getMtriggereffectrangeId(),
                    root -> root.join(MTriggerEffectBase_.mtriggereffectrange, JoinType.LEFT).get(MTriggerEffectRange_.id)));
            }
            if (criteria.getMTargetTriggerEffectGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTargetTriggerEffectGroupId(),
                    root -> root.join(MTriggerEffectBase_.mTargetTriggerEffectGroups, JoinType.LEFT).get(MTargetTriggerEffectGroup_.id)));
            }
        }
        return specification;
    }
}
