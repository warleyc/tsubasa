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

import io.shm.tsubasa.domain.MCombinationCutPosition;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCombinationCutPositionRepository;
import io.shm.tsubasa.service.dto.MCombinationCutPositionCriteria;
import io.shm.tsubasa.service.dto.MCombinationCutPositionDTO;
import io.shm.tsubasa.service.mapper.MCombinationCutPositionMapper;

/**
 * Service for executing complex queries for {@link MCombinationCutPosition} entities in the database.
 * The main input is a {@link MCombinationCutPositionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCombinationCutPositionDTO} or a {@link Page} of {@link MCombinationCutPositionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCombinationCutPositionQueryService extends QueryService<MCombinationCutPosition> {

    private final Logger log = LoggerFactory.getLogger(MCombinationCutPositionQueryService.class);

    private final MCombinationCutPositionRepository mCombinationCutPositionRepository;

    private final MCombinationCutPositionMapper mCombinationCutPositionMapper;

    public MCombinationCutPositionQueryService(MCombinationCutPositionRepository mCombinationCutPositionRepository, MCombinationCutPositionMapper mCombinationCutPositionMapper) {
        this.mCombinationCutPositionRepository = mCombinationCutPositionRepository;
        this.mCombinationCutPositionMapper = mCombinationCutPositionMapper;
    }

    /**
     * Return a {@link List} of {@link MCombinationCutPositionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCombinationCutPositionDTO> findByCriteria(MCombinationCutPositionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCombinationCutPosition> specification = createSpecification(criteria);
        return mCombinationCutPositionMapper.toDto(mCombinationCutPositionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCombinationCutPositionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCombinationCutPositionDTO> findByCriteria(MCombinationCutPositionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCombinationCutPosition> specification = createSpecification(criteria);
        return mCombinationCutPositionRepository.findAll(specification, page)
            .map(mCombinationCutPositionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCombinationCutPositionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCombinationCutPosition> specification = createSpecification(criteria);
        return mCombinationCutPositionRepository.count(specification);
    }

    /**
     * Function to convert MCombinationCutPositionCriteria to a {@link Specification}.
     */
    private Specification<MCombinationCutPosition> createSpecification(MCombinationCutPositionCriteria criteria) {
        Specification<MCombinationCutPosition> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCombinationCutPosition_.id));
            }
            if (criteria.getActionCutId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionCutId(), MCombinationCutPosition_.actionCutId));
            }
            if (criteria.getCharacterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCharacterId(), MCombinationCutPosition_.characterId));
            }
            if (criteria.getActivatorPosition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActivatorPosition(), MCombinationCutPosition_.activatorPosition));
            }
            if (criteria.getParticipantPosition1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParticipantPosition1(), MCombinationCutPosition_.participantPosition1));
            }
            if (criteria.getParticipantPosition2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParticipantPosition2(), MCombinationCutPosition_.participantPosition2));
            }
            if (criteria.getParticipantPosition3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParticipantPosition3(), MCombinationCutPosition_.participantPosition3));
            }
            if (criteria.getParticipantPosition4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParticipantPosition4(), MCombinationCutPosition_.participantPosition4));
            }
            if (criteria.getParticipantPosition5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParticipantPosition5(), MCombinationCutPosition_.participantPosition5));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MCombinationCutPosition_.id, JoinType.LEFT).get(MCharacter_.id)));
            }
        }
        return specification;
    }
}
