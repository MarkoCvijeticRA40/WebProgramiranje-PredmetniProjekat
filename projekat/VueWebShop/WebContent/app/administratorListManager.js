Vue.component("listManager-page", {
	data: function () {
		    return {
		      managers : null,
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
	<td><button v-on:click="searchASCName()">SortNameByACS</button></td>
  	<td><button v-on:click="searchDESCName()">SortNameByDESC</button></td>
  	<td><button v-on:click="searchACSLastName()">SortLastNameByACS</button></td>
  	<td><button v-on:click="searchDECSLastName()">SortLastNameByDESC</button></td>
	<td><button v-on:click="searchACSUserName()">SortUserNameByASC</button></td>
	<td><button v-on:click="searchDECSUserName()">SortUserNameByDESC</button></td>
</table>
<table style="width:100%" border="1px">
 <tr>
	<th>Username</th>
    <th>Name</th>
	<th>LastName</th>
    <th>Gender</th>
    <th>Date of Birth</th>
  </tr>
  <tr v-for="m in managers">
  <td>{{m.username}}</td>
  <td>{{m.name}}</td>
  <td>{{m.lastName}}</td>
  <td>{{m.gender}}</td>
  <td>&ensp;{{m.dateOfBirth | dateFormat('DD.MM.YYYY')}}</td>
  </tr>
</table>
<br>
</div>
`
	, 
	methods : {						
		search : function() {
		axios 
		.post('rest/managers/search', { searchText : this.searchText })
		.then(response => (this.managers = response.data))
		},	
		searchASCName : function() {
		axios
			.get('rest/managers/getAllNameACS')
			.then(response => (this.managers = response.data))
		},	
		searchDESCName : function() {
		axios
			.get('rest/managers/getAllNameDESC')
			.then(response => (this.managers = response.data))
		},	
		searchACSLastName : function() {
		axios
			.get('rest/managers/getAllLastNameACS')
			.then(response => (this.managers = response.data))
		},
		searchDECSLastName : function() {
		axios
			.get('rest/managers/getAllLastNameDESC')
			.then(response => (this.managers = response.data))
		},
		searchACSUserName : function() {
		axios
			.get('rest/managers/getAllUserNameACS')
			.then(response => (this.managers = response.data))
		},
		searchDECSUserName : function() {
		axios
			.get('rest/managers/getAllUserNameDESC')
			.then(response => (this.managers = response.data))
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
		.get('rest/managers/getAll')
		.then(response => (this.managers = response.data))
    		   },
});