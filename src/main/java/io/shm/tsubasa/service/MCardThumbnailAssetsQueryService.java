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

import io.shm.tsubasa.domain.MCardThumbnailAssets;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCardThumbnailAssetsRepository;
import io.shm.tsubasa.service.dto.MCardThumbnailAssetsCriteria;
import io.shm.tsubasa.service.dto.MCardThumbnailAssetsDTO;
import io.shm.tsubasa.service.mapper.MCardThumbnailAssetsMapper;

/**
 * Service for executing complex queries for {@link MCardThumbnailAssets} entities in the database.
 * The main input is a {@link MCardThumbnailAssetsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCardThumbnailAssetsDTO} or a {@link Page} of {@link MCardThumbnailAssetsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCardThumbnailAssetsQueryService extends QueryService<MCardThumbnailAssets> {

    private final Logger log = LoggerFactory.getLogger(MCardThumbnailAssetsQueryService.class);

    private final MCardThumbnailAssetsRepository mCardThumbnailAssetsRepository;

    private final MCardThumbnailAssetsMapper mCardThumbnailAssetsMapper;

    public MCardThumbnailAssetsQueryService(MCardThumbnailAssetsRepository mCardThumbnailAssetsRepository, MCardThumbnailAssetsMapper mCardThumbnailAssetsMapper) {
        this.mCardThumbnailAssetsRepository = mCardThumbnailAssetsRepository;
        this.mCardThumbnailAssetsMapper = mCardThumbnailAssetsMapper;
    }

    /**
     * Return a {@link List} of {@link MCardThumbnailAssetsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCardThumbnailAssetsDTO> findByCriteria(MCardThumbnailAssetsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCardThumbnailAssets> specification = createSpecification(criteria);
        return mCardThumbnailAssetsMapper.toDto(mCardThumbnailAssetsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCardThumbnailAssetsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardThumbnailAssetsDTO> findByCriteria(MCardThumbnailAssetsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCardThumbnailAssets> specification = createSpecification(criteria);
        return mCardThumbnailAssetsRepository.findAll(specification, page)
            .map(mCardThumbnailAssetsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCardThumbnailAssetsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCardThumbnailAssets> specification = createSpecification(criteria);
        return mCardThumbnailAssetsRepository.count(specification);
    }

    /**
     * Function to convert MCardThumbnailAssetsCriteria to a {@link Specification}.
     */
    private Specification<MCardThumbnailAssets> createSpecification(MCardThumbnailAssetsCriteria criteria) {
        Specification<MCardThumbnailAssets> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCardThumbnailAssets_.id));
            }
            if (criteria.getMCardPowerupActionSkillId() != null) {
                specification = specification.and(buildSpecification(criteria.getMCardPowerupActionSkillId(),
                    root -> root.join(MCardThumbnailAssets_.mCardPowerupActionSkills, JoinType.LEFT).get(MCardPowerupActionSkill_.id)));
            }
            if (criteria.getMTrainingCardId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTrainingCardId(),
                    root -> root.join(MCardThumbnailAssets_.mTrainingCards, JoinType.LEFT).get(MTrainingCard_.id)));
            }
        }
        return specification;
    }
}
