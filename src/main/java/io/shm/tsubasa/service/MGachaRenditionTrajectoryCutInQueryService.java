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

import io.shm.tsubasa.domain.MGachaRenditionTrajectoryCutIn;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionTrajectoryCutInRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCutInCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCutInDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTrajectoryCutInMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionTrajectoryCutIn} entities in the database.
 * The main input is a {@link MGachaRenditionTrajectoryCutInCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionTrajectoryCutInDTO} or a {@link Page} of {@link MGachaRenditionTrajectoryCutInDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionTrajectoryCutInQueryService extends QueryService<MGachaRenditionTrajectoryCutIn> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTrajectoryCutInQueryService.class);

    private final MGachaRenditionTrajectoryCutInRepository mGachaRenditionTrajectoryCutInRepository;

    private final MGachaRenditionTrajectoryCutInMapper mGachaRenditionTrajectoryCutInMapper;

    public MGachaRenditionTrajectoryCutInQueryService(MGachaRenditionTrajectoryCutInRepository mGachaRenditionTrajectoryCutInRepository, MGachaRenditionTrajectoryCutInMapper mGachaRenditionTrajectoryCutInMapper) {
        this.mGachaRenditionTrajectoryCutInRepository = mGachaRenditionTrajectoryCutInRepository;
        this.mGachaRenditionTrajectoryCutInMapper = mGachaRenditionTrajectoryCutInMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionTrajectoryCutInDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionTrajectoryCutInDTO> findByCriteria(MGachaRenditionTrajectoryCutInCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionTrajectoryCutIn> specification = createSpecification(criteria);
        return mGachaRenditionTrajectoryCutInMapper.toDto(mGachaRenditionTrajectoryCutInRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionTrajectoryCutInDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionTrajectoryCutInDTO> findByCriteria(MGachaRenditionTrajectoryCutInCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionTrajectoryCutIn> specification = createSpecification(criteria);
        return mGachaRenditionTrajectoryCutInRepository.findAll(specification, page)
            .map(mGachaRenditionTrajectoryCutInMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionTrajectoryCutInCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionTrajectoryCutIn> specification = createSpecification(criteria);
        return mGachaRenditionTrajectoryCutInRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionTrajectoryCutInCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionTrajectoryCutIn> createSpecification(MGachaRenditionTrajectoryCutInCriteria criteria) {
        Specification<MGachaRenditionTrajectoryCutIn> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionTrajectoryCutIn_.id));
            }
            if (criteria.getRenditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRenditionId(), MGachaRenditionTrajectoryCutIn_.renditionId));
            }
            if (criteria.getTrajectoryType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrajectoryType(), MGachaRenditionTrajectoryCutIn_.trajectoryType));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionTrajectoryCutIn_.weight));
            }
            if (criteria.getExceptKickerId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExceptKickerId(), MGachaRenditionTrajectoryCutIn_.exceptKickerId));
            }
        }
        return specification;
    }
}
