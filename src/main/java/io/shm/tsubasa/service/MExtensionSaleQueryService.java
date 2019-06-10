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

import io.shm.tsubasa.domain.MExtensionSale;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MExtensionSaleRepository;
import io.shm.tsubasa.service.dto.MExtensionSaleCriteria;
import io.shm.tsubasa.service.dto.MExtensionSaleDTO;
import io.shm.tsubasa.service.mapper.MExtensionSaleMapper;

/**
 * Service for executing complex queries for {@link MExtensionSale} entities in the database.
 * The main input is a {@link MExtensionSaleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MExtensionSaleDTO} or a {@link Page} of {@link MExtensionSaleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MExtensionSaleQueryService extends QueryService<MExtensionSale> {

    private final Logger log = LoggerFactory.getLogger(MExtensionSaleQueryService.class);

    private final MExtensionSaleRepository mExtensionSaleRepository;

    private final MExtensionSaleMapper mExtensionSaleMapper;

    public MExtensionSaleQueryService(MExtensionSaleRepository mExtensionSaleRepository, MExtensionSaleMapper mExtensionSaleMapper) {
        this.mExtensionSaleRepository = mExtensionSaleRepository;
        this.mExtensionSaleMapper = mExtensionSaleMapper;
    }

    /**
     * Return a {@link List} of {@link MExtensionSaleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MExtensionSaleDTO> findByCriteria(MExtensionSaleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MExtensionSale> specification = createSpecification(criteria);
        return mExtensionSaleMapper.toDto(mExtensionSaleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MExtensionSaleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MExtensionSaleDTO> findByCriteria(MExtensionSaleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MExtensionSale> specification = createSpecification(criteria);
        return mExtensionSaleRepository.findAll(specification, page)
            .map(mExtensionSaleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MExtensionSaleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MExtensionSale> specification = createSpecification(criteria);
        return mExtensionSaleRepository.count(specification);
    }

    /**
     * Function to convert MExtensionSaleCriteria to a {@link Specification}.
     */
    private Specification<MExtensionSale> createSpecification(MExtensionSaleCriteria criteria) {
        Specification<MExtensionSale> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MExtensionSale_.id));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MExtensionSale_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MExtensionSale_.endAt));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getType(), MExtensionSale_.type));
            }
            if (criteria.getAddExtension() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAddExtension(), MExtensionSale_.addExtension));
            }
        }
        return specification;
    }
}
