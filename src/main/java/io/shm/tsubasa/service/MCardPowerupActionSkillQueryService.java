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

import io.shm.tsubasa.domain.MCardPowerupActionSkill;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCardPowerupActionSkillRepository;
import io.shm.tsubasa.service.dto.MCardPowerupActionSkillCriteria;
import io.shm.tsubasa.service.dto.MCardPowerupActionSkillDTO;
import io.shm.tsubasa.service.mapper.MCardPowerupActionSkillMapper;

/**
 * Service for executing complex queries for {@link MCardPowerupActionSkill} entities in the database.
 * The main input is a {@link MCardPowerupActionSkillCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCardPowerupActionSkillDTO} or a {@link Page} of {@link MCardPowerupActionSkillDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCardPowerupActionSkillQueryService extends QueryService<MCardPowerupActionSkill> {

    private final Logger log = LoggerFactory.getLogger(MCardPowerupActionSkillQueryService.class);

    private final MCardPowerupActionSkillRepository mCardPowerupActionSkillRepository;

    private final MCardPowerupActionSkillMapper mCardPowerupActionSkillMapper;

    public MCardPowerupActionSkillQueryService(MCardPowerupActionSkillRepository mCardPowerupActionSkillRepository, MCardPowerupActionSkillMapper mCardPowerupActionSkillMapper) {
        this.mCardPowerupActionSkillRepository = mCardPowerupActionSkillRepository;
        this.mCardPowerupActionSkillMapper = mCardPowerupActionSkillMapper;
    }

    /**
     * Return a {@link List} of {@link MCardPowerupActionSkillDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCardPowerupActionSkillDTO> findByCriteria(MCardPowerupActionSkillCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCardPowerupActionSkill> specification = createSpecification(criteria);
        return mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkillRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCardPowerupActionSkillDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardPowerupActionSkillDTO> findByCriteria(MCardPowerupActionSkillCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCardPowerupActionSkill> specification = createSpecification(criteria);
        return mCardPowerupActionSkillRepository.findAll(specification, page)
            .map(mCardPowerupActionSkillMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCardPowerupActionSkillCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCardPowerupActionSkill> specification = createSpecification(criteria);
        return mCardPowerupActionSkillRepository.count(specification);
    }

    /**
     * Function to convert MCardPowerupActionSkillCriteria to a {@link Specification}.
     */
    private Specification<MCardPowerupActionSkill> createSpecification(MCardPowerupActionSkillCriteria criteria) {
        Specification<MCardPowerupActionSkill> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCardPowerupActionSkill_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MCardPowerupActionSkill_.rarity));
            }
            if (criteria.getAttribute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAttribute(), MCardPowerupActionSkill_.attribute));
            }
            if (criteria.getActionRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionRarity(), MCardPowerupActionSkill_.actionRarity));
            }
            if (criteria.getGainActionExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGainActionExp(), MCardPowerupActionSkill_.gainActionExp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MCardPowerupActionSkill_.coin));
            }
            if (criteria.getSellMedalId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSellMedalId(), MCardPowerupActionSkill_.sellMedalId));
            }
            if (criteria.getThumbnailAssetsId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThumbnailAssetsId(), MCardPowerupActionSkill_.thumbnailAssetsId));
            }
            if (criteria.getCardIllustAssetsId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCardIllustAssetsId(), MCardPowerupActionSkill_.cardIllustAssetsId));
            }
            if (criteria.getTargetActionCommandType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetActionCommandType(), MCardPowerupActionSkill_.targetActionCommandType));
            }
            if (criteria.getTargetCharacterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharacterId(), MCardPowerupActionSkill_.targetCharacterId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MCardPowerupActionSkill_.id, JoinType.LEFT).get(MCardThumbnailAssets_.id)));
            }
        }
        return specification;
    }
}
