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

import io.shm.tsubasa.domain.MTips;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTipsRepository;
import io.shm.tsubasa.service.dto.MTipsCriteria;
import io.shm.tsubasa.service.dto.MTipsDTO;
import io.shm.tsubasa.service.mapper.MTipsMapper;

/**
 * Service for executing complex queries for {@link MTips} entities in the database.
 * The main input is a {@link MTipsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTipsDTO} or a {@link Page} of {@link MTipsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTipsQueryService extends QueryService<MTips> {

    private final Logger log = LoggerFactory.getLogger(MTipsQueryService.class);

    private final MTipsRepository mTipsRepository;

    private final MTipsMapper mTipsMapper;

    public MTipsQueryService(MTipsRepository mTipsRepository, MTipsMapper mTipsMapper) {
        this.mTipsRepository = mTipsRepository;
        this.mTipsMapper = mTipsMapper;
    }

    /**
     * Return a {@link List} of {@link MTipsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTipsDTO> findByCriteria(MTipsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTips> specification = createSpecification(criteria);
        return mTipsMapper.toDto(mTipsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTipsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTipsDTO> findByCriteria(MTipsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTips> specification = createSpecification(criteria);
        return mTipsRepository.findAll(specification, page)
            .map(mTipsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTipsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTips> specification = createSpecification(criteria);
        return mTipsRepository.count(specification);
    }

    /**
     * Function to convert MTipsCriteria to a {@link Specification}.
     */
    private Specification<MTips> createSpecification(MTipsCriteria criteria) {
        Specification<MTips> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTips_.id));
            }
            if (criteria.getPriority() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriority(), MTips_.priority));
            }
            if (criteria.getColorType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorType(), MTips_.colorType));
            }
            if (criteria.getIsTips() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsTips(), MTips_.isTips));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MTips_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MTips_.endAt));
            }
        }
        return specification;
    }
}
