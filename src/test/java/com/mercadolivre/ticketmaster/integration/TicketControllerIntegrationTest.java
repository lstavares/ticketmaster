package com.mercadolivre.ticketmaster.integration;

import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import com.mercadolivre.ticketmaster.domain.entity.Ticket;
import com.mercadolivre.ticketmaster.domain.mapper.TicketMapper;
import com.mercadolivre.ticketmaster.infrastructure.repository.CategoryRepository;
import com.mercadolivre.ticketmaster.infrastructure.repository.TicketRepository;
import com.mercadolivre.ticketmaster.integration.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.ANY;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = ANY)
@Transactional
@ActiveProfiles("test")
@Sql(scripts = "/inserts.sql", executionPhase = BEFORE_TEST_CLASS)
public class TicketControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TicketMapper ticketMapper;

    private String loadJson(String fileName) throws IOException {
        return TestUtil.readJsonFile("src/test/resources/json/" + fileName);
    }

    @Test
    public void testCreateTicket() throws Exception {
        String ticketJson = loadJson("ticket_valid.json");

        mockMvc.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ticketJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Ticket"))
                .andExpect(jsonPath("$.description").value("New Description"));
    }

    @Test
    public void testDeleteTicket() throws Exception {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTitle("New Ticket");
        ticketDTO.setDescription("New Description");
        Ticket createdTicket = ticketRepository.save(ticketMapper.toEntity(ticketDTO));

        mockMvc.perform(delete("/tickets/" + createdTicket.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetNonExistentTicket() throws Exception {
        mockMvc.perform(get("/tickets/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Ticket id 999 not found."));
    }

    @Test
    public void testCreateTicketValidationError() throws Exception {
        String invalidTicketJson = loadJson("ticket_invalid.json");

        mockMvc.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidTicketJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("must not be blank"));
    }
}
