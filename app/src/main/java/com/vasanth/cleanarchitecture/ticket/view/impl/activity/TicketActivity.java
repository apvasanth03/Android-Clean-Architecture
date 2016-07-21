package com.vasanth.cleanarchitecture.ticket.view.impl.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.vasanth.cleanarchitecture.R;
import com.vasanth.cleanarchitecture.base.mvp.PresenterFactory;
import com.vasanth.cleanarchitecture.base.mvp.PresenterLoader;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket;
import com.vasanth.cleanarchitecture.ticket.presenter.TicketPresenter;
import com.vasanth.cleanarchitecture.ticket.presenter.impl.TicketPresenterImpl;
import com.vasanth.cleanarchitecture.ticket.view.TicketView;
import com.vasanth.cleanarchitecture.ticket.view.impl.adapter.TicketAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Ticket Activity.
 * <p>
 * 1. Responsibility.
 * 1.a. Provides UI for Tickets Screen.
 *
 * @author Vasanth
 */
public class TicketActivity extends AppCompatActivity implements TicketView, LoaderManager.LoaderCallbacks<TicketPresenter> {

    // LOADER_ID_TICKET_PRESENTER.
    private static final int LOADER_ID_TICKET_PRESENTER = 101;

    // STATE_KEY_TICKETS.
    private static final String STATE_KEY_TICKETS = "state_key_tickets";

    // ticketsListView.
    private ListView ticketsListView;

    // ticketsEmptyTextView.
    private TextView ticketsEmptyTextView;

    // ticketProgressDialog.
    private ProgressDialog ticketProgressDialog;

    // ticketsAdapter.
    private TicketAdapter ticketsAdapter;

    // ticketPresenter.
    private TicketPresenter ticketPresenter;

    /**
     * On Create.
     *
     * @param savedInstanceState Saved Instance State.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        // Initialize Views.
        initializeViews();

        // Create presenter using loader.
        getSupportLoaderManager().initLoader(LOADER_ID_TICKET_PRESENTER, null, this);
    }

    /**
     * On Start.
     */
    @Override
    protected void onStart() {
        super.onStart();
        ticketPresenter.onAttachView(this);
        ticketPresenter.getTickets();
    }

    /**
     * On Stop.
     */
    @Override
    protected void onStop() {
        super.onStop();
        ticketPresenter.onDetachView();
    }


    /**
     * On Save Instance State.
     *
     * @param outState Out State.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveTicketViewState(outState);
    }

    /**
     * Used to save ticket view state.
     *
     * @param outState Out State.
     */
    private void saveTicketViewState(final Bundle outState) {

    }

    /**
     * Used to restore ticket view state.
     *
     * @param savedInstanceState Saved Instance State.
     */
    private void restoreTicketViewState(final Bundle savedInstanceState) {
        if (savedInstanceState != null) {

        }
    }

    /**
     * Used to initialize views.
     */
    private void initializeViews() {
        ticketsListView = (ListView) findViewById(R.id.activityTicket_view_ticketList);
        ticketsEmptyTextView = (TextView) findViewById(android.R.id.empty);
        ticketsListView.setEmptyView(ticketsEmptyTextView);
        ticketsAdapter = new TicketAdapter(this, new ArrayList<Ticket>());
    }

    /**
     * LoaderManager.LoaderCallbacks<LoginPresenter>
     */
    /**
     * On Create Loader.
     *
     * @param id   Id.
     * @param args Args.
     * @return Loader.
     */
    @Override
    public Loader<TicketPresenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, new TicketPresenterFactory(), args);
    }

    /**
     * On Load Finished.
     *
     * @param loader Loader.
     * @param data   Data.
     */
    @Override
    public void onLoadFinished(Loader<TicketPresenter> loader, TicketPresenter data) {
        this.ticketPresenter = data;
    }

    /**
     * On Loader Reset.
     *
     * @param loader Loader.
     */
    @Override
    public void onLoaderReset(Loader<TicketPresenter> loader) {
        this.ticketPresenter.onDestroyView();
        this.ticketPresenter = null;
    }

    /**
     * Ticket Presenter Factory.
     */
    private static class TicketPresenterFactory implements PresenterFactory<TicketPresenter> {
        /**
         * Create Presenter.
         *
         * @return Ticket Presenter.
         */
        @Override
        public TicketPresenter createPresenter() {
            return new TicketPresenterImpl();
        }
    }

    /**
     * Ticket View.
     */
    /**
     * Used to show progress.
     */
    @Override
    public void showProgress() {
        if (ticketProgressDialog == null || !ticketProgressDialog.isShowing()) {
            ticketProgressDialog = ProgressDialog.show(this, getString(R.string.ticket), getString(R.string.please_wait), true, false);
        }
    }

    /**
     * Used to hide progress.
     */
    @Override
    public void hideProgress() {
        if (ticketProgressDialog != null && ticketProgressDialog.isShowing()) {
            ticketProgressDialog.dismiss();
        }
    }

    /**
     * Used to populate Tickets.
     *
     * @param tickets Tickets.
     */
    @Override
    public void populateTickets(List<Ticket> tickets) {
        if (ticketsListView.getAdapter() == null) {
            ticketsListView.setAdapter(ticketsAdapter);
        }
        ticketsAdapter.setTickets(tickets);
        ticketsEmptyTextView.setText(getString(R.string.no_tickets_found));
        ticketsAdapter.notifyDataSetChanged();
    }

    /**
     * Used to populate error.
     *
     * @param errorMessage Error Message.
     */
    @Override
    public void populateError(String errorMessage) {
        ticketsEmptyTextView.setText(errorMessage);
        ticketsEmptyTextView.setVisibility(View.VISIBLE);
    }
}
