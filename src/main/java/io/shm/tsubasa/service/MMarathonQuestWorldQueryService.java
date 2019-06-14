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

import io.shm.tsubasa.domain.MMarathonQuestWorld;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonQuestWorldRepository;
import io.shm.tsubasa.service.dto.MMarathonQuestWorldCriteria;
import io.shm.tsubasa.service.dto.MMarathonQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestWorldMapper;

/**
 * Service for executing complex queries for {@link MMarathonQuestWorld} entities in the database.
 * The main input is a {@link MMarathonQuestWorldCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonQuestWorldDTO} or a {@link Page} of {@link MMarathonQuestWorldDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonQuestWorldQueryService extends QueryService<MMarathonQuestWorld> {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestWorldQueryService.class);

    private final MMarathonQuestWorldRepository mMarathonQuestWorldRepository;

    private final MMarathonQuestWorldMapper mMarathonQuestWorldMapper;

    public MMarathonQuestWorldQueryService(MMarathonQuestWorldRepository mMarathonQuestWorldRepository, MMarathonQuestWorldMapper mMarathonQuestWorldMapper) {
        this.mMarathonQuestWorldRepository = mMarathonQuestWorldRepository;
        this.mMarathonQuestWorldMapper = mMarathonQuestWorldMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonQuestWorldDTO> findByCriteria(MMarathonQuestWorldCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonQuestWorld> specification = createSpecification(criteria);
        return mMarathonQuestWorldMapper.toDto(mMarathonQuestWorldRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonQuestWorldDTO> findByCriteria(MMarathonQuestWorldCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonQuestWorld> specification = createSpecification(criteria);
        return mMarathonQuestWorldRepository.findAll(specification, page)
            .map(mMarathonQuestWorldMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonQuestWorldCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonQuestWorld> specification = createSpecification(criteria);
        return mMarathonQuestWorldRepository.count(specification);
    }

    /**
     * Function to convert MMarathonQuestWorldCriteria to a {@link Specification}.
     */
    private Specification<MMarathonQuestWorld> createSpecification(MMarathonQuestWorldCriteria criteria) {
        Specification<MMarathonQuestWorld> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonQuestWorld_.id));
            }
            if (criteria.getSetId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSetId(), MMarathonQuestWorld_.setId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MMarathonQuestWorld_.number));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MMarathonQuestWorld_.stageUnlockPattern));
            }
            if (criteria.getSpecialRewardContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentType(), MMarathonQuestWorld_.specialRewardContentType));
            }
            if (criteria.getSpecialRewardContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentId(), MMarathonQuestWorld_.specialRewardContentId));
            }
            if (criteria.getIsEnableCoop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsEnableCoop(), MMarathonQuestWorld_.isEnableCoop));
            }
            if (criteria.getMMarathonQuestStageId() != null) {
                specification = specification.and(buildSpecification(criteria.getMMarathonQuestStageId(),
                    root -> root.join(MMarathonQuestWorld_.mMarathonQuestStages, JoinType.LEFT).get(MMarathonQuestStage_.id)));
            }
        }
        return specification;
    }
}
