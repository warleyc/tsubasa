package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuildEffectLevelService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuildEffectLevelDTO;
import io.shm.tsubasa.service.dto.MGuildEffectLevelCriteria;
import io.shm.tsubasa.service.MGuildEffectLevelQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuildEffectLevel}.
 */
@RestController
@RequestMapping("/api")
public class MGuildEffectLevelResource {

    private final Logger log = LoggerFactory.getLogger(MGuildEffectLevelResource.class);

    private static final String ENTITY_NAME = "mGuildEffectLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuildEffectLevelService mGuildEffectLevelService;

    private final MGuildEffectLevelQueryService mGuildEffectLevelQueryService;

    public MGuildEffectLevelResource(MGuildEffectLevelService mGuildEffectLevelService, MGuildEffectLevelQueryService mGuildEffectLevelQueryService) {
        this.mGuildEffectLevelService = mGuildEffectLevelService;
        this.mGuildEffectLevelQueryService = mGuildEffectLevelQueryService;
    }

    /**
     * {@code POST  /m-guild-effect-levels} : Create a new mGuildEffectLevel.
     *
     * @param mGuildEffectLevelDTO the mGuildEffectLevelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuildEffectLevelDTO, or with status {@code 400 (Bad Request)} if the mGuildEffectLevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guild-effect-levels")
    public ResponseEntity<MGuildEffectLevelDTO> createMGuildEffectLevel(@Valid @RequestBody MGuildEffectLevelDTO mGuildEffectLevelDTO) throws URISyntaxException {
        log.debug("REST request to save MGuildEffectLevel : {}", mGuildEffectLevelDTO);
        if (mGuildEffectLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuildEffectLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuildEffectLevelDTO result = mGuildEffectLevelService.save(mGuildEffectLevelDTO);
        return ResponseEntity.created(new URI("/api/m-guild-effect-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guild-effect-levels} : Updates an existing mGuildEffectLevel.
     *
     * @param mGuildEffectLevelDTO the mGuildEffectLevelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuildEffectLevelDTO,
     * or with status {@code 400 (Bad Request)} if the mGuildEffectLevelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuildEffectLevelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guild-effect-levels")
    public ResponseEntity<MGuildEffectLevelDTO> updateMGuildEffectLevel(@Valid @RequestBody MGuildEffectLevelDTO mGuildEffectLevelDTO) throws URISyntaxException {
        log.debug("REST request to update MGuildEffectLevel : {}", mGuildEffectLevelDTO);
        if (mGuildEffectLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuildEffectLevelDTO result = mGuildEffectLevelService.save(mGuildEffectLevelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuildEffectLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guild-effect-levels} : get all the mGuildEffectLevels.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuildEffectLevels in body.
     */
    @GetMapping("/m-guild-effect-levels")
    public ResponseEntity<List<MGuildEffectLevelDTO>> getAllMGuildEffectLevels(MGuildEffectLevelCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuildEffectLevels by criteria: {}", criteria);
        Page<MGuildEffectLevelDTO> page = mGuildEffectLevelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guild-effect-levels/count} : count all the mGuildEffectLevels.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guild-effect-levels/count")
    public ResponseEntity<Long> countMGuildEffectLevels(MGuildEffectLevelCriteria criteria) {
        log.debug("REST request to count MGuildEffectLevels by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuildEffectLevelQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guild-effect-levels/:id} : get the "id" mGuildEffectLevel.
     *
     * @param id the id of the mGuildEffectLevelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuildEffectLevelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guild-effect-levels/{id}")
    public ResponseEntity<MGuildEffectLevelDTO> getMGuildEffectLevel(@PathVariable Long id) {
        log.debug("REST request to get MGuildEffectLevel : {}", id);
        Optional<MGuildEffectLevelDTO> mGuildEffectLevelDTO = mGuildEffectLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuildEffectLevelDTO);
    }

    /**
     * {@code DELETE  /m-guild-effect-levels/:id} : delete the "id" mGuildEffectLevel.
     *
     * @param id the id of the mGuildEffectLevelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guild-effect-levels/{id}")
    public ResponseEntity<Void> deleteMGuildEffectLevel(@PathVariable Long id) {
        log.debug("REST request to delete MGuildEffectLevel : {}", id);
        mGuildEffectLevelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
