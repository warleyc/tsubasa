package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuildChatDefaultStampService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuildChatDefaultStampDTO;
import io.shm.tsubasa.service.dto.MGuildChatDefaultStampCriteria;
import io.shm.tsubasa.service.MGuildChatDefaultStampQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuildChatDefaultStamp}.
 */
@RestController
@RequestMapping("/api")
public class MGuildChatDefaultStampResource {

    private final Logger log = LoggerFactory.getLogger(MGuildChatDefaultStampResource.class);

    private static final String ENTITY_NAME = "mGuildChatDefaultStamp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuildChatDefaultStampService mGuildChatDefaultStampService;

    private final MGuildChatDefaultStampQueryService mGuildChatDefaultStampQueryService;

    public MGuildChatDefaultStampResource(MGuildChatDefaultStampService mGuildChatDefaultStampService, MGuildChatDefaultStampQueryService mGuildChatDefaultStampQueryService) {
        this.mGuildChatDefaultStampService = mGuildChatDefaultStampService;
        this.mGuildChatDefaultStampQueryService = mGuildChatDefaultStampQueryService;
    }

    /**
     * {@code POST  /m-guild-chat-default-stamps} : Create a new mGuildChatDefaultStamp.
     *
     * @param mGuildChatDefaultStampDTO the mGuildChatDefaultStampDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuildChatDefaultStampDTO, or with status {@code 400 (Bad Request)} if the mGuildChatDefaultStamp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guild-chat-default-stamps")
    public ResponseEntity<MGuildChatDefaultStampDTO> createMGuildChatDefaultStamp(@Valid @RequestBody MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO) throws URISyntaxException {
        log.debug("REST request to save MGuildChatDefaultStamp : {}", mGuildChatDefaultStampDTO);
        if (mGuildChatDefaultStampDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuildChatDefaultStamp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuildChatDefaultStampDTO result = mGuildChatDefaultStampService.save(mGuildChatDefaultStampDTO);
        return ResponseEntity.created(new URI("/api/m-guild-chat-default-stamps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guild-chat-default-stamps} : Updates an existing mGuildChatDefaultStamp.
     *
     * @param mGuildChatDefaultStampDTO the mGuildChatDefaultStampDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuildChatDefaultStampDTO,
     * or with status {@code 400 (Bad Request)} if the mGuildChatDefaultStampDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuildChatDefaultStampDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guild-chat-default-stamps")
    public ResponseEntity<MGuildChatDefaultStampDTO> updateMGuildChatDefaultStamp(@Valid @RequestBody MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO) throws URISyntaxException {
        log.debug("REST request to update MGuildChatDefaultStamp : {}", mGuildChatDefaultStampDTO);
        if (mGuildChatDefaultStampDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuildChatDefaultStampDTO result = mGuildChatDefaultStampService.save(mGuildChatDefaultStampDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuildChatDefaultStampDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guild-chat-default-stamps} : get all the mGuildChatDefaultStamps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuildChatDefaultStamps in body.
     */
    @GetMapping("/m-guild-chat-default-stamps")
    public ResponseEntity<List<MGuildChatDefaultStampDTO>> getAllMGuildChatDefaultStamps(MGuildChatDefaultStampCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuildChatDefaultStamps by criteria: {}", criteria);
        Page<MGuildChatDefaultStampDTO> page = mGuildChatDefaultStampQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guild-chat-default-stamps/count} : count all the mGuildChatDefaultStamps.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guild-chat-default-stamps/count")
    public ResponseEntity<Long> countMGuildChatDefaultStamps(MGuildChatDefaultStampCriteria criteria) {
        log.debug("REST request to count MGuildChatDefaultStamps by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuildChatDefaultStampQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guild-chat-default-stamps/:id} : get the "id" mGuildChatDefaultStamp.
     *
     * @param id the id of the mGuildChatDefaultStampDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuildChatDefaultStampDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guild-chat-default-stamps/{id}")
    public ResponseEntity<MGuildChatDefaultStampDTO> getMGuildChatDefaultStamp(@PathVariable Long id) {
        log.debug("REST request to get MGuildChatDefaultStamp : {}", id);
        Optional<MGuildChatDefaultStampDTO> mGuildChatDefaultStampDTO = mGuildChatDefaultStampService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuildChatDefaultStampDTO);
    }

    /**
     * {@code DELETE  /m-guild-chat-default-stamps/:id} : delete the "id" mGuildChatDefaultStamp.
     *
     * @param id the id of the mGuildChatDefaultStampDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guild-chat-default-stamps/{id}")
    public ResponseEntity<Void> deleteMGuildChatDefaultStamp(@PathVariable Long id) {
        log.debug("REST request to delete MGuildChatDefaultStamp : {}", id);
        mGuildChatDefaultStampService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
