Vue.component("addSportObject-page", {
	data: function () {
		    return {
		      name: "",
		      type: "",
		      address: "",
		      city: "",
		      postalCode: "",
		      longitude: "",
		      latitude: "",
		      startTime: "",
		      endTime: "",
		      selectedFile: null,
		      managers: null,
		      selectedManagerUsername: "",
		      selectedManager: null,
		      createdSportObject: null,
		      mode: "listNotEmpty",
		      username: "",
		      password: "",
		      managerName: "",
		      surname: "",
		      picked: null,
		      pickedF: null,
		      date: null
		      
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
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table>
	<tr>
		<td><label for="name">Name:</label></td>
		<td><input type="text" v-model="name" id="name"></td>
	</tr>
	
	<tr>
		<td><label for="type">Type:</label></td>
		<td><input type="type" v-model="type" id="type"></td>
	</tr>
	
	<tr>
		<td><label for="workTime">Work time:</label></td>
		<td>
			<select id="workTime" v-model="startTime">
				<option>7:00</option>
				<option>7:30</option>
				<option>8:00</option>
				<option>8:30</option>
				<option>9:00</option>
				<option>9:30</option>
				<option>10:00</option>
				<option>10:30</option>
				<option>11:00</option>
				<option>11:30</option>
				<option>12:00</option>
				<option>12:30</option>
				<option>13:00</option>
				<option>13:30</option>
				<option>14:00</option>
				<option>14:30</option>
				<option>15:00</option>
				<option>15:30</option>
				<option>16:00</option>
				<option>16:30</option>
				<option>17:00</option>
				<option>17:30</option>
				<option>18:00</option>
				<option>18:30</option>
				<option>19:00</option>
				<option>19:30</option>
				<option>20:00</option>
				<option>20:30</option>
				<option>21:00</option>
				<option>21:30</option>
				<option>22:00</option>
				<option>22:30</option>
				<option>23:00</option>
				<option>23:30</option>
				<option>00:00</option>
				<option>00:30</option>
				<option>1:00</option>
				<option>1:30</option>
				<option>2:00</option>
				<option>2:30</option>
				<option>3:00</option>
				<option>3:30</option>
				<option>4:00</option>
				<option>4:30</option>
				<option>5:00</option>
				<option>5:30</option>
				<option>6:00</option>
				<option>6:30</option>
			</select>
			-
			<select id="workTime" v-model="endTime">
				<option>7:00</option>
				<option>7:30</option>
				<option>8:00</option>
				<option>8:30</option>
				<option>9:00</option>
				<option>9:30</option>
				<option>10:00</option>
				<option>10:30</option>
				<option>11:00</option>
				<option>11:30</option>
				<option>12:00</option>
				<option>12:30</option>
				<option>13:00</option>
				<option>13:30</option>
				<option>14:00</option>
				<option>14:30</option>
				<option>15:00</option>
				<option>15:30</option>
				<option>16:00</option>
				<option>16:30</option>
				<option>17:00</option>
				<option>17:30</option>
				<option>18:00</option>
				<option>18:30</option>
				<option>19:00</option>
				<option>19:30</option>
				<option>20:00</option>
				<option>20:30</option>
				<option>21:00</option>
				<option>21:30</option>
				<option>22:00</option>
				<option>22:30</option>
				<option>23:00</option>
				<option>23:30</option>
				<option>00:00</option>
				<option>00:30</option>
				<option>1:00</option>
				<option>1:30</option>
				<option>2:00</option>
				<option>2:30</option>
				<option>3:00</option>
				<option>3:30</option>
				<option>4:00</option>
				<option>4:30</option>
				<option>5:00</option>
				<option>5:30</option>
				<option>6:00</option>
				<option>6:30</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td><label for="address">Street and number:</label></td>
		<td><input type="text" v-model="address" id="address"></td>
	</tr>
	
	<tr>
		<td><label for="city">City:</label></td>
		<td><input type="text" v-model="city" id="city"></td>
	</tr>
	
	<tr>
		<td><label for="postalCode">Postal code:</label></td>
		<td><input type="text" v-model="postalCode" id="postalCode"></td>
	</tr>
	
	<tr>
		<td><label for="longitude">Longitude:</label></td>
		<td><input type="number" step="any" v-model="longitude" id="longitude"></td>
	</tr>
	
	<tr>
		<td><label for="latitude">Latitude:</label></td>
		<td><input type="number" step="any" v-model="latitude" id="latitude"></td>
	</tr>
	
	<tr>
		<td><label for="logo">Logo:</label></td>
		<td><input type="file" name="file" accept="image/*" @change="onFileSelected"></td>
	</tr>
	
	<tr>
		<td><label for="manager">Manager:</label></td>
		<td>
			<select @change="changeSelectedManager($event)" v-bind:disabled="mode=='listIsEmpty'">
				<option v-for="manager in managers" :value="manager.id" :key="manager.id"> {{ manager.username }} </option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td><label for="username">Username:</label></td>
		<td><input type="text" v-model="username" id="username" v-bind:disabled="mode=='listNotEmpty'"></td>
	</tr>
	
	<tr>
		<td><label for="password">Password:</label></td>
		<td><input type="password" v-model="password" id="password" v-bind:disabled="mode=='listNotEmpty'"></td>
	</tr>
	
	<tr>
		<td><label for="mname">Name:</label></td>
		<td><input type="text" v-model="managerName" id="mname" v-bind:disabled="mode=='listNotEmpty'"></td>
	</tr>
	
	<tr>
		<td><label for="surname">Surname:</label></td>
		<td><input type="text" v-model="surname" id="surname" v-bind:disabled="mode=='listNotEmpty'"></td>
	</tr>
	
	<tr>
		<td><label for="gender">Gender:</label></td>
		<td><input type="radio" id="male" name="gender" value="male" v-model="picked" v-bind:disabled="mode=='listNotEmpty'">
			<label for="male">Male</label>
			<input type="radio" id="female" name="gender" value="female" v-model="pickedF" v-bind:disabled="mode=='listNotEmpty'">
			<label for="female">Female</label>
		</td>
	</tr>
	
	<tr>
		<td><label for="date">Date of birth:</label></td>
		<td><input type="date" v-model="date" id="date" v-bind:disabled="mode=='listNotEmpty'"></td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="createSportObject(); upload();">Save</button></td>
	</tr>
	
	
</table>
</div>
`
	, 
	methods : {
		 createSportObject : function() {
			if (this.mode === 'listNotEmpty') {
				if (this.name === "" || this.type === "" || this.startTime === "" || this.endTime === "" || this.address === "" || this.city === "" || this.postalCode === "" || this.longitude === "" || this.latitude === "" || this.selectedFile === null || this.selectedManagerUsername === "") {
				toast("All fields must be filled!")
				return;
			}
				axios
				.post('rest/sportobject/create', {
					name: this.name,
					type: this.type,
					startTime: this.startTime,
					endTime: this.endTime,
					streetAndNumber: this.address,
					city: this.city,
					postalCode: this.postalCode,
					longitude: this.longitude,
					latitude: this.latitude,
					image: "images\\" + this.selectedFile.name 
				})
				.then(response => 
	    		{
					this.createdSportObject = response.data;
	    			toast("Sport object created!");
	    			axios
	    			.post('rest/managers/getManagerByUsername', { username: this.selectedManagerUsername })
	    			.then(response =>
	    			{
						this.selectedManager = response.data;
						this.updateManager()
					});
	    		}   		
	    		);
    		}
    		else {
				if (this.name === "" || this.type === "" || this.startTime === "" || this.endTime === "" || this.address === "" || this.city === "" || this.postalCode === "" || this.longitude === "" || this.latitude === "" || this.selectedFile === null || this.username === "" || this.password === "" || this.managerName === "" || this.surname === "" || (this.picked === null && this.pickedF === null) || this.date === null) {
					toast("All fields must be filled!")
					return;
				}
				
				axios
				.post('rest/managers/isUnique', { username: this.username })
				.then(response => {
					if (response.data === 'NO') {
						toast("There is already manager with same username!");
						return;
					}
					
					axios
					.post('rest/sportobject/create', {
					name: this.name,
					type: this.type,
					startTime: this.startTime,
					endTime: this.endTime,
					streetAndNumber: this.address,
					city: this.city,
					postalCode: this.postalCode,
					longitude: this.longitude,
					latitude: this.latitude,
					image: "images\\" + this.selectedFile.name 
				})
				.then(response => 
	    		{
					this.createdSportObject = response.data;
	    			toast("Sport object created!");
	    			if (this.picked === "male") {
		    			axios
						.post('rest/managers/createManagerDTO', {
							username: this.username,
							password: this.password,
							name: this.managerName,
							lastName: this.surname,
							gender: "Male",
							dateOfBirth: this.date,
							objectId: this.createdSportObject.id,
							objectName: this.name,
							type: this.type,
							startTime: this.startTime,
							endTime: this.endTime,
							streetAndNumber: this.address,
							city: this.city,
							postalCode: this.postalCode,
							longitude: this.longitude,
							latitude: this.latitude,
							image: "images\\" + this.selectedFile.name 
							
						})
						
					}
					else {
						axios
						.post('rest/managers/createManagerDTO', {
							username: this.username,
							password: this.password,
							name: this.managerName,
							lastName: this.surname,
							gender: "Female",
							dateOfBirth: this.date,
							objectId: this.createdSportObject.id,
							objectName: this.name,
							type: this.type,
							startTime: this.startTime,
							endTime: this.endTime,
							streetAndNumber: this.address,
							city: this.city,
							postalCode: this.postalCode,
							longitude: this.longitude,
							latitude: this.latitude,
							image: "images\\" + this.selectedFile.name 
							
						})
					}
			    			
	    		}   		
	    		);
					
				})
				
				router.push("/asp");
			}
    		
    		
		},	
		
		updateManager : function() {
			axios
			.post('rest/managers/updateDTO', {
				id: this.selectedManager.id,
				username: this.selectedManager.username,
				password: this.selectedManager.password,
				name: this.selectedManager.name,
				lastName: this.selectedManager.lastName,
				gender: this.selectedManager.gender,
				dateOfBirth: this.selectedManager.dateOfBirth,
				objectId: this.createdSportObject.id,
				objectName: this.name,
				type: this.type,
				startTime: this.startTime,
				endTime: this.endTime,
				streetAndNumber: this.address,
				city: this.city,
				postalCode: this.postalCode,
				longitude: this.longitude,
				latitude: this.latitude,
				image: "images\\" + this.selectedFile.name 
				
			});
		},
		
		
		upload : function() {
			
			const formData = new FormData();
			formData.append('file', this.selectedFile);
		
			axios
			.post('rest/sportobject/upload', formData)
		},
		
		onFileSelected : function(event) {
			this.selectedFile = event.target.files[0]
		},
		
		changeSelectedManager : function(event) {
			this.selectedManagerUsername = event.target.options[event.target.options.selectedIndex].text
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
         .get('rest/managers/getManagersWithoutSportObject')
         .then(response => {
			 this.managers = response.data;
			 if (response.data.length !== 0) {
				this.selectedManagerUsername = this.managers[0].username;
			}
			else {
				this.mode = 'listIsEmpty';
			}
			 })
    },
});