package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestCoopRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestCoopRewardDTO;
import io.shm.tsubasa.service.dto.MQuestCoopRewardCriteria;
import io.shm.tsubasa.service.MQuestCoopRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestCoopReward}.
 */
@RestController
@RequestMapping("/api")
public class MQuestCoopRewardResource {

    private final Logger log = LoggerFactory.getLogger(MQuestCoopRewardResource.class);

    private static final String ENTITY_NAME = "mQuestCoopReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestCoopRewardService mQuestCoopRewardService;

    private final MQuestCoopRewardQueryService mQuestCoopRewardQueryService;

    public MQuestCoopRewardResource(MQuestCoopRewardService mQuestCoopRewardService, MQuestCoopRewardQueryService mQuestCoopRewardQueryService) {
        this.mQuestCoopRewardService = mQuestCoopRewardService;
        this.mQuestCoopRewardQueryService = mQuestCoopRewardQueryService;
    }

    /**
     * {@code POST  /m-quest-coop-rewards} : Create a new mQuestCoopReward.
     *
     * @param mQuestCoopRewardDTO the mQuestCoopRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestCoopRewardDTO, or with status {@code 400 (Bad Request)} if the mQuestCoopReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-coop-rewards")
    public ResponseEntity<MQuestCoopRewardDTO> createMQuestCoopReward(@Valid @RequestBody MQuestCoopRewardDTO mQuestCoopRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestCoopReward : {}", mQuestCoopRewardDTO);
        if (mQuestCoopRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestCoopReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestCoopRewardDTO result = mQuestCoopRewardService.save(mQuestCoopRewardDTO);
        return ResponseEntity.created(new URI("/api/m-quest-coop-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-coop-rewards} : Updates an existing mQuestCoopReward.
     *
     * @param mQuestCoopRewardDTO the mQuestCoopRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestCoopRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestCoopRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestCoopRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-coop-rewards")
    public ResponseEntity<MQuestCoopRewardDTO> updateMQuestCoopReward(@Valid @RequestBody MQuestCoopRewardDTO mQuestCoopRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestCoopReward : {}", mQuestCoopRewardDTO);
        if (mQuestCoopRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestCoopRewardDTO result = mQuestCoopRewardService.save(mQuestCoopRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestCoopRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-coop-rewards} : get all the mQuestCoopRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestCoopRewards in body.
     */
    @GetMapping("/m-quest-coop-rewards")
    public ResponseEntity<List<MQuestCoopRewardDTO>> getAllMQuestCoopRewards(MQuestCoopRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestCoopRewards by criteria: {}", criteria);
        Page<MQuestCoopRewardDTO> page = mQuestCoopRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-coop-rewards/count} : count all the mQuestCoopRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-coop-rewards/count")
    public ResponseEntity<Long> countMQuestCoopRewards(MQuestCoopRewardCriteria criteria) {
        log.debug("REST request to count MQuestCoopRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestCoopRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-coop-rewards/:id} : get the "id" mQuestCoopReward.
     *
     * @param id the id of the mQuestCoopRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestCoopRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-coop-rewards/{id}")
    public ResponseEntity<MQuestCoopRewardDTO> getMQuestCoopReward(@PathVariable Long id) {
        log.debug("REST request to get MQuestCoopReward : {}", id);
        Optional<MQuestCoopRewardDTO> mQuestCoopRewardDTO = mQuestCoopRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestCoopRewardDTO);
    }

    /**
     * {@code DELETE  /m-quest-coop-rewards/:id} : delete the "id" mQuestCoopReward.
     *
     * @param id the id of the mQuestCoopRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-coop-rewards/{id}")
    public ResponseEntity<Void> deleteMQuestCoopReward(@PathVariable Long id) {
        log.debug("REST request to delete MQuestCoopReward : {}", id);
        mQuestCoopRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
