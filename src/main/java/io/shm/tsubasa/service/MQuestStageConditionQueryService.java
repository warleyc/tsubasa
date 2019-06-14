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

import io.shm.tsubasa.domain.MQuestStageCondition;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestStageConditionRepository;
import io.shm.tsubasa.service.dto.MQuestStageConditionCriteria;
import io.shm.tsubasa.service.dto.MQuestStageConditionDTO;
import io.shm.tsubasa.service.mapper.MQuestStageConditionMapper;

/**
 * Service for executing complex queries for {@link MQuestStageCondition} entities in the database.
 * The main input is a {@link MQuestStageConditionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestStageConditionDTO} or a {@link Page} of {@link MQuestStageConditionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestStageConditionQueryService extends QueryService<MQuestStageCondition> {

    private final Logger log = LoggerFactory.getLogger(MQuestStageConditionQueryService.class);

    private final MQuestStageConditionRepository mQuestStageConditionRepository;

    private final MQuestStageConditionMapper mQuestStageConditionMapper;

    public MQuestStageConditionQueryService(MQuestStageConditionRepository mQuestStageConditionRepository, MQuestStageConditionMapper mQuestStageConditionMapper) {
        this.mQuestStageConditionRepository = mQuestStageConditionRepository;
        this.mQuestStageConditionMapper = mQuestStageConditionMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestStageConditionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestStageConditionDTO> findByCriteria(MQuestStageConditionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestStageCondition> specification = createSpecification(criteria);
        return mQuestStageConditionMapper.toDto(mQuestStageConditionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestStageConditionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestStageConditionDTO> findByCriteria(MQuestStageConditionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestStageCondition> specification = createSpecification(criteria);
        return mQuestStageConditionRepository.findAll(specification, page)
            .map(mQuestStageConditionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestStageConditionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestStageCondition> specification = createSpecification(criteria);
        return mQuestStageConditionRepository.count(specification);
    }

    /**
     * Function to convert MQuestStageConditionCriteria to a {@link Specification}.
     */
    private Specification<MQuestStageCondition> createSpecification(MQuestStageConditionCriteria criteria) {
        Specification<MQuestStageCondition> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestStageCondition_.id));
            }
            if (criteria.getSuccessConditionType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSuccessConditionType(), MQuestStageCondition_.successConditionType));
            }
            if (criteria.getSuccessConditionDetailType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSuccessConditionDetailType(), MQuestStageCondition_.successConditionDetailType));
            }
            if (criteria.getSuccessConditionValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSuccessConditionValue(), MQuestStageCondition_.successConditionValue));
            }
            if (criteria.getTargetCharacterGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharacterGroupId(), MQuestStageCondition_.targetCharacterGroupId));
            }
            if (criteria.getFailureConditionType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFailureConditionType(), MQuestStageCondition_.failureConditionType));
            }
        }
        return specification;
    }
}
