import React, { useEffect, useState } from 'react';
import api from '../services/api';

const TicketList = () => {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    api.get('/tickets')
      .then(response => setTickets(response.data))
      .catch(error => console.error('Error fetching tickets:', error));
  }, []);

  const handleDelete = (id) => {
    api.delete(`/tickets/${id}`)
      .then(() => setTickets(tickets.filter(ticket => ticket.id !== id)))
      .catch(error => console.error('Error deleting ticket:', error));
  };

  return (
    <div>
      <h2>Ticket List</h2>
      <ul>
        {tickets.map(ticket => (
          <li key={ticket.id}>
            {ticket.title} - {ticket.category.name}
            <button onClick={() => handleDelete(ticket.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TicketList;
