// src/components/Header.js
import React from 'react';
import './Header.css';
import logo from '../assets/images/logo.jpeg';
import { Navbar, Nav, Form, FormControl, Button, NavDropdown } from 'react-bootstrap';
import SearchBar from './SearchBar'; // SearchBar component


export default function Header() {

  //Handle the logout click
  async function handleLogout(){
    try {
      // Send the logout request to the backend
      const response = await fetch('http://localhost:8080/ordershistorylogout', {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (response.ok) {
        // Redirect to the home page after successful logout
        window.location.href = 'http://localhost:8080/';
      } else {
        console.error('Logout failed');
      }
    } catch (error) {
      console.error('Error during logout:', error);
    }
  };

//     return (
//       <header className="header">
//         <div className="header-logo">
//           <a href="http://localhost:8080">
//           <img src={logo} alt="BuyBuy Logo" style={{ height: '50px' }} />
//           </a>
//         </div>
// {/*         <div className="header-search">
//           <input type="text" placeholder="Search Product" />
//           <span style={{ cursor: 'pointer' }}>🔍</span>
//         </div> */}
//         <div className="header-actions">
//           <span 
//           style={{ cursor: 'pointer' }} 
//           onClick={handleLogout}
//         >
//           Logout
//         </span>
//         </div>
//       </header>
//     );
return (
  <>
    {/* First Navigation Bar */}
    <Navbar bg="light" expand="lg">
      <Navbar.Brand href="http://localhost:8080">
      <img src={logo} alt="BuyBuy Logo" style={{ height: '50px' }} />
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="navbarSearchCart" />
      <Navbar.Collapse  className="justify-content-end" id="navbarSearchCart">
        <SearchBar /> {/* Search Bar */}
        <Nav>
            <Nav.Link href="http://localhost:8080/cart">
              <i className="fas fa-shopping-cart"></i>
            </Nav.Link>
          </Nav>
      </Navbar.Collapse>
    </Navbar>

    {/* Second Navigation Bar */}
    <Navbar bg="dark" variant="dark" expand="lg">
      <div className="container">
        <Navbar.Toggle aria-controls="navbarNav" />
        <Navbar.Collapse id="navbarNav">
          <Nav>
            <Nav.Link href="http://localhost:8080"><i className="fa fa-home"></i> Home</Nav.Link>
            <NavDropdown title="Categories" id="categoriesDropdown">
              <NavDropdown.Item href="http://localhost:8080/products/category/Laptop">Laptop</NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title="Brands" id="brandsDropdown">
              <NavDropdown.Item href="http://localhost:8080/products/brand/ASUS">ASUS</NavDropdown.Item>
              <NavDropdown.Item href="http://localhost:8080/products/brand/ACER">ACER</NavDropdown.Item>
              <NavDropdown.Item href="http://localhost:8080/products/brand/LENOVO">LENOVO</NavDropdown.Item>
              <NavDropdown.Item href="http://localhost:8080/products/brand/MSI">MSI</NavDropdown.Item>
            </NavDropdown>
          </Nav>
          </Navbar.Collapse>  
          <Navbar.Collapse className="justify-content-end">
          <Nav> 
            <NavDropdown title="My Profile" id="myProfileDropdown">
              <NavDropdown.Item href="http://localhost:8080/view-profile">View Profile</NavDropdown.Item>
              <NavDropdown.Item href="http://localhost:8080/change-password">Change Password</NavDropdown.Item>
              <NavDropdown.Item href="http://localhost:3000">View Orders</NavDropdown.Item>
            </NavDropdown>
            <Nav.Link onClick={handleLogout} style={{ cursor: 'pointer' }}>
                <i className="fa fa-sign-out"></i> Logout
              </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </div>
    </Navbar>
  </>
);
  }