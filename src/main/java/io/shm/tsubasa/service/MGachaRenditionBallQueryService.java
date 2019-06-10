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

import io.shm.tsubasa.domain.MGachaRenditionBall;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionBallRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionBallCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionBallDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionBallMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionBall} entities in the database.
 * The main input is a {@link MGachaRenditionBallCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionBallDTO} or a {@link Page} of {@link MGachaRenditionBallDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionBallQueryService extends QueryService<MGachaRenditionBall> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionBallQueryService.class);

    private final MGachaRenditionBallRepository mGachaRenditionBallRepository;

    private final MGachaRenditionBallMapper mGachaRenditionBallMapper;

    public MGachaRenditionBallQueryService(MGachaRenditionBallRepository mGachaRenditionBallRepository, MGachaRenditionBallMapper mGachaRenditionBallMapper) {
        this.mGachaRenditionBallRepository = mGachaRenditionBallRepository;
        this.mGachaRenditionBallMapper = mGachaRenditionBallMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionBallDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionBallDTO> findByCriteria(MGachaRenditionBallCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionBall> specification = createSpecification(criteria);
        return mGachaRenditionBallMapper.toDto(mGachaRenditionBallRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionBallDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionBallDTO> findByCriteria(MGachaRenditionBallCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionBall> specification = createSpecification(criteria);
        return mGachaRenditionBallRepository.findAll(specification, page)
            .map(mGachaRenditionBallMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionBallCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionBall> specification = createSpecification(criteria);
        return mGachaRenditionBallRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionBallCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionBall> createSpecification(MGachaRenditionBallCriteria criteria) {
        Specification<MGachaRenditionBall> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionBall_.id));
            }
            if (criteria.getRenditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRenditionId(), MGachaRenditionBall_.renditionId));
            }
            if (criteria.getIsSsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSsr(), MGachaRenditionBall_.isSsr));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionBall_.weight));
            }
        }
        return specification;
    }
}
