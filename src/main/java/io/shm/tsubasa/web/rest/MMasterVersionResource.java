package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMasterVersionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMasterVersionDTO;
import io.shm.tsubasa.service.dto.MMasterVersionCriteria;
import io.shm.tsubasa.service.MMasterVersionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMasterVersion}.
 */
@RestController
@RequestMapping("/api")
public class MMasterVersionResource {

    private final Logger log = LoggerFactory.getLogger(MMasterVersionResource.class);

    private static final String ENTITY_NAME = "mMasterVersion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMasterVersionService mMasterVersionService;

    private final MMasterVersionQueryService mMasterVersionQueryService;

    public MMasterVersionResource(MMasterVersionService mMasterVersionService, MMasterVersionQueryService mMasterVersionQueryService) {
        this.mMasterVersionService = mMasterVersionService;
        this.mMasterVersionQueryService = mMasterVersionQueryService;
    }

    /**
     * {@code POST  /m-master-versions} : Create a new mMasterVersion.
     *
     * @param mMasterVersionDTO the mMasterVersionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMasterVersionDTO, or with status {@code 400 (Bad Request)} if the mMasterVersion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-master-versions")
    public ResponseEntity<MMasterVersionDTO> createMMasterVersion(@Valid @RequestBody MMasterVersionDTO mMasterVersionDTO) throws URISyntaxException {
        log.debug("REST request to save MMasterVersion : {}", mMasterVersionDTO);
        if (mMasterVersionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMasterVersion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMasterVersionDTO result = mMasterVersionService.save(mMasterVersionDTO);
        return ResponseEntity.created(new URI("/api/m-master-versions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-master-versions} : Updates an existing mMasterVersion.
     *
     * @param mMasterVersionDTO the mMasterVersionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMasterVersionDTO,
     * or with status {@code 400 (Bad Request)} if the mMasterVersionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMasterVersionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-master-versions")
    public ResponseEntity<MMasterVersionDTO> updateMMasterVersion(@Valid @RequestBody MMasterVersionDTO mMasterVersionDTO) throws URISyntaxException {
        log.debug("REST request to update MMasterVersion : {}", mMasterVersionDTO);
        if (mMasterVersionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMasterVersionDTO result = mMasterVersionService.save(mMasterVersionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMasterVersionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-master-versions} : get all the mMasterVersions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMasterVersions in body.
     */
    @GetMapping("/m-master-versions")
    public ResponseEntity<List<MMasterVersionDTO>> getAllMMasterVersions(MMasterVersionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMasterVersions by criteria: {}", criteria);
        Page<MMasterVersionDTO> page = mMasterVersionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-master-versions/count} : count all the mMasterVersions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-master-versions/count")
    public ResponseEntity<Long> countMMasterVersions(MMasterVersionCriteria criteria) {
        log.debug("REST request to count MMasterVersions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMasterVersionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-master-versions/:id} : get the "id" mMasterVersion.
     *
     * @param id the id of the mMasterVersionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMasterVersionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-master-versions/{id}")
    public ResponseEntity<MMasterVersionDTO> getMMasterVersion(@PathVariable Long id) {
        log.debug("REST request to get MMasterVersion : {}", id);
        Optional<MMasterVersionDTO> mMasterVersionDTO = mMasterVersionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMasterVersionDTO);
    }

    /**
     * {@code DELETE  /m-master-versions/:id} : delete the "id" mMasterVersion.
     *
     * @param id the id of the mMasterVersionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-master-versions/{id}")
    public ResponseEntity<Void> deleteMMasterVersion(@PathVariable Long id) {
        log.debug("REST request to delete MMasterVersion : {}", id);
        mMasterVersionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
