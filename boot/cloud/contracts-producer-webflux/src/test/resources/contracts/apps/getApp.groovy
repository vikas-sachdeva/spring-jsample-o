package apps

org.springframework.cloud.contract.spec.Contract.make {
    request { 
        method 'GET'
        url '/getApps'
    }
    
        response { 
        status OK() 
        body([
               "name"				: 		$(regex(nonBlank())),
               "running"			: 		$(regex(anyBoolean())),
               "id"					: 		$(regex(nonBlank())),
               "createdAt"			: 		$(regex(nonBlank())),
               "lastModifiedDate"	: 		$(regex(nonBlank()))
        ])
        headers { 
            contentType('application/json')
        }
    }
}