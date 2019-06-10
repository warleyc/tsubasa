package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MEncountersCutService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MEncountersCutDTO;
import io.shm.tsubasa.service.dto.MEncountersCutCriteria;
import io.shm.tsubasa.service.MEncountersCutQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MEncountersCut}.
 */
@RestController
@RequestMapping("/api")
public class MEncountersCutResource {

    private final Logger log = LoggerFactory.getLogger(MEncountersCutResource.class);

    private static final String ENTITY_NAME = "mEncountersCut";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MEncountersCutService mEncountersCutService;

    private final MEncountersCutQueryService mEncountersCutQueryService;

    public MEncountersCutResource(MEncountersCutService mEncountersCutService, MEncountersCutQueryService mEncountersCutQueryService) {
        this.mEncountersCutService = mEncountersCutService;
        this.mEncountersCutQueryService = mEncountersCutQueryService;
    }

    /**
     * {@code POST  /m-encounters-cuts} : Create a new mEncountersCut.
     *
     * @param mEncountersCutDTO the mEncountersCutDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mEncountersCutDTO, or with status {@code 400 (Bad Request)} if the mEncountersCut has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-encounters-cuts")
    public ResponseEntity<MEncountersCutDTO> createMEncountersCut(@Valid @RequestBody MEncountersCutDTO mEncountersCutDTO) throws URISyntaxException {
        log.debug("REST request to save MEncountersCut : {}", mEncountersCutDTO);
        if (mEncountersCutDTO.getId() != null) {
            throw new BadRequestAlertException("A new mEncountersCut cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MEncountersCutDTO result = mEncountersCutService.save(mEncountersCutDTO);
        return ResponseEntity.created(new URI("/api/m-encounters-cuts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-encounters-cuts} : Updates an existing mEncountersCut.
     *
     * @param mEncountersCutDTO the mEncountersCutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mEncountersCutDTO,
     * or with status {@code 400 (Bad Request)} if the mEncountersCutDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mEncountersCutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-encounters-cuts")
    public ResponseEntity<MEncountersCutDTO> updateMEncountersCut(@Valid @RequestBody MEncountersCutDTO mEncountersCutDTO) throws URISyntaxException {
        log.debug("REST request to update MEncountersCut : {}", mEncountersCutDTO);
        if (mEncountersCutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MEncountersCutDTO result = mEncountersCutService.save(mEncountersCutDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mEncountersCutDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-encounters-cuts} : get all the mEncountersCuts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mEncountersCuts in body.
     */
    @GetMapping("/m-encounters-cuts")
    public ResponseEntity<List<MEncountersCutDTO>> getAllMEncountersCuts(MEncountersCutCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MEncountersCuts by criteria: {}", criteria);
        Page<MEncountersCutDTO> page = mEncountersCutQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-encounters-cuts/count} : count all the mEncountersCuts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-encounters-cuts/count")
    public ResponseEntity<Long> countMEncountersCuts(MEncountersCutCriteria criteria) {
        log.debug("REST request to count MEncountersCuts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mEncountersCutQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-encounters-cuts/:id} : get the "id" mEncountersCut.
     *
     * @param id the id of the mEncountersCutDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mEncountersCutDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-encounters-cuts/{id}")
    public ResponseEntity<MEncountersCutDTO> getMEncountersCut(@PathVariable Long id) {
        log.debug("REST request to get MEncountersCut : {}", id);
        Optional<MEncountersCutDTO> mEncountersCutDTO = mEncountersCutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mEncountersCutDTO);
    }

    /**
     * {@code DELETE  /m-encounters-cuts/:id} : delete the "id" mEncountersCut.
     *
     * @param id the id of the mEncountersCutDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-encounters-cuts/{id}")
    public ResponseEntity<Void> deleteMEncountersCut(@PathVariable Long id) {
        log.debug("REST request to delete MEncountersCut : {}", id);
        mEncountersCutService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
