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

import io.shm.tsubasa.domain.MHomeBanner;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MHomeBannerRepository;
import io.shm.tsubasa.service.dto.MHomeBannerCriteria;
import io.shm.tsubasa.service.dto.MHomeBannerDTO;
import io.shm.tsubasa.service.mapper.MHomeBannerMapper;

/**
 * Service for executing complex queries for {@link MHomeBanner} entities in the database.
 * The main input is a {@link MHomeBannerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MHomeBannerDTO} or a {@link Page} of {@link MHomeBannerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MHomeBannerQueryService extends QueryService<MHomeBanner> {

    private final Logger log = LoggerFactory.getLogger(MHomeBannerQueryService.class);

    private final MHomeBannerRepository mHomeBannerRepository;

    private final MHomeBannerMapper mHomeBannerMapper;

    public MHomeBannerQueryService(MHomeBannerRepository mHomeBannerRepository, MHomeBannerMapper mHomeBannerMapper) {
        this.mHomeBannerRepository = mHomeBannerRepository;
        this.mHomeBannerMapper = mHomeBannerMapper;
    }

    /**
     * Return a {@link List} of {@link MHomeBannerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MHomeBannerDTO> findByCriteria(MHomeBannerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MHomeBanner> specification = createSpecification(criteria);
        return mHomeBannerMapper.toDto(mHomeBannerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MHomeBannerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MHomeBannerDTO> findByCriteria(MHomeBannerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MHomeBanner> specification = createSpecification(criteria);
        return mHomeBannerRepository.findAll(specification, page)
            .map(mHomeBannerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MHomeBannerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MHomeBanner> specification = createSpecification(criteria);
        return mHomeBannerRepository.count(specification);
    }

    /**
     * Function to convert MHomeBannerCriteria to a {@link Specification}.
     */
    private Specification<MHomeBanner> createSpecification(MHomeBannerCriteria criteria) {
        Specification<MHomeBanner> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MHomeBanner_.id));
            }
            if (criteria.getBannerType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBannerType(), MHomeBanner_.bannerType));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MHomeBanner_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MHomeBanner_.endAt));
            }
            if (criteria.getLabelEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLabelEndAt(), MHomeBanner_.labelEndAt));
            }
            if (criteria.getSceneTransition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSceneTransition(), MHomeBanner_.sceneTransition));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MHomeBanner_.orderNum));
            }
            if (criteria.getAppealType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAppealType(), MHomeBanner_.appealType));
            }
            if (criteria.getAppealContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAppealContentId(), MHomeBanner_.appealContentId));
            }
        }
        return specification;
    }
}
