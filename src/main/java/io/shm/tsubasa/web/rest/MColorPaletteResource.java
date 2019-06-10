package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MColorPaletteService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MColorPaletteDTO;
import io.shm.tsubasa.service.dto.MColorPaletteCriteria;
import io.shm.tsubasa.service.MColorPaletteQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MColorPalette}.
 */
@RestController
@RequestMapping("/api")
public class MColorPaletteResource {

    private final Logger log = LoggerFactory.getLogger(MColorPaletteResource.class);

    private static final String ENTITY_NAME = "mColorPalette";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MColorPaletteService mColorPaletteService;

    private final MColorPaletteQueryService mColorPaletteQueryService;

    public MColorPaletteResource(MColorPaletteService mColorPaletteService, MColorPaletteQueryService mColorPaletteQueryService) {
        this.mColorPaletteService = mColorPaletteService;
        this.mColorPaletteQueryService = mColorPaletteQueryService;
    }

    /**
     * {@code POST  /m-color-palettes} : Create a new mColorPalette.
     *
     * @param mColorPaletteDTO the mColorPaletteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mColorPaletteDTO, or with status {@code 400 (Bad Request)} if the mColorPalette has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-color-palettes")
    public ResponseEntity<MColorPaletteDTO> createMColorPalette(@Valid @RequestBody MColorPaletteDTO mColorPaletteDTO) throws URISyntaxException {
        log.debug("REST request to save MColorPalette : {}", mColorPaletteDTO);
        if (mColorPaletteDTO.getId() != null) {
            throw new BadRequestAlertException("A new mColorPalette cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MColorPaletteDTO result = mColorPaletteService.save(mColorPaletteDTO);
        return ResponseEntity.created(new URI("/api/m-color-palettes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-color-palettes} : Updates an existing mColorPalette.
     *
     * @param mColorPaletteDTO the mColorPaletteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mColorPaletteDTO,
     * or with status {@code 400 (Bad Request)} if the mColorPaletteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mColorPaletteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-color-palettes")
    public ResponseEntity<MColorPaletteDTO> updateMColorPalette(@Valid @RequestBody MColorPaletteDTO mColorPaletteDTO) throws URISyntaxException {
        log.debug("REST request to update MColorPalette : {}", mColorPaletteDTO);
        if (mColorPaletteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MColorPaletteDTO result = mColorPaletteService.save(mColorPaletteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mColorPaletteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-color-palettes} : get all the mColorPalettes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mColorPalettes in body.
     */
    @GetMapping("/m-color-palettes")
    public ResponseEntity<List<MColorPaletteDTO>> getAllMColorPalettes(MColorPaletteCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MColorPalettes by criteria: {}", criteria);
        Page<MColorPaletteDTO> page = mColorPaletteQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-color-palettes/count} : count all the mColorPalettes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-color-palettes/count")
    public ResponseEntity<Long> countMColorPalettes(MColorPaletteCriteria criteria) {
        log.debug("REST request to count MColorPalettes by criteria: {}", criteria);
        return ResponseEntity.ok().body(mColorPaletteQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-color-palettes/:id} : get the "id" mColorPalette.
     *
     * @param id the id of the mColorPaletteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mColorPaletteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-color-palettes/{id}")
    public ResponseEntity<MColorPaletteDTO> getMColorPalette(@PathVariable Long id) {
        log.debug("REST request to get MColorPalette : {}", id);
        Optional<MColorPaletteDTO> mColorPaletteDTO = mColorPaletteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mColorPaletteDTO);
    }

    /**
     * {@code DELETE  /m-color-palettes/:id} : delete the "id" mColorPalette.
     *
     * @param id the id of the mColorPaletteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-color-palettes/{id}")
    public ResponseEntity<Void> deleteMColorPalette(@PathVariable Long id) {
        log.debug("REST request to delete MColorPalette : {}", id);
        mColorPaletteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
