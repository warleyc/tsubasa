package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestStageRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestStageRewardDTO;
import io.shm.tsubasa.service.dto.MQuestStageRewardCriteria;
import io.shm.tsubasa.service.MQuestStageRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestStageReward}.
 */
@RestController
@RequestMapping("/api")
public class MQuestStageRewardResource {

    private final Logger log = LoggerFactory.getLogger(MQuestStageRewardResource.class);

    private static final String ENTITY_NAME = "mQuestStageReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestStageRewardService mQuestStageRewardService;

    private final MQuestStageRewardQueryService mQuestStageRewardQueryService;

    public MQuestStageRewardResource(MQuestStageRewardService mQuestStageRewardService, MQuestStageRewardQueryService mQuestStageRewardQueryService) {
        this.mQuestStageRewardService = mQuestStageRewardService;
        this.mQuestStageRewardQueryService = mQuestStageRewardQueryService;
    }

    /**
     * {@code POST  /m-quest-stage-rewards} : Create a new mQuestStageReward.
     *
     * @param mQuestStageRewardDTO the mQuestStageRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestStageRewardDTO, or with status {@code 400 (Bad Request)} if the mQuestStageReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-stage-rewards")
    public ResponseEntity<MQuestStageRewardDTO> createMQuestStageReward(@Valid @RequestBody MQuestStageRewardDTO mQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestStageReward : {}", mQuestStageRewardDTO);
        if (mQuestStageRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestStageReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestStageRewardDTO result = mQuestStageRewardService.save(mQuestStageRewardDTO);
        return ResponseEntity.created(new URI("/api/m-quest-stage-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-stage-rewards} : Updates an existing mQuestStageReward.
     *
     * @param mQuestStageRewardDTO the mQuestStageRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestStageRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestStageRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestStageRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-stage-rewards")
    public ResponseEntity<MQuestStageRewardDTO> updateMQuestStageReward(@Valid @RequestBody MQuestStageRewardDTO mQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestStageReward : {}", mQuestStageRewardDTO);
        if (mQuestStageRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestStageRewardDTO result = mQuestStageRewardService.save(mQuestStageRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestStageRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-stage-rewards} : get all the mQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestStageRewards in body.
     */
    @GetMapping("/m-quest-stage-rewards")
    public ResponseEntity<List<MQuestStageRewardDTO>> getAllMQuestStageRewards(MQuestStageRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestStageRewards by criteria: {}", criteria);
        Page<MQuestStageRewardDTO> page = mQuestStageRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-stage-rewards/count} : count all the mQuestStageRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-stage-rewards/count")
    public ResponseEntity<Long> countMQuestStageRewards(MQuestStageRewardCriteria criteria) {
        log.debug("REST request to count MQuestStageRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestStageRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-stage-rewards/:id} : get the "id" mQuestStageReward.
     *
     * @param id the id of the mQuestStageRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestStageRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-stage-rewards/{id}")
    public ResponseEntity<MQuestStageRewardDTO> getMQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to get MQuestStageReward : {}", id);
        Optional<MQuestStageRewardDTO> mQuestStageRewardDTO = mQuestStageRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestStageRewardDTO);
    }

    /**
     * {@code DELETE  /m-quest-stage-rewards/:id} : delete the "id" mQuestStageReward.
     *
     * @param id the id of the mQuestStageRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-stage-rewards/{id}")
    public ResponseEntity<Void> deleteMQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to delete MQuestStageReward : {}", id);
        mQuestStageRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
