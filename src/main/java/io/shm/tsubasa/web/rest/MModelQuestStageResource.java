package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MModelQuestStageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MModelQuestStageDTO;
import io.shm.tsubasa.service.dto.MModelQuestStageCriteria;
import io.shm.tsubasa.service.MModelQuestStageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MModelQuestStage}.
 */
@RestController
@RequestMapping("/api")
public class MModelQuestStageResource {

    private final Logger log = LoggerFactory.getLogger(MModelQuestStageResource.class);

    private static final String ENTITY_NAME = "mModelQuestStage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MModelQuestStageService mModelQuestStageService;

    private final MModelQuestStageQueryService mModelQuestStageQueryService;

    public MModelQuestStageResource(MModelQuestStageService mModelQuestStageService, MModelQuestStageQueryService mModelQuestStageQueryService) {
        this.mModelQuestStageService = mModelQuestStageService;
        this.mModelQuestStageQueryService = mModelQuestStageQueryService;
    }

    /**
     * {@code POST  /m-model-quest-stages} : Create a new mModelQuestStage.
     *
     * @param mModelQuestStageDTO the mModelQuestStageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mModelQuestStageDTO, or with status {@code 400 (Bad Request)} if the mModelQuestStage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-model-quest-stages")
    public ResponseEntity<MModelQuestStageDTO> createMModelQuestStage(@Valid @RequestBody MModelQuestStageDTO mModelQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to save MModelQuestStage : {}", mModelQuestStageDTO);
        if (mModelQuestStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mModelQuestStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MModelQuestStageDTO result = mModelQuestStageService.save(mModelQuestStageDTO);
        return ResponseEntity.created(new URI("/api/m-model-quest-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-model-quest-stages} : Updates an existing mModelQuestStage.
     *
     * @param mModelQuestStageDTO the mModelQuestStageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mModelQuestStageDTO,
     * or with status {@code 400 (Bad Request)} if the mModelQuestStageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mModelQuestStageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-model-quest-stages")
    public ResponseEntity<MModelQuestStageDTO> updateMModelQuestStage(@Valid @RequestBody MModelQuestStageDTO mModelQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to update MModelQuestStage : {}", mModelQuestStageDTO);
        if (mModelQuestStageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MModelQuestStageDTO result = mModelQuestStageService.save(mModelQuestStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mModelQuestStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-model-quest-stages} : get all the mModelQuestStages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mModelQuestStages in body.
     */
    @GetMapping("/m-model-quest-stages")
    public ResponseEntity<List<MModelQuestStageDTO>> getAllMModelQuestStages(MModelQuestStageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MModelQuestStages by criteria: {}", criteria);
        Page<MModelQuestStageDTO> page = mModelQuestStageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-model-quest-stages/count} : count all the mModelQuestStages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-model-quest-stages/count")
    public ResponseEntity<Long> countMModelQuestStages(MModelQuestStageCriteria criteria) {
        log.debug("REST request to count MModelQuestStages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mModelQuestStageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-model-quest-stages/:id} : get the "id" mModelQuestStage.
     *
     * @param id the id of the mModelQuestStageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mModelQuestStageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-model-quest-stages/{id}")
    public ResponseEntity<MModelQuestStageDTO> getMModelQuestStage(@PathVariable Long id) {
        log.debug("REST request to get MModelQuestStage : {}", id);
        Optional<MModelQuestStageDTO> mModelQuestStageDTO = mModelQuestStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mModelQuestStageDTO);
    }

    /**
     * {@code DELETE  /m-model-quest-stages/:id} : delete the "id" mModelQuestStage.
     *
     * @param id the id of the mModelQuestStageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-model-quest-stages/{id}")
    public ResponseEntity<Void> deleteMModelQuestStage(@PathVariable Long id) {
        log.debug("REST request to delete MModelQuestStage : {}", id);
        mModelQuestStageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
