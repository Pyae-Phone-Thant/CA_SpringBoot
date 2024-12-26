import React, { useState } from 'react';
import OrderCard from './OrderCard';
import OrderDetails from './OrderDetails'; // Import OrderDetails component
import { useOrders } from '../context/OrderContext'; // Import useOrders hook from OrderContext
import './OrderHistory.css'; // Optional: Include any additional styling

export default function OrderHistory() {
  const { orders, loading, error } = useOrders(); // Use the useOrders hook to get orders, loading, and error states

  const [selectedOrder, setSelectedOrder] = useState(null); // State to track selected order
  const [selectedYear, setSelectedYear] = useState('2024'); // State to filter orders by selected year
  const [searchTerm, setSearchTerm] = useState(''); // State to track search input

  // Function to handle viewing order details
  function handleViewDetails(order) {
    setSelectedOrder(order);
  }

  // Function to clear selected order and go back to order list
  function handleGoBack() {
    setSelectedOrder(null);
  }

  // Function to handle year filter change
  function handleYearChange(event) {
    setSelectedYear(event.target.value);
  }

  // Function to handle search term change
  function handleSearchChange(event) {
    setSearchTerm(event.target.value);
  }

  // If orders are still loading, show a loading message
  if (loading) return <p>Loading orders...</p>;

  // If there was an error fetching orders, show an error message
  if (error) return <p>Error fetching orders: {error.message}</p>;

// Filter orders based on the selected year and search term
const filteredOrders = orders.filter((order) => {
  // Ensure datePlaced exists and includes the selected year
  const matchesYear = order.datePlaced && order.datePlaced.includes(selectedYear);

  let matchesSearch = false;

  if(searchTerm === '' || order.items){
    for(let i=0; i < order.items.length; i++){
      const item = order.items[i];
      if(item.name && item.name.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1) {
        matchesSearch = true;
        break;
    }
  }
}
  console.log(order.items);  // Check if items exist and are consistent
  console.log(searchTerm);

  // Return true only if both year and search term conditions are met
  return matchesYear && matchesSearch;
});

  return (
    <div className="content order-history">
      {selectedOrder ? (
        // Show OrderDetails component if an order is selected
        <OrderDetails order={selectedOrder} goBack={handleGoBack} />
      ) : (
        <>
          <h2>Your Orders</h2>
          <div className="filter-bar">
            <select value={selectedYear} onChange={handleYearChange}>
              <option value="2024">2024</option>
              <option value="2023">2023</option>
              <option value="2022">2022</option>
              <option value="2021">2021</option>
              <option value="2020">2020</option>
            </select>
            <input
              className="search-bar"
              type="text"
              placeholder="Search by name"
              value={searchTerm}
              onChange={handleSearchChange}
            />
          </div>
          <div className="order-list">
            {/* Map through the filtered orders and display each as an OrderCard */}
            {filteredOrders.map((order) => (
              <OrderCard key={order.orderId} order={order} onViewDetails={() => handleViewDetails(order)} />
            ))}
          </div>
        </>
      )}
    </div>
  );
}