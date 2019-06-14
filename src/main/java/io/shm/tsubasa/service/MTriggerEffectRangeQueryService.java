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

import io.shm.tsubasa.domain.MTriggerEffectRange;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTriggerEffectRangeRepository;
import io.shm.tsubasa.service.dto.MTriggerEffectRangeCriteria;
import io.shm.tsubasa.service.dto.MTriggerEffectRangeDTO;
import io.shm.tsubasa.service.mapper.MTriggerEffectRangeMapper;

/**
 * Service for executing complex queries for {@link MTriggerEffectRange} entities in the database.
 * The main input is a {@link MTriggerEffectRangeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTriggerEffectRangeDTO} or a {@link Page} of {@link MTriggerEffectRangeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTriggerEffectRangeQueryService extends QueryService<MTriggerEffectRange> {

    private final Logger log = LoggerFactory.getLogger(MTriggerEffectRangeQueryService.class);

    private final MTriggerEffectRangeRepository mTriggerEffectRangeRepository;

    private final MTriggerEffectRangeMapper mTriggerEffectRangeMapper;

    public MTriggerEffectRangeQueryService(MTriggerEffectRangeRepository mTriggerEffectRangeRepository, MTriggerEffectRangeMapper mTriggerEffectRangeMapper) {
        this.mTriggerEffectRangeRepository = mTriggerEffectRangeRepository;
        this.mTriggerEffectRangeMapper = mTriggerEffectRangeMapper;
    }

    /**
     * Return a {@link List} of {@link MTriggerEffectRangeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTriggerEffectRangeDTO> findByCriteria(MTriggerEffectRangeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTriggerEffectRange> specification = createSpecification(criteria);
        return mTriggerEffectRangeMapper.toDto(mTriggerEffectRangeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTriggerEffectRangeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTriggerEffectRangeDTO> findByCriteria(MTriggerEffectRangeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTriggerEffectRange> specification = createSpecification(criteria);
        return mTriggerEffectRangeRepository.findAll(specification, page)
            .map(mTriggerEffectRangeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTriggerEffectRangeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTriggerEffectRange> specification = createSpecification(criteria);
        return mTriggerEffectRangeRepository.count(specification);
    }

    /**
     * Function to convert MTriggerEffectRangeCriteria to a {@link Specification}.
     */
    private Specification<MTriggerEffectRange> createSpecification(MTriggerEffectRangeCriteria criteria) {
        Specification<MTriggerEffectRange> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTriggerEffectRange_.id));
            }
            if (criteria.getEffectType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectType(), MTriggerEffectRange_.effectType));
            }
            if (criteria.getTargetPositionGk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPositionGk(), MTriggerEffectRange_.targetPositionGk));
            }
            if (criteria.getTargetPositionFw() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPositionFw(), MTriggerEffectRange_.targetPositionFw));
            }
            if (criteria.getTargetPositionOmf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPositionOmf(), MTriggerEffectRange_.targetPositionOmf));
            }
            if (criteria.getTargetPositionDmf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPositionDmf(), MTriggerEffectRange_.targetPositionDmf));
            }
            if (criteria.getTargetPositionDf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPositionDf(), MTriggerEffectRange_.targetPositionDf));
            }
            if (criteria.getTargetAttribute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetAttribute(), MTriggerEffectRange_.targetAttribute));
            }
            if (criteria.getTargetNationalityGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetNationalityGroupId(), MTriggerEffectRange_.targetNationalityGroupId));
            }
            if (criteria.getTargetTeamGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTeamGroupId(), MTriggerEffectRange_.targetTeamGroupId));
            }
            if (criteria.getTargetCharacterGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharacterGroupId(), MTriggerEffectRange_.targetCharacterGroupId));
            }
            if (criteria.getTargetFormationGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetFormationGroupId(), MTriggerEffectRange_.targetFormationGroupId));
            }
            if (criteria.getTargetActionCommand() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetActionCommand(), MTriggerEffectRange_.targetActionCommand));
            }
            if (criteria.getMTriggerEffectBaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTriggerEffectBaseId(),
                    root -> root.join(MTriggerEffectRange_.mTriggerEffectBases, JoinType.LEFT).get(MTriggerEffectBase_.id)));
            }
        }
        return specification;
    }
}
