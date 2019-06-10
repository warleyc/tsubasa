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

import io.shm.tsubasa.domain.MAdventQuestWorld;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MAdventQuestWorldRepository;
import io.shm.tsubasa.service.dto.MAdventQuestWorldCriteria;
import io.shm.tsubasa.service.dto.MAdventQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestWorldMapper;

/**
 * Service for executing complex queries for {@link MAdventQuestWorld} entities in the database.
 * The main input is a {@link MAdventQuestWorldCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MAdventQuestWorldDTO} or a {@link Page} of {@link MAdventQuestWorldDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MAdventQuestWorldQueryService extends QueryService<MAdventQuestWorld> {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestWorldQueryService.class);

    private final MAdventQuestWorldRepository mAdventQuestWorldRepository;

    private final MAdventQuestWorldMapper mAdventQuestWorldMapper;

    public MAdventQuestWorldQueryService(MAdventQuestWorldRepository mAdventQuestWorldRepository, MAdventQuestWorldMapper mAdventQuestWorldMapper) {
        this.mAdventQuestWorldRepository = mAdventQuestWorldRepository;
        this.mAdventQuestWorldMapper = mAdventQuestWorldMapper;
    }

    /**
     * Return a {@link List} of {@link MAdventQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MAdventQuestWorldDTO> findByCriteria(MAdventQuestWorldCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MAdventQuestWorld> specification = createSpecification(criteria);
        return mAdventQuestWorldMapper.toDto(mAdventQuestWorldRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MAdventQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MAdventQuestWorldDTO> findByCriteria(MAdventQuestWorldCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MAdventQuestWorld> specification = createSpecification(criteria);
        return mAdventQuestWorldRepository.findAll(specification, page)
            .map(mAdventQuestWorldMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MAdventQuestWorldCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MAdventQuestWorld> specification = createSpecification(criteria);
        return mAdventQuestWorldRepository.count(specification);
    }

    /**
     * Function to convert MAdventQuestWorldCriteria to a {@link Specification}.
     */
    private Specification<MAdventQuestWorld> createSpecification(MAdventQuestWorldCriteria criteria) {
        Specification<MAdventQuestWorld> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MAdventQuestWorld_.id));
            }
            if (criteria.getSetId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSetId(), MAdventQuestWorld_.setId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MAdventQuestWorld_.number));
            }
            if (criteria.getSymbolType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSymbolType(), MAdventQuestWorld_.symbolType));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MAdventQuestWorld_.stageUnlockPattern));
            }
            if (criteria.getSpecialRewardContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentType(), MAdventQuestWorld_.specialRewardContentType));
            }
            if (criteria.getSpecialRewardContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentId(), MAdventQuestWorld_.specialRewardContentId));
            }
            if (criteria.getIsEnableCoop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsEnableCoop(), MAdventQuestWorld_.isEnableCoop));
            }
            if (criteria.getMAdventQuestStageId() != null) {
                specification = specification.and(buildSpecification(criteria.getMAdventQuestStageId(),
                    root -> root.join(MAdventQuestWorld_.mAdventQuestStages, JoinType.LEFT).get(MAdventQuestStage_.id)));
            }
        }
        return specification;
    }
}
