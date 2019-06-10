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

import io.shm.tsubasa.domain.MActionRarity;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MActionRarityRepository;
import io.shm.tsubasa.service.dto.MActionRarityCriteria;
import io.shm.tsubasa.service.dto.MActionRarityDTO;
import io.shm.tsubasa.service.mapper.MActionRarityMapper;

/**
 * Service for executing complex queries for {@link MActionRarity} entities in the database.
 * The main input is a {@link MActionRarityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MActionRarityDTO} or a {@link Page} of {@link MActionRarityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MActionRarityQueryService extends QueryService<MActionRarity> {

    private final Logger log = LoggerFactory.getLogger(MActionRarityQueryService.class);

    private final MActionRarityRepository mActionRarityRepository;

    private final MActionRarityMapper mActionRarityMapper;

    public MActionRarityQueryService(MActionRarityRepository mActionRarityRepository, MActionRarityMapper mActionRarityMapper) {
        this.mActionRarityRepository = mActionRarityRepository;
        this.mActionRarityMapper = mActionRarityMapper;
    }

    /**
     * Return a {@link List} of {@link MActionRarityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MActionRarityDTO> findByCriteria(MActionRarityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MActionRarity> specification = createSpecification(criteria);
        return mActionRarityMapper.toDto(mActionRarityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MActionRarityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionRarityDTO> findByCriteria(MActionRarityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MActionRarity> specification = createSpecification(criteria);
        return mActionRarityRepository.findAll(specification, page)
            .map(mActionRarityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MActionRarityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MActionRarity> specification = createSpecification(criteria);
        return mActionRarityRepository.count(specification);
    }

    /**
     * Function to convert MActionRarityCriteria to a {@link Specification}.
     */
    private Specification<MActionRarity> createSpecification(MActionRarityCriteria criteria) {
        Specification<MActionRarity> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MActionRarity_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MActionRarity_.rarity));
            }
        }
        return specification;
    }
}
