package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MAdventQuestStageRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MAdventQuestStageRewardDTO;
import io.shm.tsubasa.service.dto.MAdventQuestStageRewardCriteria;
import io.shm.tsubasa.service.MAdventQuestStageRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MAdventQuestStageReward}.
 */
@RestController
@RequestMapping("/api")
public class MAdventQuestStageRewardResource {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestStageRewardResource.class);

    private static final String ENTITY_NAME = "mAdventQuestStageReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MAdventQuestStageRewardService mAdventQuestStageRewardService;

    private final MAdventQuestStageRewardQueryService mAdventQuestStageRewardQueryService;

    public MAdventQuestStageRewardResource(MAdventQuestStageRewardService mAdventQuestStageRewardService, MAdventQuestStageRewardQueryService mAdventQuestStageRewardQueryService) {
        this.mAdventQuestStageRewardService = mAdventQuestStageRewardService;
        this.mAdventQuestStageRewardQueryService = mAdventQuestStageRewardQueryService;
    }

    /**
     * {@code POST  /m-advent-quest-stage-rewards} : Create a new mAdventQuestStageReward.
     *
     * @param mAdventQuestStageRewardDTO the mAdventQuestStageRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mAdventQuestStageRewardDTO, or with status {@code 400 (Bad Request)} if the mAdventQuestStageReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-advent-quest-stage-rewards")
    public ResponseEntity<MAdventQuestStageRewardDTO> createMAdventQuestStageReward(@Valid @RequestBody MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MAdventQuestStageReward : {}", mAdventQuestStageRewardDTO);
        if (mAdventQuestStageRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mAdventQuestStageReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MAdventQuestStageRewardDTO result = mAdventQuestStageRewardService.save(mAdventQuestStageRewardDTO);
        return ResponseEntity.created(new URI("/api/m-advent-quest-stage-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-advent-quest-stage-rewards} : Updates an existing mAdventQuestStageReward.
     *
     * @param mAdventQuestStageRewardDTO the mAdventQuestStageRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mAdventQuestStageRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mAdventQuestStageRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mAdventQuestStageRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-advent-quest-stage-rewards")
    public ResponseEntity<MAdventQuestStageRewardDTO> updateMAdventQuestStageReward(@Valid @RequestBody MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MAdventQuestStageReward : {}", mAdventQuestStageRewardDTO);
        if (mAdventQuestStageRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MAdventQuestStageRewardDTO result = mAdventQuestStageRewardService.save(mAdventQuestStageRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mAdventQuestStageRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-advent-quest-stage-rewards} : get all the mAdventQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mAdventQuestStageRewards in body.
     */
    @GetMapping("/m-advent-quest-stage-rewards")
    public ResponseEntity<List<MAdventQuestStageRewardDTO>> getAllMAdventQuestStageRewards(MAdventQuestStageRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MAdventQuestStageRewards by criteria: {}", criteria);
        Page<MAdventQuestStageRewardDTO> page = mAdventQuestStageRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-advent-quest-stage-rewards/count} : count all the mAdventQuestStageRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-advent-quest-stage-rewards/count")
    public ResponseEntity<Long> countMAdventQuestStageRewards(MAdventQuestStageRewardCriteria criteria) {
        log.debug("REST request to count MAdventQuestStageRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mAdventQuestStageRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-advent-quest-stage-rewards/:id} : get the "id" mAdventQuestStageReward.
     *
     * @param id the id of the mAdventQuestStageRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mAdventQuestStageRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-advent-quest-stage-rewards/{id}")
    public ResponseEntity<MAdventQuestStageRewardDTO> getMAdventQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to get MAdventQuestStageReward : {}", id);
        Optional<MAdventQuestStageRewardDTO> mAdventQuestStageRewardDTO = mAdventQuestStageRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mAdventQuestStageRewardDTO);
    }

    /**
     * {@code DELETE  /m-advent-quest-stage-rewards/:id} : delete the "id" mAdventQuestStageReward.
     *
     * @param id the id of the mAdventQuestStageRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-advent-quest-stage-rewards/{id}")
    public ResponseEntity<Void> deleteMAdventQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to delete MAdventQuestStageReward : {}", id);
        mAdventQuestStageRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
