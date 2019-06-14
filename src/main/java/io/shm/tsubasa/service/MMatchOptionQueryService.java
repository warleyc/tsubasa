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

import io.shm.tsubasa.domain.MMatchOption;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMatchOptionRepository;
import io.shm.tsubasa.service.dto.MMatchOptionCriteria;
import io.shm.tsubasa.service.dto.MMatchOptionDTO;
import io.shm.tsubasa.service.mapper.MMatchOptionMapper;

/**
 * Service for executing complex queries for {@link MMatchOption} entities in the database.
 * The main input is a {@link MMatchOptionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMatchOptionDTO} or a {@link Page} of {@link MMatchOptionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMatchOptionQueryService extends QueryService<MMatchOption> {

    private final Logger log = LoggerFactory.getLogger(MMatchOptionQueryService.class);

    private final MMatchOptionRepository mMatchOptionRepository;

    private final MMatchOptionMapper mMatchOptionMapper;

    public MMatchOptionQueryService(MMatchOptionRepository mMatchOptionRepository, MMatchOptionMapper mMatchOptionMapper) {
        this.mMatchOptionRepository = mMatchOptionRepository;
        this.mMatchOptionMapper = mMatchOptionMapper;
    }

    /**
     * Return a {@link List} of {@link MMatchOptionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMatchOptionDTO> findByCriteria(MMatchOptionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMatchOption> specification = createSpecification(criteria);
        return mMatchOptionMapper.toDto(mMatchOptionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMatchOptionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMatchOptionDTO> findByCriteria(MMatchOptionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMatchOption> specification = createSpecification(criteria);
        return mMatchOptionRepository.findAll(specification, page)
            .map(mMatchOptionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMatchOptionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMatchOption> specification = createSpecification(criteria);
        return mMatchOptionRepository.count(specification);
    }

    /**
     * Function to convert MMatchOptionCriteria to a {@link Specification}.
     */
    private Specification<MMatchOption> createSpecification(MMatchOptionCriteria criteria) {
        Specification<MMatchOption> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMatchOption_.id));
            }
            if (criteria.getPassiveEffectId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPassiveEffectId(), MMatchOption_.passiveEffectId));
            }
            if (criteria.getPassiveEffectValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPassiveEffectValue(), MMatchOption_.passiveEffectValue));
            }
            if (criteria.getActivateFullPowerType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActivateFullPowerType(), MMatchOption_.activateFullPowerType));
            }
            if (criteria.getAttributeMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAttributeMagnification(), MMatchOption_.attributeMagnification));
            }
            if (criteria.getUseStaminaMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUseStaminaMagnification(), MMatchOption_.useStaminaMagnification));
            }
            if (criteria.getIsIgnoreTeamSkill() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsIgnoreTeamSkill(), MMatchOption_.isIgnoreTeamSkill));
            }
            if (criteria.getStaminaInfinityType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStaminaInfinityType(), MMatchOption_.staminaInfinityType));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MMatchOption_.id, JoinType.LEFT).get(MPassiveEffectRange_.id)));
            }
            if (criteria.getMLeagueRegulationId() != null) {
                specification = specification.and(buildSpecification(criteria.getMLeagueRegulationId(),
                    root -> root.join(MMatchOption_.mLeagueRegulations, JoinType.LEFT).get(MLeagueRegulation_.id)));
            }
            if (criteria.getMPvpRegulationId() != null) {
                specification = specification.and(buildSpecification(criteria.getMPvpRegulationId(),
                    root -> root.join(MMatchOption_.mPvpRegulations, JoinType.LEFT).get(MPvpRegulation_.id)));
            }
        }
        return specification;
    }
}
