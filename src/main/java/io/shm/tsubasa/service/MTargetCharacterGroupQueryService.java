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

import io.shm.tsubasa.domain.MTargetCharacterGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTargetCharacterGroupRepository;
import io.shm.tsubasa.service.dto.MTargetCharacterGroupCriteria;
import io.shm.tsubasa.service.dto.MTargetCharacterGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetCharacterGroupMapper;

/**
 * Service for executing complex queries for {@link MTargetCharacterGroup} entities in the database.
 * The main input is a {@link MTargetCharacterGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTargetCharacterGroupDTO} or a {@link Page} of {@link MTargetCharacterGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTargetCharacterGroupQueryService extends QueryService<MTargetCharacterGroup> {

    private final Logger log = LoggerFactory.getLogger(MTargetCharacterGroupQueryService.class);

    private final MTargetCharacterGroupRepository mTargetCharacterGroupRepository;

    private final MTargetCharacterGroupMapper mTargetCharacterGroupMapper;

    public MTargetCharacterGroupQueryService(MTargetCharacterGroupRepository mTargetCharacterGroupRepository, MTargetCharacterGroupMapper mTargetCharacterGroupMapper) {
        this.mTargetCharacterGroupRepository = mTargetCharacterGroupRepository;
        this.mTargetCharacterGroupMapper = mTargetCharacterGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MTargetCharacterGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTargetCharacterGroupDTO> findByCriteria(MTargetCharacterGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTargetCharacterGroup> specification = createSpecification(criteria);
        return mTargetCharacterGroupMapper.toDto(mTargetCharacterGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTargetCharacterGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetCharacterGroupDTO> findByCriteria(MTargetCharacterGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTargetCharacterGroup> specification = createSpecification(criteria);
        return mTargetCharacterGroupRepository.findAll(specification, page)
            .map(mTargetCharacterGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTargetCharacterGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTargetCharacterGroup> specification = createSpecification(criteria);
        return mTargetCharacterGroupRepository.count(specification);
    }

    /**
     * Function to convert MTargetCharacterGroupCriteria to a {@link Specification}.
     */
    private Specification<MTargetCharacterGroup> createSpecification(MTargetCharacterGroupCriteria criteria) {
        Specification<MTargetCharacterGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTargetCharacterGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MTargetCharacterGroup_.groupId));
            }
            if (criteria.getCharacterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCharacterId(), MTargetCharacterGroup_.characterId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MTargetCharacterGroup_.id, JoinType.LEFT).get(MCharacter_.id)));
            }
        }
        return specification;
    }
}
