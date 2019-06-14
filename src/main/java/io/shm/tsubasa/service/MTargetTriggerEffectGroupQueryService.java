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

import io.shm.tsubasa.domain.MTargetTriggerEffectGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTargetTriggerEffectGroupRepository;
import io.shm.tsubasa.service.dto.MTargetTriggerEffectGroupCriteria;
import io.shm.tsubasa.service.dto.MTargetTriggerEffectGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetTriggerEffectGroupMapper;

/**
 * Service for executing complex queries for {@link MTargetTriggerEffectGroup} entities in the database.
 * The main input is a {@link MTargetTriggerEffectGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTargetTriggerEffectGroupDTO} or a {@link Page} of {@link MTargetTriggerEffectGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTargetTriggerEffectGroupQueryService extends QueryService<MTargetTriggerEffectGroup> {

    private final Logger log = LoggerFactory.getLogger(MTargetTriggerEffectGroupQueryService.class);

    private final MTargetTriggerEffectGroupRepository mTargetTriggerEffectGroupRepository;

    private final MTargetTriggerEffectGroupMapper mTargetTriggerEffectGroupMapper;

    public MTargetTriggerEffectGroupQueryService(MTargetTriggerEffectGroupRepository mTargetTriggerEffectGroupRepository, MTargetTriggerEffectGroupMapper mTargetTriggerEffectGroupMapper) {
        this.mTargetTriggerEffectGroupRepository = mTargetTriggerEffectGroupRepository;
        this.mTargetTriggerEffectGroupMapper = mTargetTriggerEffectGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MTargetTriggerEffectGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTargetTriggerEffectGroupDTO> findByCriteria(MTargetTriggerEffectGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTargetTriggerEffectGroup> specification = createSpecification(criteria);
        return mTargetTriggerEffectGroupMapper.toDto(mTargetTriggerEffectGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTargetTriggerEffectGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetTriggerEffectGroupDTO> findByCriteria(MTargetTriggerEffectGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTargetTriggerEffectGroup> specification = createSpecification(criteria);
        return mTargetTriggerEffectGroupRepository.findAll(specification, page)
            .map(mTargetTriggerEffectGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTargetTriggerEffectGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTargetTriggerEffectGroup> specification = createSpecification(criteria);
        return mTargetTriggerEffectGroupRepository.count(specification);
    }

    /**
     * Function to convert MTargetTriggerEffectGroupCriteria to a {@link Specification}.
     */
    private Specification<MTargetTriggerEffectGroup> createSpecification(MTargetTriggerEffectGroupCriteria criteria) {
        Specification<MTargetTriggerEffectGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTargetTriggerEffectGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MTargetTriggerEffectGroup_.groupId));
            }
            if (criteria.getTriggerEffectId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerEffectId(), MTargetTriggerEffectGroup_.triggerEffectId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MTargetTriggerEffectGroup_.id, JoinType.LEFT).get(MTriggerEffectBase_.id)));
            }
        }
        return specification;
    }
}
