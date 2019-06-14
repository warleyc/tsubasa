package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonBoostScheduleService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonBoostScheduleDTO;
import io.shm.tsubasa.service.dto.MMarathonBoostScheduleCriteria;
import io.shm.tsubasa.service.MMarathonBoostScheduleQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonBoostSchedule}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonBoostScheduleResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonBoostScheduleResource.class);

    private static final String ENTITY_NAME = "mMarathonBoostSchedule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonBoostScheduleService mMarathonBoostScheduleService;

    private final MMarathonBoostScheduleQueryService mMarathonBoostScheduleQueryService;

    public MMarathonBoostScheduleResource(MMarathonBoostScheduleService mMarathonBoostScheduleService, MMarathonBoostScheduleQueryService mMarathonBoostScheduleQueryService) {
        this.mMarathonBoostScheduleService = mMarathonBoostScheduleService;
        this.mMarathonBoostScheduleQueryService = mMarathonBoostScheduleQueryService;
    }

    /**
     * {@code POST  /m-marathon-boost-schedules} : Create a new mMarathonBoostSchedule.
     *
     * @param mMarathonBoostScheduleDTO the mMarathonBoostScheduleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonBoostScheduleDTO, or with status {@code 400 (Bad Request)} if the mMarathonBoostSchedule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-boost-schedules")
    public ResponseEntity<MMarathonBoostScheduleDTO> createMMarathonBoostSchedule(@Valid @RequestBody MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonBoostSchedule : {}", mMarathonBoostScheduleDTO);
        if (mMarathonBoostScheduleDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonBoostSchedule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonBoostScheduleDTO result = mMarathonBoostScheduleService.save(mMarathonBoostScheduleDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-boost-schedules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-boost-schedules} : Updates an existing mMarathonBoostSchedule.
     *
     * @param mMarathonBoostScheduleDTO the mMarathonBoostScheduleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonBoostScheduleDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonBoostScheduleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonBoostScheduleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-boost-schedules")
    public ResponseEntity<MMarathonBoostScheduleDTO> updateMMarathonBoostSchedule(@Valid @RequestBody MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonBoostSchedule : {}", mMarathonBoostScheduleDTO);
        if (mMarathonBoostScheduleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonBoostScheduleDTO result = mMarathonBoostScheduleService.save(mMarathonBoostScheduleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonBoostScheduleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-boost-schedules} : get all the mMarathonBoostSchedules.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonBoostSchedules in body.
     */
    @GetMapping("/m-marathon-boost-schedules")
    public ResponseEntity<List<MMarathonBoostScheduleDTO>> getAllMMarathonBoostSchedules(MMarathonBoostScheduleCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonBoostSchedules by criteria: {}", criteria);
        Page<MMarathonBoostScheduleDTO> page = mMarathonBoostScheduleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-boost-schedules/count} : count all the mMarathonBoostSchedules.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-boost-schedules/count")
    public ResponseEntity<Long> countMMarathonBoostSchedules(MMarathonBoostScheduleCriteria criteria) {
        log.debug("REST request to count MMarathonBoostSchedules by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonBoostScheduleQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-boost-schedules/:id} : get the "id" mMarathonBoostSchedule.
     *
     * @param id the id of the mMarathonBoostScheduleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonBoostScheduleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-boost-schedules/{id}")
    public ResponseEntity<MMarathonBoostScheduleDTO> getMMarathonBoostSchedule(@PathVariable Long id) {
        log.debug("REST request to get MMarathonBoostSchedule : {}", id);
        Optional<MMarathonBoostScheduleDTO> mMarathonBoostScheduleDTO = mMarathonBoostScheduleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonBoostScheduleDTO);
    }

    /**
     * {@code DELETE  /m-marathon-boost-schedules/:id} : delete the "id" mMarathonBoostSchedule.
     *
     * @param id the id of the mMarathonBoostScheduleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-boost-schedules/{id}")
    public ResponseEntity<Void> deleteMMarathonBoostSchedule(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonBoostSchedule : {}", id);
        mMarathonBoostScheduleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
