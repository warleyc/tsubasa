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

import io.shm.tsubasa.domain.MLuckWeeklyQuestWorld;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLuckWeeklyQuestWorldRepository;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestWorldCriteria;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MLuckWeeklyQuestWorldMapper;

/**
 * Service for executing complex queries for {@link MLuckWeeklyQuestWorld} entities in the database.
 * The main input is a {@link MLuckWeeklyQuestWorldCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLuckWeeklyQuestWorldDTO} or a {@link Page} of {@link MLuckWeeklyQuestWorldDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLuckWeeklyQuestWorldQueryService extends QueryService<MLuckWeeklyQuestWorld> {

    private final Logger log = LoggerFactory.getLogger(MLuckWeeklyQuestWorldQueryService.class);

    private final MLuckWeeklyQuestWorldRepository mLuckWeeklyQuestWorldRepository;

    private final MLuckWeeklyQuestWorldMapper mLuckWeeklyQuestWorldMapper;

    public MLuckWeeklyQuestWorldQueryService(MLuckWeeklyQuestWorldRepository mLuckWeeklyQuestWorldRepository, MLuckWeeklyQuestWorldMapper mLuckWeeklyQuestWorldMapper) {
        this.mLuckWeeklyQuestWorldRepository = mLuckWeeklyQuestWorldRepository;
        this.mLuckWeeklyQuestWorldMapper = mLuckWeeklyQuestWorldMapper;
    }

    /**
     * Return a {@link List} of {@link MLuckWeeklyQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLuckWeeklyQuestWorldDTO> findByCriteria(MLuckWeeklyQuestWorldCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLuckWeeklyQuestWorld> specification = createSpecification(criteria);
        return mLuckWeeklyQuestWorldMapper.toDto(mLuckWeeklyQuestWorldRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLuckWeeklyQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLuckWeeklyQuestWorldDTO> findByCriteria(MLuckWeeklyQuestWorldCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLuckWeeklyQuestWorld> specification = createSpecification(criteria);
        return mLuckWeeklyQuestWorldRepository.findAll(specification, page)
            .map(mLuckWeeklyQuestWorldMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLuckWeeklyQuestWorldCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLuckWeeklyQuestWorld> specification = createSpecification(criteria);
        return mLuckWeeklyQuestWorldRepository.count(specification);
    }

    /**
     * Function to convert MLuckWeeklyQuestWorldCriteria to a {@link Specification}.
     */
    private Specification<MLuckWeeklyQuestWorld> createSpecification(MLuckWeeklyQuestWorldCriteria criteria) {
        Specification<MLuckWeeklyQuestWorld> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLuckWeeklyQuestWorld_.id));
            }
            if (criteria.getSetId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSetId(), MLuckWeeklyQuestWorld_.setId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MLuckWeeklyQuestWorld_.number));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MLuckWeeklyQuestWorld_.stageUnlockPattern));
            }
            if (criteria.getSpecialRewardContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentType(), MLuckWeeklyQuestWorld_.specialRewardContentType));
            }
            if (criteria.getSpecialRewardContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentId(), MLuckWeeklyQuestWorld_.specialRewardContentId));
            }
            if (criteria.getIsEnableCoop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsEnableCoop(), MLuckWeeklyQuestWorld_.isEnableCoop));
            }
            if (criteria.getClearLimit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearLimit(), MLuckWeeklyQuestWorld_.clearLimit));
            }
            if (criteria.getMLuckWeeklyQuestStageId() != null) {
                specification = specification.and(buildSpecification(criteria.getMLuckWeeklyQuestStageId(),
                    root -> root.join(MLuckWeeklyQuestWorld_.mLuckWeeklyQuestStages, JoinType.LEFT).get(MLuckWeeklyQuestStage_.id)));
            }
        }
        return specification;
    }
}
