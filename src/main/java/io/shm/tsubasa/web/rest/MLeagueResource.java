package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLeagueService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLeagueDTO;
import io.shm.tsubasa.service.dto.MLeagueCriteria;
import io.shm.tsubasa.service.MLeagueQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLeague}.
 */
@RestController
@RequestMapping("/api")
public class MLeagueResource {

    private final Logger log = LoggerFactory.getLogger(MLeagueResource.class);

    private static final String ENTITY_NAME = "mLeague";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLeagueService mLeagueService;

    private final MLeagueQueryService mLeagueQueryService;

    public MLeagueResource(MLeagueService mLeagueService, MLeagueQueryService mLeagueQueryService) {
        this.mLeagueService = mLeagueService;
        this.mLeagueQueryService = mLeagueQueryService;
    }

    /**
     * {@code POST  /m-leagues} : Create a new mLeague.
     *
     * @param mLeagueDTO the mLeagueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLeagueDTO, or with status {@code 400 (Bad Request)} if the mLeague has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-leagues")
    public ResponseEntity<MLeagueDTO> createMLeague(@Valid @RequestBody MLeagueDTO mLeagueDTO) throws URISyntaxException {
        log.debug("REST request to save MLeague : {}", mLeagueDTO);
        if (mLeagueDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLeague cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLeagueDTO result = mLeagueService.save(mLeagueDTO);
        return ResponseEntity.created(new URI("/api/m-leagues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-leagues} : Updates an existing mLeague.
     *
     * @param mLeagueDTO the mLeagueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLeagueDTO,
     * or with status {@code 400 (Bad Request)} if the mLeagueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLeagueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-leagues")
    public ResponseEntity<MLeagueDTO> updateMLeague(@Valid @RequestBody MLeagueDTO mLeagueDTO) throws URISyntaxException {
        log.debug("REST request to update MLeague : {}", mLeagueDTO);
        if (mLeagueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLeagueDTO result = mLeagueService.save(mLeagueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLeagueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-leagues} : get all the mLeagues.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLeagues in body.
     */
    @GetMapping("/m-leagues")
    public ResponseEntity<List<MLeagueDTO>> getAllMLeagues(MLeagueCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLeagues by criteria: {}", criteria);
        Page<MLeagueDTO> page = mLeagueQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-leagues/count} : count all the mLeagues.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-leagues/count")
    public ResponseEntity<Long> countMLeagues(MLeagueCriteria criteria) {
        log.debug("REST request to count MLeagues by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLeagueQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-leagues/:id} : get the "id" mLeague.
     *
     * @param id the id of the mLeagueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLeagueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-leagues/{id}")
    public ResponseEntity<MLeagueDTO> getMLeague(@PathVariable Long id) {
        log.debug("REST request to get MLeague : {}", id);
        Optional<MLeagueDTO> mLeagueDTO = mLeagueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLeagueDTO);
    }

    /**
     * {@code DELETE  /m-leagues/:id} : delete the "id" mLeague.
     *
     * @param id the id of the mLeagueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-leagues/{id}")
    public ResponseEntity<Void> deleteMLeague(@PathVariable Long id) {
        log.debug("REST request to delete MLeague : {}", id);
        mLeagueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
