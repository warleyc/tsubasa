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

import io.shm.tsubasa.domain.MEncountersBonus;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MEncountersBonusRepository;
import io.shm.tsubasa.service.dto.MEncountersBonusCriteria;
import io.shm.tsubasa.service.dto.MEncountersBonusDTO;
import io.shm.tsubasa.service.mapper.MEncountersBonusMapper;

/**
 * Service for executing complex queries for {@link MEncountersBonus} entities in the database.
 * The main input is a {@link MEncountersBonusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MEncountersBonusDTO} or a {@link Page} of {@link MEncountersBonusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MEncountersBonusQueryService extends QueryService<MEncountersBonus> {

    private final Logger log = LoggerFactory.getLogger(MEncountersBonusQueryService.class);

    private final MEncountersBonusRepository mEncountersBonusRepository;

    private final MEncountersBonusMapper mEncountersBonusMapper;

    public MEncountersBonusQueryService(MEncountersBonusRepository mEncountersBonusRepository, MEncountersBonusMapper mEncountersBonusMapper) {
        this.mEncountersBonusRepository = mEncountersBonusRepository;
        this.mEncountersBonusMapper = mEncountersBonusMapper;
    }

    /**
     * Return a {@link List} of {@link MEncountersBonusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MEncountersBonusDTO> findByCriteria(MEncountersBonusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MEncountersBonus> specification = createSpecification(criteria);
        return mEncountersBonusMapper.toDto(mEncountersBonusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MEncountersBonusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MEncountersBonusDTO> findByCriteria(MEncountersBonusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MEncountersBonus> specification = createSpecification(criteria);
        return mEncountersBonusRepository.findAll(specification, page)
            .map(mEncountersBonusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MEncountersBonusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MEncountersBonus> specification = createSpecification(criteria);
        return mEncountersBonusRepository.count(specification);
    }

    /**
     * Function to convert MEncountersBonusCriteria to a {@link Specification}.
     */
    private Specification<MEncountersBonus> createSpecification(MEncountersBonusCriteria criteria) {
        Specification<MEncountersBonus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MEncountersBonus_.id));
            }
            if (criteria.getEncountersType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEncountersType(), MEncountersBonus_.encountersType));
            }
            if (criteria.getIsSkillSuccess() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSkillSuccess(), MEncountersBonus_.isSkillSuccess));
            }
            if (criteria.getThreshold() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThreshold(), MEncountersBonus_.threshold));
            }
            if (criteria.getProbabilityBonusValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProbabilityBonusValue(), MEncountersBonus_.probabilityBonusValue));
            }
        }
        return specification;
    }
}
