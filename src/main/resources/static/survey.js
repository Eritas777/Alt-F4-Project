document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('create-survey-form');
    const messageContainer = document.getElementById('message-container');

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const title = document.getElementById('survey-title').value;
        const description = document.getElementById('survey-description').value;

         const surveyData = {
            title: title,
             description: description,
             questions: [] // на начальном этапе вопросов нет
        };
      try {
            const apiEndpoint = "http://localhost:8080/api/platform/surveys"; // Замените на URL вашего API
            const response = await fetch(apiEndpoint, {
                method: 'POST',
                 headers: {
                  'Content-Type': 'application/json',
                },
                 body: JSON.stringify(surveyData),
            });
           if (!response.ok) {
              const errorData = await response.json()
               throw new Error(`Ошибка создания опроса: ${response.status} ${errorData.message}`);
            }

            const result = await response.json()
            console.log(result);
            showMessage('Опрос успешно создан! ID: ' + result.id, 'success'); // выведем ID
       } catch (error) {
            console.error("Ошибка при создании опроса:", error);
           showMessage(error.message, 'error');
       }
    });


    function showMessage(message, type) {
         messageContainer.textContent = message;
        messageContainer.className = `message ${type}`;
      }
});