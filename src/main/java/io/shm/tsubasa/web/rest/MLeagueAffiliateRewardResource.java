package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLeagueAffiliateRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLeagueAffiliateRewardDTO;
import io.shm.tsubasa.service.dto.MLeagueAffiliateRewardCriteria;
import io.shm.tsubasa.service.MLeagueAffiliateRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLeagueAffiliateReward}.
 */
@RestController
@RequestMapping("/api")
public class MLeagueAffiliateRewardResource {

    private final Logger log = LoggerFactory.getLogger(MLeagueAffiliateRewardResource.class);

    private static final String ENTITY_NAME = "mLeagueAffiliateReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLeagueAffiliateRewardService mLeagueAffiliateRewardService;

    private final MLeagueAffiliateRewardQueryService mLeagueAffiliateRewardQueryService;

    public MLeagueAffiliateRewardResource(MLeagueAffiliateRewardService mLeagueAffiliateRewardService, MLeagueAffiliateRewardQueryService mLeagueAffiliateRewardQueryService) {
        this.mLeagueAffiliateRewardService = mLeagueAffiliateRewardService;
        this.mLeagueAffiliateRewardQueryService = mLeagueAffiliateRewardQueryService;
    }

    /**
     * {@code POST  /m-league-affiliate-rewards} : Create a new mLeagueAffiliateReward.
     *
     * @param mLeagueAffiliateRewardDTO the mLeagueAffiliateRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLeagueAffiliateRewardDTO, or with status {@code 400 (Bad Request)} if the mLeagueAffiliateReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-league-affiliate-rewards")
    public ResponseEntity<MLeagueAffiliateRewardDTO> createMLeagueAffiliateReward(@Valid @RequestBody MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MLeagueAffiliateReward : {}", mLeagueAffiliateRewardDTO);
        if (mLeagueAffiliateRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLeagueAffiliateReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLeagueAffiliateRewardDTO result = mLeagueAffiliateRewardService.save(mLeagueAffiliateRewardDTO);
        return ResponseEntity.created(new URI("/api/m-league-affiliate-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-league-affiliate-rewards} : Updates an existing mLeagueAffiliateReward.
     *
     * @param mLeagueAffiliateRewardDTO the mLeagueAffiliateRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLeagueAffiliateRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mLeagueAffiliateRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLeagueAffiliateRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-league-affiliate-rewards")
    public ResponseEntity<MLeagueAffiliateRewardDTO> updateMLeagueAffiliateReward(@Valid @RequestBody MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MLeagueAffiliateReward : {}", mLeagueAffiliateRewardDTO);
        if (mLeagueAffiliateRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLeagueAffiliateRewardDTO result = mLeagueAffiliateRewardService.save(mLeagueAffiliateRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLeagueAffiliateRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-league-affiliate-rewards} : get all the mLeagueAffiliateRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLeagueAffiliateRewards in body.
     */
    @GetMapping("/m-league-affiliate-rewards")
    public ResponseEntity<List<MLeagueAffiliateRewardDTO>> getAllMLeagueAffiliateRewards(MLeagueAffiliateRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLeagueAffiliateRewards by criteria: {}", criteria);
        Page<MLeagueAffiliateRewardDTO> page = mLeagueAffiliateRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-league-affiliate-rewards/count} : count all the mLeagueAffiliateRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-league-affiliate-rewards/count")
    public ResponseEntity<Long> countMLeagueAffiliateRewards(MLeagueAffiliateRewardCriteria criteria) {
        log.debug("REST request to count MLeagueAffiliateRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLeagueAffiliateRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-league-affiliate-rewards/:id} : get the "id" mLeagueAffiliateReward.
     *
     * @param id the id of the mLeagueAffiliateRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLeagueAffiliateRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-league-affiliate-rewards/{id}")
    public ResponseEntity<MLeagueAffiliateRewardDTO> getMLeagueAffiliateReward(@PathVariable Long id) {
        log.debug("REST request to get MLeagueAffiliateReward : {}", id);
        Optional<MLeagueAffiliateRewardDTO> mLeagueAffiliateRewardDTO = mLeagueAffiliateRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLeagueAffiliateRewardDTO);
    }

    /**
     * {@code DELETE  /m-league-affiliate-rewards/:id} : delete the "id" mLeagueAffiliateReward.
     *
     * @param id the id of the mLeagueAffiliateRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-league-affiliate-rewards/{id}")
    public ResponseEntity<Void> deleteMLeagueAffiliateReward(@PathVariable Long id) {
        log.debug("REST request to delete MLeagueAffiliateReward : {}", id);
        mLeagueAffiliateRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
