package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestStageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestStageDTO;
import io.shm.tsubasa.service.dto.MQuestStageCriteria;
import io.shm.tsubasa.service.MQuestStageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestStage}.
 */
@RestController
@RequestMapping("/api")
public class MQuestStageResource {

    private final Logger log = LoggerFactory.getLogger(MQuestStageResource.class);

    private static final String ENTITY_NAME = "mQuestStage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestStageService mQuestStageService;

    private final MQuestStageQueryService mQuestStageQueryService;

    public MQuestStageResource(MQuestStageService mQuestStageService, MQuestStageQueryService mQuestStageQueryService) {
        this.mQuestStageService = mQuestStageService;
        this.mQuestStageQueryService = mQuestStageQueryService;
    }

    /**
     * {@code POST  /m-quest-stages} : Create a new mQuestStage.
     *
     * @param mQuestStageDTO the mQuestStageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestStageDTO, or with status {@code 400 (Bad Request)} if the mQuestStage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-stages")
    public ResponseEntity<MQuestStageDTO> createMQuestStage(@Valid @RequestBody MQuestStageDTO mQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestStage : {}", mQuestStageDTO);
        if (mQuestStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestStageDTO result = mQuestStageService.save(mQuestStageDTO);
        return ResponseEntity.created(new URI("/api/m-quest-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-stages} : Updates an existing mQuestStage.
     *
     * @param mQuestStageDTO the mQuestStageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestStageDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestStageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestStageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-stages")
    public ResponseEntity<MQuestStageDTO> updateMQuestStage(@Valid @RequestBody MQuestStageDTO mQuestStageDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestStage : {}", mQuestStageDTO);
        if (mQuestStageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestStageDTO result = mQuestStageService.save(mQuestStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-stages} : get all the mQuestStages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestStages in body.
     */
    @GetMapping("/m-quest-stages")
    public ResponseEntity<List<MQuestStageDTO>> getAllMQuestStages(MQuestStageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestStages by criteria: {}", criteria);
        Page<MQuestStageDTO> page = mQuestStageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-stages/count} : count all the mQuestStages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-stages/count")
    public ResponseEntity<Long> countMQuestStages(MQuestStageCriteria criteria) {
        log.debug("REST request to count MQuestStages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestStageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-stages/:id} : get the "id" mQuestStage.
     *
     * @param id the id of the mQuestStageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestStageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-stages/{id}")
    public ResponseEntity<MQuestStageDTO> getMQuestStage(@PathVariable Long id) {
        log.debug("REST request to get MQuestStage : {}", id);
        Optional<MQuestStageDTO> mQuestStageDTO = mQuestStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestStageDTO);
    }

    /**
     * {@code DELETE  /m-quest-stages/:id} : delete the "id" mQuestStage.
     *
     * @param id the id of the mQuestStageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-stages/{id}")
    public ResponseEntity<Void> deleteMQuestStage(@PathVariable Long id) {
        log.debug("REST request to delete MQuestStage : {}", id);
        mQuestStageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
