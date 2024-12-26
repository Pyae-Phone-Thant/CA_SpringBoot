// src/context/OrderContext.js
import React, { createContext, useContext, useState, useEffect } from 'react';
import axios from 'axios';


// Create a context for orders
const OrderContext = createContext();

// Create a custom hook to use the OrderContext
export const useOrders = () => useContext(OrderContext);

// Create a provider component that wraps around children and provides the orders data
export const OrderProvider = ({ children }) => {
  const [orders, setOrders] = useState([]); // State to hold orders
  const [loading, setLoading] = useState(true); // State to indicate loading status
  const [error, setError] = useState(null); // State to hold any errors

  useEffect(() => {

    // Set up axios to include credentials with requests
    axios.defaults.withCredentials = true;

    // Fetch orders data
    const fetchOrders = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/order-history',{
          withCredentials: true, // Ensure cookies (e.g., JSESSIONID) are included in the request
        });
        setOrders(response.data);
        setLoading(false);
      } catch (err) {
        setError(err);
        setLoading(false);
      }
    };

    fetchOrders(); // Call the fetchOrders function when component mounts
  }, []);

  // Return the provider component with value prop to make `orders` available to children components
  return (
    <OrderContext.Provider value={{ orders, loading, error }}>
      {children}
    </OrderContext.Provider>
  );
};
