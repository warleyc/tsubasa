package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MFormationService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MFormationDTO;
import io.shm.tsubasa.service.dto.MFormationCriteria;
import io.shm.tsubasa.service.MFormationQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MFormation}.
 */
@RestController
@RequestMapping("/api")
public class MFormationResource {

    private final Logger log = LoggerFactory.getLogger(MFormationResource.class);

    private static final String ENTITY_NAME = "mFormation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MFormationService mFormationService;

    private final MFormationQueryService mFormationQueryService;

    public MFormationResource(MFormationService mFormationService, MFormationQueryService mFormationQueryService) {
        this.mFormationService = mFormationService;
        this.mFormationQueryService = mFormationQueryService;
    }

    /**
     * {@code POST  /m-formations} : Create a new mFormation.
     *
     * @param mFormationDTO the mFormationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mFormationDTO, or with status {@code 400 (Bad Request)} if the mFormation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-formations")
    public ResponseEntity<MFormationDTO> createMFormation(@Valid @RequestBody MFormationDTO mFormationDTO) throws URISyntaxException {
        log.debug("REST request to save MFormation : {}", mFormationDTO);
        if (mFormationDTO.getId() != null) {
            throw new BadRequestAlertException("A new mFormation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MFormationDTO result = mFormationService.save(mFormationDTO);
        return ResponseEntity.created(new URI("/api/m-formations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-formations} : Updates an existing mFormation.
     *
     * @param mFormationDTO the mFormationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mFormationDTO,
     * or with status {@code 400 (Bad Request)} if the mFormationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mFormationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-formations")
    public ResponseEntity<MFormationDTO> updateMFormation(@Valid @RequestBody MFormationDTO mFormationDTO) throws URISyntaxException {
        log.debug("REST request to update MFormation : {}", mFormationDTO);
        if (mFormationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MFormationDTO result = mFormationService.save(mFormationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mFormationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-formations} : get all the mFormations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mFormations in body.
     */
    @GetMapping("/m-formations")
    public ResponseEntity<List<MFormationDTO>> getAllMFormations(MFormationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MFormations by criteria: {}", criteria);
        Page<MFormationDTO> page = mFormationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-formations/count} : count all the mFormations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-formations/count")
    public ResponseEntity<Long> countMFormations(MFormationCriteria criteria) {
        log.debug("REST request to count MFormations by criteria: {}", criteria);
        return ResponseEntity.ok().body(mFormationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-formations/:id} : get the "id" mFormation.
     *
     * @param id the id of the mFormationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mFormationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-formations/{id}")
    public ResponseEntity<MFormationDTO> getMFormation(@PathVariable Long id) {
        log.debug("REST request to get MFormation : {}", id);
        Optional<MFormationDTO> mFormationDTO = mFormationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mFormationDTO);
    }

    /**
     * {@code DELETE  /m-formations/:id} : delete the "id" mFormation.
     *
     * @param id the id of the mFormationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-formations/{id}")
    public ResponseEntity<Void> deleteMFormation(@PathVariable Long id) {
        log.debug("REST request to delete MFormation : {}", id);
        mFormationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
