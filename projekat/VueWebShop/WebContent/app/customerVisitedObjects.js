Vue.component("customerVisitedObjects-page", {
	data: function () {
		    return {
		      customer: null,
		      sportObjects: null,
		      isSelected: false,
		      text: "",
		      grade: "",
		      selectedSportObject: null
		    }
	},
	template: ` 
<div> 
<ul>
  <li><a class="active" href="#/csp">Profile</a></li>
  <li><a class="active" href="#/ctvp">Trainings</a></li>
  <li><a class="active" href="#/cst">Schedule training</a></li>
  <li><a class="active" href="#/ccm">Buy Membership</a></li>
  <li><a class="active" href="#/cam">Active Membership</a></li>
  <li><a class="active" href="#/cvo">Visited Objects</a></li>
  <li><a href="#/lu">Log out</a></li>
</ul>
<br>
<br>
<table v-if="isSelected === false" style="width:100%" border="1px">
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
  	<tr v-for="s in sportObjects" v-on:click="comment(s)" class="hand">
  		<td>{{s.name}}</td>
  		<td>{{s.type}}</td>
  		<td>{{s.content}}</td>
  		<td>{{s.location}}</td>
  		<td>{{s.averageGrade}}</td>
  		<td><img v-bind:src="s.image" width="260px" Height="160px" alt="Image is not posted!"></td>
  		<td>{{s.workTime}}</td>
  		<td>{{s.status}}</td>
  	</tr>
	</table>	
	
	<div v-if="isSelected">
  		<textarea id="comment" name="comment" rows="8" cols="50" placeholder="Leave a comment..." v-model="text"></textarea>
  		<br>
  		<label for="grade">Grade (between 1 and 5):</label>
		<input type="number" id="grade" name="quantity" min="1" max="5" v-model="grade">
		<br>
		<button v-on:click="submit()">Submit</button>
	</div>

</div>
`
	, 
	methods : {
	
		comment : function(sportObject) {
			this.selectedSportObject = sportObject;
			this.isSelected = true;
		},
		
		submit : function() {
			if (this.text === "" || this.grade === "") {
				toast("All fields must be filled!");
				return;
			}
			
			axios
			.post('rest/comments/create', {
				comment: this.text,
				grade: this.grade,
				sportObjectId: this.selectedSportObject.id,
				customerId: this.customer.id
			})
			.then(response => {
				toast("Comment is sent!");
				this.isSelected = false;
			});
		}
	},
	mounted () {
         axios
         .get('rest/users/activeCustomer')
         .then(response => { 
			this.customer = response.data; 
			axios
			.post('rest/sportobject/getVisitedObjects', { id: this.customer.id })
			.then(response => this.sportObjects = response.data);
		});
    },
    filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	}
});