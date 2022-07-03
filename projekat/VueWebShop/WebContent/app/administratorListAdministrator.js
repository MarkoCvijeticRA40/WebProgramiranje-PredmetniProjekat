Vue.component("listAdministrator-page", {
	data: function () {
		    return {
		      administrators : null,
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
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<table>
	<td><input type="text" v-model="searchText" v-on:keyup="search"></td>
	<td>&#128269</td>
	<td><button v-on:click="searchNameASC()">SortNameByACS</button></td>
  	<td><button v-on:click="searchNameDESC()">SortNameByDESC</button></td>
  	<td><button v-on:click="searchASCLastName()">SortLastnameByACS</button></td>
  	<td><button v-on:click="searchDESCLastName()">SortLastNameByDESC</button></td>
	<td><button v-on:click="searchASCUserName()">SortUserNameByACS</button></td>
	<td><button v-on:click="searchDESCUserName()">SortUserNameByDESC</button></td>
</table>
<table style="width:100%" border="1px">
 <tr>
	<th>Username</th>
    <th>Name</th>
	<th>LastName</th>
    <th>Gender</th>
    <th>Date of Birth</th>
  </tr>
  <tr v-for="s in administrators">
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
		.post('rest/administrators/search', { searchText : this.searchText })
		.then(response => (this.administrators = response.data))
		},		
		searchNameASC : function() {
		axios 
		.post('rest/administrators/searchASC', { searchText : this.searchText })
		.then(response => (this.administrators = response.data))
		},
		searchNameDESC : function() {
		axios 
		.post('rest/administrators/searchDESC', { searchText : this.searchText })
		.then(response => (this.administrators = response.data))
		},
		searchASCLastName : function() {
		axios 
		.post('rest/administrators/searchASCLastName', { searchText : this.searchText })
		.then(response => (this.administrators = response.data))
		},
		searchDESCLastName : function() {
		axios 
		.post('rest/administrators/searchDESCLastName', { searchText : this.searchText })
		.then(response => (this.administrators = response.data))
		},
		searchASCUserName : function() {
		axios 
		.post('rest/administrators/searchASCUserName', { searchText : this.searchText })
		.then(response => (this.administrators = response.data))
		},
		searchDESCUserName : function() {
		axios 
		.post('rest/administrators/searchDESCUserName', { searchText : this.searchText })
		.then(response => (this.administrators = response.data))
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
		.get('rest/administrators/getAll')
		.then(response => (this.administrators = response.data))
    		   },
});