package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionBallService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionBallDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionBallCriteria;
import io.shm.tsubasa.service.MGachaRenditionBallQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionBall}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionBallResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionBallResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionBall";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionBallService mGachaRenditionBallService;

    private final MGachaRenditionBallQueryService mGachaRenditionBallQueryService;

    public MGachaRenditionBallResource(MGachaRenditionBallService mGachaRenditionBallService, MGachaRenditionBallQueryService mGachaRenditionBallQueryService) {
        this.mGachaRenditionBallService = mGachaRenditionBallService;
        this.mGachaRenditionBallQueryService = mGachaRenditionBallQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-balls} : Create a new mGachaRenditionBall.
     *
     * @param mGachaRenditionBallDTO the mGachaRenditionBallDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionBallDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionBall has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-balls")
    public ResponseEntity<MGachaRenditionBallDTO> createMGachaRenditionBall(@Valid @RequestBody MGachaRenditionBallDTO mGachaRenditionBallDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionBall : {}", mGachaRenditionBallDTO);
        if (mGachaRenditionBallDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionBall cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionBallDTO result = mGachaRenditionBallService.save(mGachaRenditionBallDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-balls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-balls} : Updates an existing mGachaRenditionBall.
     *
     * @param mGachaRenditionBallDTO the mGachaRenditionBallDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionBallDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionBallDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionBallDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-balls")
    public ResponseEntity<MGachaRenditionBallDTO> updateMGachaRenditionBall(@Valid @RequestBody MGachaRenditionBallDTO mGachaRenditionBallDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionBall : {}", mGachaRenditionBallDTO);
        if (mGachaRenditionBallDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionBallDTO result = mGachaRenditionBallService.save(mGachaRenditionBallDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionBallDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-balls} : get all the mGachaRenditionBalls.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionBalls in body.
     */
    @GetMapping("/m-gacha-rendition-balls")
    public ResponseEntity<List<MGachaRenditionBallDTO>> getAllMGachaRenditionBalls(MGachaRenditionBallCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionBalls by criteria: {}", criteria);
        Page<MGachaRenditionBallDTO> page = mGachaRenditionBallQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-balls/count} : count all the mGachaRenditionBalls.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-balls/count")
    public ResponseEntity<Long> countMGachaRenditionBalls(MGachaRenditionBallCriteria criteria) {
        log.debug("REST request to count MGachaRenditionBalls by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionBallQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-balls/:id} : get the "id" mGachaRenditionBall.
     *
     * @param id the id of the mGachaRenditionBallDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionBallDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-balls/{id}")
    public ResponseEntity<MGachaRenditionBallDTO> getMGachaRenditionBall(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionBall : {}", id);
        Optional<MGachaRenditionBallDTO> mGachaRenditionBallDTO = mGachaRenditionBallService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionBallDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-balls/:id} : delete the "id" mGachaRenditionBall.
     *
     * @param id the id of the mGachaRenditionBallDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-balls/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionBall(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionBall : {}", id);
        mGachaRenditionBallService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
