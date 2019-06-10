package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCoopRoomStampService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCoopRoomStampDTO;
import io.shm.tsubasa.service.dto.MCoopRoomStampCriteria;
import io.shm.tsubasa.service.MCoopRoomStampQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCoopRoomStamp}.
 */
@RestController
@RequestMapping("/api")
public class MCoopRoomStampResource {

    private final Logger log = LoggerFactory.getLogger(MCoopRoomStampResource.class);

    private static final String ENTITY_NAME = "mCoopRoomStamp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCoopRoomStampService mCoopRoomStampService;

    private final MCoopRoomStampQueryService mCoopRoomStampQueryService;

    public MCoopRoomStampResource(MCoopRoomStampService mCoopRoomStampService, MCoopRoomStampQueryService mCoopRoomStampQueryService) {
        this.mCoopRoomStampService = mCoopRoomStampService;
        this.mCoopRoomStampQueryService = mCoopRoomStampQueryService;
    }

    /**
     * {@code POST  /m-coop-room-stamps} : Create a new mCoopRoomStamp.
     *
     * @param mCoopRoomStampDTO the mCoopRoomStampDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCoopRoomStampDTO, or with status {@code 400 (Bad Request)} if the mCoopRoomStamp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-coop-room-stamps")
    public ResponseEntity<MCoopRoomStampDTO> createMCoopRoomStamp(@Valid @RequestBody MCoopRoomStampDTO mCoopRoomStampDTO) throws URISyntaxException {
        log.debug("REST request to save MCoopRoomStamp : {}", mCoopRoomStampDTO);
        if (mCoopRoomStampDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCoopRoomStamp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCoopRoomStampDTO result = mCoopRoomStampService.save(mCoopRoomStampDTO);
        return ResponseEntity.created(new URI("/api/m-coop-room-stamps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-coop-room-stamps} : Updates an existing mCoopRoomStamp.
     *
     * @param mCoopRoomStampDTO the mCoopRoomStampDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCoopRoomStampDTO,
     * or with status {@code 400 (Bad Request)} if the mCoopRoomStampDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCoopRoomStampDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-coop-room-stamps")
    public ResponseEntity<MCoopRoomStampDTO> updateMCoopRoomStamp(@Valid @RequestBody MCoopRoomStampDTO mCoopRoomStampDTO) throws URISyntaxException {
        log.debug("REST request to update MCoopRoomStamp : {}", mCoopRoomStampDTO);
        if (mCoopRoomStampDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCoopRoomStampDTO result = mCoopRoomStampService.save(mCoopRoomStampDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCoopRoomStampDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-coop-room-stamps} : get all the mCoopRoomStamps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCoopRoomStamps in body.
     */
    @GetMapping("/m-coop-room-stamps")
    public ResponseEntity<List<MCoopRoomStampDTO>> getAllMCoopRoomStamps(MCoopRoomStampCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCoopRoomStamps by criteria: {}", criteria);
        Page<MCoopRoomStampDTO> page = mCoopRoomStampQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-coop-room-stamps/count} : count all the mCoopRoomStamps.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-coop-room-stamps/count")
    public ResponseEntity<Long> countMCoopRoomStamps(MCoopRoomStampCriteria criteria) {
        log.debug("REST request to count MCoopRoomStamps by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCoopRoomStampQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-coop-room-stamps/:id} : get the "id" mCoopRoomStamp.
     *
     * @param id the id of the mCoopRoomStampDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCoopRoomStampDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-coop-room-stamps/{id}")
    public ResponseEntity<MCoopRoomStampDTO> getMCoopRoomStamp(@PathVariable Long id) {
        log.debug("REST request to get MCoopRoomStamp : {}", id);
        Optional<MCoopRoomStampDTO> mCoopRoomStampDTO = mCoopRoomStampService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCoopRoomStampDTO);
    }

    /**
     * {@code DELETE  /m-coop-room-stamps/:id} : delete the "id" mCoopRoomStamp.
     *
     * @param id the id of the mCoopRoomStampDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-coop-room-stamps/{id}")
    public ResponseEntity<Void> deleteMCoopRoomStamp(@PathVariable Long id) {
        log.debug("REST request to delete MCoopRoomStamp : {}", id);
        mCoopRoomStampService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
