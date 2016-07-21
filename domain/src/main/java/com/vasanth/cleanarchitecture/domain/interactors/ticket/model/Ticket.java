package com.vasanth.cleanarchitecture.domain.interactors.ticket.model;

import java.io.Serializable;

/**
 * Ticket.
 * <p>
 * 1. Responsibility.
 * 1.a. Model used to hold Ticket details.
 *
 * @author Vasanth
 */
public class Ticket implements Serializable{

    // ticketName.
    private String ticketName;

    // ticketDescription.
    private String ticketDescription;

    /**
     * Constructor.
     */
    public Ticket() {

    }

    /**
     * Get Ticket Name.
     *
     * @return Ticket Name.
     */
    public String getTicketName() {
        return ticketName;
    }

    /**
     * Set Ticket Name.
     *
     * @param ticketName Ticket Name.
     */
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    /**
     * Get Ticket Description.
     *
     * @return Ticket Description.
     */
    public String getTicketDescription() {
        return ticketDescription;
    }

    /**
     * Set Ticket Description.
     *
     * @param ticketDescription Ticket Description.
     */
    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    /**
     * To String.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketName='" + ticketName + '\'' +
                ", ticketDescription='" + ticketDescription + '\'' +
                '}';
    }
}
