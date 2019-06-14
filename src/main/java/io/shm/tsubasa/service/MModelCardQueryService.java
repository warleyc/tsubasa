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

import io.shm.tsubasa.domain.MModelCard;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MModelCardRepository;
import io.shm.tsubasa.service.dto.MModelCardCriteria;
import io.shm.tsubasa.service.dto.MModelCardDTO;
import io.shm.tsubasa.service.mapper.MModelCardMapper;

/**
 * Service for executing complex queries for {@link MModelCard} entities in the database.
 * The main input is a {@link MModelCardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MModelCardDTO} or a {@link Page} of {@link MModelCardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MModelCardQueryService extends QueryService<MModelCard> {

    private final Logger log = LoggerFactory.getLogger(MModelCardQueryService.class);

    private final MModelCardRepository mModelCardRepository;

    private final MModelCardMapper mModelCardMapper;

    public MModelCardQueryService(MModelCardRepository mModelCardRepository, MModelCardMapper mModelCardMapper) {
        this.mModelCardRepository = mModelCardRepository;
        this.mModelCardMapper = mModelCardMapper;
    }

    /**
     * Return a {@link List} of {@link MModelCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MModelCardDTO> findByCriteria(MModelCardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MModelCard> specification = createSpecification(criteria);
        return mModelCardMapper.toDto(mModelCardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MModelCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelCardDTO> findByCriteria(MModelCardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MModelCard> specification = createSpecification(criteria);
        return mModelCardRepository.findAll(specification, page)
            .map(mModelCardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MModelCardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MModelCard> specification = createSpecification(criteria);
        return mModelCardRepository.count(specification);
    }

    /**
     * Function to convert MModelCardCriteria to a {@link Specification}.
     */
    private Specification<MModelCard> createSpecification(MModelCardCriteria criteria) {
        Specification<MModelCard> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MModelCard_.id));
            }
            if (criteria.getHairModel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHairModel(), MModelCard_.hairModel));
            }
            if (criteria.getHairTexture() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHairTexture(), MModelCard_.hairTexture));
            }
            if (criteria.getHeadModel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeadModel(), MModelCard_.headModel));
            }
            if (criteria.getHeadTexture() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeadTexture(), MModelCard_.headTexture));
            }
            if (criteria.getSkinTexture() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSkinTexture(), MModelCard_.skinTexture));
            }
            if (criteria.getShoesModel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShoesModel(), MModelCard_.shoesModel));
            }
            if (criteria.getShoesTexture() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShoesTexture(), MModelCard_.shoesTexture));
            }
            if (criteria.getGlobeTexture() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGlobeTexture(), MModelCard_.globeTexture));
            }
            if (criteria.getUniqueModel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniqueModel(), MModelCard_.uniqueModel));
            }
            if (criteria.getUniqueTexture() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniqueTexture(), MModelCard_.uniqueTexture));
            }
            if (criteria.getDressingTypeUp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDressingTypeUp(), MModelCard_.dressingTypeUp));
            }
            if (criteria.getDressingTypeBottom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDressingTypeBottom(), MModelCard_.dressingTypeBottom));
            }
            if (criteria.getHeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeight(), MModelCard_.height));
            }
            if (criteria.getWidth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWidth(), MModelCard_.width));
            }
            if (criteria.getMPlayableCardId() != null) {
                specification = specification.and(buildSpecification(criteria.getMPlayableCardId(),
                    root -> root.join(MModelCard_.mPlayableCards, JoinType.LEFT).get(MPlayableCard_.id)));
            }
        }
        return specification;
    }
}
