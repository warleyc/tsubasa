package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MModelUniformBottomService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MModelUniformBottomDTO;
import io.shm.tsubasa.service.dto.MModelUniformBottomCriteria;
import io.shm.tsubasa.service.MModelUniformBottomQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MModelUniformBottom}.
 */
@RestController
@RequestMapping("/api")
public class MModelUniformBottomResource {

    private final Logger log = LoggerFactory.getLogger(MModelUniformBottomResource.class);

    private static final String ENTITY_NAME = "mModelUniformBottom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MModelUniformBottomService mModelUniformBottomService;

    private final MModelUniformBottomQueryService mModelUniformBottomQueryService;

    public MModelUniformBottomResource(MModelUniformBottomService mModelUniformBottomService, MModelUniformBottomQueryService mModelUniformBottomQueryService) {
        this.mModelUniformBottomService = mModelUniformBottomService;
        this.mModelUniformBottomQueryService = mModelUniformBottomQueryService;
    }

    /**
     * {@code POST  /m-model-uniform-bottoms} : Create a new mModelUniformBottom.
     *
     * @param mModelUniformBottomDTO the mModelUniformBottomDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mModelUniformBottomDTO, or with status {@code 400 (Bad Request)} if the mModelUniformBottom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-model-uniform-bottoms")
    public ResponseEntity<MModelUniformBottomDTO> createMModelUniformBottom(@Valid @RequestBody MModelUniformBottomDTO mModelUniformBottomDTO) throws URISyntaxException {
        log.debug("REST request to save MModelUniformBottom : {}", mModelUniformBottomDTO);
        if (mModelUniformBottomDTO.getId() != null) {
            throw new BadRequestAlertException("A new mModelUniformBottom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MModelUniformBottomDTO result = mModelUniformBottomService.save(mModelUniformBottomDTO);
        return ResponseEntity.created(new URI("/api/m-model-uniform-bottoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-model-uniform-bottoms} : Updates an existing mModelUniformBottom.
     *
     * @param mModelUniformBottomDTO the mModelUniformBottomDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mModelUniformBottomDTO,
     * or with status {@code 400 (Bad Request)} if the mModelUniformBottomDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mModelUniformBottomDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-model-uniform-bottoms")
    public ResponseEntity<MModelUniformBottomDTO> updateMModelUniformBottom(@Valid @RequestBody MModelUniformBottomDTO mModelUniformBottomDTO) throws URISyntaxException {
        log.debug("REST request to update MModelUniformBottom : {}", mModelUniformBottomDTO);
        if (mModelUniformBottomDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MModelUniformBottomDTO result = mModelUniformBottomService.save(mModelUniformBottomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mModelUniformBottomDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-model-uniform-bottoms} : get all the mModelUniformBottoms.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mModelUniformBottoms in body.
     */
    @GetMapping("/m-model-uniform-bottoms")
    public ResponseEntity<List<MModelUniformBottomDTO>> getAllMModelUniformBottoms(MModelUniformBottomCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MModelUniformBottoms by criteria: {}", criteria);
        Page<MModelUniformBottomDTO> page = mModelUniformBottomQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-model-uniform-bottoms/count} : count all the mModelUniformBottoms.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-model-uniform-bottoms/count")
    public ResponseEntity<Long> countMModelUniformBottoms(MModelUniformBottomCriteria criteria) {
        log.debug("REST request to count MModelUniformBottoms by criteria: {}", criteria);
        return ResponseEntity.ok().body(mModelUniformBottomQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-model-uniform-bottoms/:id} : get the "id" mModelUniformBottom.
     *
     * @param id the id of the mModelUniformBottomDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mModelUniformBottomDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-model-uniform-bottoms/{id}")
    public ResponseEntity<MModelUniformBottomDTO> getMModelUniformBottom(@PathVariable Long id) {
        log.debug("REST request to get MModelUniformBottom : {}", id);
        Optional<MModelUniformBottomDTO> mModelUniformBottomDTO = mModelUniformBottomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mModelUniformBottomDTO);
    }

    /**
     * {@code DELETE  /m-model-uniform-bottoms/:id} : delete the "id" mModelUniformBottom.
     *
     * @param id the id of the mModelUniformBottomDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-model-uniform-bottoms/{id}")
    public ResponseEntity<Void> deleteMModelUniformBottom(@PathVariable Long id) {
        log.debug("REST request to delete MModelUniformBottom : {}", id);
        mModelUniformBottomService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
