package com.vasanth.cleanarchitecture.domain.interactors.ticket.impl;

import com.vasanth.cleanarchitecture.domain.executor.Executor;
import com.vasanth.cleanarchitecture.domain.executor.PostExecutionThread;
import com.vasanth.cleanarchitecture.domain.interactors.base.BaseInteractor;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.TicketInteractor;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.repository.TicketRepository;

import java.util.List;

/**
 * Ticket Interactor Implementation.
 * <p>
 * 1. Responsibility.
 * 1.a. Implements TicketInteractor & provides functionality to get TIckets.
 *
 * @author Vasanth
 */
public class TicketInteractorImpl extends BaseInteractor implements TicketInteractor, TicketRepository.TicketRepositoryCallback {

    // ticketCallback.
    private TicketCallback ticketCallback;

    // ticketRepository.
    private TicketRepository ticketRepository;

    /**
     * Constructor.
     *
     * @param executor            Executor.
     * @param postExecutionThread Post Execution Thread.
     */
    public TicketInteractorImpl(Executor executor, PostExecutionThread postExecutionThread,
                                final TicketRepository ticketRepository) {
        super(executor, postExecutionThread);
        this.ticketRepository = ticketRepository;
    }

    /**
     * Ticket Interactor.
     */
    /**
     * Used to get Tickets.
     *
     * @param ticketCallback Ticket Callback.
     */
    @Override
    public void getTickets(final TicketCallback ticketCallback) {
        this.ticketCallback = ticketCallback;

        // Execute interactor in new thread.
        executeInteractor();
    }

    /**
     * Used to cancel get Tickets.
     */
    @Override
    public void cancelGetTickets() {
        ticketCallback = null;
    }

    /**
     * Run.
     * <p>
     * 1. This method runs in new thread.
     * 2. Hence do our logic here & send callback using postExecutionThread.
     */
    @Override
    public void run() {
        ticketRepository.getTickets(this);
    }

    /**
     * Ticket Repository Callback.
     */
    /**
     * Gets called if get tickets is successful.
     *
     * @param tickets Tickets.
     */
    @Override
    public void onGetTicketsSuccessful(List<Ticket> tickets) {
        notifyGetTicketsSuccess(tickets);
    }

    /**
     * Gets called if get tickets failed.
     *
     * @param errorMessage Error Message.
     */
    @Override
    public void onGetTicketsFailed(String errorMessage) {
        notifyGetTicketsFailed(errorMessage);
    }

    /**
     * Used to notify that getTickets is successful.
     *
     * @param tickets Tickets.
     */
    private void notifyGetTicketsSuccess(final List<Ticket> tickets) {
        if (ticketCallback != null) {
            postResult(new Runnable() {
                @Override
                public void run() {
                    ticketCallback.onGetTicketsSuccess(tickets);
                }
            });
        }
    }

    /**
     * Used to notify that getTickets is failed.
     *
     * @param errorMessage Error Message.
     */
    private void notifyGetTicketsFailed(final String errorMessage) {
        if (ticketCallback != null) {
            postResult(new Runnable() {
                @Override
                public void run() {
                    ticketCallback.onGetTicketsFailed(errorMessage);
                }
            });
        }
    }

}
