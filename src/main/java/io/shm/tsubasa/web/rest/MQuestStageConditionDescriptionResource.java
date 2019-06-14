package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestStageConditionDescriptionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestStageConditionDescriptionDTO;
import io.shm.tsubasa.service.dto.MQuestStageConditionDescriptionCriteria;
import io.shm.tsubasa.service.MQuestStageConditionDescriptionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestStageConditionDescription}.
 */
@RestController
@RequestMapping("/api")
public class MQuestStageConditionDescriptionResource {

    private final Logger log = LoggerFactory.getLogger(MQuestStageConditionDescriptionResource.class);

    private static final String ENTITY_NAME = "mQuestStageConditionDescription";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestStageConditionDescriptionService mQuestStageConditionDescriptionService;

    private final MQuestStageConditionDescriptionQueryService mQuestStageConditionDescriptionQueryService;

    public MQuestStageConditionDescriptionResource(MQuestStageConditionDescriptionService mQuestStageConditionDescriptionService, MQuestStageConditionDescriptionQueryService mQuestStageConditionDescriptionQueryService) {
        this.mQuestStageConditionDescriptionService = mQuestStageConditionDescriptionService;
        this.mQuestStageConditionDescriptionQueryService = mQuestStageConditionDescriptionQueryService;
    }

    /**
     * {@code POST  /m-quest-stage-condition-descriptions} : Create a new mQuestStageConditionDescription.
     *
     * @param mQuestStageConditionDescriptionDTO the mQuestStageConditionDescriptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestStageConditionDescriptionDTO, or with status {@code 400 (Bad Request)} if the mQuestStageConditionDescription has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-stage-condition-descriptions")
    public ResponseEntity<MQuestStageConditionDescriptionDTO> createMQuestStageConditionDescription(@Valid @RequestBody MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestStageConditionDescription : {}", mQuestStageConditionDescriptionDTO);
        if (mQuestStageConditionDescriptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestStageConditionDescription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestStageConditionDescriptionDTO result = mQuestStageConditionDescriptionService.save(mQuestStageConditionDescriptionDTO);
        return ResponseEntity.created(new URI("/api/m-quest-stage-condition-descriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-stage-condition-descriptions} : Updates an existing mQuestStageConditionDescription.
     *
     * @param mQuestStageConditionDescriptionDTO the mQuestStageConditionDescriptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestStageConditionDescriptionDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestStageConditionDescriptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestStageConditionDescriptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-stage-condition-descriptions")
    public ResponseEntity<MQuestStageConditionDescriptionDTO> updateMQuestStageConditionDescription(@Valid @RequestBody MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestStageConditionDescription : {}", mQuestStageConditionDescriptionDTO);
        if (mQuestStageConditionDescriptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestStageConditionDescriptionDTO result = mQuestStageConditionDescriptionService.save(mQuestStageConditionDescriptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestStageConditionDescriptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-stage-condition-descriptions} : get all the mQuestStageConditionDescriptions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestStageConditionDescriptions in body.
     */
    @GetMapping("/m-quest-stage-condition-descriptions")
    public ResponseEntity<List<MQuestStageConditionDescriptionDTO>> getAllMQuestStageConditionDescriptions(MQuestStageConditionDescriptionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestStageConditionDescriptions by criteria: {}", criteria);
        Page<MQuestStageConditionDescriptionDTO> page = mQuestStageConditionDescriptionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-stage-condition-descriptions/count} : count all the mQuestStageConditionDescriptions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-stage-condition-descriptions/count")
    public ResponseEntity<Long> countMQuestStageConditionDescriptions(MQuestStageConditionDescriptionCriteria criteria) {
        log.debug("REST request to count MQuestStageConditionDescriptions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestStageConditionDescriptionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-stage-condition-descriptions/:id} : get the "id" mQuestStageConditionDescription.
     *
     * @param id the id of the mQuestStageConditionDescriptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestStageConditionDescriptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-stage-condition-descriptions/{id}")
    public ResponseEntity<MQuestStageConditionDescriptionDTO> getMQuestStageConditionDescription(@PathVariable Long id) {
        log.debug("REST request to get MQuestStageConditionDescription : {}", id);
        Optional<MQuestStageConditionDescriptionDTO> mQuestStageConditionDescriptionDTO = mQuestStageConditionDescriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestStageConditionDescriptionDTO);
    }

    /**
     * {@code DELETE  /m-quest-stage-condition-descriptions/:id} : delete the "id" mQuestStageConditionDescription.
     *
     * @param id the id of the mQuestStageConditionDescriptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-stage-condition-descriptions/{id}")
    public ResponseEntity<Void> deleteMQuestStageConditionDescription(@PathVariable Long id) {
        log.debug("REST request to delete MQuestStageConditionDescription : {}", id);
        mQuestStageConditionDescriptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
