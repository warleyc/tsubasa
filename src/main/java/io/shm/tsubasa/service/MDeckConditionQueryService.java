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

import io.shm.tsubasa.domain.MDeckCondition;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDeckConditionRepository;
import io.shm.tsubasa.service.dto.MDeckConditionCriteria;
import io.shm.tsubasa.service.dto.MDeckConditionDTO;
import io.shm.tsubasa.service.mapper.MDeckConditionMapper;

/**
 * Service for executing complex queries for {@link MDeckCondition} entities in the database.
 * The main input is a {@link MDeckConditionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDeckConditionDTO} or a {@link Page} of {@link MDeckConditionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDeckConditionQueryService extends QueryService<MDeckCondition> {

    private final Logger log = LoggerFactory.getLogger(MDeckConditionQueryService.class);

    private final MDeckConditionRepository mDeckConditionRepository;

    private final MDeckConditionMapper mDeckConditionMapper;

    public MDeckConditionQueryService(MDeckConditionRepository mDeckConditionRepository, MDeckConditionMapper mDeckConditionMapper) {
        this.mDeckConditionRepository = mDeckConditionRepository;
        this.mDeckConditionMapper = mDeckConditionMapper;
    }

    /**
     * Return a {@link List} of {@link MDeckConditionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDeckConditionDTO> findByCriteria(MDeckConditionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDeckCondition> specification = createSpecification(criteria);
        return mDeckConditionMapper.toDto(mDeckConditionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDeckConditionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDeckConditionDTO> findByCriteria(MDeckConditionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDeckCondition> specification = createSpecification(criteria);
        return mDeckConditionRepository.findAll(specification, page)
            .map(mDeckConditionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDeckConditionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDeckCondition> specification = createSpecification(criteria);
        return mDeckConditionRepository.count(specification);
    }

    /**
     * Function to convert MDeckConditionCriteria to a {@link Specification}.
     */
    private Specification<MDeckCondition> createSpecification(MDeckConditionCriteria criteria) {
        Specification<MDeckCondition> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDeckCondition_.id));
            }
            if (criteria.getTargetFormationGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetFormationGroupId(), MDeckCondition_.targetFormationGroupId));
            }
            if (criteria.getTargetCharacterGroupMinId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharacterGroupMinId(), MDeckCondition_.targetCharacterGroupMinId));
            }
            if (criteria.getTargetCharacterGroupMaxId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharacterGroupMaxId(), MDeckCondition_.targetCharacterGroupMaxId));
            }
            if (criteria.getTargetPlayableCardGroupMinId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPlayableCardGroupMinId(), MDeckCondition_.targetPlayableCardGroupMinId));
            }
            if (criteria.getTargetPlayableCardGroupMaxId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPlayableCardGroupMaxId(), MDeckCondition_.targetPlayableCardGroupMaxId));
            }
            if (criteria.getTargetRarityGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetRarityGroupId(), MDeckCondition_.targetRarityGroupId));
            }
            if (criteria.getTargetAttribute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetAttribute(), MDeckCondition_.targetAttribute));
            }
            if (criteria.getTargetNationalityGroupMinId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetNationalityGroupMinId(), MDeckCondition_.targetNationalityGroupMinId));
            }
            if (criteria.getTargetNationalityGroupMaxId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetNationalityGroupMaxId(), MDeckCondition_.targetNationalityGroupMaxId));
            }
            if (criteria.getTargetTeamGroupMinId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTeamGroupMinId(), MDeckCondition_.targetTeamGroupMinId));
            }
            if (criteria.getTargetTeamGroupMaxId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTeamGroupMaxId(), MDeckCondition_.targetTeamGroupMaxId));
            }
            if (criteria.getTargetActionGroupMinId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetActionGroupMinId(), MDeckCondition_.targetActionGroupMinId));
            }
            if (criteria.getTargetActionGroupMaxId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetActionGroupMaxId(), MDeckCondition_.targetActionGroupMaxId));
            }
            if (criteria.getTargetTriggerEffectGroupMinId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTriggerEffectGroupMinId(), MDeckCondition_.targetTriggerEffectGroupMinId));
            }
            if (criteria.getTargetTriggerEffectGroupMaxId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTriggerEffectGroupMaxId(), MDeckCondition_.targetTriggerEffectGroupMaxId));
            }
            if (criteria.getTargetCharacterMinCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharacterMinCount(), MDeckCondition_.targetCharacterMinCount));
            }
            if (criteria.getTargetCharacterMaxCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharacterMaxCount(), MDeckCondition_.targetCharacterMaxCount));
            }
            if (criteria.getTargetPlayableCardMinCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPlayableCardMinCount(), MDeckCondition_.targetPlayableCardMinCount));
            }
            if (criteria.getTargetPlayableCardMaxCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPlayableCardMaxCount(), MDeckCondition_.targetPlayableCardMaxCount));
            }
            if (criteria.getTargetRarityMaxCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetRarityMaxCount(), MDeckCondition_.targetRarityMaxCount));
            }
            if (criteria.getTargetAttributeMinCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetAttributeMinCount(), MDeckCondition_.targetAttributeMinCount));
            }
            if (criteria.getTargetNationalityMinCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetNationalityMinCount(), MDeckCondition_.targetNationalityMinCount));
            }
            if (criteria.getTargetNationalityMaxCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetNationalityMaxCount(), MDeckCondition_.targetNationalityMaxCount));
            }
            if (criteria.getTargetTeamMinCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTeamMinCount(), MDeckCondition_.targetTeamMinCount));
            }
            if (criteria.getTargetTeamMaxCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTeamMaxCount(), MDeckCondition_.targetTeamMaxCount));
            }
            if (criteria.getTargetActionMinCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetActionMinCount(), MDeckCondition_.targetActionMinCount));
            }
            if (criteria.getTargetActionMaxCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetActionMaxCount(), MDeckCondition_.targetActionMaxCount));
            }
            if (criteria.getTargetTriggerEffectMinCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTriggerEffectMinCount(), MDeckCondition_.targetTriggerEffectMinCount));
            }
            if (criteria.getTargetTriggerEffectMaxCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTriggerEffectMaxCount(), MDeckCondition_.targetTriggerEffectMaxCount));
            }
            if (criteria.getTargetCharacterIsStartingMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharacterIsStartingMin(), MDeckCondition_.targetCharacterIsStartingMin));
            }
            if (criteria.getTargetCharacterIsStartingMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharacterIsStartingMax(), MDeckCondition_.targetCharacterIsStartingMax));
            }
            if (criteria.getTargetPlayableCardIsStartingMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPlayableCardIsStartingMin(), MDeckCondition_.targetPlayableCardIsStartingMin));
            }
            if (criteria.getTargetPlayableCardIsStartingMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPlayableCardIsStartingMax(), MDeckCondition_.targetPlayableCardIsStartingMax));
            }
            if (criteria.getTargetRarityIsStarting() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetRarityIsStarting(), MDeckCondition_.targetRarityIsStarting));
            }
            if (criteria.getTargetAttributeIsStarting() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetAttributeIsStarting(), MDeckCondition_.targetAttributeIsStarting));
            }
            if (criteria.getTargetNationalityIsStartingMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetNationalityIsStartingMin(), MDeckCondition_.targetNationalityIsStartingMin));
            }
            if (criteria.getTargetNationalityIsStartingMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetNationalityIsStartingMax(), MDeckCondition_.targetNationalityIsStartingMax));
            }
            if (criteria.getTargetTeamIsStartingMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTeamIsStartingMin(), MDeckCondition_.targetTeamIsStartingMin));
            }
            if (criteria.getTargetTeamIsStartingMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTeamIsStartingMax(), MDeckCondition_.targetTeamIsStartingMax));
            }
            if (criteria.getTargetActionIsStartingMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetActionIsStartingMin(), MDeckCondition_.targetActionIsStartingMin));
            }
            if (criteria.getTargetActionIsStartingMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetActionIsStartingMax(), MDeckCondition_.targetActionIsStartingMax));
            }
            if (criteria.getTargetTriggerEffectIsStartingMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTriggerEffectIsStartingMin(), MDeckCondition_.targetTriggerEffectIsStartingMin));
            }
            if (criteria.getTargetTriggerEffectIsStartingMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTriggerEffectIsStartingMax(), MDeckCondition_.targetTriggerEffectIsStartingMax));
            }
        }
        return specification;
    }
}
