package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuerillaQuestStageRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageRewardDTO;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageRewardCriteria;
import io.shm.tsubasa.service.MGuerillaQuestStageRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuerillaQuestStageReward}.
 */
@RestController
@RequestMapping("/api")
public class MGuerillaQuestStageRewardResource {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestStageRewardResource.class);

    private static final String ENTITY_NAME = "mGuerillaQuestStageReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuerillaQuestStageRewardService mGuerillaQuestStageRewardService;

    private final MGuerillaQuestStageRewardQueryService mGuerillaQuestStageRewardQueryService;

    public MGuerillaQuestStageRewardResource(MGuerillaQuestStageRewardService mGuerillaQuestStageRewardService, MGuerillaQuestStageRewardQueryService mGuerillaQuestStageRewardQueryService) {
        this.mGuerillaQuestStageRewardService = mGuerillaQuestStageRewardService;
        this.mGuerillaQuestStageRewardQueryService = mGuerillaQuestStageRewardQueryService;
    }

    /**
     * {@code POST  /m-guerilla-quest-stage-rewards} : Create a new mGuerillaQuestStageReward.
     *
     * @param mGuerillaQuestStageRewardDTO the mGuerillaQuestStageRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuerillaQuestStageRewardDTO, or with status {@code 400 (Bad Request)} if the mGuerillaQuestStageReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guerilla-quest-stage-rewards")
    public ResponseEntity<MGuerillaQuestStageRewardDTO> createMGuerillaQuestStageReward(@Valid @RequestBody MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MGuerillaQuestStageReward : {}", mGuerillaQuestStageRewardDTO);
        if (mGuerillaQuestStageRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuerillaQuestStageReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuerillaQuestStageRewardDTO result = mGuerillaQuestStageRewardService.save(mGuerillaQuestStageRewardDTO);
        return ResponseEntity.created(new URI("/api/m-guerilla-quest-stage-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guerilla-quest-stage-rewards} : Updates an existing mGuerillaQuestStageReward.
     *
     * @param mGuerillaQuestStageRewardDTO the mGuerillaQuestStageRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuerillaQuestStageRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mGuerillaQuestStageRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuerillaQuestStageRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guerilla-quest-stage-rewards")
    public ResponseEntity<MGuerillaQuestStageRewardDTO> updateMGuerillaQuestStageReward(@Valid @RequestBody MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MGuerillaQuestStageReward : {}", mGuerillaQuestStageRewardDTO);
        if (mGuerillaQuestStageRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuerillaQuestStageRewardDTO result = mGuerillaQuestStageRewardService.save(mGuerillaQuestStageRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuerillaQuestStageRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guerilla-quest-stage-rewards} : get all the mGuerillaQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuerillaQuestStageRewards in body.
     */
    @GetMapping("/m-guerilla-quest-stage-rewards")
    public ResponseEntity<List<MGuerillaQuestStageRewardDTO>> getAllMGuerillaQuestStageRewards(MGuerillaQuestStageRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuerillaQuestStageRewards by criteria: {}", criteria);
        Page<MGuerillaQuestStageRewardDTO> page = mGuerillaQuestStageRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guerilla-quest-stage-rewards/count} : count all the mGuerillaQuestStageRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guerilla-quest-stage-rewards/count")
    public ResponseEntity<Long> countMGuerillaQuestStageRewards(MGuerillaQuestStageRewardCriteria criteria) {
        log.debug("REST request to count MGuerillaQuestStageRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuerillaQuestStageRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guerilla-quest-stage-rewards/:id} : get the "id" mGuerillaQuestStageReward.
     *
     * @param id the id of the mGuerillaQuestStageRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuerillaQuestStageRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guerilla-quest-stage-rewards/{id}")
    public ResponseEntity<MGuerillaQuestStageRewardDTO> getMGuerillaQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to get MGuerillaQuestStageReward : {}", id);
        Optional<MGuerillaQuestStageRewardDTO> mGuerillaQuestStageRewardDTO = mGuerillaQuestStageRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuerillaQuestStageRewardDTO);
    }

    /**
     * {@code DELETE  /m-guerilla-quest-stage-rewards/:id} : delete the "id" mGuerillaQuestStageReward.
     *
     * @param id the id of the mGuerillaQuestStageRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guerilla-quest-stage-rewards/{id}")
    public ResponseEntity<Void> deleteMGuerillaQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to delete MGuerillaQuestStageReward : {}", id);
        mGuerillaQuestStageRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
