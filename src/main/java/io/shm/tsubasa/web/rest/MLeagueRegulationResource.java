package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLeagueRegulationService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLeagueRegulationDTO;
import io.shm.tsubasa.service.dto.MLeagueRegulationCriteria;
import io.shm.tsubasa.service.MLeagueRegulationQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLeagueRegulation}.
 */
@RestController
@RequestMapping("/api")
public class MLeagueRegulationResource {

    private final Logger log = LoggerFactory.getLogger(MLeagueRegulationResource.class);

    private static final String ENTITY_NAME = "mLeagueRegulation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLeagueRegulationService mLeagueRegulationService;

    private final MLeagueRegulationQueryService mLeagueRegulationQueryService;

    public MLeagueRegulationResource(MLeagueRegulationService mLeagueRegulationService, MLeagueRegulationQueryService mLeagueRegulationQueryService) {
        this.mLeagueRegulationService = mLeagueRegulationService;
        this.mLeagueRegulationQueryService = mLeagueRegulationQueryService;
    }

    /**
     * {@code POST  /m-league-regulations} : Create a new mLeagueRegulation.
     *
     * @param mLeagueRegulationDTO the mLeagueRegulationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLeagueRegulationDTO, or with status {@code 400 (Bad Request)} if the mLeagueRegulation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-league-regulations")
    public ResponseEntity<MLeagueRegulationDTO> createMLeagueRegulation(@Valid @RequestBody MLeagueRegulationDTO mLeagueRegulationDTO) throws URISyntaxException {
        log.debug("REST request to save MLeagueRegulation : {}", mLeagueRegulationDTO);
        if (mLeagueRegulationDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLeagueRegulation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLeagueRegulationDTO result = mLeagueRegulationService.save(mLeagueRegulationDTO);
        return ResponseEntity.created(new URI("/api/m-league-regulations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-league-regulations} : Updates an existing mLeagueRegulation.
     *
     * @param mLeagueRegulationDTO the mLeagueRegulationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLeagueRegulationDTO,
     * or with status {@code 400 (Bad Request)} if the mLeagueRegulationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLeagueRegulationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-league-regulations")
    public ResponseEntity<MLeagueRegulationDTO> updateMLeagueRegulation(@Valid @RequestBody MLeagueRegulationDTO mLeagueRegulationDTO) throws URISyntaxException {
        log.debug("REST request to update MLeagueRegulation : {}", mLeagueRegulationDTO);
        if (mLeagueRegulationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLeagueRegulationDTO result = mLeagueRegulationService.save(mLeagueRegulationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLeagueRegulationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-league-regulations} : get all the mLeagueRegulations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLeagueRegulations in body.
     */
    @GetMapping("/m-league-regulations")
    public ResponseEntity<List<MLeagueRegulationDTO>> getAllMLeagueRegulations(MLeagueRegulationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLeagueRegulations by criteria: {}", criteria);
        Page<MLeagueRegulationDTO> page = mLeagueRegulationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-league-regulations/count} : count all the mLeagueRegulations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-league-regulations/count")
    public ResponseEntity<Long> countMLeagueRegulations(MLeagueRegulationCriteria criteria) {
        log.debug("REST request to count MLeagueRegulations by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLeagueRegulationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-league-regulations/:id} : get the "id" mLeagueRegulation.
     *
     * @param id the id of the mLeagueRegulationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLeagueRegulationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-league-regulations/{id}")
    public ResponseEntity<MLeagueRegulationDTO> getMLeagueRegulation(@PathVariable Long id) {
        log.debug("REST request to get MLeagueRegulation : {}", id);
        Optional<MLeagueRegulationDTO> mLeagueRegulationDTO = mLeagueRegulationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLeagueRegulationDTO);
    }

    /**
     * {@code DELETE  /m-league-regulations/:id} : delete the "id" mLeagueRegulation.
     *
     * @param id the id of the mLeagueRegulationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-league-regulations/{id}")
    public ResponseEntity<Void> deleteMLeagueRegulation(@PathVariable Long id) {
        log.debug("REST request to delete MLeagueRegulation : {}", id);
        mLeagueRegulationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
