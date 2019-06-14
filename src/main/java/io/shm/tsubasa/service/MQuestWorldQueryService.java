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

import io.shm.tsubasa.domain.MQuestWorld;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestWorldRepository;
import io.shm.tsubasa.service.dto.MQuestWorldCriteria;
import io.shm.tsubasa.service.dto.MQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MQuestWorldMapper;

/**
 * Service for executing complex queries for {@link MQuestWorld} entities in the database.
 * The main input is a {@link MQuestWorldCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestWorldDTO} or a {@link Page} of {@link MQuestWorldDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestWorldQueryService extends QueryService<MQuestWorld> {

    private final Logger log = LoggerFactory.getLogger(MQuestWorldQueryService.class);

    private final MQuestWorldRepository mQuestWorldRepository;

    private final MQuestWorldMapper mQuestWorldMapper;

    public MQuestWorldQueryService(MQuestWorldRepository mQuestWorldRepository, MQuestWorldMapper mQuestWorldMapper) {
        this.mQuestWorldRepository = mQuestWorldRepository;
        this.mQuestWorldMapper = mQuestWorldMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestWorldDTO> findByCriteria(MQuestWorldCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestWorld> specification = createSpecification(criteria);
        return mQuestWorldMapper.toDto(mQuestWorldRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestWorldDTO> findByCriteria(MQuestWorldCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestWorld> specification = createSpecification(criteria);
        return mQuestWorldRepository.findAll(specification, page)
            .map(mQuestWorldMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestWorldCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestWorld> specification = createSpecification(criteria);
        return mQuestWorldRepository.count(specification);
    }

    /**
     * Function to convert MQuestWorldCriteria to a {@link Specification}.
     */
    private Specification<MQuestWorld> createSpecification(MQuestWorldCriteria criteria) {
        Specification<MQuestWorld> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestWorld_.id));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MQuestWorld_.number));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MQuestWorld_.startAt));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MQuestWorld_.stageUnlockPattern));
            }
            if (criteria.getSpecialRewardContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentType(), MQuestWorld_.specialRewardContentType));
            }
            if (criteria.getSpecialRewardContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentId(), MQuestWorld_.specialRewardContentId));
            }
            if (criteria.getIsEnableCoop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsEnableCoop(), MQuestWorld_.isEnableCoop));
            }
            if (criteria.getMQuestStageId() != null) {
                specification = specification.and(buildSpecification(criteria.getMQuestStageId(),
                    root -> root.join(MQuestWorld_.mQuestStages, JoinType.LEFT).get(MQuestStage_.id)));
            }
        }
        return specification;
    }
}
