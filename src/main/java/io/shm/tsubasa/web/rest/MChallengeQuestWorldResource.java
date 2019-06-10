package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MChallengeQuestWorldService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MChallengeQuestWorldDTO;
import io.shm.tsubasa.service.dto.MChallengeQuestWorldCriteria;
import io.shm.tsubasa.service.MChallengeQuestWorldQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MChallengeQuestWorld}.
 */
@RestController
@RequestMapping("/api")
public class MChallengeQuestWorldResource {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestWorldResource.class);

    private static final String ENTITY_NAME = "mChallengeQuestWorld";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MChallengeQuestWorldService mChallengeQuestWorldService;

    private final MChallengeQuestWorldQueryService mChallengeQuestWorldQueryService;

    public MChallengeQuestWorldResource(MChallengeQuestWorldService mChallengeQuestWorldService, MChallengeQuestWorldQueryService mChallengeQuestWorldQueryService) {
        this.mChallengeQuestWorldService = mChallengeQuestWorldService;
        this.mChallengeQuestWorldQueryService = mChallengeQuestWorldQueryService;
    }

    /**
     * {@code POST  /m-challenge-quest-worlds} : Create a new mChallengeQuestWorld.
     *
     * @param mChallengeQuestWorldDTO the mChallengeQuestWorldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mChallengeQuestWorldDTO, or with status {@code 400 (Bad Request)} if the mChallengeQuestWorld has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-challenge-quest-worlds")
    public ResponseEntity<MChallengeQuestWorldDTO> createMChallengeQuestWorld(@Valid @RequestBody MChallengeQuestWorldDTO mChallengeQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to save MChallengeQuestWorld : {}", mChallengeQuestWorldDTO);
        if (mChallengeQuestWorldDTO.getId() != null) {
            throw new BadRequestAlertException("A new mChallengeQuestWorld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MChallengeQuestWorldDTO result = mChallengeQuestWorldService.save(mChallengeQuestWorldDTO);
        return ResponseEntity.created(new URI("/api/m-challenge-quest-worlds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-challenge-quest-worlds} : Updates an existing mChallengeQuestWorld.
     *
     * @param mChallengeQuestWorldDTO the mChallengeQuestWorldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mChallengeQuestWorldDTO,
     * or with status {@code 400 (Bad Request)} if the mChallengeQuestWorldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mChallengeQuestWorldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-challenge-quest-worlds")
    public ResponseEntity<MChallengeQuestWorldDTO> updateMChallengeQuestWorld(@Valid @RequestBody MChallengeQuestWorldDTO mChallengeQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to update MChallengeQuestWorld : {}", mChallengeQuestWorldDTO);
        if (mChallengeQuestWorldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MChallengeQuestWorldDTO result = mChallengeQuestWorldService.save(mChallengeQuestWorldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mChallengeQuestWorldDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-challenge-quest-worlds} : get all the mChallengeQuestWorlds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mChallengeQuestWorlds in body.
     */
    @GetMapping("/m-challenge-quest-worlds")
    public ResponseEntity<List<MChallengeQuestWorldDTO>> getAllMChallengeQuestWorlds(MChallengeQuestWorldCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MChallengeQuestWorlds by criteria: {}", criteria);
        Page<MChallengeQuestWorldDTO> page = mChallengeQuestWorldQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-challenge-quest-worlds/count} : count all the mChallengeQuestWorlds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-challenge-quest-worlds/count")
    public ResponseEntity<Long> countMChallengeQuestWorlds(MChallengeQuestWorldCriteria criteria) {
        log.debug("REST request to count MChallengeQuestWorlds by criteria: {}", criteria);
        return ResponseEntity.ok().body(mChallengeQuestWorldQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-challenge-quest-worlds/:id} : get the "id" mChallengeQuestWorld.
     *
     * @param id the id of the mChallengeQuestWorldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mChallengeQuestWorldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-challenge-quest-worlds/{id}")
    public ResponseEntity<MChallengeQuestWorldDTO> getMChallengeQuestWorld(@PathVariable Long id) {
        log.debug("REST request to get MChallengeQuestWorld : {}", id);
        Optional<MChallengeQuestWorldDTO> mChallengeQuestWorldDTO = mChallengeQuestWorldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mChallengeQuestWorldDTO);
    }

    /**
     * {@code DELETE  /m-challenge-quest-worlds/:id} : delete the "id" mChallengeQuestWorld.
     *
     * @param id the id of the mChallengeQuestWorldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-challenge-quest-worlds/{id}")
    public ResponseEntity<Void> deleteMChallengeQuestWorld(@PathVariable Long id) {
        log.debug("REST request to delete MChallengeQuestWorld : {}", id);
        mChallengeQuestWorldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
