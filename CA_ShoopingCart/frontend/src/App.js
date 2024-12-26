// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import OrderHistory from './components/OrderHistory';
import './App.css';
import Header from './components/Header';
import Footer from './components/Footer';
import { OrderProvider } from './context/OrderContext'; // Import the OrderProvider
import 'bootstrap/dist/css/bootstrap.min.css';
import '@fortawesome/fontawesome-free/css/all.min.css';


function App() {
  return (
    <Router>
      <div className="App">
      <Header />
        {/* Wrap the Routes inside the OrderProvider to provide context to all routes */}
        <OrderProvider>
          <Routes>
            <Route path="/" element={<OrderHistory />} />
            {/* Add other routes here if needed */}
          </Routes>
        </OrderProvider>
        <Footer />
      </div>
    </Router>
  );
}

export default App;