package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLeagueRankingRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardDTO;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardCriteria;
import io.shm.tsubasa.service.MLeagueRankingRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLeagueRankingReward}.
 */
@RestController
@RequestMapping("/api")
public class MLeagueRankingRewardResource {

    private final Logger log = LoggerFactory.getLogger(MLeagueRankingRewardResource.class);

    private static final String ENTITY_NAME = "mLeagueRankingReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLeagueRankingRewardService mLeagueRankingRewardService;

    private final MLeagueRankingRewardQueryService mLeagueRankingRewardQueryService;

    public MLeagueRankingRewardResource(MLeagueRankingRewardService mLeagueRankingRewardService, MLeagueRankingRewardQueryService mLeagueRankingRewardQueryService) {
        this.mLeagueRankingRewardService = mLeagueRankingRewardService;
        this.mLeagueRankingRewardQueryService = mLeagueRankingRewardQueryService;
    }

    /**
     * {@code POST  /m-league-ranking-rewards} : Create a new mLeagueRankingReward.
     *
     * @param mLeagueRankingRewardDTO the mLeagueRankingRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLeagueRankingRewardDTO, or with status {@code 400 (Bad Request)} if the mLeagueRankingReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-league-ranking-rewards")
    public ResponseEntity<MLeagueRankingRewardDTO> createMLeagueRankingReward(@Valid @RequestBody MLeagueRankingRewardDTO mLeagueRankingRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MLeagueRankingReward : {}", mLeagueRankingRewardDTO);
        if (mLeagueRankingRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLeagueRankingReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLeagueRankingRewardDTO result = mLeagueRankingRewardService.save(mLeagueRankingRewardDTO);
        return ResponseEntity.created(new URI("/api/m-league-ranking-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-league-ranking-rewards} : Updates an existing mLeagueRankingReward.
     *
     * @param mLeagueRankingRewardDTO the mLeagueRankingRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLeagueRankingRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mLeagueRankingRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLeagueRankingRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-league-ranking-rewards")
    public ResponseEntity<MLeagueRankingRewardDTO> updateMLeagueRankingReward(@Valid @RequestBody MLeagueRankingRewardDTO mLeagueRankingRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MLeagueRankingReward : {}", mLeagueRankingRewardDTO);
        if (mLeagueRankingRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLeagueRankingRewardDTO result = mLeagueRankingRewardService.save(mLeagueRankingRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLeagueRankingRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-league-ranking-rewards} : get all the mLeagueRankingRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLeagueRankingRewards in body.
     */
    @GetMapping("/m-league-ranking-rewards")
    public ResponseEntity<List<MLeagueRankingRewardDTO>> getAllMLeagueRankingRewards(MLeagueRankingRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLeagueRankingRewards by criteria: {}", criteria);
        Page<MLeagueRankingRewardDTO> page = mLeagueRankingRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-league-ranking-rewards/count} : count all the mLeagueRankingRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-league-ranking-rewards/count")
    public ResponseEntity<Long> countMLeagueRankingRewards(MLeagueRankingRewardCriteria criteria) {
        log.debug("REST request to count MLeagueRankingRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLeagueRankingRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-league-ranking-rewards/:id} : get the "id" mLeagueRankingReward.
     *
     * @param id the id of the mLeagueRankingRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLeagueRankingRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-league-ranking-rewards/{id}")
    public ResponseEntity<MLeagueRankingRewardDTO> getMLeagueRankingReward(@PathVariable Long id) {
        log.debug("REST request to get MLeagueRankingReward : {}", id);
        Optional<MLeagueRankingRewardDTO> mLeagueRankingRewardDTO = mLeagueRankingRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLeagueRankingRewardDTO);
    }

    /**
     * {@code DELETE  /m-league-ranking-rewards/:id} : delete the "id" mLeagueRankingReward.
     *
     * @param id the id of the mLeagueRankingRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-league-ranking-rewards/{id}")
    public ResponseEntity<Void> deleteMLeagueRankingReward(@PathVariable Long id) {
        log.debug("REST request to delete MLeagueRankingReward : {}", id);
        mLeagueRankingRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
