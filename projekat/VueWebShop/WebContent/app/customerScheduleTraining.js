Vue.component("customerScheduleTraining-page", {
	data: function () {
		    return {
		      customer: null,
		      trainings: null
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/csp">Profile</a></li>
  <li><a class="active" href="#/ctvp">Trainings</a></li>
  <li><a class="active" href="#/ccm">Buy Membership</a></li>
  <li><a class="active" href="#/cam">Active Membership</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="font-size:50">
	<tr>
		<td><label for="training">Training:</label></td>
		<td>
			<select @change="changeSelectedTraining($event)">
				<option v-for="training in trainings" :value="training.id" :key="training.id"> {{training.name}}-{{training.sportObject.name}} </option>
			</select>
		</td>
	</tr>
	
	
	<tr>
		<td><label for="name">Name:</label></td>
		<td>&ensp;{{customer.name}}</td>
	</tr>
	
	<tr>
		<td><label for="surname">Surname:</label></td>
		<td>&ensp;{{customer.lastName}}</td>
	</tr>
	
	<tr>
		<td><label for="date">Date of birth:</label></td>
		<td>&ensp;{{customer.dateOfBirth | dateFormat('DD.MM.YYYY')}}</td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="editCustomer()">Edit</button></td>
	</tr>
</table>

</div>
`
	, 
	methods : {
	
		changeSelectedTraining : function(event) {
			this.selectedTrainingName = event.target.options[event.target.options.selectedIndex].text
		}
		
	},
	mounted () {
         axios
         .get('rest/users/activeCustomer')
         .then(response => this.customer = response.data);
    },
    filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	}
});