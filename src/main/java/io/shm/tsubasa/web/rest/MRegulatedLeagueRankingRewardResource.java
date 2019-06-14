package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MRegulatedLeagueRankingRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MRegulatedLeagueRankingRewardDTO;
import io.shm.tsubasa.service.dto.MRegulatedLeagueRankingRewardCriteria;
import io.shm.tsubasa.service.MRegulatedLeagueRankingRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MRegulatedLeagueRankingReward}.
 */
@RestController
@RequestMapping("/api")
public class MRegulatedLeagueRankingRewardResource {

    private final Logger log = LoggerFactory.getLogger(MRegulatedLeagueRankingRewardResource.class);

    private static final String ENTITY_NAME = "mRegulatedLeagueRankingReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MRegulatedLeagueRankingRewardService mRegulatedLeagueRankingRewardService;

    private final MRegulatedLeagueRankingRewardQueryService mRegulatedLeagueRankingRewardQueryService;

    public MRegulatedLeagueRankingRewardResource(MRegulatedLeagueRankingRewardService mRegulatedLeagueRankingRewardService, MRegulatedLeagueRankingRewardQueryService mRegulatedLeagueRankingRewardQueryService) {
        this.mRegulatedLeagueRankingRewardService = mRegulatedLeagueRankingRewardService;
        this.mRegulatedLeagueRankingRewardQueryService = mRegulatedLeagueRankingRewardQueryService;
    }

    /**
     * {@code POST  /m-regulated-league-ranking-rewards} : Create a new mRegulatedLeagueRankingReward.
     *
     * @param mRegulatedLeagueRankingRewardDTO the mRegulatedLeagueRankingRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mRegulatedLeagueRankingRewardDTO, or with status {@code 400 (Bad Request)} if the mRegulatedLeagueRankingReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-regulated-league-ranking-rewards")
    public ResponseEntity<MRegulatedLeagueRankingRewardDTO> createMRegulatedLeagueRankingReward(@Valid @RequestBody MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MRegulatedLeagueRankingReward : {}", mRegulatedLeagueRankingRewardDTO);
        if (mRegulatedLeagueRankingRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mRegulatedLeagueRankingReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MRegulatedLeagueRankingRewardDTO result = mRegulatedLeagueRankingRewardService.save(mRegulatedLeagueRankingRewardDTO);
        return ResponseEntity.created(new URI("/api/m-regulated-league-ranking-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-regulated-league-ranking-rewards} : Updates an existing mRegulatedLeagueRankingReward.
     *
     * @param mRegulatedLeagueRankingRewardDTO the mRegulatedLeagueRankingRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mRegulatedLeagueRankingRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mRegulatedLeagueRankingRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mRegulatedLeagueRankingRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-regulated-league-ranking-rewards")
    public ResponseEntity<MRegulatedLeagueRankingRewardDTO> updateMRegulatedLeagueRankingReward(@Valid @RequestBody MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MRegulatedLeagueRankingReward : {}", mRegulatedLeagueRankingRewardDTO);
        if (mRegulatedLeagueRankingRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MRegulatedLeagueRankingRewardDTO result = mRegulatedLeagueRankingRewardService.save(mRegulatedLeagueRankingRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mRegulatedLeagueRankingRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-regulated-league-ranking-rewards} : get all the mRegulatedLeagueRankingRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mRegulatedLeagueRankingRewards in body.
     */
    @GetMapping("/m-regulated-league-ranking-rewards")
    public ResponseEntity<List<MRegulatedLeagueRankingRewardDTO>> getAllMRegulatedLeagueRankingRewards(MRegulatedLeagueRankingRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MRegulatedLeagueRankingRewards by criteria: {}", criteria);
        Page<MRegulatedLeagueRankingRewardDTO> page = mRegulatedLeagueRankingRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-regulated-league-ranking-rewards/count} : count all the mRegulatedLeagueRankingRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-regulated-league-ranking-rewards/count")
    public ResponseEntity<Long> countMRegulatedLeagueRankingRewards(MRegulatedLeagueRankingRewardCriteria criteria) {
        log.debug("REST request to count MRegulatedLeagueRankingRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mRegulatedLeagueRankingRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-regulated-league-ranking-rewards/:id} : get the "id" mRegulatedLeagueRankingReward.
     *
     * @param id the id of the mRegulatedLeagueRankingRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mRegulatedLeagueRankingRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-regulated-league-ranking-rewards/{id}")
    public ResponseEntity<MRegulatedLeagueRankingRewardDTO> getMRegulatedLeagueRankingReward(@PathVariable Long id) {
        log.debug("REST request to get MRegulatedLeagueRankingReward : {}", id);
        Optional<MRegulatedLeagueRankingRewardDTO> mRegulatedLeagueRankingRewardDTO = mRegulatedLeagueRankingRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mRegulatedLeagueRankingRewardDTO);
    }

    /**
     * {@code DELETE  /m-regulated-league-ranking-rewards/:id} : delete the "id" mRegulatedLeagueRankingReward.
     *
     * @param id the id of the mRegulatedLeagueRankingRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-regulated-league-ranking-rewards/{id}")
    public ResponseEntity<Void> deleteMRegulatedLeagueRankingReward(@PathVariable Long id) {
        log.debug("REST request to delete MRegulatedLeagueRankingReward : {}", id);
        mRegulatedLeagueRankingRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
