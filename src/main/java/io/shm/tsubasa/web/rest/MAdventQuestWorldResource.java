package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MAdventQuestWorldService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MAdventQuestWorldDTO;
import io.shm.tsubasa.service.dto.MAdventQuestWorldCriteria;
import io.shm.tsubasa.service.MAdventQuestWorldQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MAdventQuestWorld}.
 */
@RestController
@RequestMapping("/api")
public class MAdventQuestWorldResource {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestWorldResource.class);

    private static final String ENTITY_NAME = "mAdventQuestWorld";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MAdventQuestWorldService mAdventQuestWorldService;

    private final MAdventQuestWorldQueryService mAdventQuestWorldQueryService;

    public MAdventQuestWorldResource(MAdventQuestWorldService mAdventQuestWorldService, MAdventQuestWorldQueryService mAdventQuestWorldQueryService) {
        this.mAdventQuestWorldService = mAdventQuestWorldService;
        this.mAdventQuestWorldQueryService = mAdventQuestWorldQueryService;
    }

    /**
     * {@code POST  /m-advent-quest-worlds} : Create a new mAdventQuestWorld.
     *
     * @param mAdventQuestWorldDTO the mAdventQuestWorldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mAdventQuestWorldDTO, or with status {@code 400 (Bad Request)} if the mAdventQuestWorld has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-advent-quest-worlds")
    public ResponseEntity<MAdventQuestWorldDTO> createMAdventQuestWorld(@Valid @RequestBody MAdventQuestWorldDTO mAdventQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to save MAdventQuestWorld : {}", mAdventQuestWorldDTO);
        if (mAdventQuestWorldDTO.getId() != null) {
            throw new BadRequestAlertException("A new mAdventQuestWorld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MAdventQuestWorldDTO result = mAdventQuestWorldService.save(mAdventQuestWorldDTO);
        return ResponseEntity.created(new URI("/api/m-advent-quest-worlds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-advent-quest-worlds} : Updates an existing mAdventQuestWorld.
     *
     * @param mAdventQuestWorldDTO the mAdventQuestWorldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mAdventQuestWorldDTO,
     * or with status {@code 400 (Bad Request)} if the mAdventQuestWorldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mAdventQuestWorldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-advent-quest-worlds")
    public ResponseEntity<MAdventQuestWorldDTO> updateMAdventQuestWorld(@Valid @RequestBody MAdventQuestWorldDTO mAdventQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to update MAdventQuestWorld : {}", mAdventQuestWorldDTO);
        if (mAdventQuestWorldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MAdventQuestWorldDTO result = mAdventQuestWorldService.save(mAdventQuestWorldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mAdventQuestWorldDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-advent-quest-worlds} : get all the mAdventQuestWorlds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mAdventQuestWorlds in body.
     */
    @GetMapping("/m-advent-quest-worlds")
    public ResponseEntity<List<MAdventQuestWorldDTO>> getAllMAdventQuestWorlds(MAdventQuestWorldCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MAdventQuestWorlds by criteria: {}", criteria);
        Page<MAdventQuestWorldDTO> page = mAdventQuestWorldQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-advent-quest-worlds/count} : count all the mAdventQuestWorlds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-advent-quest-worlds/count")
    public ResponseEntity<Long> countMAdventQuestWorlds(MAdventQuestWorldCriteria criteria) {
        log.debug("REST request to count MAdventQuestWorlds by criteria: {}", criteria);
        return ResponseEntity.ok().body(mAdventQuestWorldQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-advent-quest-worlds/:id} : get the "id" mAdventQuestWorld.
     *
     * @param id the id of the mAdventQuestWorldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mAdventQuestWorldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-advent-quest-worlds/{id}")
    public ResponseEntity<MAdventQuestWorldDTO> getMAdventQuestWorld(@PathVariable Long id) {
        log.debug("REST request to get MAdventQuestWorld : {}", id);
        Optional<MAdventQuestWorldDTO> mAdventQuestWorldDTO = mAdventQuestWorldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mAdventQuestWorldDTO);
    }

    /**
     * {@code DELETE  /m-advent-quest-worlds/:id} : delete the "id" mAdventQuestWorld.
     *
     * @param id the id of the mAdventQuestWorldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-advent-quest-worlds/{id}")
    public ResponseEntity<Void> deleteMAdventQuestWorld(@PathVariable Long id) {
        log.debug("REST request to delete MAdventQuestWorld : {}", id);
        mAdventQuestWorldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
