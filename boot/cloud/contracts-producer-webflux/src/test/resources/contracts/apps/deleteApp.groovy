package apps

org.springframework.cloud.contract.spec.Contract.make {
    request { 
        method 'DELETE'
        url($(c(regex('/deleteApp/(.+)')), p('/deleteApp/2')))
    }
    response { 
        status NO_CONTENT()
    }
}