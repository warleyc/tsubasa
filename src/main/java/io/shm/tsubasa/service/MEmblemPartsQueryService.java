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

import io.shm.tsubasa.domain.MEmblemParts;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MEmblemPartsRepository;
import io.shm.tsubasa.service.dto.MEmblemPartsCriteria;
import io.shm.tsubasa.service.dto.MEmblemPartsDTO;
import io.shm.tsubasa.service.mapper.MEmblemPartsMapper;

/**
 * Service for executing complex queries for {@link MEmblemParts} entities in the database.
 * The main input is a {@link MEmblemPartsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MEmblemPartsDTO} or a {@link Page} of {@link MEmblemPartsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MEmblemPartsQueryService extends QueryService<MEmblemParts> {

    private final Logger log = LoggerFactory.getLogger(MEmblemPartsQueryService.class);

    private final MEmblemPartsRepository mEmblemPartsRepository;

    private final MEmblemPartsMapper mEmblemPartsMapper;

    public MEmblemPartsQueryService(MEmblemPartsRepository mEmblemPartsRepository, MEmblemPartsMapper mEmblemPartsMapper) {
        this.mEmblemPartsRepository = mEmblemPartsRepository;
        this.mEmblemPartsMapper = mEmblemPartsMapper;
    }

    /**
     * Return a {@link List} of {@link MEmblemPartsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MEmblemPartsDTO> findByCriteria(MEmblemPartsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MEmblemParts> specification = createSpecification(criteria);
        return mEmblemPartsMapper.toDto(mEmblemPartsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MEmblemPartsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MEmblemPartsDTO> findByCriteria(MEmblemPartsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MEmblemParts> specification = createSpecification(criteria);
        return mEmblemPartsRepository.findAll(specification, page)
            .map(mEmblemPartsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MEmblemPartsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MEmblemParts> specification = createSpecification(criteria);
        return mEmblemPartsRepository.count(specification);
    }

    /**
     * Function to convert MEmblemPartsCriteria to a {@link Specification}.
     */
    private Specification<MEmblemParts> createSpecification(MEmblemPartsCriteria criteria) {
        Specification<MEmblemParts> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MEmblemParts_.id));
            }
            if (criteria.getPartsType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPartsType(), MEmblemParts_.partsType));
            }
            if (criteria.getMDummyEmblemId() != null) {
                specification = specification.and(buildSpecification(criteria.getMDummyEmblemId(),
                    root -> root.join(MEmblemParts_.mDummyEmblems, JoinType.LEFT).get(MDummyEmblem_.id)));
            }
        }
        return specification;
    }
}
