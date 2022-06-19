Vue.component("editCustomerProfile-page", {
	data: function () {
		    return {
		      customer: null,
		      mode : "dontChangePassword",
		      oldPassword : "",
		      newPassword : "",
		      confirmPassword : ""
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/csp">Profile</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table style="font-size:50">
	<tr>
		<td><label for="username">Username:</label></td>
		<td><input type="text" v-model="customer.username" id="username"></td>
	</tr>
	
	
	<tr>
		<td><label for="name">Name:</label></td>
		<td><input type="text" v-model="customer.name" id="name"></td>
	</tr>
	
	<tr>
		<td><label for="surname">Surname:</label></td>
		<td><input type="text" v-model="customer.lastName" id="surname"></td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="updateCustomer()">Save</button></td>
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
		<td><button  v-on:click="updateCustomerPassword()" v-bind:disabled="mode=='dontChangePassword'" >Save</button></td>
	</tr>
</table>



</div>
`
	, 
	methods : {
		
		updateCustomer : function() {
			if (this.customer.username === "" || this.customer.name === "" || this.customer.lastName === "") {
				toast("All fields must be filled!")
				return;
			}
			axios
			.post('rest/customers/update', this.customer)
			.then(response => toast("You have successfully updated your profile!"));
			
		},
		
		changePassword : function() {
			this.mode = "changePassword";
		},
		
		updateCustomerPassword : function() {
			if (this.oldPassword === this.customer.password && this.newPassword === this.confirmPassword) {
				 this.customer.password = this.newPassword;	
				 axios
				.post('rest/customers/update', this.customer)
				.then(response => toast("You have successfully changed your password!"));
			}
			else {
				toast("Try again!");	
			}
			
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