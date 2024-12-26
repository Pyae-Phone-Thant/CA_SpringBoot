// src/components/OrderDetails.js
import React from 'react';
import './OrderDetails.css'; // Include the corresponding CSS file
import mastercard from '../assets/images/mastercard.svg';
import visa from '../assets/images/visa.svg';
import googlePay from '../assets/images/googlePay.svg';
import paynow from '../assets/images/PayNow.svg';
import applepay from '../assets/images/applepay.svg';
import grabpay from '../assets/images/Grabpay.svg';
import banktransfer from '../assets/images/banktransfer.svg';
import paypal from '../assets/images/paypal.svg';
import alipay from '../assets/images/alipay.svg';
import paymenterror from '../assets/images/paymentError.svg'
import ASUSZenBook from '../assets/images/ASUS ZenBook_1.jpg';
import LenovoLegion from '../assets/images/Lenovo Legion_1.jpg';
import MSICyborg from '../assets/images/MSI Cyborg_1.jpg';
import ACERSwift from '../assets/images/ACER Swift_1.jpg';
import ASUSVivoBook from '../assets/images/ASUS VivoBook_1.jpg';
import LenovoYoga from '../assets/images/Lenovo Yoga_1.jpg';


export default function OrderDetails({ order, goBack }) {
  // Create a mapping object for the payment method icons
  const paymentMethodIcons = {
    'VISA': visa,
    'MASTERCARD': mastercard,
    'GOOGLE PAY': googlePay,
    'PAYNOW': paynow,
    'APPLE PAY': applepay,
    'GRAB PAY': grabpay,
    'PAYPAL':paypal,
    'BANK TRANSFER': banktransfer,
    'ALIPAY': alipay,
    'Payment Error': paymenterror
  };

  // Dynamically choose the icon based on the payment method
  // Default to `visa` if the `order.paymentMethod` is not found in the mapping
  const paymentIcon = paymentMethodIcons[order.paymentMethod] || paymenterror;

  // Mapping object to link item names to images
  const imageUrlGenerate = {
    'ASUSZenBook': ASUSZenBook,
    'LenovoLegion': LenovoLegion,
    'MSICyborg':MSICyborg,
    'ACERSwift': ACERSwift,
    'ASUSVivoBook':ASUSVivoBook,
    'LenovoYoga':LenovoYoga
  }

  // Calculate grand total based on order items
  const grandTotal = order.items.reduce(
    (total, item) => total + item.quantity * parseFloat(item.price.replace('S$', '')),
    0
  );

  return (
    <div className="order-details-page">
      <button className="back-button" onClick={goBack}>
        Back to Orders
      </button>
      <h2>Order Details</h2>
      <p>
        <strong>Ordered on:</strong> {order.datePlaced}
      </p>
      <p>
        <strong>Order#:</strong> {order.orderId}
      </p>

      <div className="order-details-container">
        <div className="delivery-address">
          <h4>Delivery Address</h4>
          <p>{order.deliveredTo}</p>
          <p>{order.deliveryAddress}</p>
        </div>

        <div className="payment-method">
          <h4>Payment Method</h4>
          <p>
            <img src={paymentIcon} alt={order.paymentMethod} width="50" />
            {order.paymentMethod}
          </p>
        </div>

        <div className="order-summary">
          <h4>Order Summary</h4>
          
          {/* Loop through each order item and display the details */}
          {order.items.map((item, index) => {
          const imageIcon = imageUrlGenerate[item.imageUrl] || ASUSZenBook;
          return (
            <div key={index} className="order-item-summary">
              <img src={imageIcon} alt={item.name} className="product-image" />
              <div className="item-details">
                <p><strong>{item.name}</strong></p>
                <p>Quantity: {item.quantity}</p>
                <p>Price: {item.price}</p>
                <p>Subtotal: S${(item.quantity * parseFloat(item.price.replace('S$', ''))).toFixed(2)}</p>
              </div>
            </div>
          );
          })}

          <p>
            <strong>Delivery:</strong> Free
          </p>
          <h4>Grand Total: S${grandTotal.toFixed(2)}</h4>
        </div>
      </div>
    </div>
  );
}
