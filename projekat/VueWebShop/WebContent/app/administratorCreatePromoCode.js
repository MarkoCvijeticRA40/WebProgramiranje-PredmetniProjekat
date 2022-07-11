Vue.component("administratorCreatePromoCode-page", {
	data: function () {
		    return {
			  startDate: null,
			  endDate: null,
		      quantity: null,
		      discount: null
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
<br>
<table>

	<tr>
		<td><label for="startDate">Start date:</label></td>
		<td><input type="date" v-model="startDate" id="startDate"></td>
	</tr>
	
	<tr>
		<td><label for="endDate">End date:</label></td>
		<td><input type="date" v-model="endDate" id="endDate"></td>
	</tr>
	
	<tr>
		<td><label for="quantity">Quantity:</label></td>
		<td><input type="number" v-model="quantity" id="quantity"></td>
	</tr>
	
	<tr>
		<td><label for="discount">Discount (%):</label></td>
		<td><input type="number" step="any" v-model="discount" id="discount"></td>
	</tr>
	
	<tr>
		<td></td>
		<td><button v-on:click="createPromoCode()">Create</button></td>
	</tr>
	
</table>
</div>
`
	, 
	methods : {						
		createPromoCode : function() {
			if (this.startTime === null || this.endTime === null || this.quantity === null || this.discount === null) {
				toast("All fields must be filled!")
				return;
			}
			
			axios
			.post('rest/promoCodes/create', {
				startDate: this.startDate,
				endDate: this.endDate,
				quantity: this.quantity,
				discountPercentage: this.discount
			})
			.then(response => toast("Created!"));
		
			
    	},								
	},
	filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	},
	mounted () {

    		   },
});