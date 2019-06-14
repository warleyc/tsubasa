package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MWeeklyQuestStageRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageRewardDTO;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageRewardCriteria;
import io.shm.tsubasa.service.MWeeklyQuestStageRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MWeeklyQuestStageReward}.
 */
@RestController
@RequestMapping("/api")
public class MWeeklyQuestStageRewardResource {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestStageRewardResource.class);

    private static final String ENTITY_NAME = "mWeeklyQuestStageReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MWeeklyQuestStageRewardService mWeeklyQuestStageRewardService;

    private final MWeeklyQuestStageRewardQueryService mWeeklyQuestStageRewardQueryService;

    public MWeeklyQuestStageRewardResource(MWeeklyQuestStageRewardService mWeeklyQuestStageRewardService, MWeeklyQuestStageRewardQueryService mWeeklyQuestStageRewardQueryService) {
        this.mWeeklyQuestStageRewardService = mWeeklyQuestStageRewardService;
        this.mWeeklyQuestStageRewardQueryService = mWeeklyQuestStageRewardQueryService;
    }

    /**
     * {@code POST  /m-weekly-quest-stage-rewards} : Create a new mWeeklyQuestStageReward.
     *
     * @param mWeeklyQuestStageRewardDTO the mWeeklyQuestStageRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mWeeklyQuestStageRewardDTO, or with status {@code 400 (Bad Request)} if the mWeeklyQuestStageReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-weekly-quest-stage-rewards")
    public ResponseEntity<MWeeklyQuestStageRewardDTO> createMWeeklyQuestStageReward(@Valid @RequestBody MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MWeeklyQuestStageReward : {}", mWeeklyQuestStageRewardDTO);
        if (mWeeklyQuestStageRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mWeeklyQuestStageReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MWeeklyQuestStageRewardDTO result = mWeeklyQuestStageRewardService.save(mWeeklyQuestStageRewardDTO);
        return ResponseEntity.created(new URI("/api/m-weekly-quest-stage-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-weekly-quest-stage-rewards} : Updates an existing mWeeklyQuestStageReward.
     *
     * @param mWeeklyQuestStageRewardDTO the mWeeklyQuestStageRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mWeeklyQuestStageRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mWeeklyQuestStageRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mWeeklyQuestStageRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-weekly-quest-stage-rewards")
    public ResponseEntity<MWeeklyQuestStageRewardDTO> updateMWeeklyQuestStageReward(@Valid @RequestBody MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MWeeklyQuestStageReward : {}", mWeeklyQuestStageRewardDTO);
        if (mWeeklyQuestStageRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MWeeklyQuestStageRewardDTO result = mWeeklyQuestStageRewardService.save(mWeeklyQuestStageRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mWeeklyQuestStageRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-weekly-quest-stage-rewards} : get all the mWeeklyQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mWeeklyQuestStageRewards in body.
     */
    @GetMapping("/m-weekly-quest-stage-rewards")
    public ResponseEntity<List<MWeeklyQuestStageRewardDTO>> getAllMWeeklyQuestStageRewards(MWeeklyQuestStageRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MWeeklyQuestStageRewards by criteria: {}", criteria);
        Page<MWeeklyQuestStageRewardDTO> page = mWeeklyQuestStageRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-weekly-quest-stage-rewards/count} : count all the mWeeklyQuestStageRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-weekly-quest-stage-rewards/count")
    public ResponseEntity<Long> countMWeeklyQuestStageRewards(MWeeklyQuestStageRewardCriteria criteria) {
        log.debug("REST request to count MWeeklyQuestStageRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mWeeklyQuestStageRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-weekly-quest-stage-rewards/:id} : get the "id" mWeeklyQuestStageReward.
     *
     * @param id the id of the mWeeklyQuestStageRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mWeeklyQuestStageRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-weekly-quest-stage-rewards/{id}")
    public ResponseEntity<MWeeklyQuestStageRewardDTO> getMWeeklyQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to get MWeeklyQuestStageReward : {}", id);
        Optional<MWeeklyQuestStageRewardDTO> mWeeklyQuestStageRewardDTO = mWeeklyQuestStageRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mWeeklyQuestStageRewardDTO);
    }

    /**
     * {@code DELETE  /m-weekly-quest-stage-rewards/:id} : delete the "id" mWeeklyQuestStageReward.
     *
     * @param id the id of the mWeeklyQuestStageRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-weekly-quest-stage-rewards/{id}")
    public ResponseEntity<Void> deleteMWeeklyQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to delete MWeeklyQuestStageReward : {}", id);
        mWeeklyQuestStageRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
