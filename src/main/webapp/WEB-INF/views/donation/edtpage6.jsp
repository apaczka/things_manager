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


<div class="mydiv">
    <!-- STEP 6 -->


    <div class="moja">


        <h3 style="font-size: 25px">Podsumowanie Twojej darowizny</h3>
        <div class="form-section">

            <h4>Oddajesz:</h4>
            <ul>
                <li>
                    <span class="bag"></span>
                    <span class="summary--text"
                    >${edt2.numberOfBags} worki ${edt1.category}</span
                    >
                </li>

                <li>
                    <span class="icon icon-hand"></span>
                    <span class="summary--text"
                    >Dla ${edt4.name} w ${edt4.location}</span
                    >
                </li>
            </ul>
        </div>


        <h4>Adres odbioru:</h4>
        <ul>
            <li>${edt5.street}</li>
            <li>${edt5.city}</li>
            <li>${edt5.postalCode}</li>
            <li>${edt5.phoneNumber}</li>
        </ul>


        <div class="form-section--column">
            <h4>Termin odbioru:</h4>
            <ul>
                <li>${edt5.date}</li>
                <li>${edt5.time}</li>
                <li>Brak uwag</li>
            </ul>
        </div>
    </div>


    <div class="form-group form-group--buttons">
        <a href="/donation/page5/edt/edt">
            <button type="button" class="btn prev-step">Wstecz</button>
        </a>
        <a href="/donation/page6/edt">
            <button class="btn">Potwierdzam</button>
        </a>
    </div>
</div>


<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form class="form--contact">
            <div class="form-group form-group--50">
                <input type="text" name="name" placeholder="Imię"/>
            </div>
            <div class="form-group form-group--50">
                <input type="text" name="surname" placeholder="Nazwisko"/>
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
