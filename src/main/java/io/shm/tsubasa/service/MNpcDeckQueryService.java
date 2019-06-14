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

import io.shm.tsubasa.domain.MNpcDeck;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MNpcDeckRepository;
import io.shm.tsubasa.service.dto.MNpcDeckCriteria;
import io.shm.tsubasa.service.dto.MNpcDeckDTO;
import io.shm.tsubasa.service.mapper.MNpcDeckMapper;

/**
 * Service for executing complex queries for {@link MNpcDeck} entities in the database.
 * The main input is a {@link MNpcDeckCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MNpcDeckDTO} or a {@link Page} of {@link MNpcDeckDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MNpcDeckQueryService extends QueryService<MNpcDeck> {

    private final Logger log = LoggerFactory.getLogger(MNpcDeckQueryService.class);

    private final MNpcDeckRepository mNpcDeckRepository;

    private final MNpcDeckMapper mNpcDeckMapper;

    public MNpcDeckQueryService(MNpcDeckRepository mNpcDeckRepository, MNpcDeckMapper mNpcDeckMapper) {
        this.mNpcDeckRepository = mNpcDeckRepository;
        this.mNpcDeckMapper = mNpcDeckMapper;
    }

    /**
     * Return a {@link List} of {@link MNpcDeckDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MNpcDeckDTO> findByCriteria(MNpcDeckCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MNpcDeck> specification = createSpecification(criteria);
        return mNpcDeckMapper.toDto(mNpcDeckRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MNpcDeckDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MNpcDeckDTO> findByCriteria(MNpcDeckCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MNpcDeck> specification = createSpecification(criteria);
        return mNpcDeckRepository.findAll(specification, page)
            .map(mNpcDeckMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MNpcDeckCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MNpcDeck> specification = createSpecification(criteria);
        return mNpcDeckRepository.count(specification);
    }

    /**
     * Function to convert MNpcDeckCriteria to a {@link Specification}.
     */
    private Specification<MNpcDeck> createSpecification(MNpcDeckCriteria criteria) {
        Specification<MNpcDeck> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MNpcDeck_.id));
            }
            if (criteria.getUniformBottomFp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformBottomFp(), MNpcDeck_.uniformBottomFp));
            }
            if (criteria.getUniformUpFp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformUpFp(), MNpcDeck_.uniformUpFp));
            }
            if (criteria.getUniformBottomGk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformBottomGk(), MNpcDeck_.uniformBottomGk));
            }
            if (criteria.getUniformUpGk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformUpGk(), MNpcDeck_.uniformUpGk));
            }
            if (criteria.getFormationId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFormationId(), MNpcDeck_.formationId));
            }
            if (criteria.getCaptainCardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCaptainCardId(), MNpcDeck_.captainCardId));
            }
            if (criteria.getTeamEffect1CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamEffect1CardId(), MNpcDeck_.teamEffect1CardId));
            }
            if (criteria.getTeamEffect2CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamEffect2CardId(), MNpcDeck_.teamEffect2CardId));
            }
            if (criteria.getTeamEffect3CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamEffect3CardId(), MNpcDeck_.teamEffect3CardId));
            }
            if (criteria.getNpcCardId1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId1(), MNpcDeck_.npcCardId1));
            }
            if (criteria.getNpcCardId2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId2(), MNpcDeck_.npcCardId2));
            }
            if (criteria.getNpcCardId3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId3(), MNpcDeck_.npcCardId3));
            }
            if (criteria.getNpcCardId4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId4(), MNpcDeck_.npcCardId4));
            }
            if (criteria.getNpcCardId5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId5(), MNpcDeck_.npcCardId5));
            }
            if (criteria.getNpcCardId6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId6(), MNpcDeck_.npcCardId6));
            }
            if (criteria.getNpcCardId7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId7(), MNpcDeck_.npcCardId7));
            }
            if (criteria.getNpcCardId8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId8(), MNpcDeck_.npcCardId8));
            }
            if (criteria.getNpcCardId9() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId9(), MNpcDeck_.npcCardId9));
            }
            if (criteria.getNpcCardId10() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId10(), MNpcDeck_.npcCardId10));
            }
            if (criteria.getNpcCardId11() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcCardId11(), MNpcDeck_.npcCardId11));
            }
            if (criteria.getTick() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTick(), MNpcDeck_.tick));
            }
            if (criteria.getMformationId() != null) {
                specification = specification.and(buildSpecification(criteria.getMformationId(),
                    root -> root.join(MNpcDeck_.mformation, JoinType.LEFT).get(MFormation_.id)));
            }
            if (criteria.getMDummyOpponentId() != null) {
                specification = specification.and(buildSpecification(criteria.getMDummyOpponentId(),
                    root -> root.join(MNpcDeck_.mDummyOpponents, JoinType.LEFT).get(MDummyOpponent_.id)));
            }
        }
        return specification;
    }
}
