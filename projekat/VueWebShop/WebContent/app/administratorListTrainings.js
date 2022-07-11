Vue.component("administratorListTrainings-page", {
	data: function () {
		    return {
		      trainings: null
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/asp">Profile</a></li>
  <li><a class="active" href="#/act">Add Trainer</a></li>
  <li><a class="active" href="#/acm">Add Manager</a></li>
   <li><a class="active" href="#/aso">Add Sport Object</a></li>
  <li><a class="active" href="#/lic">Customers</a></li>
  <li><a class="active" href="#/lim">Managers</a></li>
  <li><a class="active" href="#/lit">Trainers</a></li>
<li><a class="active" href="#/lia">Administrators</a></li>
<li><a class="active" href="#/acp">Comments</a></li>
<li><a class="active" href="#/also">Sport Objects</a></li>
<li><a class="active" href="#/alt">Trainings</a></li>
<li><a class="active" href="#/acpc">Add Promo Code</a></li>
<li><a class="active" href="#/apcv">Promo Codes</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>

<table style="width:100%" border="1px">
 <tr>
	<th>Name</th>
    <th>Type</th>
	<th style="width:260px">Image</th>
    <th>Description</th>
    <th>Duration</th>
    <th>Trainer</th>
  </tr>
  <tr v-for="training in trainings" v-on:click="selectedTraining(training)">
  <td>{{training.name}}</td>
  <td>{{training.type}}</td>
  <td><img v-bind:src="training.image" width="260px" Height="160px" alt="Image is not posted."></td>
  <td>{{training.description}}</td>
  <td v-if="training.durationInMinutes !== 0">{{training.durationInMinutes}}</td>
  <td v-if="training.durationInMinutes === 0">Duration is not defined.</td>
  <td v-if="training.trainer !== null">{{training.trainer.name}} {{training.trainer.lastName}}</td>
  <td v-if="training.trainer === null"></td>
  <td><button v-on:click="deleteTraining(training)">Delete</button></td>
  </tr>
</table>
<br>
</div>
`
	, 
	methods : {						
		selectedTraining : function(training) {
			axios
			.post('rest/trainings/deleteTraining', { id: training.id })
			.then(response => {
				axios 
				.get('rest/trainings/getAll')
				.then(response => {
					 this.trainings = response.data;
					 toast("Training is deleted!"); 
				});
			})
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
		.get('rest/trainings/getAll')
		.then(response => (this.trainings = response.data))
    	},
});