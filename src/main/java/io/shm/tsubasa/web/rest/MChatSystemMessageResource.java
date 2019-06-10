package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MChatSystemMessageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MChatSystemMessageDTO;
import io.shm.tsubasa.service.dto.MChatSystemMessageCriteria;
import io.shm.tsubasa.service.MChatSystemMessageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MChatSystemMessage}.
 */
@RestController
@RequestMapping("/api")
public class MChatSystemMessageResource {

    private final Logger log = LoggerFactory.getLogger(MChatSystemMessageResource.class);

    private static final String ENTITY_NAME = "mChatSystemMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MChatSystemMessageService mChatSystemMessageService;

    private final MChatSystemMessageQueryService mChatSystemMessageQueryService;

    public MChatSystemMessageResource(MChatSystemMessageService mChatSystemMessageService, MChatSystemMessageQueryService mChatSystemMessageQueryService) {
        this.mChatSystemMessageService = mChatSystemMessageService;
        this.mChatSystemMessageQueryService = mChatSystemMessageQueryService;
    }

    /**
     * {@code POST  /m-chat-system-messages} : Create a new mChatSystemMessage.
     *
     * @param mChatSystemMessageDTO the mChatSystemMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mChatSystemMessageDTO, or with status {@code 400 (Bad Request)} if the mChatSystemMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-chat-system-messages")
    public ResponseEntity<MChatSystemMessageDTO> createMChatSystemMessage(@Valid @RequestBody MChatSystemMessageDTO mChatSystemMessageDTO) throws URISyntaxException {
        log.debug("REST request to save MChatSystemMessage : {}", mChatSystemMessageDTO);
        if (mChatSystemMessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mChatSystemMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MChatSystemMessageDTO result = mChatSystemMessageService.save(mChatSystemMessageDTO);
        return ResponseEntity.created(new URI("/api/m-chat-system-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-chat-system-messages} : Updates an existing mChatSystemMessage.
     *
     * @param mChatSystemMessageDTO the mChatSystemMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mChatSystemMessageDTO,
     * or with status {@code 400 (Bad Request)} if the mChatSystemMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mChatSystemMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-chat-system-messages")
    public ResponseEntity<MChatSystemMessageDTO> updateMChatSystemMessage(@Valid @RequestBody MChatSystemMessageDTO mChatSystemMessageDTO) throws URISyntaxException {
        log.debug("REST request to update MChatSystemMessage : {}", mChatSystemMessageDTO);
        if (mChatSystemMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MChatSystemMessageDTO result = mChatSystemMessageService.save(mChatSystemMessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mChatSystemMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-chat-system-messages} : get all the mChatSystemMessages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mChatSystemMessages in body.
     */
    @GetMapping("/m-chat-system-messages")
    public ResponseEntity<List<MChatSystemMessageDTO>> getAllMChatSystemMessages(MChatSystemMessageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MChatSystemMessages by criteria: {}", criteria);
        Page<MChatSystemMessageDTO> page = mChatSystemMessageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-chat-system-messages/count} : count all the mChatSystemMessages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-chat-system-messages/count")
    public ResponseEntity<Long> countMChatSystemMessages(MChatSystemMessageCriteria criteria) {
        log.debug("REST request to count MChatSystemMessages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mChatSystemMessageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-chat-system-messages/:id} : get the "id" mChatSystemMessage.
     *
     * @param id the id of the mChatSystemMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mChatSystemMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-chat-system-messages/{id}")
    public ResponseEntity<MChatSystemMessageDTO> getMChatSystemMessage(@PathVariable Long id) {
        log.debug("REST request to get MChatSystemMessage : {}", id);
        Optional<MChatSystemMessageDTO> mChatSystemMessageDTO = mChatSystemMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mChatSystemMessageDTO);
    }

    /**
     * {@code DELETE  /m-chat-system-messages/:id} : delete the "id" mChatSystemMessage.
     *
     * @param id the id of the mChatSystemMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-chat-system-messages/{id}")
    public ResponseEntity<Void> deleteMChatSystemMessage(@PathVariable Long id) {
        log.debug("REST request to delete MChatSystemMessage : {}", id);
        mChatSystemMessageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
