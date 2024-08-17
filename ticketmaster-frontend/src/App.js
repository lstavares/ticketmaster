import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import TicketList from './components/TicketList';
import CategoryList from './components/CategoryList';
import TicketForm from './components/TicketForm';
import CategoryForm from './components/CategoryForm';
import './App.css';

function App() {
  return (
    <Router>
      <div className="app-container">
        <nav className="navbar">
          <ul className="nav-list">
            <li className="nav-item">
              <Link to="/tickets" className="nav-link">Tickets</Link>
            </li>
            <li className="nav-item">
              <Link to="/categories" className="nav-link">Categories</Link>
            </li>
          </ul>
        </nav>

        <div className="content">
          <Routes>
            <Route path="/tickets" exact element={<TicketList />} />
            <Route path="/categories" exact element={<CategoryList />} />
            <Route path="/tickets/new" element={<TicketForm />} />
            <Route path="/categories/new" element={<CategoryForm />} />
            <Route path="/tickets/edit/:id" element={<TicketForm />} />
            <Route path="/categories/edit/:id" element={<CategoryForm />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;