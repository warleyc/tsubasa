package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MChallengeQuestStageRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MChallengeQuestStageRewardDTO;
import io.shm.tsubasa.service.dto.MChallengeQuestStageRewardCriteria;
import io.shm.tsubasa.service.MChallengeQuestStageRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MChallengeQuestStageReward}.
 */
@RestController
@RequestMapping("/api")
public class MChallengeQuestStageRewardResource {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestStageRewardResource.class);

    private static final String ENTITY_NAME = "mChallengeQuestStageReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MChallengeQuestStageRewardService mChallengeQuestStageRewardService;

    private final MChallengeQuestStageRewardQueryService mChallengeQuestStageRewardQueryService;

    public MChallengeQuestStageRewardResource(MChallengeQuestStageRewardService mChallengeQuestStageRewardService, MChallengeQuestStageRewardQueryService mChallengeQuestStageRewardQueryService) {
        this.mChallengeQuestStageRewardService = mChallengeQuestStageRewardService;
        this.mChallengeQuestStageRewardQueryService = mChallengeQuestStageRewardQueryService;
    }

    /**
     * {@code POST  /m-challenge-quest-stage-rewards} : Create a new mChallengeQuestStageReward.
     *
     * @param mChallengeQuestStageRewardDTO the mChallengeQuestStageRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mChallengeQuestStageRewardDTO, or with status {@code 400 (Bad Request)} if the mChallengeQuestStageReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-challenge-quest-stage-rewards")
    public ResponseEntity<MChallengeQuestStageRewardDTO> createMChallengeQuestStageReward(@Valid @RequestBody MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MChallengeQuestStageReward : {}", mChallengeQuestStageRewardDTO);
        if (mChallengeQuestStageRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mChallengeQuestStageReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MChallengeQuestStageRewardDTO result = mChallengeQuestStageRewardService.save(mChallengeQuestStageRewardDTO);
        return ResponseEntity.created(new URI("/api/m-challenge-quest-stage-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-challenge-quest-stage-rewards} : Updates an existing mChallengeQuestStageReward.
     *
     * @param mChallengeQuestStageRewardDTO the mChallengeQuestStageRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mChallengeQuestStageRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mChallengeQuestStageRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mChallengeQuestStageRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-challenge-quest-stage-rewards")
    public ResponseEntity<MChallengeQuestStageRewardDTO> updateMChallengeQuestStageReward(@Valid @RequestBody MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MChallengeQuestStageReward : {}", mChallengeQuestStageRewardDTO);
        if (mChallengeQuestStageRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MChallengeQuestStageRewardDTO result = mChallengeQuestStageRewardService.save(mChallengeQuestStageRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mChallengeQuestStageRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-challenge-quest-stage-rewards} : get all the mChallengeQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mChallengeQuestStageRewards in body.
     */
    @GetMapping("/m-challenge-quest-stage-rewards")
    public ResponseEntity<List<MChallengeQuestStageRewardDTO>> getAllMChallengeQuestStageRewards(MChallengeQuestStageRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MChallengeQuestStageRewards by criteria: {}", criteria);
        Page<MChallengeQuestStageRewardDTO> page = mChallengeQuestStageRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-challenge-quest-stage-rewards/count} : count all the mChallengeQuestStageRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-challenge-quest-stage-rewards/count")
    public ResponseEntity<Long> countMChallengeQuestStageRewards(MChallengeQuestStageRewardCriteria criteria) {
        log.debug("REST request to count MChallengeQuestStageRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mChallengeQuestStageRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-challenge-quest-stage-rewards/:id} : get the "id" mChallengeQuestStageReward.
     *
     * @param id the id of the mChallengeQuestStageRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mChallengeQuestStageRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-challenge-quest-stage-rewards/{id}")
    public ResponseEntity<MChallengeQuestStageRewardDTO> getMChallengeQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to get MChallengeQuestStageReward : {}", id);
        Optional<MChallengeQuestStageRewardDTO> mChallengeQuestStageRewardDTO = mChallengeQuestStageRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mChallengeQuestStageRewardDTO);
    }

    /**
     * {@code DELETE  /m-challenge-quest-stage-rewards/:id} : delete the "id" mChallengeQuestStageReward.
     *
     * @param id the id of the mChallengeQuestStageRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-challenge-quest-stage-rewards/{id}")
    public ResponseEntity<Void> deleteMChallengeQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to delete MChallengeQuestStageReward : {}", id);
        mChallengeQuestStageRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
