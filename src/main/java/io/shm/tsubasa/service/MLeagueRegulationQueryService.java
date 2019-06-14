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

import io.shm.tsubasa.domain.MLeagueRegulation;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLeagueRegulationRepository;
import io.shm.tsubasa.service.dto.MLeagueRegulationCriteria;
import io.shm.tsubasa.service.dto.MLeagueRegulationDTO;
import io.shm.tsubasa.service.mapper.MLeagueRegulationMapper;

/**
 * Service for executing complex queries for {@link MLeagueRegulation} entities in the database.
 * The main input is a {@link MLeagueRegulationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLeagueRegulationDTO} or a {@link Page} of {@link MLeagueRegulationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLeagueRegulationQueryService extends QueryService<MLeagueRegulation> {

    private final Logger log = LoggerFactory.getLogger(MLeagueRegulationQueryService.class);

    private final MLeagueRegulationRepository mLeagueRegulationRepository;

    private final MLeagueRegulationMapper mLeagueRegulationMapper;

    public MLeagueRegulationQueryService(MLeagueRegulationRepository mLeagueRegulationRepository, MLeagueRegulationMapper mLeagueRegulationMapper) {
        this.mLeagueRegulationRepository = mLeagueRegulationRepository;
        this.mLeagueRegulationMapper = mLeagueRegulationMapper;
    }

    /**
     * Return a {@link List} of {@link MLeagueRegulationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLeagueRegulationDTO> findByCriteria(MLeagueRegulationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLeagueRegulation> specification = createSpecification(criteria);
        return mLeagueRegulationMapper.toDto(mLeagueRegulationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLeagueRegulationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueRegulationDTO> findByCriteria(MLeagueRegulationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLeagueRegulation> specification = createSpecification(criteria);
        return mLeagueRegulationRepository.findAll(specification, page)
            .map(mLeagueRegulationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLeagueRegulationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLeagueRegulation> specification = createSpecification(criteria);
        return mLeagueRegulationRepository.count(specification);
    }

    /**
     * Function to convert MLeagueRegulationCriteria to a {@link Specification}.
     */
    private Specification<MLeagueRegulation> createSpecification(MLeagueRegulationCriteria criteria) {
        Specification<MLeagueRegulation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLeagueRegulation_.id));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MLeagueRegulation_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MLeagueRegulation_.endAt));
            }
            if (criteria.getMatchOptionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchOptionId(), MLeagueRegulation_.matchOptionId));
            }
            if (criteria.getDeckConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckConditionId(), MLeagueRegulation_.deckConditionId));
            }
            if (criteria.getRuleTutorialId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRuleTutorialId(), MLeagueRegulation_.ruleTutorialId));
            }
            if (criteria.getMmatchoptionId() != null) {
                specification = specification.and(buildSpecification(criteria.getMmatchoptionId(),
                    root -> root.join(MLeagueRegulation_.mmatchoption, JoinType.LEFT).get(MMatchOption_.id)));
            }
        }
        return specification;
    }
}
