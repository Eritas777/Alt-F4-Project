async function getStudentsData(api_url) {
  /**
   * Отправляет GET-запрос по указанному URL и обрабатывает JSON-ответ
   * со списком студентов.
   *
   * @param {string} api_url URL API для получения списка студентов.
   * @returns {Promise<Array|null>} Список объектов с данными студента или null в случае ошибки.
   */
try {
  const response = await fetch(api_url);
  if (!response.ok) {
    throw new Error(`HTTP error! Status: ${response.status}`);
  }
  const studentsData = await response.json();

  if (Array.isArray(studentsData)) {
    return studentsData;
  } else {
    throw new Error("Ошибка: JSON-ответ не является списком.");
     // throw Error чтобы перешло в catch
  }
} catch (error) {
      console.error(`Ошибка при выполнении запроса: ${error}`);
  displayError(error.message);
  return null; // Возвращаем null чтобы не сломать основную логику
}
}

function displayError(message) {
  const errorContainer = document.getElementById("error-container");
  errorContainer.textContent = message;
}

function displayStudents(students) {
   const studentContainer = document.getElementById("student-container");
  if(!students){
      studentContainer.innerHTML = `<p>Не удалось получить данные студентов.</p>`;
       return
  }
  let output = "";
  students.forEach((student) => {
     output += `
          <div class="student">
            <p>System ID: ${student.systemId || 'N/A'}</p>
            <p>Email: ${student.email || 'N/A'}</p>
            <p>Имя: ${student.firstName || 'N/A'}</p>
            <p>Фамилия: ${student.lastName || 'N/A'}</p>
            <p>Дата рождения: ${student.dateOfBirth || 'N/A'}</p>
            <p>Пол: ${student.gender || 'N/A'}</p>
            <p>Имя матери: ${student.motherName || 'N/A'}</p>
            <p>Телефон матери: ${student.motherPhoneNumber || 'N/A'}</p>
             <p>Имя отца: ${student.fatherName || 'N/A'}</p>
            <p>Телефон отца: ${student.fatherPhoneNumber || 'N/A'}</p>
             <p>Адрес проживания: ${student.residentialAdress || 'N/A'}</p>
            <p>Номер телефона: ${student.phoneNumber || 'N/A'}</p>
             <p>ID учебного заведения: ${student.institutionID || 'N/A'}</p>
             <p>Роль: ${student.role || 'N/A'}</p>
            <p>Учебная группа: ${student.studyGroup || 'N/A'}</p>
           <p>Учебная дисциплина: ${student.academicDiscipline || 'N/A'}</p>
           </div>
     `;
  });
   studentContainer.innerHTML = output;
}


async function main() {
// Используем предоставленный URL
const apiEndpoint = "http://localhost:8080/api/platform/student-list";

const students = await getStudentsData(apiEndpoint);

displayStudents(students);
}

main();