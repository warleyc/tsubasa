package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MActionLevelService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MActionLevelDTO;
import io.shm.tsubasa.service.dto.MActionLevelCriteria;
import io.shm.tsubasa.service.MActionLevelQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MActionLevel}.
 */
@RestController
@RequestMapping("/api")
public class MActionLevelResource {

    private final Logger log = LoggerFactory.getLogger(MActionLevelResource.class);

    private static final String ENTITY_NAME = "mActionLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MActionLevelService mActionLevelService;

    private final MActionLevelQueryService mActionLevelQueryService;

    public MActionLevelResource(MActionLevelService mActionLevelService, MActionLevelQueryService mActionLevelQueryService) {
        this.mActionLevelService = mActionLevelService;
        this.mActionLevelQueryService = mActionLevelQueryService;
    }

    /**
     * {@code POST  /m-action-levels} : Create a new mActionLevel.
     *
     * @param mActionLevelDTO the mActionLevelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mActionLevelDTO, or with status {@code 400 (Bad Request)} if the mActionLevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-action-levels")
    public ResponseEntity<MActionLevelDTO> createMActionLevel(@Valid @RequestBody MActionLevelDTO mActionLevelDTO) throws URISyntaxException {
        log.debug("REST request to save MActionLevel : {}", mActionLevelDTO);
        if (mActionLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new mActionLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MActionLevelDTO result = mActionLevelService.save(mActionLevelDTO);
        return ResponseEntity.created(new URI("/api/m-action-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-action-levels} : Updates an existing mActionLevel.
     *
     * @param mActionLevelDTO the mActionLevelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mActionLevelDTO,
     * or with status {@code 400 (Bad Request)} if the mActionLevelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mActionLevelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-action-levels")
    public ResponseEntity<MActionLevelDTO> updateMActionLevel(@Valid @RequestBody MActionLevelDTO mActionLevelDTO) throws URISyntaxException {
        log.debug("REST request to update MActionLevel : {}", mActionLevelDTO);
        if (mActionLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MActionLevelDTO result = mActionLevelService.save(mActionLevelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mActionLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-action-levels} : get all the mActionLevels.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mActionLevels in body.
     */
    @GetMapping("/m-action-levels")
    public ResponseEntity<List<MActionLevelDTO>> getAllMActionLevels(MActionLevelCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MActionLevels by criteria: {}", criteria);
        Page<MActionLevelDTO> page = mActionLevelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-action-levels/count} : count all the mActionLevels.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-action-levels/count")
    public ResponseEntity<Long> countMActionLevels(MActionLevelCriteria criteria) {
        log.debug("REST request to count MActionLevels by criteria: {}", criteria);
        return ResponseEntity.ok().body(mActionLevelQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-action-levels/:id} : get the "id" mActionLevel.
     *
     * @param id the id of the mActionLevelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mActionLevelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-action-levels/{id}")
    public ResponseEntity<MActionLevelDTO> getMActionLevel(@PathVariable Long id) {
        log.debug("REST request to get MActionLevel : {}", id);
        Optional<MActionLevelDTO> mActionLevelDTO = mActionLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mActionLevelDTO);
    }

    /**
     * {@code DELETE  /m-action-levels/:id} : delete the "id" mActionLevel.
     *
     * @param id the id of the mActionLevelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-action-levels/{id}")
    public ResponseEntity<Void> deleteMActionLevel(@PathVariable Long id) {
        log.debug("REST request to delete MActionLevel : {}", id);
        mActionLevelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
