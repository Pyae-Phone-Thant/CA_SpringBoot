// src/components/OrderCard.js
import React from 'react';
import jsPDF from 'jspdf';
import './OrderCard.css';
import ASUSZenBook from '../assets/images/ASUS ZenBook_1.jpg';
import LenovoLegion from '../assets/images/Lenovo Legion_1.jpg';
import MSICyborg from '../assets/images/MSI Cyborg_1.jpg';
import ACERSwift from '../assets/images/ACER Swift_1.jpg';
import ASUSVivoBook from '../assets/images/ASUS VivoBook_1.jpg';
import LenovoYoga from '../assets/images/Lenovo Yoga_1.jpg';

// OrderCard is the component that displays the order details. It has 2 props, order and onViewDetails.
const OrderCard = ({ order, onViewDetails }) => {
  
  // Function to generate PDF receipt using jsPDF.
  // Content is writen as 1. content written. 2. x-coordinate. 3. y-coordinate
  //The PDF receipt will have order details. Order ID, Date placed, Total cost paid, delivered to and order items.
  const generatePDFReceipt = () => {
    const doc = new jsPDF();
    doc.setFont("times", "italic");
    doc.setFontSize(12);

    doc.text(`Order ID: ${order.orderId}`, 10, 30);
    doc.text(`Date Placed: ${order.datePlaced}`, 10, 40);
    doc.text(`Total: ${order.total}`, 10, 50);
    doc.text(`Delivered To: ${order.deliveredTo}`, 10, 60);
    doc.text(`Delivery Address: ${order.deliveryAddress}`, 10, 70);
    order.items.forEach((item, index) => {
      doc.text(`Item: ${item.name}`, 10, 80 + index * 10);
    });
    doc.save('receipt.pdf');
  };

  // Mapping object to link item names to images
  const imageUrlGenerate = {
    'ASUSZenBook': ASUSZenBook,
    'LenovoLegion': LenovoLegion,
    'MSICyborg':MSICyborg,
    'ACERSwift': ACERSwift,
    'ASUSVivoBook':ASUSVivoBook,
    'LenovoYoga':LenovoYoga
  }

  return (
    <div className="order-card">
      <div className="order-details">
        <div>
          <strong>ORDER PLACED</strong>
          <p>{order.datePlaced}</p>
        </div>
        <div>
          <strong>TOTAL</strong>
          <p>{order.total}</p>
        </div>
        <div>
          <strong>DELIVER TO</strong>
          <p>{order.deliveredTo}</p>
        </div>
        <div>
          <strong>ORDER #</strong>
          <p>{order.orderId}</p>
        </div>
      </div>

      <div className="order-summary">
        {order.items.map((item, index) => {

          const imageIcon = imageUrlGenerate[item.imageUrl] || ASUSZenBook;
          return(
            <div key={index} className="order-item">
            <img src={imageIcon} alt={item.name} className="product-image" />
            <div className="item-details">
              <h4>{item.name}</h4>
              <p>{item.status}</p>
            </div>
          </div>
          );
        })}
      </div>

      <div className="order-actions">
        <button className="action-button" onClick={onViewDetails}>
          View Order Details
        </button>
        <button className="action-button" onClick={generatePDFReceipt}>
          Receipt
        </button>
      </div>
    </div>
  );
};

export default OrderCard;
