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

import io.shm.tsubasa.domain.MDictionaryJa;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDictionaryJaRepository;
import io.shm.tsubasa.service.dto.MDictionaryJaCriteria;
import io.shm.tsubasa.service.dto.MDictionaryJaDTO;
import io.shm.tsubasa.service.mapper.MDictionaryJaMapper;

/**
 * Service for executing complex queries for {@link MDictionaryJa} entities in the database.
 * The main input is a {@link MDictionaryJaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDictionaryJaDTO} or a {@link Page} of {@link MDictionaryJaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDictionaryJaQueryService extends QueryService<MDictionaryJa> {

    private final Logger log = LoggerFactory.getLogger(MDictionaryJaQueryService.class);

    private final MDictionaryJaRepository mDictionaryJaRepository;

    private final MDictionaryJaMapper mDictionaryJaMapper;

    public MDictionaryJaQueryService(MDictionaryJaRepository mDictionaryJaRepository, MDictionaryJaMapper mDictionaryJaMapper) {
        this.mDictionaryJaRepository = mDictionaryJaRepository;
        this.mDictionaryJaMapper = mDictionaryJaMapper;
    }

    /**
     * Return a {@link List} of {@link MDictionaryJaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDictionaryJaDTO> findByCriteria(MDictionaryJaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDictionaryJa> specification = createSpecification(criteria);
        return mDictionaryJaMapper.toDto(mDictionaryJaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDictionaryJaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDictionaryJaDTO> findByCriteria(MDictionaryJaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDictionaryJa> specification = createSpecification(criteria);
        return mDictionaryJaRepository.findAll(specification, page)
            .map(mDictionaryJaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDictionaryJaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDictionaryJa> specification = createSpecification(criteria);
        return mDictionaryJaRepository.count(specification);
    }

    /**
     * Function to convert MDictionaryJaCriteria to a {@link Specification}.
     */
    private Specification<MDictionaryJa> createSpecification(MDictionaryJaCriteria criteria) {
        Specification<MDictionaryJa> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDictionaryJa_.id));
            }
        }
        return specification;
    }
}
