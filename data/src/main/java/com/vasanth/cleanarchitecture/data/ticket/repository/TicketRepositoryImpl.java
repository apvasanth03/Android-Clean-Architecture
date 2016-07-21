package com.vasanth.cleanarchitecture.data.ticket.repository;

import com.vasanth.cleanarchitecture.data.ticket.entity.TicketEntity;
import com.vasanth.cleanarchitecture.data.ticket.entity.mapper.TicketEntityDataMapper;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Ticket Repository Implementation.
 * <p/>
 * 1. Responsibility.
 * 1.a. Implements TicketRepository and provides functionality to get Tickets.
 *
 * @author Vasanth
 */
public class TicketRepositoryImpl implements TicketRepository {

    /**
     * Constructor.
     */
    public TicketRepositoryImpl() {

    }

    /**
     * Used to get Tickets.
     *
     * @param ticketRepositoryCallback Ticket Repository Callback.
     */
    @Override
    public void getTickets(TicketRepositoryCallback ticketRepositoryCallback) {

        // let's simulate some network/database lag
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<TicketEntity> ticketEntities = getTicketEntities();
        TicketEntityDataMapper ticketEntityDataMapper = new TicketEntityDataMapper();
        List<Ticket> tickets = ticketEntityDataMapper.transform(ticketEntities);
        ticketRepositoryCallback.onGetTicketsSuccessful(tickets);
    }

    /**
     * Used to get Ticket Entities.
     *
     * @return Ticket Entities.
     */
    private List<TicketEntity> getTicketEntities() {
        List<TicketEntity> ticketEntities = new ArrayList<>();
        ticketEntities.add(getTicketEntity("Ticket 1", "Ticket Description 1"));
        ticketEntities.add(getTicketEntity("Ticket 2", "Ticket Description 2"));
        ticketEntities.add(getTicketEntity("Ticket 3", "Ticket Description 3"));
        ticketEntities.add(getTicketEntity("Ticket 4", "Ticket Description 4"));
        ticketEntities.add(getTicketEntity("Ticket 5", "Ticket Description 5"));
        ticketEntities.add(getTicketEntity("Ticket 6", "Ticket Description 6"));
        ticketEntities.add(getTicketEntity("Ticket 7", "Ticket Description 7"));
        ticketEntities.add(getTicketEntity("Ticket 8", "Ticket Description 8"));
        ticketEntities.add(getTicketEntity("Ticket 9", "Ticket Description 9"));
        ticketEntities.add(getTicketEntity("Ticket 10", "Ticket Description 10"));
        return ticketEntities;
    }

    /**
     * Used to get Ticket Entity.
     *
     * @param ticketName        Ticket Name.
     * @param ticketDescription Ticket Description.
     * @return Ticket Entity.
     */
    private TicketEntity getTicketEntity(final String ticketName, final String ticketDescription) {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTicketName(ticketName);
        ticketEntity.setTicketDescription(ticketDescription);
        return ticketEntity;
    }
}
