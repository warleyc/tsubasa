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

import io.shm.tsubasa.domain.MDistributeParamPoint;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDistributeParamPointRepository;
import io.shm.tsubasa.service.dto.MDistributeParamPointCriteria;
import io.shm.tsubasa.service.dto.MDistributeParamPointDTO;
import io.shm.tsubasa.service.mapper.MDistributeParamPointMapper;

/**
 * Service for executing complex queries for {@link MDistributeParamPoint} entities in the database.
 * The main input is a {@link MDistributeParamPointCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDistributeParamPointDTO} or a {@link Page} of {@link MDistributeParamPointDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDistributeParamPointQueryService extends QueryService<MDistributeParamPoint> {

    private final Logger log = LoggerFactory.getLogger(MDistributeParamPointQueryService.class);

    private final MDistributeParamPointRepository mDistributeParamPointRepository;

    private final MDistributeParamPointMapper mDistributeParamPointMapper;

    public MDistributeParamPointQueryService(MDistributeParamPointRepository mDistributeParamPointRepository, MDistributeParamPointMapper mDistributeParamPointMapper) {
        this.mDistributeParamPointRepository = mDistributeParamPointRepository;
        this.mDistributeParamPointMapper = mDistributeParamPointMapper;
    }

    /**
     * Return a {@link List} of {@link MDistributeParamPointDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDistributeParamPointDTO> findByCriteria(MDistributeParamPointCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDistributeParamPoint> specification = createSpecification(criteria);
        return mDistributeParamPointMapper.toDto(mDistributeParamPointRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDistributeParamPointDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDistributeParamPointDTO> findByCriteria(MDistributeParamPointCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDistributeParamPoint> specification = createSpecification(criteria);
        return mDistributeParamPointRepository.findAll(specification, page)
            .map(mDistributeParamPointMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDistributeParamPointCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDistributeParamPoint> specification = createSpecification(criteria);
        return mDistributeParamPointRepository.count(specification);
    }

    /**
     * Function to convert MDistributeParamPointCriteria to a {@link Specification}.
     */
    private Specification<MDistributeParamPoint> createSpecification(MDistributeParamPointCriteria criteria) {
        Specification<MDistributeParamPoint> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDistributeParamPoint_.id));
            }
            if (criteria.getTargetAttribute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetAttribute(), MDistributeParamPoint_.targetAttribute));
            }
            if (criteria.getTargetNationalityGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetNationalityGroupId(), MDistributeParamPoint_.targetNationalityGroupId));
            }
        }
        return specification;
    }
}
