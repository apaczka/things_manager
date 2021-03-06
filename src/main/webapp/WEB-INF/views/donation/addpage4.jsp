<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/style.css"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj ${user.firstName}
                <ul class="dropdown">
                    <li><a href="/edit/${user.id}">Edytuj profil</a></li>
                    <li><a href="/donation/all">Moje dary</a></li>
                    <li><a href="/logout">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="#" class="btn btn--without-border active">Start</a></li>
            <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#" class="btn btn--without-border">O nas</a></li>
            <li>
                <a href="/institution/all" class="btn btn--without-border"
                >Fundacje i organizacje</a
                >
            </li>
            <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Jeśli wiesz komu chcesz pomóc, możesz wpisać nazwę tej organizacji w
                wyszukiwarce. Możesz też filtrować organizacje po ich lokalizacji
                bądź celu ich pomocy.
            </p>
            <p data-step="4">
                Na podstawie Twoich kryteriów oraz rzeczy, które masz do oddania
                wybraliśmy organizacje, którym możesz pomóc. Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="5">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>4</span>/5</div>
        <%--<!-- STEP 4 -->--%>
        <form:form method="post" modelAttribute="donation" action="/donation/page4">
        <div data-step="4" class="active">

            <h3>Wybierz organizacje, której chcesz pomóc:</h3>

            <div class="form-group form-group--checkbox">
                <label>
                    <input type="radio" name="organization" value="old" />
                    <%--<span class="checkbox radio"></span>--%>
                    <span class="description">
    <div class="title">

        <%--<form:radiobuttons class="checkbox radio" path="institution" items="${institutions}" itemLabel="name" itemValue="id"/>--%>
<form:select path="institution">
    <form:option value="0" label="wybierz instytucję"/>
    <form:options items="${institutions}" itemLabel="name" itemValue="id"/>
</form:select>
    </div>
                    </span>
                </label>
            </div>


            <div class="form-group form-group--buttons">
                <a href="/donation/page2/again"><button type="button" class="btn prev-step">Wstecz</button></a>
                <button type="submit" class="btn next-step">Dalej</button>
            </div>
        </div>
        </form:form>
    </div>
        <footer>
            <div class="contact">
                <h2>Skontaktuj się z nami</h2>
                <h3>Formularz kontaktowy</h3>
                <form class="form--contact">
                    <div class="form-group form-group--50">
                        <input type="text" name="name" placeholder="Imię" />
                    </div>
                    <div class="form-group form-group--50">
                        <input type="text" name="surname" placeholder="Nazwisko" />
                    </div>

                    <div class="form-group">
    <textarea
            name="message"
            placeholder="Wiadomość"
            rows="1"
    ></textarea>
                    </div>

                    <button class="btn" type="submit">Wyślij</button>
                </form>
            </div>
            <div class="bottom-line">
                <span class="bottom-line--copy">Copyright &copy; 2018</span>
                <div class="bottom-line--icons">
                    <a href="#" class="btn btn--small"
                    ><img src="/resources/images/icon-facebook.svg"
                    /></a>
                    <a href="#" class="btn btn--small"
                    ><img src="/resources/images/icon-instagram.svg"
                    /></a>
                </div>
            </div>
        </footer>

        <%--<script src="/resources/js/app.js"></script>--%>
</body>
</html>
