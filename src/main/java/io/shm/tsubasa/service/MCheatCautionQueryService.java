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

import io.shm.tsubasa.domain.MCheatCaution;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCheatCautionRepository;
import io.shm.tsubasa.service.dto.MCheatCautionCriteria;
import io.shm.tsubasa.service.dto.MCheatCautionDTO;
import io.shm.tsubasa.service.mapper.MCheatCautionMapper;

/**
 * Service for executing complex queries for {@link MCheatCaution} entities in the database.
 * The main input is a {@link MCheatCautionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCheatCautionDTO} or a {@link Page} of {@link MCheatCautionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCheatCautionQueryService extends QueryService<MCheatCaution> {

    private final Logger log = LoggerFactory.getLogger(MCheatCautionQueryService.class);

    private final MCheatCautionRepository mCheatCautionRepository;

    private final MCheatCautionMapper mCheatCautionMapper;

    public MCheatCautionQueryService(MCheatCautionRepository mCheatCautionRepository, MCheatCautionMapper mCheatCautionMapper) {
        this.mCheatCautionRepository = mCheatCautionRepository;
        this.mCheatCautionMapper = mCheatCautionMapper;
    }

    /**
     * Return a {@link List} of {@link MCheatCautionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCheatCautionDTO> findByCriteria(MCheatCautionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCheatCaution> specification = createSpecification(criteria);
        return mCheatCautionMapper.toDto(mCheatCautionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCheatCautionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCheatCautionDTO> findByCriteria(MCheatCautionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCheatCaution> specification = createSpecification(criteria);
        return mCheatCautionRepository.findAll(specification, page)
            .map(mCheatCautionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCheatCautionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCheatCaution> specification = createSpecification(criteria);
        return mCheatCautionRepository.count(specification);
    }

    /**
     * Function to convert MCheatCautionCriteria to a {@link Specification}.
     */
    private Specification<MCheatCaution> createSpecification(MCheatCautionCriteria criteria) {
        Specification<MCheatCaution> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCheatCaution_.id));
            }
            if (criteria.getCaution() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCaution(), MCheatCaution_.caution));
            }
        }
        return specification;
    }
}
