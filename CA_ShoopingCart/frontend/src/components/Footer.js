import React from 'react';

const Footer = () => (
  <footer className="footer-section bg-light text-center py-3">
    <p>© 2024 Buy Buy Store. All Rights Reserved.</p>
    <div>
      {['facebook', 'instagram', 'twitter'].map(platform => (
        <a key={platform} href={`https://www.${platform}.com`}>
          <img
            src={`http://localhost:8080/images/${platform}.png`}
            alt={`${platform} logo`}
            style={{ width: '30px', margin: '0 10px' }}
          />
        </a>
      ))}
    </div>
  </footer>
);

export default Footer;
