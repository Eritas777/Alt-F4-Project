// ... your existing JavaScript code ...

function adjustFontSize() {
const userNameElement = document.getElementById('userName');
const nameTextElement = document.getElementById('nameText');

if (userNameElement && nameTextElement) {
let fontSize = 16; // Initial font size (adjust this as needed)

while (userNameElement.offsetWidth > userNameElement.parentElement.offsetWidth) {
fontSize--;
userNameElement.style.fontSize = fontSize + 'px';
nameTextElement.style.fontSize = fontSize + 'px'; // Adjust the "Имя" text as well
}
}
}

// Function to display user data
function displayUserData(data) {
document.getElementById('userName').innerText = data.firstName + ' ' + data.lastName || 'Не указано';
document.getElementById('userEmail').innerText = data.email || 'Не указано';
document.getElementById('userPhone').innerText = data.phoneNumber || 'Не указано';
document.getElementById('userGender').innerText = data.gender || 'Не указано';
document.getElementById('userBirthday').innerText = data.dateOfBirth || 'Не указано';
document.getElementById('userAddress').innerText = data.residentialAdress || 'Не указано';
document.getElementById('Idsystem').innerText = data.systemId || 'Не указано';

// Adjust font size after updating the name
adjustFontSize();
}

// Function to fetch user data from the backend
async function fetchUserData() {
try {
// Fetch the email URL from the backend
const urlEmailResponse = await fetch('http://localhost:8080/api/platform/email');
if (!urlEmailResponse.ok) {
throw new Error('Сетевая ошибка при получении URL email: ' + urlEmailResponse.status);
}
const finalUrl = await urlEmailResponse.text();
console.log('Полученный URL email:', finalUrl);

// Fetch the user data
const response = await fetch('http://localhost:8080/api/platform/' + finalUrl);
if (!response.ok) {
throw new Error('Сетевая ошибка: ' + response.status);
}
const userData = await response.json();
displayUserData(userData);
} catch (error) {
console.error('Ошибка при получении данных пользователя:', error);
document.getElementById('profileInfo').innerText = 'Не удалось загрузить данные.';
}
}

// Load data when the page loads
window.onload = fetchUserData;