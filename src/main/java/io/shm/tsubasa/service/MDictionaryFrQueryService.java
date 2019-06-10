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

import io.shm.tsubasa.domain.MDictionaryFr;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDictionaryFrRepository;
import io.shm.tsubasa.service.dto.MDictionaryFrCriteria;
import io.shm.tsubasa.service.dto.MDictionaryFrDTO;
import io.shm.tsubasa.service.mapper.MDictionaryFrMapper;

/**
 * Service for executing complex queries for {@link MDictionaryFr} entities in the database.
 * The main input is a {@link MDictionaryFrCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDictionaryFrDTO} or a {@link Page} of {@link MDictionaryFrDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDictionaryFrQueryService extends QueryService<MDictionaryFr> {

    private final Logger log = LoggerFactory.getLogger(MDictionaryFrQueryService.class);

    private final MDictionaryFrRepository mDictionaryFrRepository;

    private final MDictionaryFrMapper mDictionaryFrMapper;

    public MDictionaryFrQueryService(MDictionaryFrRepository mDictionaryFrRepository, MDictionaryFrMapper mDictionaryFrMapper) {
        this.mDictionaryFrRepository = mDictionaryFrRepository;
        this.mDictionaryFrMapper = mDictionaryFrMapper;
    }

    /**
     * Return a {@link List} of {@link MDictionaryFrDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDictionaryFrDTO> findByCriteria(MDictionaryFrCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDictionaryFr> specification = createSpecification(criteria);
        return mDictionaryFrMapper.toDto(mDictionaryFrRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDictionaryFrDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryFrDTO> findByCriteria(MDictionaryFrCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDictionaryFr> specification = createSpecification(criteria);
        return mDictionaryFrRepository.findAll(specification, page)
            .map(mDictionaryFrMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDictionaryFrCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDictionaryFr> specification = createSpecification(criteria);
        return mDictionaryFrRepository.count(specification);
    }

    /**
     * Function to convert MDictionaryFrCriteria to a {@link Specification}.
     */
    private Specification<MDictionaryFr> createSpecification(MDictionaryFrCriteria criteria) {
        Specification<MDictionaryFr> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDictionaryFr_.id));
            }
        }
        return specification;
    }
}
