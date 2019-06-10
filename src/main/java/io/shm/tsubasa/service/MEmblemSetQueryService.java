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

import io.shm.tsubasa.domain.MEmblemSet;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MEmblemSetRepository;
import io.shm.tsubasa.service.dto.MEmblemSetCriteria;
import io.shm.tsubasa.service.dto.MEmblemSetDTO;
import io.shm.tsubasa.service.mapper.MEmblemSetMapper;

/**
 * Service for executing complex queries for {@link MEmblemSet} entities in the database.
 * The main input is a {@link MEmblemSetCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MEmblemSetDTO} or a {@link Page} of {@link MEmblemSetDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MEmblemSetQueryService extends QueryService<MEmblemSet> {

    private final Logger log = LoggerFactory.getLogger(MEmblemSetQueryService.class);

    private final MEmblemSetRepository mEmblemSetRepository;

    private final MEmblemSetMapper mEmblemSetMapper;

    public MEmblemSetQueryService(MEmblemSetRepository mEmblemSetRepository, MEmblemSetMapper mEmblemSetMapper) {
        this.mEmblemSetRepository = mEmblemSetRepository;
        this.mEmblemSetMapper = mEmblemSetMapper;
    }

    /**
     * Return a {@link List} of {@link MEmblemSetDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MEmblemSetDTO> findByCriteria(MEmblemSetCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MEmblemSet> specification = createSpecification(criteria);
        return mEmblemSetMapper.toDto(mEmblemSetRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MEmblemSetDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MEmblemSetDTO> findByCriteria(MEmblemSetCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MEmblemSet> specification = createSpecification(criteria);
        return mEmblemSetRepository.findAll(specification, page)
            .map(mEmblemSetMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MEmblemSetCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MEmblemSet> specification = createSpecification(criteria);
        return mEmblemSetRepository.count(specification);
    }

    /**
     * Function to convert MEmblemSetCriteria to a {@link Specification}.
     */
    private Specification<MEmblemSet> createSpecification(MEmblemSetCriteria criteria) {
        Specification<MEmblemSet> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MEmblemSet_.id));
            }
        }
        return specification;
    }
}
