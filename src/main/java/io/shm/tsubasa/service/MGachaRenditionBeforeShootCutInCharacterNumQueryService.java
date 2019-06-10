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

import io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInCharacterNum;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionBeforeShootCutInCharacterNumRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInCharacterNumCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInCharacterNumDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionBeforeShootCutInCharacterNumMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionBeforeShootCutInCharacterNum} entities in the database.
 * The main input is a {@link MGachaRenditionBeforeShootCutInCharacterNumCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionBeforeShootCutInCharacterNumDTO} or a {@link Page} of {@link MGachaRenditionBeforeShootCutInCharacterNumDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionBeforeShootCutInCharacterNumQueryService extends QueryService<MGachaRenditionBeforeShootCutInCharacterNum> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionBeforeShootCutInCharacterNumQueryService.class);

    private final MGachaRenditionBeforeShootCutInCharacterNumRepository mGachaRenditionBeforeShootCutInCharacterNumRepository;

    private final MGachaRenditionBeforeShootCutInCharacterNumMapper mGachaRenditionBeforeShootCutInCharacterNumMapper;

    public MGachaRenditionBeforeShootCutInCharacterNumQueryService(MGachaRenditionBeforeShootCutInCharacterNumRepository mGachaRenditionBeforeShootCutInCharacterNumRepository, MGachaRenditionBeforeShootCutInCharacterNumMapper mGachaRenditionBeforeShootCutInCharacterNumMapper) {
        this.mGachaRenditionBeforeShootCutInCharacterNumRepository = mGachaRenditionBeforeShootCutInCharacterNumRepository;
        this.mGachaRenditionBeforeShootCutInCharacterNumMapper = mGachaRenditionBeforeShootCutInCharacterNumMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionBeforeShootCutInCharacterNumDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionBeforeShootCutInCharacterNumDTO> findByCriteria(MGachaRenditionBeforeShootCutInCharacterNumCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionBeforeShootCutInCharacterNum> specification = createSpecification(criteria);
        return mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionBeforeShootCutInCharacterNumDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionBeforeShootCutInCharacterNumDTO> findByCriteria(MGachaRenditionBeforeShootCutInCharacterNumCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionBeforeShootCutInCharacterNum> specification = createSpecification(criteria);
        return mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll(specification, page)
            .map(mGachaRenditionBeforeShootCutInCharacterNumMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionBeforeShootCutInCharacterNumCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionBeforeShootCutInCharacterNum> specification = createSpecification(criteria);
        return mGachaRenditionBeforeShootCutInCharacterNumRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionBeforeShootCutInCharacterNumCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionBeforeShootCutInCharacterNum> createSpecification(MGachaRenditionBeforeShootCutInCharacterNumCriteria criteria) {
        Specification<MGachaRenditionBeforeShootCutInCharacterNum> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionBeforeShootCutInCharacterNum_.id));
            }
            if (criteria.getIsManySsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsManySsr(), MGachaRenditionBeforeShootCutInCharacterNum_.isManySsr));
            }
            if (criteria.getIsSsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSsr(), MGachaRenditionBeforeShootCutInCharacterNum_.isSsr));
            }
            if (criteria.getPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPattern(), MGachaRenditionBeforeShootCutInCharacterNum_.pattern));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionBeforeShootCutInCharacterNum_.weight));
            }
            if (criteria.getNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNum(), MGachaRenditionBeforeShootCutInCharacterNum_.num));
            }
        }
        return specification;
    }
}
