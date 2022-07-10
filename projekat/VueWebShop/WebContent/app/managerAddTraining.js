Vue.component("managerAddTraining-page", {
	data: function () {
		    return {
		      manager : null,
		      selectedTrainerUsername: "",
		      selectedTrainer: null,
		      trainers: null,
		      isThereSportObject: true,
			  print: false,
			  name: "",
			  type: "",
			  selectedFile: null,
			  description: "",
			  duration: ""
			  
		    }
	},
	template: ` 
<div>
<ul>
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
</ul>
<br>
<br>
<table v-if="isThereSportObject" style="font-size:50">
	<tr>
		<td><label for="name">Name:</label></td>
		<td><input type="text" id="name" v-model="name"></td>
	</tr>
	
	<tr>
		<td><label for="type">Type:</label></td>
		<td><input type="text" id="type" v-model="type"></td>
	</tr>
	
	<tr>
		<td><label>Image:</label></td>
		<td><input type="file" name="file" accept="image/*" @change="onFileSelected"></td>
	</tr>
	
	<tr>
		<td><label for="description">Description:</label></td>
		<td><input type="text" id="description" v-model="description"></td>
	</tr>
	
	<tr>
		<td><label for="duration">Duration in minutes:</label></td>
		<td><input type="number" id="duration" v-model="duration"></td>
	</tr>
	
	<tr>
		<td><label for="trainer">Trainer:</label></td>
		<td>
			<select @change="changeSelectedTrainer($event)">
				<option v-for="trainer in trainers" :value="trainer.id" :key="trainer.id"> {{ trainer.username }} </option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="createTraining(); upload()">Save</button></td>
	</tr>
	
</table>

<span v-if="print">Manager is not in charge of any sport object.</span>

</div>
`
	, 
	methods : {
		createTraining : function() {
			if (this.name === "" || this.type === "" || this.selectedFile === null || this.description === "" || this.duration === 0) {
				toast("All fields must be filled! ");
				return;
			}
			axios
			.post('rest/trainers/getTrainerByUsername', { username: this.selectedTrainerUsername })
			.then(response => {
				this.selectedTrainer = response.data;
				axios
				.post('rest/trainings/createTraining', {
					name: this.name,
					type: this.type,
					image: "images\\" + this.selectedFile.name,
					description: this.description,
					durationInMinutes: this.duration,
					sportObjectId: this.manager.sportObject.id,
					trainerId:  this.selectedTrainer.id
				})
				.then(response => toast("Training is added!"));
			});
		},
		
		onFileSelected : function(event) {
			this.selectedFile = event.target.files[0]
		},
		
		upload : function() {
			
			const formData = new FormData();
			formData.append('file', this.selectedFile);
		
			axios
			.post('rest/sportobject/upload', formData)
		},
		
		changeSelectedTrainer : function(event) {
			this.selectedTrainerUsername = event.target.options[event.target.options.selectedIndex].text
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
				.post('rest/sportobject/transformToDTO', { id: this.manager.sportObject.id })
				.then(response => {
			 	if (response.data === "") {
			 		this.isThereSportObject = false;
					this.print = true;	
					return;
				 }
				 axios
				.get('rest/trainers/getAll')
				.then(response => {
					 this.trainers = response.data;
					 this.selectedTrainerUsername = this.trainers[0].username; 
				}
				);
				 
				 });
				
				
			}
		});
    },
});