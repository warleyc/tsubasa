package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGoalJudgementService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGoalJudgementDTO;
import io.shm.tsubasa.service.dto.MGoalJudgementCriteria;
import io.shm.tsubasa.service.MGoalJudgementQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGoalJudgement}.
 */
@RestController
@RequestMapping("/api")
public class MGoalJudgementResource {

    private final Logger log = LoggerFactory.getLogger(MGoalJudgementResource.class);

    private static final String ENTITY_NAME = "mGoalJudgement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGoalJudgementService mGoalJudgementService;

    private final MGoalJudgementQueryService mGoalJudgementQueryService;

    public MGoalJudgementResource(MGoalJudgementService mGoalJudgementService, MGoalJudgementQueryService mGoalJudgementQueryService) {
        this.mGoalJudgementService = mGoalJudgementService;
        this.mGoalJudgementQueryService = mGoalJudgementQueryService;
    }

    /**
     * {@code POST  /m-goal-judgements} : Create a new mGoalJudgement.
     *
     * @param mGoalJudgementDTO the mGoalJudgementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGoalJudgementDTO, or with status {@code 400 (Bad Request)} if the mGoalJudgement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-goal-judgements")
    public ResponseEntity<MGoalJudgementDTO> createMGoalJudgement(@Valid @RequestBody MGoalJudgementDTO mGoalJudgementDTO) throws URISyntaxException {
        log.debug("REST request to save MGoalJudgement : {}", mGoalJudgementDTO);
        if (mGoalJudgementDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGoalJudgement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGoalJudgementDTO result = mGoalJudgementService.save(mGoalJudgementDTO);
        return ResponseEntity.created(new URI("/api/m-goal-judgements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-goal-judgements} : Updates an existing mGoalJudgement.
     *
     * @param mGoalJudgementDTO the mGoalJudgementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGoalJudgementDTO,
     * or with status {@code 400 (Bad Request)} if the mGoalJudgementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGoalJudgementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-goal-judgements")
    public ResponseEntity<MGoalJudgementDTO> updateMGoalJudgement(@Valid @RequestBody MGoalJudgementDTO mGoalJudgementDTO) throws URISyntaxException {
        log.debug("REST request to update MGoalJudgement : {}", mGoalJudgementDTO);
        if (mGoalJudgementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGoalJudgementDTO result = mGoalJudgementService.save(mGoalJudgementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGoalJudgementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-goal-judgements} : get all the mGoalJudgements.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGoalJudgements in body.
     */
    @GetMapping("/m-goal-judgements")
    public ResponseEntity<List<MGoalJudgementDTO>> getAllMGoalJudgements(MGoalJudgementCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGoalJudgements by criteria: {}", criteria);
        Page<MGoalJudgementDTO> page = mGoalJudgementQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-goal-judgements/count} : count all the mGoalJudgements.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-goal-judgements/count")
    public ResponseEntity<Long> countMGoalJudgements(MGoalJudgementCriteria criteria) {
        log.debug("REST request to count MGoalJudgements by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGoalJudgementQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-goal-judgements/:id} : get the "id" mGoalJudgement.
     *
     * @param id the id of the mGoalJudgementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGoalJudgementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-goal-judgements/{id}")
    public ResponseEntity<MGoalJudgementDTO> getMGoalJudgement(@PathVariable Long id) {
        log.debug("REST request to get MGoalJudgement : {}", id);
        Optional<MGoalJudgementDTO> mGoalJudgementDTO = mGoalJudgementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGoalJudgementDTO);
    }

    /**
     * {@code DELETE  /m-goal-judgements/:id} : delete the "id" mGoalJudgement.
     *
     * @param id the id of the mGoalJudgementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-goal-judgements/{id}")
    public ResponseEntity<Void> deleteMGoalJudgement(@PathVariable Long id) {
        log.debug("REST request to delete MGoalJudgement : {}", id);
        mGoalJudgementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
