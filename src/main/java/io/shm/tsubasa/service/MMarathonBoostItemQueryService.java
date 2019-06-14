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

import io.shm.tsubasa.domain.MMarathonBoostItem;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonBoostItemRepository;
import io.shm.tsubasa.service.dto.MMarathonBoostItemCriteria;
import io.shm.tsubasa.service.dto.MMarathonBoostItemDTO;
import io.shm.tsubasa.service.mapper.MMarathonBoostItemMapper;

/**
 * Service for executing complex queries for {@link MMarathonBoostItem} entities in the database.
 * The main input is a {@link MMarathonBoostItemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonBoostItemDTO} or a {@link Page} of {@link MMarathonBoostItemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonBoostItemQueryService extends QueryService<MMarathonBoostItem> {

    private final Logger log = LoggerFactory.getLogger(MMarathonBoostItemQueryService.class);

    private final MMarathonBoostItemRepository mMarathonBoostItemRepository;

    private final MMarathonBoostItemMapper mMarathonBoostItemMapper;

    public MMarathonBoostItemQueryService(MMarathonBoostItemRepository mMarathonBoostItemRepository, MMarathonBoostItemMapper mMarathonBoostItemMapper) {
        this.mMarathonBoostItemRepository = mMarathonBoostItemRepository;
        this.mMarathonBoostItemMapper = mMarathonBoostItemMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonBoostItemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonBoostItemDTO> findByCriteria(MMarathonBoostItemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonBoostItem> specification = createSpecification(criteria);
        return mMarathonBoostItemMapper.toDto(mMarathonBoostItemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonBoostItemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonBoostItemDTO> findByCriteria(MMarathonBoostItemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonBoostItem> specification = createSpecification(criteria);
        return mMarathonBoostItemRepository.findAll(specification, page)
            .map(mMarathonBoostItemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonBoostItemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonBoostItem> specification = createSpecification(criteria);
        return mMarathonBoostItemRepository.count(specification);
    }

    /**
     * Function to convert MMarathonBoostItemCriteria to a {@link Specification}.
     */
    private Specification<MMarathonBoostItem> createSpecification(MMarathonBoostItemCriteria criteria) {
        Specification<MMarathonBoostItem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonBoostItem_.id));
            }
            if (criteria.getEventId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventId(), MMarathonBoostItem_.eventId));
            }
            if (criteria.getBoostRatio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBoostRatio(), MMarathonBoostItem_.boostRatio));
            }
        }
        return specification;
    }
}
