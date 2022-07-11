Vue.component("administratorPromoCodesView-page", {
	data: function () {
		    return {
		      promoCodes: null
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
	<th>ID</th>
    <th>Start date</th>
	<th>Expiration date</th>
    <th>Quantity</th>
    <th>Discount (%)</th>
  </tr>
  <tr v-for="p in promoCodes">
  <td>{{p.id}}</td>
  <td>{{p.startDate.dayOfMonth}}.{{p.startDate.month}}.{{p.startDate.year}}</td>
  <td>{{p.endDate.dayOfMonth}}.{{p.endDate.month}}.{{p.endDate.year}}</td>
  <td>{{p.quantity}}</td>
  <td>{{p.discountPercentage}}</td>
  <td><button v-on:click="deletePromoCode(p)">Delete</button></td>
  </tr>
</table>
<br>
</div>
`
	, 
	methods : {					
		
		deletePromoCode : function(promoCode) {
			axios
			.post('rest/promocodes/deletePromoCode', { id: promoCode.id })
			.then(response => {
				axios 
				.get('rest/promocodes/getAll')
				.then(response => (this.promoCodes = response.data))
				toast("Promo code is deleted!");
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
		.get('rest/promocodes/getAll')
		.then(response => (this.promoCodes = response.data))
    	},
});