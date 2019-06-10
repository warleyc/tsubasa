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

import io.shm.tsubasa.domain.MDbMapping;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDbMappingRepository;
import io.shm.tsubasa.service.dto.MDbMappingCriteria;
import io.shm.tsubasa.service.dto.MDbMappingDTO;
import io.shm.tsubasa.service.mapper.MDbMappingMapper;

/**
 * Service for executing complex queries for {@link MDbMapping} entities in the database.
 * The main input is a {@link MDbMappingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDbMappingDTO} or a {@link Page} of {@link MDbMappingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDbMappingQueryService extends QueryService<MDbMapping> {

    private final Logger log = LoggerFactory.getLogger(MDbMappingQueryService.class);

    private final MDbMappingRepository mDbMappingRepository;

    private final MDbMappingMapper mDbMappingMapper;

    public MDbMappingQueryService(MDbMappingRepository mDbMappingRepository, MDbMappingMapper mDbMappingMapper) {
        this.mDbMappingRepository = mDbMappingRepository;
        this.mDbMappingMapper = mDbMappingMapper;
    }

    /**
     * Return a {@link List} of {@link MDbMappingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDbMappingDTO> findByCriteria(MDbMappingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDbMapping> specification = createSpecification(criteria);
        return mDbMappingMapper.toDto(mDbMappingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDbMappingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDbMappingDTO> findByCriteria(MDbMappingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDbMapping> specification = createSpecification(criteria);
        return mDbMappingRepository.findAll(specification, page)
            .map(mDbMappingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDbMappingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDbMapping> specification = createSpecification(criteria);
        return mDbMappingRepository.count(specification);
    }

    /**
     * Function to convert MDbMappingCriteria to a {@link Specification}.
     */
    private Specification<MDbMapping> createSpecification(MDbMappingCriteria criteria) {
        Specification<MDbMapping> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDbMapping_.id));
            }
        }
        return specification;
    }
}
