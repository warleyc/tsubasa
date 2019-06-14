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

import io.shm.tsubasa.domain.MMasterVersion;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMasterVersionRepository;
import io.shm.tsubasa.service.dto.MMasterVersionCriteria;
import io.shm.tsubasa.service.dto.MMasterVersionDTO;
import io.shm.tsubasa.service.mapper.MMasterVersionMapper;

/**
 * Service for executing complex queries for {@link MMasterVersion} entities in the database.
 * The main input is a {@link MMasterVersionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMasterVersionDTO} or a {@link Page} of {@link MMasterVersionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMasterVersionQueryService extends QueryService<MMasterVersion> {

    private final Logger log = LoggerFactory.getLogger(MMasterVersionQueryService.class);

    private final MMasterVersionRepository mMasterVersionRepository;

    private final MMasterVersionMapper mMasterVersionMapper;

    public MMasterVersionQueryService(MMasterVersionRepository mMasterVersionRepository, MMasterVersionMapper mMasterVersionMapper) {
        this.mMasterVersionRepository = mMasterVersionRepository;
        this.mMasterVersionMapper = mMasterVersionMapper;
    }

    /**
     * Return a {@link List} of {@link MMasterVersionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMasterVersionDTO> findByCriteria(MMasterVersionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMasterVersion> specification = createSpecification(criteria);
        return mMasterVersionMapper.toDto(mMasterVersionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMasterVersionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMasterVersionDTO> findByCriteria(MMasterVersionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMasterVersion> specification = createSpecification(criteria);
        return mMasterVersionRepository.findAll(specification, page)
            .map(mMasterVersionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMasterVersionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMasterVersion> specification = createSpecification(criteria);
        return mMasterVersionRepository.count(specification);
    }

    /**
     * Function to convert MMasterVersionCriteria to a {@link Specification}.
     */
    private Specification<MMasterVersion> createSpecification(MMasterVersionCriteria criteria) {
        Specification<MMasterVersion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMasterVersion_.id));
            }
            if (criteria.getVersion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVersion(), MMasterVersion_.version));
            }
            if (criteria.getSize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSize(), MMasterVersion_.size));
            }
        }
        return specification;
    }
}
