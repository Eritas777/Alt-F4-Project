const headerHtml = `
    <div id="header-main">
        <nav id="navbar"> 
            <div class="navbar-left" id="logo-image">
                <a href="home.html">
                    <img src="logo.png" alt="Логотип"/>
                </a>
            </div>
            <ul id="nav-list">
                <li><a class="nav-links auth-links" href="home.html">ГЛАВНАЯ</a></li>
                <li><a class="nav-links auth-links" href="surveys.html">ОПРОСЫ</a></li>
                <li><a class="nav-links auth-links" href="spisok.html">УЧАЩИЕСЯ</a></li>
                <li><a class="nav-links auth-links" id="my-class-link" href="meineclasse.html">МОЙ КЛАСС</a></li>
                <li><a class="nav-links auth-links" id="professors-link" href="#">ПРЕПОДАВАТЕЛИ</a></li>
            </ul>
            <div class="navbar-right" id="profile-icon">
                <a href="cabinet.html">
                    <svg width="50" height="50" viewBox="0 0 24 24" style="stroke: var(--icon-stroke-dark); fill: var(--icon-bg-dark)" xmlns="http://www.w3.org/2000/svg">
                        <circle cx="12" cy="12" r="9" stroke-width="2" />
                        <circle cx="12" cy="10" r="3" stroke-width="2" stroke-linecap="round"/>
                        <path d="M17.7805 18.8264C17.9076 18.7566 17.9678 18.6055 17.914 18.4708C17.5284 17.5045 16.7856 16.6534 15.7814 16.0332C14.6966 15.3632 13.3674 15 12 15C10.6326 15 9.30341 15.3632 8.21858 16.0332C7.21444 16.6534 6.4716 17.5045 6.08598 18.4708C6.03223 18.6055 6.09236 18.7566 6.21948 18.8264C9.81971 20.803 14.1803 20.803 17.7805 18.8264Z" style="fill: var(--icon-stroke-dark)"/>
                    </svg>
                </a>
            </div>
            <a class="navbar-right nav-links" id="authorization-link" href="register.html">ВХОД | РЕГИСТРАЦИЯ</a>
        </nav>
    </div>
`

let header = document.createElement('header');
header.innerHTML = headerHtml;
let content = document.getElementById('content');
content.prepend(header);
