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

import io.shm.tsubasa.domain.MDistributeCardParameter;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDistributeCardParameterRepository;
import io.shm.tsubasa.service.dto.MDistributeCardParameterCriteria;
import io.shm.tsubasa.service.dto.MDistributeCardParameterDTO;
import io.shm.tsubasa.service.mapper.MDistributeCardParameterMapper;

/**
 * Service for executing complex queries for {@link MDistributeCardParameter} entities in the database.
 * The main input is a {@link MDistributeCardParameterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDistributeCardParameterDTO} or a {@link Page} of {@link MDistributeCardParameterDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDistributeCardParameterQueryService extends QueryService<MDistributeCardParameter> {

    private final Logger log = LoggerFactory.getLogger(MDistributeCardParameterQueryService.class);

    private final MDistributeCardParameterRepository mDistributeCardParameterRepository;

    private final MDistributeCardParameterMapper mDistributeCardParameterMapper;

    public MDistributeCardParameterQueryService(MDistributeCardParameterRepository mDistributeCardParameterRepository, MDistributeCardParameterMapper mDistributeCardParameterMapper) {
        this.mDistributeCardParameterRepository = mDistributeCardParameterRepository;
        this.mDistributeCardParameterMapper = mDistributeCardParameterMapper;
    }

    /**
     * Return a {@link List} of {@link MDistributeCardParameterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDistributeCardParameterDTO> findByCriteria(MDistributeCardParameterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDistributeCardParameter> specification = createSpecification(criteria);
        return mDistributeCardParameterMapper.toDto(mDistributeCardParameterRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDistributeCardParameterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDistributeCardParameterDTO> findByCriteria(MDistributeCardParameterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDistributeCardParameter> specification = createSpecification(criteria);
        return mDistributeCardParameterRepository.findAll(specification, page)
            .map(mDistributeCardParameterMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDistributeCardParameterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDistributeCardParameter> specification = createSpecification(criteria);
        return mDistributeCardParameterRepository.count(specification);
    }

    /**
     * Function to convert MDistributeCardParameterCriteria to a {@link Specification}.
     */
    private Specification<MDistributeCardParameter> createSpecification(MDistributeCardParameterCriteria criteria) {
        Specification<MDistributeCardParameter> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDistributeCardParameter_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MDistributeCardParameter_.rarity));
            }
            if (criteria.getIsGk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsGk(), MDistributeCardParameter_.isGk));
            }
            if (criteria.getMaxStepCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxStepCount(), MDistributeCardParameter_.maxStepCount));
            }
            if (criteria.getMaxTotalStepCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxTotalStepCount(), MDistributeCardParameter_.maxTotalStepCount));
            }
            if (criteria.getDistributePointByStep() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDistributePointByStep(), MDistributeCardParameter_.distributePointByStep));
            }
        }
        return specification;
    }
}
