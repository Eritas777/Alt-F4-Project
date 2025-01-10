
let messageContainer;
let errorContainer;

// Функции для отображения сообщений об успехе и ошибке
function displaySuccess(message) {
    displayMessage(message, 'success');
}

function displayError(message) {
    displayMessage(message, 'error');
    errorContainer.textContent = message;
}

function displayMessage(message, type) {
    messageContainer.textContent = message;
    messageContainer.className = `message ${type}`;
    setTimeout(() => {
        messageContainer.textContent = '';
        messageContainer.className = 'message';
    }, 5000);
}

document.addEventListener('DOMContentLoaded', () => {
    const pollsContainer = document.getElementById('polls-container');
    messageContainer = document.getElementById('message-container');
    errorContainer = document.getElementById('error-container')
    const form = document.getElementById('create-poll-form')
    const questionForm = document.getElementById('create-question-form');
    const optionForm = document.getElementById('create-option-form');
    const questionsContainer = document.getElementById('questions-container');
    const optionsContainer = document.getElementById('options-container');

    form.addEventListener('submit', async (event) =>{
        event.preventDefault();
        try {
            // Получаем данные из полей ввода (предполагается, что у вас есть поля с ID)
            const titleInput = document.getElementById('poll-title');
            const descriptionInput = document.getElementById('poll-description');

            const title = titleInput.value;
            const description = descriptionInput.value;

            const pollData = {
                title: title,
                description: description
            };

            const apiEndpoint = "http://localhost:8080/api/platform/polls/add"; // URL вашего API

            const response = await fetch(apiEndpoint, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(pollData),
            });

            if (!response.ok) {
                const errorData = await response.json();
                const errorMessage = `Ошибка создания опроса: ${response.status} ${errorData.message || ''}`;
                displayError(errorMessage);
                return;
            }

            const result = await response.json();
            displaySuccess(`Опрос успешно создан! ID: ${result.id}`);
            displayPoll(result);
        }
        catch (error) {
            console.error('Ошибка при создании опроса:', error);
            displayError('Ошибка при создании опроса.');
        }
    });


    questionForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        try {
            const questionText = document.getElementById('question-text').value;
            const pollId = document.getElementById('poll-id-for-question').value;

            const questionData = {
                text: questionText,
            };

            const apiEndpoint = `http://localhost:8080/api/platform/polls/questions/add/${pollId}`;

            const response = await fetch(apiEndpoint, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(questionData),
            });

            if (!response.ok) {
                const errorData = await response.json();
                const errorMessage = `Ошибка создания вопроса: ${response.status} ${errorData.message || ''}`;
                displayError(errorMessage);
                return;
            }

            const questionResult = await response.json();
            displaySuccess(`Вопрос успешно создан! ID: ${questionResult.id}`);
            loadQuestions(pollId);
        }
        catch (error) {
            console.error('Ошибка при создании вопроса:', error);
            displayError('Ошибка при создании вопроса.');
        }
    });

    optionForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        try {
            const optionText = document.getElementById('option-text').value;
            const questionId = document.getElementById('question-id-for-option').value;

            const optionData = {
                text: optionText,
            };

            const apiEndpoint = `http://localhost:8080/api/platform/polls/questions/options/add-single/${questionId}`;

            const response = await fetch(apiEndpoint, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(optionData),
            });
            if (!response.ok) {
                const errorData = await response.json();
                const errorMessage = `Ошибка создания варианта ответа: ${response.status} ${errorData.message || ''}`;
                displayError(errorMessage);
                return;
            }

            const optionResult = await response.json();
            displaySuccess(`Вариант ответа успешно создан! ID: ${optionResult.id}`);
            loadOptions(questionId);

        }
        catch (error) {
            console.error('Ошибка при создании варианта ответа:', error);
            displayError('Ошибка при создании варианта ответа.');
        }
    });
    // Функция для загрузки списка опросов при загрузке страницы
    async function loadPolls() {
        try{
            const apiEndpoint = "http://localhost:8080/api/platform/polls/poll-list";
            const response = await fetch(apiEndpoint);
            if(!response.ok) {
                throw new Error(`Ошибка загрузки списка опросов ${response.status}`)
            }
            const polls = await response.json();
            displayPolls(polls); // Отобразить опросы на странице
        }
        catch (error){
            console.error('Ошибка при загрузке списка опросов:', error);
            displayError(`Ошибка при загрузке списка опросов: ${error.message}`)
            pollsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;
        }
    }
    // Функция для загрузки вопросов для выбранного опроса
    async function loadQuestions(pollId) {
        try {
            const apiEndpoint = `http://localhost:8080/api/platform/polls/questions/question-list/${pollId}`;
            const response = await fetch(apiEndpoint);
            if (!response.ok) {
                throw new Error(`Ошибка загрузки вопросов: ${response.status}`);
            }

            const questions = await response.json();
            displayQuestions(questions);

        } catch (error) {
            console.error('Ошибка при загрузке вопросов:', error);
            displayError(`Ошибка при загрузке вопросов: ${error.message}`);
            questionsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;
        }
    }
    // Функция для загрузки вариантов ответа для выбранного вопроса
    async function loadOptions(questionId) {
        try {
            const apiEndpoint = `http://localhost:8080/api/platform/polls/questions/options/options-list/${questionId}`;
            const response = await fetch(apiEndpoint);
            if (!response.ok) {
                throw new Error(`Ошибка загрузки вариантов ответа: ${response.status}`);
            }

            const options = await response.json();
            displayOptions(options);

        }
        catch (error) {
            console.error('Ошибка при загрузке вариантов ответа:', error);
            displayError(`Ошибка при загрузке вариантов ответа: ${error.message}`);
            optionsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;

        }
    }

    loadPolls();
    function displayPoll(poll) {
        const pollDataContainer = document.getElementById('poll-data');
        pollDataContainer.innerHTML = '';
        let output = "<pre>";
        output +=`ID: ${poll.id}<br>`
        output +=`Title: ${poll.title}<br>`
        output +=`Description: ${poll.description}<br>`
        output += "</pre>";
        pollDataContainer.innerHTML = output;
        loadQuestions(poll.id);
    }
    function displayPolls(polls) {
        if(!polls) {
            pollsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;
            return;
        }
        let output = "";
        polls.forEach((poll) => {
            output += `<div class="poll" onclick="displayPoll( ${JSON.stringify(poll)} )">
                <p>ID: ${poll.id}</p>
                <p>Title: ${poll.title}</p>
                <p>Description: ${poll.description}</p>
            </div>
        `;
        });
        pollsContainer.innerHTML = output;
    }
    function displayQuestions(questions) {

        if(!questions || !questions.questions) {
            questionsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;
            return;
        }
        let output = "";
        questions.questions.forEach((question) => {
            output += `<div class="question" onclick="loadOptions( ${JSON.stringify(question.id)} )">
                <p>ID: ${question.id}</p>
                <p>Text: ${question.text}</p>
            </div>
        `;
        });
        questionsContainer.innerHTML = output;
    }

    function displayOptions(options) {
        if(!options || !options.options) {
            optionsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;
            return;
        }
        let output = "";
        options.options.forEach((option) => {
            output += `<div class="option">
                <p>ID: ${option.id}</p>
                <p>Text: ${option.text}</p>
            </div>
        `;
        });
        optionsContainer.innerHTML = output;
    }
});