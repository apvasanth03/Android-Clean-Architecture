package com.vasanth.cleanarchitecture.data.ticket.entity;

/**
 * Ticket Entity.
 * <p>
 * 1. Responsibility.
 * 1.a. Model used to hold Ticket details.
 *
 * @author Vasanth
 */
public class TicketEntity {

    // ticketName.
    private String ticketName;

    // ticketDescription.
    private String ticketDescription;

    /**
     * Constructor.
     */
    public TicketEntity() {

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
        return "TicketEntity{" +
                "ticketName='" + ticketName + '\'' +
                ", ticketDescription='" + ticketDescription + '\'' +
                '}';
    }
}
