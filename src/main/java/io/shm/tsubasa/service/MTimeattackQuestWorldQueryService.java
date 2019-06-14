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

import io.shm.tsubasa.domain.MTimeattackQuestWorld;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTimeattackQuestWorldRepository;
import io.shm.tsubasa.service.dto.MTimeattackQuestWorldCriteria;
import io.shm.tsubasa.service.dto.MTimeattackQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MTimeattackQuestWorldMapper;

/**
 * Service for executing complex queries for {@link MTimeattackQuestWorld} entities in the database.
 * The main input is a {@link MTimeattackQuestWorldCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTimeattackQuestWorldDTO} or a {@link Page} of {@link MTimeattackQuestWorldDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTimeattackQuestWorldQueryService extends QueryService<MTimeattackQuestWorld> {

    private final Logger log = LoggerFactory.getLogger(MTimeattackQuestWorldQueryService.class);

    private final MTimeattackQuestWorldRepository mTimeattackQuestWorldRepository;

    private final MTimeattackQuestWorldMapper mTimeattackQuestWorldMapper;

    public MTimeattackQuestWorldQueryService(MTimeattackQuestWorldRepository mTimeattackQuestWorldRepository, MTimeattackQuestWorldMapper mTimeattackQuestWorldMapper) {
        this.mTimeattackQuestWorldRepository = mTimeattackQuestWorldRepository;
        this.mTimeattackQuestWorldMapper = mTimeattackQuestWorldMapper;
    }

    /**
     * Return a {@link List} of {@link MTimeattackQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTimeattackQuestWorldDTO> findByCriteria(MTimeattackQuestWorldCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTimeattackQuestWorld> specification = createSpecification(criteria);
        return mTimeattackQuestWorldMapper.toDto(mTimeattackQuestWorldRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTimeattackQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTimeattackQuestWorldDTO> findByCriteria(MTimeattackQuestWorldCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTimeattackQuestWorld> specification = createSpecification(criteria);
        return mTimeattackQuestWorldRepository.findAll(specification, page)
            .map(mTimeattackQuestWorldMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTimeattackQuestWorldCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTimeattackQuestWorld> specification = createSpecification(criteria);
        return mTimeattackQuestWorldRepository.count(specification);
    }

    /**
     * Function to convert MTimeattackQuestWorldCriteria to a {@link Specification}.
     */
    private Specification<MTimeattackQuestWorld> createSpecification(MTimeattackQuestWorldCriteria criteria) {
        Specification<MTimeattackQuestWorld> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTimeattackQuestWorld_.id));
            }
            if (criteria.getSetId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSetId(), MTimeattackQuestWorld_.setId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MTimeattackQuestWorld_.number));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MTimeattackQuestWorld_.stageUnlockPattern));
            }
            if (criteria.getSpecialRewardContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentType(), MTimeattackQuestWorld_.specialRewardContentType));
            }
            if (criteria.getSpecialRewardContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentId(), MTimeattackQuestWorld_.specialRewardContentId));
            }
            if (criteria.getIsEnableCoop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsEnableCoop(), MTimeattackQuestWorld_.isEnableCoop));
            }
            if (criteria.getMTimeattackQuestStageId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTimeattackQuestStageId(),
                    root -> root.join(MTimeattackQuestWorld_.mTimeattackQuestStages, JoinType.LEFT).get(MTimeattackQuestStage_.id)));
            }
        }
        return specification;
    }
}
