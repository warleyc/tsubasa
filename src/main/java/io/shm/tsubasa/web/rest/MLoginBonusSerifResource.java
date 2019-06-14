package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLoginBonusSerifService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLoginBonusSerifDTO;
import io.shm.tsubasa.service.dto.MLoginBonusSerifCriteria;
import io.shm.tsubasa.service.MLoginBonusSerifQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLoginBonusSerif}.
 */
@RestController
@RequestMapping("/api")
public class MLoginBonusSerifResource {

    private final Logger log = LoggerFactory.getLogger(MLoginBonusSerifResource.class);

    private static final String ENTITY_NAME = "mLoginBonusSerif";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLoginBonusSerifService mLoginBonusSerifService;

    private final MLoginBonusSerifQueryService mLoginBonusSerifQueryService;

    public MLoginBonusSerifResource(MLoginBonusSerifService mLoginBonusSerifService, MLoginBonusSerifQueryService mLoginBonusSerifQueryService) {
        this.mLoginBonusSerifService = mLoginBonusSerifService;
        this.mLoginBonusSerifQueryService = mLoginBonusSerifQueryService;
    }

    /**
     * {@code POST  /m-login-bonus-serifs} : Create a new mLoginBonusSerif.
     *
     * @param mLoginBonusSerifDTO the mLoginBonusSerifDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLoginBonusSerifDTO, or with status {@code 400 (Bad Request)} if the mLoginBonusSerif has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-login-bonus-serifs")
    public ResponseEntity<MLoginBonusSerifDTO> createMLoginBonusSerif(@Valid @RequestBody MLoginBonusSerifDTO mLoginBonusSerifDTO) throws URISyntaxException {
        log.debug("REST request to save MLoginBonusSerif : {}", mLoginBonusSerifDTO);
        if (mLoginBonusSerifDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLoginBonusSerif cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLoginBonusSerifDTO result = mLoginBonusSerifService.save(mLoginBonusSerifDTO);
        return ResponseEntity.created(new URI("/api/m-login-bonus-serifs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-login-bonus-serifs} : Updates an existing mLoginBonusSerif.
     *
     * @param mLoginBonusSerifDTO the mLoginBonusSerifDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLoginBonusSerifDTO,
     * or with status {@code 400 (Bad Request)} if the mLoginBonusSerifDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLoginBonusSerifDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-login-bonus-serifs")
    public ResponseEntity<MLoginBonusSerifDTO> updateMLoginBonusSerif(@Valid @RequestBody MLoginBonusSerifDTO mLoginBonusSerifDTO) throws URISyntaxException {
        log.debug("REST request to update MLoginBonusSerif : {}", mLoginBonusSerifDTO);
        if (mLoginBonusSerifDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLoginBonusSerifDTO result = mLoginBonusSerifService.save(mLoginBonusSerifDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLoginBonusSerifDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-login-bonus-serifs} : get all the mLoginBonusSerifs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLoginBonusSerifs in body.
     */
    @GetMapping("/m-login-bonus-serifs")
    public ResponseEntity<List<MLoginBonusSerifDTO>> getAllMLoginBonusSerifs(MLoginBonusSerifCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLoginBonusSerifs by criteria: {}", criteria);
        Page<MLoginBonusSerifDTO> page = mLoginBonusSerifQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-login-bonus-serifs/count} : count all the mLoginBonusSerifs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-login-bonus-serifs/count")
    public ResponseEntity<Long> countMLoginBonusSerifs(MLoginBonusSerifCriteria criteria) {
        log.debug("REST request to count MLoginBonusSerifs by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLoginBonusSerifQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-login-bonus-serifs/:id} : get the "id" mLoginBonusSerif.
     *
     * @param id the id of the mLoginBonusSerifDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLoginBonusSerifDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-login-bonus-serifs/{id}")
    public ResponseEntity<MLoginBonusSerifDTO> getMLoginBonusSerif(@PathVariable Long id) {
        log.debug("REST request to get MLoginBonusSerif : {}", id);
        Optional<MLoginBonusSerifDTO> mLoginBonusSerifDTO = mLoginBonusSerifService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLoginBonusSerifDTO);
    }

    /**
     * {@code DELETE  /m-login-bonus-serifs/:id} : delete the "id" mLoginBonusSerif.
     *
     * @param id the id of the mLoginBonusSerifDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-login-bonus-serifs/{id}")
    public ResponseEntity<Void> deleteMLoginBonusSerif(@PathVariable Long id) {
        log.debug("REST request to delete MLoginBonusSerif : {}", id);
        mLoginBonusSerifService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
