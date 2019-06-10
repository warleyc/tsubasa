package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MArousalMaterialService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MArousalMaterialDTO;
import io.shm.tsubasa.service.dto.MArousalMaterialCriteria;
import io.shm.tsubasa.service.MArousalMaterialQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MArousalMaterial}.
 */
@RestController
@RequestMapping("/api")
public class MArousalMaterialResource {

    private final Logger log = LoggerFactory.getLogger(MArousalMaterialResource.class);

    private static final String ENTITY_NAME = "mArousalMaterial";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MArousalMaterialService mArousalMaterialService;

    private final MArousalMaterialQueryService mArousalMaterialQueryService;

    public MArousalMaterialResource(MArousalMaterialService mArousalMaterialService, MArousalMaterialQueryService mArousalMaterialQueryService) {
        this.mArousalMaterialService = mArousalMaterialService;
        this.mArousalMaterialQueryService = mArousalMaterialQueryService;
    }

    /**
     * {@code POST  /m-arousal-materials} : Create a new mArousalMaterial.
     *
     * @param mArousalMaterialDTO the mArousalMaterialDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mArousalMaterialDTO, or with status {@code 400 (Bad Request)} if the mArousalMaterial has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-arousal-materials")
    public ResponseEntity<MArousalMaterialDTO> createMArousalMaterial(@Valid @RequestBody MArousalMaterialDTO mArousalMaterialDTO) throws URISyntaxException {
        log.debug("REST request to save MArousalMaterial : {}", mArousalMaterialDTO);
        if (mArousalMaterialDTO.getId() != null) {
            throw new BadRequestAlertException("A new mArousalMaterial cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MArousalMaterialDTO result = mArousalMaterialService.save(mArousalMaterialDTO);
        return ResponseEntity.created(new URI("/api/m-arousal-materials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-arousal-materials} : Updates an existing mArousalMaterial.
     *
     * @param mArousalMaterialDTO the mArousalMaterialDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mArousalMaterialDTO,
     * or with status {@code 400 (Bad Request)} if the mArousalMaterialDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mArousalMaterialDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-arousal-materials")
    public ResponseEntity<MArousalMaterialDTO> updateMArousalMaterial(@Valid @RequestBody MArousalMaterialDTO mArousalMaterialDTO) throws URISyntaxException {
        log.debug("REST request to update MArousalMaterial : {}", mArousalMaterialDTO);
        if (mArousalMaterialDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MArousalMaterialDTO result = mArousalMaterialService.save(mArousalMaterialDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mArousalMaterialDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-arousal-materials} : get all the mArousalMaterials.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mArousalMaterials in body.
     */
    @GetMapping("/m-arousal-materials")
    public ResponseEntity<List<MArousalMaterialDTO>> getAllMArousalMaterials(MArousalMaterialCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MArousalMaterials by criteria: {}", criteria);
        Page<MArousalMaterialDTO> page = mArousalMaterialQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-arousal-materials/count} : count all the mArousalMaterials.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-arousal-materials/count")
    public ResponseEntity<Long> countMArousalMaterials(MArousalMaterialCriteria criteria) {
        log.debug("REST request to count MArousalMaterials by criteria: {}", criteria);
        return ResponseEntity.ok().body(mArousalMaterialQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-arousal-materials/:id} : get the "id" mArousalMaterial.
     *
     * @param id the id of the mArousalMaterialDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mArousalMaterialDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-arousal-materials/{id}")
    public ResponseEntity<MArousalMaterialDTO> getMArousalMaterial(@PathVariable Long id) {
        log.debug("REST request to get MArousalMaterial : {}", id);
        Optional<MArousalMaterialDTO> mArousalMaterialDTO = mArousalMaterialService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mArousalMaterialDTO);
    }

    /**
     * {@code DELETE  /m-arousal-materials/:id} : delete the "id" mArousalMaterial.
     *
     * @param id the id of the mArousalMaterialDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-arousal-materials/{id}")
    public ResponseEntity<Void> deleteMArousalMaterial(@PathVariable Long id) {
        log.debug("REST request to delete MArousalMaterial : {}", id);
        mArousalMaterialService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
