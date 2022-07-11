Vue.component("listCustomers-page", {
	data: function () {
		    return {
		      customers : null,
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
	<td><button v-on:click="searchASCPoints()">SortPointsByACS</button></td>
	<td><button v-on:click="searchDESCPoints()">SortPointsByDESC</button></td>
  </table>

  <table style="width:100%" border="1px">
  <tr>
	<th>Username</th>
    <th>Name</th>
	<th>LastName</th>
    <th>Gender</th>
    <th>Date of Birth</th>
	<th>Points</th>
	<th>Type</th>
  </tr>
  <tr v-for="c in customers">
  <td>{{c.username}}</td>
  <td>{{c.name}}</td>
  <td>{{c.lastName}}</td>
  <td>{{c.gender}}</td>
  <td>&ensp;{{c.dateOfBirth | dateFormat('DD.MM.YYYY')}}</td>
  <td>{{c.points}}</td>
  <td>{{c.customerType.name}}</td>	
  </tr>
</table>


<br>
</div>

`
	, 
	methods : {						
		search : function() {
		axios 
		.post('rest/customers/search', { searchText : this.searchText })
		.then(response => (this.customers = response.data))
		},	
		searchNameASC : function() { 
		axios
			.get('rest/customers/getAll1')
			.then(response => (this.customers = response.data))
		},
		searchNameDESC : function() {
			axios
			.get('rest/customers/getAll2')
			.then(response => (this.customers = response.data))
		},
		searchASCLastName : function() {
			axios
			.get('rest/customers/getAll3')
			.then(response => (this.customers = response.data))
		},
		searchDESCLastName : function() {
		axios
			.get('rest/customers/getAll4')
			.then(response => (this.customers = response.data))
		},
		searchASCUserName : function() {
		axios
			.get('rest/customers/getAll5')
			.then(response => (this.customers = response.data))
		},
		searchDESCUserName : function() {
		axios
			.get('rest/customers/getAll6')
			.then(response => (this.customers = response.data))
		},	
		searchASCPoints : function() {
		axios
			.get('rest/customers/getAll7')
			.then(response => (this.customers = response.data))
		},
		searchDESCPoints : function() {
		axios
			.get('rest/customers/getAll8')
			.then(response => (this.customers = response.data))
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
		.get('rest/customers/getAll')
		.then(response => (this.customers = response.data))
    		   },
});