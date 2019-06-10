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

import io.shm.tsubasa.domain.MDictionaryEn;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDictionaryEnRepository;
import io.shm.tsubasa.service.dto.MDictionaryEnCriteria;
import io.shm.tsubasa.service.dto.MDictionaryEnDTO;
import io.shm.tsubasa.service.mapper.MDictionaryEnMapper;

/**
 * Service for executing complex queries for {@link MDictionaryEn} entities in the database.
 * The main input is a {@link MDictionaryEnCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDictionaryEnDTO} or a {@link Page} of {@link MDictionaryEnDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDictionaryEnQueryService extends QueryService<MDictionaryEn> {

    private final Logger log = LoggerFactory.getLogger(MDictionaryEnQueryService.class);

    private final MDictionaryEnRepository mDictionaryEnRepository;

    private final MDictionaryEnMapper mDictionaryEnMapper;

    public MDictionaryEnQueryService(MDictionaryEnRepository mDictionaryEnRepository, MDictionaryEnMapper mDictionaryEnMapper) {
        this.mDictionaryEnRepository = mDictionaryEnRepository;
        this.mDictionaryEnMapper = mDictionaryEnMapper;
    }

    /**
     * Return a {@link List} of {@link MDictionaryEnDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDictionaryEnDTO> findByCriteria(MDictionaryEnCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDictionaryEn> specification = createSpecification(criteria);
        return mDictionaryEnMapper.toDto(mDictionaryEnRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDictionaryEnDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryEnDTO> findByCriteria(MDictionaryEnCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDictionaryEn> specification = createSpecification(criteria);
        return mDictionaryEnRepository.findAll(specification, page)
            .map(mDictionaryEnMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDictionaryEnCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDictionaryEn> specification = createSpecification(criteria);
        return mDictionaryEnRepository.count(specification);
    }

    /**
     * Function to convert MDictionaryEnCriteria to a {@link Specification}.
     */
    private Specification<MDictionaryEn> createSpecification(MDictionaryEnCriteria criteria) {
        Specification<MDictionaryEn> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDictionaryEn_.id));
            }
        }
        return specification;
    }
}
