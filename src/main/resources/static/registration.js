document.getElementById('registration-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Отменяем стандартное действие формы

    // Собираем данные из формы
    const userData = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        role: document.getElementById('role').value,
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        phoneNumber: document.getElementById('phone-number').value,
        dateOfBirth: document.getElementById('date-of-birth').value,
        gender: document.getElementById('gender').value,
        residentialAdress: document.getElementById('address').value
    };

     let apiUrl;

        // Выбор URL в зависимости от выбранной роли
        switch (userData.role) {
            case 'student':
                apiUrl = 'http://localhost:8080/api/platform/new-student';
                break;
            case 'teacher':
                apiUrl = 'http://localhost:8080/api/platform/new-teacher';
                break;
            case 'adm':
                apiUrl = 'http://localhost:8080/api/platform/new-admin';
                break;
            default:
                console.error('Неизвестная роль');
                return; // Выход, если роль неизвестна
        }

    // Отправка POST-запроса
    fetch(apiUrl, { // Замените '/api/register' на ваш конечный URL
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData) // Преобразуем объект в строку JSON
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Ошибка регистрации');
        }
        return response.json();
    })
    .then(data => {
        console.log('Успешно зарегистрировано:', data);
        window.location.href = 'http://localhost:8080/auth.html';
        // Здесь можно обработать успешную регистрацию (например, перенаправить пользователя)
    })
    .catch(error => {
        console.error('Ошибка:', error);
        console.log(userData);
        // Здесь можно показать сообщение об ошибке пользователю
    });
});
