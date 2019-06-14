package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestStageConditionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestStageConditionDTO;
import io.shm.tsubasa.service.dto.MQuestStageConditionCriteria;
import io.shm.tsubasa.service.MQuestStageConditionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestStageCondition}.
 */
@RestController
@RequestMapping("/api")
public class MQuestStageConditionResource {

    private final Logger log = LoggerFactory.getLogger(MQuestStageConditionResource.class);

    private static final String ENTITY_NAME = "mQuestStageCondition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestStageConditionService mQuestStageConditionService;

    private final MQuestStageConditionQueryService mQuestStageConditionQueryService;

    public MQuestStageConditionResource(MQuestStageConditionService mQuestStageConditionService, MQuestStageConditionQueryService mQuestStageConditionQueryService) {
        this.mQuestStageConditionService = mQuestStageConditionService;
        this.mQuestStageConditionQueryService = mQuestStageConditionQueryService;
    }

    /**
     * {@code POST  /m-quest-stage-conditions} : Create a new mQuestStageCondition.
     *
     * @param mQuestStageConditionDTO the mQuestStageConditionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestStageConditionDTO, or with status {@code 400 (Bad Request)} if the mQuestStageCondition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-stage-conditions")
    public ResponseEntity<MQuestStageConditionDTO> createMQuestStageCondition(@Valid @RequestBody MQuestStageConditionDTO mQuestStageConditionDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestStageCondition : {}", mQuestStageConditionDTO);
        if (mQuestStageConditionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestStageCondition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestStageConditionDTO result = mQuestStageConditionService.save(mQuestStageConditionDTO);
        return ResponseEntity.created(new URI("/api/m-quest-stage-conditions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-stage-conditions} : Updates an existing mQuestStageCondition.
     *
     * @param mQuestStageConditionDTO the mQuestStageConditionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestStageConditionDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestStageConditionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestStageConditionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-stage-conditions")
    public ResponseEntity<MQuestStageConditionDTO> updateMQuestStageCondition(@Valid @RequestBody MQuestStageConditionDTO mQuestStageConditionDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestStageCondition : {}", mQuestStageConditionDTO);
        if (mQuestStageConditionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestStageConditionDTO result = mQuestStageConditionService.save(mQuestStageConditionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestStageConditionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-stage-conditions} : get all the mQuestStageConditions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestStageConditions in body.
     */
    @GetMapping("/m-quest-stage-conditions")
    public ResponseEntity<List<MQuestStageConditionDTO>> getAllMQuestStageConditions(MQuestStageConditionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestStageConditions by criteria: {}", criteria);
        Page<MQuestStageConditionDTO> page = mQuestStageConditionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-stage-conditions/count} : count all the mQuestStageConditions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-stage-conditions/count")
    public ResponseEntity<Long> countMQuestStageConditions(MQuestStageConditionCriteria criteria) {
        log.debug("REST request to count MQuestStageConditions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestStageConditionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-stage-conditions/:id} : get the "id" mQuestStageCondition.
     *
     * @param id the id of the mQuestStageConditionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestStageConditionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-stage-conditions/{id}")
    public ResponseEntity<MQuestStageConditionDTO> getMQuestStageCondition(@PathVariable Long id) {
        log.debug("REST request to get MQuestStageCondition : {}", id);
        Optional<MQuestStageConditionDTO> mQuestStageConditionDTO = mQuestStageConditionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestStageConditionDTO);
    }

    /**
     * {@code DELETE  /m-quest-stage-conditions/:id} : delete the "id" mQuestStageCondition.
     *
     * @param id the id of the mQuestStageConditionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-stage-conditions/{id}")
    public ResponseEntity<Void> deleteMQuestStageCondition(@PathVariable Long id) {
        log.debug("REST request to delete MQuestStageCondition : {}", id);
        mQuestStageConditionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
