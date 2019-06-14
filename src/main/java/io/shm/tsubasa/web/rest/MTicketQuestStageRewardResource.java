package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTicketQuestStageRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTicketQuestStageRewardDTO;
import io.shm.tsubasa.service.dto.MTicketQuestStageRewardCriteria;
import io.shm.tsubasa.service.MTicketQuestStageRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTicketQuestStageReward}.
 */
@RestController
@RequestMapping("/api")
public class MTicketQuestStageRewardResource {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestStageRewardResource.class);

    private static final String ENTITY_NAME = "mTicketQuestStageReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTicketQuestStageRewardService mTicketQuestStageRewardService;

    private final MTicketQuestStageRewardQueryService mTicketQuestStageRewardQueryService;

    public MTicketQuestStageRewardResource(MTicketQuestStageRewardService mTicketQuestStageRewardService, MTicketQuestStageRewardQueryService mTicketQuestStageRewardQueryService) {
        this.mTicketQuestStageRewardService = mTicketQuestStageRewardService;
        this.mTicketQuestStageRewardQueryService = mTicketQuestStageRewardQueryService;
    }

    /**
     * {@code POST  /m-ticket-quest-stage-rewards} : Create a new mTicketQuestStageReward.
     *
     * @param mTicketQuestStageRewardDTO the mTicketQuestStageRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTicketQuestStageRewardDTO, or with status {@code 400 (Bad Request)} if the mTicketQuestStageReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-ticket-quest-stage-rewards")
    public ResponseEntity<MTicketQuestStageRewardDTO> createMTicketQuestStageReward(@Valid @RequestBody MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MTicketQuestStageReward : {}", mTicketQuestStageRewardDTO);
        if (mTicketQuestStageRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTicketQuestStageReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTicketQuestStageRewardDTO result = mTicketQuestStageRewardService.save(mTicketQuestStageRewardDTO);
        return ResponseEntity.created(new URI("/api/m-ticket-quest-stage-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-ticket-quest-stage-rewards} : Updates an existing mTicketQuestStageReward.
     *
     * @param mTicketQuestStageRewardDTO the mTicketQuestStageRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTicketQuestStageRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mTicketQuestStageRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTicketQuestStageRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-ticket-quest-stage-rewards")
    public ResponseEntity<MTicketQuestStageRewardDTO> updateMTicketQuestStageReward(@Valid @RequestBody MTicketQuestStageRewardDTO mTicketQuestStageRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MTicketQuestStageReward : {}", mTicketQuestStageRewardDTO);
        if (mTicketQuestStageRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTicketQuestStageRewardDTO result = mTicketQuestStageRewardService.save(mTicketQuestStageRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTicketQuestStageRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-ticket-quest-stage-rewards} : get all the mTicketQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTicketQuestStageRewards in body.
     */
    @GetMapping("/m-ticket-quest-stage-rewards")
    public ResponseEntity<List<MTicketQuestStageRewardDTO>> getAllMTicketQuestStageRewards(MTicketQuestStageRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTicketQuestStageRewards by criteria: {}", criteria);
        Page<MTicketQuestStageRewardDTO> page = mTicketQuestStageRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-ticket-quest-stage-rewards/count} : count all the mTicketQuestStageRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-ticket-quest-stage-rewards/count")
    public ResponseEntity<Long> countMTicketQuestStageRewards(MTicketQuestStageRewardCriteria criteria) {
        log.debug("REST request to count MTicketQuestStageRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTicketQuestStageRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-ticket-quest-stage-rewards/:id} : get the "id" mTicketQuestStageReward.
     *
     * @param id the id of the mTicketQuestStageRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTicketQuestStageRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-ticket-quest-stage-rewards/{id}")
    public ResponseEntity<MTicketQuestStageRewardDTO> getMTicketQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to get MTicketQuestStageReward : {}", id);
        Optional<MTicketQuestStageRewardDTO> mTicketQuestStageRewardDTO = mTicketQuestStageRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTicketQuestStageRewardDTO);
    }

    /**
     * {@code DELETE  /m-ticket-quest-stage-rewards/:id} : delete the "id" mTicketQuestStageReward.
     *
     * @param id the id of the mTicketQuestStageRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-ticket-quest-stage-rewards/{id}")
    public ResponseEntity<Void> deleteMTicketQuestStageReward(@PathVariable Long id) {
        log.debug("REST request to delete MTicketQuestStageReward : {}", id);
        mTicketQuestStageRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
