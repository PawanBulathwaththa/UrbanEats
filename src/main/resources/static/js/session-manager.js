// sessionManager.js - Include this file on all pages

// Function to update the user menu based on session
function updateUserMenu() {
    const userMenu = document.getElementById('userMenu');
    if (!userMenu) return; // Safety check in case element doesn't exist on this page
    
    const currentUser = sessionStorage.getItem('currentUser');
    
    if (currentUser) {
        // User is logged in
        const userData = JSON.parse(currentUser);
        
        // Clear existing menu
        userMenu.innerHTML = '';
        
        // Create welcome text with username
        const welcomeItem = document.createElement('li');
        welcomeItem.innerHTML = `<span style="padding: 10px; display: block;">Welcome, ${userData.username || userData.email}</span>`;
        userMenu.appendChild(welcomeItem);
        
        // Add account-related links
        const accountItem = document.createElement('li');
        accountItem.innerHTML = '<a href="account.html">My Account</a>';
        userMenu.appendChild(accountItem);
        
        const wishlistItem = document.createElement('li');
        wishlistItem.innerHTML = '<a href="wishlist.html">Wishlist</a>';
        userMenu.appendChild(wishlistItem);
        
        // Add logout option
        const logoutItem = document.createElement('li');
        logoutItem.innerHTML = '<a href="#" id="logoutLink">Logout</a>';
        userMenu.appendChild(logoutItem);
        
        // Add logout functionality
        setTimeout(() => {
            const logoutLink = document.getElementById('logoutLink');
            if (logoutLink) {
                logoutLink.addEventListener('click', function(e) {
                    e.preventDefault();
                    sessionStorage.removeItem('currentUser');
                    alert('You have been logged out successfully!');
                    window.location.href = 'login.html'; // Redirect to login page
                });
            }
        }, 100);        
    } else {
        // User is not logged in, show default menu
        userMenu.innerHTML = `
            <li><a href="login.html">Sign in</a></li>
            <li><a href="register.html">Register</a></li>
        `;
    }
}

// Check if user is logged in
function isLoggedIn() {
    return sessionStorage.getItem('currentUser') !== null;
}

// Get current user data
function getCurrentUser() {
    const userData = sessionStorage.getItem('currentUser');
    return userData ? JSON.parse(userData) : null;
}

// Logout function
function logout() {
    sessionStorage.removeItem('currentUser');
    window.location.reload();
}

// Run when the document is loaded
document.addEventListener('DOMContentLoaded', function() {
    updateUserMenu();
});