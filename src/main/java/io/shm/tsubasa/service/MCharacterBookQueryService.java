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

import io.shm.tsubasa.domain.MCharacterBook;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCharacterBookRepository;
import io.shm.tsubasa.service.dto.MCharacterBookCriteria;
import io.shm.tsubasa.service.dto.MCharacterBookDTO;
import io.shm.tsubasa.service.mapper.MCharacterBookMapper;

/**
 * Service for executing complex queries for {@link MCharacterBook} entities in the database.
 * The main input is a {@link MCharacterBookCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCharacterBookDTO} or a {@link Page} of {@link MCharacterBookDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCharacterBookQueryService extends QueryService<MCharacterBook> {

    private final Logger log = LoggerFactory.getLogger(MCharacterBookQueryService.class);

    private final MCharacterBookRepository mCharacterBookRepository;

    private final MCharacterBookMapper mCharacterBookMapper;

    public MCharacterBookQueryService(MCharacterBookRepository mCharacterBookRepository, MCharacterBookMapper mCharacterBookMapper) {
        this.mCharacterBookRepository = mCharacterBookRepository;
        this.mCharacterBookMapper = mCharacterBookMapper;
    }

    /**
     * Return a {@link List} of {@link MCharacterBookDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCharacterBookDTO> findByCriteria(MCharacterBookCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCharacterBook> specification = createSpecification(criteria);
        return mCharacterBookMapper.toDto(mCharacterBookRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCharacterBookDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCharacterBookDTO> findByCriteria(MCharacterBookCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCharacterBook> specification = createSpecification(criteria);
        return mCharacterBookRepository.findAll(specification, page)
            .map(mCharacterBookMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCharacterBookCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCharacterBook> specification = createSpecification(criteria);
        return mCharacterBookRepository.count(specification);
    }

    /**
     * Function to convert MCharacterBookCriteria to a {@link Specification}.
     */
    private Specification<MCharacterBook> createSpecification(MCharacterBookCriteria criteria) {
        Specification<MCharacterBook> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCharacterBook_.id));
            }
            if (criteria.getSeries() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeries(), MCharacterBook_.series));
            }
            if (criteria.getHeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeight(), MCharacterBook_.height));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MCharacterBook_.weight));
            }
        }
        return specification;
    }
}
