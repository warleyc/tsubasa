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

import io.shm.tsubasa.domain.MPvpPlayerStamp;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPvpPlayerStampRepository;
import io.shm.tsubasa.service.dto.MPvpPlayerStampCriteria;
import io.shm.tsubasa.service.dto.MPvpPlayerStampDTO;
import io.shm.tsubasa.service.mapper.MPvpPlayerStampMapper;

/**
 * Service for executing complex queries for {@link MPvpPlayerStamp} entities in the database.
 * The main input is a {@link MPvpPlayerStampCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPvpPlayerStampDTO} or a {@link Page} of {@link MPvpPlayerStampDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPvpPlayerStampQueryService extends QueryService<MPvpPlayerStamp> {

    private final Logger log = LoggerFactory.getLogger(MPvpPlayerStampQueryService.class);

    private final MPvpPlayerStampRepository mPvpPlayerStampRepository;

    private final MPvpPlayerStampMapper mPvpPlayerStampMapper;

    public MPvpPlayerStampQueryService(MPvpPlayerStampRepository mPvpPlayerStampRepository, MPvpPlayerStampMapper mPvpPlayerStampMapper) {
        this.mPvpPlayerStampRepository = mPvpPlayerStampRepository;
        this.mPvpPlayerStampMapper = mPvpPlayerStampMapper;
    }

    /**
     * Return a {@link List} of {@link MPvpPlayerStampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPvpPlayerStampDTO> findByCriteria(MPvpPlayerStampCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPvpPlayerStamp> specification = createSpecification(criteria);
        return mPvpPlayerStampMapper.toDto(mPvpPlayerStampRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPvpPlayerStampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpPlayerStampDTO> findByCriteria(MPvpPlayerStampCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPvpPlayerStamp> specification = createSpecification(criteria);
        return mPvpPlayerStampRepository.findAll(specification, page)
            .map(mPvpPlayerStampMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPvpPlayerStampCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPvpPlayerStamp> specification = createSpecification(criteria);
        return mPvpPlayerStampRepository.count(specification);
    }

    /**
     * Function to convert MPvpPlayerStampCriteria to a {@link Specification}.
     */
    private Specification<MPvpPlayerStamp> createSpecification(MPvpPlayerStampCriteria criteria) {
        Specification<MPvpPlayerStamp> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPvpPlayerStamp_.id));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MPvpPlayerStamp_.orderNum));
            }
            if (criteria.getMasterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMasterId(), MPvpPlayerStamp_.masterId));
            }
        }
        return specification;
    }
}
