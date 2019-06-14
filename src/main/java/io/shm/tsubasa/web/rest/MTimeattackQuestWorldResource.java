package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTimeattackQuestWorldService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTimeattackQuestWorldDTO;
import io.shm.tsubasa.service.dto.MTimeattackQuestWorldCriteria;
import io.shm.tsubasa.service.MTimeattackQuestWorldQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTimeattackQuestWorld}.
 */
@RestController
@RequestMapping("/api")
public class MTimeattackQuestWorldResource {

    private final Logger log = LoggerFactory.getLogger(MTimeattackQuestWorldResource.class);

    private static final String ENTITY_NAME = "mTimeattackQuestWorld";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTimeattackQuestWorldService mTimeattackQuestWorldService;

    private final MTimeattackQuestWorldQueryService mTimeattackQuestWorldQueryService;

    public MTimeattackQuestWorldResource(MTimeattackQuestWorldService mTimeattackQuestWorldService, MTimeattackQuestWorldQueryService mTimeattackQuestWorldQueryService) {
        this.mTimeattackQuestWorldService = mTimeattackQuestWorldService;
        this.mTimeattackQuestWorldQueryService = mTimeattackQuestWorldQueryService;
    }

    /**
     * {@code POST  /m-timeattack-quest-worlds} : Create a new mTimeattackQuestWorld.
     *
     * @param mTimeattackQuestWorldDTO the mTimeattackQuestWorldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTimeattackQuestWorldDTO, or with status {@code 400 (Bad Request)} if the mTimeattackQuestWorld has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-timeattack-quest-worlds")
    public ResponseEntity<MTimeattackQuestWorldDTO> createMTimeattackQuestWorld(@Valid @RequestBody MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to save MTimeattackQuestWorld : {}", mTimeattackQuestWorldDTO);
        if (mTimeattackQuestWorldDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTimeattackQuestWorld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTimeattackQuestWorldDTO result = mTimeattackQuestWorldService.save(mTimeattackQuestWorldDTO);
        return ResponseEntity.created(new URI("/api/m-timeattack-quest-worlds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-timeattack-quest-worlds} : Updates an existing mTimeattackQuestWorld.
     *
     * @param mTimeattackQuestWorldDTO the mTimeattackQuestWorldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTimeattackQuestWorldDTO,
     * or with status {@code 400 (Bad Request)} if the mTimeattackQuestWorldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTimeattackQuestWorldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-timeattack-quest-worlds")
    public ResponseEntity<MTimeattackQuestWorldDTO> updateMTimeattackQuestWorld(@Valid @RequestBody MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to update MTimeattackQuestWorld : {}", mTimeattackQuestWorldDTO);
        if (mTimeattackQuestWorldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTimeattackQuestWorldDTO result = mTimeattackQuestWorldService.save(mTimeattackQuestWorldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTimeattackQuestWorldDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-timeattack-quest-worlds} : get all the mTimeattackQuestWorlds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTimeattackQuestWorlds in body.
     */
    @GetMapping("/m-timeattack-quest-worlds")
    public ResponseEntity<List<MTimeattackQuestWorldDTO>> getAllMTimeattackQuestWorlds(MTimeattackQuestWorldCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTimeattackQuestWorlds by criteria: {}", criteria);
        Page<MTimeattackQuestWorldDTO> page = mTimeattackQuestWorldQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-timeattack-quest-worlds/count} : count all the mTimeattackQuestWorlds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-timeattack-quest-worlds/count")
    public ResponseEntity<Long> countMTimeattackQuestWorlds(MTimeattackQuestWorldCriteria criteria) {
        log.debug("REST request to count MTimeattackQuestWorlds by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTimeattackQuestWorldQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-timeattack-quest-worlds/:id} : get the "id" mTimeattackQuestWorld.
     *
     * @param id the id of the mTimeattackQuestWorldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTimeattackQuestWorldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-timeattack-quest-worlds/{id}")
    public ResponseEntity<MTimeattackQuestWorldDTO> getMTimeattackQuestWorld(@PathVariable Long id) {
        log.debug("REST request to get MTimeattackQuestWorld : {}", id);
        Optional<MTimeattackQuestWorldDTO> mTimeattackQuestWorldDTO = mTimeattackQuestWorldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTimeattackQuestWorldDTO);
    }

    /**
     * {@code DELETE  /m-timeattack-quest-worlds/:id} : delete the "id" mTimeattackQuestWorld.
     *
     * @param id the id of the mTimeattackQuestWorldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-timeattack-quest-worlds/{id}")
    public ResponseEntity<Void> deleteMTimeattackQuestWorld(@PathVariable Long id) {
        log.debug("REST request to delete MTimeattackQuestWorld : {}", id);
        mTimeattackQuestWorldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
