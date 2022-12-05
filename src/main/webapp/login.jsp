<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="./parts/head.jsp"/>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>Entrar</title>
</head>

<body>

	<main class="container-sm main-container d-flex flex-column text-center">
        <picture class="header__logo--login mb-4">
            <source srcset="./resources/imgs/FintechLogoDark.png" media="(min-width: 1024px)">
            <img src="./resources/imgs/FintechLogoDark.png" alt="Logo Fintech">
        </picture>

        <div class="row justify-content-center px-2 p-sm-0">
            <div class="col-sm-8 col-lg-4">
            
            	<c:if test="${not empty erro}">
					<div class="alert alert-danger">${erro}</div>
				</c:if>
            
                <p class="title text-start">Entrar</p>
                <div class="card-default p-4 mb-3 position-relative">
                    <form class="form text-start" action="usuario" method="POST">
                    	<input type="hidden" value="login" name="task">
                        <div class="form__input">
                            <label for="email" class="form__label">E-mail<span class="mandatory">*</span></label>
                            <input id="email" type="email" name="email" required>
                        </div>
                        
                        <div class="form__input">
                            <label for="pswd" class="form__label">Senha<span class="mandatory">*</span></label>
                            <input id="pswd" type="password" name="pswd" placeholder="6 ou mais caracteres" required>
                            <a href="" class="forgot-pswd">Esqueceu a senha?</a>
                        </div>
                        
                        <button class="form__btn" type="submit">Entrar</button>
                    </form>
                </div>

            </div>
            <p class="">Não possui cadastro? <a href="usuario?task=cadastrar">Cadastre-se</a></p>
            <p class="mb-0">raj@gmail.com</p>
            <p class="mb-0">123456</p>
        </div>   
	</main>

	<jsp:include page="./parts/footerLogin.jsp"/>
</body>
</html>