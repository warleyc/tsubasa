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

import io.shm.tsubasa.domain.MDistributeCardParameterStep;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDistributeCardParameterStepRepository;
import io.shm.tsubasa.service.dto.MDistributeCardParameterStepCriteria;
import io.shm.tsubasa.service.dto.MDistributeCardParameterStepDTO;
import io.shm.tsubasa.service.mapper.MDistributeCardParameterStepMapper;

/**
 * Service for executing complex queries for {@link MDistributeCardParameterStep} entities in the database.
 * The main input is a {@link MDistributeCardParameterStepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDistributeCardParameterStepDTO} or a {@link Page} of {@link MDistributeCardParameterStepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDistributeCardParameterStepQueryService extends QueryService<MDistributeCardParameterStep> {

    private final Logger log = LoggerFactory.getLogger(MDistributeCardParameterStepQueryService.class);

    private final MDistributeCardParameterStepRepository mDistributeCardParameterStepRepository;

    private final MDistributeCardParameterStepMapper mDistributeCardParameterStepMapper;

    public MDistributeCardParameterStepQueryService(MDistributeCardParameterStepRepository mDistributeCardParameterStepRepository, MDistributeCardParameterStepMapper mDistributeCardParameterStepMapper) {
        this.mDistributeCardParameterStepRepository = mDistributeCardParameterStepRepository;
        this.mDistributeCardParameterStepMapper = mDistributeCardParameterStepMapper;
    }

    /**
     * Return a {@link List} of {@link MDistributeCardParameterStepDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDistributeCardParameterStepDTO> findByCriteria(MDistributeCardParameterStepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDistributeCardParameterStep> specification = createSpecification(criteria);
        return mDistributeCardParameterStepMapper.toDto(mDistributeCardParameterStepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDistributeCardParameterStepDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDistributeCardParameterStepDTO> findByCriteria(MDistributeCardParameterStepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDistributeCardParameterStep> specification = createSpecification(criteria);
        return mDistributeCardParameterStepRepository.findAll(specification, page)
            .map(mDistributeCardParameterStepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDistributeCardParameterStepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDistributeCardParameterStep> specification = createSpecification(criteria);
        return mDistributeCardParameterStepRepository.count(specification);
    }

    /**
     * Function to convert MDistributeCardParameterStepCriteria to a {@link Specification}.
     */
    private Specification<MDistributeCardParameterStep> createSpecification(MDistributeCardParameterStepCriteria criteria) {
        Specification<MDistributeCardParameterStep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDistributeCardParameterStep_.id));
            }
            if (criteria.getIsGk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsGk(), MDistributeCardParameterStep_.isGk));
            }
            if (criteria.getStep() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStep(), MDistributeCardParameterStep_.step));
            }
            if (criteria.getParam() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParam(), MDistributeCardParameterStep_.param));
            }
        }
        return specification;
    }
}
