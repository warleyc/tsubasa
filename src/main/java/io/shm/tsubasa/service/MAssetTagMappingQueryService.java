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

import io.shm.tsubasa.domain.MAssetTagMapping;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MAssetTagMappingRepository;
import io.shm.tsubasa.service.dto.MAssetTagMappingCriteria;
import io.shm.tsubasa.service.dto.MAssetTagMappingDTO;
import io.shm.tsubasa.service.mapper.MAssetTagMappingMapper;

/**
 * Service for executing complex queries for {@link MAssetTagMapping} entities in the database.
 * The main input is a {@link MAssetTagMappingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MAssetTagMappingDTO} or a {@link Page} of {@link MAssetTagMappingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MAssetTagMappingQueryService extends QueryService<MAssetTagMapping> {

    private final Logger log = LoggerFactory.getLogger(MAssetTagMappingQueryService.class);

    private final MAssetTagMappingRepository mAssetTagMappingRepository;

    private final MAssetTagMappingMapper mAssetTagMappingMapper;

    public MAssetTagMappingQueryService(MAssetTagMappingRepository mAssetTagMappingRepository, MAssetTagMappingMapper mAssetTagMappingMapper) {
        this.mAssetTagMappingRepository = mAssetTagMappingRepository;
        this.mAssetTagMappingMapper = mAssetTagMappingMapper;
    }

    /**
     * Return a {@link List} of {@link MAssetTagMappingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MAssetTagMappingDTO> findByCriteria(MAssetTagMappingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MAssetTagMapping> specification = createSpecification(criteria);
        return mAssetTagMappingMapper.toDto(mAssetTagMappingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MAssetTagMappingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MAssetTagMappingDTO> findByCriteria(MAssetTagMappingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MAssetTagMapping> specification = createSpecification(criteria);
        return mAssetTagMappingRepository.findAll(specification, page)
            .map(mAssetTagMappingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MAssetTagMappingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MAssetTagMapping> specification = createSpecification(criteria);
        return mAssetTagMappingRepository.count(specification);
    }

    /**
     * Function to convert MAssetTagMappingCriteria to a {@link Specification}.
     */
    private Specification<MAssetTagMapping> createSpecification(MAssetTagMappingCriteria criteria) {
        Specification<MAssetTagMapping> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MAssetTagMapping_.id));
            }
            if (criteria.getTag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTag(), MAssetTagMapping_.tag));
            }
        }
        return specification;
    }
}
