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

import io.shm.tsubasa.domain.MTargetRarityGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTargetRarityGroupRepository;
import io.shm.tsubasa.service.dto.MTargetRarityGroupCriteria;
import io.shm.tsubasa.service.dto.MTargetRarityGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetRarityGroupMapper;

/**
 * Service for executing complex queries for {@link MTargetRarityGroup} entities in the database.
 * The main input is a {@link MTargetRarityGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTargetRarityGroupDTO} or a {@link Page} of {@link MTargetRarityGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTargetRarityGroupQueryService extends QueryService<MTargetRarityGroup> {

    private final Logger log = LoggerFactory.getLogger(MTargetRarityGroupQueryService.class);

    private final MTargetRarityGroupRepository mTargetRarityGroupRepository;

    private final MTargetRarityGroupMapper mTargetRarityGroupMapper;

    public MTargetRarityGroupQueryService(MTargetRarityGroupRepository mTargetRarityGroupRepository, MTargetRarityGroupMapper mTargetRarityGroupMapper) {
        this.mTargetRarityGroupRepository = mTargetRarityGroupRepository;
        this.mTargetRarityGroupMapper = mTargetRarityGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MTargetRarityGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTargetRarityGroupDTO> findByCriteria(MTargetRarityGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTargetRarityGroup> specification = createSpecification(criteria);
        return mTargetRarityGroupMapper.toDto(mTargetRarityGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTargetRarityGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetRarityGroupDTO> findByCriteria(MTargetRarityGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTargetRarityGroup> specification = createSpecification(criteria);
        return mTargetRarityGroupRepository.findAll(specification, page)
            .map(mTargetRarityGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTargetRarityGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTargetRarityGroup> specification = createSpecification(criteria);
        return mTargetRarityGroupRepository.count(specification);
    }

    /**
     * Function to convert MTargetRarityGroupCriteria to a {@link Specification}.
     */
    private Specification<MTargetRarityGroup> createSpecification(MTargetRarityGroupCriteria criteria) {
        Specification<MTargetRarityGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTargetRarityGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MTargetRarityGroup_.groupId));
            }
            if (criteria.getCardRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCardRarity(), MTargetRarityGroup_.cardRarity));
            }
        }
        return specification;
    }
}
