const tabs = document.getElementById('tabs');
const logout = document.getElementById('logout');
const anal = document.getElementById('anal');

function toggleTabs(isAuthenticated) {
  if (isAuthenticated) {
    logout.style.display = 'block';
    document.getElementById('tab2').style.display = "block";
    document.getElementById("anal").style.display = "block";
  } else {
    logout.style.display = 'none';
    document.getElementById('tab2').style.display = "none";
    document.getElementById('anal').style.display = "none";
  }
}

// Предположим, что функция checkAuth проверяет, авторизирован ли пользователь
const isAuthenticated = checkAuth(); 
toggleTabs(isAuthenticated);