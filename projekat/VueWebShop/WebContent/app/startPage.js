Vue.component("start-page", {
	data: function () {
		    return {
			  sportObjects : null,
			  sportObject : {},
			  searchText : "",
			  selectObject : {},
			  selected:false,
			  comments: null,
			  trainings: null
		    }
	},
	template: ` 
<div>
<ul>
  <li><a class="active" href="#/">Home</a></li>
  <li><a href="#/lu">Log in</a></li>
  <li><a href="#/ru">Register</a></li>
  <li><a href="#about">About</a></li>
</ul>

<br>

	<div v-if="selected != true">
	<table>
		<tr>
			<td><input type="text" v-model="searchText" v-on:keyup="search"></td>
			<td>&#128269</td>
			<td><button v-on:click="searchASC()">SortNameByACS</button></td>
  			<td><button v-on:click="searchDESC()">SortNameByDESC</button></td>
  			<td><button v-on:click="searchASCLocation()">SortLocationByACS</button></td>
  			<td><button v-on:click="searchDESCLocation()">SortLocationByDESC</button></td>
			<td><button v-on:click="searchDESCGrade()">SortGradeByASC</button></td>
			<td><button v-on:click="searchASCGrade()">SortGradeByDESC</button></td>
	
		</tr>
	</table>
	<br>
	<table style="width:100%" border="1px">
 	<tr>
		<th> Name </th>
    	<th>Type</th>
		<th>Content</th>
    	<th>Location</th>
    	<th style="width:80px">Average Grade</th>
		<th style="width:260px">Image</th>
		<th style="width:90px">Work Time</th>
		<th>Status</th>
  	</tr>
  	<tr v-for="s in sportObjects" v-on:click="selectedObject(s)" class="hand">
  		<td>{{s.name}}</td>
  		<td>{{s.type}}</td>
  		<td>{{s.content}}</td>
  		<td>{{s.location}}</td>
  		<td>{{s.averageGrade}}</td>
  		<td><img v-bind:src="s.image" width="260px" Height="160px" alt="bilo sta"></td>
  		<td>{{s.workTime}}</td>
  		<td>{{s.status}}</td>
  	</tr>
	</table>	
	</div>
	
	<div v-if="selected != false">
	 <table style="width:100%" border="1px">
 	<tr><button v-on:click="unselect">Back</button></tr>
	<tr>
		<th>Name</th>
    	<th>Type</th>
		<th>Content</th>
    	<th>Location</th>
    	<th style="width:80px">Average Grade</th>
		<th style="width:260px">Image</th>
		<th style="width:90px">Work Time</th>
		<th>Status</th>
  	</tr>
  	<tr>
  		<td>{{selectObject.name}}</td>
  		<td>{{selectObject.type}}</td>
  		<td>{{selectObject.content}}</td>
  		<td>{{selectObject.location}}</td>
  		<td>{{selectObject.averageGrade}}</td>
  		<td><img v-bind:src="selectObject.image" width="260px" Height="160px" alt="bilo sta"></td>
  		<td>{{selectObject.workTime}}</td>
  		<td>{{selectObject.status}}</td>
  	</tr>
	</table>
	<br>
	<table style="width:100%" border="1px">
  <tr>
	<th>Comments</th>
  </tr>
  <tr v-for="c in comments">
  <td>{{c.text}}</td>
  </tr>
</table>
<br>
<table style="width:100%" border="1px">
 <tr>
	<th>Name</th>
    <th>Type</th>
	<th style="width:260px">Image</th>
    <th>Description</th>
    <th>Duration</th>
    <th>Trainer</th>
  </tr>
  <tr v-for="training in trainings">
  <td>{{training.name}}</td>
  <td>{{training.type}}</td>
  <td><img v-bind:src="training.image" width="260px" Height="160px" alt="Image is not posted."></td>
  <td>{{training.description}}</td>
  <td v-if="training.durationInMinutes !== 0">{{training.durationInMinutes}}</td>
  <td v-if="training.durationInMinutes === 0">Duration is not defined.</td>
  <td>{{training.trainer.name}} {{training.trainer.lastName}}</td>
  </tr>
</table>
	
	</div>	
</div>	
`
	, 
	methods : {
		search : function() {
		axios 
		.post('rest/sportobject/search', { searchText : this.searchText })
		.then(response => (this.sportObjects = response.data))
		},
		searchASC : function() {
		axios 
		.post('rest/sportobject/searchACS', { searchText : this.searchText })
		.then(response => (this.sportObjects = response.data))
		},
		selectedObject : function(sportskiObjekat) {
			this.selectObject = sportskiObjekat;
			axios
		 	.post('rest/comments/getAprovedComments', { id: this.selectObject.id })
		 	.then(response => this.comments = response.data);
			this.selected = true;
			axios
			.post('rest/trainings/getTrainingsFromSportObject', { id: this.selectObject.id })
			.then(response => this.trainings = response.data); 
		},
		unselect : function(){
			console.log("Vratili smo se na tabelu");
			this.selected = false;
		},
		searchASC : function() {
		axios 
		.post('rest/sportobject/searchASC', { searchText : this.searchText })
		.then(response => (this.sportObjects = response.data))
		},
		searchDESC : function() {
		axios 
		.post('rest/sportobject/searchDESC', { searchText : this.searchText })
		.then(response => (this.sportObjects = response.data))
		},
		searchASCLocation : function() {
		axios 
		.post('rest/sportobject/searchASCLocation', { searchText : this.searchText })
		.then(response => (this.sportObjects = response.data))
		},
		searchDESCLocation : function() {
		axios 
		.post('rest/sportobject/searchDESCLocation', { searchText : this.searchText })
		.then(response => (this.sportObjects = response.data))
		},
		searchASCGrade : function() {
		axios 
		.post('rest/sportobject/searchASCGrade', { searchText : this.searchText })
		.then(response => (this.sportObjects = response.data))
		},
		searchDESCGrade : function() {
		axios 
		.post('rest/sportobject/searchDESCGrade', { searchText : this.searchText })
		.then(response => (this.sportObjects = response.data))
		},
	},
	mounted () {
        axios 
		.get('rest/sportobject/getAll')
		.then(response => {
			this.sportObjects = response.data;
		})
    },
});