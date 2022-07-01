Vue.component("managerViewTrainers-page", {
	data: function () {
		    return {
		      trainers : null,
		      manager: null,
		      isThereSportObject: false
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/msp">Profile</a></li>
  <li><a class="active" href="#/mcp">Content</a></li>
  <li><a class="active" href="#/mso">Sport Object</a></li>
  <li><a class="active" href="#/mvt">Trainers</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<table style="width:100%" border="1px">
 <tr>
	<th>Username</th>
    <th>Name</th>
	<th>LastName</th>
    <th>Gender</th>
    <th>Date of Birth</th>
  </tr>
  <tr v-for="s in trainers">
  <td>{{s.username}}</td>
  <td>{{s.name}}</td>
  <td>{{s.lastName}}</td>
  <td>{{s.gender}}</td>
  <td>&ensp;{{s.dateOfBirth | dateFormat('DD.MM.YYYY')}}</td>
  </tr>
</table>
<br>
</div>
`
	, 
	methods : {						
										
	},
	mounted () {
		axios
         .get('rest/users/activeManager')
         .then(response => { 
			this.manager = response.data;
			if (this.manager.sportObject === null) {
				this.isThereSportObject = false;
			}
			else {
			axios
			.post('rest/trainers/getAllTrainersFromSportObject', { id: this.manager.sportObject.id })
			.then(response => this.trainers = response.data );
			}
		});
    },
    filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	},
});