package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestGoalRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestGoalRewardDTO;
import io.shm.tsubasa.service.dto.MQuestGoalRewardCriteria;
import io.shm.tsubasa.service.MQuestGoalRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestGoalReward}.
 */
@RestController
@RequestMapping("/api")
public class MQuestGoalRewardResource {

    private final Logger log = LoggerFactory.getLogger(MQuestGoalRewardResource.class);

    private static final String ENTITY_NAME = "mQuestGoalReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestGoalRewardService mQuestGoalRewardService;

    private final MQuestGoalRewardQueryService mQuestGoalRewardQueryService;

    public MQuestGoalRewardResource(MQuestGoalRewardService mQuestGoalRewardService, MQuestGoalRewardQueryService mQuestGoalRewardQueryService) {
        this.mQuestGoalRewardService = mQuestGoalRewardService;
        this.mQuestGoalRewardQueryService = mQuestGoalRewardQueryService;
    }

    /**
     * {@code POST  /m-quest-goal-rewards} : Create a new mQuestGoalReward.
     *
     * @param mQuestGoalRewardDTO the mQuestGoalRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestGoalRewardDTO, or with status {@code 400 (Bad Request)} if the mQuestGoalReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-goal-rewards")
    public ResponseEntity<MQuestGoalRewardDTO> createMQuestGoalReward(@Valid @RequestBody MQuestGoalRewardDTO mQuestGoalRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestGoalReward : {}", mQuestGoalRewardDTO);
        if (mQuestGoalRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestGoalReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestGoalRewardDTO result = mQuestGoalRewardService.save(mQuestGoalRewardDTO);
        return ResponseEntity.created(new URI("/api/m-quest-goal-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-goal-rewards} : Updates an existing mQuestGoalReward.
     *
     * @param mQuestGoalRewardDTO the mQuestGoalRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestGoalRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestGoalRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestGoalRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-goal-rewards")
    public ResponseEntity<MQuestGoalRewardDTO> updateMQuestGoalReward(@Valid @RequestBody MQuestGoalRewardDTO mQuestGoalRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestGoalReward : {}", mQuestGoalRewardDTO);
        if (mQuestGoalRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestGoalRewardDTO result = mQuestGoalRewardService.save(mQuestGoalRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestGoalRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-goal-rewards} : get all the mQuestGoalRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestGoalRewards in body.
     */
    @GetMapping("/m-quest-goal-rewards")
    public ResponseEntity<List<MQuestGoalRewardDTO>> getAllMQuestGoalRewards(MQuestGoalRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestGoalRewards by criteria: {}", criteria);
        Page<MQuestGoalRewardDTO> page = mQuestGoalRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-goal-rewards/count} : count all the mQuestGoalRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-goal-rewards/count")
    public ResponseEntity<Long> countMQuestGoalRewards(MQuestGoalRewardCriteria criteria) {
        log.debug("REST request to count MQuestGoalRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestGoalRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-goal-rewards/:id} : get the "id" mQuestGoalReward.
     *
     * @param id the id of the mQuestGoalRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestGoalRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-goal-rewards/{id}")
    public ResponseEntity<MQuestGoalRewardDTO> getMQuestGoalReward(@PathVariable Long id) {
        log.debug("REST request to get MQuestGoalReward : {}", id);
        Optional<MQuestGoalRewardDTO> mQuestGoalRewardDTO = mQuestGoalRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestGoalRewardDTO);
    }

    /**
     * {@code DELETE  /m-quest-goal-rewards/:id} : delete the "id" mQuestGoalReward.
     *
     * @param id the id of the mQuestGoalRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-goal-rewards/{id}")
    public ResponseEntity<Void> deleteMQuestGoalReward(@PathVariable Long id) {
        log.debug("REST request to delete MQuestGoalReward : {}", id);
        mQuestGoalRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
