<?xml version="1.0" encoding="utf-8"?>
<partner>
	<name>$fullName$</name>
	<date>$dateOfBirth$</date>
	<email>$emailoto.emailstr$</email>
	<phonenumbertag>
		$phoneList: { it |
			<phone>
				<phoneNo>$it.phoneNumber$</phoneNo>
			</phone>
		}$	
	</phonenumbertag>
	
	<address>
		$addressotm: { it |
			<addresses>
				<street>$it.street$</street>
				<area>$it.area$</area>
				<state>$it.state$</state>
				<zip>$it.zip$</zip>
				<city>$it.city$</city>
				<country>$it.country$</country>
			</addresses>
		}$
	</address>
</partner>