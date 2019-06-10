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

import io.shm.tsubasa.domain.MExtraNews;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MExtraNewsRepository;
import io.shm.tsubasa.service.dto.MExtraNewsCriteria;
import io.shm.tsubasa.service.dto.MExtraNewsDTO;
import io.shm.tsubasa.service.mapper.MExtraNewsMapper;

/**
 * Service for executing complex queries for {@link MExtraNews} entities in the database.
 * The main input is a {@link MExtraNewsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MExtraNewsDTO} or a {@link Page} of {@link MExtraNewsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MExtraNewsQueryService extends QueryService<MExtraNews> {

    private final Logger log = LoggerFactory.getLogger(MExtraNewsQueryService.class);

    private final MExtraNewsRepository mExtraNewsRepository;

    private final MExtraNewsMapper mExtraNewsMapper;

    public MExtraNewsQueryService(MExtraNewsRepository mExtraNewsRepository, MExtraNewsMapper mExtraNewsMapper) {
        this.mExtraNewsRepository = mExtraNewsRepository;
        this.mExtraNewsMapper = mExtraNewsMapper;
    }

    /**
     * Return a {@link List} of {@link MExtraNewsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MExtraNewsDTO> findByCriteria(MExtraNewsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MExtraNews> specification = createSpecification(criteria);
        return mExtraNewsMapper.toDto(mExtraNewsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MExtraNewsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MExtraNewsDTO> findByCriteria(MExtraNewsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MExtraNews> specification = createSpecification(criteria);
        return mExtraNewsRepository.findAll(specification, page)
            .map(mExtraNewsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MExtraNewsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MExtraNews> specification = createSpecification(criteria);
        return mExtraNewsRepository.count(specification);
    }

    /**
     * Function to convert MExtraNewsCriteria to a {@link Specification}.
     */
    private Specification<MExtraNews> createSpecification(MExtraNewsCriteria criteria) {
        Specification<MExtraNews> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MExtraNews_.id));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MExtraNews_.orderNum));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MExtraNews_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MExtraNews_.endAt));
            }
        }
        return specification;
    }
}
