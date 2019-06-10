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

import io.shm.tsubasa.domain.MDummyEmblem;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDummyEmblemRepository;
import io.shm.tsubasa.service.dto.MDummyEmblemCriteria;
import io.shm.tsubasa.service.dto.MDummyEmblemDTO;
import io.shm.tsubasa.service.mapper.MDummyEmblemMapper;

/**
 * Service for executing complex queries for {@link MDummyEmblem} entities in the database.
 * The main input is a {@link MDummyEmblemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDummyEmblemDTO} or a {@link Page} of {@link MDummyEmblemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDummyEmblemQueryService extends QueryService<MDummyEmblem> {

    private final Logger log = LoggerFactory.getLogger(MDummyEmblemQueryService.class);

    private final MDummyEmblemRepository mDummyEmblemRepository;

    private final MDummyEmblemMapper mDummyEmblemMapper;

    public MDummyEmblemQueryService(MDummyEmblemRepository mDummyEmblemRepository, MDummyEmblemMapper mDummyEmblemMapper) {
        this.mDummyEmblemRepository = mDummyEmblemRepository;
        this.mDummyEmblemMapper = mDummyEmblemMapper;
    }

    /**
     * Return a {@link List} of {@link MDummyEmblemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDummyEmblemDTO> findByCriteria(MDummyEmblemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDummyEmblem> specification = createSpecification(criteria);
        return mDummyEmblemMapper.toDto(mDummyEmblemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDummyEmblemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDummyEmblemDTO> findByCriteria(MDummyEmblemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDummyEmblem> specification = createSpecification(criteria);
        return mDummyEmblemRepository.findAll(specification, page)
            .map(mDummyEmblemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDummyEmblemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDummyEmblem> specification = createSpecification(criteria);
        return mDummyEmblemRepository.count(specification);
    }

    /**
     * Function to convert MDummyEmblemCriteria to a {@link Specification}.
     */
    private Specification<MDummyEmblem> createSpecification(MDummyEmblemCriteria criteria) {
        Specification<MDummyEmblem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDummyEmblem_.id));
            }
            if (criteria.getBackgroundId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBackgroundId(), MDummyEmblem_.backgroundId));
            }
            if (criteria.getUpperId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpperId(), MDummyEmblem_.upperId));
            }
            if (criteria.getMiddleId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMiddleId(), MDummyEmblem_.middleId));
            }
            if (criteria.getLowerId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLowerId(), MDummyEmblem_.lowerId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MDummyEmblem_.id, JoinType.LEFT).get(MEmblemParts_.id)));
            }
        }
        return specification;
    }
}
