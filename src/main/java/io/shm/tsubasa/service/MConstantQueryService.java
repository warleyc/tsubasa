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

import io.shm.tsubasa.domain.MConstant;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MConstantRepository;
import io.shm.tsubasa.service.dto.MConstantCriteria;
import io.shm.tsubasa.service.dto.MConstantDTO;
import io.shm.tsubasa.service.mapper.MConstantMapper;

/**
 * Service for executing complex queries for {@link MConstant} entities in the database.
 * The main input is a {@link MConstantCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MConstantDTO} or a {@link Page} of {@link MConstantDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MConstantQueryService extends QueryService<MConstant> {

    private final Logger log = LoggerFactory.getLogger(MConstantQueryService.class);

    private final MConstantRepository mConstantRepository;

    private final MConstantMapper mConstantMapper;

    public MConstantQueryService(MConstantRepository mConstantRepository, MConstantMapper mConstantMapper) {
        this.mConstantRepository = mConstantRepository;
        this.mConstantMapper = mConstantMapper;
    }

    /**
     * Return a {@link List} of {@link MConstantDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MConstantDTO> findByCriteria(MConstantCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MConstant> specification = createSpecification(criteria);
        return mConstantMapper.toDto(mConstantRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MConstantDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MConstantDTO> findByCriteria(MConstantCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MConstant> specification = createSpecification(criteria);
        return mConstantRepository.findAll(specification, page)
            .map(mConstantMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MConstantCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MConstant> specification = createSpecification(criteria);
        return mConstantRepository.count(specification);
    }

    /**
     * Function to convert MConstantCriteria to a {@link Specification}.
     */
    private Specification<MConstant> createSpecification(MConstantCriteria criteria) {
        Specification<MConstant> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MConstant_.id));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValue(), MConstant_.value));
            }
        }
        return specification;
    }
}
