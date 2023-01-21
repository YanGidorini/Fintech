<header class="header container-fluid">
    <div class="container-xxl">
        <div class="row align-items-center">
            <div class="col header__logo d-sm-block">
                <picture>
                    <source srcset="${pageContext.request.contextPath}/resources/imgs/FintechLogo.png" media="(min-width: 1024px)">
                    <img src="${pageContext.request.contextPath}/resources/imgs/FintechLogo.png" alt="Logo Fintech">
                </picture>
            </div>

            <nav class="col-auto header__nav d-sm-flex justify-content-center">
                <a href="${pageContext.request.contextPath}/dashboard" title="Ir para a Dashboard">
                    <svg class="header__item home" xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="24px"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M12 5.69l5 4.5V18h-2v-6H9v6H7v-7.81l5-4.5M12 3L2 12h3v8h6v-6h2v6h6v-8h3L12 3z"/></svg>
                </a>
                <a href="${pageContext.request.contextPath}/receitas" title="Minhas receitas">
                    <svg class="header__item revenue" width="37" height="26" viewBox="0 0 37 26" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <g clip-path="url(#clip0_767_380)">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M35.2248 9.5188L36.4747 0.9729L28.1687 3.34048L30.546 5.42206L20.0487 17.2223L13.6017 10.7753L1.51352 22.8796L3.93115 25.2972L13.6017 15.6106L20.0487 22.0576L32.9602 7.53591L35.2248 9.5188Z" />
                    </g>
                    <defs>
                    <clipPath id="clip0_767_380">
                    <rect width="35.6757" height="25.1351" transform="translate(0.972977 0.432373)"/>
                    </clipPath>
                    </defs>
                    </svg>
                </a>
                <a href="${pageContext.request.contextPath}/despesas" title="Minhas despesas">
                    <svg class="header__item spent" width="37" height="26" viewBox="0 0 37 26" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <g clip-path="url(#clip0_767_376)">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M34.279 16.7516L35.5288 25.2975L27.2229 22.9299L29.6002 20.8483L19.1027 9.04795L12.6557 15.495L0.567596 3.3907L2.98522 0.973076L12.6557 10.6597L19.1027 4.2127L32.0144 18.7345L34.279 16.7516Z" />
                    </g>
                    <defs>
                    <clipPath id="clip0_767_376">
                    <rect width="35.6757" height="25.1351" transform="matrix(1 0 0 -1 0.567566 25.5676)"/>
                    </clipPath>
                    </defs>
                    </svg>
                </a>
                <a href="${pageContext.request.contextPath}/investimentos" title="Meus investimentos">
                    <svg class="header__item investment" xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="24px"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M5 9.2h3V19H5V9.2zM10.6 5h2.8v14h-2.8V5zm5.6 8H19v6h-2.8v-6z"/></svg>
                </a>
           		<a href="${pageContext.request.contextPath}/objetivos" title="Meus objetivos">
                       <svg class="header__item target" width="37" height="37" viewBox="0 0 37 37" fill="none" xmlns="http://www.w3.org/2000/svg">
                       <circle cx="18.9054" cy="18.5" r="16.5" stroke="white" stroke-width="3"/>
                       <circle cx="18.9054" cy="18.5" r="8.25" stroke="white" stroke-width="3"/>
                       <circle cx="18.9054" cy="18.5" r="2.75" fill="white"/>
                       </svg>
            	</a>
            </nav>
        
            <div class="col d-flex justify-content-sm-end">
                <a href="${pageContext.request.contextPath}/usuario?task=conta" title="Minha conta">
                    <div class="header__account d-flex align-items-center">
                        <span class="header__account-name">${sessionScope.user.nmUsuario}</span>
                        <div class="header__account-photo">
                            <img src="${pageContext.request.contextPath}/resources/imgs/perfil.png" alt="foto de perfil">
                        </div>
                    </div>
                </a>
            </div>
	
			<div class="col-auto">
				<a href="${pageContext.request.contextPath}/usuario?task=logout">
					<svg class="header__item" viewBox="0 0 24 24">
    					<path d="M16,17V14H9V10H16V7L21,12L16,17M14,2A2,2 0 0,1 16,4V6H14V4H5V20H14V18H16V20A2,2 0 0,1 14,22H5A2,2 0 0,1 3,20V4A2,2 0 0,1 5,2H14Z" />
					</svg>
            	</a>
            </div>  
        </div>
    </div>
</header>