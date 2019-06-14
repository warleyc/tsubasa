package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonQuestStageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonQuestStageDTO;
import io.shm.tsubasa.service.dto.MMarathonQuestStageCriteria;
import io.shm.tsubasa.service.MMarathonQuestStageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonQuestStage}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonQuestStageResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestStageResource.class);

    private static final String ENTITY_NAME = "mMarathonQuestStage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonQuestStageService mMarathonQuestStageService;

    private final MMarathonQuestStageQueryService mMarathonQuestStageQueryService;

    public MMarathonQuestStageResource(MMarathonQuestStageService mMarathonQuestStageService, MMarathonQuestStageQueryService mMarathonQuestStageQueryService) {
        this.mMarathonQuestStageService = mMarathonQuestStageService;
        this.mMarathonQuestStageQueryService = mMarathonQuestStageQueryService;
    }

    /**
     * {@code POST  /m-marathon-quest-stages} : Create a new mMarathonQuestStage.
     *
     * @param mMarathonQuestStageDTO the mMarathonQuestStageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonQuestStageDTO, or with status {@code 400 (Bad Request)} if the mMarathonQuestStage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-quest-stages")
    public ResponseEntity<MMarathonQuestStageDTO> createMMarathonQuestStage(@Valid @RequestBody MMarathonQuestStageDTO mMarathonQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonQuestStage : {}", mMarathonQuestStageDTO);
        if (mMarathonQuestStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonQuestStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonQuestStageDTO result = mMarathonQuestStageService.save(mMarathonQuestStageDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-quest-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-quest-stages} : Updates an existing mMarathonQuestStage.
     *
     * @param mMarathonQuestStageDTO the mMarathonQuestStageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonQuestStageDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonQuestStageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonQuestStageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-quest-stages")
    public ResponseEntity<MMarathonQuestStageDTO> updateMMarathonQuestStage(@Valid @RequestBody MMarathonQuestStageDTO mMarathonQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonQuestStage : {}", mMarathonQuestStageDTO);
        if (mMarathonQuestStageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonQuestStageDTO result = mMarathonQuestStageService.save(mMarathonQuestStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonQuestStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-quest-stages} : get all the mMarathonQuestStages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonQuestStages in body.
     */
    @GetMapping("/m-marathon-quest-stages")
    public ResponseEntity<List<MMarathonQuestStageDTO>> getAllMMarathonQuestStages(MMarathonQuestStageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonQuestStages by criteria: {}", criteria);
        Page<MMarathonQuestStageDTO> page = mMarathonQuestStageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-quest-stages/count} : count all the mMarathonQuestStages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-quest-stages/count")
    public ResponseEntity<Long> countMMarathonQuestStages(MMarathonQuestStageCriteria criteria) {
        log.debug("REST request to count MMarathonQuestStages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonQuestStageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-quest-stages/:id} : get the "id" mMarathonQuestStage.
     *
     * @param id the id of the mMarathonQuestStageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonQuestStageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-quest-stages/{id}")
    public ResponseEntity<MMarathonQuestStageDTO> getMMarathonQuestStage(@PathVariable Long id) {
        log.debug("REST request to get MMarathonQuestStage : {}", id);
        Optional<MMarathonQuestStageDTO> mMarathonQuestStageDTO = mMarathonQuestStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonQuestStageDTO);
    }

    /**
     * {@code DELETE  /m-marathon-quest-stages/:id} : delete the "id" mMarathonQuestStage.
     *
     * @param id the id of the mMarathonQuestStageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-quest-stages/{id}")
    public ResponseEntity<Void> deleteMMarathonQuestStage(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonQuestStage : {}", id);
        mMarathonQuestStageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
