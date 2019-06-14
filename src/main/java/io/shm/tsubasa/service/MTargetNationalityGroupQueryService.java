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

import io.shm.tsubasa.domain.MTargetNationalityGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTargetNationalityGroupRepository;
import io.shm.tsubasa.service.dto.MTargetNationalityGroupCriteria;
import io.shm.tsubasa.service.dto.MTargetNationalityGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetNationalityGroupMapper;

/**
 * Service for executing complex queries for {@link MTargetNationalityGroup} entities in the database.
 * The main input is a {@link MTargetNationalityGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTargetNationalityGroupDTO} or a {@link Page} of {@link MTargetNationalityGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTargetNationalityGroupQueryService extends QueryService<MTargetNationalityGroup> {

    private final Logger log = LoggerFactory.getLogger(MTargetNationalityGroupQueryService.class);

    private final MTargetNationalityGroupRepository mTargetNationalityGroupRepository;

    private final MTargetNationalityGroupMapper mTargetNationalityGroupMapper;

    public MTargetNationalityGroupQueryService(MTargetNationalityGroupRepository mTargetNationalityGroupRepository, MTargetNationalityGroupMapper mTargetNationalityGroupMapper) {
        this.mTargetNationalityGroupRepository = mTargetNationalityGroupRepository;
        this.mTargetNationalityGroupMapper = mTargetNationalityGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MTargetNationalityGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTargetNationalityGroupDTO> findByCriteria(MTargetNationalityGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTargetNationalityGroup> specification = createSpecification(criteria);
        return mTargetNationalityGroupMapper.toDto(mTargetNationalityGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTargetNationalityGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetNationalityGroupDTO> findByCriteria(MTargetNationalityGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTargetNationalityGroup> specification = createSpecification(criteria);
        return mTargetNationalityGroupRepository.findAll(specification, page)
            .map(mTargetNationalityGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTargetNationalityGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTargetNationalityGroup> specification = createSpecification(criteria);
        return mTargetNationalityGroupRepository.count(specification);
    }

    /**
     * Function to convert MTargetNationalityGroupCriteria to a {@link Specification}.
     */
    private Specification<MTargetNationalityGroup> createSpecification(MTargetNationalityGroupCriteria criteria) {
        Specification<MTargetNationalityGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTargetNationalityGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MTargetNationalityGroup_.groupId));
            }
            if (criteria.getNationalityId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNationalityId(), MTargetNationalityGroup_.nationalityId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MTargetNationalityGroup_.id, JoinType.LEFT).get(MNationality_.id)));
            }
        }
        return specification;
    }
}
