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

import io.shm.tsubasa.domain.MDictionaryDe;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDictionaryDeRepository;
import io.shm.tsubasa.service.dto.MDictionaryDeCriteria;
import io.shm.tsubasa.service.dto.MDictionaryDeDTO;
import io.shm.tsubasa.service.mapper.MDictionaryDeMapper;

/**
 * Service for executing complex queries for {@link MDictionaryDe} entities in the database.
 * The main input is a {@link MDictionaryDeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDictionaryDeDTO} or a {@link Page} of {@link MDictionaryDeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDictionaryDeQueryService extends QueryService<MDictionaryDe> {

    private final Logger log = LoggerFactory.getLogger(MDictionaryDeQueryService.class);

    private final MDictionaryDeRepository mDictionaryDeRepository;

    private final MDictionaryDeMapper mDictionaryDeMapper;

    public MDictionaryDeQueryService(MDictionaryDeRepository mDictionaryDeRepository, MDictionaryDeMapper mDictionaryDeMapper) {
        this.mDictionaryDeRepository = mDictionaryDeRepository;
        this.mDictionaryDeMapper = mDictionaryDeMapper;
    }

    /**
     * Return a {@link List} of {@link MDictionaryDeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDictionaryDeDTO> findByCriteria(MDictionaryDeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDictionaryDe> specification = createSpecification(criteria);
        return mDictionaryDeMapper.toDto(mDictionaryDeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDictionaryDeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryDeDTO> findByCriteria(MDictionaryDeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDictionaryDe> specification = createSpecification(criteria);
        return mDictionaryDeRepository.findAll(specification, page)
            .map(mDictionaryDeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDictionaryDeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDictionaryDe> specification = createSpecification(criteria);
        return mDictionaryDeRepository.count(specification);
    }

    /**
     * Function to convert MDictionaryDeCriteria to a {@link Specification}.
     */
    private Specification<MDictionaryDe> createSpecification(MDictionaryDeCriteria criteria) {
        Specification<MDictionaryDe> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDictionaryDe_.id));
            }
        }
        return specification;
    }
}
