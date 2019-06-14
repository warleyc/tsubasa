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

import io.shm.tsubasa.domain.MWeeklyQuestWorld;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MWeeklyQuestWorldRepository;
import io.shm.tsubasa.service.dto.MWeeklyQuestWorldCriteria;
import io.shm.tsubasa.service.dto.MWeeklyQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestWorldMapper;

/**
 * Service for executing complex queries for {@link MWeeklyQuestWorld} entities in the database.
 * The main input is a {@link MWeeklyQuestWorldCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MWeeklyQuestWorldDTO} or a {@link Page} of {@link MWeeklyQuestWorldDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MWeeklyQuestWorldQueryService extends QueryService<MWeeklyQuestWorld> {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestWorldQueryService.class);

    private final MWeeklyQuestWorldRepository mWeeklyQuestWorldRepository;

    private final MWeeklyQuestWorldMapper mWeeklyQuestWorldMapper;

    public MWeeklyQuestWorldQueryService(MWeeklyQuestWorldRepository mWeeklyQuestWorldRepository, MWeeklyQuestWorldMapper mWeeklyQuestWorldMapper) {
        this.mWeeklyQuestWorldRepository = mWeeklyQuestWorldRepository;
        this.mWeeklyQuestWorldMapper = mWeeklyQuestWorldMapper;
    }

    /**
     * Return a {@link List} of {@link MWeeklyQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MWeeklyQuestWorldDTO> findByCriteria(MWeeklyQuestWorldCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MWeeklyQuestWorld> specification = createSpecification(criteria);
        return mWeeklyQuestWorldMapper.toDto(mWeeklyQuestWorldRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MWeeklyQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MWeeklyQuestWorldDTO> findByCriteria(MWeeklyQuestWorldCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MWeeklyQuestWorld> specification = createSpecification(criteria);
        return mWeeklyQuestWorldRepository.findAll(specification, page)
            .map(mWeeklyQuestWorldMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MWeeklyQuestWorldCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MWeeklyQuestWorld> specification = createSpecification(criteria);
        return mWeeklyQuestWorldRepository.count(specification);
    }

    /**
     * Function to convert MWeeklyQuestWorldCriteria to a {@link Specification}.
     */
    private Specification<MWeeklyQuestWorld> createSpecification(MWeeklyQuestWorldCriteria criteria) {
        Specification<MWeeklyQuestWorld> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MWeeklyQuestWorld_.id));
            }
            if (criteria.getSetId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSetId(), MWeeklyQuestWorld_.setId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MWeeklyQuestWorld_.number));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MWeeklyQuestWorld_.stageUnlockPattern));
            }
            if (criteria.getSpecialRewardContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentType(), MWeeklyQuestWorld_.specialRewardContentType));
            }
            if (criteria.getSpecialRewardContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentId(), MWeeklyQuestWorld_.specialRewardContentId));
            }
            if (criteria.getIsEnableCoop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsEnableCoop(), MWeeklyQuestWorld_.isEnableCoop));
            }
            if (criteria.getMWeeklyQuestStageId() != null) {
                specification = specification.and(buildSpecification(criteria.getMWeeklyQuestStageId(),
                    root -> root.join(MWeeklyQuestWorld_.mWeeklyQuestStages, JoinType.LEFT).get(MWeeklyQuestStage_.id)));
            }
        }
        return specification;
    }
}
