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

import io.shm.tsubasa.domain.MTargetTeamGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTargetTeamGroupRepository;
import io.shm.tsubasa.service.dto.MTargetTeamGroupCriteria;
import io.shm.tsubasa.service.dto.MTargetTeamGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetTeamGroupMapper;

/**
 * Service for executing complex queries for {@link MTargetTeamGroup} entities in the database.
 * The main input is a {@link MTargetTeamGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTargetTeamGroupDTO} or a {@link Page} of {@link MTargetTeamGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTargetTeamGroupQueryService extends QueryService<MTargetTeamGroup> {

    private final Logger log = LoggerFactory.getLogger(MTargetTeamGroupQueryService.class);

    private final MTargetTeamGroupRepository mTargetTeamGroupRepository;

    private final MTargetTeamGroupMapper mTargetTeamGroupMapper;

    public MTargetTeamGroupQueryService(MTargetTeamGroupRepository mTargetTeamGroupRepository, MTargetTeamGroupMapper mTargetTeamGroupMapper) {
        this.mTargetTeamGroupRepository = mTargetTeamGroupRepository;
        this.mTargetTeamGroupMapper = mTargetTeamGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MTargetTeamGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTargetTeamGroupDTO> findByCriteria(MTargetTeamGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTargetTeamGroup> specification = createSpecification(criteria);
        return mTargetTeamGroupMapper.toDto(mTargetTeamGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTargetTeamGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetTeamGroupDTO> findByCriteria(MTargetTeamGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTargetTeamGroup> specification = createSpecification(criteria);
        return mTargetTeamGroupRepository.findAll(specification, page)
            .map(mTargetTeamGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTargetTeamGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTargetTeamGroup> specification = createSpecification(criteria);
        return mTargetTeamGroupRepository.count(specification);
    }

    /**
     * Function to convert MTargetTeamGroupCriteria to a {@link Specification}.
     */
    private Specification<MTargetTeamGroup> createSpecification(MTargetTeamGroupCriteria criteria) {
        Specification<MTargetTeamGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTargetTeamGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MTargetTeamGroup_.groupId));
            }
            if (criteria.getTeamId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamId(), MTargetTeamGroup_.teamId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MTargetTeamGroup_.id, JoinType.LEFT).get(MTeam_.id)));
            }
        }
        return specification;
    }
}
