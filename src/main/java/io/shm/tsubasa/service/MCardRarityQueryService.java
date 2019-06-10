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

import io.shm.tsubasa.domain.MCardRarity;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCardRarityRepository;
import io.shm.tsubasa.service.dto.MCardRarityCriteria;
import io.shm.tsubasa.service.dto.MCardRarityDTO;
import io.shm.tsubasa.service.mapper.MCardRarityMapper;

/**
 * Service for executing complex queries for {@link MCardRarity} entities in the database.
 * The main input is a {@link MCardRarityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCardRarityDTO} or a {@link Page} of {@link MCardRarityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCardRarityQueryService extends QueryService<MCardRarity> {

    private final Logger log = LoggerFactory.getLogger(MCardRarityQueryService.class);

    private final MCardRarityRepository mCardRarityRepository;

    private final MCardRarityMapper mCardRarityMapper;

    public MCardRarityQueryService(MCardRarityRepository mCardRarityRepository, MCardRarityMapper mCardRarityMapper) {
        this.mCardRarityRepository = mCardRarityRepository;
        this.mCardRarityMapper = mCardRarityMapper;
    }

    /**
     * Return a {@link List} of {@link MCardRarityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCardRarityDTO> findByCriteria(MCardRarityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCardRarity> specification = createSpecification(criteria);
        return mCardRarityMapper.toDto(mCardRarityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCardRarityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardRarityDTO> findByCriteria(MCardRarityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCardRarity> specification = createSpecification(criteria);
        return mCardRarityRepository.findAll(specification, page)
            .map(mCardRarityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCardRarityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCardRarity> specification = createSpecification(criteria);
        return mCardRarityRepository.count(specification);
    }

    /**
     * Function to convert MCardRarityCriteria to a {@link Specification}.
     */
    private Specification<MCardRarity> createSpecification(MCardRarityCriteria criteria) {
        Specification<MCardRarity> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCardRarity_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MCardRarity_.rarity));
            }
        }
        return specification;
    }
}
