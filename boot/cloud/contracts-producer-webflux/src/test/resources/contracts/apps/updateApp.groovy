package apps

org.springframework.cloud.contract.spec.Contract.make {
    request { 
        method 'PUT'
        url($(c(regex('/updateApp/(.+)')), p('/updateApp/3')))
        body([ 
               "name"				:		 $( c(regex(nonBlank())), p('app-3') ),
               "running"			:		 $( c(regex(anyBoolean())), p(false) ),
               "lastModifiedDate"	:		 $( c(regex(nonBlank())), p('2019-09-17T10:44:50.953+0000'))
               
        ])
        headers { 
            contentType('application/json')
        }
    }
    response { 
        status OK() 
        body([
               "name"				: 		$( c(fromRequest().body('$.name')), p('app-3')),
               "running"			: 		$( c(fromRequest().body('$.running')), p(false)),
               "id"					: 		$(regex(nonBlank())),
               "lastModifiedDate"	: 		$(regex(nonBlank())),
               "createdAt"			: 		$(regex(nonBlank()))
        ])
        headers { 
            contentType('application/json')
        }
    }
}