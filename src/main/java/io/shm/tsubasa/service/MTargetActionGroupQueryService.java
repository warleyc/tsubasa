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

import io.shm.tsubasa.domain.MTargetActionGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTargetActionGroupRepository;
import io.shm.tsubasa.service.dto.MTargetActionGroupCriteria;
import io.shm.tsubasa.service.dto.MTargetActionGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetActionGroupMapper;

/**
 * Service for executing complex queries for {@link MTargetActionGroup} entities in the database.
 * The main input is a {@link MTargetActionGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTargetActionGroupDTO} or a {@link Page} of {@link MTargetActionGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTargetActionGroupQueryService extends QueryService<MTargetActionGroup> {

    private final Logger log = LoggerFactory.getLogger(MTargetActionGroupQueryService.class);

    private final MTargetActionGroupRepository mTargetActionGroupRepository;

    private final MTargetActionGroupMapper mTargetActionGroupMapper;

    public MTargetActionGroupQueryService(MTargetActionGroupRepository mTargetActionGroupRepository, MTargetActionGroupMapper mTargetActionGroupMapper) {
        this.mTargetActionGroupRepository = mTargetActionGroupRepository;
        this.mTargetActionGroupMapper = mTargetActionGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MTargetActionGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTargetActionGroupDTO> findByCriteria(MTargetActionGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTargetActionGroup> specification = createSpecification(criteria);
        return mTargetActionGroupMapper.toDto(mTargetActionGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTargetActionGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetActionGroupDTO> findByCriteria(MTargetActionGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTargetActionGroup> specification = createSpecification(criteria);
        return mTargetActionGroupRepository.findAll(specification, page)
            .map(mTargetActionGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTargetActionGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTargetActionGroup> specification = createSpecification(criteria);
        return mTargetActionGroupRepository.count(specification);
    }

    /**
     * Function to convert MTargetActionGroupCriteria to a {@link Specification}.
     */
    private Specification<MTargetActionGroup> createSpecification(MTargetActionGroupCriteria criteria) {
        Specification<MTargetActionGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTargetActionGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MTargetActionGroup_.groupId));
            }
            if (criteria.getActionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionId(), MTargetActionGroup_.actionId));
            }
            if (criteria.getMactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getMactionId(),
                    root -> root.join(MTargetActionGroup_.maction, JoinType.LEFT).get(MAction_.id)));
            }
        }
        return specification;
    }
}
