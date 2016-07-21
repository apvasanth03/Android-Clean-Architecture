package com.vasanth.cleanarchitecture.ticket.view;

import com.vasanth.cleanarchitecture.base.mvp.View;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket;

import java.util.List;

/**
 * Ticket View.
 * <p/>
 * 1. Responsibility.
 * 1.a. TicketView - Defines method which should be implemented by class which provides Ui for Ticket screen.
 *
 * @author Vasanth
 */
public interface TicketView extends View {

    /**
     * PROGRESS VIEW.
     */
    /**
     * Used to show progress.
     */
    void showProgress();

    /**
     * Used to hide progress.
     */
    void hideProgress();

    /**
     * LIST VIEW.
     */

    /**
     * Used to populate Tickets.
     *
     * @param tickets Tickets.
     */
    void populateTickets(List<Ticket> tickets);

    /**
     * Used to populate error.
     *
     * @param errorMessage Error Message.
     */
    void populateError(String errorMessage);
}
