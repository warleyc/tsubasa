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

import io.shm.tsubasa.domain.MGuildNegativeWord;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuildNegativeWordRepository;
import io.shm.tsubasa.service.dto.MGuildNegativeWordCriteria;
import io.shm.tsubasa.service.dto.MGuildNegativeWordDTO;
import io.shm.tsubasa.service.mapper.MGuildNegativeWordMapper;

/**
 * Service for executing complex queries for {@link MGuildNegativeWord} entities in the database.
 * The main input is a {@link MGuildNegativeWordCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuildNegativeWordDTO} or a {@link Page} of {@link MGuildNegativeWordDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuildNegativeWordQueryService extends QueryService<MGuildNegativeWord> {

    private final Logger log = LoggerFactory.getLogger(MGuildNegativeWordQueryService.class);

    private final MGuildNegativeWordRepository mGuildNegativeWordRepository;

    private final MGuildNegativeWordMapper mGuildNegativeWordMapper;

    public MGuildNegativeWordQueryService(MGuildNegativeWordRepository mGuildNegativeWordRepository, MGuildNegativeWordMapper mGuildNegativeWordMapper) {
        this.mGuildNegativeWordRepository = mGuildNegativeWordRepository;
        this.mGuildNegativeWordMapper = mGuildNegativeWordMapper;
    }

    /**
     * Return a {@link List} of {@link MGuildNegativeWordDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuildNegativeWordDTO> findByCriteria(MGuildNegativeWordCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuildNegativeWord> specification = createSpecification(criteria);
        return mGuildNegativeWordMapper.toDto(mGuildNegativeWordRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuildNegativeWordDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildNegativeWordDTO> findByCriteria(MGuildNegativeWordCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuildNegativeWord> specification = createSpecification(criteria);
        return mGuildNegativeWordRepository.findAll(specification, page)
            .map(mGuildNegativeWordMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuildNegativeWordCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuildNegativeWord> specification = createSpecification(criteria);
        return mGuildNegativeWordRepository.count(specification);
    }

    /**
     * Function to convert MGuildNegativeWordCriteria to a {@link Specification}.
     */
    private Specification<MGuildNegativeWord> createSpecification(MGuildNegativeWordCriteria criteria) {
        Specification<MGuildNegativeWord> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuildNegativeWord_.id));
            }
        }
        return specification;
    }
}
