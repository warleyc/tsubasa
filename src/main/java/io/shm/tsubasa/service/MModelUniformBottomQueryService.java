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

import io.shm.tsubasa.domain.MModelUniformBottom;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MModelUniformBottomRepository;
import io.shm.tsubasa.service.dto.MModelUniformBottomCriteria;
import io.shm.tsubasa.service.dto.MModelUniformBottomDTO;
import io.shm.tsubasa.service.mapper.MModelUniformBottomMapper;

/**
 * Service for executing complex queries for {@link MModelUniformBottom} entities in the database.
 * The main input is a {@link MModelUniformBottomCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MModelUniformBottomDTO} or a {@link Page} of {@link MModelUniformBottomDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MModelUniformBottomQueryService extends QueryService<MModelUniformBottom> {

    private final Logger log = LoggerFactory.getLogger(MModelUniformBottomQueryService.class);

    private final MModelUniformBottomRepository mModelUniformBottomRepository;

    private final MModelUniformBottomMapper mModelUniformBottomMapper;

    public MModelUniformBottomQueryService(MModelUniformBottomRepository mModelUniformBottomRepository, MModelUniformBottomMapper mModelUniformBottomMapper) {
        this.mModelUniformBottomRepository = mModelUniformBottomRepository;
        this.mModelUniformBottomMapper = mModelUniformBottomMapper;
    }

    /**
     * Return a {@link List} of {@link MModelUniformBottomDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MModelUniformBottomDTO> findByCriteria(MModelUniformBottomCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MModelUniformBottom> specification = createSpecification(criteria);
        return mModelUniformBottomMapper.toDto(mModelUniformBottomRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MModelUniformBottomDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelUniformBottomDTO> findByCriteria(MModelUniformBottomCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MModelUniformBottom> specification = createSpecification(criteria);
        return mModelUniformBottomRepository.findAll(specification, page)
            .map(mModelUniformBottomMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MModelUniformBottomCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MModelUniformBottom> specification = createSpecification(criteria);
        return mModelUniformBottomRepository.count(specification);
    }

    /**
     * Function to convert MModelUniformBottomCriteria to a {@link Specification}.
     */
    private Specification<MModelUniformBottom> createSpecification(MModelUniformBottomCriteria criteria) {
        Specification<MModelUniformBottom> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MModelUniformBottom_.id));
            }
            if (criteria.getUniformBottomId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformBottomId(), MModelUniformBottom_.uniformBottomId));
            }
            if (criteria.getDefaultDressingType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDefaultDressingType(), MModelUniformBottom_.defaultDressingType));
            }
            if (criteria.getUniformModel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformModel(), MModelUniformBottom_.uniformModel));
            }
            if (criteria.getUniformTexture() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformTexture(), MModelUniformBottom_.uniformTexture));
            }
            if (criteria.getUniformNoTexture() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformNoTexture(), MModelUniformBottom_.uniformNoTexture));
            }
            if (criteria.getNumberRightLeg() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberRightLeg(), MModelUniformBottom_.numberRightLeg));
            }
            if (criteria.getNumberLeftLeg() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberLeftLeg(), MModelUniformBottom_.numberLeftLeg));
            }
            if (criteria.getUniformNoPositionX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformNoPositionX(), MModelUniformBottom_.uniformNoPositionX));
            }
            if (criteria.getUniformNoPositionY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformNoPositionY(), MModelUniformBottom_.uniformNoPositionY));
            }
        }
        return specification;
    }
}
