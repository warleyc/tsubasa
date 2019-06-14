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

import io.shm.tsubasa.domain.MMatchEnvironment;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMatchEnvironmentRepository;
import io.shm.tsubasa.service.dto.MMatchEnvironmentCriteria;
import io.shm.tsubasa.service.dto.MMatchEnvironmentDTO;
import io.shm.tsubasa.service.mapper.MMatchEnvironmentMapper;

/**
 * Service for executing complex queries for {@link MMatchEnvironment} entities in the database.
 * The main input is a {@link MMatchEnvironmentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMatchEnvironmentDTO} or a {@link Page} of {@link MMatchEnvironmentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMatchEnvironmentQueryService extends QueryService<MMatchEnvironment> {

    private final Logger log = LoggerFactory.getLogger(MMatchEnvironmentQueryService.class);

    private final MMatchEnvironmentRepository mMatchEnvironmentRepository;

    private final MMatchEnvironmentMapper mMatchEnvironmentMapper;

    public MMatchEnvironmentQueryService(MMatchEnvironmentRepository mMatchEnvironmentRepository, MMatchEnvironmentMapper mMatchEnvironmentMapper) {
        this.mMatchEnvironmentRepository = mMatchEnvironmentRepository;
        this.mMatchEnvironmentMapper = mMatchEnvironmentMapper;
    }

    /**
     * Return a {@link List} of {@link MMatchEnvironmentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMatchEnvironmentDTO> findByCriteria(MMatchEnvironmentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMatchEnvironment> specification = createSpecification(criteria);
        return mMatchEnvironmentMapper.toDto(mMatchEnvironmentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMatchEnvironmentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMatchEnvironmentDTO> findByCriteria(MMatchEnvironmentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMatchEnvironment> specification = createSpecification(criteria);
        return mMatchEnvironmentRepository.findAll(specification, page)
            .map(mMatchEnvironmentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMatchEnvironmentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMatchEnvironment> specification = createSpecification(criteria);
        return mMatchEnvironmentRepository.count(specification);
    }

    /**
     * Function to convert MMatchEnvironmentCriteria to a {@link Specification}.
     */
    private Specification<MMatchEnvironment> createSpecification(MMatchEnvironmentCriteria criteria) {
        Specification<MMatchEnvironment> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMatchEnvironment_.id));
            }
            if (criteria.getCategory() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCategory(), MMatchEnvironment_.category));
            }
            if (criteria.getIsPlayRainySound() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsPlayRainySound(), MMatchEnvironment_.isPlayRainySound));
            }
        }
        return specification;
    }
}
