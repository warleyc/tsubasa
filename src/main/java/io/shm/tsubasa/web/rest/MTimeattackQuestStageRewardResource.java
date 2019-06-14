package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTimeattackQuestStageRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageRewardDTO;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageRewardCriteria;
import io.shm.tsubasa.service.MTimeattackQuestStageRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTimeattackQuestStageReward}.
 */
@RestController
@RequestMapping("/api")
public class MTimeattackQuestStageRewardResource {

    private final Logger log = LoggerFactory.getLogger(MTimeattackQuestStageRewardResource.class);

    private static final String ENTITY_NAME = "mTimeattackQuestStageReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTimeattackQuestStageRewardService mTimeattackQuestStageRewardService;

    private final MTimeattackQuestStageRewardQueryService mTimeattackQuestStageRewardQueryService;

    public MTimeattackQuestStageRewardResource(MTimeattackQuestStageRewardService mTimeattackQuestStageRewardService, MTimeattackQuestStageRewardQueryService mTimeattackQuestStageRewardQueryService) {
        this.mTimeattackQuestStageRewardService = mTimeattackQuestStageRewardService;
        this.mTimeattackQuestStageRewardQueryService = mTimeattackQuestStageRewardQueryService;
    }

    /**
     * {@code POST  /m-timeattack-quest-stage-rewards} : Create a new mTimeattackQuestStageReward.
     *
     * @param mTimeattackQuestStageRewardDTO the mTimeattackQuestStageRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTimeattackQuestStageRewardDTO, or with status {@code 400 (Bad Request)} if the mTimeattackQuestStageReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-timeattack-quest-stage-rewards")
    public ResponseEntity<MTimeattackQuestStageRewardDTO> createMTimeattackQuestStageReward(@Valid @RequestBody MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MTimeattackQuestStageReward : {}", mTimeattackQuestStageRewardDTO);
        if (mTimeattackQuestStageRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTimeattackQuestStageReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTimeattackQuestStageRewardDTO result = mTimeattackQuestStageRewardService.save(mTimeattackQuestStageRewardDTO);
        return ResponseEntity.created(new URI("/api/m-timeattack-quest-stage-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-timeattack-quest-stage-rewards} : Updates an existing mTimeattackQuestStageReward.
     *
     * @param mTimeattackQuestStageRewardDTO the mTimeattackQuestStageRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTimeattackQuestStageRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mTimeattackQuestStageRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTimeattackQuestStageRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-timeattack-quest-stage-rewards")
    public ResponseEntity<MTimeattackQuestStageRewardDTO> updateMTimeattackQuestStageReward(@Valid @RequestBody MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MTimeattackQuestStageReward : {}", mTimeattackQuestStageRewardDTO);
        if (mTimeattackQuestStageRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTimeattackQuestStageRewardDTO result = mTimeattackQuestStageRewardService.save(mTimeattackQuestStageRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTimeattackQuestStageRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-timeattack-quest-stage-rewards} : get all the mTimeattackQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTimeattackQuestStageRewards in body.
     */
    @GetMapping("/m-timeattack-quest-stage-rewards")
    public ResponseEntity<List<MTimeattackQuestStageRewardDTO>> getAllMTimeattackQuestStageRewards(MTimeattackQuestStageRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTimeattackQuestStageRewards by criteria: {}", criteria);
        Page<MTimeattackQuestStageRewardDTO> page = mTimeattackQuestStageRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-timeattack-quest-stage-rewards/count} : count all the mTimeattackQuestStageRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-timeattack-quest-stage-rewards/count")
    public ResponseEntity<Long> countMTimeattackQuestStageRewards(MTimeattackQuestStageRewardCriteria criteria) {
        log.debug("REST request to count MTimeattackQuestStageRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTimeattackQuestStageRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-timeattack-quest-stage-rewards/:id} : get the "id" mTimeattackQuestStageReward.
     *
     * @param id the id of the mTimeattackQuestStageRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTimeattackQuestStageRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-timeattack-quest-stage-rewards/{id}")
    public ResponseEntity<MTimeattackQuestStageRewardDTO> getMTimeattackQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to get MTimeattackQuestStageReward : {}", id);
        Optional<MTimeattackQuestStageRewardDTO> mTimeattackQuestStageRewardDTO = mTimeattackQuestStageRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTimeattackQuestStageRewardDTO);
    }

    /**
     * {@code DELETE  /m-timeattack-quest-stage-rewards/:id} : delete the "id" mTimeattackQuestStageReward.
     *
     * @param id the id of the mTimeattackQuestStageRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-timeattack-quest-stage-rewards/{id}")
    public ResponseEntity<Void> deleteMTimeattackQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to delete MTimeattackQuestStageReward : {}", id);
        mTimeattackQuestStageRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
