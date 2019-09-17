package apps

org.springframework.cloud.contract.spec.Contract.make {
    request { 
        method 'POST' 
        url '/addApp' 
        body([ 
               "name"				:		 $( c(regex(nonBlank())), p('app-1') ),
               "running"			:		 $( c(regex(anyBoolean())), p(true) ),
        ])
        headers { 
            contentType('application/json')
        }
    }
    response { 
        status OK() 
        body([
               "name"				: 		$( c(fromRequest().body('$.name')), p('app-1')),
               "running"			: 		$( c(fromRequest().body('$.running')), p(true)),
               "id"					: 		$(regex(nonBlank())),
			   "createdAt"			: 		$(regex(nonBlank())),
               "lastModifiedDate"	: 		$(regex(nonBlank()))
        ])
    }
}