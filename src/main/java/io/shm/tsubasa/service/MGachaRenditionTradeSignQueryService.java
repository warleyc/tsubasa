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

import io.shm.tsubasa.domain.MGachaRenditionTradeSign;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionTradeSignRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionTradeSignCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionTradeSignDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTradeSignMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionTradeSign} entities in the database.
 * The main input is a {@link MGachaRenditionTradeSignCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionTradeSignDTO} or a {@link Page} of {@link MGachaRenditionTradeSignDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionTradeSignQueryService extends QueryService<MGachaRenditionTradeSign> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTradeSignQueryService.class);

    private final MGachaRenditionTradeSignRepository mGachaRenditionTradeSignRepository;

    private final MGachaRenditionTradeSignMapper mGachaRenditionTradeSignMapper;

    public MGachaRenditionTradeSignQueryService(MGachaRenditionTradeSignRepository mGachaRenditionTradeSignRepository, MGachaRenditionTradeSignMapper mGachaRenditionTradeSignMapper) {
        this.mGachaRenditionTradeSignRepository = mGachaRenditionTradeSignRepository;
        this.mGachaRenditionTradeSignMapper = mGachaRenditionTradeSignMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionTradeSignDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionTradeSignDTO> findByCriteria(MGachaRenditionTradeSignCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionTradeSign> specification = createSpecification(criteria);
        return mGachaRenditionTradeSignMapper.toDto(mGachaRenditionTradeSignRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionTradeSignDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionTradeSignDTO> findByCriteria(MGachaRenditionTradeSignCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionTradeSign> specification = createSpecification(criteria);
        return mGachaRenditionTradeSignRepository.findAll(specification, page)
            .map(mGachaRenditionTradeSignMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionTradeSignCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionTradeSign> specification = createSpecification(criteria);
        return mGachaRenditionTradeSignRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionTradeSignCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionTradeSign> createSpecification(MGachaRenditionTradeSignCriteria criteria) {
        Specification<MGachaRenditionTradeSign> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionTradeSign_.id));
            }
            if (criteria.getRenditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRenditionId(), MGachaRenditionTradeSign_.renditionId));
            }
            if (criteria.getIsSsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSsr(), MGachaRenditionTradeSign_.isSsr));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionTradeSign_.weight));
            }
        }
        return specification;
    }
}
