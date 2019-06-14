package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPvpRankingRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPvpRankingRewardDTO;
import io.shm.tsubasa.service.dto.MPvpRankingRewardCriteria;
import io.shm.tsubasa.service.MPvpRankingRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPvpRankingReward}.
 */
@RestController
@RequestMapping("/api")
public class MPvpRankingRewardResource {

    private final Logger log = LoggerFactory.getLogger(MPvpRankingRewardResource.class);

    private static final String ENTITY_NAME = "mPvpRankingReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPvpRankingRewardService mPvpRankingRewardService;

    private final MPvpRankingRewardQueryService mPvpRankingRewardQueryService;

    public MPvpRankingRewardResource(MPvpRankingRewardService mPvpRankingRewardService, MPvpRankingRewardQueryService mPvpRankingRewardQueryService) {
        this.mPvpRankingRewardService = mPvpRankingRewardService;
        this.mPvpRankingRewardQueryService = mPvpRankingRewardQueryService;
    }

    /**
     * {@code POST  /m-pvp-ranking-rewards} : Create a new mPvpRankingReward.
     *
     * @param mPvpRankingRewardDTO the mPvpRankingRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPvpRankingRewardDTO, or with status {@code 400 (Bad Request)} if the mPvpRankingReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-pvp-ranking-rewards")
    public ResponseEntity<MPvpRankingRewardDTO> createMPvpRankingReward(@Valid @RequestBody MPvpRankingRewardDTO mPvpRankingRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MPvpRankingReward : {}", mPvpRankingRewardDTO);
        if (mPvpRankingRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPvpRankingReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPvpRankingRewardDTO result = mPvpRankingRewardService.save(mPvpRankingRewardDTO);
        return ResponseEntity.created(new URI("/api/m-pvp-ranking-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-pvp-ranking-rewards} : Updates an existing mPvpRankingReward.
     *
     * @param mPvpRankingRewardDTO the mPvpRankingRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPvpRankingRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mPvpRankingRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPvpRankingRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-pvp-ranking-rewards")
    public ResponseEntity<MPvpRankingRewardDTO> updateMPvpRankingReward(@Valid @RequestBody MPvpRankingRewardDTO mPvpRankingRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MPvpRankingReward : {}", mPvpRankingRewardDTO);
        if (mPvpRankingRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPvpRankingRewardDTO result = mPvpRankingRewardService.save(mPvpRankingRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPvpRankingRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-pvp-ranking-rewards} : get all the mPvpRankingRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPvpRankingRewards in body.
     */
    @GetMapping("/m-pvp-ranking-rewards")
    public ResponseEntity<List<MPvpRankingRewardDTO>> getAllMPvpRankingRewards(MPvpRankingRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPvpRankingRewards by criteria: {}", criteria);
        Page<MPvpRankingRewardDTO> page = mPvpRankingRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-pvp-ranking-rewards/count} : count all the mPvpRankingRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-pvp-ranking-rewards/count")
    public ResponseEntity<Long> countMPvpRankingRewards(MPvpRankingRewardCriteria criteria) {
        log.debug("REST request to count MPvpRankingRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPvpRankingRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-pvp-ranking-rewards/:id} : get the "id" mPvpRankingReward.
     *
     * @param id the id of the mPvpRankingRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPvpRankingRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-pvp-ranking-rewards/{id}")
    public ResponseEntity<MPvpRankingRewardDTO> getMPvpRankingReward(@PathVariable Long id) {
        log.debug("REST request to get MPvpRankingReward : {}", id);
        Optional<MPvpRankingRewardDTO> mPvpRankingRewardDTO = mPvpRankingRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPvpRankingRewardDTO);
    }

    /**
     * {@code DELETE  /m-pvp-ranking-rewards/:id} : delete the "id" mPvpRankingReward.
     *
     * @param id the id of the mPvpRankingRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-pvp-ranking-rewards/{id}")
    public ResponseEntity<Void> deleteMPvpRankingReward(@PathVariable Long id) {
        log.debug("REST request to delete MPvpRankingReward : {}", id);
        mPvpRankingRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
