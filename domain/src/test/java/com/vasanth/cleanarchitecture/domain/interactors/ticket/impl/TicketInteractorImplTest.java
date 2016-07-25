package com.vasanth.cleanarchitecture.domain.interactors.ticket.impl;

import com.vasanth.cleanarchitecture.domain.executor.TestExecutor;
import com.vasanth.cleanarchitecture.domain.executor.TestPostExecutionThread;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.TicketInteractor;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.repository.TicketRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Ticket Interactor Implementation Test.
 * <p/>
 * 1. Responsibility.
 * 1.a. Class used to unit test TicketInteractorImpl
 *
 * @author Vasanth
 * @see TicketInteractorImpl
 */
public class TicketInteractorImplTest {

    // ticketInteractorImpl.
    private TicketInteractorImpl ticketInteractorImpl;


    // ticketRepository.
    private TicketRepository ticketRepository;


    /**
     * Constructor.
     */
    public TicketInteractorImplTest() {

    }

    /**
     * Set Up.
     * <p/>
     * 1. Create instance of TicketInteractorImpl.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        ticketRepository = mock(TicketRepository.class);
        ticketInteractorImpl = new TicketInteractorImpl(TestExecutor.getInstance(), TestPostExecutionThread.getInstance(), ticketRepository);
    }

    /**
     * Used to test GetTickets.
     *
     * @throws Exception
     */
    @Test
    public void test01GetTickets() throws Exception {
        ticketInteractorImpl.getTickets(null);

        // Validations.
        // Verify that TicketRepository's -> GetTickets method gets executed.
        verify(ticketRepository).getTickets(any(TicketRepository.TicketRepositoryCallback.class));
    }

    /**
     * Used to test - OnGetTicketsSuccessful.
     * <p/>
     * 1. On executing OnGetTicketsSuccessful - Verify that TicketCallback -> onGetTicketsSuccessful method gets called.
     *
     * @throws Exception
     */
    @Test
    public void test02OnGetTicketsSuccessful() throws Exception {
        TicketInteractor.TicketCallback ticketCallback = mock(TicketInteractor.TicketCallback.class);
        List<Ticket> tickets = new ArrayList<>();
        ticketInteractorImpl.getTickets(ticketCallback);
        ticketInteractorImpl.onGetTicketsSuccessful(tickets);

        // Validations.
        // Verify that TicketCallback -> onGetTicketsSuccessful method gets called.
        verify(ticketCallback).onGetTicketsSuccess(tickets);
    }

    /**
     * Used to test - OnGetTicketsFailed.
     * <p/>
     * 1. On executing OnGetTicketsFailed - Verify that TicketCallback -> onGetTicketsFailed method gets called.
     *
     * @throws Exception
     */
    @Test
    public void test03OnGetTicketsFailed() throws Exception {
        TicketInteractor.TicketCallback ticketCallback = mock(TicketInteractor.TicketCallback.class);
        String errorMessage = "Error";
        ticketInteractorImpl.getTickets(ticketCallback);
        ticketInteractorImpl.onGetTicketsFailed(errorMessage);

        // Validations.
        // Verify that TicketCallback -> onGetTicketsFailed method gets called.
        verify(ticketCallback).onGetTicketsFailed(errorMessage);
    }
}
