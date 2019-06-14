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

import io.shm.tsubasa.domain.MTeam;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTeamRepository;
import io.shm.tsubasa.service.dto.MTeamCriteria;
import io.shm.tsubasa.service.dto.MTeamDTO;
import io.shm.tsubasa.service.mapper.MTeamMapper;

/**
 * Service for executing complex queries for {@link MTeam} entities in the database.
 * The main input is a {@link MTeamCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTeamDTO} or a {@link Page} of {@link MTeamDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTeamQueryService extends QueryService<MTeam> {

    private final Logger log = LoggerFactory.getLogger(MTeamQueryService.class);

    private final MTeamRepository mTeamRepository;

    private final MTeamMapper mTeamMapper;

    public MTeamQueryService(MTeamRepository mTeamRepository, MTeamMapper mTeamMapper) {
        this.mTeamRepository = mTeamRepository;
        this.mTeamMapper = mTeamMapper;
    }

    /**
     * Return a {@link List} of {@link MTeamDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTeamDTO> findByCriteria(MTeamCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTeam> specification = createSpecification(criteria);
        return mTeamMapper.toDto(mTeamRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTeamDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTeamDTO> findByCriteria(MTeamCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTeam> specification = createSpecification(criteria);
        return mTeamRepository.findAll(specification, page)
            .map(mTeamMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTeamCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTeam> specification = createSpecification(criteria);
        return mTeamRepository.count(specification);
    }

    /**
     * Function to convert MTeamCriteria to a {@link Specification}.
     */
    private Specification<MTeam> createSpecification(MTeamCriteria criteria) {
        Specification<MTeam> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTeam_.id));
            }
            if (criteria.getMTargetTeamGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTargetTeamGroupId(),
                    root -> root.join(MTeam_.mTargetTeamGroups, JoinType.LEFT).get(MTargetTeamGroup_.id)));
            }
        }
        return specification;
    }
}
