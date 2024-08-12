import React, { useState, useEffect } from 'react';
import api from '../services/api';

const CategoryForm = ({ categoryId }) => {
  const [category, setCategory] = useState({ name: '', parentId: null });
  const [categories, setCategories] = useState([]);

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
    setCategory({ ...category, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const apiMethod = categoryId ? api.put : api.post;
    const endpoint = categoryId ? `/categories/${categoryId}` : '/categories';

    apiMethod(endpoint, category)
      .then(() => alert('Category saved successfully!'))
      .catch(error => console.error('Error saving category:', error));
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Name</label>
        <input name="name" value={category.name} onChange={handleChange} required />
      </div>
      <div>
        <label>Parent Category</label>
        <select name="parentId" value={category.parentId || ''} onChange={handleChange}>
          <option value=''>None</option>
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
