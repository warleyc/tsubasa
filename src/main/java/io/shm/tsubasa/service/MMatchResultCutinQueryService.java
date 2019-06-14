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

import io.shm.tsubasa.domain.MMatchResultCutin;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMatchResultCutinRepository;
import io.shm.tsubasa.service.dto.MMatchResultCutinCriteria;
import io.shm.tsubasa.service.dto.MMatchResultCutinDTO;
import io.shm.tsubasa.service.mapper.MMatchResultCutinMapper;

/**
 * Service for executing complex queries for {@link MMatchResultCutin} entities in the database.
 * The main input is a {@link MMatchResultCutinCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMatchResultCutinDTO} or a {@link Page} of {@link MMatchResultCutinDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMatchResultCutinQueryService extends QueryService<MMatchResultCutin> {

    private final Logger log = LoggerFactory.getLogger(MMatchResultCutinQueryService.class);

    private final MMatchResultCutinRepository mMatchResultCutinRepository;

    private final MMatchResultCutinMapper mMatchResultCutinMapper;

    public MMatchResultCutinQueryService(MMatchResultCutinRepository mMatchResultCutinRepository, MMatchResultCutinMapper mMatchResultCutinMapper) {
        this.mMatchResultCutinRepository = mMatchResultCutinRepository;
        this.mMatchResultCutinMapper = mMatchResultCutinMapper;
    }

    /**
     * Return a {@link List} of {@link MMatchResultCutinDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMatchResultCutinDTO> findByCriteria(MMatchResultCutinCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMatchResultCutin> specification = createSpecification(criteria);
        return mMatchResultCutinMapper.toDto(mMatchResultCutinRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMatchResultCutinDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMatchResultCutinDTO> findByCriteria(MMatchResultCutinCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMatchResultCutin> specification = createSpecification(criteria);
        return mMatchResultCutinRepository.findAll(specification, page)
            .map(mMatchResultCutinMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMatchResultCutinCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMatchResultCutin> specification = createSpecification(criteria);
        return mMatchResultCutinRepository.count(specification);
    }

    /**
     * Function to convert MMatchResultCutinCriteria to a {@link Specification}.
     */
    private Specification<MMatchResultCutin> createSpecification(MMatchResultCutinCriteria criteria) {
        Specification<MMatchResultCutin> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMatchResultCutin_.id));
            }
            if (criteria.getCharacterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCharacterId(), MMatchResultCutin_.characterId));
            }
            if (criteria.getTeamId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamId(), MMatchResultCutin_.teamId));
            }
            if (criteria.getIsWin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsWin(), MMatchResultCutin_.isWin));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MMatchResultCutin_.id, JoinType.LEFT).get(MCharacter_.id)));
            }
        }
        return specification;
    }
}
