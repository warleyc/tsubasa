package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLuckWeeklyQuestStageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageDTO;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageCriteria;
import io.shm.tsubasa.service.MLuckWeeklyQuestStageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLuckWeeklyQuestStage}.
 */
@RestController
@RequestMapping("/api")
public class MLuckWeeklyQuestStageResource {

    private final Logger log = LoggerFactory.getLogger(MLuckWeeklyQuestStageResource.class);

    private static final String ENTITY_NAME = "mLuckWeeklyQuestStage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLuckWeeklyQuestStageService mLuckWeeklyQuestStageService;

    private final MLuckWeeklyQuestStageQueryService mLuckWeeklyQuestStageQueryService;

    public MLuckWeeklyQuestStageResource(MLuckWeeklyQuestStageService mLuckWeeklyQuestStageService, MLuckWeeklyQuestStageQueryService mLuckWeeklyQuestStageQueryService) {
        this.mLuckWeeklyQuestStageService = mLuckWeeklyQuestStageService;
        this.mLuckWeeklyQuestStageQueryService = mLuckWeeklyQuestStageQueryService;
    }

    /**
     * {@code POST  /m-luck-weekly-quest-stages} : Create a new mLuckWeeklyQuestStage.
     *
     * @param mLuckWeeklyQuestStageDTO the mLuckWeeklyQuestStageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLuckWeeklyQuestStageDTO, or with status {@code 400 (Bad Request)} if the mLuckWeeklyQuestStage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-luck-weekly-quest-stages")
    public ResponseEntity<MLuckWeeklyQuestStageDTO> createMLuckWeeklyQuestStage(@Valid @RequestBody MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to save MLuckWeeklyQuestStage : {}", mLuckWeeklyQuestStageDTO);
        if (mLuckWeeklyQuestStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLuckWeeklyQuestStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLuckWeeklyQuestStageDTO result = mLuckWeeklyQuestStageService.save(mLuckWeeklyQuestStageDTO);
        return ResponseEntity.created(new URI("/api/m-luck-weekly-quest-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-luck-weekly-quest-stages} : Updates an existing mLuckWeeklyQuestStage.
     *
     * @param mLuckWeeklyQuestStageDTO the mLuckWeeklyQuestStageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLuckWeeklyQuestStageDTO,
     * or with status {@code 400 (Bad Request)} if the mLuckWeeklyQuestStageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLuckWeeklyQuestStageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-luck-weekly-quest-stages")
    public ResponseEntity<MLuckWeeklyQuestStageDTO> updateMLuckWeeklyQuestStage(@Valid @RequestBody MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to update MLuckWeeklyQuestStage : {}", mLuckWeeklyQuestStageDTO);
        if (mLuckWeeklyQuestStageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLuckWeeklyQuestStageDTO result = mLuckWeeklyQuestStageService.save(mLuckWeeklyQuestStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLuckWeeklyQuestStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-luck-weekly-quest-stages} : get all the mLuckWeeklyQuestStages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLuckWeeklyQuestStages in body.
     */
    @GetMapping("/m-luck-weekly-quest-stages")
    public ResponseEntity<List<MLuckWeeklyQuestStageDTO>> getAllMLuckWeeklyQuestStages(MLuckWeeklyQuestStageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLuckWeeklyQuestStages by criteria: {}", criteria);
        Page<MLuckWeeklyQuestStageDTO> page = mLuckWeeklyQuestStageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-luck-weekly-quest-stages/count} : count all the mLuckWeeklyQuestStages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-luck-weekly-quest-stages/count")
    public ResponseEntity<Long> countMLuckWeeklyQuestStages(MLuckWeeklyQuestStageCriteria criteria) {
        log.debug("REST request to count MLuckWeeklyQuestStages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLuckWeeklyQuestStageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-luck-weekly-quest-stages/:id} : get the "id" mLuckWeeklyQuestStage.
     *
     * @param id the id of the mLuckWeeklyQuestStageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLuckWeeklyQuestStageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-luck-weekly-quest-stages/{id}")
    public ResponseEntity<MLuckWeeklyQuestStageDTO> getMLuckWeeklyQuestStage(@PathVariable Long id) {
        log.debug("REST request to get MLuckWeeklyQuestStage : {}", id);
        Optional<MLuckWeeklyQuestStageDTO> mLuckWeeklyQuestStageDTO = mLuckWeeklyQuestStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLuckWeeklyQuestStageDTO);
    }

    /**
     * {@code DELETE  /m-luck-weekly-quest-stages/:id} : delete the "id" mLuckWeeklyQuestStage.
     *
     * @param id the id of the mLuckWeeklyQuestStageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-luck-weekly-quest-stages/{id}")
    public ResponseEntity<Void> deleteMLuckWeeklyQuestStage(@PathVariable Long id) {
        log.debug("REST request to delete MLuckWeeklyQuestStage : {}", id);
        mLuckWeeklyQuestStageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
