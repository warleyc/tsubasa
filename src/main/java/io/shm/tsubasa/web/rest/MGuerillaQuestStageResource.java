package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuerillaQuestStageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageDTO;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageCriteria;
import io.shm.tsubasa.service.MGuerillaQuestStageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuerillaQuestStage}.
 */
@RestController
@RequestMapping("/api")
public class MGuerillaQuestStageResource {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestStageResource.class);

    private static final String ENTITY_NAME = "mGuerillaQuestStage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuerillaQuestStageService mGuerillaQuestStageService;

    private final MGuerillaQuestStageQueryService mGuerillaQuestStageQueryService;

    public MGuerillaQuestStageResource(MGuerillaQuestStageService mGuerillaQuestStageService, MGuerillaQuestStageQueryService mGuerillaQuestStageQueryService) {
        this.mGuerillaQuestStageService = mGuerillaQuestStageService;
        this.mGuerillaQuestStageQueryService = mGuerillaQuestStageQueryService;
    }

    /**
     * {@code POST  /m-guerilla-quest-stages} : Create a new mGuerillaQuestStage.
     *
     * @param mGuerillaQuestStageDTO the mGuerillaQuestStageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuerillaQuestStageDTO, or with status {@code 400 (Bad Request)} if the mGuerillaQuestStage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guerilla-quest-stages")
    public ResponseEntity<MGuerillaQuestStageDTO> createMGuerillaQuestStage(@Valid @RequestBody MGuerillaQuestStageDTO mGuerillaQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to save MGuerillaQuestStage : {}", mGuerillaQuestStageDTO);
        if (mGuerillaQuestStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuerillaQuestStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuerillaQuestStageDTO result = mGuerillaQuestStageService.save(mGuerillaQuestStageDTO);
        return ResponseEntity.created(new URI("/api/m-guerilla-quest-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guerilla-quest-stages} : Updates an existing mGuerillaQuestStage.
     *
     * @param mGuerillaQuestStageDTO the mGuerillaQuestStageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuerillaQuestStageDTO,
     * or with status {@code 400 (Bad Request)} if the mGuerillaQuestStageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuerillaQuestStageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guerilla-quest-stages")
    public ResponseEntity<MGuerillaQuestStageDTO> updateMGuerillaQuestStage(@Valid @RequestBody MGuerillaQuestStageDTO mGuerillaQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to update MGuerillaQuestStage : {}", mGuerillaQuestStageDTO);
        if (mGuerillaQuestStageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuerillaQuestStageDTO result = mGuerillaQuestStageService.save(mGuerillaQuestStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuerillaQuestStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guerilla-quest-stages} : get all the mGuerillaQuestStages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuerillaQuestStages in body.
     */
    @GetMapping("/m-guerilla-quest-stages")
    public ResponseEntity<List<MGuerillaQuestStageDTO>> getAllMGuerillaQuestStages(MGuerillaQuestStageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuerillaQuestStages by criteria: {}", criteria);
        Page<MGuerillaQuestStageDTO> page = mGuerillaQuestStageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guerilla-quest-stages/count} : count all the mGuerillaQuestStages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guerilla-quest-stages/count")
    public ResponseEntity<Long> countMGuerillaQuestStages(MGuerillaQuestStageCriteria criteria) {
        log.debug("REST request to count MGuerillaQuestStages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuerillaQuestStageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guerilla-quest-stages/:id} : get the "id" mGuerillaQuestStage.
     *
     * @param id the id of the mGuerillaQuestStageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuerillaQuestStageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guerilla-quest-stages/{id}")
    public ResponseEntity<MGuerillaQuestStageDTO> getMGuerillaQuestStage(@PathVariable Long id) {
        log.debug("REST request to get MGuerillaQuestStage : {}", id);
        Optional<MGuerillaQuestStageDTO> mGuerillaQuestStageDTO = mGuerillaQuestStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuerillaQuestStageDTO);
    }

    /**
     * {@code DELETE  /m-guerilla-quest-stages/:id} : delete the "id" mGuerillaQuestStage.
     *
     * @param id the id of the mGuerillaQuestStageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guerilla-quest-stages/{id}")
    public ResponseEntity<Void> deleteMGuerillaQuestStage(@PathVariable Long id) {
        log.debug("REST request to delete MGuerillaQuestStage : {}", id);
        mGuerillaQuestStageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
