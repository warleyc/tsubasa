package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTimeattackRankingRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardDTO;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardCriteria;
import io.shm.tsubasa.service.MTimeattackRankingRewardQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.shm.tsubasa.domain.MTimeattackRankingReward}.
 */
@RestController
@RequestMapping("/api")
public class MTimeattackRankingRewardResource {

    private final Logger log = LoggerFactory.getLogger(MTimeattackRankingRewardResource.class);

    private static final String ENTITY_NAME = "mTimeattackRankingReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTimeattackRankingRewardService mTimeattackRankingRewardService;

    private final MTimeattackRankingRewardQueryService mTimeattackRankingRewardQueryService;

    public MTimeattackRankingRewardResource(MTimeattackRankingRewardService mTimeattackRankingRewardService, MTimeattackRankingRewardQueryService mTimeattackRankingRewardQueryService) {
        this.mTimeattackRankingRewardService = mTimeattackRankingRewardService;
        this.mTimeattackRankingRewardQueryService = mTimeattackRankingRewardQueryService;
    }

    /**
     * {@code POST  /m-timeattack-ranking-rewards} : Create a new mTimeattackRankingReward.
     *
     * @param mTimeattackRankingRewardDTO the mTimeattackRankingRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTimeattackRankingRewardDTO, or with status {@code 400 (Bad Request)} if the mTimeattackRankingReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-timeattack-ranking-rewards")
    public ResponseEntity<MTimeattackRankingRewardDTO> createMTimeattackRankingReward(@Valid @RequestBody MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MTimeattackRankingReward : {}", mTimeattackRankingRewardDTO);
        if (mTimeattackRankingRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTimeattackRankingReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTimeattackRankingRewardDTO result = mTimeattackRankingRewardService.save(mTimeattackRankingRewardDTO);
        return ResponseEntity.created(new URI("/api/m-timeattack-ranking-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-timeattack-ranking-rewards} : Updates an existing mTimeattackRankingReward.
     *
     * @param mTimeattackRankingRewardDTO the mTimeattackRankingRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTimeattackRankingRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mTimeattackRankingRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTimeattackRankingRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-timeattack-ranking-rewards")
    public ResponseEntity<MTimeattackRankingRewardDTO> updateMTimeattackRankingReward(@Valid @RequestBody MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MTimeattackRankingReward : {}", mTimeattackRankingRewardDTO);
        if (mTimeattackRankingRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTimeattackRankingRewardDTO result = mTimeattackRankingRewardService.save(mTimeattackRankingRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTimeattackRankingRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-timeattack-ranking-rewards} : get all the mTimeattackRankingRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTimeattackRankingRewards in body.
     */
    @GetMapping("/m-timeattack-ranking-rewards")
    public ResponseEntity<List<MTimeattackRankingRewardDTO>> getAllMTimeattackRankingRewards(MTimeattackRankingRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTimeattackRankingRewards by criteria: {}", criteria);
        Page<MTimeattackRankingRewardDTO> page = mTimeattackRankingRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-timeattack-ranking-rewards/count} : count all the mTimeattackRankingRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-timeattack-ranking-rewards/count")
    public ResponseEntity<Long> countMTimeattackRankingRewards(MTimeattackRankingRewardCriteria criteria) {
        log.debug("REST request to count MTimeattackRankingRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTimeattackRankingRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-timeattack-ranking-rewards/:id} : get the "id" mTimeattackRankingReward.
     *
     * @param id the id of the mTimeattackRankingRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTimeattackRankingRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-timeattack-ranking-rewards/{id}")
    public ResponseEntity<MTimeattackRankingRewardDTO> getMTimeattackRankingReward(@PathVariable Long id) {
        log.debug("REST request to get MTimeattackRankingReward : {}", id);
        Optional<MTimeattackRankingRewardDTO> mTimeattackRankingRewardDTO = mTimeattackRankingRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTimeattackRankingRewardDTO);
    }

    /**
     * {@code DELETE  /m-timeattack-ranking-rewards/:id} : delete the "id" mTimeattackRankingReward.
     *
     * @param id the id of the mTimeattackRankingRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-timeattack-ranking-rewards/{id}")
    public ResponseEntity<Void> deleteMTimeattackRankingReward(@PathVariable Long id) {
        log.debug("REST request to delete MTimeattackRankingReward : {}", id);
        mTimeattackRankingRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
