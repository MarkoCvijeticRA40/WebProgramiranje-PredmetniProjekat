Vue.component("trainerGroupTrainingsView-page", {
	data: function () {
		    return {
		      trainer : null,
		      trainings: null
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/tsp">Profile</a></li>
  <li><a class="active" href="#/ttvp">Trainings</a></li>
  <li><a class="active" href="#/tgtvp">Group Trainings</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="width:100%" border="1px">
 <tr>
	<th>Name</th>
    <th>Type</th>
    <th>Sport Object</th>
    <th>Date</th>
    <th>Time</th>
  </tr>
  <tr v-for="t in trainings">
  <td>{{t.training.name}}</td>
  <td>{{t.training.type}}</td>
  <td>{{t.training.sportObject.name}}</td>
  <td>{{t.applicationDate.dayOfMonth}}.{{t.applicationDate.monthValue}}.{{t.applicationDate.year}}</td>
  <td>{{t.applicationDate.hour}}:00</td>
  </tr>
</table>

</div>
`
	, 
	methods : {
		 
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
			 .post('rest/trainers/getGroupTrainings', { id: this.trainer.id })
			 .then(response => this.trainings = response.data);
		  });
    },
});