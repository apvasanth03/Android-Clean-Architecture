package com.vasanth.cleanarchitecture.domain.interactors.ticket;

import com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket;

import java.util.List;

/**
 * Ticket Interactor.
 * <p>
 * 1. Responsibility.
 * 1.a. Interactor responsible to get tickets & send callback.
 *
 * @author Vasanth
 */
public interface TicketInteractor {

    /**
     * Ticket Callback.
     */
    interface TicketCallback {

        /**
         * Gets called if get tickets is successful.
         *
         * @param tickets Tickets.
         */
        void onGetTicketsSuccess(List<Ticket> tickets);

        /**
         * Gets called if get tickets failed.
         *
         * @param errorMessage Error Message.
         */
        void onGetTicketsFailed(String errorMessage);
    }

    /**
     * Used to get Tickets.
     *
     * @param ticketCallback   Ticket Callback.
     */
    void getTickets(TicketCallback ticketCallback);

    /**
     * Used to cancel get Tickets.
     */
    void cancelGetTickets();

}
