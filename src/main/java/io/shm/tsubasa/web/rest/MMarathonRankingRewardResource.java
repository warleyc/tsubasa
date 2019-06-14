package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonRankingRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardDTO;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardCriteria;
import io.shm.tsubasa.service.MMarathonRankingRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonRankingReward}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonRankingRewardResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonRankingRewardResource.class);

    private static final String ENTITY_NAME = "mMarathonRankingReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonRankingRewardService mMarathonRankingRewardService;

    private final MMarathonRankingRewardQueryService mMarathonRankingRewardQueryService;

    public MMarathonRankingRewardResource(MMarathonRankingRewardService mMarathonRankingRewardService, MMarathonRankingRewardQueryService mMarathonRankingRewardQueryService) {
        this.mMarathonRankingRewardService = mMarathonRankingRewardService;
        this.mMarathonRankingRewardQueryService = mMarathonRankingRewardQueryService;
    }

    /**
     * {@code POST  /m-marathon-ranking-rewards} : Create a new mMarathonRankingReward.
     *
     * @param mMarathonRankingRewardDTO the mMarathonRankingRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonRankingRewardDTO, or with status {@code 400 (Bad Request)} if the mMarathonRankingReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-ranking-rewards")
    public ResponseEntity<MMarathonRankingRewardDTO> createMMarathonRankingReward(@Valid @RequestBody MMarathonRankingRewardDTO mMarathonRankingRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonRankingReward : {}", mMarathonRankingRewardDTO);
        if (mMarathonRankingRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonRankingReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonRankingRewardDTO result = mMarathonRankingRewardService.save(mMarathonRankingRewardDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-ranking-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-ranking-rewards} : Updates an existing mMarathonRankingReward.
     *
     * @param mMarathonRankingRewardDTO the mMarathonRankingRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonRankingRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonRankingRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonRankingRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-ranking-rewards")
    public ResponseEntity<MMarathonRankingRewardDTO> updateMMarathonRankingReward(@Valid @RequestBody MMarathonRankingRewardDTO mMarathonRankingRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonRankingReward : {}", mMarathonRankingRewardDTO);
        if (mMarathonRankingRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonRankingRewardDTO result = mMarathonRankingRewardService.save(mMarathonRankingRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonRankingRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-ranking-rewards} : get all the mMarathonRankingRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonRankingRewards in body.
     */
    @GetMapping("/m-marathon-ranking-rewards")
    public ResponseEntity<List<MMarathonRankingRewardDTO>> getAllMMarathonRankingRewards(MMarathonRankingRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonRankingRewards by criteria: {}", criteria);
        Page<MMarathonRankingRewardDTO> page = mMarathonRankingRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-ranking-rewards/count} : count all the mMarathonRankingRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-ranking-rewards/count")
    public ResponseEntity<Long> countMMarathonRankingRewards(MMarathonRankingRewardCriteria criteria) {
        log.debug("REST request to count MMarathonRankingRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonRankingRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-ranking-rewards/:id} : get the "id" mMarathonRankingReward.
     *
     * @param id the id of the mMarathonRankingRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonRankingRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-ranking-rewards/{id}")
    public ResponseEntity<MMarathonRankingRewardDTO> getMMarathonRankingReward(@PathVariable Long id) {
        log.debug("REST request to get MMarathonRankingReward : {}", id);
        Optional<MMarathonRankingRewardDTO> mMarathonRankingRewardDTO = mMarathonRankingRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonRankingRewardDTO);
    }

    /**
     * {@code DELETE  /m-marathon-ranking-rewards/:id} : delete the "id" mMarathonRankingReward.
     *
     * @param id the id of the mMarathonRankingRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-ranking-rewards/{id}")
    public ResponseEntity<Void> deleteMMarathonRankingReward(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonRankingReward : {}", id);
        mMarathonRankingRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
