package com.vasanth.cleanarchitecture.ticket.presenter;

import com.vasanth.cleanarchitecture.base.mvp.Presenter;
import com.vasanth.cleanarchitecture.ticket.view.TicketView;

/**
 * Ticket Presenter.
 * <p/>
 * 1. Responsibility.
 * 1.a. Ticket Presenter - Defines method which acts as a bridge between TicketUI & TicketData.
 *
 * @author Vasanth
 */
public interface TicketPresenter extends Presenter<TicketView> {

    /**
     * Used to get tickets.
     */
    void getTickets();

}
