Vue.component("managerWelcomeCustomer-page", {
	data: function () {
		    return {
		      manager : null,
		      sportObject: null,
		      trainings: null,
		      customers: null,
		      selectedTrainingName: "",
		      selectedCustomerUsername: ""
		    }
	},
	template: ` 
<div>
<ul>
 <li><a class="active" href="#/msp">Profile</a></li>
 <li><a class="active" href="#/mwc">Welcome Customer</a></li>
  <li><a class="active" href="#/mcp">Add Content</a></li>
  <li><a class="active" href="#/mat">Add Training</a></li>
  <li><a class="active" href="#/mso">Sport Object</a></li>
  <li><a class="active" href="#/mvt">Trainers</a></li>
  <li><a class="active" href="#/mcpp">Customers</a></li>
  <li><a class="active" href="#/mcvp">Content</a></li>
  <li><a class="active" href="#/mtvp">Trainings</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="font-size:50">
	
	<tr>
		<td><label for="training">Training:</label></td>
		<td>
			<select @change="changeSelectedTraining($event)">
				<option v-for="training in trainings" :value="training.id" :key="training.id">{{training.name}}-{{training.type}}-{{training.sportObject.name}}</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td><label for="customer">Customer:</label></td>
		<td>
			<select @change="changeSelectedCustomer($event)">
				<option v-for="customer in customers" :value="customer.username" :key="customer.username">{{customer.username}}</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="submit()">Submit</button></td>
	</tr>
	
</table>

</div>
`
	, 
	methods : {
		 
		 changeSelectedTraining : function(event) {
			this.selectedTrainingName = event.target.options[event.target.options.selectedIndex].text
		},
		
		changeSelectedCustomer : function(event) {
			this.selectedCustomerUsername = event.target.options[event.target.options.selectedIndex].text
		},
		
		submit : function() {
			if (this.selectedTrainingName === "" || this.selectedCustomerUsername === "") {
				toast("All fields must be filled!");
				return;
			}
			
			axios
			.post("rest/history/welcomeCustomer", {
				training: this.selectedTrainingName,
				customer: this.selectedCustomerUsername 
			})
			.then(response => {
				if (response.data === "") {
					toast("Customer doesn't have active membership or more available trainings!")
				}
				else {
					toast("Success!")
				}
				});
		},
		 
	},
	mounted () {
         axios
         .get('rest/users/activeManager')
         .then(response => { 
			this.manager = response.data 
			axios
			.post('rest/sportobject/transformToDTO', { id: this.manager.sportObject.id })
			.then(response => { 
				this.sportObject = response.data
				axios
				.post('rest/trainings/getTrainingsFromSportObject', { id: this.manager.sportObject.id })
				.then(response => {
					 this.trainings = response.data;
					 this.selectedTrainingName = this.trainings[0].name + "-" + this.trainings[0].type + "-" + this.trainings[0].sportObject.name;
					  axios
					  .get('rest/customers/getAll')
					  .then(response => {
					   this.customers = response.data;
					   this.selectedCustomerUsername = this.customers[0].username; 
					   });
					 }); 
			});			
		});
    },
	filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	}
});