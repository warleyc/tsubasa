package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MModelUniformUpResourceService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MModelUniformUpResourceDTO;
import io.shm.tsubasa.service.dto.MModelUniformUpResourceCriteria;
import io.shm.tsubasa.service.MModelUniformUpResourceQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MModelUniformUpResource}.
 */
@RestController
@RequestMapping("/api")
public class MModelUniformUpResourceResource {

    private final Logger log = LoggerFactory.getLogger(MModelUniformUpResourceResource.class);

    private static final String ENTITY_NAME = "mModelUniformUpResource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MModelUniformUpResourceService mModelUniformUpResourceService;

    private final MModelUniformUpResourceQueryService mModelUniformUpResourceQueryService;

    public MModelUniformUpResourceResource(MModelUniformUpResourceService mModelUniformUpResourceService, MModelUniformUpResourceQueryService mModelUniformUpResourceQueryService) {
        this.mModelUniformUpResourceService = mModelUniformUpResourceService;
        this.mModelUniformUpResourceQueryService = mModelUniformUpResourceQueryService;
    }

    /**
     * {@code POST  /m-model-uniform-up-resources} : Create a new mModelUniformUpResource.
     *
     * @param mModelUniformUpResourceDTO the mModelUniformUpResourceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mModelUniformUpResourceDTO, or with status {@code 400 (Bad Request)} if the mModelUniformUpResource has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-model-uniform-up-resources")
    public ResponseEntity<MModelUniformUpResourceDTO> createMModelUniformUpResource(@Valid @RequestBody MModelUniformUpResourceDTO mModelUniformUpResourceDTO) throws URISyntaxException {
        log.debug("REST request to save MModelUniformUpResource : {}", mModelUniformUpResourceDTO);
        if (mModelUniformUpResourceDTO.getId() != null) {
            throw new BadRequestAlertException("A new mModelUniformUpResource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MModelUniformUpResourceDTO result = mModelUniformUpResourceService.save(mModelUniformUpResourceDTO);
        return ResponseEntity.created(new URI("/api/m-model-uniform-up-resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-model-uniform-up-resources} : Updates an existing mModelUniformUpResource.
     *
     * @param mModelUniformUpResourceDTO the mModelUniformUpResourceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mModelUniformUpResourceDTO,
     * or with status {@code 400 (Bad Request)} if the mModelUniformUpResourceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mModelUniformUpResourceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-model-uniform-up-resources")
    public ResponseEntity<MModelUniformUpResourceDTO> updateMModelUniformUpResource(@Valid @RequestBody MModelUniformUpResourceDTO mModelUniformUpResourceDTO) throws URISyntaxException {
        log.debug("REST request to update MModelUniformUpResource : {}", mModelUniformUpResourceDTO);
        if (mModelUniformUpResourceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MModelUniformUpResourceDTO result = mModelUniformUpResourceService.save(mModelUniformUpResourceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mModelUniformUpResourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-model-uniform-up-resources} : get all the mModelUniformUpResources.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mModelUniformUpResources in body.
     */
    @GetMapping("/m-model-uniform-up-resources")
    public ResponseEntity<List<MModelUniformUpResourceDTO>> getAllMModelUniformUpResources(MModelUniformUpResourceCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MModelUniformUpResources by criteria: {}", criteria);
        Page<MModelUniformUpResourceDTO> page = mModelUniformUpResourceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-model-uniform-up-resources/count} : count all the mModelUniformUpResources.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-model-uniform-up-resources/count")
    public ResponseEntity<Long> countMModelUniformUpResources(MModelUniformUpResourceCriteria criteria) {
        log.debug("REST request to count MModelUniformUpResources by criteria: {}", criteria);
        return ResponseEntity.ok().body(mModelUniformUpResourceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-model-uniform-up-resources/:id} : get the "id" mModelUniformUpResource.
     *
     * @param id the id of the mModelUniformUpResourceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mModelUniformUpResourceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-model-uniform-up-resources/{id}")
    public ResponseEntity<MModelUniformUpResourceDTO> getMModelUniformUpResource(@PathVariable Long id) {
        log.debug("REST request to get MModelUniformUpResource : {}", id);
        Optional<MModelUniformUpResourceDTO> mModelUniformUpResourceDTO = mModelUniformUpResourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mModelUniformUpResourceDTO);
    }

    /**
     * {@code DELETE  /m-model-uniform-up-resources/:id} : delete the "id" mModelUniformUpResource.
     *
     * @param id the id of the mModelUniformUpResourceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-model-uniform-up-resources/{id}")
    public ResponseEntity<Void> deleteMModelUniformUpResource(@PathVariable Long id) {
        log.debug("REST request to delete MModelUniformUpResource : {}", id);
        mModelUniformUpResourceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
