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

import io.shm.tsubasa.domain.MPenaltyKickCut;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPenaltyKickCutRepository;
import io.shm.tsubasa.service.dto.MPenaltyKickCutCriteria;
import io.shm.tsubasa.service.dto.MPenaltyKickCutDTO;
import io.shm.tsubasa.service.mapper.MPenaltyKickCutMapper;

/**
 * Service for executing complex queries for {@link MPenaltyKickCut} entities in the database.
 * The main input is a {@link MPenaltyKickCutCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPenaltyKickCutDTO} or a {@link Page} of {@link MPenaltyKickCutDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPenaltyKickCutQueryService extends QueryService<MPenaltyKickCut> {

    private final Logger log = LoggerFactory.getLogger(MPenaltyKickCutQueryService.class);

    private final MPenaltyKickCutRepository mPenaltyKickCutRepository;

    private final MPenaltyKickCutMapper mPenaltyKickCutMapper;

    public MPenaltyKickCutQueryService(MPenaltyKickCutRepository mPenaltyKickCutRepository, MPenaltyKickCutMapper mPenaltyKickCutMapper) {
        this.mPenaltyKickCutRepository = mPenaltyKickCutRepository;
        this.mPenaltyKickCutMapper = mPenaltyKickCutMapper;
    }

    /**
     * Return a {@link List} of {@link MPenaltyKickCutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPenaltyKickCutDTO> findByCriteria(MPenaltyKickCutCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPenaltyKickCut> specification = createSpecification(criteria);
        return mPenaltyKickCutMapper.toDto(mPenaltyKickCutRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPenaltyKickCutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPenaltyKickCutDTO> findByCriteria(MPenaltyKickCutCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPenaltyKickCut> specification = createSpecification(criteria);
        return mPenaltyKickCutRepository.findAll(specification, page)
            .map(mPenaltyKickCutMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPenaltyKickCutCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPenaltyKickCut> specification = createSpecification(criteria);
        return mPenaltyKickCutRepository.count(specification);
    }

    /**
     * Function to convert MPenaltyKickCutCriteria to a {@link Specification}.
     */
    private Specification<MPenaltyKickCut> createSpecification(MPenaltyKickCutCriteria criteria) {
        Specification<MPenaltyKickCut> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPenaltyKickCut_.id));
            }
            if (criteria.getKickerCourse() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickerCourse(), MPenaltyKickCut_.kickerCourse));
            }
            if (criteria.getKeeperCourse() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKeeperCourse(), MPenaltyKickCut_.keeperCourse));
            }
            if (criteria.getKeeperCommand() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKeeperCommand(), MPenaltyKickCut_.keeperCommand));
            }
            if (criteria.getResultType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResultType(), MPenaltyKickCut_.resultType));
            }
        }
        return specification;
    }
}
