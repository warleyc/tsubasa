package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestTicketService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestTicketDTO;
import io.shm.tsubasa.service.dto.MQuestTicketCriteria;
import io.shm.tsubasa.service.MQuestTicketQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestTicket}.
 */
@RestController
@RequestMapping("/api")
public class MQuestTicketResource {

    private final Logger log = LoggerFactory.getLogger(MQuestTicketResource.class);

    private static final String ENTITY_NAME = "mQuestTicket";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestTicketService mQuestTicketService;

    private final MQuestTicketQueryService mQuestTicketQueryService;

    public MQuestTicketResource(MQuestTicketService mQuestTicketService, MQuestTicketQueryService mQuestTicketQueryService) {
        this.mQuestTicketService = mQuestTicketService;
        this.mQuestTicketQueryService = mQuestTicketQueryService;
    }

    /**
     * {@code POST  /m-quest-tickets} : Create a new mQuestTicket.
     *
     * @param mQuestTicketDTO the mQuestTicketDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestTicketDTO, or with status {@code 400 (Bad Request)} if the mQuestTicket has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-tickets")
    public ResponseEntity<MQuestTicketDTO> createMQuestTicket(@Valid @RequestBody MQuestTicketDTO mQuestTicketDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestTicket : {}", mQuestTicketDTO);
        if (mQuestTicketDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestTicket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestTicketDTO result = mQuestTicketService.save(mQuestTicketDTO);
        return ResponseEntity.created(new URI("/api/m-quest-tickets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-tickets} : Updates an existing mQuestTicket.
     *
     * @param mQuestTicketDTO the mQuestTicketDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestTicketDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestTicketDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestTicketDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-tickets")
    public ResponseEntity<MQuestTicketDTO> updateMQuestTicket(@Valid @RequestBody MQuestTicketDTO mQuestTicketDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestTicket : {}", mQuestTicketDTO);
        if (mQuestTicketDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestTicketDTO result = mQuestTicketService.save(mQuestTicketDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestTicketDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-tickets} : get all the mQuestTickets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestTickets in body.
     */
    @GetMapping("/m-quest-tickets")
    public ResponseEntity<List<MQuestTicketDTO>> getAllMQuestTickets(MQuestTicketCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestTickets by criteria: {}", criteria);
        Page<MQuestTicketDTO> page = mQuestTicketQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-tickets/count} : count all the mQuestTickets.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-tickets/count")
    public ResponseEntity<Long> countMQuestTickets(MQuestTicketCriteria criteria) {
        log.debug("REST request to count MQuestTickets by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestTicketQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-tickets/:id} : get the "id" mQuestTicket.
     *
     * @param id the id of the mQuestTicketDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestTicketDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-tickets/{id}")
    public ResponseEntity<MQuestTicketDTO> getMQuestTicket(@PathVariable Long id) {
        log.debug("REST request to get MQuestTicket : {}", id);
        Optional<MQuestTicketDTO> mQuestTicketDTO = mQuestTicketService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestTicketDTO);
    }

    /**
     * {@code DELETE  /m-quest-tickets/:id} : delete the "id" mQuestTicket.
     *
     * @param id the id of the mQuestTicketDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-tickets/{id}")
    public ResponseEntity<Void> deleteMQuestTicket(@PathVariable Long id) {
        log.debug("REST request to delete MQuestTicket : {}", id);
        mQuestTicketService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
