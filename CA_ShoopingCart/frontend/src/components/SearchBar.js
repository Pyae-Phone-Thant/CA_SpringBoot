// src/components/SearchBar.js
import React, { useState } from 'react';
import axios from 'axios';
import { FormControl } from 'react-bootstrap';

const SearchBar = () => {
  const [query, setQuery] = useState('');
  const [suggestions, setSuggestions] = useState([]);

  const handleInputChange = (e) => {
    const value = e.target.value;
    setQuery(value);

    if (value.length >= 2) {
      axios.get(`http://localhost:8080/products/search?query=${value}`)
        .then((response) => setSuggestions(response.data))
        .catch(() => setSuggestions([]));
    } else {
      setSuggestions([]);
    }
  };

  return (
    <div style={{ position: 'relative', width: '20%' }}>
      <FormControl
        type="text"
        placeholder="Search Product"
        value={query}
        onChange={handleInputChange}
        autoComplete="off"
      />
      {suggestions.length > 0 && (
        <div className="suggestions">
          {suggestions.map((item) => (
            <div
              key={item.id}
              onClick={() => window.location.href = `http://localhost:8080/product/${item.id}`}
              className="suggestion-item"
            >
               <img
                src={`http://localhost:8080/images/${item.name}_1.jpg`} // Adjust the image path as needed
                alt={item.name}
                className="suggestion-image"
              />
              <span>{item.name}</span>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default SearchBar;