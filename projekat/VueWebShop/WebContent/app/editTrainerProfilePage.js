Vue.component("editTrainerProfile-page", {
	data: function () {
		    return {
		      trainer: null,
		      mode : "dontChangePassword",
		      oldPassword : "",
		      newPassword : "",
		      confirmPassword : ""
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/tsp">Profile</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="font-size:50">
		
	<tr>
		<td><label for="name">Name:</label></td>
		<td><input type="text" v-model="trainer.name" id="name"></td>
	</tr>
	
	<tr>
		<td><label for="surname">Surname:</label></td>
		<td><input type="text" v-model="trainer.lastName" id="surname"></td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="updateTrainer()">Save</button></td>
	</tr>
	
</table>
<br>
<br>
<br>	
<table>
	<tr>
		<td><label for="password"  v-bind:disabled="mode=='dontChangePassword'">Old password:</label></td>
		<td><input type="password" v-model="oldPassword"  v-bind:disabled="mode=='dontChangePassword'" id="password"></td>
	</tr>
	
	<tr>
		<td><label for="newPassword"  v-bind:disabled="mode=='dontChangePassword'">New password:</label></td>
		<td><input type="password" v-model="newPassword" v-bind:disabled="mode=='dontChangePassword'" id="newPassword"></td>
	</tr>
	
	<tr>
		<td><label for="confirmPassword"  v-bind:disabled="mode=='dontChangePassword'">Confirm new password:</label></td>
		<td><input type="password" v-model="confirmPassword" v-bind:disabled="mode=='dontChangePassword'" id="confirmPassword"></td>
	</tr>
	
	<tr>
		<td><button class="center" v-on:click="changePassword()" >Change password</button></td>
		<td><button  v-on:click="updateTrainerPassword()" v-bind:disabled="mode=='dontChangePassword'" >Save</button></td>
	</tr>
</table>


</div>
`
	,
	methods : {
		
		updateTrainer : function() {
			if (this.trainer.name === "" || this.trainer.lastName === "") {
				toast("All fields must be filled!")
				return;
			}
			axios
			.post('rest/trainers/update', this.trainer)
			.then(response => toast("You have successfully updated your profile!"));
			
		},
		
		changePassword : function() {
			this.mode = "changePassword";
		},
		
		updateTrainerPassword : function() {
			if (this.oldPassword === this.trainer.password && this.newPassword === this.confirmPassword) {
				 this.trainer.password = this.newPassword;	
				 axios
				.post('rest/trainers/update', this.trainer)
				.then(response => toast("You have successfully changed your password!"));
			}
			else {
				toast("Try again!");	
			}
			
		}
	
		
	},
	mounted () {
         axios
         .get('rest/users/activeTrainer')
         .then(response => this.trainer = response.data);
    },
    filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	}
});