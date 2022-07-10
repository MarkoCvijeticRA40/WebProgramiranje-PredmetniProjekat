Vue.component("managerContentView-page", {
	data: function () {
		    return {
			  manager: null,
			  sportObject: null,
			  isThereSportObject: true,
			  print: false,
			  content: null,
			  mode: "view",
			  selected: null,
			  backup: null,
			  selectedFile: null
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

<table v-if="isThereSportObject && mode === 'view'" style="width:100%" border="1px">
 <tr>
	<th>Name</th>
    <th>Type</th>
	<th>Image</th>
    <th>Description</th>
    <th>Duration</th>
  </tr>
  <tr v-for="c in content" class="hand" v-on:click="selectedContent(c)">
  <td>{{c.name}}</td>
  <td>{{c.type}}</td>
  <td><img v-bind:src="c.image" width="260px" Height="160px" alt="Image is not posted."></td>
  <td>{{c.description}}</td>
  <td v-if="c.durationInMinutes !== 0">{{c.durationInMinutes}}</td>
  <td v-if="c.durationInMinutes === 0">Duration is not defined.</td>
  </tr>
</table>

<span v-if="print">Manager is not in charge of any sport object.</span>

<table v-if="mode === 'edit'" style="font-size:50">
	
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
		<td><button v-on:click="editContent(); upload()">Save</button></td>
		<td><button v-on:click="cancelEdit()">Cancel</button></td>
	</tr>
	
</table>

	
</div>`
	, 
	methods : {
		
		selectedContent : function(content) {
			this.selected = content;
			this.backup = [this.selected.name, this.selected.type, this.selected.description, this.selected.durationInMinutes];
			this.mode = "edit";
		},
		
		cancelEdit : function() {
			this.selected.name = this.backup[0];
			this.selected.type = this.backup[1];
			this.selected.description = this.backup[2];
			this.selected.durationInMinutes = this.backup[3];
			this.mode = "view";
		},
		
		onFileSelected : function(event) {
			this.selectedFile = event.target.files[0]
		},
		
		editContent : function() {
			if (this.selected.name === "" || this.selected.type === "") {
				toast("Name, type and image are required fields! ");
				return;
			}
			
			if (this.selectedFile === null) {
				axios
				.post('rest/sportobject/updateContent', {
				name: this.selected.name,
				type: this.selected.type,
				image: this.selected.image,
				description: this.selected.description,
				durationInMinutes: this.selected.durationInMinutes,
				sportObjectId: this.manager.sportObject.id 
				})
				.then(response => { 
					toast("Content is updated!");
					this.mode = "view";
				});
			}
			else {
				axios
				.post('rest/sportobject/updateContent', {
				name: this.selected.name,
				type: this.selected.type,
				image: "images\\" + this.selectedFile.name,
				description: this.selected.description,
				durationInMinutes: this.selected.durationInMinutes,
				sportObjectId: this.manager.sportObject.id 
				})
				.then(response => { 
					toast("Content is updated!");
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
				this.print = true;
			}
			else {
			axios
			.post('rest/sportobject/transformToDTO', { id: this.manager.sportObject.id })
			.then(response => { 
				this.sportObject = response.data
				if (this.sportObject !== "") {
				axios
				.post('rest/sportobject/getContent', { id: this.sportObject.id })
				.then(response => { 
					this.content = response.data;
					});
				}
				 } );
			}
		});
		
    },
});