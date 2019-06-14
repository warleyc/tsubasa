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

import io.shm.tsubasa.domain.MTargetActionTypeGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTargetActionTypeGroupRepository;
import io.shm.tsubasa.service.dto.MTargetActionTypeGroupCriteria;
import io.shm.tsubasa.service.dto.MTargetActionTypeGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetActionTypeGroupMapper;

/**
 * Service for executing complex queries for {@link MTargetActionTypeGroup} entities in the database.
 * The main input is a {@link MTargetActionTypeGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTargetActionTypeGroupDTO} or a {@link Page} of {@link MTargetActionTypeGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTargetActionTypeGroupQueryService extends QueryService<MTargetActionTypeGroup> {

    private final Logger log = LoggerFactory.getLogger(MTargetActionTypeGroupQueryService.class);

    private final MTargetActionTypeGroupRepository mTargetActionTypeGroupRepository;

    private final MTargetActionTypeGroupMapper mTargetActionTypeGroupMapper;

    public MTargetActionTypeGroupQueryService(MTargetActionTypeGroupRepository mTargetActionTypeGroupRepository, MTargetActionTypeGroupMapper mTargetActionTypeGroupMapper) {
        this.mTargetActionTypeGroupRepository = mTargetActionTypeGroupRepository;
        this.mTargetActionTypeGroupMapper = mTargetActionTypeGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MTargetActionTypeGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTargetActionTypeGroupDTO> findByCriteria(MTargetActionTypeGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTargetActionTypeGroup> specification = createSpecification(criteria);
        return mTargetActionTypeGroupMapper.toDto(mTargetActionTypeGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTargetActionTypeGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetActionTypeGroupDTO> findByCriteria(MTargetActionTypeGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTargetActionTypeGroup> specification = createSpecification(criteria);
        return mTargetActionTypeGroupRepository.findAll(specification, page)
            .map(mTargetActionTypeGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTargetActionTypeGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTargetActionTypeGroup> specification = createSpecification(criteria);
        return mTargetActionTypeGroupRepository.count(specification);
    }

    /**
     * Function to convert MTargetActionTypeGroupCriteria to a {@link Specification}.
     */
    private Specification<MTargetActionTypeGroup> createSpecification(MTargetActionTypeGroupCriteria criteria) {
        Specification<MTargetActionTypeGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTargetActionTypeGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MTargetActionTypeGroup_.groupId));
            }
            if (criteria.getCommandType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCommandType(), MTargetActionTypeGroup_.commandType));
            }
        }
        return specification;
    }
}
