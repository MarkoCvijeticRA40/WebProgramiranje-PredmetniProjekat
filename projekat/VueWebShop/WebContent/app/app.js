const StartPage = { template: '<start-page></start-page>' }
const RegisterPage = { template: '<register-page></register-page>' }


const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: StartPage },
	    { path: '/ru', component: RegisterPage }
	  ]
});

var app = new Vue({
	router,
	el: '#startPage'
});

