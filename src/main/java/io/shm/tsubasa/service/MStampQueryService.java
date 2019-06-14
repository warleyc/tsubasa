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

import io.shm.tsubasa.domain.MStamp;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MStampRepository;
import io.shm.tsubasa.service.dto.MStampCriteria;
import io.shm.tsubasa.service.dto.MStampDTO;
import io.shm.tsubasa.service.mapper.MStampMapper;

/**
 * Service for executing complex queries for {@link MStamp} entities in the database.
 * The main input is a {@link MStampCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MStampDTO} or a {@link Page} of {@link MStampDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MStampQueryService extends QueryService<MStamp> {

    private final Logger log = LoggerFactory.getLogger(MStampQueryService.class);

    private final MStampRepository mStampRepository;

    private final MStampMapper mStampMapper;

    public MStampQueryService(MStampRepository mStampRepository, MStampMapper mStampMapper) {
        this.mStampRepository = mStampRepository;
        this.mStampMapper = mStampMapper;
    }

    /**
     * Return a {@link List} of {@link MStampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MStampDTO> findByCriteria(MStampCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MStamp> specification = createSpecification(criteria);
        return mStampMapper.toDto(mStampRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MStampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MStampDTO> findByCriteria(MStampCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MStamp> specification = createSpecification(criteria);
        return mStampRepository.findAll(specification, page)
            .map(mStampMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MStampCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MStamp> specification = createSpecification(criteria);
        return mStampRepository.count(specification);
    }

    /**
     * Function to convert MStampCriteria to a {@link Specification}.
     */
    private Specification<MStamp> createSpecification(MStampCriteria criteria) {
        Specification<MStamp> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MStamp_.id));
            }
        }
        return specification;
    }
}
