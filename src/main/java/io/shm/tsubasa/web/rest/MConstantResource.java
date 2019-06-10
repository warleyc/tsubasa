package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MConstantService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MConstantDTO;
import io.shm.tsubasa.service.dto.MConstantCriteria;
import io.shm.tsubasa.service.MConstantQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MConstant}.
 */
@RestController
@RequestMapping("/api")
public class MConstantResource {

    private final Logger log = LoggerFactory.getLogger(MConstantResource.class);

    private static final String ENTITY_NAME = "mConstant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MConstantService mConstantService;

    private final MConstantQueryService mConstantQueryService;

    public MConstantResource(MConstantService mConstantService, MConstantQueryService mConstantQueryService) {
        this.mConstantService = mConstantService;
        this.mConstantQueryService = mConstantQueryService;
    }

    /**
     * {@code POST  /m-constants} : Create a new mConstant.
     *
     * @param mConstantDTO the mConstantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mConstantDTO, or with status {@code 400 (Bad Request)} if the mConstant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-constants")
    public ResponseEntity<MConstantDTO> createMConstant(@Valid @RequestBody MConstantDTO mConstantDTO) throws URISyntaxException {
        log.debug("REST request to save MConstant : {}", mConstantDTO);
        if (mConstantDTO.getId() != null) {
            throw new BadRequestAlertException("A new mConstant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MConstantDTO result = mConstantService.save(mConstantDTO);
        return ResponseEntity.created(new URI("/api/m-constants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-constants} : Updates an existing mConstant.
     *
     * @param mConstantDTO the mConstantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mConstantDTO,
     * or with status {@code 400 (Bad Request)} if the mConstantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mConstantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-constants")
    public ResponseEntity<MConstantDTO> updateMConstant(@Valid @RequestBody MConstantDTO mConstantDTO) throws URISyntaxException {
        log.debug("REST request to update MConstant : {}", mConstantDTO);
        if (mConstantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MConstantDTO result = mConstantService.save(mConstantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mConstantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-constants} : get all the mConstants.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mConstants in body.
     */
    @GetMapping("/m-constants")
    public ResponseEntity<List<MConstantDTO>> getAllMConstants(MConstantCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MConstants by criteria: {}", criteria);
        Page<MConstantDTO> page = mConstantQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-constants/count} : count all the mConstants.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-constants/count")
    public ResponseEntity<Long> countMConstants(MConstantCriteria criteria) {
        log.debug("REST request to count MConstants by criteria: {}", criteria);
        return ResponseEntity.ok().body(mConstantQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-constants/:id} : get the "id" mConstant.
     *
     * @param id the id of the mConstantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mConstantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-constants/{id}")
    public ResponseEntity<MConstantDTO> getMConstant(@PathVariable Long id) {
        log.debug("REST request to get MConstant : {}", id);
        Optional<MConstantDTO> mConstantDTO = mConstantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mConstantDTO);
    }

    /**
     * {@code DELETE  /m-constants/:id} : delete the "id" mConstant.
     *
     * @param id the id of the mConstantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-constants/{id}")
    public ResponseEntity<Void> deleteMConstant(@PathVariable Long id) {
        log.debug("REST request to delete MConstant : {}", id);
        mConstantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
