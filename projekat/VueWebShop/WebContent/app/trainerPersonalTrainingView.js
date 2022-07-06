Vue.component("trainerPersonalTrainingsView-page", {
	data: function () {
		    return {
		      trainer : null,
		      trainings: null,
		      cancel: "false"
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/tsp">Profile</a></li>
  <li><a class="active" href="#/ttvp">Trainings</a></li>
  <li><a class="active" href="#/tgtvp">Group Trainings</a></li>
  <li><a class="active" href="#/tptvp">Personal Trainings</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="width:100%" border="1px">
 <tr>
	<th>Name</th>
    <th>Type</th>
    <th>Sport Object</th>
    <th>Customer</th>
    <th>Date</th>
    <th>Time</th>
  </tr>
  <tr v-for="t in trainings">
  <td>{{t.training.name}}</td>
  <td>{{t.training.type}}</td>
  <td>{{t.training.sportObject.name}}</td>
  <td>{{t.customer.name}} {{t.customer.lastName}}</td>
  <td>{{t.applicationDate.dayOfMonth}}.{{t.applicationDate.monthValue}}.{{t.applicationDate.year}}</td>
  <td>{{t.applicationDate.hour}}:00</td>
  <td v-if="cancel === 'true'"><button>Cancel</button></td>
  </tr>
</table>

</div>
`
	, 
	methods : {
		 canCancel: function(training) {
		 	axios
		 	.post('rest/scheduledTrainings/canCancel', { id: training.id })
		 	.then(response => this.cancel = response.data);
		 } 
	},
	filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	},
	mounted () {
         axios
         .get('rest/users/activeTrainer')
         .then(response => {
			 this.trainer = response.data;
			 axios 
			 .post('rest/trainers/getPersonalTrainings', { id: this.trainer.id })
			 .then(response => this.trainings = response.data);
		  });
    },
});