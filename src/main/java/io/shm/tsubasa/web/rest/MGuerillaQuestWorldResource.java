package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuerillaQuestWorldService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuerillaQuestWorldDTO;
import io.shm.tsubasa.service.dto.MGuerillaQuestWorldCriteria;
import io.shm.tsubasa.service.MGuerillaQuestWorldQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuerillaQuestWorld}.
 */
@RestController
@RequestMapping("/api")
public class MGuerillaQuestWorldResource {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestWorldResource.class);

    private static final String ENTITY_NAME = "mGuerillaQuestWorld";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuerillaQuestWorldService mGuerillaQuestWorldService;

    private final MGuerillaQuestWorldQueryService mGuerillaQuestWorldQueryService;

    public MGuerillaQuestWorldResource(MGuerillaQuestWorldService mGuerillaQuestWorldService, MGuerillaQuestWorldQueryService mGuerillaQuestWorldQueryService) {
        this.mGuerillaQuestWorldService = mGuerillaQuestWorldService;
        this.mGuerillaQuestWorldQueryService = mGuerillaQuestWorldQueryService;
    }

    /**
     * {@code POST  /m-guerilla-quest-worlds} : Create a new mGuerillaQuestWorld.
     *
     * @param mGuerillaQuestWorldDTO the mGuerillaQuestWorldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuerillaQuestWorldDTO, or with status {@code 400 (Bad Request)} if the mGuerillaQuestWorld has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guerilla-quest-worlds")
    public ResponseEntity<MGuerillaQuestWorldDTO> createMGuerillaQuestWorld(@Valid @RequestBody MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to save MGuerillaQuestWorld : {}", mGuerillaQuestWorldDTO);
        if (mGuerillaQuestWorldDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuerillaQuestWorld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuerillaQuestWorldDTO result = mGuerillaQuestWorldService.save(mGuerillaQuestWorldDTO);
        return ResponseEntity.created(new URI("/api/m-guerilla-quest-worlds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guerilla-quest-worlds} : Updates an existing mGuerillaQuestWorld.
     *
     * @param mGuerillaQuestWorldDTO the mGuerillaQuestWorldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuerillaQuestWorldDTO,
     * or with status {@code 400 (Bad Request)} if the mGuerillaQuestWorldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuerillaQuestWorldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guerilla-quest-worlds")
    public ResponseEntity<MGuerillaQuestWorldDTO> updateMGuerillaQuestWorld(@Valid @RequestBody MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to update MGuerillaQuestWorld : {}", mGuerillaQuestWorldDTO);
        if (mGuerillaQuestWorldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuerillaQuestWorldDTO result = mGuerillaQuestWorldService.save(mGuerillaQuestWorldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuerillaQuestWorldDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guerilla-quest-worlds} : get all the mGuerillaQuestWorlds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuerillaQuestWorlds in body.
     */
    @GetMapping("/m-guerilla-quest-worlds")
    public ResponseEntity<List<MGuerillaQuestWorldDTO>> getAllMGuerillaQuestWorlds(MGuerillaQuestWorldCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuerillaQuestWorlds by criteria: {}", criteria);
        Page<MGuerillaQuestWorldDTO> page = mGuerillaQuestWorldQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guerilla-quest-worlds/count} : count all the mGuerillaQuestWorlds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guerilla-quest-worlds/count")
    public ResponseEntity<Long> countMGuerillaQuestWorlds(MGuerillaQuestWorldCriteria criteria) {
        log.debug("REST request to count MGuerillaQuestWorlds by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuerillaQuestWorldQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guerilla-quest-worlds/:id} : get the "id" mGuerillaQuestWorld.
     *
     * @param id the id of the mGuerillaQuestWorldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuerillaQuestWorldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guerilla-quest-worlds/{id}")
    public ResponseEntity<MGuerillaQuestWorldDTO> getMGuerillaQuestWorld(@PathVariable Long id) {
        log.debug("REST request to get MGuerillaQuestWorld : {}", id);
        Optional<MGuerillaQuestWorldDTO> mGuerillaQuestWorldDTO = mGuerillaQuestWorldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuerillaQuestWorldDTO);
    }

    /**
     * {@code DELETE  /m-guerilla-quest-worlds/:id} : delete the "id" mGuerillaQuestWorld.
     *
     * @param id the id of the mGuerillaQuestWorldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guerilla-quest-worlds/{id}")
    public ResponseEntity<Void> deleteMGuerillaQuestWorld(@PathVariable Long id) {
        log.debug("REST request to delete MGuerillaQuestWorld : {}", id);
        mGuerillaQuestWorldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
