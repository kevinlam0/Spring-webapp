import React from 'react';
import { Navbar, Nav, Container } from 'react-bootstrap';
import { NavLink } from 'react-router-dom';
import styles from './NavBar.module.css';
import { useUser } from "./context/UserContext";

const NavBar = () => {
  const { user, handleLogout } = useUser();
  const handleLogoutSubmission = () => {
    handleLogout();
  }
  return (
    <Navbar bg="white" expand="lg" className={styles.navbar}>
      <Container className={styles.navbarContainer} >
        <Navbar.Brand id="navbrand" as={NavLink} className="navbar-brand fs-3" style={{ color: '#1a7431' }} to="/">Kevin's Blog</Navbar.Brand>
        <div className='nav-link-container'>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link as={NavLink} to="/" className={styles.navLink}>
                Home
              </Nav.Link>
              {
                user === "Guest" ?
                  <Nav.Link as={NavLink} to="/login" className={styles.navLink}>
                    Login
                  </Nav.Link>
                  :
                  <Nav.Link as={NavLink} onClick={handleLogoutSubmission} className={styles.navLink}>
                    Logout
                  </Nav.Link>
              }
            </Nav>
          </Navbar.Collapse>
        </div>
      </Container>
    </Navbar>
  );
};

export default NavBar;