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

import io.shm.tsubasa.domain.MMovieAsset;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMovieAssetRepository;
import io.shm.tsubasa.service.dto.MMovieAssetCriteria;
import io.shm.tsubasa.service.dto.MMovieAssetDTO;
import io.shm.tsubasa.service.mapper.MMovieAssetMapper;

/**
 * Service for executing complex queries for {@link MMovieAsset} entities in the database.
 * The main input is a {@link MMovieAssetCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMovieAssetDTO} or a {@link Page} of {@link MMovieAssetDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMovieAssetQueryService extends QueryService<MMovieAsset> {

    private final Logger log = LoggerFactory.getLogger(MMovieAssetQueryService.class);

    private final MMovieAssetRepository mMovieAssetRepository;

    private final MMovieAssetMapper mMovieAssetMapper;

    public MMovieAssetQueryService(MMovieAssetRepository mMovieAssetRepository, MMovieAssetMapper mMovieAssetMapper) {
        this.mMovieAssetRepository = mMovieAssetRepository;
        this.mMovieAssetMapper = mMovieAssetMapper;
    }

    /**
     * Return a {@link List} of {@link MMovieAssetDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMovieAssetDTO> findByCriteria(MMovieAssetCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMovieAsset> specification = createSpecification(criteria);
        return mMovieAssetMapper.toDto(mMovieAssetRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMovieAssetDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMovieAssetDTO> findByCriteria(MMovieAssetCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMovieAsset> specification = createSpecification(criteria);
        return mMovieAssetRepository.findAll(specification, page)
            .map(mMovieAssetMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMovieAssetCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMovieAsset> specification = createSpecification(criteria);
        return mMovieAssetRepository.count(specification);
    }

    /**
     * Function to convert MMovieAssetCriteria to a {@link Specification}.
     */
    private Specification<MMovieAsset> createSpecification(MMovieAssetCriteria criteria) {
        Specification<MMovieAsset> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMovieAsset_.id));
            }
            if (criteria.getLang() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLang(), MMovieAsset_.lang));
            }
            if (criteria.getSize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSize(), MMovieAsset_.size));
            }
            if (criteria.getVersion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVersion(), MMovieAsset_.version));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getType(), MMovieAsset_.type));
            }
        }
        return specification;
    }
}
