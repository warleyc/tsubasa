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

import io.shm.tsubasa.domain.MSellCardCoin;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MSellCardCoinRepository;
import io.shm.tsubasa.service.dto.MSellCardCoinCriteria;
import io.shm.tsubasa.service.dto.MSellCardCoinDTO;
import io.shm.tsubasa.service.mapper.MSellCardCoinMapper;

/**
 * Service for executing complex queries for {@link MSellCardCoin} entities in the database.
 * The main input is a {@link MSellCardCoinCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MSellCardCoinDTO} or a {@link Page} of {@link MSellCardCoinDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MSellCardCoinQueryService extends QueryService<MSellCardCoin> {

    private final Logger log = LoggerFactory.getLogger(MSellCardCoinQueryService.class);

    private final MSellCardCoinRepository mSellCardCoinRepository;

    private final MSellCardCoinMapper mSellCardCoinMapper;

    public MSellCardCoinQueryService(MSellCardCoinRepository mSellCardCoinRepository, MSellCardCoinMapper mSellCardCoinMapper) {
        this.mSellCardCoinRepository = mSellCardCoinRepository;
        this.mSellCardCoinMapper = mSellCardCoinMapper;
    }

    /**
     * Return a {@link List} of {@link MSellCardCoinDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MSellCardCoinDTO> findByCriteria(MSellCardCoinCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MSellCardCoin> specification = createSpecification(criteria);
        return mSellCardCoinMapper.toDto(mSellCardCoinRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MSellCardCoinDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MSellCardCoinDTO> findByCriteria(MSellCardCoinCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MSellCardCoin> specification = createSpecification(criteria);
        return mSellCardCoinRepository.findAll(specification, page)
            .map(mSellCardCoinMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MSellCardCoinCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MSellCardCoin> specification = createSpecification(criteria);
        return mSellCardCoinRepository.count(specification);
    }

    /**
     * Function to convert MSellCardCoinCriteria to a {@link Specification}.
     */
    private Specification<MSellCardCoin> createSpecification(MSellCardCoinCriteria criteria) {
        Specification<MSellCardCoin> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MSellCardCoin_.id));
            }
            if (criteria.getGroupNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupNum(), MSellCardCoin_.groupNum));
            }
            if (criteria.getLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevel(), MSellCardCoin_.level));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MSellCardCoin_.coin));
            }
        }
        return specification;
    }
}
