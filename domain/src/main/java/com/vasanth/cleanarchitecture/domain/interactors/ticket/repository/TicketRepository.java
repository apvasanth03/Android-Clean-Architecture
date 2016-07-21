package com.vasanth.cleanarchitecture.domain.interactors.ticket.repository;

import com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket;

import java.util.List;

/**
 * Ticket Repository.
 * <p>
 * 1. Responsibility.
 * 1.a. Interface represents Repository for getting {@link com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket}
 *
 * @author Vasanth
 */
public interface TicketRepository {

    /**
     * Ticket Repository Callback.
     */
    interface TicketRepositoryCallback {

        /**
         * Gets called if get tickets is successful.
         *
         * @param tickets Tickets.
         */
        void onGetTicketsSuccessful(final List<Ticket> tickets);

        /**
         * Gets called if get tickets failed.
         *
         * @param errorMessage Error Message.
         */
        void onGetTicketsFailed(final String errorMessage);

    }

    /**
     * Used to get Tickets.
     *
     * @param ticketRepositoryCallback Ticket Repository Callback.
     */
    void getTickets(final TicketRepositoryCallback ticketRepositoryCallback);

}
