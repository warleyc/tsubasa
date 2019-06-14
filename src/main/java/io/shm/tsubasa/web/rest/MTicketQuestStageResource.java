package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTicketQuestStageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTicketQuestStageDTO;
import io.shm.tsubasa.service.dto.MTicketQuestStageCriteria;
import io.shm.tsubasa.service.MTicketQuestStageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTicketQuestStage}.
 */
@RestController
@RequestMapping("/api")
public class MTicketQuestStageResource {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestStageResource.class);

    private static final String ENTITY_NAME = "mTicketQuestStage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTicketQuestStageService mTicketQuestStageService;

    private final MTicketQuestStageQueryService mTicketQuestStageQueryService;

    public MTicketQuestStageResource(MTicketQuestStageService mTicketQuestStageService, MTicketQuestStageQueryService mTicketQuestStageQueryService) {
        this.mTicketQuestStageService = mTicketQuestStageService;
        this.mTicketQuestStageQueryService = mTicketQuestStageQueryService;
    }

    /**
     * {@code POST  /m-ticket-quest-stages} : Create a new mTicketQuestStage.
     *
     * @param mTicketQuestStageDTO the mTicketQuestStageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTicketQuestStageDTO, or with status {@code 400 (Bad Request)} if the mTicketQuestStage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-ticket-quest-stages")
    public ResponseEntity<MTicketQuestStageDTO> createMTicketQuestStage(@Valid @RequestBody MTicketQuestStageDTO mTicketQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to save MTicketQuestStage : {}", mTicketQuestStageDTO);
        if (mTicketQuestStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTicketQuestStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTicketQuestStageDTO result = mTicketQuestStageService.save(mTicketQuestStageDTO);
        return ResponseEntity.created(new URI("/api/m-ticket-quest-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-ticket-quest-stages} : Updates an existing mTicketQuestStage.
     *
     * @param mTicketQuestStageDTO the mTicketQuestStageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTicketQuestStageDTO,
     * or with status {@code 400 (Bad Request)} if the mTicketQuestStageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTicketQuestStageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-ticket-quest-stages")
    public ResponseEntity<MTicketQuestStageDTO> updateMTicketQuestStage(@Valid @RequestBody MTicketQuestStageDTO mTicketQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to update MTicketQuestStage : {}", mTicketQuestStageDTO);
        if (mTicketQuestStageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTicketQuestStageDTO result = mTicketQuestStageService.save(mTicketQuestStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTicketQuestStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-ticket-quest-stages} : get all the mTicketQuestStages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTicketQuestStages in body.
     */
    @GetMapping("/m-ticket-quest-stages")
    public ResponseEntity<List<MTicketQuestStageDTO>> getAllMTicketQuestStages(MTicketQuestStageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTicketQuestStages by criteria: {}", criteria);
        Page<MTicketQuestStageDTO> page = mTicketQuestStageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-ticket-quest-stages/count} : count all the mTicketQuestStages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-ticket-quest-stages/count")
    public ResponseEntity<Long> countMTicketQuestStages(MTicketQuestStageCriteria criteria) {
        log.debug("REST request to count MTicketQuestStages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTicketQuestStageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-ticket-quest-stages/:id} : get the "id" mTicketQuestStage.
     *
     * @param id the id of the mTicketQuestStageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTicketQuestStageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-ticket-quest-stages/{id}")
    public ResponseEntity<MTicketQuestStageDTO> getMTicketQuestStage(@PathVariable Long id) {
        log.debug("REST request to get MTicketQuestStage : {}", id);
        Optional<MTicketQuestStageDTO> mTicketQuestStageDTO = mTicketQuestStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTicketQuestStageDTO);
    }

    /**
     * {@code DELETE  /m-ticket-quest-stages/:id} : delete the "id" mTicketQuestStage.
     *
     * @param id the id of the mTicketQuestStageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-ticket-quest-stages/{id}")
    public ResponseEntity<Void> deleteMTicketQuestStage(@PathVariable Long id) {
        log.debug("REST request to delete MTicketQuestStage : {}", id);
        mTicketQuestStageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
