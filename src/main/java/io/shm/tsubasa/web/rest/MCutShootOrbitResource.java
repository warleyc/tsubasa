package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCutShootOrbitService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCutShootOrbitDTO;
import io.shm.tsubasa.service.dto.MCutShootOrbitCriteria;
import io.shm.tsubasa.service.MCutShootOrbitQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCutShootOrbit}.
 */
@RestController
@RequestMapping("/api")
public class MCutShootOrbitResource {

    private final Logger log = LoggerFactory.getLogger(MCutShootOrbitResource.class);

    private static final String ENTITY_NAME = "mCutShootOrbit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCutShootOrbitService mCutShootOrbitService;

    private final MCutShootOrbitQueryService mCutShootOrbitQueryService;

    public MCutShootOrbitResource(MCutShootOrbitService mCutShootOrbitService, MCutShootOrbitQueryService mCutShootOrbitQueryService) {
        this.mCutShootOrbitService = mCutShootOrbitService;
        this.mCutShootOrbitQueryService = mCutShootOrbitQueryService;
    }

    /**
     * {@code POST  /m-cut-shoot-orbits} : Create a new mCutShootOrbit.
     *
     * @param mCutShootOrbitDTO the mCutShootOrbitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCutShootOrbitDTO, or with status {@code 400 (Bad Request)} if the mCutShootOrbit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-cut-shoot-orbits")
    public ResponseEntity<MCutShootOrbitDTO> createMCutShootOrbit(@Valid @RequestBody MCutShootOrbitDTO mCutShootOrbitDTO) throws URISyntaxException {
        log.debug("REST request to save MCutShootOrbit : {}", mCutShootOrbitDTO);
        if (mCutShootOrbitDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCutShootOrbit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCutShootOrbitDTO result = mCutShootOrbitService.save(mCutShootOrbitDTO);
        return ResponseEntity.created(new URI("/api/m-cut-shoot-orbits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-cut-shoot-orbits} : Updates an existing mCutShootOrbit.
     *
     * @param mCutShootOrbitDTO the mCutShootOrbitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCutShootOrbitDTO,
     * or with status {@code 400 (Bad Request)} if the mCutShootOrbitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCutShootOrbitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-cut-shoot-orbits")
    public ResponseEntity<MCutShootOrbitDTO> updateMCutShootOrbit(@Valid @RequestBody MCutShootOrbitDTO mCutShootOrbitDTO) throws URISyntaxException {
        log.debug("REST request to update MCutShootOrbit : {}", mCutShootOrbitDTO);
        if (mCutShootOrbitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCutShootOrbitDTO result = mCutShootOrbitService.save(mCutShootOrbitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCutShootOrbitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-cut-shoot-orbits} : get all the mCutShootOrbits.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCutShootOrbits in body.
     */
    @GetMapping("/m-cut-shoot-orbits")
    public ResponseEntity<List<MCutShootOrbitDTO>> getAllMCutShootOrbits(MCutShootOrbitCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCutShootOrbits by criteria: {}", criteria);
        Page<MCutShootOrbitDTO> page = mCutShootOrbitQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-cut-shoot-orbits/count} : count all the mCutShootOrbits.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-cut-shoot-orbits/count")
    public ResponseEntity<Long> countMCutShootOrbits(MCutShootOrbitCriteria criteria) {
        log.debug("REST request to count MCutShootOrbits by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCutShootOrbitQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-cut-shoot-orbits/:id} : get the "id" mCutShootOrbit.
     *
     * @param id the id of the mCutShootOrbitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCutShootOrbitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-cut-shoot-orbits/{id}")
    public ResponseEntity<MCutShootOrbitDTO> getMCutShootOrbit(@PathVariable Long id) {
        log.debug("REST request to get MCutShootOrbit : {}", id);
        Optional<MCutShootOrbitDTO> mCutShootOrbitDTO = mCutShootOrbitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCutShootOrbitDTO);
    }

    /**
     * {@code DELETE  /m-cut-shoot-orbits/:id} : delete the "id" mCutShootOrbit.
     *
     * @param id the id of the mCutShootOrbitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-cut-shoot-orbits/{id}")
    public ResponseEntity<Void> deleteMCutShootOrbit(@PathVariable Long id) {
        log.debug("REST request to delete MCutShootOrbit : {}", id);
        mCutShootOrbitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
