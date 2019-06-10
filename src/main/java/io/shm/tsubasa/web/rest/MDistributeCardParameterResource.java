package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDistributeCardParameterService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDistributeCardParameterDTO;
import io.shm.tsubasa.service.dto.MDistributeCardParameterCriteria;
import io.shm.tsubasa.service.MDistributeCardParameterQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDistributeCardParameter}.
 */
@RestController
@RequestMapping("/api")
public class MDistributeCardParameterResource {

    private final Logger log = LoggerFactory.getLogger(MDistributeCardParameterResource.class);

    private static final String ENTITY_NAME = "mDistributeCardParameter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDistributeCardParameterService mDistributeCardParameterService;

    private final MDistributeCardParameterQueryService mDistributeCardParameterQueryService;

    public MDistributeCardParameterResource(MDistributeCardParameterService mDistributeCardParameterService, MDistributeCardParameterQueryService mDistributeCardParameterQueryService) {
        this.mDistributeCardParameterService = mDistributeCardParameterService;
        this.mDistributeCardParameterQueryService = mDistributeCardParameterQueryService;
    }

    /**
     * {@code POST  /m-distribute-card-parameters} : Create a new mDistributeCardParameter.
     *
     * @param mDistributeCardParameterDTO the mDistributeCardParameterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDistributeCardParameterDTO, or with status {@code 400 (Bad Request)} if the mDistributeCardParameter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-distribute-card-parameters")
    public ResponseEntity<MDistributeCardParameterDTO> createMDistributeCardParameter(@Valid @RequestBody MDistributeCardParameterDTO mDistributeCardParameterDTO) throws URISyntaxException {
        log.debug("REST request to save MDistributeCardParameter : {}", mDistributeCardParameterDTO);
        if (mDistributeCardParameterDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDistributeCardParameter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDistributeCardParameterDTO result = mDistributeCardParameterService.save(mDistributeCardParameterDTO);
        return ResponseEntity.created(new URI("/api/m-distribute-card-parameters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-distribute-card-parameters} : Updates an existing mDistributeCardParameter.
     *
     * @param mDistributeCardParameterDTO the mDistributeCardParameterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDistributeCardParameterDTO,
     * or with status {@code 400 (Bad Request)} if the mDistributeCardParameterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDistributeCardParameterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-distribute-card-parameters")
    public ResponseEntity<MDistributeCardParameterDTO> updateMDistributeCardParameter(@Valid @RequestBody MDistributeCardParameterDTO mDistributeCardParameterDTO) throws URISyntaxException {
        log.debug("REST request to update MDistributeCardParameter : {}", mDistributeCardParameterDTO);
        if (mDistributeCardParameterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDistributeCardParameterDTO result = mDistributeCardParameterService.save(mDistributeCardParameterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDistributeCardParameterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-distribute-card-parameters} : get all the mDistributeCardParameters.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDistributeCardParameters in body.
     */
    @GetMapping("/m-distribute-card-parameters")
    public ResponseEntity<List<MDistributeCardParameterDTO>> getAllMDistributeCardParameters(MDistributeCardParameterCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDistributeCardParameters by criteria: {}", criteria);
        Page<MDistributeCardParameterDTO> page = mDistributeCardParameterQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-distribute-card-parameters/count} : count all the mDistributeCardParameters.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-distribute-card-parameters/count")
    public ResponseEntity<Long> countMDistributeCardParameters(MDistributeCardParameterCriteria criteria) {
        log.debug("REST request to count MDistributeCardParameters by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDistributeCardParameterQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-distribute-card-parameters/:id} : get the "id" mDistributeCardParameter.
     *
     * @param id the id of the mDistributeCardParameterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDistributeCardParameterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-distribute-card-parameters/{id}")
    public ResponseEntity<MDistributeCardParameterDTO> getMDistributeCardParameter(@PathVariable Long id) {
        log.debug("REST request to get MDistributeCardParameter : {}", id);
        Optional<MDistributeCardParameterDTO> mDistributeCardParameterDTO = mDistributeCardParameterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDistributeCardParameterDTO);
    }

    /**
     * {@code DELETE  /m-distribute-card-parameters/:id} : delete the "id" mDistributeCardParameter.
     *
     * @param id the id of the mDistributeCardParameterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-distribute-card-parameters/{id}")
    public ResponseEntity<Void> deleteMDistributeCardParameter(@PathVariable Long id) {
        log.debug("REST request to delete MDistributeCardParameter : {}", id);
        mDistributeCardParameterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
