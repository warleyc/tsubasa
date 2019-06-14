package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestCoopService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestCoopDTO;
import io.shm.tsubasa.service.dto.MQuestCoopCriteria;
import io.shm.tsubasa.service.MQuestCoopQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestCoop}.
 */
@RestController
@RequestMapping("/api")
public class MQuestCoopResource {

    private final Logger log = LoggerFactory.getLogger(MQuestCoopResource.class);

    private static final String ENTITY_NAME = "mQuestCoop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestCoopService mQuestCoopService;

    private final MQuestCoopQueryService mQuestCoopQueryService;

    public MQuestCoopResource(MQuestCoopService mQuestCoopService, MQuestCoopQueryService mQuestCoopQueryService) {
        this.mQuestCoopService = mQuestCoopService;
        this.mQuestCoopQueryService = mQuestCoopQueryService;
    }

    /**
     * {@code POST  /m-quest-coops} : Create a new mQuestCoop.
     *
     * @param mQuestCoopDTO the mQuestCoopDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestCoopDTO, or with status {@code 400 (Bad Request)} if the mQuestCoop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-coops")
    public ResponseEntity<MQuestCoopDTO> createMQuestCoop(@Valid @RequestBody MQuestCoopDTO mQuestCoopDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestCoop : {}", mQuestCoopDTO);
        if (mQuestCoopDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestCoop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestCoopDTO result = mQuestCoopService.save(mQuestCoopDTO);
        return ResponseEntity.created(new URI("/api/m-quest-coops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-coops} : Updates an existing mQuestCoop.
     *
     * @param mQuestCoopDTO the mQuestCoopDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestCoopDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestCoopDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestCoopDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-coops")
    public ResponseEntity<MQuestCoopDTO> updateMQuestCoop(@Valid @RequestBody MQuestCoopDTO mQuestCoopDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestCoop : {}", mQuestCoopDTO);
        if (mQuestCoopDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestCoopDTO result = mQuestCoopService.save(mQuestCoopDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestCoopDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-coops} : get all the mQuestCoops.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestCoops in body.
     */
    @GetMapping("/m-quest-coops")
    public ResponseEntity<List<MQuestCoopDTO>> getAllMQuestCoops(MQuestCoopCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestCoops by criteria: {}", criteria);
        Page<MQuestCoopDTO> page = mQuestCoopQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-coops/count} : count all the mQuestCoops.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-coops/count")
    public ResponseEntity<Long> countMQuestCoops(MQuestCoopCriteria criteria) {
        log.debug("REST request to count MQuestCoops by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestCoopQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-coops/:id} : get the "id" mQuestCoop.
     *
     * @param id the id of the mQuestCoopDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestCoopDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-coops/{id}")
    public ResponseEntity<MQuestCoopDTO> getMQuestCoop(@PathVariable Long id) {
        log.debug("REST request to get MQuestCoop : {}", id);
        Optional<MQuestCoopDTO> mQuestCoopDTO = mQuestCoopService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestCoopDTO);
    }

    /**
     * {@code DELETE  /m-quest-coops/:id} : delete the "id" mQuestCoop.
     *
     * @param id the id of the mQuestCoopDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-coops/{id}")
    public ResponseEntity<Void> deleteMQuestCoop(@PathVariable Long id) {
        log.debug("REST request to delete MQuestCoop : {}", id);
        mQuestCoopService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
