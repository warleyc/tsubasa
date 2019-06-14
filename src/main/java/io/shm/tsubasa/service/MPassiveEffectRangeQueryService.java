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

import io.shm.tsubasa.domain.MPassiveEffectRange;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPassiveEffectRangeRepository;
import io.shm.tsubasa.service.dto.MPassiveEffectRangeCriteria;
import io.shm.tsubasa.service.dto.MPassiveEffectRangeDTO;
import io.shm.tsubasa.service.mapper.MPassiveEffectRangeMapper;

/**
 * Service for executing complex queries for {@link MPassiveEffectRange} entities in the database.
 * The main input is a {@link MPassiveEffectRangeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPassiveEffectRangeDTO} or a {@link Page} of {@link MPassiveEffectRangeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPassiveEffectRangeQueryService extends QueryService<MPassiveEffectRange> {

    private final Logger log = LoggerFactory.getLogger(MPassiveEffectRangeQueryService.class);

    private final MPassiveEffectRangeRepository mPassiveEffectRangeRepository;

    private final MPassiveEffectRangeMapper mPassiveEffectRangeMapper;

    public MPassiveEffectRangeQueryService(MPassiveEffectRangeRepository mPassiveEffectRangeRepository, MPassiveEffectRangeMapper mPassiveEffectRangeMapper) {
        this.mPassiveEffectRangeRepository = mPassiveEffectRangeRepository;
        this.mPassiveEffectRangeMapper = mPassiveEffectRangeMapper;
    }

    /**
     * Return a {@link List} of {@link MPassiveEffectRangeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPassiveEffectRangeDTO> findByCriteria(MPassiveEffectRangeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPassiveEffectRange> specification = createSpecification(criteria);
        return mPassiveEffectRangeMapper.toDto(mPassiveEffectRangeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPassiveEffectRangeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPassiveEffectRangeDTO> findByCriteria(MPassiveEffectRangeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPassiveEffectRange> specification = createSpecification(criteria);
        return mPassiveEffectRangeRepository.findAll(specification, page)
            .map(mPassiveEffectRangeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPassiveEffectRangeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPassiveEffectRange> specification = createSpecification(criteria);
        return mPassiveEffectRangeRepository.count(specification);
    }

    /**
     * Function to convert MPassiveEffectRangeCriteria to a {@link Specification}.
     */
    private Specification<MPassiveEffectRange> createSpecification(MPassiveEffectRangeCriteria criteria) {
        Specification<MPassiveEffectRange> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPassiveEffectRange_.id));
            }
            if (criteria.getEffectParamType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectParamType(), MPassiveEffectRange_.effectParamType));
            }
            if (criteria.getTargetPositionGk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPositionGk(), MPassiveEffectRange_.targetPositionGk));
            }
            if (criteria.getTargetPositionFw() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPositionFw(), MPassiveEffectRange_.targetPositionFw));
            }
            if (criteria.getTargetPositionOmf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPositionOmf(), MPassiveEffectRange_.targetPositionOmf));
            }
            if (criteria.getTargetPositionDmf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPositionDmf(), MPassiveEffectRange_.targetPositionDmf));
            }
            if (criteria.getTargetPositionDf() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPositionDf(), MPassiveEffectRange_.targetPositionDf));
            }
            if (criteria.getTargetAttribute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetAttribute(), MPassiveEffectRange_.targetAttribute));
            }
            if (criteria.getTargetNationalityGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetNationalityGroupId(), MPassiveEffectRange_.targetNationalityGroupId));
            }
            if (criteria.getTargetTeamGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTeamGroupId(), MPassiveEffectRange_.targetTeamGroupId));
            }
            if (criteria.getTargetPlayableCardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPlayableCardGroupId(), MPassiveEffectRange_.targetPlayableCardGroupId));
            }
            if (criteria.getMFormationId() != null) {
                specification = specification.and(buildSpecification(criteria.getMFormationId(),
                    root -> root.join(MPassiveEffectRange_.mFormations, JoinType.LEFT).get(MFormation_.id)));
            }
            if (criteria.getMMatchOptionId() != null) {
                specification = specification.and(buildSpecification(criteria.getMMatchOptionId(),
                    root -> root.join(MPassiveEffectRange_.mMatchOptions, JoinType.LEFT).get(MMatchOption_.id)));
            }
            if (criteria.getMTeamEffectBaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTeamEffectBaseId(),
                    root -> root.join(MPassiveEffectRange_.mTeamEffectBases, JoinType.LEFT).get(MTeamEffectBase_.id)));
            }
        }
        return specification;
    }
}
