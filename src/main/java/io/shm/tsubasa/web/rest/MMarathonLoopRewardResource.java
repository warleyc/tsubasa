package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonLoopRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardDTO;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardCriteria;
import io.shm.tsubasa.service.MMarathonLoopRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonLoopReward}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonLoopRewardResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonLoopRewardResource.class);

    private static final String ENTITY_NAME = "mMarathonLoopReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonLoopRewardService mMarathonLoopRewardService;

    private final MMarathonLoopRewardQueryService mMarathonLoopRewardQueryService;

    public MMarathonLoopRewardResource(MMarathonLoopRewardService mMarathonLoopRewardService, MMarathonLoopRewardQueryService mMarathonLoopRewardQueryService) {
        this.mMarathonLoopRewardService = mMarathonLoopRewardService;
        this.mMarathonLoopRewardQueryService = mMarathonLoopRewardQueryService;
    }

    /**
     * {@code POST  /m-marathon-loop-rewards} : Create a new mMarathonLoopReward.
     *
     * @param mMarathonLoopRewardDTO the mMarathonLoopRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonLoopRewardDTO, or with status {@code 400 (Bad Request)} if the mMarathonLoopReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-loop-rewards")
    public ResponseEntity<MMarathonLoopRewardDTO> createMMarathonLoopReward(@Valid @RequestBody MMarathonLoopRewardDTO mMarathonLoopRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonLoopReward : {}", mMarathonLoopRewardDTO);
        if (mMarathonLoopRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonLoopReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonLoopRewardDTO result = mMarathonLoopRewardService.save(mMarathonLoopRewardDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-loop-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-loop-rewards} : Updates an existing mMarathonLoopReward.
     *
     * @param mMarathonLoopRewardDTO the mMarathonLoopRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonLoopRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonLoopRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonLoopRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-loop-rewards")
    public ResponseEntity<MMarathonLoopRewardDTO> updateMMarathonLoopReward(@Valid @RequestBody MMarathonLoopRewardDTO mMarathonLoopRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonLoopReward : {}", mMarathonLoopRewardDTO);
        if (mMarathonLoopRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonLoopRewardDTO result = mMarathonLoopRewardService.save(mMarathonLoopRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonLoopRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-loop-rewards} : get all the mMarathonLoopRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonLoopRewards in body.
     */
    @GetMapping("/m-marathon-loop-rewards")
    public ResponseEntity<List<MMarathonLoopRewardDTO>> getAllMMarathonLoopRewards(MMarathonLoopRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonLoopRewards by criteria: {}", criteria);
        Page<MMarathonLoopRewardDTO> page = mMarathonLoopRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-loop-rewards/count} : count all the mMarathonLoopRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-loop-rewards/count")
    public ResponseEntity<Long> countMMarathonLoopRewards(MMarathonLoopRewardCriteria criteria) {
        log.debug("REST request to count MMarathonLoopRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonLoopRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-loop-rewards/:id} : get the "id" mMarathonLoopReward.
     *
     * @param id the id of the mMarathonLoopRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonLoopRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-loop-rewards/{id}")
    public ResponseEntity<MMarathonLoopRewardDTO> getMMarathonLoopReward(@PathVariable Long id) {
        log.debug("REST request to get MMarathonLoopReward : {}", id);
        Optional<MMarathonLoopRewardDTO> mMarathonLoopRewardDTO = mMarathonLoopRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonLoopRewardDTO);
    }

    /**
     * {@code DELETE  /m-marathon-loop-rewards/:id} : delete the "id" mMarathonLoopReward.
     *
     * @param id the id of the mMarathonLoopRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-loop-rewards/{id}")
    public ResponseEntity<Void> deleteMMarathonLoopReward(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonLoopReward : {}", id);
        mMarathonLoopRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
