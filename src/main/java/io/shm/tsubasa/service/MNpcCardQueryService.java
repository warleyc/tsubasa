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

import io.shm.tsubasa.domain.MNpcCard;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MNpcCardRepository;
import io.shm.tsubasa.service.dto.MNpcCardCriteria;
import io.shm.tsubasa.service.dto.MNpcCardDTO;
import io.shm.tsubasa.service.mapper.MNpcCardMapper;

/**
 * Service for executing complex queries for {@link MNpcCard} entities in the database.
 * The main input is a {@link MNpcCardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MNpcCardDTO} or a {@link Page} of {@link MNpcCardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MNpcCardQueryService extends QueryService<MNpcCard> {

    private final Logger log = LoggerFactory.getLogger(MNpcCardQueryService.class);

    private final MNpcCardRepository mNpcCardRepository;

    private final MNpcCardMapper mNpcCardMapper;

    public MNpcCardQueryService(MNpcCardRepository mNpcCardRepository, MNpcCardMapper mNpcCardMapper) {
        this.mNpcCardRepository = mNpcCardRepository;
        this.mNpcCardMapper = mNpcCardMapper;
    }

    /**
     * Return a {@link List} of {@link MNpcCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MNpcCardDTO> findByCriteria(MNpcCardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MNpcCard> specification = createSpecification(criteria);
        return mNpcCardMapper.toDto(mNpcCardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MNpcCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MNpcCardDTO> findByCriteria(MNpcCardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MNpcCard> specification = createSpecification(criteria);
        return mNpcCardRepository.findAll(specification, page)
            .map(mNpcCardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MNpcCardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MNpcCard> specification = createSpecification(criteria);
        return mNpcCardRepository.count(specification);
    }

    /**
     * Function to convert MNpcCardCriteria to a {@link Specification}.
     */
    private Specification<MNpcCard> createSpecification(MNpcCardCriteria criteria) {
        Specification<MNpcCard> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MNpcCard_.id));
            }
            if (criteria.getCharacterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCharacterId(), MNpcCard_.characterId));
            }
            if (criteria.getTeamId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamId(), MNpcCard_.teamId));
            }
            if (criteria.getNationalityId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNationalityId(), MNpcCard_.nationalityId));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MNpcCard_.rarity));
            }
            if (criteria.getAttribute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAttribute(), MNpcCard_.attribute));
            }
            if (criteria.getModelId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModelId(), MNpcCard_.modelId));
            }
            if (criteria.getLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevel(), MNpcCard_.level));
            }
            if (criteria.getThumbnailAssetsId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThumbnailAssetsId(), MNpcCard_.thumbnailAssetsId));
            }
            if (criteria.getCardIllustAssetsId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCardIllustAssetsId(), MNpcCard_.cardIllustAssetsId));
            }
            if (criteria.getPlayableAssetsId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlayableAssetsId(), MNpcCard_.playableAssetsId));
            }
            if (criteria.getTeamEffectId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamEffectId(), MNpcCard_.teamEffectId));
            }
            if (criteria.getTeamEffectLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamEffectLevel(), MNpcCard_.teamEffectLevel));
            }
            if (criteria.getTriggerEffectId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerEffectId(), MNpcCard_.triggerEffectId));
            }
            if (criteria.getAction1Id() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAction1Id(), MNpcCard_.action1Id));
            }
            if (criteria.getAction1Level() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAction1Level(), MNpcCard_.action1Level));
            }
            if (criteria.getAction2Id() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAction2Id(), MNpcCard_.action2Id));
            }
            if (criteria.getAction2Level() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAction2Level(), MNpcCard_.action2Level));
            }
            if (criteria.getAction3Id() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAction3Id(), MNpcCard_.action3Id));
            }
            if (criteria.getAction3Level() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAction3Level(), MNpcCard_.action3Level));
            }
            if (criteria.getAction4Id() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAction4Id(), MNpcCard_.action4Id));
            }
            if (criteria.getAction4Level() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAction4Level(), MNpcCard_.action4Level));
            }
            if (criteria.getAction5Id() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAction5Id(), MNpcCard_.action5Id));
            }
            if (criteria.getAction5Level() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAction5Level(), MNpcCard_.action5Level));
            }
            if (criteria.getStamina() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStamina(), MNpcCard_.stamina));
            }
            if (criteria.getDribble() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDribble(), MNpcCard_.dribble));
            }
            if (criteria.getShoot() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShoot(), MNpcCard_.shoot));
            }
            if (criteria.getBallPass() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBallPass(), MNpcCard_.ballPass));
            }
            if (criteria.getTackle() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTackle(), MNpcCard_.tackle));
            }
            if (criteria.getBlock() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBlock(), MNpcCard_.block));
            }
            if (criteria.getIntercept() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIntercept(), MNpcCard_.intercept));
            }
            if (criteria.getSpeed() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpeed(), MNpcCard_.speed));
            }
            if (criteria.getPower() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPower(), MNpcCard_.power));
            }
            if (criteria.getTechnique() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTechnique(), MNpcCard_.technique));
            }
            if (criteria.getPunching() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPunching(), MNpcCard_.punching));
            }
            if (criteria.getCatching() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCatching(), MNpcCard_.catching));
            }
            if (criteria.getHighBallBonus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHighBallBonus(), MNpcCard_.highBallBonus));
            }
            if (criteria.getLowBallBonus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLowBallBonus(), MNpcCard_.lowBallBonus));
            }
            if (criteria.getPersonalityId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPersonalityId(), MNpcCard_.personalityId));
            }
            if (criteria.getUniformNo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformNo(), MNpcCard_.uniformNo));
            }
            if (criteria.getLevelGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevelGroupId(), MNpcCard_.levelGroupId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MNpcCard_.id, JoinType.LEFT).get(MCharacter_.id)));
            }
        }
        return specification;
    }
}
