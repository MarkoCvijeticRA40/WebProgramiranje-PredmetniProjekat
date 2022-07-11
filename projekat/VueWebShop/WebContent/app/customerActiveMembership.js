Vue.component("customerActive-page", {
	data: function () {
		    return {
				customer:null,
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
	<table style="width:100%" border="1px">
		<tr>
			<th>Start validation:</th>
			<th>End validation:</th>
			<th>Number of terms:</th>
		</tr>
	
		<tr>
			<td>{{customer.membership.startValidation.dayOfMonth}},{{customer.membership.startValidation.month}},{{customer.membership.startValidation.year}}</td>
			<td>{{customer.membership.endValidation.dayOfMonth}},{{customer.membership.endValidation.month}},{{customer.membership.endValidation.year}}</td>
			<td>{{customer.membership.numberOfTerms}}</td>
		</tr>
	</table>
</div>
`
	, 
	methods : {
	
	},
	mounted () {
			axios 
		.get('rest/users/activeCustomer')
		.then(response => (this.customer = response.data))
    },
	filters: {
    	dateFormat: function (value, format) {
    		var parsed = moment(value);
    		return parsed.format(format);
    	}
   	}
});