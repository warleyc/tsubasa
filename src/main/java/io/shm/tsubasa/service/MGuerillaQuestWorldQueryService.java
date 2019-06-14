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

import io.shm.tsubasa.domain.MGuerillaQuestWorld;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuerillaQuestWorldRepository;
import io.shm.tsubasa.service.dto.MGuerillaQuestWorldCriteria;
import io.shm.tsubasa.service.dto.MGuerillaQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestWorldMapper;

/**
 * Service for executing complex queries for {@link MGuerillaQuestWorld} entities in the database.
 * The main input is a {@link MGuerillaQuestWorldCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuerillaQuestWorldDTO} or a {@link Page} of {@link MGuerillaQuestWorldDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuerillaQuestWorldQueryService extends QueryService<MGuerillaQuestWorld> {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestWorldQueryService.class);

    private final MGuerillaQuestWorldRepository mGuerillaQuestWorldRepository;

    private final MGuerillaQuestWorldMapper mGuerillaQuestWorldMapper;

    public MGuerillaQuestWorldQueryService(MGuerillaQuestWorldRepository mGuerillaQuestWorldRepository, MGuerillaQuestWorldMapper mGuerillaQuestWorldMapper) {
        this.mGuerillaQuestWorldRepository = mGuerillaQuestWorldRepository;
        this.mGuerillaQuestWorldMapper = mGuerillaQuestWorldMapper;
    }

    /**
     * Return a {@link List} of {@link MGuerillaQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuerillaQuestWorldDTO> findByCriteria(MGuerillaQuestWorldCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuerillaQuestWorld> specification = createSpecification(criteria);
        return mGuerillaQuestWorldMapper.toDto(mGuerillaQuestWorldRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuerillaQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuerillaQuestWorldDTO> findByCriteria(MGuerillaQuestWorldCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuerillaQuestWorld> specification = createSpecification(criteria);
        return mGuerillaQuestWorldRepository.findAll(specification, page)
            .map(mGuerillaQuestWorldMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuerillaQuestWorldCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuerillaQuestWorld> specification = createSpecification(criteria);
        return mGuerillaQuestWorldRepository.count(specification);
    }

    /**
     * Function to convert MGuerillaQuestWorldCriteria to a {@link Specification}.
     */
    private Specification<MGuerillaQuestWorld> createSpecification(MGuerillaQuestWorldCriteria criteria) {
        Specification<MGuerillaQuestWorld> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuerillaQuestWorld_.id));
            }
            if (criteria.getSetId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSetId(), MGuerillaQuestWorld_.setId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MGuerillaQuestWorld_.number));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MGuerillaQuestWorld_.stageUnlockPattern));
            }
            if (criteria.getSpecialRewardContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentType(), MGuerillaQuestWorld_.specialRewardContentType));
            }
            if (criteria.getSpecialRewardContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentId(), MGuerillaQuestWorld_.specialRewardContentId));
            }
            if (criteria.getIsEnableCoop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsEnableCoop(), MGuerillaQuestWorld_.isEnableCoop));
            }
            if (criteria.getMGuerillaQuestStageId() != null) {
                specification = specification.and(buildSpecification(criteria.getMGuerillaQuestStageId(),
                    root -> root.join(MGuerillaQuestWorld_.mGuerillaQuestStages, JoinType.LEFT).get(MGuerillaQuestStage_.id)));
            }
        }
        return specification;
    }
}
