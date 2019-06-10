package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MAdventQuestStageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MAdventQuestStageDTO;
import io.shm.tsubasa.service.dto.MAdventQuestStageCriteria;
import io.shm.tsubasa.service.MAdventQuestStageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MAdventQuestStage}.
 */
@RestController
@RequestMapping("/api")
public class MAdventQuestStageResource {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestStageResource.class);

    private static final String ENTITY_NAME = "mAdventQuestStage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MAdventQuestStageService mAdventQuestStageService;

    private final MAdventQuestStageQueryService mAdventQuestStageQueryService;

    public MAdventQuestStageResource(MAdventQuestStageService mAdventQuestStageService, MAdventQuestStageQueryService mAdventQuestStageQueryService) {
        this.mAdventQuestStageService = mAdventQuestStageService;
        this.mAdventQuestStageQueryService = mAdventQuestStageQueryService;
    }

    /**
     * {@code POST  /m-advent-quest-stages} : Create a new mAdventQuestStage.
     *
     * @param mAdventQuestStageDTO the mAdventQuestStageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mAdventQuestStageDTO, or with status {@code 400 (Bad Request)} if the mAdventQuestStage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-advent-quest-stages")
    public ResponseEntity<MAdventQuestStageDTO> createMAdventQuestStage(@Valid @RequestBody MAdventQuestStageDTO mAdventQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to save MAdventQuestStage : {}", mAdventQuestStageDTO);
        if (mAdventQuestStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mAdventQuestStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MAdventQuestStageDTO result = mAdventQuestStageService.save(mAdventQuestStageDTO);
        return ResponseEntity.created(new URI("/api/m-advent-quest-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-advent-quest-stages} : Updates an existing mAdventQuestStage.
     *
     * @param mAdventQuestStageDTO the mAdventQuestStageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mAdventQuestStageDTO,
     * or with status {@code 400 (Bad Request)} if the mAdventQuestStageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mAdventQuestStageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-advent-quest-stages")
    public ResponseEntity<MAdventQuestStageDTO> updateMAdventQuestStage(@Valid @RequestBody MAdventQuestStageDTO mAdventQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to update MAdventQuestStage : {}", mAdventQuestStageDTO);
        if (mAdventQuestStageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MAdventQuestStageDTO result = mAdventQuestStageService.save(mAdventQuestStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mAdventQuestStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-advent-quest-stages} : get all the mAdventQuestStages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mAdventQuestStages in body.
     */
    @GetMapping("/m-advent-quest-stages")
    public ResponseEntity<List<MAdventQuestStageDTO>> getAllMAdventQuestStages(MAdventQuestStageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MAdventQuestStages by criteria: {}", criteria);
        Page<MAdventQuestStageDTO> page = mAdventQuestStageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-advent-quest-stages/count} : count all the mAdventQuestStages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-advent-quest-stages/count")
    public ResponseEntity<Long> countMAdventQuestStages(MAdventQuestStageCriteria criteria) {
        log.debug("REST request to count MAdventQuestStages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mAdventQuestStageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-advent-quest-stages/:id} : get the "id" mAdventQuestStage.
     *
     * @param id the id of the mAdventQuestStageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mAdventQuestStageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-advent-quest-stages/{id}")
    public ResponseEntity<MAdventQuestStageDTO> getMAdventQuestStage(@PathVariable Long id) {
        log.debug("REST request to get MAdventQuestStage : {}", id);
        Optional<MAdventQuestStageDTO> mAdventQuestStageDTO = mAdventQuestStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mAdventQuestStageDTO);
    }

    /**
     * {@code DELETE  /m-advent-quest-stages/:id} : delete the "id" mAdventQuestStage.
     *
     * @param id the id of the mAdventQuestStageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-advent-quest-stages/{id}")
    public ResponseEntity<Void> deleteMAdventQuestStage(@PathVariable Long id) {
        log.debug("REST request to delete MAdventQuestStage : {}", id);
        mAdventQuestStageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
