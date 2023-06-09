Vue.component("trainerStart-page", {
	data: function () {
		    return {
		      trainer : null
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
<table style="font-size:50">
	<tr>
		<td><label for="username">Username:</label></td>
		<td>&ensp;{{trainer.username}}</td>
	</tr>
	
	
	<tr>
		<td><label for="name">Name:</label></td>
		<td>&ensp;{{trainer.name}}</td>
	</tr>
	
	<tr>
		<td><label for="surname">Surname:</label></td>
		<td>&ensp;{{trainer.lastName}}</td>
	</tr>
	
	<tr>
		<td><label for="date">Date of birth:</label></td>
		<td>&ensp;{{trainer.dateOfBirth | dateFormat('DD.MM.YYYY')}}</td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="editTrainer()">Edit</button></td>
	</tr>
</table>

</div>
`
	, 
	methods : {
		 
		 editTrainer : function() {
			router.push("/etp");
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
         .then(response => this.trainer = response.data);
    },
});