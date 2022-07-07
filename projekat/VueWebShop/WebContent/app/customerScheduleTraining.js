Vue.component("customerScheduleTraining-page", {
	data: function () {
		    return {
		      customer: null,
		      trainings: null,
		      selectedTrainingName: null,
		      date: null,
		      time: "",
		      isActive: true

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
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="font-size:50" v-if="isActive">
	<tr>
		<td><label for="training">Training:</label></td>
		<td>
			<select @change="changeSelectedTraining($event)">
				<option v-for="training in trainings" :value="training.id" :key="training.id">{{training.name}}-{{training.type}}-{{training.sportObject.name}}</option>
			</select>
		</td>
	</tr>
	
	
	<tr>
		<td><label for="date">Date:</label></td>
		<td><input type="date" v-model="date" id="date"></td>
	</tr>
	
	<tr>
		<td><label for="time">Time:</label></td>
		<td>
			<select id="time" v-model="time">
				<option>7:00</option>
				<option>8:00</option>
				<option>9:00</option>
				<option>10:00</option>
				<option>11:00</option>
				<option>12:00</option>
				<option>13:00</option>
				<option>14:00</option>
				<option>15:00</option>
				<option>16:00</option>
				<option>17:00</option>
				<option>18:00</option>
				<option>19:00</option>
				<option>20:00</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="schedule()">Schedule</button></td>
	</tr>
</table>

<span v-if="isActive === false">Your membership is not active!</span>

</div>
`
	, 
	methods : {
	
		changeSelectedTraining : function(event) {
			this.selectedTrainingName = event.target.options[event.target.options.selectedIndex].text
		},
		
		schedule : function() {
			if (this.selectedTrainingName === null || this.date === null || this.time === "") {
				toast("All fields must be filled!");
				return;
			}
			
			axios
			.post('rest/scheduledTrainings/schedule', {
			 	trainingName: this.selectedTrainingName,
			 	date: this.date,
			 	time: this.time,
			 	customerId: this.customer.id
			})
			.then(response => toast("Training is scheduled!"));
			
		}
		
	},
	mounted () {
         axios
         .get('rest/users/activeCustomer')
         .then(response => { 
			this.customer = response.data;
			if (this.customer.membership.membershipStatus === "NoActive") {
				this.isActive = false;
				return;
			}
			axios
			.get('rest/trainings/getAll')
			.then(response => { 
				this.trainings = response.data;
				this.selectedTrainingName = this.trainings[0].name + "-" + this.trainings[0].type + "-" + this.trainings[0].sportObject.name; 
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