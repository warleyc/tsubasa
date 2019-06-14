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

import io.shm.tsubasa.domain.MLeague;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLeagueRepository;
import io.shm.tsubasa.service.dto.MLeagueCriteria;
import io.shm.tsubasa.service.dto.MLeagueDTO;
import io.shm.tsubasa.service.mapper.MLeagueMapper;

/**
 * Service for executing complex queries for {@link MLeague} entities in the database.
 * The main input is a {@link MLeagueCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLeagueDTO} or a {@link Page} of {@link MLeagueDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLeagueQueryService extends QueryService<MLeague> {

    private final Logger log = LoggerFactory.getLogger(MLeagueQueryService.class);

    private final MLeagueRepository mLeagueRepository;

    private final MLeagueMapper mLeagueMapper;

    public MLeagueQueryService(MLeagueRepository mLeagueRepository, MLeagueMapper mLeagueMapper) {
        this.mLeagueRepository = mLeagueRepository;
        this.mLeagueMapper = mLeagueMapper;
    }

    /**
     * Return a {@link List} of {@link MLeagueDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLeagueDTO> findByCriteria(MLeagueCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLeague> specification = createSpecification(criteria);
        return mLeagueMapper.toDto(mLeagueRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLeagueDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueDTO> findByCriteria(MLeagueCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLeague> specification = createSpecification(criteria);
        return mLeagueRepository.findAll(specification, page)
            .map(mLeagueMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLeagueCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLeague> specification = createSpecification(criteria);
        return mLeagueRepository.count(specification);
    }

    /**
     * Function to convert MLeagueCriteria to a {@link Specification}.
     */
    private Specification<MLeague> createSpecification(MLeagueCriteria criteria) {
        Specification<MLeague> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLeague_.id));
            }
            if (criteria.getHierarchy() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHierarchy(), MLeague_.hierarchy));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MLeague_.weight));
            }
            if (criteria.getRooms() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRooms(), MLeague_.rooms));
            }
            if (criteria.getPromotionRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPromotionRate(), MLeague_.promotionRate));
            }
            if (criteria.getDemotionRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDemotionRate(), MLeague_.demotionRate));
            }
            if (criteria.getTotalParameterRangeUpper() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalParameterRangeUpper(), MLeague_.totalParameterRangeUpper));
            }
            if (criteria.getTotalParameterRangeLower() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalParameterRangeLower(), MLeague_.totalParameterRangeLower));
            }
            if (criteria.getRequiredMatches() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRequiredMatches(), MLeague_.requiredMatches));
            }
            if (criteria.getStartWeekId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartWeekId(), MLeague_.startWeekId));
            }
        }
        return specification;
    }
}
