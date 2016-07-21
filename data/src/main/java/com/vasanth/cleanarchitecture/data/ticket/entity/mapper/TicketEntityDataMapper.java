package com.vasanth.cleanarchitecture.data.ticket.entity.mapper;

import com.vasanth.cleanarchitecture.data.ticket.entity.TicketEntity;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Ticket Entity Data Mapper.
 * <p/>
 * 1. Responsibility.
 * 1.a. Mapper class used to transform {@link com.vasanth.cleanarchitecture.data.ticket.entity.TicketEntity}
 * (in the data layer) to {@link com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket} in the
 * domain layer.
 *
 * @author Vasanth
 */
public class TicketEntityDataMapper {

    /**
     * Constructor.
     */
    public TicketEntityDataMapper() {

    }

    /**
     * Transform TicketEntity in Data layer to Ticket in Domain layer.
     *
     * @param ticketEntity TicketEntity to be transformed.
     * @return Ticket
     */
    public Ticket transform(final TicketEntity ticketEntity) {
        Ticket ticket = null;
        if (ticketEntity != null) {
            ticket = new Ticket();
            ticket.setTicketName(ticketEntity.getTicketName());
            ticket.setTicketDescription(ticketEntity.getTicketDescription());
        }
        return ticket;
    }

    /**
     * Transform TicketEntities in Data layer to Tickets in Domain layer.
     *
     * @param ticketEntities TicketEntities to be transformed.
     * @return Tickets
     */
    public List<Ticket> transform(List<TicketEntity> ticketEntities) {
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket;
        for (TicketEntity ticketEntity : ticketEntities) {
            ticket = transform(ticketEntity);
            if (ticket != null) {
                tickets.add(ticket);
            }
        }
        return tickets;
    }

}
