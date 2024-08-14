import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import api from '../services/api';

const CategoryForm = () => {
  const { categoryId } = useParams();
  const [category, setCategory] = useState({ name: '', parent: null });
  const [categories, setCategories] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    api.get('/categories')
      .then(response => setCategories(response.data))
      .catch(error => console.error('Error fetching categories:', error));

    if (categoryId) {
      api.get(`/categories/${categoryId}`)
        .then(response => setCategory(response.data))
        .catch(error => console.error('Error fetching category:', error));
    }
  }, [categoryId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCategory({ ...category, [name]: value });
  };

  const handleParentChange = (e) => {
    const parentId = e.target.value;
    const parentCategory = categories.find(cat => cat.id === parseInt(parentId, 10));
    setCategory({ ...category, parent: parentCategory || null });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const apiMethod = categoryId ? api.put : api.post;
    const endpoint = categoryId ? `/categories/${categoryId}` : '/categories';

    apiMethod(endpoint, category)
      .then(() => {
        alert('Category saved successfully!');
        navigate('/categories');
      })
      .catch(error => {
        if (error.response && error.response.status === 400) {
          alert(error.response.data.message);
        } else {
          console.error('Error saving category:', error);
        }
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Name</label>
        <input name="name" value={category.name} onChange={handleChange} required />
      </div>
      <div>
        <label>Parent Category</label>
        <select name="parent" value={category.parent?.id || ''} onChange={handleParentChange}>
          <option value="">-- Select one --</option>
          {categories.map(cat => (
            <option key={cat.id} value={cat.id}>{cat.name}</option>
          ))}
        </select>
      </div>
      <button type="submit">Save</button>
    </form>
  );
};

export default CategoryForm;
