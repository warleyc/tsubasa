package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MModelUniformBottomResourceService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MModelUniformBottomResourceDTO;
import io.shm.tsubasa.service.dto.MModelUniformBottomResourceCriteria;
import io.shm.tsubasa.service.MModelUniformBottomResourceQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MModelUniformBottomResource}.
 */
@RestController
@RequestMapping("/api")
public class MModelUniformBottomResourceResource {

    private final Logger log = LoggerFactory.getLogger(MModelUniformBottomResourceResource.class);

    private static final String ENTITY_NAME = "mModelUniformBottomResource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MModelUniformBottomResourceService mModelUniformBottomResourceService;

    private final MModelUniformBottomResourceQueryService mModelUniformBottomResourceQueryService;

    public MModelUniformBottomResourceResource(MModelUniformBottomResourceService mModelUniformBottomResourceService, MModelUniformBottomResourceQueryService mModelUniformBottomResourceQueryService) {
        this.mModelUniformBottomResourceService = mModelUniformBottomResourceService;
        this.mModelUniformBottomResourceQueryService = mModelUniformBottomResourceQueryService;
    }

    /**
     * {@code POST  /m-model-uniform-bottom-resources} : Create a new mModelUniformBottomResource.
     *
     * @param mModelUniformBottomResourceDTO the mModelUniformBottomResourceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mModelUniformBottomResourceDTO, or with status {@code 400 (Bad Request)} if the mModelUniformBottomResource has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-model-uniform-bottom-resources")
    public ResponseEntity<MModelUniformBottomResourceDTO> createMModelUniformBottomResource(@Valid @RequestBody MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO) throws URISyntaxException {
        log.debug("REST request to save MModelUniformBottomResource : {}", mModelUniformBottomResourceDTO);
        if (mModelUniformBottomResourceDTO.getId() != null) {
            throw new BadRequestAlertException("A new mModelUniformBottomResource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MModelUniformBottomResourceDTO result = mModelUniformBottomResourceService.save(mModelUniformBottomResourceDTO);
        return ResponseEntity.created(new URI("/api/m-model-uniform-bottom-resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-model-uniform-bottom-resources} : Updates an existing mModelUniformBottomResource.
     *
     * @param mModelUniformBottomResourceDTO the mModelUniformBottomResourceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mModelUniformBottomResourceDTO,
     * or with status {@code 400 (Bad Request)} if the mModelUniformBottomResourceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mModelUniformBottomResourceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-model-uniform-bottom-resources")
    public ResponseEntity<MModelUniformBottomResourceDTO> updateMModelUniformBottomResource(@Valid @RequestBody MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO) throws URISyntaxException {
        log.debug("REST request to update MModelUniformBottomResource : {}", mModelUniformBottomResourceDTO);
        if (mModelUniformBottomResourceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MModelUniformBottomResourceDTO result = mModelUniformBottomResourceService.save(mModelUniformBottomResourceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mModelUniformBottomResourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-model-uniform-bottom-resources} : get all the mModelUniformBottomResources.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mModelUniformBottomResources in body.
     */
    @GetMapping("/m-model-uniform-bottom-resources")
    public ResponseEntity<List<MModelUniformBottomResourceDTO>> getAllMModelUniformBottomResources(MModelUniformBottomResourceCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MModelUniformBottomResources by criteria: {}", criteria);
        Page<MModelUniformBottomResourceDTO> page = mModelUniformBottomResourceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-model-uniform-bottom-resources/count} : count all the mModelUniformBottomResources.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-model-uniform-bottom-resources/count")
    public ResponseEntity<Long> countMModelUniformBottomResources(MModelUniformBottomResourceCriteria criteria) {
        log.debug("REST request to count MModelUniformBottomResources by criteria: {}", criteria);
        return ResponseEntity.ok().body(mModelUniformBottomResourceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-model-uniform-bottom-resources/:id} : get the "id" mModelUniformBottomResource.
     *
     * @param id the id of the mModelUniformBottomResourceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mModelUniformBottomResourceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-model-uniform-bottom-resources/{id}")
    public ResponseEntity<MModelUniformBottomResourceDTO> getMModelUniformBottomResource(@PathVariable Long id) {
        log.debug("REST request to get MModelUniformBottomResource : {}", id);
        Optional<MModelUniformBottomResourceDTO> mModelUniformBottomResourceDTO = mModelUniformBottomResourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mModelUniformBottomResourceDTO);
    }

    /**
     * {@code DELETE  /m-model-uniform-bottom-resources/:id} : delete the "id" mModelUniformBottomResource.
     *
     * @param id the id of the mModelUniformBottomResourceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-model-uniform-bottom-resources/{id}")
    public ResponseEntity<Void> deleteMModelUniformBottomResource(@PathVariable Long id) {
        log.debug("REST request to delete MModelUniformBottomResource : {}", id);
        mModelUniformBottomResourceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
