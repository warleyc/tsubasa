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

import io.shm.tsubasa.domain.MDictionaryZh;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDictionaryZhRepository;
import io.shm.tsubasa.service.dto.MDictionaryZhCriteria;
import io.shm.tsubasa.service.dto.MDictionaryZhDTO;
import io.shm.tsubasa.service.mapper.MDictionaryZhMapper;

/**
 * Service for executing complex queries for {@link MDictionaryZh} entities in the database.
 * The main input is a {@link MDictionaryZhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDictionaryZhDTO} or a {@link Page} of {@link MDictionaryZhDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDictionaryZhQueryService extends QueryService<MDictionaryZh> {

    private final Logger log = LoggerFactory.getLogger(MDictionaryZhQueryService.class);

    private final MDictionaryZhRepository mDictionaryZhRepository;

    private final MDictionaryZhMapper mDictionaryZhMapper;

    public MDictionaryZhQueryService(MDictionaryZhRepository mDictionaryZhRepository, MDictionaryZhMapper mDictionaryZhMapper) {
        this.mDictionaryZhRepository = mDictionaryZhRepository;
        this.mDictionaryZhMapper = mDictionaryZhMapper;
    }

    /**
     * Return a {@link List} of {@link MDictionaryZhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDictionaryZhDTO> findByCriteria(MDictionaryZhCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDictionaryZh> specification = createSpecification(criteria);
        return mDictionaryZhMapper.toDto(mDictionaryZhRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDictionaryZhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryZhDTO> findByCriteria(MDictionaryZhCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDictionaryZh> specification = createSpecification(criteria);
        return mDictionaryZhRepository.findAll(specification, page)
            .map(mDictionaryZhMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDictionaryZhCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDictionaryZh> specification = createSpecification(criteria);
        return mDictionaryZhRepository.count(specification);
    }

    /**
     * Function to convert MDictionaryZhCriteria to a {@link Specification}.
     */
    private Specification<MDictionaryZh> createSpecification(MDictionaryZhCriteria criteria) {
        Specification<MDictionaryZh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDictionaryZh_.id));
            }
        }
        return specification;
    }
}
