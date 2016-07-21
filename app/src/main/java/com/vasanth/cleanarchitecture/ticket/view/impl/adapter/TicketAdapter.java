package com.vasanth.cleanarchitecture.ticket.view.impl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vasanth.cleanarchitecture.R;
import com.vasanth.cleanarchitecture.domain.interactors.ticket.model.Ticket;

import java.util.List;

/**
 * Ticket Adapter.
 * <p>
 * 1. Responsibility.
 * 1.a. Adapter used to populate Ticket ListView.
 *
 * @author Vasanth
 */
public class TicketAdapter extends ArrayAdapter<Ticket> {

    // context.
    private Context context;

    // tickets.
    private List<Ticket> tickets;

    /**
     * Constructor.
     *
     * @param context Context.
     * @param tickets Tickets.
     */
    public TicketAdapter(Context context, List<Ticket> tickets) {
        super(context, R.layout.row_ticket, tickets);
        this.context = context;
        this.tickets = tickets;
    }

    /**
     * Get Count.
     *
     * @return Count.
     */
    @Override
    public int getCount() {
        return (tickets != null) ? tickets.size() : 0;
    }

    /**
     * Get Ite  Id.
     *
     * @param position Position.
     * @return Item Id.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get Item.
     *
     * @param position Position.
     * @return Item at the given position.
     */
    @Override
    public Ticket getItem(int position) {
        return (tickets != null) ? tickets.get(position) : null;
    }

    /**
     * Get View.
     *
     * @param position    Position.
     * @param convertView COnvert View.
     * @param parent      Parent.
     * @return View.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_ticket, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ticketNameTextView = (TextView) convertView.findViewById(R.id.rowTicket_view_ticketName);
            viewHolder.ticketDescriptionTextView = (TextView) convertView.findViewById(R.id.rowTicket_view_ticketDescription);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate.
        if (tickets != null) {
            Ticket ticket = tickets.get(position);
            viewHolder.ticketNameTextView.setText(ticket.getTicketName());
            viewHolder.ticketDescriptionTextView.setText(ticket.getTicketDescription());
        }
        return convertView;
    }

    /**
     * View Holder.
     */
    private class ViewHolder {
        // ticketNameTextView.
        private TextView ticketNameTextView;

        // ticketDescriptionTextView.
        private TextView ticketDescriptionTextView;
    }

    /**
     * Set Tickets.
     *
     * @param tickets Tickets.
     */
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    /**
     * Get Tickets.
     *
     * @return Tickets.
     */
    public List<Ticket> getTickets() {
        return tickets;
    }
}
