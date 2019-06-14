package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestWorldService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestWorldDTO;
import io.shm.tsubasa.service.dto.MQuestWorldCriteria;
import io.shm.tsubasa.service.MQuestWorldQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestWorld}.
 */
@RestController
@RequestMapping("/api")
public class MQuestWorldResource {

    private final Logger log = LoggerFactory.getLogger(MQuestWorldResource.class);

    private static final String ENTITY_NAME = "mQuestWorld";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestWorldService mQuestWorldService;

    private final MQuestWorldQueryService mQuestWorldQueryService;

    public MQuestWorldResource(MQuestWorldService mQuestWorldService, MQuestWorldQueryService mQuestWorldQueryService) {
        this.mQuestWorldService = mQuestWorldService;
        this.mQuestWorldQueryService = mQuestWorldQueryService;
    }

    /**
     * {@code POST  /m-quest-worlds} : Create a new mQuestWorld.
     *
     * @param mQuestWorldDTO the mQuestWorldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestWorldDTO, or with status {@code 400 (Bad Request)} if the mQuestWorld has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-worlds")
    public ResponseEntity<MQuestWorldDTO> createMQuestWorld(@Valid @RequestBody MQuestWorldDTO mQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestWorld : {}", mQuestWorldDTO);
        if (mQuestWorldDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestWorld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestWorldDTO result = mQuestWorldService.save(mQuestWorldDTO);
        return ResponseEntity.created(new URI("/api/m-quest-worlds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-worlds} : Updates an existing mQuestWorld.
     *
     * @param mQuestWorldDTO the mQuestWorldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestWorldDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestWorldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestWorldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-worlds")
    public ResponseEntity<MQuestWorldDTO> updateMQuestWorld(@Valid @RequestBody MQuestWorldDTO mQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestWorld : {}", mQuestWorldDTO);
        if (mQuestWorldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestWorldDTO result = mQuestWorldService.save(mQuestWorldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestWorldDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-worlds} : get all the mQuestWorlds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestWorlds in body.
     */
    @GetMapping("/m-quest-worlds")
    public ResponseEntity<List<MQuestWorldDTO>> getAllMQuestWorlds(MQuestWorldCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestWorlds by criteria: {}", criteria);
        Page<MQuestWorldDTO> page = mQuestWorldQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-worlds/count} : count all the mQuestWorlds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-worlds/count")
    public ResponseEntity<Long> countMQuestWorlds(MQuestWorldCriteria criteria) {
        log.debug("REST request to count MQuestWorlds by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestWorldQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-worlds/:id} : get the "id" mQuestWorld.
     *
     * @param id the id of the mQuestWorldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestWorldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-worlds/{id}")
    public ResponseEntity<MQuestWorldDTO> getMQuestWorld(@PathVariable Long id) {
        log.debug("REST request to get MQuestWorld : {}", id);
        Optional<MQuestWorldDTO> mQuestWorldDTO = mQuestWorldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestWorldDTO);
    }

    /**
     * {@code DELETE  /m-quest-worlds/:id} : delete the "id" mQuestWorld.
     *
     * @param id the id of the mQuestWorldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-worlds/{id}")
    public ResponseEntity<Void> deleteMQuestWorld(@PathVariable Long id) {
        log.debug("REST request to delete MQuestWorld : {}", id);
        mQuestWorldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
