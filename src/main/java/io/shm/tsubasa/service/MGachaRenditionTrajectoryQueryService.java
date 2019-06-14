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

import io.shm.tsubasa.domain.MGachaRenditionTrajectory;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionTrajectoryRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTrajectoryMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionTrajectory} entities in the database.
 * The main input is a {@link MGachaRenditionTrajectoryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionTrajectoryDTO} or a {@link Page} of {@link MGachaRenditionTrajectoryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionTrajectoryQueryService extends QueryService<MGachaRenditionTrajectory> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTrajectoryQueryService.class);

    private final MGachaRenditionTrajectoryRepository mGachaRenditionTrajectoryRepository;

    private final MGachaRenditionTrajectoryMapper mGachaRenditionTrajectoryMapper;

    public MGachaRenditionTrajectoryQueryService(MGachaRenditionTrajectoryRepository mGachaRenditionTrajectoryRepository, MGachaRenditionTrajectoryMapper mGachaRenditionTrajectoryMapper) {
        this.mGachaRenditionTrajectoryRepository = mGachaRenditionTrajectoryRepository;
        this.mGachaRenditionTrajectoryMapper = mGachaRenditionTrajectoryMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionTrajectoryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionTrajectoryDTO> findByCriteria(MGachaRenditionTrajectoryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionTrajectory> specification = createSpecification(criteria);
        return mGachaRenditionTrajectoryMapper.toDto(mGachaRenditionTrajectoryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionTrajectoryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionTrajectoryDTO> findByCriteria(MGachaRenditionTrajectoryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionTrajectory> specification = createSpecification(criteria);
        return mGachaRenditionTrajectoryRepository.findAll(specification, page)
            .map(mGachaRenditionTrajectoryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionTrajectoryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionTrajectory> specification = createSpecification(criteria);
        return mGachaRenditionTrajectoryRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionTrajectoryCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionTrajectory> createSpecification(MGachaRenditionTrajectoryCriteria criteria) {
        Specification<MGachaRenditionTrajectory> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionTrajectory_.id));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionTrajectory_.weight));
            }
            if (criteria.getTrajectoryType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrajectoryType(), MGachaRenditionTrajectory_.trajectoryType));
            }
        }
        return specification;
    }
}
