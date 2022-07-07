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
const administratorCreateManager = {template:'<administratorCreateManager-page></administratorCreateManager-page>'}
const administratorCreateTrainer = {template:'<administratorCreateTrainer-page></administratorCreateTrainer-page>'}
const EditTrainerProfilePage = { template: '<editTrainerProfile-page></editTrainerProfile-page>' }
const ListCustomers = { template: '<listCustomers-page></listCustomers-page>' }
const administratorListManager = { template: '<listManager-page></listManager-page>' }
const administratorListTrainer = { template: '<listTrainer-page></listTrainer-page>' }
const administratorListAdministrator = { template: '<listAdministrator-page></listAdministrator-page>' }
const managerContent = { template: '<managerContent-page></managerContent-page>' }
const AddSportObjectPage = {template: '<addSportObject-page></addSportObject-page>'}
const ManagerSportObjectPage = {template: '<managerSportObject-page></managerSportObject-page>'}
const ManagerTrainersPage = { template: '<managerViewTrainers-page></managerViewTrainers-page>'}
const ManagerCustomersPage = { template: '<managerViewCustomers-page></managerViewCustomers-page>'}
const ManagerAddTrainingPage = { template: '<managerAddTraining-page></managerAddTraining-page>'}
const ManagerContentViewPage = { template: '<managerContentView-page></managerContentView-page>'}
const ManagerTrainingViewPage = { template: '<managerTrainingView-page></managerTrainingView-page>'}
const CustomerTrainingViewPage = { template: '<customerTrainingView-page></customerTrainingView-page>'}
const TrainerTrainingViewPage = { template: '<trainerTrainingView-page></trainerTrainingView-page>'}
const CustomerScheduleTrainingPage = { template: '<customerScheduleTraining-page></customerScheduleTraining-page>'}
const TrainerGroupTrainingsViewPage = { template: '<trainerGroupTrainingsView-page></trainerGroupTrainingsView-page>'}
const TrainerPersonalTrainingsViewPage = { template: '<trainerPersonalTrainingsView-page></trainerPersonalTrainingsView-page>'}
const customerCreateMemberShip = { template: '<customerMemberShip-page></customerMemberShip-page>'}
const customerActiveMembership =  { template: '<customerActive-page></customerActive-page>'}
const ManagerWelcomeCustomer =  { template: '<managerWelcomeCustomer-page></managerWelcomeCustomer-page>'}
const CustomerVisitedObjectsPage =  { template: '<customerVisitedObjects-page></customerVisitedObjects-page>'}


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
	    { path: '/emp', component: EditManagerProfilePage },
		{ path: '/acm', component: administratorCreateManager },
		{ path: '/act', component: administratorCreateTrainer },
	    { path: '/etp', component: EditTrainerProfilePage },
		{ path: '/lic', component: ListCustomers },
		{ path: '/lim', component: administratorListManager },
		{ path: '/lit', component: administratorListTrainer },
		{ path: '/lia', component: administratorListAdministrator },
		{ path: '/mcp', component: managerContent },
		{ path: '/aso', component: AddSportObjectPage },
		{ path: '/mcp', component: managerContent },
		{ path: '/mso', component: ManagerSportObjectPage },
		{ path: '/mvt', component: ManagerTrainersPage },
		{ path: '/mcpp', component: ManagerCustomersPage },
		{ path: '/mat', component: ManagerAddTrainingPage },
		{ path: '/mcvp', component: ManagerContentViewPage },
		{ path: '/mtvp', component: ManagerTrainingViewPage },
		{ path: '/ctvp', component: CustomerTrainingViewPage },
		{ path: '/ttvp', component: TrainerTrainingViewPage },
		{ path: '/cst', component: CustomerScheduleTrainingPage },
		{ path: '/tgtvp', component: TrainerGroupTrainingsViewPage },
		{ path: '/tptvp', component: TrainerPersonalTrainingsViewPage },
		{ path: '/ccm', component: customerCreateMemberShip },
		{ path: '/cam', component: customerActiveMembership },
		{ path: '/mwc', component: ManagerWelcomeCustomer },
		{ path: '/cvo', component: CustomerVisitedObjectsPage }

	  ]
});

var app = new Vue({
	router,
	el: '#startPage'
});

