<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {background-color:#f5f5f5;}
    </style>
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/style.css"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
<c:choose>
    <c:when test="${user.roles.equals('ROLE_ADMIN')}">
            <li><a href="/admin/mainpanel">Panel główny</a></li>
    </c:when>
    <c:otherwise>
        <li><a href="/user/mainpanel">Panel główny</a></li>
    </c:otherwise>
</c:choose>
            <li class="highlighted"><a href="/logout">Wyloguj</a></li>
        </ul>

        <ul>
            <li><a href="/">Start</a></li>
            <li><a href="#">O co chodzi?</a></li>
            <li><a href="#">O nas</a></li>
            <li><a href="/institution/all">Fundacje i organizacje</a></li>
            <li><a href="#">Kontakt</a></li>
        </ul>
    </nav>
</header>

<div class="different">
    <h2> Zaufane instytucje</h2>

    <table class="tt">
        <tr>
            <th>Nazwa</th>
            <th>Opis</th>
            <th>Miasto</th>
            <th>Adres</th>
            <th>Edytuj</th>
            <th>Usuń</th>

        </tr>
        <c:forEach var="institution" items="${institutions}">
            <tr>

                <td>${institution.name}</td>
                <td>${institution.description}</td>
                <td>${institution.location}</td>
                <td>${institution.address}</td>

                <c:choose>
                <c:when test="${user.roles.equals('ROLE_ADMIN')}">
                <td><a href="/institution/edit/${institution.id}"><img class="pencil" src="/resources/images/pencil.svg"/></a></td>
                <td><a href="/institution/remove/${institution.id}"><img class="pencil" src="/resources/images/recycle-bin.svg"/></a></td>
                </c:when>
                </c:choose>

            </tr>
        </c:forEach>

    </table>
</div>
<c:choose>
    <c:when test="${user.roles.equals('ROLE_ADMIN')}">
<div class="button">

    <a href="/institution/add"><button class="btn">Dodaj instytucję</button></a>
</div>
    </c:when>
</c:choose>
<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form>
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

</body>
</html>
