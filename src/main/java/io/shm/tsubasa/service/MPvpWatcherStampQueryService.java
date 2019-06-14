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

import io.shm.tsubasa.domain.MPvpWatcherStamp;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPvpWatcherStampRepository;
import io.shm.tsubasa.service.dto.MPvpWatcherStampCriteria;
import io.shm.tsubasa.service.dto.MPvpWatcherStampDTO;
import io.shm.tsubasa.service.mapper.MPvpWatcherStampMapper;

/**
 * Service for executing complex queries for {@link MPvpWatcherStamp} entities in the database.
 * The main input is a {@link MPvpWatcherStampCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPvpWatcherStampDTO} or a {@link Page} of {@link MPvpWatcherStampDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPvpWatcherStampQueryService extends QueryService<MPvpWatcherStamp> {

    private final Logger log = LoggerFactory.getLogger(MPvpWatcherStampQueryService.class);

    private final MPvpWatcherStampRepository mPvpWatcherStampRepository;

    private final MPvpWatcherStampMapper mPvpWatcherStampMapper;

    public MPvpWatcherStampQueryService(MPvpWatcherStampRepository mPvpWatcherStampRepository, MPvpWatcherStampMapper mPvpWatcherStampMapper) {
        this.mPvpWatcherStampRepository = mPvpWatcherStampRepository;
        this.mPvpWatcherStampMapper = mPvpWatcherStampMapper;
    }

    /**
     * Return a {@link List} of {@link MPvpWatcherStampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPvpWatcherStampDTO> findByCriteria(MPvpWatcherStampCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPvpWatcherStamp> specification = createSpecification(criteria);
        return mPvpWatcherStampMapper.toDto(mPvpWatcherStampRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPvpWatcherStampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpWatcherStampDTO> findByCriteria(MPvpWatcherStampCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPvpWatcherStamp> specification = createSpecification(criteria);
        return mPvpWatcherStampRepository.findAll(specification, page)
            .map(mPvpWatcherStampMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPvpWatcherStampCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPvpWatcherStamp> specification = createSpecification(criteria);
        return mPvpWatcherStampRepository.count(specification);
    }

    /**
     * Function to convert MPvpWatcherStampCriteria to a {@link Specification}.
     */
    private Specification<MPvpWatcherStamp> createSpecification(MPvpWatcherStampCriteria criteria) {
        Specification<MPvpWatcherStamp> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPvpWatcherStamp_.id));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MPvpWatcherStamp_.orderNum));
            }
            if (criteria.getMasterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMasterId(), MPvpWatcherStamp_.masterId));
            }
        }
        return specification;
    }
}
