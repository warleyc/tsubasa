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

import io.shm.tsubasa.domain.MApRecoveryItem;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MApRecoveryItemRepository;
import io.shm.tsubasa.service.dto.MApRecoveryItemCriteria;
import io.shm.tsubasa.service.dto.MApRecoveryItemDTO;
import io.shm.tsubasa.service.mapper.MApRecoveryItemMapper;

/**
 * Service for executing complex queries for {@link MApRecoveryItem} entities in the database.
 * The main input is a {@link MApRecoveryItemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MApRecoveryItemDTO} or a {@link Page} of {@link MApRecoveryItemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MApRecoveryItemQueryService extends QueryService<MApRecoveryItem> {

    private final Logger log = LoggerFactory.getLogger(MApRecoveryItemQueryService.class);

    private final MApRecoveryItemRepository mApRecoveryItemRepository;

    private final MApRecoveryItemMapper mApRecoveryItemMapper;

    public MApRecoveryItemQueryService(MApRecoveryItemRepository mApRecoveryItemRepository, MApRecoveryItemMapper mApRecoveryItemMapper) {
        this.mApRecoveryItemRepository = mApRecoveryItemRepository;
        this.mApRecoveryItemMapper = mApRecoveryItemMapper;
    }

    /**
     * Return a {@link List} of {@link MApRecoveryItemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MApRecoveryItemDTO> findByCriteria(MApRecoveryItemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MApRecoveryItem> specification = createSpecification(criteria);
        return mApRecoveryItemMapper.toDto(mApRecoveryItemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MApRecoveryItemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MApRecoveryItemDTO> findByCriteria(MApRecoveryItemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MApRecoveryItem> specification = createSpecification(criteria);
        return mApRecoveryItemRepository.findAll(specification, page)
            .map(mApRecoveryItemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MApRecoveryItemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MApRecoveryItem> specification = createSpecification(criteria);
        return mApRecoveryItemRepository.count(specification);
    }

    /**
     * Function to convert MApRecoveryItemCriteria to a {@link Specification}.
     */
    private Specification<MApRecoveryItem> createSpecification(MApRecoveryItemCriteria criteria) {
        Specification<MApRecoveryItem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MApRecoveryItem_.id));
            }
            if (criteria.getApRecoveryItemType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getApRecoveryItemType(), MApRecoveryItem_.apRecoveryItemType));
            }
        }
        return specification;
    }
}
