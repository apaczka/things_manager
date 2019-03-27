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

        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/style.css"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/logout">Wyloguj</a></li>
        </ul>

        <ul>
            <li><a href="/">Start</a></li>
<c:choose>
    <c:when test="${user.roles.equals('ROLE_ADMIN')}">
            <li><a href="/admin/mainpanel">Panel główny</a></li>
    </c:when>
    <c:otherwise>
        <li><a href="/user/mainpanel">Panel główny</a></li>

    </c:otherwise>
</c:choose>
            <li><a href="#">O nas</a></li>
            <li><a href="/institution/all">Fundacje i organizacje</a></li>
            <li><a href="#">Kontakt</a></li>
        </ul>
    </nav>
</header>

<div class="different">
    <h2> Lista Twoich darów</h2>
    <table class="tt">
        <tr>
            <th>Instytucja</th>
            <th>Data przekazania</th>
            <th>Data utworzenia wpisu</th>
            <th>Liczba przekazanych worków</th>
            <th>Dar przekazany</th>
            <th>Edytuj</th>
            <th>Usuń</th>
            <th>Oznacz jako przekazany</th>
        </tr>
        <c:forEach var="donation" items="${donations}">
            <tr>

                <td>${donation.institution.name}</td>
                <td>${donation.date}</td>
                <td>${donation.created}</td>
                <td>${donation.numberOfBags}</td>
                <td>${donation.given}</td>
               <c:choose>
                <c:when test="${donation.given==false}">

                <td><a href="/donation/page1/edt/${donation.id}"><img class="pencil" src="/resources/images/pencil.svg"/></a>
                </td>
                </c:when>
                    <c:otherwise>
                        <td></td>
                    </c:otherwise>
                </c:choose>

                <td><a href="/donation/remove/${donation.id}"><img class="pencil"
                                                                   src="/resources/images/recycle-bin.svg"/></a></td>
                <c:choose>
                    <c:when test="${donation.given==false}">
                        <td><a href="/donation/mark/${donation.id}"><img class="pencil"
                                                                         src="/resources/images/giving-love.svg"/></a>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td></td>
                    </c:otherwise>
                </c:choose>

            </tr>
        </c:forEach>
    </table>
</div>


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
