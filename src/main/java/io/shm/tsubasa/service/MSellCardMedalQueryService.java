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

import io.shm.tsubasa.domain.MSellCardMedal;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MSellCardMedalRepository;
import io.shm.tsubasa.service.dto.MSellCardMedalCriteria;
import io.shm.tsubasa.service.dto.MSellCardMedalDTO;
import io.shm.tsubasa.service.mapper.MSellCardMedalMapper;

/**
 * Service for executing complex queries for {@link MSellCardMedal} entities in the database.
 * The main input is a {@link MSellCardMedalCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MSellCardMedalDTO} or a {@link Page} of {@link MSellCardMedalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MSellCardMedalQueryService extends QueryService<MSellCardMedal> {

    private final Logger log = LoggerFactory.getLogger(MSellCardMedalQueryService.class);

    private final MSellCardMedalRepository mSellCardMedalRepository;

    private final MSellCardMedalMapper mSellCardMedalMapper;

    public MSellCardMedalQueryService(MSellCardMedalRepository mSellCardMedalRepository, MSellCardMedalMapper mSellCardMedalMapper) {
        this.mSellCardMedalRepository = mSellCardMedalRepository;
        this.mSellCardMedalMapper = mSellCardMedalMapper;
    }

    /**
     * Return a {@link List} of {@link MSellCardMedalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MSellCardMedalDTO> findByCriteria(MSellCardMedalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MSellCardMedal> specification = createSpecification(criteria);
        return mSellCardMedalMapper.toDto(mSellCardMedalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MSellCardMedalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MSellCardMedalDTO> findByCriteria(MSellCardMedalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MSellCardMedal> specification = createSpecification(criteria);
        return mSellCardMedalRepository.findAll(specification, page)
            .map(mSellCardMedalMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MSellCardMedalCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MSellCardMedal> specification = createSpecification(criteria);
        return mSellCardMedalRepository.count(specification);
    }

    /**
     * Function to convert MSellCardMedalCriteria to a {@link Specification}.
     */
    private Specification<MSellCardMedal> createSpecification(MSellCardMedalCriteria criteria) {
        Specification<MSellCardMedal> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MSellCardMedal_.id));
            }
            if (criteria.getMedalId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMedalId(), MSellCardMedal_.medalId));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), MSellCardMedal_.amount));
            }
        }
        return specification;
    }
}
