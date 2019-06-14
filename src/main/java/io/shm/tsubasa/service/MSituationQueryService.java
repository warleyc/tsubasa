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

import io.shm.tsubasa.domain.MSituation;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MSituationRepository;
import io.shm.tsubasa.service.dto.MSituationCriteria;
import io.shm.tsubasa.service.dto.MSituationDTO;
import io.shm.tsubasa.service.mapper.MSituationMapper;

/**
 * Service for executing complex queries for {@link MSituation} entities in the database.
 * The main input is a {@link MSituationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MSituationDTO} or a {@link Page} of {@link MSituationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MSituationQueryService extends QueryService<MSituation> {

    private final Logger log = LoggerFactory.getLogger(MSituationQueryService.class);

    private final MSituationRepository mSituationRepository;

    private final MSituationMapper mSituationMapper;

    public MSituationQueryService(MSituationRepository mSituationRepository, MSituationMapper mSituationMapper) {
        this.mSituationRepository = mSituationRepository;
        this.mSituationMapper = mSituationMapper;
    }

    /**
     * Return a {@link List} of {@link MSituationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MSituationDTO> findByCriteria(MSituationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MSituation> specification = createSpecification(criteria);
        return mSituationMapper.toDto(mSituationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MSituationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MSituationDTO> findByCriteria(MSituationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MSituation> specification = createSpecification(criteria);
        return mSituationRepository.findAll(specification, page)
            .map(mSituationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MSituationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MSituation> specification = createSpecification(criteria);
        return mSituationRepository.count(specification);
    }

    /**
     * Function to convert MSituationCriteria to a {@link Specification}.
     */
    private Specification<MSituation> createSpecification(MSituationCriteria criteria) {
        Specification<MSituation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MSituation_.id));
            }
            if (criteria.getKickoff() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickoff(), MSituation_.kickoff));
            }
            if (criteria.getPenaltyKick() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPenaltyKick(), MSituation_.penaltyKick));
            }
            if (criteria.getMatchInterval() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchInterval(), MSituation_.matchInterval));
            }
            if (criteria.getOutOfPlay() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOutOfPlay(), MSituation_.outOfPlay));
            }
            if (criteria.getFoul() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFoul(), MSituation_.foul));
            }
            if (criteria.getGoal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoal(), MSituation_.goal));
            }
            if (criteria.getScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScore(), MSituation_.score));
            }
            if (criteria.getTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTime(), MSituation_.time));
            }
        }
        return specification;
    }
}
