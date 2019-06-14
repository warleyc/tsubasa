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

import io.shm.tsubasa.domain.MTeamEffectRarity;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTeamEffectRarityRepository;
import io.shm.tsubasa.service.dto.MTeamEffectRarityCriteria;
import io.shm.tsubasa.service.dto.MTeamEffectRarityDTO;
import io.shm.tsubasa.service.mapper.MTeamEffectRarityMapper;

/**
 * Service for executing complex queries for {@link MTeamEffectRarity} entities in the database.
 * The main input is a {@link MTeamEffectRarityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTeamEffectRarityDTO} or a {@link Page} of {@link MTeamEffectRarityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTeamEffectRarityQueryService extends QueryService<MTeamEffectRarity> {

    private final Logger log = LoggerFactory.getLogger(MTeamEffectRarityQueryService.class);

    private final MTeamEffectRarityRepository mTeamEffectRarityRepository;

    private final MTeamEffectRarityMapper mTeamEffectRarityMapper;

    public MTeamEffectRarityQueryService(MTeamEffectRarityRepository mTeamEffectRarityRepository, MTeamEffectRarityMapper mTeamEffectRarityMapper) {
        this.mTeamEffectRarityRepository = mTeamEffectRarityRepository;
        this.mTeamEffectRarityMapper = mTeamEffectRarityMapper;
    }

    /**
     * Return a {@link List} of {@link MTeamEffectRarityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTeamEffectRarityDTO> findByCriteria(MTeamEffectRarityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTeamEffectRarity> specification = createSpecification(criteria);
        return mTeamEffectRarityMapper.toDto(mTeamEffectRarityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTeamEffectRarityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTeamEffectRarityDTO> findByCriteria(MTeamEffectRarityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTeamEffectRarity> specification = createSpecification(criteria);
        return mTeamEffectRarityRepository.findAll(specification, page)
            .map(mTeamEffectRarityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTeamEffectRarityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTeamEffectRarity> specification = createSpecification(criteria);
        return mTeamEffectRarityRepository.count(specification);
    }

    /**
     * Function to convert MTeamEffectRarityCriteria to a {@link Specification}.
     */
    private Specification<MTeamEffectRarity> createSpecification(MTeamEffectRarityCriteria criteria) {
        Specification<MTeamEffectRarity> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTeamEffectRarity_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MTeamEffectRarity_.rarity));
            }
            if (criteria.getMaxLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxLevel(), MTeamEffectRarity_.maxLevel));
            }
        }
        return specification;
    }
}
