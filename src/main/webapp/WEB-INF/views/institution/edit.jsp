<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/style.css"/>
</head>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/admin/mainpanel">Panel główny</a></li>
            <li class="highlighted"><a href="/logout">Wyloguj</a></li>
        </ul>

        <ul>
            <li><a href="/">Start</a></li>
            <li><a href="#">O co chodzi?</a></li>
            <li><a href="/institution/all">O nas</a></li>
            <li><a href="#">Fundacje i organizacje</a></li>
            <li><a href="#">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Edytuj instytucję</h2>
    <form:form method="post" modelAttribute="institution" action="/institution/edit">
        <form:hidden path="id"/>
        <div class="form-group">
            <form:input path="name" placeholder="nazwa"/>
            <form:errors path="name" cssClass="error" element="div"/>
        </div>
        <div class="form-group">
            <form:input path="location" placeholder="miasto"/>
            <form:errors path="location" cssClass="error" element="div"/>
        </div>
        <div class="form-group">
            <form:textarea cols="20" rows="5" path="address" placeholder="adres"/>
            <form:errors path="address" cssClass="error" element="div"/>
        </div>
        <div class="form-group">
            <label> Komu pomaga </label>
            <form:select path="description">
                <form:option value="0" label="--Wybierz z listy--"/>
                <form:options items="${helps}"/>
            </form:select>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zmień</button>
        </div>
    </form:form>
</section>

<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form>
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
</body>
</html>

