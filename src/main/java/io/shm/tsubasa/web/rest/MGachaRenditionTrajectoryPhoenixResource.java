package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionTrajectoryPhoenixService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryPhoenixDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryPhoenixCriteria;
import io.shm.tsubasa.service.MGachaRenditionTrajectoryPhoenixQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionTrajectoryPhoenix}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionTrajectoryPhoenixResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTrajectoryPhoenixResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionTrajectoryPhoenix";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionTrajectoryPhoenixService mGachaRenditionTrajectoryPhoenixService;

    private final MGachaRenditionTrajectoryPhoenixQueryService mGachaRenditionTrajectoryPhoenixQueryService;

    public MGachaRenditionTrajectoryPhoenixResource(MGachaRenditionTrajectoryPhoenixService mGachaRenditionTrajectoryPhoenixService, MGachaRenditionTrajectoryPhoenixQueryService mGachaRenditionTrajectoryPhoenixQueryService) {
        this.mGachaRenditionTrajectoryPhoenixService = mGachaRenditionTrajectoryPhoenixService;
        this.mGachaRenditionTrajectoryPhoenixQueryService = mGachaRenditionTrajectoryPhoenixQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-trajectory-phoenixes} : Create a new mGachaRenditionTrajectoryPhoenix.
     *
     * @param mGachaRenditionTrajectoryPhoenixDTO the mGachaRenditionTrajectoryPhoenixDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionTrajectoryPhoenixDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionTrajectoryPhoenix has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-trajectory-phoenixes")
    public ResponseEntity<MGachaRenditionTrajectoryPhoenixDTO> createMGachaRenditionTrajectoryPhoenix(@Valid @RequestBody MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionTrajectoryPhoenix : {}", mGachaRenditionTrajectoryPhoenixDTO);
        if (mGachaRenditionTrajectoryPhoenixDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionTrajectoryPhoenix cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionTrajectoryPhoenixDTO result = mGachaRenditionTrajectoryPhoenixService.save(mGachaRenditionTrajectoryPhoenixDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-trajectory-phoenixes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-trajectory-phoenixes} : Updates an existing mGachaRenditionTrajectoryPhoenix.
     *
     * @param mGachaRenditionTrajectoryPhoenixDTO the mGachaRenditionTrajectoryPhoenixDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionTrajectoryPhoenixDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionTrajectoryPhoenixDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionTrajectoryPhoenixDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-trajectory-phoenixes")
    public ResponseEntity<MGachaRenditionTrajectoryPhoenixDTO> updateMGachaRenditionTrajectoryPhoenix(@Valid @RequestBody MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionTrajectoryPhoenix : {}", mGachaRenditionTrajectoryPhoenixDTO);
        if (mGachaRenditionTrajectoryPhoenixDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionTrajectoryPhoenixDTO result = mGachaRenditionTrajectoryPhoenixService.save(mGachaRenditionTrajectoryPhoenixDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionTrajectoryPhoenixDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-trajectory-phoenixes} : get all the mGachaRenditionTrajectoryPhoenixes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionTrajectoryPhoenixes in body.
     */
    @GetMapping("/m-gacha-rendition-trajectory-phoenixes")
    public ResponseEntity<List<MGachaRenditionTrajectoryPhoenixDTO>> getAllMGachaRenditionTrajectoryPhoenixes(MGachaRenditionTrajectoryPhoenixCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionTrajectoryPhoenixes by criteria: {}", criteria);
        Page<MGachaRenditionTrajectoryPhoenixDTO> page = mGachaRenditionTrajectoryPhoenixQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-trajectory-phoenixes/count} : count all the mGachaRenditionTrajectoryPhoenixes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-trajectory-phoenixes/count")
    public ResponseEntity<Long> countMGachaRenditionTrajectoryPhoenixes(MGachaRenditionTrajectoryPhoenixCriteria criteria) {
        log.debug("REST request to count MGachaRenditionTrajectoryPhoenixes by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionTrajectoryPhoenixQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-trajectory-phoenixes/:id} : get the "id" mGachaRenditionTrajectoryPhoenix.
     *
     * @param id the id of the mGachaRenditionTrajectoryPhoenixDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionTrajectoryPhoenixDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-trajectory-phoenixes/{id}")
    public ResponseEntity<MGachaRenditionTrajectoryPhoenixDTO> getMGachaRenditionTrajectoryPhoenix(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionTrajectoryPhoenix : {}", id);
        Optional<MGachaRenditionTrajectoryPhoenixDTO> mGachaRenditionTrajectoryPhoenixDTO = mGachaRenditionTrajectoryPhoenixService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionTrajectoryPhoenixDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-trajectory-phoenixes/:id} : delete the "id" mGachaRenditionTrajectoryPhoenix.
     *
     * @param id the id of the mGachaRenditionTrajectoryPhoenixDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-trajectory-phoenixes/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionTrajectoryPhoenix(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionTrajectoryPhoenix : {}", id);
        mGachaRenditionTrajectoryPhoenixService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
