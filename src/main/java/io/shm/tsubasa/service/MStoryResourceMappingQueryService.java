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

import io.shm.tsubasa.domain.MStoryResourceMapping;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MStoryResourceMappingRepository;
import io.shm.tsubasa.service.dto.MStoryResourceMappingCriteria;
import io.shm.tsubasa.service.dto.MStoryResourceMappingDTO;
import io.shm.tsubasa.service.mapper.MStoryResourceMappingMapper;

/**
 * Service for executing complex queries for {@link MStoryResourceMapping} entities in the database.
 * The main input is a {@link MStoryResourceMappingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MStoryResourceMappingDTO} or a {@link Page} of {@link MStoryResourceMappingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MStoryResourceMappingQueryService extends QueryService<MStoryResourceMapping> {

    private final Logger log = LoggerFactory.getLogger(MStoryResourceMappingQueryService.class);

    private final MStoryResourceMappingRepository mStoryResourceMappingRepository;

    private final MStoryResourceMappingMapper mStoryResourceMappingMapper;

    public MStoryResourceMappingQueryService(MStoryResourceMappingRepository mStoryResourceMappingRepository, MStoryResourceMappingMapper mStoryResourceMappingMapper) {
        this.mStoryResourceMappingRepository = mStoryResourceMappingRepository;
        this.mStoryResourceMappingMapper = mStoryResourceMappingMapper;
    }

    /**
     * Return a {@link List} of {@link MStoryResourceMappingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MStoryResourceMappingDTO> findByCriteria(MStoryResourceMappingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MStoryResourceMapping> specification = createSpecification(criteria);
        return mStoryResourceMappingMapper.toDto(mStoryResourceMappingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MStoryResourceMappingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MStoryResourceMappingDTO> findByCriteria(MStoryResourceMappingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MStoryResourceMapping> specification = createSpecification(criteria);
        return mStoryResourceMappingRepository.findAll(specification, page)
            .map(mStoryResourceMappingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MStoryResourceMappingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MStoryResourceMapping> specification = createSpecification(criteria);
        return mStoryResourceMappingRepository.count(specification);
    }

    /**
     * Function to convert MStoryResourceMappingCriteria to a {@link Specification}.
     */
    private Specification<MStoryResourceMapping> createSpecification(MStoryResourceMappingCriteria criteria) {
        Specification<MStoryResourceMapping> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MStoryResourceMapping_.id));
            }
            if (criteria.getLang() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLang(), MStoryResourceMapping_.lang));
            }
        }
        return specification;
    }
}
