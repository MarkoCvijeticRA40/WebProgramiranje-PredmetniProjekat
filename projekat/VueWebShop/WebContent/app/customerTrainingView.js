Vue.component("customerTrainingView-page", {
	data: function () {
		    return {
		      customer: null,
		      trainings: null, 
			  searchText : "",
		    }
	},
	template: ` 
<div>
<ul>
 <li><a class="active" href="#/csp">Profile</a></li>
  <li><a class="active" href="#/ctvp">Trainings</a></li>
  <li><a class="active" href="#/cst">Schedule training</a></li>
  <li><a class="active" href="#/ccm">Buy Membership</a></li>
  <li><a class="active" href="#/cam">Active Membership</a></li>
  <li><a class="active" href="#/cvo">Visited Objects</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<table>
<td><button v-on:click="SportObjectNameASC()">SortSportObjectByACS</button></td>
<td><button v-on:click="SportObjectNameDESC()">SortSportObjectByACS</button></td>
</table>
<br>
<table style="width:100%" border="1px">
 	<tr>
		<th>Name</th>
    	<th>Sport Object</th>
		<th>Date</th>
  	</tr>
  	<tr v-for="t in trainings">
  		<td>{{t.training.name}}</td>
  		<td>{{t.training.sportObject.name}}</td>
  		<td>{{t.applicationDate.dayOfMonth}}.{{t.applicationDate.monthValue}}.{{t.applicationDate.year}}</td>
  	</tr>
	</table>	

</div>
`
	, 
	methods : {
		SportObjectNameASC : function() { 
		axios
			.post('rest/history/getTrainingsACS', { id: this.customer.id })
			.then(response => this.trainings = response.data); 
		},
		SportObjectNameDESC : function() { 
		axios
			.post('rest/history/getTrainingsDESC', { id: this.customer.id })
			.then(response => this.trainings = response.data); 
		},
	},
	mounted () {
         axios
         .get('rest/users/activeCustomer')
         .then(response => { 
			this.customer = response.data;
			axios
			.post('rest/history/getTrainings', { id: this.customer.id })
			.then(response => this.trainings = response.data); 
		});
    },
    filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	}
});