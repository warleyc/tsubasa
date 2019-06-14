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

import io.shm.tsubasa.domain.MTrainingCard;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTrainingCardRepository;
import io.shm.tsubasa.service.dto.MTrainingCardCriteria;
import io.shm.tsubasa.service.dto.MTrainingCardDTO;
import io.shm.tsubasa.service.mapper.MTrainingCardMapper;

/**
 * Service for executing complex queries for {@link MTrainingCard} entities in the database.
 * The main input is a {@link MTrainingCardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTrainingCardDTO} or a {@link Page} of {@link MTrainingCardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTrainingCardQueryService extends QueryService<MTrainingCard> {

    private final Logger log = LoggerFactory.getLogger(MTrainingCardQueryService.class);

    private final MTrainingCardRepository mTrainingCardRepository;

    private final MTrainingCardMapper mTrainingCardMapper;

    public MTrainingCardQueryService(MTrainingCardRepository mTrainingCardRepository, MTrainingCardMapper mTrainingCardMapper) {
        this.mTrainingCardRepository = mTrainingCardRepository;
        this.mTrainingCardMapper = mTrainingCardMapper;
    }

    /**
     * Return a {@link List} of {@link MTrainingCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTrainingCardDTO> findByCriteria(MTrainingCardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTrainingCard> specification = createSpecification(criteria);
        return mTrainingCardMapper.toDto(mTrainingCardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTrainingCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTrainingCardDTO> findByCriteria(MTrainingCardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTrainingCard> specification = createSpecification(criteria);
        return mTrainingCardRepository.findAll(specification, page)
            .map(mTrainingCardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTrainingCardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTrainingCard> specification = createSpecification(criteria);
        return mTrainingCardRepository.count(specification);
    }

    /**
     * Function to convert MTrainingCardCriteria to a {@link Specification}.
     */
    private Specification<MTrainingCard> createSpecification(MTrainingCardCriteria criteria) {
        Specification<MTrainingCard> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTrainingCard_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MTrainingCard_.rarity));
            }
            if (criteria.getAttribute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAttribute(), MTrainingCard_.attribute));
            }
            if (criteria.getGainExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGainExp(), MTrainingCard_.gainExp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MTrainingCard_.coin));
            }
            if (criteria.getSellMedalId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSellMedalId(), MTrainingCard_.sellMedalId));
            }
            if (criteria.getPlusDribble() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusDribble(), MTrainingCard_.plusDribble));
            }
            if (criteria.getPlusShoot() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusShoot(), MTrainingCard_.plusShoot));
            }
            if (criteria.getPlusPass() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusPass(), MTrainingCard_.plusPass));
            }
            if (criteria.getPlusTackle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusTackle(), MTrainingCard_.plusTackle));
            }
            if (criteria.getPlusBlock() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusBlock(), MTrainingCard_.plusBlock));
            }
            if (criteria.getPlusIntercept() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusIntercept(), MTrainingCard_.plusIntercept));
            }
            if (criteria.getPlusSpeed() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusSpeed(), MTrainingCard_.plusSpeed));
            }
            if (criteria.getPlusPower() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusPower(), MTrainingCard_.plusPower));
            }
            if (criteria.getPlusTechnique() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusTechnique(), MTrainingCard_.plusTechnique));
            }
            if (criteria.getPlusPunching() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusPunching(), MTrainingCard_.plusPunching));
            }
            if (criteria.getPlusCatching() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusCatching(), MTrainingCard_.plusCatching));
            }
            if (criteria.getThumbnailAssetsId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThumbnailAssetsId(), MTrainingCard_.thumbnailAssetsId));
            }
            if (criteria.getCardIllustAssetsId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCardIllustAssetsId(), MTrainingCard_.cardIllustAssetsId));
            }
            if (criteria.getMcardthumbnailassetsId() != null) {
                specification = specification.and(buildSpecification(criteria.getMcardthumbnailassetsId(),
                    root -> root.join(MTrainingCard_.mcardthumbnailassets, JoinType.LEFT).get(MCardThumbnailAssets_.id)));
            }
        }
        return specification;
    }
}
