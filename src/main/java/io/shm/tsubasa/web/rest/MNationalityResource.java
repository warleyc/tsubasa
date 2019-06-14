package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MNationalityService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MNationalityDTO;
import io.shm.tsubasa.service.dto.MNationalityCriteria;
import io.shm.tsubasa.service.MNationalityQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MNationality}.
 */
@RestController
@RequestMapping("/api")
public class MNationalityResource {

    private final Logger log = LoggerFactory.getLogger(MNationalityResource.class);

    private static final String ENTITY_NAME = "mNationality";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MNationalityService mNationalityService;

    private final MNationalityQueryService mNationalityQueryService;

    public MNationalityResource(MNationalityService mNationalityService, MNationalityQueryService mNationalityQueryService) {
        this.mNationalityService = mNationalityService;
        this.mNationalityQueryService = mNationalityQueryService;
    }

    /**
     * {@code POST  /m-nationalities} : Create a new mNationality.
     *
     * @param mNationalityDTO the mNationalityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mNationalityDTO, or with status {@code 400 (Bad Request)} if the mNationality has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-nationalities")
    public ResponseEntity<MNationalityDTO> createMNationality(@Valid @RequestBody MNationalityDTO mNationalityDTO) throws URISyntaxException {
        log.debug("REST request to save MNationality : {}", mNationalityDTO);
        if (mNationalityDTO.getId() != null) {
            throw new BadRequestAlertException("A new mNationality cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MNationalityDTO result = mNationalityService.save(mNationalityDTO);
        return ResponseEntity.created(new URI("/api/m-nationalities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-nationalities} : Updates an existing mNationality.
     *
     * @param mNationalityDTO the mNationalityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mNationalityDTO,
     * or with status {@code 400 (Bad Request)} if the mNationalityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mNationalityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-nationalities")
    public ResponseEntity<MNationalityDTO> updateMNationality(@Valid @RequestBody MNationalityDTO mNationalityDTO) throws URISyntaxException {
        log.debug("REST request to update MNationality : {}", mNationalityDTO);
        if (mNationalityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MNationalityDTO result = mNationalityService.save(mNationalityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mNationalityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-nationalities} : get all the mNationalities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mNationalities in body.
     */
    @GetMapping("/m-nationalities")
    public ResponseEntity<List<MNationalityDTO>> getAllMNationalities(MNationalityCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MNationalities by criteria: {}", criteria);
        Page<MNationalityDTO> page = mNationalityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-nationalities/count} : count all the mNationalities.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-nationalities/count")
    public ResponseEntity<Long> countMNationalities(MNationalityCriteria criteria) {
        log.debug("REST request to count MNationalities by criteria: {}", criteria);
        return ResponseEntity.ok().body(mNationalityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-nationalities/:id} : get the "id" mNationality.
     *
     * @param id the id of the mNationalityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mNationalityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-nationalities/{id}")
    public ResponseEntity<MNationalityDTO> getMNationality(@PathVariable Long id) {
        log.debug("REST request to get MNationality : {}", id);
        Optional<MNationalityDTO> mNationalityDTO = mNationalityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mNationalityDTO);
    }

    /**
     * {@code DELETE  /m-nationalities/:id} : delete the "id" mNationality.
     *
     * @param id the id of the mNationalityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-nationalities/{id}")
    public ResponseEntity<Void> deleteMNationality(@PathVariable Long id) {
        log.debug("REST request to delete MNationality : {}", id);
        mNationalityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
