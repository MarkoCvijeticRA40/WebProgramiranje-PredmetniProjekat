Vue.component("administratorComments-page", {
	data: function () {
		    return {
		      comments: null
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
<li><a class="active" href="#/also">Sport Objects</a></li>
<li><a class="active" href="#/alt">Trainings</a></li>
<li><a class="active" href="#/acpc">Add Promo Code</a></li>
<li><a class="active" href="#/apcv">Promo Codes</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<table style="width:100%" border="1px">
  <tr>
	<th>Comment</th>
    <th>Customer</th>
	<th>Sport Object</th>
  </tr>
  <tr v-for="c in comments">
  <td>{{c.text}}</td>
  <td>{{c.customer.username}}</td>
  <td>{{c.sportObject.name}}</td>
  <td v-if="!c.aproved && !c.denied"><button v-on:click="aprove(c)">Aprove</button></td>
  <td v-if="!c.aproved && !c.denied"><button v-on:click="denie(c)">Denie</button></td>
  <td v-if="c.aproved">APROVED</td>
  <td v-if="c.denied">DENIED</td>
  </tr>
</table>
<br>
</div>
`
	, 
	methods : {						
		aprove : function(comment) {
			axios
			.post('rest/comments/aprove', { id: comment.id })
			.then(response => { 
				toast("Aproved!");
				axios 
				.get('rest/comments/getAll')
				.then(response => (this.comments = response.data)) 
			})
		},
		
		denie : function(comment) {
			axios
			.post('rest/comments/denie', { id: comment.id })
			.then(response => { 
				toast("Denied!");
				axios 
				.get('rest/comments/getAll')
				.then(response => (this.comments = response.data)) 
			})
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
		.get('rest/comments/getAll')
		.then(response => (this.comments = response.data))
    },
});