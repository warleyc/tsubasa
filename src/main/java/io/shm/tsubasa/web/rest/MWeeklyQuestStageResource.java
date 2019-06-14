package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MWeeklyQuestStageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageDTO;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageCriteria;
import io.shm.tsubasa.service.MWeeklyQuestStageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MWeeklyQuestStage}.
 */
@RestController
@RequestMapping("/api")
public class MWeeklyQuestStageResource {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestStageResource.class);

    private static final String ENTITY_NAME = "mWeeklyQuestStage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MWeeklyQuestStageService mWeeklyQuestStageService;

    private final MWeeklyQuestStageQueryService mWeeklyQuestStageQueryService;

    public MWeeklyQuestStageResource(MWeeklyQuestStageService mWeeklyQuestStageService, MWeeklyQuestStageQueryService mWeeklyQuestStageQueryService) {
        this.mWeeklyQuestStageService = mWeeklyQuestStageService;
        this.mWeeklyQuestStageQueryService = mWeeklyQuestStageQueryService;
    }

    /**
     * {@code POST  /m-weekly-quest-stages} : Create a new mWeeklyQuestStage.
     *
     * @param mWeeklyQuestStageDTO the mWeeklyQuestStageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mWeeklyQuestStageDTO, or with status {@code 400 (Bad Request)} if the mWeeklyQuestStage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-weekly-quest-stages")
    public ResponseEntity<MWeeklyQuestStageDTO> createMWeeklyQuestStage(@Valid @RequestBody MWeeklyQuestStageDTO mWeeklyQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to save MWeeklyQuestStage : {}", mWeeklyQuestStageDTO);
        if (mWeeklyQuestStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mWeeklyQuestStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MWeeklyQuestStageDTO result = mWeeklyQuestStageService.save(mWeeklyQuestStageDTO);
        return ResponseEntity.created(new URI("/api/m-weekly-quest-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-weekly-quest-stages} : Updates an existing mWeeklyQuestStage.
     *
     * @param mWeeklyQuestStageDTO the mWeeklyQuestStageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mWeeklyQuestStageDTO,
     * or with status {@code 400 (Bad Request)} if the mWeeklyQuestStageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mWeeklyQuestStageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-weekly-quest-stages")
    public ResponseEntity<MWeeklyQuestStageDTO> updateMWeeklyQuestStage(@Valid @RequestBody MWeeklyQuestStageDTO mWeeklyQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to update MWeeklyQuestStage : {}", mWeeklyQuestStageDTO);
        if (mWeeklyQuestStageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MWeeklyQuestStageDTO result = mWeeklyQuestStageService.save(mWeeklyQuestStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mWeeklyQuestStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-weekly-quest-stages} : get all the mWeeklyQuestStages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mWeeklyQuestStages in body.
     */
    @GetMapping("/m-weekly-quest-stages")
    public ResponseEntity<List<MWeeklyQuestStageDTO>> getAllMWeeklyQuestStages(MWeeklyQuestStageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MWeeklyQuestStages by criteria: {}", criteria);
        Page<MWeeklyQuestStageDTO> page = mWeeklyQuestStageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-weekly-quest-stages/count} : count all the mWeeklyQuestStages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-weekly-quest-stages/count")
    public ResponseEntity<Long> countMWeeklyQuestStages(MWeeklyQuestStageCriteria criteria) {
        log.debug("REST request to count MWeeklyQuestStages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mWeeklyQuestStageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-weekly-quest-stages/:id} : get the "id" mWeeklyQuestStage.
     *
     * @param id the id of the mWeeklyQuestStageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mWeeklyQuestStageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-weekly-quest-stages/{id}")
    public ResponseEntity<MWeeklyQuestStageDTO> getMWeeklyQuestStage(@PathVariable Long id) {
        log.debug("REST request to get MWeeklyQuestStage : {}", id);
        Optional<MWeeklyQuestStageDTO> mWeeklyQuestStageDTO = mWeeklyQuestStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mWeeklyQuestStageDTO);
    }

    /**
     * {@code DELETE  /m-weekly-quest-stages/:id} : delete the "id" mWeeklyQuestStage.
     *
     * @param id the id of the mWeeklyQuestStageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-weekly-quest-stages/{id}")
    public ResponseEntity<Void> deleteMWeeklyQuestStage(@PathVariable Long id) {
        log.debug("REST request to delete MWeeklyQuestStage : {}", id);
        mWeeklyQuestStageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
