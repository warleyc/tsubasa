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

import io.shm.tsubasa.domain.MCharacter;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCharacterRepository;
import io.shm.tsubasa.service.dto.MCharacterCriteria;
import io.shm.tsubasa.service.dto.MCharacterDTO;
import io.shm.tsubasa.service.mapper.MCharacterMapper;

/**
 * Service for executing complex queries for {@link MCharacter} entities in the database.
 * The main input is a {@link MCharacterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCharacterDTO} or a {@link Page} of {@link MCharacterDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCharacterQueryService extends QueryService<MCharacter> {

    private final Logger log = LoggerFactory.getLogger(MCharacterQueryService.class);

    private final MCharacterRepository mCharacterRepository;

    private final MCharacterMapper mCharacterMapper;

    public MCharacterQueryService(MCharacterRepository mCharacterRepository, MCharacterMapper mCharacterMapper) {
        this.mCharacterRepository = mCharacterRepository;
        this.mCharacterMapper = mCharacterMapper;
    }

    /**
     * Return a {@link List} of {@link MCharacterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCharacterDTO> findByCriteria(MCharacterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCharacter> specification = createSpecification(criteria);
        return mCharacterMapper.toDto(mCharacterRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCharacterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCharacterDTO> findByCriteria(MCharacterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCharacter> specification = createSpecification(criteria);
        return mCharacterRepository.findAll(specification, page)
            .map(mCharacterMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCharacterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCharacter> specification = createSpecification(criteria);
        return mCharacterRepository.count(specification);
    }

    /**
     * Function to convert MCharacterCriteria to a {@link Specification}.
     */
    private Specification<MCharacter> createSpecification(MCharacterCriteria criteria) {
        Specification<MCharacter> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCharacter_.id));
            }
            if (criteria.getCharacterBookPriority() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCharacterBookPriority(), MCharacter_.characterBookPriority));
            }
            if (criteria.getMActionSkillHolderCardContentId() != null) {
                specification = specification.and(buildSpecification(criteria.getMActionSkillHolderCardContentId(),
                    root -> root.join(MCharacter_.mActionSkillHolderCardContents, JoinType.LEFT).get(MActionSkillHolderCardContent_.id)));
            }
            if (criteria.getMCombinationCutPositionId() != null) {
                specification = specification.and(buildSpecification(criteria.getMCombinationCutPositionId(),
                    root -> root.join(MCharacter_.mCombinationCutPositions, JoinType.LEFT).get(MCombinationCutPosition_.id)));
            }
            if (criteria.getMMatchResultCutinId() != null) {
                specification = specification.and(buildSpecification(criteria.getMMatchResultCutinId(),
                    root -> root.join(MCharacter_.mMatchResultCutins, JoinType.LEFT).get(MMatchResultCutin_.id)));
            }
            if (criteria.getMNpcCardId() != null) {
                specification = specification.and(buildSpecification(criteria.getMNpcCardId(),
                    root -> root.join(MCharacter_.mNpcCards, JoinType.LEFT).get(MNpcCard_.id)));
            }
            if (criteria.getMTargetCharacterGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTargetCharacterGroupId(),
                    root -> root.join(MCharacter_.mTargetCharacterGroups, JoinType.LEFT).get(MTargetCharacterGroup_.id)));
            }
        }
        return specification;
    }
}
