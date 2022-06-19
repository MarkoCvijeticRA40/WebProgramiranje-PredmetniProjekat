const StartPage = { template: '<start-page></start-page>' }
const RegisterPage = { template: '<register-page></register-page>' }
const LoginPage = { template: '<login-page></login-page>' }
const AdministratorStartPage = { template: '<administratorStart-page></administratorStart-page>' }
const CustomerStartPage = { template: '<customerStart-page></customerStart-page>' }
const ManagerStartPage = { template: '<managerStart-page></managerStart-page>' }
const TrainerStartPage = { template: '<trainerStart-page></trainerStart-page>' }
const EditCustomerProfilePage = { template: '<editCustomerProfile-page></editCustomerProfile-page>' }
const EditAdministratorProfilePage = { template: '<editAdministratorProfile-page></editAdministratorProfile-page>' }
const EditManagerProfilePage = { template: '<editManagerProfile-page></editManagerProfile-page>' }



const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: StartPage },
	    { path: '/ru', component: RegisterPage },
	    { path: '/lu', component: LoginPage },
	    { path: '/asp', component: AdministratorStartPage },
	    { path: '/csp', component: CustomerStartPage },
	    { path: '/msp', component: ManagerStartPage },
	    { path: '/tsp', component: TrainerStartPage },
	    { path: '/ecp', component: EditCustomerProfilePage },
	    { path: '/eap', component: EditAdministratorProfilePage },
	    { path: '/emp', component: EditManagerProfilePage }
	  ]
});

var app = new Vue({
	router,
	el: '#startPage'
});

