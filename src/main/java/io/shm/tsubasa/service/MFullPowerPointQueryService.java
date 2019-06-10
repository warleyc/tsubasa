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

import io.shm.tsubasa.domain.MFullPowerPoint;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MFullPowerPointRepository;
import io.shm.tsubasa.service.dto.MFullPowerPointCriteria;
import io.shm.tsubasa.service.dto.MFullPowerPointDTO;
import io.shm.tsubasa.service.mapper.MFullPowerPointMapper;

/**
 * Service for executing complex queries for {@link MFullPowerPoint} entities in the database.
 * The main input is a {@link MFullPowerPointCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MFullPowerPointDTO} or a {@link Page} of {@link MFullPowerPointDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MFullPowerPointQueryService extends QueryService<MFullPowerPoint> {

    private final Logger log = LoggerFactory.getLogger(MFullPowerPointQueryService.class);

    private final MFullPowerPointRepository mFullPowerPointRepository;

    private final MFullPowerPointMapper mFullPowerPointMapper;

    public MFullPowerPointQueryService(MFullPowerPointRepository mFullPowerPointRepository, MFullPowerPointMapper mFullPowerPointMapper) {
        this.mFullPowerPointRepository = mFullPowerPointRepository;
        this.mFullPowerPointMapper = mFullPowerPointMapper;
    }

    /**
     * Return a {@link List} of {@link MFullPowerPointDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MFullPowerPointDTO> findByCriteria(MFullPowerPointCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MFullPowerPoint> specification = createSpecification(criteria);
        return mFullPowerPointMapper.toDto(mFullPowerPointRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MFullPowerPointDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MFullPowerPointDTO> findByCriteria(MFullPowerPointCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MFullPowerPoint> specification = createSpecification(criteria);
        return mFullPowerPointRepository.findAll(specification, page)
            .map(mFullPowerPointMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MFullPowerPointCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MFullPowerPoint> specification = createSpecification(criteria);
        return mFullPowerPointRepository.count(specification);
    }

    /**
     * Function to convert MFullPowerPointCriteria to a {@link Specification}.
     */
    private Specification<MFullPowerPoint> createSpecification(MFullPowerPointCriteria criteria) {
        Specification<MFullPowerPoint> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MFullPowerPoint_.id));
            }
            if (criteria.getPointType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPointType(), MFullPowerPoint_.pointType));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValue(), MFullPowerPoint_.value));
            }
        }
        return specification;
    }
}
