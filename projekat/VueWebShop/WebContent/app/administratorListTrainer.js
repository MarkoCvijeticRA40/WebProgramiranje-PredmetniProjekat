Vue.component("listTrainer-page", {
	data: function () {
		    return {
		      trainers : null,
			  searchText : "",
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
<table>
	<td><input type="text" v-model="searchText" v-on:keyup="search"></td>
	<td>&#128269</td>
	<td><button v-on:click="getAllTrainersNameACS">SortNameByACS</button></td>
  	<td><button v-on:click="getAllTrainersNameDESC">SortNameByDESC</button></td>
  	<td><button v-on:click="getAllTrainersLastNameACS">SortLastNameByACS</button></td>
  	<td><button v-on:click="getAllTrainersLastNameDESC">SortLastNameByDESC</button></td>
	<td><button v-on:click="getAllTrainersUserNameACS">SortUserNameByASC</button></td>
	<td><button v-on:click="getAllTrainersLastNameDESC">SortUserNameByDESC</button></td>
	
</table>
<table style="width:100%" border="1px">
 <tr>
	<th>Username</th>
    <th>Name</th>
	<th>LastName</th>
    <th>Gender</th>
    <th>Date of Birth</th>
  </tr>
  <tr v-for="s in trainers">
  <td>{{s.username}}</td>
  <td>{{s.name}}</td>
  <td>{{s.lastName}}</td>
  <td>{{s.gender}}</td>
  <td>&ensp;{{s.dateOfBirth | dateFormat('DD.MM.YYYY')}}</td>
  </tr>
</table>
<br>
</div>
`
	, 
	methods : {						
		search : function() {
		axios 
		.post('rest/trainers/search', { searchText : this.searchText })
		.then(response => (this.trainers = response.data))
		},
		getAllTrainersNameDESC : function() {
			axios
			.get('rest/trainers/getAllNameDESC')
			.then(response => (this.trainers = response.data))
		},
		getAllTrainersNameACS : function() {
			axios
			.get('rest/trainers/getAllNameACS')
			.then(response => (this.trainers = response.data))
		},
		getAllTrainersLastNameACS : function() {
			axios
			.get('rest/trainers/getAllLastNameACS')
			.then(response => (this.trainers = response.data))
		},
		getAllTrainersLastNameDESC : function() {
			axios
			.get('rest/trainers/getAllLastNameDESC')
			.then(response => (this.trainers = response.data))
		},
		getAllTrainersUserNameACS : function() {
			axios
			.get('rest/trainers/getAllLastNameACS')
			.then(response => (this.trainers = response.data))
		},
		getAllTrainersLastNameDESC : function() {
			axios
			.get('rest/trainers/getAllUserNameDESC')
			.then(response => (this.trainers = response.data))
		},
	},
	filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	},
	mounted () {
		axios 
		.get('rest/trainers/getAll')
		.then(response => (this.trainers = response.data))
    		   },
});