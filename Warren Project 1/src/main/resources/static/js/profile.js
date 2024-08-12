document.addEventListener('DOMContentLoaded', async function () {
    // check JWT token in localStorage
    const token = localStorage.getItem('jwtToken');

    if (token) {
        // if the JWT token exists fetch profile data
        try {
            const response = await fetch('http://54.168.221.156/api/1.0/user/profile', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const data = await response.json();
                // get the profile detail from profileAPI response
                displayUserProfile(data.data.user);
            } else {
                console.error('Failed to fetch profile data:', response.status);
            }
        } catch (error) {
            console.error('Error fetching profile data:', error);
        }
    } else {
        console.log('No JWT token found, showing login and signup forms.');
    }

    // Login
    document.getElementById('login-form').addEventListener('submit', async function (event) {
        event.preventDefault();

        const provider = 'native';
        const email = document.getElementById('login-email').value;
        const password = document.getElementById('login-password').value;

        try {
            const response = await fetch('http://54.168.221.156/api/1.0/user/signin', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({provider, email, password})
            });

            if (response.ok) {
                const data = await response.json();

                // store the JWT token in local storage
                localStorage.setItem('jwtToken', data.data.access_token);
                // get the profile detail from signIn API response
                displayUserProfile(data.data.user);

                alert('Login successful');
            } else {
                alert('Login failed');
                console.error('Login error:', response.status);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });

    // Signup
    document.getElementById('signup-form').addEventListener('submit', async function (event) {
        event.preventDefault();

        const name = document.getElementById('signup-name').value;
        const email = document.getElementById('signup-email').value;
        const password = document.getElementById('signup-password').value;

        try {
            const response = await fetch('http://54.168.221.156/api/1.0/user/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({name, email, password})
            });

            if (response.ok) {
                const data = await response.json();

                // Store the JWT token in local storage
                localStorage.setItem('jwtToken', data.data.access_token);
                // get the profile detail from signUp API response
                displayUserProfile(data.data.user);

                alert('Sign up successful');
            } else {
                alert('Sign up failed');
                console.error('Sign up error:', response.status);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });
});

function displayUserProfile(user) {
    // create a new div to display user profile data
    const userProfileContainer = document.getElementById('user-profile-container');

    const profileDiv = document.createElement('div');
    profileDiv.className = 'user-profile';

    const profilePicture = document.createElement('img');
    profilePicture.src = user.picture;
    profilePicture.alt = `${user.name}'s profile picture`;

    const profileName = document.createElement('h2');
    profileName.textContent = `Name: ${user.name}`;

    const profileEmail = document.createElement('p');
    profileEmail.textContent = `Email: ${user.email}`;

    const profileProvider = document.createElement('p');
    profileProvider.textContent = `Provider: ${user.provider}`;

    // append new elements to profileDiv
    profileDiv.appendChild(profilePicture);
    profileDiv.appendChild(profileName);
    profileDiv.appendChild(profileEmail);
    // add the profileDiv to userProfileContainer
    userProfileContainer.appendChild(profileDiv);
}