package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMatchFormationService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMatchFormationDTO;
import io.shm.tsubasa.service.dto.MMatchFormationCriteria;
import io.shm.tsubasa.service.MMatchFormationQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMatchFormation}.
 */
@RestController
@RequestMapping("/api")
public class MMatchFormationResource {

    private final Logger log = LoggerFactory.getLogger(MMatchFormationResource.class);

    private static final String ENTITY_NAME = "mMatchFormation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMatchFormationService mMatchFormationService;

    private final MMatchFormationQueryService mMatchFormationQueryService;

    public MMatchFormationResource(MMatchFormationService mMatchFormationService, MMatchFormationQueryService mMatchFormationQueryService) {
        this.mMatchFormationService = mMatchFormationService;
        this.mMatchFormationQueryService = mMatchFormationQueryService;
    }

    /**
     * {@code POST  /m-match-formations} : Create a new mMatchFormation.
     *
     * @param mMatchFormationDTO the mMatchFormationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMatchFormationDTO, or with status {@code 400 (Bad Request)} if the mMatchFormation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-match-formations")
    public ResponseEntity<MMatchFormationDTO> createMMatchFormation(@Valid @RequestBody MMatchFormationDTO mMatchFormationDTO) throws URISyntaxException {
        log.debug("REST request to save MMatchFormation : {}", mMatchFormationDTO);
        if (mMatchFormationDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMatchFormation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMatchFormationDTO result = mMatchFormationService.save(mMatchFormationDTO);
        return ResponseEntity.created(new URI("/api/m-match-formations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-match-formations} : Updates an existing mMatchFormation.
     *
     * @param mMatchFormationDTO the mMatchFormationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMatchFormationDTO,
     * or with status {@code 400 (Bad Request)} if the mMatchFormationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMatchFormationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-match-formations")
    public ResponseEntity<MMatchFormationDTO> updateMMatchFormation(@Valid @RequestBody MMatchFormationDTO mMatchFormationDTO) throws URISyntaxException {
        log.debug("REST request to update MMatchFormation : {}", mMatchFormationDTO);
        if (mMatchFormationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMatchFormationDTO result = mMatchFormationService.save(mMatchFormationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMatchFormationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-match-formations} : get all the mMatchFormations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMatchFormations in body.
     */
    @GetMapping("/m-match-formations")
    public ResponseEntity<List<MMatchFormationDTO>> getAllMMatchFormations(MMatchFormationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMatchFormations by criteria: {}", criteria);
        Page<MMatchFormationDTO> page = mMatchFormationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-match-formations/count} : count all the mMatchFormations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-match-formations/count")
    public ResponseEntity<Long> countMMatchFormations(MMatchFormationCriteria criteria) {
        log.debug("REST request to count MMatchFormations by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMatchFormationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-match-formations/:id} : get the "id" mMatchFormation.
     *
     * @param id the id of the mMatchFormationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMatchFormationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-match-formations/{id}")
    public ResponseEntity<MMatchFormationDTO> getMMatchFormation(@PathVariable Long id) {
        log.debug("REST request to get MMatchFormation : {}", id);
        Optional<MMatchFormationDTO> mMatchFormationDTO = mMatchFormationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMatchFormationDTO);
    }

    /**
     * {@code DELETE  /m-match-formations/:id} : delete the "id" mMatchFormation.
     *
     * @param id the id of the mMatchFormationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-match-formations/{id}")
    public ResponseEntity<Void> deleteMMatchFormation(@PathVariable Long id) {
        log.debug("REST request to delete MMatchFormation : {}", id);
        mMatchFormationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
