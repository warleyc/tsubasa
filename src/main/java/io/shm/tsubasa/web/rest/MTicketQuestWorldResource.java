package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTicketQuestWorldService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTicketQuestWorldDTO;
import io.shm.tsubasa.service.dto.MTicketQuestWorldCriteria;
import io.shm.tsubasa.service.MTicketQuestWorldQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTicketQuestWorld}.
 */
@RestController
@RequestMapping("/api")
public class MTicketQuestWorldResource {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestWorldResource.class);

    private static final String ENTITY_NAME = "mTicketQuestWorld";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTicketQuestWorldService mTicketQuestWorldService;

    private final MTicketQuestWorldQueryService mTicketQuestWorldQueryService;

    public MTicketQuestWorldResource(MTicketQuestWorldService mTicketQuestWorldService, MTicketQuestWorldQueryService mTicketQuestWorldQueryService) {
        this.mTicketQuestWorldService = mTicketQuestWorldService;
        this.mTicketQuestWorldQueryService = mTicketQuestWorldQueryService;
    }

    /**
     * {@code POST  /m-ticket-quest-worlds} : Create a new mTicketQuestWorld.
     *
     * @param mTicketQuestWorldDTO the mTicketQuestWorldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTicketQuestWorldDTO, or with status {@code 400 (Bad Request)} if the mTicketQuestWorld has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-ticket-quest-worlds")
    public ResponseEntity<MTicketQuestWorldDTO> createMTicketQuestWorld(@Valid @RequestBody MTicketQuestWorldDTO mTicketQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to save MTicketQuestWorld : {}", mTicketQuestWorldDTO);
        if (mTicketQuestWorldDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTicketQuestWorld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTicketQuestWorldDTO result = mTicketQuestWorldService.save(mTicketQuestWorldDTO);
        return ResponseEntity.created(new URI("/api/m-ticket-quest-worlds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-ticket-quest-worlds} : Updates an existing mTicketQuestWorld.
     *
     * @param mTicketQuestWorldDTO the mTicketQuestWorldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTicketQuestWorldDTO,
     * or with status {@code 400 (Bad Request)} if the mTicketQuestWorldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTicketQuestWorldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-ticket-quest-worlds")
    public ResponseEntity<MTicketQuestWorldDTO> updateMTicketQuestWorld(@Valid @RequestBody MTicketQuestWorldDTO mTicketQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to update MTicketQuestWorld : {}", mTicketQuestWorldDTO);
        if (mTicketQuestWorldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTicketQuestWorldDTO result = mTicketQuestWorldService.save(mTicketQuestWorldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTicketQuestWorldDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-ticket-quest-worlds} : get all the mTicketQuestWorlds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTicketQuestWorlds in body.
     */
    @GetMapping("/m-ticket-quest-worlds")
    public ResponseEntity<List<MTicketQuestWorldDTO>> getAllMTicketQuestWorlds(MTicketQuestWorldCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTicketQuestWorlds by criteria: {}", criteria);
        Page<MTicketQuestWorldDTO> page = mTicketQuestWorldQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-ticket-quest-worlds/count} : count all the mTicketQuestWorlds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-ticket-quest-worlds/count")
    public ResponseEntity<Long> countMTicketQuestWorlds(MTicketQuestWorldCriteria criteria) {
        log.debug("REST request to count MTicketQuestWorlds by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTicketQuestWorldQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-ticket-quest-worlds/:id} : get the "id" mTicketQuestWorld.
     *
     * @param id the id of the mTicketQuestWorldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTicketQuestWorldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-ticket-quest-worlds/{id}")
    public ResponseEntity<MTicketQuestWorldDTO> getMTicketQuestWorld(@PathVariable Long id) {
        log.debug("REST request to get MTicketQuestWorld : {}", id);
        Optional<MTicketQuestWorldDTO> mTicketQuestWorldDTO = mTicketQuestWorldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTicketQuestWorldDTO);
    }

    /**
     * {@code DELETE  /m-ticket-quest-worlds/:id} : delete the "id" mTicketQuestWorld.
     *
     * @param id the id of the mTicketQuestWorldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-ticket-quest-worlds/{id}")
    public ResponseEntity<Void> deleteMTicketQuestWorld(@PathVariable Long id) {
        log.debug("REST request to delete MTicketQuestWorld : {}", id);
        mTicketQuestWorldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
