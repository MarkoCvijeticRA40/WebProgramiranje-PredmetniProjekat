const StartPage = { template: '<start-page></start-page>' }
const RegisterPage = { template: '<register-page></register-page>' }
const LoginPage = { template: '<login-page></login-page>' }
const AdministratorStartPage = { template: '<administratorStart-page></administratorStart-page>' }
const CustomerStartPage = { template: '<customerStart-page></customerStart-page>' }
const ManagerStartPage = { template: '<managerStart-page></managerStart-page>' }
const TrainerStartPage = { template: '<trainerStart-page></trainerStart-page>' }



const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: StartPage },
	    { path: '/ru', component: RegisterPage },
	    { path: '/lu', component: LoginPage },
	    { path: '/asp', component: AdministratorStartPage },
	    { path: '/csp', component: CustomerStartPage },
	    { path: '/msp', component: ManagerStartPage },
	    { path: '/tsp', component: TrainerStartPage }
	  ]
});

var app = new Vue({
	router,
	el: '#startPage'
});

