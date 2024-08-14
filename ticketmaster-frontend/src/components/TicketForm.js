import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';

const TicketForm = ({ ticketId }) => {
  const [ticket, setTicket] = useState({ title: '', description: '', categoryId: '', subcategoryId: '', severityLevel: '' });
  const [categories, setCategories] = useState([]);
  const [subcategories, setSubcategories] = useState({});
  const [selectedCategories, setSelectedCategories] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    api.get('/categories')
      .then(response => {
        const rootCategories = response.data.filter(category => !category.parent);
        setCategories(rootCategories);
      })
      .catch(error => console.error('Error fetching categories:', error));

    if (ticketId) {
      api.get(`/tickets/${ticketId}`)
        .then(response => setTicket(response.data))
        .catch(error => console.error('Error fetching ticket:', error));
    }
  }, [ticketId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTicket({ ...ticket, [name]: value });

    if (name.startsWith('categoryId')) {
      const level = parseInt(name.replace('categoryId', ''), 10);
      fetchSubcategories(value, level);
    }
  };

  const fetchSubcategories = (categoryId, level) => {
    api.get(`/categories/${categoryId}`)
      .then(response => {
        const subcategoriesData = response.data.subcategories;
        setSubcategories(prev => ({ ...prev, [level]: subcategoriesData }));
        setSelectedCategories(prev => prev.slice(0, level).concat(categoryId));

        // Update categoryId and subcategoryId in the ticket state
        const newTicket = { ...ticket };
        if (subcategoriesData.length > 0) {
          newTicket.categoryId = categoryId;
          newTicket.subcategoryId = '';
        } else {
          newTicket.subcategoryId = categoryId;
        }
        setTicket(newTicket);
      })
      .catch(error => console.error('Error fetching subcategories:', error));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const apiMethod = ticketId ? api.put : api.post;
    const endpoint = ticketId ? `/tickets/${ticketId}` : '/tickets';

    apiMethod(endpoint, ticket)
      .then(() => {
        navigate('/tickets');
        alert('Ticket saved successfully!');
      })
      .catch(error => {
        if (error.response && error.response.status === 400) {
          alert(error.response.data.message);
        } else {
          console.error('Error saving ticket:', error);
        }
      });
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
        <select name="categoryId0" value={selectedCategories[0] || ''} onChange={handleChange} required>
          <option value="">-- Select one --</option>
          {categories.map(category => (
            <option key={category.id} value={category.id}>{category.name}</option>
          ))}
        </select>
      </div>
      {selectedCategories.map((categoryId, index) => (
        subcategories[index] && subcategories[index].length > 0 && (
          <div key={`subcategory-${index}`}>
            <label>Subcategory - {categories.find(cat => cat.id === categoryId)?.name}</label>
            <select name={`categoryId${index + 1}`} value={selectedCategories[index + 1] || ''} onChange={handleChange} required>
              <option value="">-- Select one --</option>
              {subcategories[index].map(subcategory => (
                <option key={subcategory.id} value={subcategory.id}>{subcategory.name}</option>
              ))}
            </select>
          </div>
        )
      ))}
      <div>
        <label>Severity</label>
        <select name="severityLevel" value={ticket.severityLevel} onChange={handleChange} required>
          <option value="">-- Select one --</option>
          <option value="1">Issue high</option>
          <option value="2">High</option>
          <option value="3">Medium</option>
          <option value="4">Low</option>
        </select>
      </div>
      <button type="submit">Save</button>
    </form>
  );
};

export default TicketForm;
