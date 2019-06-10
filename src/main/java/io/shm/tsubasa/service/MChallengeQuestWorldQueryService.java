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

import io.shm.tsubasa.domain.MChallengeQuestWorld;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MChallengeQuestWorldRepository;
import io.shm.tsubasa.service.dto.MChallengeQuestWorldCriteria;
import io.shm.tsubasa.service.dto.MChallengeQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestWorldMapper;

/**
 * Service for executing complex queries for {@link MChallengeQuestWorld} entities in the database.
 * The main input is a {@link MChallengeQuestWorldCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MChallengeQuestWorldDTO} or a {@link Page} of {@link MChallengeQuestWorldDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MChallengeQuestWorldQueryService extends QueryService<MChallengeQuestWorld> {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestWorldQueryService.class);

    private final MChallengeQuestWorldRepository mChallengeQuestWorldRepository;

    private final MChallengeQuestWorldMapper mChallengeQuestWorldMapper;

    public MChallengeQuestWorldQueryService(MChallengeQuestWorldRepository mChallengeQuestWorldRepository, MChallengeQuestWorldMapper mChallengeQuestWorldMapper) {
        this.mChallengeQuestWorldRepository = mChallengeQuestWorldRepository;
        this.mChallengeQuestWorldMapper = mChallengeQuestWorldMapper;
    }

    /**
     * Return a {@link List} of {@link MChallengeQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MChallengeQuestWorldDTO> findByCriteria(MChallengeQuestWorldCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MChallengeQuestWorld> specification = createSpecification(criteria);
        return mChallengeQuestWorldMapper.toDto(mChallengeQuestWorldRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MChallengeQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MChallengeQuestWorldDTO> findByCriteria(MChallengeQuestWorldCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MChallengeQuestWorld> specification = createSpecification(criteria);
        return mChallengeQuestWorldRepository.findAll(specification, page)
            .map(mChallengeQuestWorldMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MChallengeQuestWorldCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MChallengeQuestWorld> specification = createSpecification(criteria);
        return mChallengeQuestWorldRepository.count(specification);
    }

    /**
     * Function to convert MChallengeQuestWorldCriteria to a {@link Specification}.
     */
    private Specification<MChallengeQuestWorld> createSpecification(MChallengeQuestWorldCriteria criteria) {
        Specification<MChallengeQuestWorld> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MChallengeQuestWorld_.id));
            }
            if (criteria.getSetId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSetId(), MChallengeQuestWorld_.setId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MChallengeQuestWorld_.number));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MChallengeQuestWorld_.stageUnlockPattern));
            }
            if (criteria.getSpecialRewardContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentType(), MChallengeQuestWorld_.specialRewardContentType));
            }
            if (criteria.getSpecialRewardContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentId(), MChallengeQuestWorld_.specialRewardContentId));
            }
            if (criteria.getIsEnableCoop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsEnableCoop(), MChallengeQuestWorld_.isEnableCoop));
            }
            if (criteria.getMChallengeQuestStageId() != null) {
                specification = specification.and(buildSpecification(criteria.getMChallengeQuestStageId(),
                    root -> root.join(MChallengeQuestWorld_.mChallengeQuestStages, JoinType.LEFT).get(MChallengeQuestStage_.id)));
            }
        }
        return specification;
    }
}
