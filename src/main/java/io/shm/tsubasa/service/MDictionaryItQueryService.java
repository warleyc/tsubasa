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

import io.shm.tsubasa.domain.MDictionaryIt;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDictionaryItRepository;
import io.shm.tsubasa.service.dto.MDictionaryItCriteria;
import io.shm.tsubasa.service.dto.MDictionaryItDTO;
import io.shm.tsubasa.service.mapper.MDictionaryItMapper;

/**
 * Service for executing complex queries for {@link MDictionaryIt} entities in the database.
 * The main input is a {@link MDictionaryItCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDictionaryItDTO} or a {@link Page} of {@link MDictionaryItDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDictionaryItQueryService extends QueryService<MDictionaryIt> {

    private final Logger log = LoggerFactory.getLogger(MDictionaryItQueryService.class);

    private final MDictionaryItRepository mDictionaryItRepository;

    private final MDictionaryItMapper mDictionaryItMapper;

    public MDictionaryItQueryService(MDictionaryItRepository mDictionaryItRepository, MDictionaryItMapper mDictionaryItMapper) {
        this.mDictionaryItRepository = mDictionaryItRepository;
        this.mDictionaryItMapper = mDictionaryItMapper;
    }

    /**
     * Return a {@link List} of {@link MDictionaryItDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDictionaryItDTO> findByCriteria(MDictionaryItCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDictionaryIt> specification = createSpecification(criteria);
        return mDictionaryItMapper.toDto(mDictionaryItRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDictionaryItDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryItDTO> findByCriteria(MDictionaryItCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDictionaryIt> specification = createSpecification(criteria);
        return mDictionaryItRepository.findAll(specification, page)
            .map(mDictionaryItMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDictionaryItCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDictionaryIt> specification = createSpecification(criteria);
        return mDictionaryItRepository.count(specification);
    }

    /**
     * Function to convert MDictionaryItCriteria to a {@link Specification}.
     */
    private Specification<MDictionaryIt> createSpecification(MDictionaryItCriteria criteria) {
        Specification<MDictionaryIt> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDictionaryIt_.id));
            }
        }
        return specification;
    }
}
