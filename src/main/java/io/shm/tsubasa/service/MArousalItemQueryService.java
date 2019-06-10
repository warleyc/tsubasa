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

import io.shm.tsubasa.domain.MArousalItem;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MArousalItemRepository;
import io.shm.tsubasa.service.dto.MArousalItemCriteria;
import io.shm.tsubasa.service.dto.MArousalItemDTO;
import io.shm.tsubasa.service.mapper.MArousalItemMapper;

/**
 * Service for executing complex queries for {@link MArousalItem} entities in the database.
 * The main input is a {@link MArousalItemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MArousalItemDTO} or a {@link Page} of {@link MArousalItemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MArousalItemQueryService extends QueryService<MArousalItem> {

    private final Logger log = LoggerFactory.getLogger(MArousalItemQueryService.class);

    private final MArousalItemRepository mArousalItemRepository;

    private final MArousalItemMapper mArousalItemMapper;

    public MArousalItemQueryService(MArousalItemRepository mArousalItemRepository, MArousalItemMapper mArousalItemMapper) {
        this.mArousalItemRepository = mArousalItemRepository;
        this.mArousalItemMapper = mArousalItemMapper;
    }

    /**
     * Return a {@link List} of {@link MArousalItemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MArousalItemDTO> findByCriteria(MArousalItemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MArousalItem> specification = createSpecification(criteria);
        return mArousalItemMapper.toDto(mArousalItemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MArousalItemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MArousalItemDTO> findByCriteria(MArousalItemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MArousalItem> specification = createSpecification(criteria);
        return mArousalItemRepository.findAll(specification, page)
            .map(mArousalItemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MArousalItemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MArousalItem> specification = createSpecification(criteria);
        return mArousalItemRepository.count(specification);
    }

    /**
     * Function to convert MArousalItemCriteria to a {@link Specification}.
     */
    private Specification<MArousalItem> createSpecification(MArousalItemCriteria criteria) {
        Specification<MArousalItem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MArousalItem_.id));
            }
            if (criteria.getArousalItemType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArousalItemType(), MArousalItem_.arousalItemType));
            }
        }
        return specification;
    }
}
