import React, { useEffect, useState } from 'react';
import api from '../services/api';

const CategoryList = () => {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    api.get('/categories')
      .then(response => setCategories(response.data))
      .catch(error => console.error('Error fetching categories:', error));
  }, []);

  const handleDelete = (id) => {
    api.delete(`/categories/${id}`)
      .then(() => setCategories(categories.filter(category => category.id !== id)))
      .catch(error => console.error('Error deleting category:', error));
  };

  return (
    <div>
      <h2>Category List</h2>
      <ul>
        {categories.map(category => (
          <li key={category.id}>
            {category.name}
            <button onClick={() => handleDelete(category.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CategoryList;
