import React, { useEffect, useState } from 'react';
import api from '../services/api';
import { useNavigate, Link } from 'react-router-dom';
import '../List.css';

const TicketList = () => {
  const [tickets, setTickets] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    api.get('/tickets')
      .then(response => setTickets(response.data))
      .catch(error => console.error('Error fetching tickets:', error));
  }, []);

  const handleDelete = (id) => {
    if (window.confirm('Are you sure you want to delete this ticket?')) {
      api.delete(`/tickets/${id}`)
        .then(() => {
          alert('Ticket deleted successfully.');
          setTickets(tickets.filter(ticket => ticket.id !== id))
        })
        .catch(error => {
          console.error('There was an error deleting the ticket!', error);
        });
    }
  };

  const handleEdit = (id) => {
    navigate(`/tickets/edit/${id}`);
  };

  const getSeverityClass = (level) => {
    switch (level) {
      case 1:
        return 'severity severity-issue-high';
      case 2:
        return 'severity severity-high';
      case 3:
        return 'severity severity-medium';
      case 4:
        return 'severity severity-low';
      default:
        return 'severity';
    }
  };

  return (
    <div className="list-container">
      <div className="list-header">
        <h2 className="list-title">Tickets</h2>
        <Link to="/tickets/new" className="add-button">Create New Ticket</Link>
      </div>
      <ul className="list-group">
        {tickets.map(ticket => (
          <li key={ticket.id} className="list-group-item">
            <div>
              <h3 className="item-title">{ticket.title}</h3>
              <p className="item-description">{ticket.description}</p>
              <span>Created By: {ticket.user.name}</span>
            </div>
            <div className="item-meta">
              <span className={getSeverityClass(ticket.severityLevel)}>
                {ticket.severityLevel === 1 ? 'Issue High' : ticket.severityLevel === 2 ? 'High' : ticket.severityLevel === 3 ? 'Medium' : 'Low'}
              </span>
            </div>
            <div className="action-buttons">
              <button onClick={() => handleEdit(ticket.id)} className="edit-btn">Edit</button>
              <button onClick={() => handleDelete(ticket.id)} className="delete-btn">Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TicketList;
