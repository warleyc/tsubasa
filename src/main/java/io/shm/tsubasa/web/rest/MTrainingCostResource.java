package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTrainingCostService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTrainingCostDTO;
import io.shm.tsubasa.service.dto.MTrainingCostCriteria;
import io.shm.tsubasa.service.MTrainingCostQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTrainingCost}.
 */
@RestController
@RequestMapping("/api")
public class MTrainingCostResource {

    private final Logger log = LoggerFactory.getLogger(MTrainingCostResource.class);

    private static final String ENTITY_NAME = "mTrainingCost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTrainingCostService mTrainingCostService;

    private final MTrainingCostQueryService mTrainingCostQueryService;

    public MTrainingCostResource(MTrainingCostService mTrainingCostService, MTrainingCostQueryService mTrainingCostQueryService) {
        this.mTrainingCostService = mTrainingCostService;
        this.mTrainingCostQueryService = mTrainingCostQueryService;
    }

    /**
     * {@code POST  /m-training-costs} : Create a new mTrainingCost.
     *
     * @param mTrainingCostDTO the mTrainingCostDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTrainingCostDTO, or with status {@code 400 (Bad Request)} if the mTrainingCost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-training-costs")
    public ResponseEntity<MTrainingCostDTO> createMTrainingCost(@Valid @RequestBody MTrainingCostDTO mTrainingCostDTO) throws URISyntaxException {
        log.debug("REST request to save MTrainingCost : {}", mTrainingCostDTO);
        if (mTrainingCostDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTrainingCost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTrainingCostDTO result = mTrainingCostService.save(mTrainingCostDTO);
        return ResponseEntity.created(new URI("/api/m-training-costs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-training-costs} : Updates an existing mTrainingCost.
     *
     * @param mTrainingCostDTO the mTrainingCostDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTrainingCostDTO,
     * or with status {@code 400 (Bad Request)} if the mTrainingCostDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTrainingCostDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-training-costs")
    public ResponseEntity<MTrainingCostDTO> updateMTrainingCost(@Valid @RequestBody MTrainingCostDTO mTrainingCostDTO) throws URISyntaxException {
        log.debug("REST request to update MTrainingCost : {}", mTrainingCostDTO);
        if (mTrainingCostDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTrainingCostDTO result = mTrainingCostService.save(mTrainingCostDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTrainingCostDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-training-costs} : get all the mTrainingCosts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTrainingCosts in body.
     */
    @GetMapping("/m-training-costs")
    public ResponseEntity<List<MTrainingCostDTO>> getAllMTrainingCosts(MTrainingCostCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTrainingCosts by criteria: {}", criteria);
        Page<MTrainingCostDTO> page = mTrainingCostQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-training-costs/count} : count all the mTrainingCosts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-training-costs/count")
    public ResponseEntity<Long> countMTrainingCosts(MTrainingCostCriteria criteria) {
        log.debug("REST request to count MTrainingCosts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTrainingCostQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-training-costs/:id} : get the "id" mTrainingCost.
     *
     * @param id the id of the mTrainingCostDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTrainingCostDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-training-costs/{id}")
    public ResponseEntity<MTrainingCostDTO> getMTrainingCost(@PathVariable Long id) {
        log.debug("REST request to get MTrainingCost : {}", id);
        Optional<MTrainingCostDTO> mTrainingCostDTO = mTrainingCostService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTrainingCostDTO);
    }

    /**
     * {@code DELETE  /m-training-costs/:id} : delete the "id" mTrainingCost.
     *
     * @param id the id of the mTrainingCostDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-training-costs/{id}")
    public ResponseEntity<Void> deleteMTrainingCost(@PathVariable Long id) {
        log.debug("REST request to delete MTrainingCost : {}", id);
        mTrainingCostService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
