package com.vasanth.cleanarchitecture.ticket.presenter.impl;

import com.vasanth.cleanarchitecture.UIThreadImpl;
import com.vasanth.cleanarchitecture.data.ticket.repository.TicketRepositoryImpl;
import com.vasanth.cleanarchitecture.domain.executor.impl.ThreadExecutor;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.TicketInteractor;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.impl.TicketInteractorImpl;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.repository.TicketRepository;
import com.vasanth.cleanarchitecture.ticket.presenter.TicketPresenter;
import com.vasanth.cleanarchitecture.ticket.view.TicketView;

import java.util.List;

/**
 * Ticket Presenter Implementation.
 * <p>
 * 1. Responsibility.
 * 1.a. Implements TicketPresenter & provides functionality for it.
 *
 * @author Vasanth
 */
public class TicketPresenterImpl implements TicketPresenter, TicketInteractor.TicketCallback {

    // TICKET_STATE_NONE.
    private static final int TICKET_STATE_NONE = 101;

    // TICKET_STATE_LOADING.
    private static final int TICKET_STATE_LOADING = 102;

    // TICKET_STATE_LOAD_SUCCESS.
    private static final int TICKET_STATE_LOAD_SUCCESS = 103;

    // TICKET_STATE_LOAD_FAILED.
    private static final int TICKET_STATE_LOAD_FAILED = 104;

    // ticketInteractor.
    private TicketInteractor ticketInteractor;

    // ticketView.
    private TicketView ticketView;

    // ticketState.
    private int ticketState;

    // tickets.
    private List<Ticket> tickets;

    // errorMessage.
    private String errorMessage;

    /**
     * Constructor.
     */
    public TicketPresenterImpl() {
        TicketRepository ticketRepository = new TicketRepositoryImpl();
        ticketInteractor = new TicketInteractorImpl(ThreadExecutor.getInstance(), UIThreadImpl.getInstance(),
                ticketRepository);
        ticketState = TICKET_STATE_NONE;

    }

    /**
     * On Attach View.
     * <p>
     * 1. Gets called on attach of view.
     *
     * @param view View.
     */
    @Override
    public void onAttachView(final TicketView view) {
        this.ticketView = view;

        // Initialize Ticket View.
        initializeTicketView();
    }

    /**
     * On Detach View.
     * <p>
     * 1. Gets called on detach of view.
     */
    @Override
    public void onDetachView() {
        ticketView = null;
    }

    /**
     * On Destroy View.
     * <p>
     * 1. Gets called on destroy of view.
     */
    @Override
    public void onDestroyView() {
        // If state is still loading the cancel request.
        if (ticketState == TICKET_STATE_LOADING) {
            ticketInteractor.cancelGetTickets();
            ticketState = TICKET_STATE_NONE;
        }
    }

    /**
     * Used to get tickets.
     */
    @Override
    public void getTickets() {
        // Load tickets only if current state is not loading & ticket cache is null.
        if (ticketState != TICKET_STATE_LOADING && tickets == null) {
            ticketState = TICKET_STATE_LOADING;
            ticketInteractor.getTickets(this);
            notifyStateTicketLoading();
        }
        // If ticket cache is not null then send it to view.
        else if (tickets != null) {
            notifyStateTicketLoadSuccess(tickets);
        }
    }

    /**
     * Used to initialize ticket view.
     * <p>
     * 1. Initialize ticketView depending on presenter state.
     */
    private void initializeTicketView() {
        switch (ticketState) {
            // Ticket Loading.
            case TICKET_STATE_LOADING:
                notifyStateTicketLoading();
                break;

            // Ticket Load Success.
            case TICKET_STATE_LOAD_SUCCESS:
                notifyStateTicketLoadSuccess(tickets);
                break;

            // Ticket Load Failed.
            case TICKET_STATE_LOAD_FAILED:
                notifyStateTicketLoadFailed(errorMessage);
                break;
        }
    }

    /**
     * TicketInteractor.TicketCallback.
     */
    /**
     * Gets called if get tickets is successful.
     *
     * @param tickets Tickets.
     */
    @Override
    public void onGetTicketsSuccess(List<Ticket> tickets) {
        ticketState = TICKET_STATE_LOAD_SUCCESS;
        this.tickets = tickets;
        notifyStateTicketLoadSuccess(tickets);
    }

    /**
     * Gets called if get tickets failed.
     *
     * @param errorMessage Error Message.
     */
    @Override
    public void onGetTicketsFailed(String errorMessage) {
        ticketState = TICKET_STATE_LOAD_FAILED;
        this.errorMessage = errorMessage;
        notifyStateTicketLoadFailed(errorMessage);
    }

    /**
     * Methods used to send callback to ticketView.
     */
    /**
     * Used to notify state TicketLoading.
     */
    private void notifyStateTicketLoading() {
        if (ticketView != null) {
            ticketView.showProgress();
        }
    }

    /**
     * Used to notify state TicketLoadSuccess
     *
     * @param tickets Tickets
     */
    private void notifyStateTicketLoadSuccess(final List<Ticket> tickets) {
        if (ticketView != null) {
            ticketView.populateTickets(tickets);
            ticketView.hideProgress();
            ticketState = TICKET_STATE_NONE;
        }
    }

    /**
     * Used to notify state TicketLoadFailed
     *
     * @param errorMessage Error Message.
     */
    private void notifyStateTicketLoadFailed(final String errorMessage) {
        if (ticketView != null) {
            ticketView.populateError(errorMessage);
            ticketView.hideProgress();
            ticketState = TICKET_STATE_NONE;
        }
    }
}
