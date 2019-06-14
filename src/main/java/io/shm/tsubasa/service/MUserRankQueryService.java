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

import io.shm.tsubasa.domain.MUserRank;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MUserRankRepository;
import io.shm.tsubasa.service.dto.MUserRankCriteria;
import io.shm.tsubasa.service.dto.MUserRankDTO;
import io.shm.tsubasa.service.mapper.MUserRankMapper;

/**
 * Service for executing complex queries for {@link MUserRank} entities in the database.
 * The main input is a {@link MUserRankCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MUserRankDTO} or a {@link Page} of {@link MUserRankDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MUserRankQueryService extends QueryService<MUserRank> {

    private final Logger log = LoggerFactory.getLogger(MUserRankQueryService.class);

    private final MUserRankRepository mUserRankRepository;

    private final MUserRankMapper mUserRankMapper;

    public MUserRankQueryService(MUserRankRepository mUserRankRepository, MUserRankMapper mUserRankMapper) {
        this.mUserRankRepository = mUserRankRepository;
        this.mUserRankMapper = mUserRankMapper;
    }

    /**
     * Return a {@link List} of {@link MUserRankDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MUserRankDTO> findByCriteria(MUserRankCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MUserRank> specification = createSpecification(criteria);
        return mUserRankMapper.toDto(mUserRankRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MUserRankDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MUserRankDTO> findByCriteria(MUserRankCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MUserRank> specification = createSpecification(criteria);
        return mUserRankRepository.findAll(specification, page)
            .map(mUserRankMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MUserRankCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MUserRank> specification = createSpecification(criteria);
        return mUserRankRepository.count(specification);
    }

    /**
     * Function to convert MUserRankCriteria to a {@link Specification}.
     */
    private Specification<MUserRank> createSpecification(MUserRankCriteria criteria) {
        Specification<MUserRank> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MUserRank_.id));
            }
            if (criteria.getRank() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRank(), MUserRank_.rank));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MUserRank_.exp));
            }
            if (criteria.getQuestAp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuestAp(), MUserRank_.questAp));
            }
            if (criteria.getMaxFriendCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxFriendCount(), MUserRank_.maxFriendCount));
            }
        }
        return specification;
    }
}
