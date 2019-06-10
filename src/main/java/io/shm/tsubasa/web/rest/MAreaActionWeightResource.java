package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MAreaActionWeightService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MAreaActionWeightDTO;
import io.shm.tsubasa.service.dto.MAreaActionWeightCriteria;
import io.shm.tsubasa.service.MAreaActionWeightQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MAreaActionWeight}.
 */
@RestController
@RequestMapping("/api")
public class MAreaActionWeightResource {

    private final Logger log = LoggerFactory.getLogger(MAreaActionWeightResource.class);

    private static final String ENTITY_NAME = "mAreaActionWeight";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MAreaActionWeightService mAreaActionWeightService;

    private final MAreaActionWeightQueryService mAreaActionWeightQueryService;

    public MAreaActionWeightResource(MAreaActionWeightService mAreaActionWeightService, MAreaActionWeightQueryService mAreaActionWeightQueryService) {
        this.mAreaActionWeightService = mAreaActionWeightService;
        this.mAreaActionWeightQueryService = mAreaActionWeightQueryService;
    }

    /**
     * {@code POST  /m-area-action-weights} : Create a new mAreaActionWeight.
     *
     * @param mAreaActionWeightDTO the mAreaActionWeightDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mAreaActionWeightDTO, or with status {@code 400 (Bad Request)} if the mAreaActionWeight has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-area-action-weights")
    public ResponseEntity<MAreaActionWeightDTO> createMAreaActionWeight(@Valid @RequestBody MAreaActionWeightDTO mAreaActionWeightDTO) throws URISyntaxException {
        log.debug("REST request to save MAreaActionWeight : {}", mAreaActionWeightDTO);
        if (mAreaActionWeightDTO.getId() != null) {
            throw new BadRequestAlertException("A new mAreaActionWeight cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MAreaActionWeightDTO result = mAreaActionWeightService.save(mAreaActionWeightDTO);
        return ResponseEntity.created(new URI("/api/m-area-action-weights/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-area-action-weights} : Updates an existing mAreaActionWeight.
     *
     * @param mAreaActionWeightDTO the mAreaActionWeightDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mAreaActionWeightDTO,
     * or with status {@code 400 (Bad Request)} if the mAreaActionWeightDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mAreaActionWeightDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-area-action-weights")
    public ResponseEntity<MAreaActionWeightDTO> updateMAreaActionWeight(@Valid @RequestBody MAreaActionWeightDTO mAreaActionWeightDTO) throws URISyntaxException {
        log.debug("REST request to update MAreaActionWeight : {}", mAreaActionWeightDTO);
        if (mAreaActionWeightDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MAreaActionWeightDTO result = mAreaActionWeightService.save(mAreaActionWeightDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mAreaActionWeightDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-area-action-weights} : get all the mAreaActionWeights.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mAreaActionWeights in body.
     */
    @GetMapping("/m-area-action-weights")
    public ResponseEntity<List<MAreaActionWeightDTO>> getAllMAreaActionWeights(MAreaActionWeightCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MAreaActionWeights by criteria: {}", criteria);
        Page<MAreaActionWeightDTO> page = mAreaActionWeightQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-area-action-weights/count} : count all the mAreaActionWeights.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-area-action-weights/count")
    public ResponseEntity<Long> countMAreaActionWeights(MAreaActionWeightCriteria criteria) {
        log.debug("REST request to count MAreaActionWeights by criteria: {}", criteria);
        return ResponseEntity.ok().body(mAreaActionWeightQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-area-action-weights/:id} : get the "id" mAreaActionWeight.
     *
     * @param id the id of the mAreaActionWeightDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mAreaActionWeightDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-area-action-weights/{id}")
    public ResponseEntity<MAreaActionWeightDTO> getMAreaActionWeight(@PathVariable Long id) {
        log.debug("REST request to get MAreaActionWeight : {}", id);
        Optional<MAreaActionWeightDTO> mAreaActionWeightDTO = mAreaActionWeightService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mAreaActionWeightDTO);
    }

    /**
     * {@code DELETE  /m-area-action-weights/:id} : delete the "id" mAreaActionWeight.
     *
     * @param id the id of the mAreaActionWeightDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-area-action-weights/{id}")
    public ResponseEntity<Void> deleteMAreaActionWeight(@PathVariable Long id) {
        log.debug("REST request to delete MAreaActionWeight : {}", id);
        mAreaActionWeightService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
