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

import io.shm.tsubasa.domain.MAction;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MActionRepository;
import io.shm.tsubasa.service.dto.MActionCriteria;
import io.shm.tsubasa.service.dto.MActionDTO;
import io.shm.tsubasa.service.mapper.MActionMapper;

/**
 * Service for executing complex queries for {@link MAction} entities in the database.
 * The main input is a {@link MActionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MActionDTO} or a {@link Page} of {@link MActionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MActionQueryService extends QueryService<MAction> {

    private final Logger log = LoggerFactory.getLogger(MActionQueryService.class);

    private final MActionRepository mActionRepository;

    private final MActionMapper mActionMapper;

    public MActionQueryService(MActionRepository mActionRepository, MActionMapper mActionMapper) {
        this.mActionRepository = mActionRepository;
        this.mActionMapper = mActionMapper;
    }

    /**
     * Return a {@link List} of {@link MActionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MActionDTO> findByCriteria(MActionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MAction> specification = createSpecification(criteria);
        return mActionMapper.toDto(mActionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MActionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionDTO> findByCriteria(MActionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MAction> specification = createSpecification(criteria);
        return mActionRepository.findAll(specification, page)
            .map(mActionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MActionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MAction> specification = createSpecification(criteria);
        return mActionRepository.count(specification);
    }

    /**
     * Function to convert MActionCriteria to a {@link Specification}.
     */
    private Specification<MAction> createSpecification(MActionCriteria criteria) {
        Specification<MAction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MAction_.id));
            }
            if (criteria.getInitialCommand() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitialCommand(), MAction_.initialCommand));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MAction_.rarity));
            }
            if (criteria.getCommandType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCommandType(), MAction_.commandType));
            }
            if (criteria.getBallConditionGround() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBallConditionGround(), MAction_.ballConditionGround));
            }
            if (criteria.getBallConditionLow() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBallConditionLow(), MAction_.ballConditionLow));
            }
            if (criteria.getBallConditionHigh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBallConditionHigh(), MAction_.ballConditionHigh));
            }
            if (criteria.getStaminaLvMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStaminaLvMin(), MAction_.staminaLvMin));
            }
            if (criteria.getStaminaLvMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStaminaLvMax(), MAction_.staminaLvMax));
            }
            if (criteria.getPowerLvMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPowerLvMin(), MAction_.powerLvMin));
            }
            if (criteria.getPowerLvMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPowerLvMax(), MAction_.powerLvMax));
            }
            if (criteria.getBlowOffCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBlowOffCount(), MAction_.blowOffCount));
            }
            if (criteria.getmShootId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getmShootId(), MAction_.mShootId));
            }
            if (criteria.getFoulRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFoulRate(), MAction_.foulRate));
            }
            if (criteria.getDistanceDecayType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDistanceDecayType(), MAction_.distanceDecayType));
            }
            if (criteria.getActivateCharacterCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActivateCharacterCount(), MAction_.activateCharacterCount));
            }
            if (criteria.getActionCutId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionCutId(), MAction_.actionCutId));
            }
            if (criteria.getPowerupGroup() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPowerupGroup(), MAction_.powerupGroup));
            }
            if (criteria.getMTargetActionGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTargetActionGroupId(),
                    root -> root.join(MAction_.mTargetActionGroups, JoinType.LEFT).get(MTargetActionGroup_.id)));
            }
        }
        return specification;
    }
}
