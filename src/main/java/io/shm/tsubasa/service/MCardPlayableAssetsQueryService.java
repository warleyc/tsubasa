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

import io.shm.tsubasa.domain.MCardPlayableAssets;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCardPlayableAssetsRepository;
import io.shm.tsubasa.service.dto.MCardPlayableAssetsCriteria;
import io.shm.tsubasa.service.dto.MCardPlayableAssetsDTO;
import io.shm.tsubasa.service.mapper.MCardPlayableAssetsMapper;

/**
 * Service for executing complex queries for {@link MCardPlayableAssets} entities in the database.
 * The main input is a {@link MCardPlayableAssetsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCardPlayableAssetsDTO} or a {@link Page} of {@link MCardPlayableAssetsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCardPlayableAssetsQueryService extends QueryService<MCardPlayableAssets> {

    private final Logger log = LoggerFactory.getLogger(MCardPlayableAssetsQueryService.class);

    private final MCardPlayableAssetsRepository mCardPlayableAssetsRepository;

    private final MCardPlayableAssetsMapper mCardPlayableAssetsMapper;

    public MCardPlayableAssetsQueryService(MCardPlayableAssetsRepository mCardPlayableAssetsRepository, MCardPlayableAssetsMapper mCardPlayableAssetsMapper) {
        this.mCardPlayableAssetsRepository = mCardPlayableAssetsRepository;
        this.mCardPlayableAssetsMapper = mCardPlayableAssetsMapper;
    }

    /**
     * Return a {@link List} of {@link MCardPlayableAssetsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCardPlayableAssetsDTO> findByCriteria(MCardPlayableAssetsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCardPlayableAssets> specification = createSpecification(criteria);
        return mCardPlayableAssetsMapper.toDto(mCardPlayableAssetsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCardPlayableAssetsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardPlayableAssetsDTO> findByCriteria(MCardPlayableAssetsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCardPlayableAssets> specification = createSpecification(criteria);
        return mCardPlayableAssetsRepository.findAll(specification, page)
            .map(mCardPlayableAssetsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCardPlayableAssetsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCardPlayableAssets> specification = createSpecification(criteria);
        return mCardPlayableAssetsRepository.count(specification);
    }

    /**
     * Function to convert MCardPlayableAssetsCriteria to a {@link Specification}.
     */
    private Specification<MCardPlayableAssets> createSpecification(MCardPlayableAssetsCriteria criteria) {
        Specification<MCardPlayableAssets> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCardPlayableAssets_.id));
            }
        }
        return specification;
    }
}
