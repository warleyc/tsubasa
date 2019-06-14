package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTicketQuestTsubasaPointRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTicketQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.dto.MTicketQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MTicketQuestTsubasaPointRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTicketQuestTsubasaPointReward}.
 */
@RestController
@RequestMapping("/api")
public class MTicketQuestTsubasaPointRewardResource {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestTsubasaPointRewardResource.class);

    private static final String ENTITY_NAME = "mTicketQuestTsubasaPointReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTicketQuestTsubasaPointRewardService mTicketQuestTsubasaPointRewardService;

    private final MTicketQuestTsubasaPointRewardQueryService mTicketQuestTsubasaPointRewardQueryService;

    public MTicketQuestTsubasaPointRewardResource(MTicketQuestTsubasaPointRewardService mTicketQuestTsubasaPointRewardService, MTicketQuestTsubasaPointRewardQueryService mTicketQuestTsubasaPointRewardQueryService) {
        this.mTicketQuestTsubasaPointRewardService = mTicketQuestTsubasaPointRewardService;
        this.mTicketQuestTsubasaPointRewardQueryService = mTicketQuestTsubasaPointRewardQueryService;
    }

    /**
     * {@code POST  /m-ticket-quest-tsubasa-point-rewards} : Create a new mTicketQuestTsubasaPointReward.
     *
     * @param mTicketQuestTsubasaPointRewardDTO the mTicketQuestTsubasaPointRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTicketQuestTsubasaPointRewardDTO, or with status {@code 400 (Bad Request)} if the mTicketQuestTsubasaPointReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-ticket-quest-tsubasa-point-rewards")
    public ResponseEntity<MTicketQuestTsubasaPointRewardDTO> createMTicketQuestTsubasaPointReward(@Valid @RequestBody MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MTicketQuestTsubasaPointReward : {}", mTicketQuestTsubasaPointRewardDTO);
        if (mTicketQuestTsubasaPointRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTicketQuestTsubasaPointReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTicketQuestTsubasaPointRewardDTO result = mTicketQuestTsubasaPointRewardService.save(mTicketQuestTsubasaPointRewardDTO);
        return ResponseEntity.created(new URI("/api/m-ticket-quest-tsubasa-point-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-ticket-quest-tsubasa-point-rewards} : Updates an existing mTicketQuestTsubasaPointReward.
     *
     * @param mTicketQuestTsubasaPointRewardDTO the mTicketQuestTsubasaPointRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTicketQuestTsubasaPointRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mTicketQuestTsubasaPointRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTicketQuestTsubasaPointRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-ticket-quest-tsubasa-point-rewards")
    public ResponseEntity<MTicketQuestTsubasaPointRewardDTO> updateMTicketQuestTsubasaPointReward(@Valid @RequestBody MTicketQuestTsubasaPointRewardDTO mTicketQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MTicketQuestTsubasaPointReward : {}", mTicketQuestTsubasaPointRewardDTO);
        if (mTicketQuestTsubasaPointRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTicketQuestTsubasaPointRewardDTO result = mTicketQuestTsubasaPointRewardService.save(mTicketQuestTsubasaPointRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTicketQuestTsubasaPointRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-ticket-quest-tsubasa-point-rewards} : get all the mTicketQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTicketQuestTsubasaPointRewards in body.
     */
    @GetMapping("/m-ticket-quest-tsubasa-point-rewards")
    public ResponseEntity<List<MTicketQuestTsubasaPointRewardDTO>> getAllMTicketQuestTsubasaPointRewards(MTicketQuestTsubasaPointRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTicketQuestTsubasaPointRewards by criteria: {}", criteria);
        Page<MTicketQuestTsubasaPointRewardDTO> page = mTicketQuestTsubasaPointRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-ticket-quest-tsubasa-point-rewards/count} : count all the mTicketQuestTsubasaPointRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-ticket-quest-tsubasa-point-rewards/count")
    public ResponseEntity<Long> countMTicketQuestTsubasaPointRewards(MTicketQuestTsubasaPointRewardCriteria criteria) {
        log.debug("REST request to count MTicketQuestTsubasaPointRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTicketQuestTsubasaPointRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-ticket-quest-tsubasa-point-rewards/:id} : get the "id" mTicketQuestTsubasaPointReward.
     *
     * @param id the id of the mTicketQuestTsubasaPointRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTicketQuestTsubasaPointRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-ticket-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<MTicketQuestTsubasaPointRewardDTO> getMTicketQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to get MTicketQuestTsubasaPointReward : {}", id);
        Optional<MTicketQuestTsubasaPointRewardDTO> mTicketQuestTsubasaPointRewardDTO = mTicketQuestTsubasaPointRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTicketQuestTsubasaPointRewardDTO);
    }

    /**
     * {@code DELETE  /m-ticket-quest-tsubasa-point-rewards/:id} : delete the "id" mTicketQuestTsubasaPointReward.
     *
     * @param id the id of the mTicketQuestTsubasaPointRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-ticket-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<Void> deleteMTicketQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to delete MTicketQuestTsubasaPointReward : {}", id);
        mTicketQuestTsubasaPointRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
