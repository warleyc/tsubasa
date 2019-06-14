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

import io.shm.tsubasa.domain.MTargetFormationGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTargetFormationGroupRepository;
import io.shm.tsubasa.service.dto.MTargetFormationGroupCriteria;
import io.shm.tsubasa.service.dto.MTargetFormationGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetFormationGroupMapper;

/**
 * Service for executing complex queries for {@link MTargetFormationGroup} entities in the database.
 * The main input is a {@link MTargetFormationGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTargetFormationGroupDTO} or a {@link Page} of {@link MTargetFormationGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTargetFormationGroupQueryService extends QueryService<MTargetFormationGroup> {

    private final Logger log = LoggerFactory.getLogger(MTargetFormationGroupQueryService.class);

    private final MTargetFormationGroupRepository mTargetFormationGroupRepository;

    private final MTargetFormationGroupMapper mTargetFormationGroupMapper;

    public MTargetFormationGroupQueryService(MTargetFormationGroupRepository mTargetFormationGroupRepository, MTargetFormationGroupMapper mTargetFormationGroupMapper) {
        this.mTargetFormationGroupRepository = mTargetFormationGroupRepository;
        this.mTargetFormationGroupMapper = mTargetFormationGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MTargetFormationGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTargetFormationGroupDTO> findByCriteria(MTargetFormationGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTargetFormationGroup> specification = createSpecification(criteria);
        return mTargetFormationGroupMapper.toDto(mTargetFormationGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTargetFormationGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetFormationGroupDTO> findByCriteria(MTargetFormationGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTargetFormationGroup> specification = createSpecification(criteria);
        return mTargetFormationGroupRepository.findAll(specification, page)
            .map(mTargetFormationGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTargetFormationGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTargetFormationGroup> specification = createSpecification(criteria);
        return mTargetFormationGroupRepository.count(specification);
    }

    /**
     * Function to convert MTargetFormationGroupCriteria to a {@link Specification}.
     */
    private Specification<MTargetFormationGroup> createSpecification(MTargetFormationGroupCriteria criteria) {
        Specification<MTargetFormationGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTargetFormationGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MTargetFormationGroup_.groupId));
            }
            if (criteria.getFormationId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFormationId(), MTargetFormationGroup_.formationId));
            }
            if (criteria.getMformationId() != null) {
                specification = specification.and(buildSpecification(criteria.getMformationId(),
                    root -> root.join(MTargetFormationGroup_.mformation, JoinType.LEFT).get(MFormation_.id)));
            }
        }
        return specification;
    }
}
