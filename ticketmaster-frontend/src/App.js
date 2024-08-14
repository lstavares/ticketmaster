import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import TicketList from './components/TicketList';
import CategoryList from './components/CategoryList';
import TicketForm from './components/TicketForm';
import CategoryForm from './components/CategoryForm';

function App() {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/tickets">Tickets</Link>
            </li>
            <li>
              <Link to="/categories">Categories</Link>
            </li>
            <li>
              <Link to="/tickets/new">Create Ticket</Link>
            </li>
            <li>
              <Link to="/categories/new">Create Category</Link>
            </li>
          </ul>
        </nav>

        <Routes>
          <Route path="/tickets" element={<TicketList />} />
          <Route path="/categories" element={<CategoryList />} />
          <Route path="/tickets/new" element={<TicketForm />} />
          <Route path="/categories/new" element={<CategoryForm />} />
          <Route path="/tickets/edit/:id" element={<TicketForm />} />
          <Route path="/categories/edit/:id" element={<CategoryForm />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
