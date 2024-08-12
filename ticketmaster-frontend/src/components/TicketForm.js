import React, { useState, useEffect } from 'react';
import api from '../services/api';

const TicketForm = ({ ticketId }) => {
  const [ticket, setTicket] = useState({ title: '', description: '', categoryId: '' });
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    api.get('/categories')
      .then(response => setCategories(response.data))
      .catch(error => console.error('Error fetching categories:', error));

    if (ticketId) {
      api.get(`/tickets/${ticketId}`)
        .then(response => setTicket(response.data))
        .catch(error => console.error('Error fetching ticket:', error));
    }
  }, [ticketId]);

  const handleChange = (e) => {
    setTicket({ ...ticket, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const apiMethod = ticketId ? api.put : api.post;
    const endpoint = ticketId ? `/tickets/${ticketId}` : '/tickets';

    apiMethod(endpoint, ticket)
      .then(() => alert('Ticket saved successfully!'))
      .catch(error => console.error('Error saving ticket:', error));
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Title</label>
        <input name="title" value={ticket.title} onChange={handleChange} required />
      </div>
      <div>
        <label>Description</label>
        <textarea name="description" value={ticket.description} onChange={handleChange} required />
      </div>
      <div>
        <label>Category</label>
        <select name="categoryId" value={ticket.categoryId} onChange={handleChange} required>
          {categories.map(category => (
            <option key={category.id} value={category.id}>{category.name}</option>
          ))}
        </select>
      </div>
      <button type="submit">Save</button>
    </form>
  );
};

export default TicketForm;
