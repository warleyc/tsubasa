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

import io.shm.tsubasa.domain.MGachaRenditionTrajectoryPhoenix;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionTrajectoryPhoenixRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryPhoenixCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryPhoenixDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTrajectoryPhoenixMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionTrajectoryPhoenix} entities in the database.
 * The main input is a {@link MGachaRenditionTrajectoryPhoenixCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionTrajectoryPhoenixDTO} or a {@link Page} of {@link MGachaRenditionTrajectoryPhoenixDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionTrajectoryPhoenixQueryService extends QueryService<MGachaRenditionTrajectoryPhoenix> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTrajectoryPhoenixQueryService.class);

    private final MGachaRenditionTrajectoryPhoenixRepository mGachaRenditionTrajectoryPhoenixRepository;

    private final MGachaRenditionTrajectoryPhoenixMapper mGachaRenditionTrajectoryPhoenixMapper;

    public MGachaRenditionTrajectoryPhoenixQueryService(MGachaRenditionTrajectoryPhoenixRepository mGachaRenditionTrajectoryPhoenixRepository, MGachaRenditionTrajectoryPhoenixMapper mGachaRenditionTrajectoryPhoenixMapper) {
        this.mGachaRenditionTrajectoryPhoenixRepository = mGachaRenditionTrajectoryPhoenixRepository;
        this.mGachaRenditionTrajectoryPhoenixMapper = mGachaRenditionTrajectoryPhoenixMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionTrajectoryPhoenixDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionTrajectoryPhoenixDTO> findByCriteria(MGachaRenditionTrajectoryPhoenixCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionTrajectoryPhoenix> specification = createSpecification(criteria);
        return mGachaRenditionTrajectoryPhoenixMapper.toDto(mGachaRenditionTrajectoryPhoenixRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionTrajectoryPhoenixDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionTrajectoryPhoenixDTO> findByCriteria(MGachaRenditionTrajectoryPhoenixCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionTrajectoryPhoenix> specification = createSpecification(criteria);
        return mGachaRenditionTrajectoryPhoenixRepository.findAll(specification, page)
            .map(mGachaRenditionTrajectoryPhoenixMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionTrajectoryPhoenixCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionTrajectoryPhoenix> specification = createSpecification(criteria);
        return mGachaRenditionTrajectoryPhoenixRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionTrajectoryPhoenixCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionTrajectoryPhoenix> createSpecification(MGachaRenditionTrajectoryPhoenixCriteria criteria) {
        Specification<MGachaRenditionTrajectoryPhoenix> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionTrajectoryPhoenix_.id));
            }
            if (criteria.getIsPhoenix() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsPhoenix(), MGachaRenditionTrajectoryPhoenix_.isPhoenix));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionTrajectoryPhoenix_.weight));
            }
        }
        return specification;
    }
}
