Vue.component("managerTrainingView-page", {
	data: function () {
		    return {
			  manager: null,
			  sportObject: null,
			  isThereSportObject: true,
			  print: false,
			  trainings: null,
			  trainers: null,
			  mode: "view",
			  selected: null,
			  selectedFile: null,
			  selectedTrainerUsername: ""
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/msp">Profile</a></li>
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

<table v-if="isThereSportObject && mode === 'view'" style="width:100%" border="1px">
 <tr>
	<th>Name</th>
    <th>Type</th>
	<th style="width:260px">Image</th>
    <th>Description</th>
    <th>Duration</th>
    <th>Trainer</th>
  </tr>
  <tr v-for="training in trainings" class="hand" v-on:click="selectedTraining(training)">
  <td>{{training.name}}</td>
  <td>{{training.type}}</td>
  <td><img v-bind:src="training.image" width="260px" Height="160px" alt="Image is not posted."></td>
  <td>{{training.description}}</td>
  <td v-if="training.durationInMinutes !== 0">{{training.durationInMinutes}}</td>
  <td v-if="training.durationInMinutes === 0">Duration is not defined.</td>
  <td>{{training.trainer.name}} {{training.trainer.lastName}}</td>
  </tr>
</table>

<span v-if="print">Manager is not in charge of any sport object.</span>

<table v-if="mode === 'edit'" style="font-size:50">
	<tr>
		<td><label for="name">Name:</label></td>
		<td><input type="text" id="name" v-model="selected.name"></td>
	</tr>
	
	<tr>
		<td><label for="type">Type:</label></td>
		<td><input type="text" id="type" v-model="selected.type"></td>
	</tr>
	
	<tr>
		<td><label>Image:</label></td>
		<td><input type="file" name="file" accept="image/*" @change="onFileSelected""></td>
	</tr>
	
	<tr>
		<td><label for="description">Description:</label></td>
		<td><input type="text" id="description" v-model="selected.description"></td>
	</tr>
	
	<tr>
		<td><label for="duration">Duration in minutes:</label></td>
		<td><input type="number" id="duration" v-model="selected.durationInMinutes"></td>
	</tr>
	
	<tr>
		<td><label for="trainer">Trainer:</label></td>
		<td>
			<select @change="changeSelectedTrainer($event)">
				<option v-for="trainer in trainers" v-bind:selected="trainer.username === selected.trainer.username" :value="trainer.id" :key="trainer.id"> {{ trainer.username }} </option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td><button v-on:click="editTraining(); upload()">Save</button></td>
		<td><button v-on:click="cancelEdit()">Cancel</button></td>
	</tr>
	
</table>

	
</div>`
	, 
	methods : {
		
		selectedTraining : function(training) {
			this.selected = training;
			this.backup = [this.selected.name, this.selected.type, this.selected.description, this.selected.durationInMinutes];
			this.selectedTrainerUsername = this.selected.trainer.username;
			this.mode = "edit";
		},
		
		cancelEdit : function() {
			this.mode = "view";
			this.selected.name = this.backup[0];
			this.selected.type = this.backup[1];
			this.selected.description = this.backup[2];
			this.selected.durationInMinutes = this.backup[3];
		},
		
		onFileSelected : function(event) {
			this.selectedFile = event.target.files[0]
		},
		
		changeSelectedTrainer : function(event) {
			this.selectedTrainerUsername = event.target.options[event.target.options.selectedIndex].text;
		},
		
		upload : function() {
			
			const formData = new FormData();
			formData.append('file', this.selectedFile);
		
			axios
			.post('rest/sportobject/upload', formData)
		},
		
		editTraining : function() {
			if (this.selected.name === "" || this.selected.type === "" || this.selected.description === "" || this.durationInMinutes === "") {
				toast("All fields must be filled! ");
				return;
			}
			
			if (this.selectedFile === null) {
				axios
				.post('rest/trainings/updateTraining', {
				id: this.selected.id,
				name: this.selected.name,
				type: this.selected.type,
				image: this.selected.image,
				description: this.selected.description,
				durationInMinutes: this.selected.durationInMinutes,
				trainerUsername: this.selectedTrainerUsername,
				sportObjectId: this.manager.sportObject.id 
				})
				.then(response => { 
					toast("Training is updated!");
					axios
					.post('rest/trainings/getTrainingsFromSportObject', { id: this.manager.sportObject.id })
					.then(response => {
						 this.trainings = response.data; 
						 axios
						 .get('rest/trainers/getAll')
						 .then(response => this.trainers = response.data);
					}
					);
					this.mode = "view";
				});
			}
			else {
				axios
				.post('rest/trainings/updateTraining', {
				id: this.selected.id,
				name: this.selected.name,
				type: this.selected.type,
				image: "images\\" + this.selectedFile.name,
				description: this.selected.description,
				durationInMinutes: this.selected.durationInMinutes,
				trainerUsername: this.selectedTrainerUsername,
				sportObjectId: this.manager.sportObject.id 
				})
				.then(response => { 
					toast("Training is updated!");
					axios
					.post('rest/trainings/getTrainingsFromSportObject', { id: this.manager.sportObject.id })
					.then(response => {
						 this.trainings = response.data; 
						 axios
						 .get('rest/trainers/getAll')
						 .then(response => this.trainers = response.data);
					}
					);
					this.mode = "view";
				});
			}
			
			
		}
	},
	mounted () {
        axios
         .get('rest/users/activeManager')
         .then(response => { 
			this.manager = response.data;
			if (this.manager.sportObject === null) {
				this.isThereSportObject = false;
				this.print = true
			}
			else {
				axios
				.post('rest/trainings/getTrainingsFromSportObject', { id: this.manager.sportObject.id })
				.then(response => {
					 this.trainings = response.data; 
					 axios
					 .get('rest/trainers/getAll')
					 .then(response => this.trainers = response.data);
				}
				);
			}
		});
		
    },
});