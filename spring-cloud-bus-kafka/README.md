
Usage
=====

config center
-------------

To view config files in `config-center`, via endpoints:

    /{application}/{profile}[/{label}]
    /{application}-{profile}.yml
    /{label}/{application}-{profile}.yml
    /{application}-{profile}.properties
    /{label}/{application}-{profile}.properties

e.g:

    http://localhost:8004/client-1-default.yml
    http://localhost:8004/client-1/default


client (micro service)
----------------------

Access client-1:

    http://127.0.0.1:8081/echo

Access client-2:

    http://127.0.0.1:8082/echo


dynamic load changed config values
----------------------------------

1. Changed `app.foo` value in `client-1.yml`. 
2. Invoke `/refresh` endpoint to force client to reload config files.

        POST http://127.0.0.1:8081/refresh
        Content-Type: application/json

3. Access `http://127.0.0.1:8081/echo` to view the new value.


broadcasting config file change via SpringBus
---------------------------------------------

1. Change `app.foo` value in `client-1.yml` and `client-2.yml`.
2. Invoke `/bus/refresh` endpoint to broadcast the config file change event.

        POST http://127.0.0.1:8081/bus/refresh
        Content-Type: application/json

3. Both `client-1` and `client-2` would reload their config files, which can be confirmed by access `/echo` endpoint.

