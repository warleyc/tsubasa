package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTimeattackQuestStageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageDTO;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageCriteria;
import io.shm.tsubasa.service.MTimeattackQuestStageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTimeattackQuestStage}.
 */
@RestController
@RequestMapping("/api")
public class MTimeattackQuestStageResource {

    private final Logger log = LoggerFactory.getLogger(MTimeattackQuestStageResource.class);

    private static final String ENTITY_NAME = "mTimeattackQuestStage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTimeattackQuestStageService mTimeattackQuestStageService;

    private final MTimeattackQuestStageQueryService mTimeattackQuestStageQueryService;

    public MTimeattackQuestStageResource(MTimeattackQuestStageService mTimeattackQuestStageService, MTimeattackQuestStageQueryService mTimeattackQuestStageQueryService) {
        this.mTimeattackQuestStageService = mTimeattackQuestStageService;
        this.mTimeattackQuestStageQueryService = mTimeattackQuestStageQueryService;
    }

    /**
     * {@code POST  /m-timeattack-quest-stages} : Create a new mTimeattackQuestStage.
     *
     * @param mTimeattackQuestStageDTO the mTimeattackQuestStageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTimeattackQuestStageDTO, or with status {@code 400 (Bad Request)} if the mTimeattackQuestStage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-timeattack-quest-stages")
    public ResponseEntity<MTimeattackQuestStageDTO> createMTimeattackQuestStage(@Valid @RequestBody MTimeattackQuestStageDTO mTimeattackQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to save MTimeattackQuestStage : {}", mTimeattackQuestStageDTO);
        if (mTimeattackQuestStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTimeattackQuestStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTimeattackQuestStageDTO result = mTimeattackQuestStageService.save(mTimeattackQuestStageDTO);
        return ResponseEntity.created(new URI("/api/m-timeattack-quest-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-timeattack-quest-stages} : Updates an existing mTimeattackQuestStage.
     *
     * @param mTimeattackQuestStageDTO the mTimeattackQuestStageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTimeattackQuestStageDTO,
     * or with status {@code 400 (Bad Request)} if the mTimeattackQuestStageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTimeattackQuestStageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-timeattack-quest-stages")
    public ResponseEntity<MTimeattackQuestStageDTO> updateMTimeattackQuestStage(@Valid @RequestBody MTimeattackQuestStageDTO mTimeattackQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to update MTimeattackQuestStage : {}", mTimeattackQuestStageDTO);
        if (mTimeattackQuestStageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTimeattackQuestStageDTO result = mTimeattackQuestStageService.save(mTimeattackQuestStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTimeattackQuestStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-timeattack-quest-stages} : get all the mTimeattackQuestStages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTimeattackQuestStages in body.
     */
    @GetMapping("/m-timeattack-quest-stages")
    public ResponseEntity<List<MTimeattackQuestStageDTO>> getAllMTimeattackQuestStages(MTimeattackQuestStageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTimeattackQuestStages by criteria: {}", criteria);
        Page<MTimeattackQuestStageDTO> page = mTimeattackQuestStageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-timeattack-quest-stages/count} : count all the mTimeattackQuestStages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-timeattack-quest-stages/count")
    public ResponseEntity<Long> countMTimeattackQuestStages(MTimeattackQuestStageCriteria criteria) {
        log.debug("REST request to count MTimeattackQuestStages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTimeattackQuestStageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-timeattack-quest-stages/:id} : get the "id" mTimeattackQuestStage.
     *
     * @param id the id of the mTimeattackQuestStageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTimeattackQuestStageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-timeattack-quest-stages/{id}")
    public ResponseEntity<MTimeattackQuestStageDTO> getMTimeattackQuestStage(@PathVariable Long id) {
        log.debug("REST request to get MTimeattackQuestStage : {}", id);
        Optional<MTimeattackQuestStageDTO> mTimeattackQuestStageDTO = mTimeattackQuestStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTimeattackQuestStageDTO);
    }

    /**
     * {@code DELETE  /m-timeattack-quest-stages/:id} : delete the "id" mTimeattackQuestStage.
     *
     * @param id the id of the mTimeattackQuestStageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-timeattack-quest-stages/{id}")
    public ResponseEntity<Void> deleteMTimeattackQuestStage(@PathVariable Long id) {
        log.debug("REST request to delete MTimeattackQuestStage : {}", id);
        mTimeattackQuestStageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
