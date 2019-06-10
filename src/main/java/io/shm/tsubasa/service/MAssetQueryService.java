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

import io.shm.tsubasa.domain.MAsset;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MAssetRepository;
import io.shm.tsubasa.service.dto.MAssetCriteria;
import io.shm.tsubasa.service.dto.MAssetDTO;
import io.shm.tsubasa.service.mapper.MAssetMapper;

/**
 * Service for executing complex queries for {@link MAsset} entities in the database.
 * The main input is a {@link MAssetCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MAssetDTO} or a {@link Page} of {@link MAssetDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MAssetQueryService extends QueryService<MAsset> {

    private final Logger log = LoggerFactory.getLogger(MAssetQueryService.class);

    private final MAssetRepository mAssetRepository;

    private final MAssetMapper mAssetMapper;

    public MAssetQueryService(MAssetRepository mAssetRepository, MAssetMapper mAssetMapper) {
        this.mAssetRepository = mAssetRepository;
        this.mAssetMapper = mAssetMapper;
    }

    /**
     * Return a {@link List} of {@link MAssetDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MAssetDTO> findByCriteria(MAssetCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MAsset> specification = createSpecification(criteria);
        return mAssetMapper.toDto(mAssetRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MAssetDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MAssetDTO> findByCriteria(MAssetCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MAsset> specification = createSpecification(criteria);
        return mAssetRepository.findAll(specification, page)
            .map(mAssetMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MAssetCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MAsset> specification = createSpecification(criteria);
        return mAssetRepository.count(specification);
    }

    /**
     * Function to convert MAssetCriteria to a {@link Specification}.
     */
    private Specification<MAsset> createSpecification(MAssetCriteria criteria) {
        Specification<MAsset> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MAsset_.id));
            }
            if (criteria.geti18n() != null) {
                specification = specification.and(buildRangeSpecification(criteria.geti18n(), MAsset_.i18n));
            }
            if (criteria.getHead() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHead(), MAsset_.head));
            }
            if (criteria.getSize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSize(), MAsset_.size));
            }
            if (criteria.getKey1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKey1(), MAsset_.key1));
            }
            if (criteria.getKey2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKey2(), MAsset_.key2));
            }
        }
        return specification;
    }
}
