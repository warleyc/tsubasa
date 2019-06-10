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

import io.shm.tsubasa.domain.MGachaRenditionFirstGimmick;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionFirstGimmickRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionFirstGimmickCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionFirstGimmickDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionFirstGimmickMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionFirstGimmick} entities in the database.
 * The main input is a {@link MGachaRenditionFirstGimmickCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionFirstGimmickDTO} or a {@link Page} of {@link MGachaRenditionFirstGimmickDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionFirstGimmickQueryService extends QueryService<MGachaRenditionFirstGimmick> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionFirstGimmickQueryService.class);

    private final MGachaRenditionFirstGimmickRepository mGachaRenditionFirstGimmickRepository;

    private final MGachaRenditionFirstGimmickMapper mGachaRenditionFirstGimmickMapper;

    public MGachaRenditionFirstGimmickQueryService(MGachaRenditionFirstGimmickRepository mGachaRenditionFirstGimmickRepository, MGachaRenditionFirstGimmickMapper mGachaRenditionFirstGimmickMapper) {
        this.mGachaRenditionFirstGimmickRepository = mGachaRenditionFirstGimmickRepository;
        this.mGachaRenditionFirstGimmickMapper = mGachaRenditionFirstGimmickMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionFirstGimmickDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionFirstGimmickDTO> findByCriteria(MGachaRenditionFirstGimmickCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionFirstGimmick> specification = createSpecification(criteria);
        return mGachaRenditionFirstGimmickMapper.toDto(mGachaRenditionFirstGimmickRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionFirstGimmickDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionFirstGimmickDTO> findByCriteria(MGachaRenditionFirstGimmickCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionFirstGimmick> specification = createSpecification(criteria);
        return mGachaRenditionFirstGimmickRepository.findAll(specification, page)
            .map(mGachaRenditionFirstGimmickMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionFirstGimmickCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionFirstGimmick> specification = createSpecification(criteria);
        return mGachaRenditionFirstGimmickRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionFirstGimmickCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionFirstGimmick> createSpecification(MGachaRenditionFirstGimmickCriteria criteria) {
        Specification<MGachaRenditionFirstGimmick> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionFirstGimmick_.id));
            }
            if (criteria.getIsSsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSsr(), MGachaRenditionFirstGimmick_.isSsr));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionFirstGimmick_.weight));
            }
            if (criteria.getBirdNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirdNum(), MGachaRenditionFirstGimmick_.birdNum));
            }
            if (criteria.getIsTickerTape() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsTickerTape(), MGachaRenditionFirstGimmick_.isTickerTape));
            }
        }
        return specification;
    }
}
