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

import io.shm.tsubasa.domain.MQuestStageConditionDescription;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestStageConditionDescriptionRepository;
import io.shm.tsubasa.service.dto.MQuestStageConditionDescriptionCriteria;
import io.shm.tsubasa.service.dto.MQuestStageConditionDescriptionDTO;
import io.shm.tsubasa.service.mapper.MQuestStageConditionDescriptionMapper;

/**
 * Service for executing complex queries for {@link MQuestStageConditionDescription} entities in the database.
 * The main input is a {@link MQuestStageConditionDescriptionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestStageConditionDescriptionDTO} or a {@link Page} of {@link MQuestStageConditionDescriptionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestStageConditionDescriptionQueryService extends QueryService<MQuestStageConditionDescription> {

    private final Logger log = LoggerFactory.getLogger(MQuestStageConditionDescriptionQueryService.class);

    private final MQuestStageConditionDescriptionRepository mQuestStageConditionDescriptionRepository;

    private final MQuestStageConditionDescriptionMapper mQuestStageConditionDescriptionMapper;

    public MQuestStageConditionDescriptionQueryService(MQuestStageConditionDescriptionRepository mQuestStageConditionDescriptionRepository, MQuestStageConditionDescriptionMapper mQuestStageConditionDescriptionMapper) {
        this.mQuestStageConditionDescriptionRepository = mQuestStageConditionDescriptionRepository;
        this.mQuestStageConditionDescriptionMapper = mQuestStageConditionDescriptionMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestStageConditionDescriptionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestStageConditionDescriptionDTO> findByCriteria(MQuestStageConditionDescriptionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestStageConditionDescription> specification = createSpecification(criteria);
        return mQuestStageConditionDescriptionMapper.toDto(mQuestStageConditionDescriptionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestStageConditionDescriptionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestStageConditionDescriptionDTO> findByCriteria(MQuestStageConditionDescriptionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestStageConditionDescription> specification = createSpecification(criteria);
        return mQuestStageConditionDescriptionRepository.findAll(specification, page)
            .map(mQuestStageConditionDescriptionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestStageConditionDescriptionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestStageConditionDescription> specification = createSpecification(criteria);
        return mQuestStageConditionDescriptionRepository.count(specification);
    }

    /**
     * Function to convert MQuestStageConditionDescriptionCriteria to a {@link Specification}.
     */
    private Specification<MQuestStageConditionDescription> createSpecification(MQuestStageConditionDescriptionCriteria criteria) {
        Specification<MQuestStageConditionDescription> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestStageConditionDescription_.id));
            }
            if (criteria.getSuccessConditionType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSuccessConditionType(), MQuestStageConditionDescription_.successConditionType));
            }
            if (criteria.getSuccessConditionDetailTypeValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSuccessConditionDetailTypeValue(), MQuestStageConditionDescription_.successConditionDetailTypeValue));
            }
            if (criteria.getHasExistTargetCharacterGroup() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHasExistTargetCharacterGroup(), MQuestStageConditionDescription_.hasExistTargetCharacterGroup));
            }
            if (criteria.getHasSuccessConditionValueOneOnly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHasSuccessConditionValueOneOnly(), MQuestStageConditionDescription_.hasSuccessConditionValueOneOnly));
            }
            if (criteria.getFailureConditionTypeValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFailureConditionTypeValue(), MQuestStageConditionDescription_.failureConditionTypeValue));
            }
        }
        return specification;
    }
}
