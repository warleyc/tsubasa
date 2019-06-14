package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonQuestStageRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonQuestStageRewardDTO;
import io.shm.tsubasa.service.dto.MMarathonQuestStageRewardCriteria;
import io.shm.tsubasa.service.MMarathonQuestStageRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonQuestStageReward}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonQuestStageRewardResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestStageRewardResource.class);

    private static final String ENTITY_NAME = "mMarathonQuestStageReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonQuestStageRewardService mMarathonQuestStageRewardService;

    private final MMarathonQuestStageRewardQueryService mMarathonQuestStageRewardQueryService;

    public MMarathonQuestStageRewardResource(MMarathonQuestStageRewardService mMarathonQuestStageRewardService, MMarathonQuestStageRewardQueryService mMarathonQuestStageRewardQueryService) {
        this.mMarathonQuestStageRewardService = mMarathonQuestStageRewardService;
        this.mMarathonQuestStageRewardQueryService = mMarathonQuestStageRewardQueryService;
    }

    /**
     * {@code POST  /m-marathon-quest-stage-rewards} : Create a new mMarathonQuestStageReward.
     *
     * @param mMarathonQuestStageRewardDTO the mMarathonQuestStageRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonQuestStageRewardDTO, or with status {@code 400 (Bad Request)} if the mMarathonQuestStageReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-quest-stage-rewards")
    public ResponseEntity<MMarathonQuestStageRewardDTO> createMMarathonQuestStageReward(@Valid @RequestBody MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonQuestStageReward : {}", mMarathonQuestStageRewardDTO);
        if (mMarathonQuestStageRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonQuestStageReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonQuestStageRewardDTO result = mMarathonQuestStageRewardService.save(mMarathonQuestStageRewardDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-quest-stage-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-quest-stage-rewards} : Updates an existing mMarathonQuestStageReward.
     *
     * @param mMarathonQuestStageRewardDTO the mMarathonQuestStageRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonQuestStageRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonQuestStageRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonQuestStageRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-quest-stage-rewards")
    public ResponseEntity<MMarathonQuestStageRewardDTO> updateMMarathonQuestStageReward(@Valid @RequestBody MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonQuestStageReward : {}", mMarathonQuestStageRewardDTO);
        if (mMarathonQuestStageRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonQuestStageRewardDTO result = mMarathonQuestStageRewardService.save(mMarathonQuestStageRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonQuestStageRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-quest-stage-rewards} : get all the mMarathonQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonQuestStageRewards in body.
     */
    @GetMapping("/m-marathon-quest-stage-rewards")
    public ResponseEntity<List<MMarathonQuestStageRewardDTO>> getAllMMarathonQuestStageRewards(MMarathonQuestStageRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonQuestStageRewards by criteria: {}", criteria);
        Page<MMarathonQuestStageRewardDTO> page = mMarathonQuestStageRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-quest-stage-rewards/count} : count all the mMarathonQuestStageRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-quest-stage-rewards/count")
    public ResponseEntity<Long> countMMarathonQuestStageRewards(MMarathonQuestStageRewardCriteria criteria) {
        log.debug("REST request to count MMarathonQuestStageRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonQuestStageRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-quest-stage-rewards/:id} : get the "id" mMarathonQuestStageReward.
     *
     * @param id the id of the mMarathonQuestStageRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonQuestStageRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-quest-stage-rewards/{id}")
    public ResponseEntity<MMarathonQuestStageRewardDTO> getMMarathonQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to get MMarathonQuestStageReward : {}", id);
        Optional<MMarathonQuestStageRewardDTO> mMarathonQuestStageRewardDTO = mMarathonQuestStageRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonQuestStageRewardDTO);
    }

    /**
     * {@code DELETE  /m-marathon-quest-stage-rewards/:id} : delete the "id" mMarathonQuestStageReward.
     *
     * @param id the id of the mMarathonQuestStageRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-quest-stage-rewards/{id}")
    public ResponseEntity<Void> deleteMMarathonQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonQuestStageReward : {}", id);
        mMarathonQuestStageRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
