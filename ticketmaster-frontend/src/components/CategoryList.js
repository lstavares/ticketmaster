import React, { useEffect, useState } from 'react';
import api from '../services/api';
import { useNavigate, Link } from 'react-router-dom';
import '../List.css';

const CategoryList = () => {
  const [categories, setCategories] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    api.get('/categories')
      .then(response => setCategories(response.data))
      .catch(error => console.error('Error fetching categories:', error));
  }, []);

  const handleDelete = (id) => {
    if (window.confirm('Are you sure you want to delete this category?')) {
      api.delete(`/categories/${id}`)
        .then(() => {
          alert('Category deleted successfully.');
          setCategories(categories.filter(category => category.id !== id))
        })
        .catch(error => {
          console.error('There was an error deleting the category!', error);
        });
    }
  };

  const handleEdit = (id) => {
    navigate(`/categories/edit/${id}`);
  };

  return (
    <div className="list-container">
      <div className="list-header">
        <h2 className="list-title">Categories</h2>
        <Link to="/categories/new" className="add-button">Create New Category</Link>
      </div>
      <ul className="list-group">
        {categories.map(category => (
          <li key={category.id} className="list-group-item">
            <div>
              <h3 className="item-title">{category.title}</h3>
              <p className="item-description">{category.name}</p>
            </div>
            <div className="action-buttons">
              <button onClick={() => handleEdit(category.id)} className="edit-btn">Edit</button>
              <button onClick={() => handleDelete(category.id)} className="delete-btn">Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CategoryList;
