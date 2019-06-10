package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MChallengeQuestStageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MChallengeQuestStageDTO;
import io.shm.tsubasa.service.dto.MChallengeQuestStageCriteria;
import io.shm.tsubasa.service.MChallengeQuestStageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MChallengeQuestStage}.
 */
@RestController
@RequestMapping("/api")
public class MChallengeQuestStageResource {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestStageResource.class);

    private static final String ENTITY_NAME = "mChallengeQuestStage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MChallengeQuestStageService mChallengeQuestStageService;

    private final MChallengeQuestStageQueryService mChallengeQuestStageQueryService;

    public MChallengeQuestStageResource(MChallengeQuestStageService mChallengeQuestStageService, MChallengeQuestStageQueryService mChallengeQuestStageQueryService) {
        this.mChallengeQuestStageService = mChallengeQuestStageService;
        this.mChallengeQuestStageQueryService = mChallengeQuestStageQueryService;
    }

    /**
     * {@code POST  /m-challenge-quest-stages} : Create a new mChallengeQuestStage.
     *
     * @param mChallengeQuestStageDTO the mChallengeQuestStageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mChallengeQuestStageDTO, or with status {@code 400 (Bad Request)} if the mChallengeQuestStage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-challenge-quest-stages")
    public ResponseEntity<MChallengeQuestStageDTO> createMChallengeQuestStage(@Valid @RequestBody MChallengeQuestStageDTO mChallengeQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to save MChallengeQuestStage : {}", mChallengeQuestStageDTO);
        if (mChallengeQuestStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mChallengeQuestStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MChallengeQuestStageDTO result = mChallengeQuestStageService.save(mChallengeQuestStageDTO);
        return ResponseEntity.created(new URI("/api/m-challenge-quest-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-challenge-quest-stages} : Updates an existing mChallengeQuestStage.
     *
     * @param mChallengeQuestStageDTO the mChallengeQuestStageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mChallengeQuestStageDTO,
     * or with status {@code 400 (Bad Request)} if the mChallengeQuestStageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mChallengeQuestStageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-challenge-quest-stages")
    public ResponseEntity<MChallengeQuestStageDTO> updateMChallengeQuestStage(@Valid @RequestBody MChallengeQuestStageDTO mChallengeQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to update MChallengeQuestStage : {}", mChallengeQuestStageDTO);
        if (mChallengeQuestStageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MChallengeQuestStageDTO result = mChallengeQuestStageService.save(mChallengeQuestStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mChallengeQuestStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-challenge-quest-stages} : get all the mChallengeQuestStages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mChallengeQuestStages in body.
     */
    @GetMapping("/m-challenge-quest-stages")
    public ResponseEntity<List<MChallengeQuestStageDTO>> getAllMChallengeQuestStages(MChallengeQuestStageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MChallengeQuestStages by criteria: {}", criteria);
        Page<MChallengeQuestStageDTO> page = mChallengeQuestStageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-challenge-quest-stages/count} : count all the mChallengeQuestStages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-challenge-quest-stages/count")
    public ResponseEntity<Long> countMChallengeQuestStages(MChallengeQuestStageCriteria criteria) {
        log.debug("REST request to count MChallengeQuestStages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mChallengeQuestStageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-challenge-quest-stages/:id} : get the "id" mChallengeQuestStage.
     *
     * @param id the id of the mChallengeQuestStageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mChallengeQuestStageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-challenge-quest-stages/{id}")
    public ResponseEntity<MChallengeQuestStageDTO> getMChallengeQuestStage(@PathVariable Long id) {
        log.debug("REST request to get MChallengeQuestStage : {}", id);
        Optional<MChallengeQuestStageDTO> mChallengeQuestStageDTO = mChallengeQuestStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mChallengeQuestStageDTO);
    }

    /**
     * {@code DELETE  /m-challenge-quest-stages/:id} : delete the "id" mChallengeQuestStage.
     *
     * @param id the id of the mChallengeQuestStageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-challenge-quest-stages/{id}")
    public ResponseEntity<Void> deleteMChallengeQuestStage(@PathVariable Long id) {
        log.debug("REST request to delete MChallengeQuestStage : {}", id);
        mChallengeQuestStageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
