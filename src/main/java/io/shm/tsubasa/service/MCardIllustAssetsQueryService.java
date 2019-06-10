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

import io.shm.tsubasa.domain.MCardIllustAssets;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCardIllustAssetsRepository;
import io.shm.tsubasa.service.dto.MCardIllustAssetsCriteria;
import io.shm.tsubasa.service.dto.MCardIllustAssetsDTO;
import io.shm.tsubasa.service.mapper.MCardIllustAssetsMapper;

/**
 * Service for executing complex queries for {@link MCardIllustAssets} entities in the database.
 * The main input is a {@link MCardIllustAssetsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCardIllustAssetsDTO} or a {@link Page} of {@link MCardIllustAssetsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCardIllustAssetsQueryService extends QueryService<MCardIllustAssets> {

    private final Logger log = LoggerFactory.getLogger(MCardIllustAssetsQueryService.class);

    private final MCardIllustAssetsRepository mCardIllustAssetsRepository;

    private final MCardIllustAssetsMapper mCardIllustAssetsMapper;

    public MCardIllustAssetsQueryService(MCardIllustAssetsRepository mCardIllustAssetsRepository, MCardIllustAssetsMapper mCardIllustAssetsMapper) {
        this.mCardIllustAssetsRepository = mCardIllustAssetsRepository;
        this.mCardIllustAssetsMapper = mCardIllustAssetsMapper;
    }

    /**
     * Return a {@link List} of {@link MCardIllustAssetsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCardIllustAssetsDTO> findByCriteria(MCardIllustAssetsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCardIllustAssets> specification = createSpecification(criteria);
        return mCardIllustAssetsMapper.toDto(mCardIllustAssetsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCardIllustAssetsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardIllustAssetsDTO> findByCriteria(MCardIllustAssetsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCardIllustAssets> specification = createSpecification(criteria);
        return mCardIllustAssetsRepository.findAll(specification, page)
            .map(mCardIllustAssetsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCardIllustAssetsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCardIllustAssets> specification = createSpecification(criteria);
        return mCardIllustAssetsRepository.count(specification);
    }

    /**
     * Function to convert MCardIllustAssetsCriteria to a {@link Specification}.
     */
    private Specification<MCardIllustAssets> createSpecification(MCardIllustAssetsCriteria criteria) {
        Specification<MCardIllustAssets> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCardIllustAssets_.id));
            }
        }
        return specification;
    }
}
