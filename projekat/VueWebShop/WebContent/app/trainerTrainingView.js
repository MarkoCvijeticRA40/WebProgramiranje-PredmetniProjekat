Vue.component("trainerTrainingView-page", {
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
  <li><a class="active" href="#/tptvp">Personal Trainings</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="width:100%" border="1px">
 <tr>
	<th>Name</th>
    <th>Type</th>
	<th style="width:260px">Image</th>
    <th>Description</th>
    <th>Duration</th>
  </tr>
  <tr v-for="training in trainings">
  <td>{{training.name}}</td>
  <td>{{training.type}}</td>
  <td><img v-bind:src="training.image" width="260px" Height="160px" alt="Image is not posted."></td>
  <td>{{training.description}}</td>
  <td v-if="training.durationInMinutes !== 0">{{training.durationInMinutes}}</td>
  <td v-if="training.durationInMinutes === 0">Duration is not defined.</td>
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
			 .post('rest/trainers/getTrainings', { id: this.trainer.id })
			 .then(response => this.trainings = response.data);
		  });
    },
});