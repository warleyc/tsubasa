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

import io.shm.tsubasa.domain.MTargetPlayableCardGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTargetPlayableCardGroupRepository;
import io.shm.tsubasa.service.dto.MTargetPlayableCardGroupCriteria;
import io.shm.tsubasa.service.dto.MTargetPlayableCardGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetPlayableCardGroupMapper;

/**
 * Service for executing complex queries for {@link MTargetPlayableCardGroup} entities in the database.
 * The main input is a {@link MTargetPlayableCardGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTargetPlayableCardGroupDTO} or a {@link Page} of {@link MTargetPlayableCardGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTargetPlayableCardGroupQueryService extends QueryService<MTargetPlayableCardGroup> {

    private final Logger log = LoggerFactory.getLogger(MTargetPlayableCardGroupQueryService.class);

    private final MTargetPlayableCardGroupRepository mTargetPlayableCardGroupRepository;

    private final MTargetPlayableCardGroupMapper mTargetPlayableCardGroupMapper;

    public MTargetPlayableCardGroupQueryService(MTargetPlayableCardGroupRepository mTargetPlayableCardGroupRepository, MTargetPlayableCardGroupMapper mTargetPlayableCardGroupMapper) {
        this.mTargetPlayableCardGroupRepository = mTargetPlayableCardGroupRepository;
        this.mTargetPlayableCardGroupMapper = mTargetPlayableCardGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MTargetPlayableCardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTargetPlayableCardGroupDTO> findByCriteria(MTargetPlayableCardGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTargetPlayableCardGroup> specification = createSpecification(criteria);
        return mTargetPlayableCardGroupMapper.toDto(mTargetPlayableCardGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTargetPlayableCardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetPlayableCardGroupDTO> findByCriteria(MTargetPlayableCardGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTargetPlayableCardGroup> specification = createSpecification(criteria);
        return mTargetPlayableCardGroupRepository.findAll(specification, page)
            .map(mTargetPlayableCardGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTargetPlayableCardGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTargetPlayableCardGroup> specification = createSpecification(criteria);
        return mTargetPlayableCardGroupRepository.count(specification);
    }

    /**
     * Function to convert MTargetPlayableCardGroupCriteria to a {@link Specification}.
     */
    private Specification<MTargetPlayableCardGroup> createSpecification(MTargetPlayableCardGroupCriteria criteria) {
        Specification<MTargetPlayableCardGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTargetPlayableCardGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MTargetPlayableCardGroup_.groupId));
            }
            if (criteria.getCardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCardId(), MTargetPlayableCardGroup_.cardId));
            }
            if (criteria.getIsShowThumbnail() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsShowThumbnail(), MTargetPlayableCardGroup_.isShowThumbnail));
            }
            if (criteria.getMplayablecardId() != null) {
                specification = specification.and(buildSpecification(criteria.getMplayablecardId(),
                    root -> root.join(MTargetPlayableCardGroup_.mplayablecard, JoinType.LEFT).get(MPlayableCard_.id)));
            }
        }
        return specification;
    }
}
