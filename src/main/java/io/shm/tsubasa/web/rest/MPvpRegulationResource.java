package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPvpRegulationService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPvpRegulationDTO;
import io.shm.tsubasa.service.dto.MPvpRegulationCriteria;
import io.shm.tsubasa.service.MPvpRegulationQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPvpRegulation}.
 */
@RestController
@RequestMapping("/api")
public class MPvpRegulationResource {

    private final Logger log = LoggerFactory.getLogger(MPvpRegulationResource.class);

    private static final String ENTITY_NAME = "mPvpRegulation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPvpRegulationService mPvpRegulationService;

    private final MPvpRegulationQueryService mPvpRegulationQueryService;

    public MPvpRegulationResource(MPvpRegulationService mPvpRegulationService, MPvpRegulationQueryService mPvpRegulationQueryService) {
        this.mPvpRegulationService = mPvpRegulationService;
        this.mPvpRegulationQueryService = mPvpRegulationQueryService;
    }

    /**
     * {@code POST  /m-pvp-regulations} : Create a new mPvpRegulation.
     *
     * @param mPvpRegulationDTO the mPvpRegulationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPvpRegulationDTO, or with status {@code 400 (Bad Request)} if the mPvpRegulation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-pvp-regulations")
    public ResponseEntity<MPvpRegulationDTO> createMPvpRegulation(@Valid @RequestBody MPvpRegulationDTO mPvpRegulationDTO) throws URISyntaxException {
        log.debug("REST request to save MPvpRegulation : {}", mPvpRegulationDTO);
        if (mPvpRegulationDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPvpRegulation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPvpRegulationDTO result = mPvpRegulationService.save(mPvpRegulationDTO);
        return ResponseEntity.created(new URI("/api/m-pvp-regulations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-pvp-regulations} : Updates an existing mPvpRegulation.
     *
     * @param mPvpRegulationDTO the mPvpRegulationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPvpRegulationDTO,
     * or with status {@code 400 (Bad Request)} if the mPvpRegulationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPvpRegulationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-pvp-regulations")
    public ResponseEntity<MPvpRegulationDTO> updateMPvpRegulation(@Valid @RequestBody MPvpRegulationDTO mPvpRegulationDTO) throws URISyntaxException {
        log.debug("REST request to update MPvpRegulation : {}", mPvpRegulationDTO);
        if (mPvpRegulationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPvpRegulationDTO result = mPvpRegulationService.save(mPvpRegulationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPvpRegulationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-pvp-regulations} : get all the mPvpRegulations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPvpRegulations in body.
     */
    @GetMapping("/m-pvp-regulations")
    public ResponseEntity<List<MPvpRegulationDTO>> getAllMPvpRegulations(MPvpRegulationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPvpRegulations by criteria: {}", criteria);
        Page<MPvpRegulationDTO> page = mPvpRegulationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-pvp-regulations/count} : count all the mPvpRegulations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-pvp-regulations/count")
    public ResponseEntity<Long> countMPvpRegulations(MPvpRegulationCriteria criteria) {
        log.debug("REST request to count MPvpRegulations by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPvpRegulationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-pvp-regulations/:id} : get the "id" mPvpRegulation.
     *
     * @param id the id of the mPvpRegulationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPvpRegulationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-pvp-regulations/{id}")
    public ResponseEntity<MPvpRegulationDTO> getMPvpRegulation(@PathVariable Long id) {
        log.debug("REST request to get MPvpRegulation : {}", id);
        Optional<MPvpRegulationDTO> mPvpRegulationDTO = mPvpRegulationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPvpRegulationDTO);
    }

    /**
     * {@code DELETE  /m-pvp-regulations/:id} : delete the "id" mPvpRegulation.
     *
     * @param id the id of the mPvpRegulationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-pvp-regulations/{id}")
    public ResponseEntity<Void> deleteMPvpRegulation(@PathVariable Long id) {
        log.debug("REST request to delete MPvpRegulation : {}", id);
        mPvpRegulationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
