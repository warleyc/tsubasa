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

import io.shm.tsubasa.domain.MDictionaryAr;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDictionaryArRepository;
import io.shm.tsubasa.service.dto.MDictionaryArCriteria;
import io.shm.tsubasa.service.dto.MDictionaryArDTO;
import io.shm.tsubasa.service.mapper.MDictionaryArMapper;

/**
 * Service for executing complex queries for {@link MDictionaryAr} entities in the database.
 * The main input is a {@link MDictionaryArCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDictionaryArDTO} or a {@link Page} of {@link MDictionaryArDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDictionaryArQueryService extends QueryService<MDictionaryAr> {

    private final Logger log = LoggerFactory.getLogger(MDictionaryArQueryService.class);

    private final MDictionaryArRepository mDictionaryArRepository;

    private final MDictionaryArMapper mDictionaryArMapper;

    public MDictionaryArQueryService(MDictionaryArRepository mDictionaryArRepository, MDictionaryArMapper mDictionaryArMapper) {
        this.mDictionaryArRepository = mDictionaryArRepository;
        this.mDictionaryArMapper = mDictionaryArMapper;
    }

    /**
     * Return a {@link List} of {@link MDictionaryArDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDictionaryArDTO> findByCriteria(MDictionaryArCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDictionaryAr> specification = createSpecification(criteria);
        return mDictionaryArMapper.toDto(mDictionaryArRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDictionaryArDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryArDTO> findByCriteria(MDictionaryArCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDictionaryAr> specification = createSpecification(criteria);
        return mDictionaryArRepository.findAll(specification, page)
            .map(mDictionaryArMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDictionaryArCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDictionaryAr> specification = createSpecification(criteria);
        return mDictionaryArRepository.count(specification);
    }

    /**
     * Function to convert MDictionaryArCriteria to a {@link Specification}.
     */
    private Specification<MDictionaryAr> createSpecification(MDictionaryArCriteria criteria) {
        Specification<MDictionaryAr> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDictionaryAr_.id));
            }
        }
        return specification;
    }
}
