package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLuckWeeklyQuestStageRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageRewardDTO;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageRewardCriteria;
import io.shm.tsubasa.service.MLuckWeeklyQuestStageRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLuckWeeklyQuestStageReward}.
 */
@RestController
@RequestMapping("/api")
public class MLuckWeeklyQuestStageRewardResource {

    private final Logger log = LoggerFactory.getLogger(MLuckWeeklyQuestStageRewardResource.class);

    private static final String ENTITY_NAME = "mLuckWeeklyQuestStageReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLuckWeeklyQuestStageRewardService mLuckWeeklyQuestStageRewardService;

    private final MLuckWeeklyQuestStageRewardQueryService mLuckWeeklyQuestStageRewardQueryService;

    public MLuckWeeklyQuestStageRewardResource(MLuckWeeklyQuestStageRewardService mLuckWeeklyQuestStageRewardService, MLuckWeeklyQuestStageRewardQueryService mLuckWeeklyQuestStageRewardQueryService) {
        this.mLuckWeeklyQuestStageRewardService = mLuckWeeklyQuestStageRewardService;
        this.mLuckWeeklyQuestStageRewardQueryService = mLuckWeeklyQuestStageRewardQueryService;
    }

    /**
     * {@code POST  /m-luck-weekly-quest-stage-rewards} : Create a new mLuckWeeklyQuestStageReward.
     *
     * @param mLuckWeeklyQuestStageRewardDTO the mLuckWeeklyQuestStageRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLuckWeeklyQuestStageRewardDTO, or with status {@code 400 (Bad Request)} if the mLuckWeeklyQuestStageReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-luck-weekly-quest-stage-rewards")
    public ResponseEntity<MLuckWeeklyQuestStageRewardDTO> createMLuckWeeklyQuestStageReward(@Valid @RequestBody MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MLuckWeeklyQuestStageReward : {}", mLuckWeeklyQuestStageRewardDTO);
        if (mLuckWeeklyQuestStageRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLuckWeeklyQuestStageReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLuckWeeklyQuestStageRewardDTO result = mLuckWeeklyQuestStageRewardService.save(mLuckWeeklyQuestStageRewardDTO);
        return ResponseEntity.created(new URI("/api/m-luck-weekly-quest-stage-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-luck-weekly-quest-stage-rewards} : Updates an existing mLuckWeeklyQuestStageReward.
     *
     * @param mLuckWeeklyQuestStageRewardDTO the mLuckWeeklyQuestStageRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLuckWeeklyQuestStageRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mLuckWeeklyQuestStageRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLuckWeeklyQuestStageRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-luck-weekly-quest-stage-rewards")
    public ResponseEntity<MLuckWeeklyQuestStageRewardDTO> updateMLuckWeeklyQuestStageReward(@Valid @RequestBody MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MLuckWeeklyQuestStageReward : {}", mLuckWeeklyQuestStageRewardDTO);
        if (mLuckWeeklyQuestStageRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLuckWeeklyQuestStageRewardDTO result = mLuckWeeklyQuestStageRewardService.save(mLuckWeeklyQuestStageRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLuckWeeklyQuestStageRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-luck-weekly-quest-stage-rewards} : get all the mLuckWeeklyQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLuckWeeklyQuestStageRewards in body.
     */
    @GetMapping("/m-luck-weekly-quest-stage-rewards")
    public ResponseEntity<List<MLuckWeeklyQuestStageRewardDTO>> getAllMLuckWeeklyQuestStageRewards(MLuckWeeklyQuestStageRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLuckWeeklyQuestStageRewards by criteria: {}", criteria);
        Page<MLuckWeeklyQuestStageRewardDTO> page = mLuckWeeklyQuestStageRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-luck-weekly-quest-stage-rewards/count} : count all the mLuckWeeklyQuestStageRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-luck-weekly-quest-stage-rewards/count")
    public ResponseEntity<Long> countMLuckWeeklyQuestStageRewards(MLuckWeeklyQuestStageRewardCriteria criteria) {
        log.debug("REST request to count MLuckWeeklyQuestStageRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLuckWeeklyQuestStageRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-luck-weekly-quest-stage-rewards/:id} : get the "id" mLuckWeeklyQuestStageReward.
     *
     * @param id the id of the mLuckWeeklyQuestStageRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLuckWeeklyQuestStageRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-luck-weekly-quest-stage-rewards/{id}")
    public ResponseEntity<MLuckWeeklyQuestStageRewardDTO> getMLuckWeeklyQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to get MLuckWeeklyQuestStageReward : {}", id);
        Optional<MLuckWeeklyQuestStageRewardDTO> mLuckWeeklyQuestStageRewardDTO = mLuckWeeklyQuestStageRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLuckWeeklyQuestStageRewardDTO);
    }

    /**
     * {@code DELETE  /m-luck-weekly-quest-stage-rewards/:id} : delete the "id" mLuckWeeklyQuestStageReward.
     *
     * @param id the id of the mLuckWeeklyQuestStageRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-luck-weekly-quest-stage-rewards/{id}")
    public ResponseEntity<Void> deleteMLuckWeeklyQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to delete MLuckWeeklyQuestStageReward : {}", id);
        mLuckWeeklyQuestStageRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
