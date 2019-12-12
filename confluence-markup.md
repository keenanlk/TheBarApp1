h1. Simple Bar Review API This is a simple API to get Bars and review of the Bars for our CSCI 415 app

*Version:* 1.0.0

----

{toc:printable=true|style=square|minLevel=2|maxLevel=3|type=list|outline=false|include=.*}

h2. Endpoints

    h3. addBar
    {status:colour=Yellow|title=post|subtle=false}
    {code}
    post /api/bars/
    {code}
    *Summary:* Adds a Bar item
    *Description:* Adds a bar to the system


    h4. Parameters

        h5. Body Parameter
        ||Name||Description||Required||Default||Pattern||
        |bar |Bar item to add |(x) | |  |







    h4. Responses
        *Status Code:* 201
        *Message:*     item created
        {code:title=Response Type}

        {code}
        See [#models]



        {code:title=Response Schema |collapse=true}
{
  "description" : "item created"
}
        {code}
        *Status Code:* 400
        *Message:*     invalid input, object invalid
        {code:title=Response Type}

        {code}
        See [#models]



        {code:title=Response Schema |collapse=true}
{
  "description" : "invalid input, object invalid"
}
        {code}
        *Status Code:* 409
        *Message:*     an existing item already exists
        {code:title=Response Type}

        {code}
        See [#models]



        {code:title=Response Schema |collapse=true}
{
  "description" : "an existing item already exists"
}
        {code}
    ----

    h3. addReview
    {status:colour=Yellow|title=post|subtle=false}
    {code}
    post /api/reviews/
    {code}
    *Summary:* Adds a Bar item
    *Description:* Adds a bar to the system


    h4. Parameters

        h5. Body Parameter
        ||Name||Description||Required||Default||Pattern||
        |bar |Bar item to add |(x) | |  |







    h4. Responses
        *Status Code:* 201
        *Message:*     item created
        {code:title=Response Type}

        {code}
        See [#models]



        {code:title=Response Schema |collapse=true}
{
  "description" : "item created"
}
        {code}
        *Status Code:* 400
        *Message:*     invalid input, object invalid
        {code:title=Response Type}

        {code}
        See [#models]



        {code:title=Response Schema |collapse=true}
{
  "description" : "invalid input, object invalid"
}
        {code}
        *Status Code:* 409
        *Message:*     an existing item already exists
        {code:title=Response Type}

        {code}
        See [#models]



        {code:title=Response Schema |collapse=true}
{
  "description" : "an existing item already exists"
}
        {code}
    ----

    h3. searchInventory
    {status:colour=Yellow|title=get|subtle=false}
    {code}
    get /api/bars/
    {code}
    *Summary:* Searches Bars in System
    *Description:* Searches list of current bars 


    h4. Parameters



        h5. Query Parameters
        ||Name||Description||Required||Default||Pattern||
        |barName |pass an optional search string for looking up bar name |(x) | |  |





    h4. Responses
        *Status Code:* 200
        *Message:*     search results matching criteria
        {code:title=Response Type}
array[Bar]
        {code}
        See [#models]



        {code:title=Response Schema |collapse=true}
{
  "description" : "search results matching criteria",
  "schema" : {
    "type" : "array",
    "items" : {
      "$ref" : "#/definitions/Bar"
    }
  }
}
        {code}
        *Status Code:* 400
        *Message:*     bad input parameter
        {code:title=Response Type}

        {code}
        See [#models]



        {code:title=Response Schema |collapse=true}
{
  "description" : "bad input parameter"
}
        {code}
    ----

    h3. searchReview
    {status:colour=Yellow|title=get|subtle=false}
    {code}
    get /api/reviews/
    {code}
    *Summary:* Searches Reviews in System
    *Description:* Searches list of current reviews 


    h4. Parameters



        h5. Query Parameters
        ||Name||Description||Required||Default||Pattern||
        |barName |pass an optional search string for looking up bar name |(x) | |  |





    h4. Responses
        *Status Code:* 200
        *Message:*     search results matching criteria
        {code:title=Response Type}
array[Review]
        {code}
        See [#models]



        {code:title=Response Schema |collapse=true}
{
  "description" : "search results matching criteria",
  "schema" : {
    "type" : "array",
    "items" : {
      "$ref" : "#/definitions/Review"
    }
  }
}
        {code}
        *Status Code:* 400
        *Message:*     bad input parameter
        {code:title=Response Type}

        {code}
        See [#models]



        {code:title=Response Schema |collapse=true}
{
  "description" : "bad input parameter"
}
        {code}
    ----

h2. Models

        h3. Bar
        ||Field Name||Required||Type||Description||
         |BarName | |String | |
        h3. Review
        ||Field Name||Required||Type||Description||
         |id | |Integer | |
 |Bar | |String | |
 |Drinks | |Integer | |
 |Food | |Integer | |
 |Atmosphere | |Integer | |
