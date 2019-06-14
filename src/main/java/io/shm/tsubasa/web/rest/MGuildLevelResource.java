package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuildLevelService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuildLevelDTO;
import io.shm.tsubasa.service.dto.MGuildLevelCriteria;
import io.shm.tsubasa.service.MGuildLevelQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuildLevel}.
 */
@RestController
@RequestMapping("/api")
public class MGuildLevelResource {

    private final Logger log = LoggerFactory.getLogger(MGuildLevelResource.class);

    private static final String ENTITY_NAME = "mGuildLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuildLevelService mGuildLevelService;

    private final MGuildLevelQueryService mGuildLevelQueryService;

    public MGuildLevelResource(MGuildLevelService mGuildLevelService, MGuildLevelQueryService mGuildLevelQueryService) {
        this.mGuildLevelService = mGuildLevelService;
        this.mGuildLevelQueryService = mGuildLevelQueryService;
    }

    /**
     * {@code POST  /m-guild-levels} : Create a new mGuildLevel.
     *
     * @param mGuildLevelDTO the mGuildLevelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuildLevelDTO, or with status {@code 400 (Bad Request)} if the mGuildLevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guild-levels")
    public ResponseEntity<MGuildLevelDTO> createMGuildLevel(@Valid @RequestBody MGuildLevelDTO mGuildLevelDTO) throws URISyntaxException {
        log.debug("REST request to save MGuildLevel : {}", mGuildLevelDTO);
        if (mGuildLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuildLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuildLevelDTO result = mGuildLevelService.save(mGuildLevelDTO);
        return ResponseEntity.created(new URI("/api/m-guild-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guild-levels} : Updates an existing mGuildLevel.
     *
     * @param mGuildLevelDTO the mGuildLevelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuildLevelDTO,
     * or with status {@code 400 (Bad Request)} if the mGuildLevelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuildLevelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guild-levels")
    public ResponseEntity<MGuildLevelDTO> updateMGuildLevel(@Valid @RequestBody MGuildLevelDTO mGuildLevelDTO) throws URISyntaxException {
        log.debug("REST request to update MGuildLevel : {}", mGuildLevelDTO);
        if (mGuildLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuildLevelDTO result = mGuildLevelService.save(mGuildLevelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuildLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guild-levels} : get all the mGuildLevels.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuildLevels in body.
     */
    @GetMapping("/m-guild-levels")
    public ResponseEntity<List<MGuildLevelDTO>> getAllMGuildLevels(MGuildLevelCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuildLevels by criteria: {}", criteria);
        Page<MGuildLevelDTO> page = mGuildLevelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guild-levels/count} : count all the mGuildLevels.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guild-levels/count")
    public ResponseEntity<Long> countMGuildLevels(MGuildLevelCriteria criteria) {
        log.debug("REST request to count MGuildLevels by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuildLevelQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guild-levels/:id} : get the "id" mGuildLevel.
     *
     * @param id the id of the mGuildLevelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuildLevelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guild-levels/{id}")
    public ResponseEntity<MGuildLevelDTO> getMGuildLevel(@PathVariable Long id) {
        log.debug("REST request to get MGuildLevel : {}", id);
        Optional<MGuildLevelDTO> mGuildLevelDTO = mGuildLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuildLevelDTO);
    }

    /**
     * {@code DELETE  /m-guild-levels/:id} : delete the "id" mGuildLevel.
     *
     * @param id the id of the mGuildLevelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guild-levels/{id}")
    public ResponseEntity<Void> deleteMGuildLevel(@PathVariable Long id) {
        log.debug("REST request to delete MGuildLevel : {}", id);
        mGuildLevelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
