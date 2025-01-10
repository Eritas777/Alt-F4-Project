let selectedOptions = {};
let messageContainer;
let errorContainer;
async function addVote(optionId) {
  try {
    const apiEndpoint = `http://localhost:8080/api/platform/polls/questions/options/vote/${optionId}`; // Исправлен apiEndpoint
    const response = await fetch(apiEndpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    if (!response.ok) {
      const errorData = await response.json();
      const errorMessage = `Ошибка добавления голоса: ${response.status} ${errorData.message || ''}`;
      throw new Error(errorMessage);
    }
    const result = await response.json();
    console.log(`vote added to optionId: ${optionId}`, result);
  } catch (error) {
    console.error('Ошибка при добавлении голоса:', error);
    displayError(`Ошибка при добавлении голоса: ${error.message}`);
  }
}
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
function displayPoll(element) {
  const pollDataContainer = document.getElementById('poll-data');
  pollDataContainer.innerHTML = '';
  const pollId = element.dataset.id;
  let output = `<pre>Опрос ${pollId}</pre>`;
  output += `<div id="questions-container-${pollId}"></div>`;
  //убрали options container
  output += `<button id="submit-answers-${pollId}" onclick="submitAnswers(this)" data-poll-id="${pollId}">Подтвердить</button>`;
  pollDataContainer.innerHTML = output;
  loadQuestions(pollId);
}
async function loadQuestions(pollId) {
  try {
    const apiEndpoint = `http://localhost:8080/api/platform/polls/questions/question-list/${pollId}`;
    const response = await fetch(apiEndpoint);
    if (!response.ok) {
      throw new Error(`Ошибка загрузки вопросов: ${response.status}`);
    }
    const questions = await response.json();
    displayQuestions(questions, pollId);
  } catch (error) {
    console.error('Ошибка при загрузке вопросов:', error);
    displayError(`Ошибка при загрузке вопросов: ${error.message}`);
    document.getElementById(`questions-container-${pollId}`).innerHTML = `<p>Не удалось получить данные.</p>`;
  }
}
async function loadOptions(questionId) {
  console.log('loadOptions called, questionId:', questionId);
  try {
    const apiEndpoint = `http://localhost:8080/api/platform/polls/questions/options/options-list/${questionId}`;
    const response = await fetch(apiEndpoint);
    console.log('loadOptions response:', response);
    if (!response.ok) {
      throw new Error(`Ошибка загрузки вариантов ответа: ${response.status}`);
    }
    let options = await response.json();
     if (!options || !Array.isArray(options)) {
       options = [{ id: null, text: '1', votes: 0 }];
     }
    console.log('loadOptions, options:', options);
    displayOptions(options, questionId);
  } catch (error) {
    console.error('Ошибка при загрузке вариантов ответа:', error);
    displayError(`Ошибка при загрузке вариантов ответа: ${error.message}`);
    const optionsContainer = document.getElementById(`options-container-${questionId}`);
    if (optionsContainer) {
      optionsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;
    }
  }
}
function displayQuestions(questions, pollId) {
  console.log('displayQuestions called', questions, pollId);
  const questionsContainer = document.getElementById(`questions-container-${pollId}`);
  if (!questions) {
    questionsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;
    return;
  }
  let output = "";
  questions.forEach((question) => {
    output += `<div class="question" data-id="${question.id}" data-poll-id="${pollId}">`;
    output += `<p data-question-id="${question.id}" >Вопрос: ${question.text}</p>`;
    output += `<div id="options-container-${question.id}"></div>`;
      output += `</div>`;
  });
  questionsContainer.innerHTML = output;
  questionsContainer.querySelectorAll('.question p').forEach((questionElement) => {
    questionElement.addEventListener('click', () => {
      loadOptions(questionElement.dataset.questionId);
    });
  });
}
function displayOptions(options, questionId) {
    console.log('displayOptions called, options:', options, 'questionId:', questionId);
  const optionsContainer = document.getElementById(`options-container-${questionId}`);
   if (!optionsContainer) {
       return;
   }
  if (!options || options.length === 0) {
     console.log("container not found or empty options");
    optionsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;
     return;
  }
  let output = "";
  options.forEach((option) => {
     output += `<div class="option" data-id="${option.id}" data-question-id="${questionId}">`;
      output += `<p onclick="selectOption(this)">  Ответ: ${option.text} Votes: ${option.votes}</p>`;
     output += `</div>`;
 });
  optionsContainer.innerHTML = output;
    optionsContainer.querySelectorAll('.option p').forEach((optionElement) => {
      optionElement.addEventListener('click', () => {
          selectOption(optionElement);
      });
    });
}
function selectOption(element) {
  console.log('selectOption called', element);
  console.log('selectOption parent', element.parentElement);
  const optionId = element.parentElement.dataset.id;
  const questionId = element.parentElement.dataset.questionId;
   const optionsContainer = element.parentElement.parentElement;
    const selectedElement = optionsContainer.querySelector('.option.selected');
   if(selectedElement) {
        selectedElement.classList.remove('selected')
    }
  if (selectedOptions[questionId]) {
    selectedOptions[questionId] = optionId;
   } else {
      selectedOptions[questionId] = optionId;
    }
  element.parentElement.classList.add('selected');
}
async function submitAnswers(element) {
  const pollId = element.dataset.pollId;
  if (Object.keys(selectedOptions).length === 0) {
    displayError("Выберите варианты ответов для всех вопросов.");
    return;
  }
  try {
      for (const questionId in selectedOptions) {
          const optionId = selectedOptions[questionId];
        await addVote(optionId);
    }
      displaySuccess("Спасибо за прохождение опроса!");
       const questions = document.querySelectorAll('.question');
        for (let question of questions) {
              const questionId = question.dataset.id;
            await loadOptions(questionId);
        }
     selectedOptions = {}; // Очищаем выбранные варианты после отправки
 } catch (error) {
    displayError('Ошибка отправки ответов:' + error);
  }
}
document.addEventListener('DOMContentLoaded', () => {
  const pollsContainer = document.getElementById('polls-container');
  const pollDataContainer = document.getElementById('poll-data');
  messageContainer = document.getElementById('message-container');
  errorContainer = document.getElementById('error-container');
  // Функция для загрузки списка опросов при загрузке страницы
  async function loadPolls() {
    try {
      const apiEndpoint = "http://localhost:8080/api/platform/polls/poll-list";
      const response = await fetch(apiEndpoint);
      if (!response.ok) {
        throw new Error(`Ошибка загрузки списка опросов ${response.status}`);
      }
      const polls = await response.json();
      displayPolls(polls);
    } catch (error) {
      console.error('Ошибка при загрузке списка опросов:', error);
      displayError(`Ошибка при загрузке списка опросов: ${error.message}`);
      pollsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;
    }
  }
  function displayPolls(polls) {
    if (!polls) {
      pollsContainer.innerHTML = `<p>Не удалось получить данные.</p>`;
      return;
    }
    let output = "";
    polls.forEach((poll) => {
      output += `<div class="poll" data-id="${poll.id}" onclick="displayPoll(this)">
                <p>ID: ${poll.id}</p>
               <p>Title: ${poll.title}</p>
          </div>
        `;
    });
    pollsContainer.innerHTML = output;
  }
  loadPolls();
});